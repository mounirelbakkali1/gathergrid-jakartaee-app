<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 10/11/2023
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<form action="register" method="post">
    <input type="text" name="name" placeholder="name">
    <input type="text" name="username" placeholder="username">
    <input type="email" name="email" placeholder="email">
    <input type="password" name="password" placeholder="password">
    <input type="password" name="confirmPassword" placeholder="confirm password">
    <input type="submit" value="Register">
</form>
</body>
</html>
