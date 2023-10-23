<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 10/22/2023
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Show event</title>
  <jsp:include page="../../components/head.jsp" />

</head>
<body>
<style>
    .home-text{
        margin: 10vh 3vw;
    }
    .reserve-btn{
        background: #22134C;
        color: white;
        padding: 7px 15px;
        border-radius: 3px;
    }
    .home-text .create-btn{
        border:1px solid #22134C;
        color: #22134C;
        padding: 10px 20px;
        border-radius: 3px;
        width: 46vw
    }
    .first-section{
        margin: 0 13vw;
    }
    .card{
        width: 9rem;
        background: rgba(255, 255, 255, 0) !important;

    }
    .card h6, .card a{
        font-size: 12px;
    }

</style>

<jsp:include page="../../components/navbar.jsp"/>

<section class="d-flex first-section ">
    <div class="mt-5 home-image d-none d-md-block col-5">
        <img style="width: 100%; height: 90%" src='<c:url value="./files/event.png"></c:url>' />
    </div>
    <div class="container col-md-7 col-12 d-flex ">
        <div class="home-text  ">
            <h6 class="fw-bold">Tuesday, October 17</h6>
            <p class="">${event.name}</p>
            <div class="d-flex">
                <div>
                    <p style="font-size: 13px"><span class="fw-bold" >Date and time<br/></span><span >${event.date}</span></p>
                    <p style="font-size: 13px"><span class="fw-bold">Location<br/></span><span >${event.location}</span></p>
                </div>
                <div>
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title">Available Places</h6>
                            <p class="card-text">20</p>
                            <a href="${pageContext.request.contextPath}/new-reservation?id=${event.id}" class="btn reserve-btn">Reserve a Spot</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mt-5">
<%--                <a href="" class="btn create-btn">Create Your Event</a>--%>
                <p class="d-inline-flex gap-1">
                    <button class="btn create-btn" type="button" data-bs-toggle="collapse" data-bs-target="#multiCollapseExample1" aria-expanded="false" aria-controls="multiCollapseExample1">About this event</button>
                </p>
                <div class="collapse multi-collapse" id="multiCollapseExample1">
                    <div class="card-body">
                        ${event.description}
                    </div>
                </div>
            </div>
        </div>

    </div>


</section>

<jsp:include page="../../components/js-scripts.jsp"></jsp:include>
</body>
</html>
