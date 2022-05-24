<%-- 
    Document   : register
    Created on : May 12, 2022, 10:37:30 AM
    Author     : Acer
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>     

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

                            <div class="form-floating mb-4 mt-5">
                                <input type="text" class="form-control" id="floatingInputUsername" placeholder="myusername" required autofocus>
                                <label for="floatingInputUsername">Username</label>
                            </div>



                            <hr>

                            <div class="form-floating mb-4 mt-5">
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
                            <button class="btn btn-google btn-login text-uppercase fw-bold" type="submit">
                                <i class="fab fa-google me-2"></i> Sign in with Google
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>