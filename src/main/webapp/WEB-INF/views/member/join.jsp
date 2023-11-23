<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html> 
	<head> 
    	<meta charset="utf-8">
        <title>회원가입 임시폼</title>
	</head> 
<body>
    <h1>회원가입</h1>
    <form action="/insertMember.do" method="post">
        <label for="login_id">ID:</label>
        <input type="text" name="loginId" id="login_id" required><br>

        <label for="password">비밀번호:</label>
        <input type="password" name="password" id="password" required><br>

        <label for="name">이름:</label>
        <input type="text" name="name" id="name" required><br>

        <label for="gender">성별:</label>
        <select name="gender" id="gender">
            <option value="M">남성</option>
            <option value="F">여성</option>
        </select><br>

        <label for="birthday">생년월일:</label>
        <input type="date" name="birthday" id="birthday" required><br>

        <label for="phone">전화번호:</label>
        <input type="text" name="phone" id="phone" required><br>

        <label for="email">이메일:</label>
        <input type="email" name="email" id="email" required><br>

        <input type="submit" value="가입하기">
    </form>
</body>
</html>