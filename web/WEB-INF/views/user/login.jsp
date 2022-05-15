<%-- 
    Document   : login
    Created on : May 15, 2022, 2:32:45 PM
    Author     : SE150838 Le Quoc Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body class="khanhlq">
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
                </div>
            </div>
        </div>
    </div> 
</body>
