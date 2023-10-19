<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 18/10/2023
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Creating Events</title>
    <jsp:include page="../components/head.jsp" />
</head>
<body>
    <h1>Create User</h1>
    <div>
        <c:choose>
            <c:when test="${eventResponse.getError().size() > 0}">
                <h2>error</h2>
            </c:when>
            <c:when test="${eventResponse.getResult() != null}">
                <p>no error.</p>
            </c:when>
        </c:choose>
    </div>
    <form action="event" method="${requestScope.get("action")}">
        <div class="form-group">
            <label for="Name">Name:</label>
            <input type="text" class="form-control" id="Name" name="name" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class="form-control" id="description" name="description" required>
        </div>
        <div class="form-group">
            <label for="location">Location:</label>
            <input type="text" class="form-control" id="location" name="location" required>
        </div>
        <div class="form-group">
            <label for="time">Time:</label>
            <input type="time" class="form-control" id="time" name="time" required>
        </div>
        <div class="form-group">
            <label for="date">Date:</label>
            <input type="date" class="form-control" id="date" name="date" required>
        </div>
        <div class="form-group">
            <label>Category:</label>
            <select class="form-control" name="category">
                <option value="1">TEST</option>
            </select>
        </div>
        <c:choose>
            <c:when test="${action eq 'post'}">
                <button type="submit" class="btn btn-primary">Create</button>
            </c:when>
            <c:when test="${action eq 'put'}">
                <div>
                    <button type="submit" class="btn btn-warning">Edit</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </div>
            </c:when>
        </c:choose>
    </form>
    <br>
    <a href="dashboard">List events</a>
</body>
<script>
    window.alert("test");
</script>
</html>
