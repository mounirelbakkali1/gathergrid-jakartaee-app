<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Gather Grid</title>
    <%@include file="components/head.jsp"%>

</head>
<body>

<style>
    .navbar-brand{
        margin-left:13vw;

    }
    .navbar-collapse{
        margin-left:17vw;

    }
    .navbar-collapse a {
        font-size: 15px;
    }

    .actions-section{
        margin-right:13vw;
        margin-left: 10vw;

    }
    .home-text{
        /* margin-left: 13vw; */
    }
    .home-text .find-btn{
        background: #22134C;
        color: white;
        padding: 10px 20px;
        border-radius: 3px;
    }
    .home-text .create-btn{
        border:1px solid #22134C;
        color: #22134C;
        padding: 10px 20px;
        border-radius: 3px;
    }
    .first-section{
        margin: 0 13vw;
    }
</style>
<nav class="navbar navbar-expand-lg mt-2">

    <div class="container-fluid">
        <a class="navbar-brand fw-bolder" href="#">GatherGrid</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item mx-4 ">
                    <a class="nav-link active" aria-current="page" href="#">HOME</a>
                </li>
                <li class="nav-item mx-4">
                    <a class="nav-link" href="#">EVENTS</a>
                </li>
                <li class="nav-item mx-4">
                    <a class="nav-link" href="#">ABOUT</a>
                </li>
                <li class="nav-item mx-4">
                    <a class="nav-link" href="#">CONTACT</a>
                </li>
            </ul>

        </div>
        <div class="actions-section d-none d-lg-flex" role="search">
            <div class="dropdown me-3">
                <a class="" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    <box-icon name='user'></box-icon>
                </a>
                <ul class="dropdown-menu mx-4">
                    <li><a class="dropdown-item" href="login">Login</a></li>
                    <li><a class="dropdown-item" href="register">Register</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="profile">profile</a></li>
                </ul>

                <!-- <a href="#"><box-icon name='user'></box-icon></a> -->
            </div>
            <a href="#" class="me-3"><box-icon name='search'></box-icon>
            </a>

        </div>
    </div>
</nav>



<section class="d-flex first-section ">
    <div class="container col-md-6 col-12 d-flex align-items-center">
        <div class="home-text  ">
            <h1 class="mb-5  fw-bold">EventVibe</h1>
            <p class="w-75">Your One-Stop Destination for Reserving Memorable Experiences</p>
            <div class="mt-5">
                <a href="" class="btn find-btn">Find Your Event</a>
                <a href="" class="btn create-btn">Create Your Event</a>
            </div>
        </div>

    </div>
    <div class="mt-5 home-image d-none d-md-block col-6">
        <img style="width: 100%; height: 100%" src='<c:url value="./files/image.png"></c:url>' />
    </div>

</section>



<!-- <h1><%= "Hello world" %></h1> -->
<!-- <br/> -->
<!-- <a href="login">login</a>
<a href="register">register</a>
<a href="secure">secure</a> -->

<jsp:include page="/components/js-scripts.jsp"></jsp:include>
</body>
</html>>