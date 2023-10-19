<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 18/10/2023
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>>
    <jsp:include page="../components/head.jsp" />
</head>
<body>
<h1>Create Category</h1>
<form action="category" method="post">
    <div>
        <label for="Name">Name:</label>
        <input type="text" id="Name" name="name" required>
    </div>
    <div>
        <label for="description">description:</label>
        <input type="text" id="description" name="description" required>
    </div>
    <input type="submit" value="Create">
</form>

</body>
</html>
