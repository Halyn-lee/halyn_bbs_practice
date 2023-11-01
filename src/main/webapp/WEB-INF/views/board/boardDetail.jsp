<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>게시글 상세 화면</title>
    <link rel="stylesheet" href="/css/style.css"/>
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
                <td><c:out value="${board.boardIdx}" /></td>
                <th scope="row">조회수</th>
                <td><c:out value="${board.hitCnt}" /></td>
            </tr>
            <tr>
                <th scope="row">작성자</th>
                <td><c:out value="${board.creatorId}" /></td>
                <th scope="row">작성일</th>
                <td><c:out value="${board.createdDatetime}" /></td>
            </tr>
            <tr>
                <th scope="row">제목</th>
                <td colspan="3"><input type="text" id="title" name="title" value="<c:out value="${board.title}" />"/></td>
            </tr>
            <tr>
                <td colspan="4" class="view_text">
                    <textarea title="내용" id="contents" name="contents"><c:out value="${board.contents}" /></textarea>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="hidden" id="boardIdx" name="boardIdx" value="<c:out value="${board.boardIdx}" />">
    </form>

    <a href="<c:url value='/board/openBoardList.do' />" id="list" class="btn">목록으로</a>
    <a href="#this" id="edit" class="btn">수정하기</a>
    <a href="#this" id="delete" class="btn">삭제하기</a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#list").on("click", function(){
            location.href = "<c:url value='/board/openBoardList.do' />";
        });

        $("#edit").on("click", function(){
            var frm = $("#frm")[0];
            frm.action = "<c:url value='/board/updateBoard.do' />";
            frm.submit();
        });

        $("#delete").on("click", function(){
            var frm = $("#frm")[0];
            frm.action = "<c:url value='/board/deleteBoard.do' />";
            frm.submit();
        });
    });
</script>
</body>
</html>