<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 18/10/2023
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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


<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.name}</td>
            <td>${category.description}</td>
            <td>
                <a href="/yourapp/category?action=edit&id=${category.id}">Edit</a> |
                <a href="/yourapp/category?action=delete&id=${category.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
