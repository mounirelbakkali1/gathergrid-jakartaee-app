<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 18/10/2023
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating Events</title>
    <jsp:include page="../components/head.jsp" />
</head>
<body>
    <h1>Create User</h1>
    <form action="create-event" method="post">
        <label for="Name">Name:</label>
        <input type="text" id="Name" name="name" required><br>

        <label for="description">description:</label>
        <input type="text" id="description" name="description" required><br>

        <label for="location">location:</label>
        <input type="text" id="location" name="location" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>


        <select name="category">
            <option value="Wedding" >Wedding</option>
            <option value="Festival" >Festival</option>
            <option value="Trade" >Trade</option>
        </select>

        <input type="submit" value="Create">
    </form>
    <br>
    <a href="dashboard">List events</a>
</body>
</html>
