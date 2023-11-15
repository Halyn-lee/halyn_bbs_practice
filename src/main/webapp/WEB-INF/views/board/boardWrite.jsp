<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <title>board</title>
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" href="/css/summernote/summernote-lite.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/js/summernote/summernote-lite.js"></script>
    <script src="/js/summernote/lang/summernote-ko-KR.js"></script>
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
                           value="<c:if test='${error eq "error"}'>${board.title}</c:if>" />
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
        <input type="submit" id="submit" value="저장" class="btn">
        <input type="hidden" id="creator_id" name="creatorId" value="admin">
    </form>
</div>

<script>
$(document).ready(function() {
    var sn;
    sn = $('#summernote').summernote({
        height: 300,                 // 에디터 높이
        minHeight: null,             // 최소 높이
        maxHeight: null,             // 최대 높이
        focus: true,                // 에디터 로딩 후 포커스를 맞출지 여부
        lang: "ko-KR",              // 한글 설정
        placeholder: '최대 2048자까지 쓸 수 있습니다' // placeholder 설정
    });

    if ("${error}" == "error") {
        alert("글 작성 실패~");
    }
});
</script>
</body>
</html>
