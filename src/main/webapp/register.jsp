<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        .btn-color{
            background-color: #0e1c36;
            color: #fff;

        }
        .profile-image-pic{
            height: 200px;
            width: 200px;
            object-fit: cover;
        }
        .cardbody-color{
            background-color: #ebf2fa;
        }

        a{
            text-decoration: none;
        }
    </style>
    <title>Register</title>
    <jsp:include page="components/head.jsp"></jsp:include>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="card my-3">
                <form class="card-body cardbody-color p-lg-5" action="register" method="post">

                    <div class="text-center">
                        <img src="https://cdn.pixabay.com/photo/2016/03/31/19/56/avatar-1295397__340.png" class="img-fluid profile-image-pic img-thumbnail rounded-circle my-3"
                             width="200px" alt="profile">
                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger alert-dismissible fade show">${param.error}</div>
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <input type="text" class="form-control" id="name" aria-describedby="nameHelp"
                               placeholder="Name" name="name">
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="username" aria-describedby="emailHelp"
                               placeholder="User Name" name="username">
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="email" aria-describedby="emailHelp"
                               placeholder="Email" name="email">
                    </div>
                    <div class="mb-3">
                        <input type="password" class="form-control" id="password" placeholder="Password" name="password">
                    </div>
                    <div class="mb-3">
                        <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" name="confirmPassword">
                    </div>
                    <div class="text-center"><button type="submit" class="btn btn-color px-5 mb-5 w-100">Create</button></div>
                    <div id="emailHelp" class="form-text text-center mb-5 text-dark">Have
                        An account? <a href="login" class="text-dark fw-bold">Login in</a>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
</body>
<jsp:include page="components/js-scripts.jsp"></jsp:include>
</html>
