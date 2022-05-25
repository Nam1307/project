<%-- 
    Document   : login
    Created on : May 15, 2022, 2:32:45 PM
    Author     : SE150838 Le Quoc Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<div class='khanhlq'>
    <div class="container">
        <div class="row">
            <div class=" col-lg-10 col-xl-9 mx-auto">
                <div class="card flex-row my-4 border-0 shadow rounded-3 overflow-hidden">
                    <div class="card-img-left d-none d-md-flex">
                        
                    </div>
                    <div class="card-body p-4 p-sm-5">
                        <h5 class="card-title text-center mb-5 fw-light fs-5">Login</h5>
                        <form>

                            <div class="form-floating mb-3 mt-3">
                                <input type="text" class="form-control" id="floatingInputUsername" placeholder="myusername" required autofocus>
                                <label for="floatingInputUsername">Username</label>
                            </div>



                            <hr>

                            <div class="form-floating mb-3 mt-3">
                                <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
                                <label for="floatingPassword">Password</label>
                            </div>

                            <div class="d-grid mb-2">
                                <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">Login</button>
                            </div>



                            <a class="d-block text-center mt-2 small" href="${pageContext.request.contextPath}/user/register.do">Don't have an account? / Register</a>

                            <hr class="my-4">
                        </form>

                        <div class="d-grid mb-2">
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/WebsiteOrderStadium/user/loginGoogle.do&response_type=code
                               &client_id=192542612754-5jfceh4emnpkitavtni111euvf7ne569.apps.googleusercontent.com&approval_prompt=force">
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
