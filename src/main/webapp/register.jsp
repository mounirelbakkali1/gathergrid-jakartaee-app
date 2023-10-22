<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body{
            padding: 0 !important;
        }
        .btn-color{
            background-color: #22134C !important;
            color: #fff !important;

        }
        a{
            text-decoration: none;
        }

        .register-image{
            height: 100vh;

        }
    </style>
    <%@ include file="components/head.jsp"%>
    <title>Register</title>
</head>
<body>

<div class="d-flex">
<%--    <img src="v859-katie-11.jpg" class="col-md-5"  alt="img">--%>
    <img class="col-md-5 register-image" src='<c:url value="/files/login.jpg"></c:url>' />





    <div class="col-md-7 mt-5">
            <a href="/gg" class="text-dark m-5 text-decoration-none">< Back</a>

        <div class="">

            <form class=" p-lg-5" action="register" method="post">

                <div class="text-center">
                    <h5 class="text-start">Create Account</h5>
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
                    <input type="text" class="form-control" id="organization"
                           placeholder="Organization" name="organization" onblur="checkIfValidOrg()">
                    <%--error message if organization already exists--%>
                    <span class="text-danger" id="organizationError"></span>
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" id="password" placeholder="Password" name="password">
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" name="confirmPassword">
                </div>
                <div class="text-center"><button type="submit" class="btn rounded-3 btn-color px-5 mb-5 w-50 py-2" id="signup">Create Account</button></div>
                <div id="emailHelp" class="form-text text-center mb-5 text-dark">Have
                    An account? <a href="login" class="text-dark fw-bold">Login</a>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
<jsp:include page="components/js-scripts.jsp"></jsp:include>
<script>
        function checkIfValidOrg(){
            var value = document.getElementById("organization").value;
            $.get("${pageContext.request.contextPath}/api/organizations/"+value,function (data,status){
                if(status === "success"){
                    if(data.result!=null) {
                        $("#organizationError").text("Organization already exists");
                        $("#signup").attr("disabled",true);
                    }else{
                        $("#organizationError").text("");
                        $("#signup").attr("disabled",false);
                    }
                }
            })
        }
</script>
</html>
