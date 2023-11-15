<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <td colspan="3"><div id="title" name="title">${board.title}</div></td>
            </tr>
            <tr>
                <td colspan="4" class="view_text">
                    <div id="contents" name="contents" data-content="${board.contents}"></div>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="hidden" id="boardIdx" name="boardIdx" value="${board.boardIdx}" />
        <input type="hidden" id="pageDto" name="pageDto" value="${pageDto.nowPage}" />
    </form>

    <a href="#this" id="list" class="btn">목록으로</a>
    <a href="/board/openBoardEdit.do?boardIdx=${board.boardIdx}&nowPage=${pageDto.nowPage}" id="edit" class="btn">수정하기</a>
    <a href="/board/deleteBoard.do?boardIdx=${board.boardIdx}&nowPage=${pageDto.nowPage}" id="delete" class="btn">삭제하기</a>

    <script type="text/javascript">
        $(document).ready(function(){
            $("#list").on("click", function(){
                var frm = $("#frm")[0];
                location.href = "/board/openBoardList.do?nowPage=${pageDto.nowPage}";
            });

            $("#edit").on("click", function(){
                var frm = $("#frm")[0];
                frm.action = "/board/updateBoard.do";
                frm.submit();
            });

            $("#delete").on("click", function(){
                var frm = $("#frm")[0];
                frm.action = "/board/deleteBoard.do";
                frm.submit();
            });

        // 데이터 속성에 저장된 HTML 내용을 Summernote에 설정
        var content = $('#contents').data('content');
        $('#contents').html(content);
    });
</script>
</body>
</html>
