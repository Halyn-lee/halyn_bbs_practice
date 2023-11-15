<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>게시글 수정</title>
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" href="/css/summernote/summernote-lite.css">
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
                <td colspan="3"><input type="text" id="title" name="title" value="${board.title}" /></td>
            </tr>
            <tr>
                <td colspan="4" class="view_text">
                    <textarea title="내용" id="summernote" name="contents">${board.contents}</textarea>
                </td>
            </tr>
            </tbody>
        </table>
        <input type="hidden" id="boardIdx" name="boardIdx" value="${board.boardIdx}" />
        <input type="hidden" id="nowPage" name="nowPage" value="${pageDto.nowPage}" />
    </form>
    <a href="javascript:history.back();" id="list" class="btn">수정취소</a>
    <a href="#this" id="edit" class="btn">수정완료</a>
</div>

<script type="text/javascript">
    $(document).ready(function(){

        $("#edit").on("click", function(){
            var frm = $("#frm")[0];
            frm.action = "/board/updateBoard.do";
            frm.submit();
        });

        $('#summernote').summernote({
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
