<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard</title>
    <jsp:include page="../components/head.jsp" />
    <jsp:include page="../components/table.jsp"/>
</head>
<body>

    <h1>you are in dashboard</h1>
    <h4>List of your organization</h4>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${organizations}" var="organization">
                <tr>
                    <td data-label="first-name">${organization.name}</td>
                    <td data-label="last-name">${organization.description}</td>
                    <td data-label="last-name">
                        <button id="deleteOrganization" class="btn btn-danger" onclick="deleteOrganization(${organization.id})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div>
        <div class="d-flex justify-content-between">
            <h4>List of your events</h4>
            <a href="event">Create Event</a>
        </div>
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Location</th>
                    <th>Organization</th>
                    <th>Date</th>
                    <th>Hour</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${events}" var="events">
                    <tr>
                        <td data-label="first-name">${events.name}</td>
                        <td data-label="last-name">${events.description}</td>
                        <td data-label="last-name">${events.category.name}</td>
                        <td data-label="first-name">${events.location}</td>
                        <td data-label="last-name">${events.organization.name}</td>
                        <td data-label="first-name">${events.date}</td>
                        <td data-label="last-name">${events.hour}</td>
                        <td data-label="last-name">
                            <button class="btn btn-warning" >Edit</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
<jsp:include page="../components/js-scripts.jsp"></jsp:include>
<script>
    function deleteOrganization(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/api/organizations/" + id,
            type: 'DELETE',
            success: function(data,status) {
                console.log(status)
               if(status==="success") location.reload();
            }
        });
    }
</script>
</html>
