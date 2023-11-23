<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../header.jsp"%>

<div class="container">
<form method="POST" action="/login">
    <label for="login_id">ID:</label>
    <input type="text" name="loginId" id="login_id" required><br>

    <label for="password">비밀번호:</label>
    <input type="password" name="password" id="password" required><br>
    <!--
    <c:if test="${loginStatus eq 'success'}">
        <p>${sessionScope.loginId} 님 반갑어요~</p>
    </c:if> -->
    <button type="submit" class="btn">고고</button>
</form>
</div>


<%@ include file="../footer.jsp"%>