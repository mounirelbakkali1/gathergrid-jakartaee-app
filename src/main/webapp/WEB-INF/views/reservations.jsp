<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My Reservations</title>
</head>
<body>



<h3>My Reservations</h3>

<ul>
    <c:forEach var="reservation" items="${reservations}">
        <li>
            ${reservation.eventName} - ${reservation.ticketType} - ${reservation.price} - ${reservation.reservationDate} -${reservation.eventDate}
        </li>
    </c:forEach>
</ul>
</body>
</html>
