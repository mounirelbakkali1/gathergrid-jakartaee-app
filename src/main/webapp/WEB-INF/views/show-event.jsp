<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>${event.name}</title>
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
    }
    .card h6, .card a{
        font-size: 12px;
    }
</style>

<jsp:include page="../../components/navbar.jsp"/>

<section class="d-flex first-section ">
    <div class="mt-5 home-image d-none d-md-block col-5">
        <img style="width: 100%; height: 90%" src='<c:url value="./files/event.png"></c:url>'  alt=""/>
    </div>
    <div class="container col-md-7 col-12 d-flex ">
        <div class="home-text  ">
            <h3 class="mb-2">${event.name}</h3>
            <h6 class="fw-bold">${event.date.toLocaleString()}</h6>
            <div class="d-flex justify-content-between">
                <div class="my-3">
                    <p style="font-size: 13px"><span class="fw-bold">Location<br/></span><span >${event.location}</span></p>
                </div>
                <div class="my-3">
                    <p style="font-size: 13px"><span class="fw-bold">By<br/></span><span >${event.organization.name}</span></p>
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

            <div class="mt-1">
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
            <div class="my-3">
                <div class="be-comment-block">
                    <h1 class="comments-title">Comments (${fn:length(comments)})</h1>
                    <c:forEach items="${comments}" var="comment" varStatus="loopStatus">
                        <c:if test="${loopStatus.index < 2}">
                        <div class="be-comment">
                            <div class="be-img-comment">
                                <a href="">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="" class="be-ava-comment">
                                </a>
                            </div>
                            <div class="be-comment-content">

                        <span class="be-comment-name">
                            <a href="">${comment.user.name}</a>
                            </span>
                                <span class="be-comment-time">
                            <i class="fa fa-clock-o"></i>
                            May 27, 2015 at 3:14am
                        </span>

                                <p class="be-comment-text">
                                   ${comment.content}
                                </p>
                            </div>
                        </div>
                        </c:if>
                    </c:forEach>
                    <c:if test="${fn:length(comments) > 2}">
                        <a class="text-sm text-warning" href="#">Show More</a>
                    </c:if>
            </div>
            <div class="mt-1">
                <form id="submitComment">
                    <div class="mb-3">
                        <label for="content" class="form-label">Comment</label>
                        <textarea style="background-color: #ffffff12;" class="form-control"  name="content" id="content" rows="3"></textarea>
                    </div>
                    <button type="submit" class="reserve-btn">Comment</button>
                </form>
            </div>
        </div>

    </div>
    </div>


</section>

<jsp:include page="../../components/js-scripts.jsp"></jsp:include>
<script>
    $(document).ready(function () {
        $("#submitComment").submit(function (e) {
            e.preventDefault();
            let content = $("#content").val().trim();
            if (content==="") alert("Comment can't be empty")
            $.ajax({
                url: "${pageContext.request.contextPath}/api/comments",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({
                    content,
                    eventId: ${event.id}
                }),
                success: function (data) {
                    console.log(data);
                    if (data.result !==null && data.result !== undefined) {
                        window.location.reload();
                    } else {
                        alert("Error : "+data.error[0].message);
                    }
                },
                error: function (data) {
                    console.log(data);
                    alert("Error");
                }
            });
        });
    });
</script>
</body>
</html>
