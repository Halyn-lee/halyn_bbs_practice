<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>board</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<div class="container">
    <h2>게시글 목록</h2>
    <table class="board_list">
        <colgroup>
            <col width="15%"/>
            <col width="*"/>
            <col width="15%"/>
            <col width="20%"/>
        </colgroup>
        <thead>
        <tr>
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty list}">
                <c:forEach items="${list}" var="listItem">
                    <tr>
                        <td><c:out value="${listItem.boardIdx}" /></td>
                        <td class="title">
                            <a href="/board/openBoardDetail.do?boardIdx=${listItem.boardIdx}">
                                <c:out value="${listItem.title}" />
                            </a>
                        </td>
                        <td><c:out value="${listItem.hitCnt}" /></td>
                        <td><c:out value="${listItem.createdDatetime}" /></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4">조회된 결과가 없습니다.</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <a href="/board/openBoardWrite.do" class="btn">글 쓰기</a>
</div>
</body>
</html>