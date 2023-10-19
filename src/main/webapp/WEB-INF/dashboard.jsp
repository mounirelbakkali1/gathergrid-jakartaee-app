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
        <h4>List of your events</h4>
        <a href="create-event">Create Event</a>
    </div>
</body>
<jsp:include page="../components/js-scripts.jsp"></jsp:include>
<script>
    function deleteOrganization(id) {
        $.ajax({
            url: "http://localhost:8080/GatherGrid-1.0-SNAPSHOT/api/organizations/" + id,
            type: 'DELETE',
            success: function(data,status) {
                console.log(status)
               if(status==="success") location.reload();
            }
        });
    }
</script>
</html>
