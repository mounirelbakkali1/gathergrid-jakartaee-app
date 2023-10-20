<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Profile</title>
    <jsp:include page="../components/head.jsp"></jsp:include>
    <jsp:include page="../components/profile.jsp"></jsp:include>
</head>
<body>
<div class="container">
    <div class="row gutters">
        <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
                <div class="card-body">
                    <div class="account-settings">
                        <div class="user-profile">
                            <div class="user-avatar">
                                <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Maxwell Admin">
                            </div>
                            <h5 class="user-name">${user.name}</h5>
                            <h6 class="user-email">${user.email}</h6>
                        </div>
                        <div class="about">
                            <div class="card">
                                <div class="card-body text-center">
                                    <div class="row">
                                        <div class="col-4 border-end border-light">
                                            <h6 class="text-muted mt-1 mb-2 fw-normal">Events</h6>
                                            <h6 class="mb-0 fw-bold">${fn:length(events)}</h6>
                                        </div>
                                        <div class="col-4 border-end border-light">
                                            <h6 class="text-muted mt-1 mb-2 fw-normal">Org</h6>
                                            <h6 class="mb-0 fw-bold">${fn:length(user.organizations)}</h6>
                                        </div>
                                        <div class="col-4">
                                            <h6 class="text-muted mt-1 mb-2 fw-normal">Tickets</h6>
                                            <h6 class="mb-0 fw-bold">${fn:length(events)}</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
                <div class="card-body">
                    <div class="row gutters">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mt-3">
                            <h6 class="mb-2 text-primary">Personal Details</h6>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-3">
                            <div class="form-group">
                                <label for="fullName" class="mb-2">Full Name</label>
                                <input type="text" class="form-control" id="fullName" placeholder="Enter full name" value="${user.name}">
                            </div>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-3">
                            <div class="form-group">
                                <label for="eMail" class="mb-2">Email</label>
                                <input type="email" class="form-control" id="eMail" placeholder="Enter email ID" value="${user.email}">
                            </div>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-3">
                            <div class="form-group">
                                <label for="phone" class="mb-2">Phone</label>
                                <input type="text" class="form-control" id="phone" placeholder="Enter phone number">
                            </div>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-3">
                            <div class="form-group">
                                <label for="website" class="mb-2">Website URL</label>
                                <input type="url" class="form-control" id="website" placeholder="Website url">
                            </div>
                        </div>
                    </div>
                    <div class="row gutters mt-3">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="text-right">
                                <button type="button" id="submit" name="submit" class="btn btn-sm btn-primary">Update</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="mt-5">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 my-3">
            <h6 class="mb-2 text-primary">Your Organizations</h6>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Desc</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${user.organizations}" var="organization">
                <tr>
                    <td>${organization.name}</td>
                    <td>${organization.description}</td>
                    <td>
                        <a href="/organization/${organization.id}" class="btn btn-sm btn-primary">View</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 my-3">
            <h6 class="mb-2 text-primary">Your Events</h6>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Desc</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${user.events}" var="event">
                <tr>
                    <td>${event.name}</td>
                    <td>${event.description}</td>
                    <td>
                        <a href="/event/${event.id}" class="btn btn-sm btn-primary">View</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
    </div>
</div>

</body>
<jsp:include page="../components/js-scripts.jsp"></jsp:include>
</html>
