<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시글 등록</title>
    <%@ include file="../header.jsp" %>
</head>
<body>

<div class="container">
    <h2>게시글 등록</h2>
    <form id="frm" name="frm" method="post">
        <table class="board_detail">
            <tr>
                <td>제목</td>
                <td>
                    <input type="text" id="title" name="title"
                           value="<c:if test='${error eq "error"}'>${board.title}</c:if>"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                <textarea id="summernote" name="contents">
                    <c:if test='${error eq "error"}'>${board.contents}</c:if>
                </textarea>
                </td>
            </tr>
        </table>
        <input type="button" id="save" value="저장" class="btn">
        <input type="hidden" id="creator_id" name="creatorId" value=${sessionScope.loginId}>
    </form>
</div>

<script>
    $(document).ready(function () {
        $("#save").click(function () {
            var frm = $("#frm")[0];
            var formData = new FormData(frm);

            $.ajax({
                type: "POST",
                url: "/board/openBoardWrite.do",
                contentType: "application/json",
                data: JSON.stringify(Object.fromEntries(formData)),
                success: function (data) {
                    if (data.result) {
                        location.href = "/board/openBoardList.do"
                    } else {
                        alert("글 작성 실패~");
                    }
                },
                error: function () {
                    alert("서버 오류 발생!");
                    location.href = "/error"
                }
            });
        });

        var sn;
        sn = $('#summernote').summernote({
            height: 300,
            minHeight: null,
            maxHeight: null,
            focus: true,
            lang: "ko-KR",
            placeholder: '최대 2048자까지 쓸 수 있습니다'
        });

    });
</script>
</body>
</html>
