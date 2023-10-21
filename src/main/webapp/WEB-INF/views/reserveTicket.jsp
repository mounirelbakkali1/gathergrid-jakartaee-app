<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Reserve Ticket</title>
    <jsp:include page="../../components/head.jsp" />
</head>
<body>
<div class="container">
    <h1>Reserve Ticket for Event</h1>
    <form action="${pageContext.request.contextPath}/new-reservation" method="post">
       <%-- error list--%>
         <c:if test="${not empty errors}">
              <div class="alert alert-danger" role="alert">
                <ul>
                     <c:forEach items="${errors}" var="error">
                          <li>${error}</li>
                     </c:forEach>
                </ul>
              </div>
            </c:if>
        <%-- success message --%>
        <!-- Event Info -->
       <div class="row">
           <div class="col mb-3">
               <label for="eventName" class="form-label">Event Name</label>
               <input type="text" class="form-control" id="eventName" name="eventName" value="${event.name}"  disabled>
           </div>
           <div class="col">
               <div class="row">
                   <div class="col mb-3">
                       <label for="eventDate" class="form-label">Event Date</label>
                       <input type="text" class="form-control" id="eventDate" name="eventDate" value="${event.date}" readonly disabled>
                   </div>
                   <div class="col mb-3">
                       <label for="eventTime" class="form-label">Time</label>
                       <input type="text" class="form-control" id="eventTime" name="eventDate" value="${event.hour}" readonly disabled>
                   </div>
               </div>
           </div>
       </div>
        <div class="row">
            <div class="col mb-3">
                <label for="Location" class="form-label">Event Location</label>
                <input disabled class="form-control" id="Location" name="Location" readonly value="${event.location}"/>
            </div>
            <div class="col mb-3">
                <label for="Price" class="form-label">Ticket Price</label>
                <input disabled class="form-control" id="Price" name="Price" readonly value="25$"/>
            </div>
        </div>
        <div class="mb-3">
            <label for="eventDescription" class="form-label">Event Description</label>
            <textarea disabled class="form-control" id="eventDescription" name="eventDescription" rows="4" readonly>${event.description}</textarea>
        </div>

        <!-- Ticket Information -->
        <div class="mb-3">
            <label for="ticketType" class="form-label">Select Ticket Type</label>
            <select class="form-select" id="ticketType" name="ticketType">
                <c:forEach items="${ticketTypes}" var="ticket">
                    <option value="${ticket}">${ticket}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="quantity" class="form-label">Quantity</label>
            <input type="number" class="form-control" id="quantity" name="quantity" required>
        </div>

        <button type="submit" class="btn btn-primary">Reserve Ticket</button>
    </form>
</div>
</body>
</html>
