<%-- 
    Document   : login
    Created on : May 15, 2022, 2:32:45 PM
    Author     : SE150838 Le Quoc Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--<body class="khanhlq">
    <div class="container bg-white pb-5 mb-5">
        <div class="row d-flex justify-content-start align-items-center mt-sm-5">
            <div class="col-lg-5 col-10">
                <div id="circle"></div>
                <div class="pb-5">
                    <img src="https://img.freepik.com/free-vector/authentication-concept-illustration_114360-2168.jpg?size=338&ext=jpg&ga=GA1.2.777073396.1599399655" alt="" class="img-login">
                </div>
            </div>
            <div class="col-lg-4 offset-lg-2 col-md-6 offset-md-3">
                <div class="pt-4">
                    <h6><span class="fa fa-superpowers text-primary px-md-2"></span>COMPANY LOGO</h6>
                </div>
                <div class="mt-3 mt-md-5">
                    <h5>Log in to your account</h5>
                    <form class="pt-4">
                        <div class="d-flex flex-column pb-3">
                            <label for="email">Email Address</label>
                            <input type="email" name="email" id="emailId" class="border-bottom border-primary">
                        </div>
                        <div class="d-flex flex-column pb-3">
                            <label for="password">Password</label>
                            <input type="password" name="passwrd" id="pwd" class="border-bottom border-primary">
                        </div>
                        <button type="submit" value="Log in" class="btn btn-primary btn-block mb-3">Log in</button>
                        <div class="register">
                            <p>Don't have an account? <a href="${pageContext.request.contextPath}/user/register.do">Create an account</a></p>
                        </div>
                    </form>
                    <div>
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/WebsiteOrderStadium/user/loginGoogle.do&response_type=code
                           &client_id=192542612754-5jfceh4emnpkitavtni111euvf7ne569.apps.googleusercontent.com&approval_prompt=force"><button>Login With Google</button></a>
                    </div>
                </div>
            </div>
        </div>
    </div> 
</body>-->
<%
    String username = "";
    String password = "";
    String remember = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cook : cookies) {

            if (cook.getName().equals("cookUname")) {
                username = cook.getValue();
            } else if (cook.getName().equals("cookPass")) {
                password = cook.getValue();
            } else if (cook.getName().equals("cookRem")) {
                remember = cook.getValue();
            }
        }
    }
%>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
        <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
    </symbol>
</svg>
<div class='khanhlq'>
    <div class="container">
        <div class="row">
            <div class=" col-lg-10 col-xl-9 mx-auto">
                <div class="card flex-row my-4 border-0 shadow rounded-3 overflow-hidden">
                    <div class="card-img-left d-none d-md-flex">

                    </div>
                    <div class="card-body p-4 p-sm-5">
                        <h5 class="card-title text-center mb-5 fw-light fs-2">Login</h5>
                        <form action="${pageContext.request.contextPath}/user/checkLogin.do" method="post" class="needs-validation" novalidate>
                            <c:if test="${error != null}">
                                <div class="alert alert-danger alert-dismissible fade show mb-5" role="alert">
                                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                    <strong>Lỗi!</strong> Đăng nhập không hợp lệ.
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>
                            <div class="form-floating mb-3 mt-3">
                                <input type="text" class="form-control" id="floatingInputUsername" value="<%=username%>" name="username" placeholder="myusername" required autofocus>
                                    <label for="floatingInputUsername">Username</label>
                                    <div class="invalid-feedback">
                                        Vui lòng nhập tên đăng nhập.
                                    </div>
                            </div>
                            <hr>
                                <div class="form-floating mb-3 mt-3">
                                    <input type="password" class="form-control" id="floatingPassword" value="<%=password%>" name="password" placeholder="Password" required="">
                                        <label for="floatingPassword">Password</label>
                                        <div class="invalid-feedback">
                                            Vui lòng nhập mật khẩu.
                                        </div>
                                </div>
                                <div class="form-check mb-3">
                                        <input class="form-check-input" type="checkbox" name="remember" value="1" 
                                               <%= "1".equals(remember) ? "checked='/checked'" : ""%>
                                               >
                                            <label class="form-check-label" for="flexCheckDefault">
                                                Ghi nhớ đăng nhập
                                            </label>
                                            </div>
                                            <div class="d-grid mb-2">
                                                <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">Login</button>
                                            </div>
                                            <a class="d-block text-center mt-2 small" href="${pageContext.request.contextPath}/user/register.do">Don't have an account? / Register</a>
                                            <hr class="my-4">
                                                </form>
                                                <div class="d-grid mb-2">
                                                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/WebsiteOrderStadium/user/loginGoogle.do&response_type=code
                                                       &client_id=192542612754-a31982s55etlpeb7liogh8d3s66pms6e.apps.googleusercontent.com&approval_prompt=force">
                                                        <button class="btn btn-google btn-login text-uppercase fw-bold" type="submit">
                                                            <i class="fab fa-google me-2"></i> Sign in with Google
                                                        </button>
                                                    </a>
                                                </div>
                                                </div>
                                                </div>
                                                </div>
                                                </div>
                                                </div>
                                                </div>
                                                <script>
                                                    (function () {
                                                        'use strict'

                                                        // Fetch all the forms we want to apply custom Bootstrap validation styles to
                                                        var forms = document.querySelectorAll('.needs-validation')

                                                        // Loop over them and prevent submission
                                                        Array.prototype.slice.call(forms)
                                                                .forEach(function (form) {
                                                                    form.addEventListener('submit', function (event) {
                                                                        if (!form.checkValidity()) {
                                                                            event.preventDefault()
                                                                            event.stopPropagation()
                                                                        }

                                                                        form.classList.add('was-validated')
                                                                    }, false)
                                                                })
                                                    })()
                                                </script>
