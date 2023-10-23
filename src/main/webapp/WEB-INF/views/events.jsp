<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Active events</title>
    <jsp:include page="../../components/head.jsp"/>
</head>
<body>
<jsp:include page="../../components/navbar.jsp"/>
<jsp:include page="../../components/event-style.jsp"/>
<div class="events-body">
    <h4 class="mb-4">Active events</h4>
    <div class="row">
        <c:forEach items="${events}" var="event">
            <a href="${pageContext.request.contextPath}/show-event?id=${event.id}" class="col-lg-3 blog-card">
                <section style="position: relative">
                    <img
                            src="https://fastly.picsum.photos/id/949/1200/800.jpg?hmac=mW-_YmIqUMbyF5ydxz0QPn1GHneBWJEVlNCValTT5xw"
                            alt="white palace ceiling view"
                    />
                    <c:set var="date" value="<%= new java.util.Date() %>" scope="request" />
                    <jsp:useBean id="date" scope="request" type="java.util.Date"/>
                    <c:if test="${event.date.before(date)}">
                        <span id="badge">Passed</span>
                    </c:if>
                    <div class="blog-content">
                        <div class="d-flex justify-content-between">
                            <p class="blog-label">${event.category.name}</p>
                            <p class="blog-label-sucess">${event.date}</p>
                        </div>
                        <h6>${event.name}</h6>
                        <p>
                                ${event.description}
                        </p>
                        <div class="author">
                            <img
                                    src="https://i.pravatar.cc/150?img=68"
                                    alt="organizer avatar"
                            />
                            <p>by ${event.organization.name}</p>
                        </div>
                    </div>
                </section>
            </a>
        </c:forEach>

    </div>
</div>

</body>
<jsp:include page="../../components/js-scripts.jsp"></jsp:include>
</html>
