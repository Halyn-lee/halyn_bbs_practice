<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>게시글 상세 화면</title>
    <link rel="stylesheet" href="/css/summernote/summernote-lite.css">
    <link rel="stylesheet" href="/css/style.css"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/summernote/summernote-lite.js"></script>
    <script src="/js/summernote/lang/summernote-ko-KR.js"></script>
</head>
<body>
<div class="container">
    <h2>게시글 상세 화면</h2>
    <form id="frm" method="post">
        <table class="board_detail">
            <colgroup>
                <col width="15%"/>
                <col width="35%"/>
                <col width="15%"/>
                <col width="35%"/>
            </colgroup>
            <caption>게시글 상세내용</caption>
            <tbody>
            <tr>
                <th scope="row">글 번호</th>
                <td>${board.boardIdx}</td>
                <th scope="row">조회수</th>
                <td>${board.hitCnt}</td>
            </tr>
            <tr>
                <th scope="row">작성자</th>
                <td>${board.creatorId}</td>
                <th scope="row">작성일</th>
                <td>${board.createdDatetime}</td>
            </tr>
            <tr>
                <th scope="row">제목</th>
                <td colspan="3">
                    <div id="title" name="title">${board.title}</div>
                </td>
            </tr>
            <tr>
                <td colspan="4" class="view_text">
                    <div id="contents" name="contents" data-content="${board.contents}"></div>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="hidden" id="boardIdx" name="boardIdx" value="${board.boardIdx}"/>
        <input type="hidden" id="pageDto" name="pageDto" value="${pageDto.nowPage}"/>
    </form>

    <a href="#this" id="list" class="btn">목록으로</a>
    <a href="/board/openBoardEdit.do?boardIdx=${board.boardIdx}&nowPage=${pageDto.nowPage}" id="edit"
       class="btn">수정하기</a>
    <a id="delete" class="btn">삭제하기</a>

    <hr/>
    <h4>댓글 작성</h4>
    <form action="/board/openCommentWrite.do" method="post">
        <input type="hidden" id="creator_id" name="creatorId" value="admin">
        <p>
            <textarea name="contents">${comment.contents}</textarea>
        </p>
        <button type="submit" class="btn">확인</button>
        <input type="hidden" name="boardIdx" value="${board.boardIdx}"/>
        <input type="hidden" name="pageDto" value="${pageDto.nowPage}"/>
    </form>

    <hr class="comment-hr"/>
    <h4>댓글 목록</h4>
    <div id="comment-list">
        <c:forEach var="comment" items="${commentList}">
            <div class="comment" id="comment-${comment.commentIdx}">
                <p>작성자: ${comment.creatorId}</p>
                <p>작성일: ${comment.createdDatetime}</p>
                <p>내용: <span class="contents">${comment.contents}</span></p>
                <button type="button" class="btn cmtModify">수정</button>
                <button type="button" class="btn cmtDelete" data-comment-idx="${comment.commentIdx}">삭제</button>
            </div>
            <hr class="comment-hr"/>
        </c:forEach>
    </div>
    <script type="text/javascript">
        $(function () {

            $("#list").on("click", function () {
                var frm = $("#frm")[0];
                location.href = "/board/openBoardList.do?nowPage=${pageDto.nowPage}";
            });

            $("#edit").on("click", function () {
                var frm = $("#frm")[0];
                frm.action = "/board/updateBoard.do";
                frm.submit();
            });

            // PageDto(totalItemCount=0, pageSize=5, itemCountByPage=10, nowPage=1)

            $("#delete").click(function () {
                const isConfirm = confirm("진짜 삭제 할거임?");
                if (!isConfirm) {
                    return; // 페이지 잔류
                }

                $.ajax({
                    url: "/board/deleteBoard.do?boardIdx=${board.boardIdx}&nowPage=${pageDto.nowPage}",
                    type: "POST",
                    success: function (data) {
                        if (data) {
                            window.location.href = "/board/openBoardList.do?nowPage=" + data.nowPage
                        } else {
                            alert("삭제 처리 실패");
                        }
                    },
                    error: function () {
                        alert("요청 에러");
                    }
                });
            });

            $("#comment-list").on("click", ".cmtDelete", function () {
                var commentIdx = $(this).data("comment-idx");
                var boardIdx = $("#boardIdx").val();
                var nowPage = $("#pageDto").val();

                const isConfirm = confirm("댓글 진짜로 삭제해용?");
                if (!isConfirm) {
                    return;
                }

                const $comment = $(this).closest(".comment"); // 삭제할 댓글 요소
                const $hr = $comment.next(".comment-hr"); // & 구분선

                $.ajax({
                    url: "/board/deleteComment.do",
                    type: "POST",
                    data: {
                        commentIdx: commentIdx,
                        boardIdx: boardIdx,
                        nowPage: nowPage
                    },
                    success: function (data) {
                        if (data.success) {
                            $comment.remove();
                            $hr.remove();
                        } else {
                            alert("댓글 삭제 실패");
                        }
                    },
                    error: function () {
                        alert("요청 에러");
                    }
                });
            });


            $(".cmtModify").click(function () {
                const parentDiv = $(this).parent();
                const orgContents = parentDiv.find(".contents");
                const html = `
    <div class="comment-edit">
        <textarea>${orgContents.text()}</textarea>
        <button type="button" class="btn" id="addBtn">수정완료</button>
    </div>
    `;
                parentDiv.hide();
                parentDiv.before(html);

                $("#addBtn").click(function () {
                    const afterComment = $(this).siblings("textarea").val();

                    const isConfirm = confirm("내용 진짜로 수정할 거에용?");
                    if (!isConfirm) {
                        return;
                    }

                    $.ajax({
                        url: "/board/updateComment.do",
                        type: "POST",
                        data: {
                            commentIdx: parentDiv.attr("id").split("-")[1],
                            boardIdx: $("#boardIdx").val(),
                            nowPage: $("#pageDto").val(),
                            contents: afterComment
                        },
                        success: function (data) {
                            parentDiv.find(".contents").text(afterComment);
                            parentDiv.show();
                            parentDiv.siblings(".comment-edit").remove();
                        },
                        error: function () {
                            alert("댓글 수정 실패");
                            parentDiv.show();
                            parentDiv.siblings(".comment-edit").remove();
                        }
                    });
                });
            });

            // 데이터 속성에 저장된 HTML 내용을 Summernote에 설정
            var content = $('#contents').data('content');
            $('#contents').html(content);
        });

    </script>
</body>
</html>
