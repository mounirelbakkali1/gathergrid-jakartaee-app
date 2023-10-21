<%@ page import="ma.youcode.gathergrid.domain.Event" %><%--
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
    <jsp:include page="../components/my-events.jsp" />
</head>
<body>

    <div>
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
                        Event has been affected successfully
                    </div>
                </div>
            </c:when>
        </c:choose>
    </div>
    <form id="eventForm" action="event" method="post">
        <input id="formAction" hidden="hidden" name="action" value="${event == null ? "post" : "put"}" />
        <input name="id" hidden="hidden" value="${event.id}" />
        <div class="form-group">
            <label for="Name">Name:</label>
            <input value="${event.name}" type="text" class="form-control" id="Name" name="name" placeholder="Event Name" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <input value="${event.description}" type="text" class="form-control" id="description" name="description"  placeholder="Event Description"  required>
        </div>
        <div class="form-group">
            <label for="location">Location:</label>
            <input value="${event.location}" type="text" class="form-control" id="location" name="location" placeholder="Event Location" required>
        </div>
        <%
            Event event = (Event) request.getAttribute("event");
            String _time = "00:00";
            if(event != null){
                _time = event.getHour().toLocalTime().toString();
            }

        %>
        <div class="form-group">
            <label for="time">Time:</label>
            <input value="<%= _time %>" type="time" class="form-control" id="time" name="time" required>
        </div>
        <div class="form-group">
            <label for="date">Date:</label>
            <input value="${event.date}" type="date" class="form-control" id="date" name="date" required>
        </div>
        <div class="form-group">
            <label>Category:</label>
            <select class="form-control" name="category">
                <c:forEach items="${categories}" var="category">
                    <option ${event.category.id == category.id ? 'selected' : ''} value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Your Beloved Organizations:</label>
            <select class="form-control" name="organization">
                <c:forEach items="${organizations}" var="organization">
                    <option value="${organization.id}" ${event.organization.id == organization.id ? 'selected' : ''} >${organization.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Available Places:</label>
            <input class="form-control" type="number" min="10" value="10" required name="maxTickets">
        </div>
        <c:choose>
            <c:when test="${event == null}">
                <button type="submit" class="btn btn-primary">Create</button>
            </c:when>
            <c:otherwise>
                <div>
                    <button type="submit" class="btn btn-warning">Edit</button>
                    <button onclick="setActionThenDelete()" type="button" class="btn btn-danger">Delete</button>
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <br>
    <a href="dashboard">List events</a>
    </div>
</body>
<script>
    function setActionThenDelete(){
        //$("#formAction").val("delete")
        //$("#eventForm").submit()
    }
    function prepareModal(name){
        console.log(name)
        $("#addEmployeeModal #modalName").val(name);
    }
</script>
</html>
