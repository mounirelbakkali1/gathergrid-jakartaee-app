<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <jsp:include page="components/head.jsp" />
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
        .register-image{
            height:100vh;
        }
    </style>
</head>
<body>

<div class="d-flex">
    <%--    <img src="v859-katie-11.jpg" class="col-md-5"  alt="img">--%>
    <img class="col-md-5 register-image" src='<c:url value="/files/login.jpg"></c:url>' />





    <div class="col-md-7 mt-5 ">
        <div>

        <a href="/gg" class="text-dark m-5 text-decoration-none">< Back</a>

        <div class="">

            <form class=" m-5 p-5" action="login" method="post">


                <c:if test="${param.error != null}">
                    <div class="alert alert-danger alert-dismissible fade show">${param.error}</div>
                </c:if>

                <div class="mb-3">
                    <input type="text" class="form-control" id="username" aria-describedby="emailHelp"
                           placeholder="User Name" name="username">
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" id="password" placeholder="password" name="password">
                </div>
                <div class="text-center"><button type="submit" class="btn btn-color px-5 mb-5 w-100">Login</button></div>
                <div id="emailHelp" class="form-text text-center mb-5 text-dark">Not
                    Registered? <a href="register" class="text-dark fw-bold"> Create an
                        Account</a>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>
