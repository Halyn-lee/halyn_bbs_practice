<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<html>

<div style="text-align: right">
    <form method="post" action="/logout">
        <span>${sessionScope.loginId} 님, 반갑습니다!</span>
        <button type="submit" class="btn">로그아웃</button>
    </form>
</div>

</html>