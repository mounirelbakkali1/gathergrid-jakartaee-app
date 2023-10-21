<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Profile</title>
    <jsp:include page="../../components/head.jsp"></jsp:include>
    <jsp:include page="../../components/profile.jsp"></jsp:include>
    <style>
        input{padding: 8px!important;}
    </style>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"></jsp:include>
<div class="container-fluid main">
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
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 mt-3">
                            <h6 class="mb-2 text-primary">Personal Details</h6>
                        </div>
                        <c:if test="${req.error != null}">
                            <div class="alert alert-danger" role="alert" >
                                <span class="alert-inner--text"><strong>Error!</strong> ${error}</span>
                            </div>
                        </c:if>
                        <form action="profile" method="POST">
                            <div class="row gutters">
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-3">
                                <div class="form-group">
                                    <label for="fullName" class="mb-2">Full Name</label>
                                    <input type="text" name="name" class="form-control" id="fullName" placeholder="Enter full name" value="${user.name}">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-3">
                                <div class="form-group">
                                    <label for="eMail" class="mb-2">Email</label>
                                    <input type="email" name="email" class="form-control" id="eMail" placeholder="Enter email ID" value="${user.email}">
                                </div>
                            </div>
                             </div>
                            <div class="row gutters">
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-3">
                                <div class="form-group">
                                    <label for="password" class="mb-2">Password</label>
                                    <input type="password" name="password" class="form-control" id="password" placeholder="Enter new password">
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12 mt-3">
                                <div class="form-group">
                                    <label for="confirmPassword" class="mb-2">Confirm Password</label>
                                    <input type="password" name="confirmPassword" class="form-control" id="confirmPassword" placeholder="Confirm Password">
                                </div>
                            </div>
                            </div>
                            <div class="row gutters mt-3">
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="text-right">
                                        <button type="submit" id="submit" name="submit" class="btn btn-sm btn-primary" disabled>Update</button>
                                    </div>
                                </div>
                            </div>
                        </form>

                </div>
            </div>
        </div>
    </div>
    <div class="mt-5">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 my-3">
            <h6 class="mb-2 text-primary">My Organizations</h6>
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
            <h6 class="mb-2 text-primary">Mes Events</h6>
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
            <c:choose>
                <c:when test="${fn:length(events) == 0}">
                    <tr>
                        <td colspan="3" class="text-center">No events</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${events}" var="event">
                        <tr>
                            <td>${event.name}</td>
                            <td>${event.description}</td>
                            <td>
                                <a href="/event/${event.id}" class="btn btn-sm btn-primary">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
            </table>
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 my-3">
            <h6 class="mb-2 text-primary">My Reservations</h6>
        </div>
        <table class="table table-striped mb-5">
            <thead>
            <tr>
                <th scope="col">Event</th>
                <th scope="col">Date</th>
                <th scope="col">Quantity</th>
                <th scope="col">Price</th>
                <th scope="col">Type</th>
                <th scope="col">Took</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${fn:length(tickets) == 0}">
                    <tr>
                        <td colspan="6" class="text-center">No tickets</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${tickets}" var="reservation">
                        <tr>
                            <td>${reservation.eventName}</td>
                            <td>${reservation.eventDate}</td>
                            <td>${reservation.quantity}</td>
                            <td>${reservation.price}$</td>
                            <td>
                                <span class="badge bg-${reservation.ticketType=='VIP' ? 'info' : (reservation.ticketType=='STUDENT' ? 'primary' : 'warning ')}">${reservation.ticketType}</span>
                            </td>
                            <td>${reservation.reservationDate}</td>
                            <c:choose>
                                <c:when test="${!LocalDateTime.now().isAfter(reservation.eventDate)}">
                                    <td>
                                        <button class="btn btn-sm btn-danger">cancel</button>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>--</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>

</body>
<jsp:include page="../../components/js-scripts.jsp"></jsp:include>
</html>
