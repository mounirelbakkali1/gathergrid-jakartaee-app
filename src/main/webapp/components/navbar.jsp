<%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 10/19/2023
  Time: 3:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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

</body>
</html>
