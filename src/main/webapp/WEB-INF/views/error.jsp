<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<div>
    <h1>Error Page</h1>
    <b>에러 코드 : </b><span>${requestScope["code"]}</span>
    <br><b>에러 메세지 : </b><span>${requestScope["msg"]}</span>
    <br><b>시간 : </b><span>${timestamp}</span>
</div>
</body>
</html>
