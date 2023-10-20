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
    <h1>Create Events</h1>
    <div>
        <svg xmlns="http://www.w3.org/2000/svg" class="d-none">
            <symbol id="check-circle-fill" viewBox="0 0 16 16">
                <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"></path>
            </symbol>
            <symbol id="exclamation-triangle-fill" viewBox="0 0 16 16">
                <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"></path>
            </symbol>
        </svg>
        <c:choose>
            <c:when test="${response.error != null}">
                <div class="alert alert-danger d-flex align-items-center" role="alert">
                    <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"></use></svg>
                    <c:forEach items="${response.error}" var="error">
                        <div>
                            ${error.message}
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:when test="${response.result != null}">
                <div class="alert alert-success d-flex align-items-center" role="alert">
                    <svg class="bi flex-shrink-0 me-2" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"></use></svg>
                    <div>
                        Event has been added successfully
                    </div>
                </div>
            </c:when>
        </c:choose>
    </div>
    <form action="event" method="${event != null ? 'put' : 'post'}">
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
                <c:forEach items="${categories}" var="categories">
                    <option value="${categories.id}">${categories.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Your Beloved Organizations:</label>
            <select class="form-control" name="organization">
                <c:forEach items="${organizations}" var="organization">
                    <option value="${organization.id}" ${organization.id == 1 ? 'selected' : '' } >${organization.name}</option>
                </c:forEach>
            </select>
        </div>
        <c:choose>
            <c:when test="${event == null}">
                <button type="submit" class="btn btn-primary">Create</button>
            </c:when>
            <c:otherwise>
                <div>
                    <button type="submit" class="btn btn-warning">Edit</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <br>
    <a href="dashboard">List events</a>
</body>
<script>
</script>
</html>
