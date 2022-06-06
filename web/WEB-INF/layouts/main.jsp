<%-- 
    Document   : main
    Created on : Jan 18, 2022, 12:37:20 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jdbc Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link href="${pageContext.request.contextPath}/css/site.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <style>
            .notification {
                color: white;
                text-decoration: none;
                padding: 5px 26px;
                position: relative;
                display: inline-block;
                border-radius: 2px;
                /*                margin-left: 30px;
                                margin-right: 30px;*/
                margin-top: 3px;
            }
            .notification .badge {
                position: absolute;
                top: -8px;
                right: 85px;
                padding: 5px 10px;
                border-radius: 50%;
                background-color: red;
                color: white;
            }
        </style>
    </head>
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/home/index.do">Start Bootstrap</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="${pageContext.request.contextPath}/home/index.do">Trang chủ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/home/about.do">Giới thiệu</a>
                        </li>
                        <c:if test="${user != null && user.roleID == 'US'}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/user/bookingList.do?userID=${user.userID}">Xem sân đã đặt</a>
                            </li>
                        </c:if>
                        <c:if test="${user.roleID == 'OW'}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/owner/viewBooking.do?userID=${user.userID}">Quản lý đặt sân</a>
                            </li>
                        </c:if>
                        <c:if test="${user != null && user.roleID == 'US'}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle notification" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <span><i class="bi bi-bell"></i>Thông báo</span><span class="badge">${countN}</span> 
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown" id="notification" style="padding: 0px; margin: 0px;">
                                    <div class="text-center fs-5 fw-bolder">Thông báo</div>
                                    <hr class="dropdown-divider" style="padding: 0px; margin: 0px">
                                    <c:forEach var="n" items="${listNo}">
                                        <c:forEach var="cp" items="${listCP1}">
                                            <c:forEach var="p" items="${listP1}">
                                                <c:if test="${n.childrenPitchID eq cp.childrenPitchID && cp.pitchID eq p.pitchID}">
                                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/user/bookingList.do?userID=${user.userID}">
                                                        <div class="form-check pt-4">
                                                            <i class="bi bi-bell-fill"></i>
                                                            <label class="form-check-label fst-italic">Trận đấu sắp diễn ra</label>
                                                            <p class="form-check-label"><h6>${p.pitchName}</h6></p>
                                                            <p class="justify fst-italic">Sân con: ${cp.childrenPitchName}</p>
                                                            <p class="justify">Ngày: <span class="fw-bold">${n.bookingDate}</span></p>
                                                            <p class="justify">Thời gian: <span class="fw-bold"><fmt:formatDate type="time" value="${n.timeStart}" pattern="HH:mm" />-<fmt:formatDate type="time" value="${n.timeEnd}" pattern="HH:mm" />h</span></p>
                                                        </div>
                                                    </a>
                                                    <hr class="dropdown-divider" style="padding: 0px; margin: 0px">
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                    </c:forEach>              
                                </ul>
                            </li> 
                        </c:if>
                        <c:if test="${user == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/user/login.do">Đăng nhập</a>
                            </li>
                        </c:if>
                        <c:if test="${user != null}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <img class="img-thumbnail rounded-circle" src="${user.imgLink}" alt="..." width="33px" height="33px" />  
                                </a>
                                <ul class="dropdown-menu" style="text-align: center" aria-labelledby="navbarDropdown">
                                    <li>
                                        <div>Xin chào: ${sessionScope.user.fullName}</div>
                                    </li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/logout.do?"><i class="bi bi-box-arrow-right"></i> Đăng xuất</a></li>
                                </ul>
                            </li>         
                        </c:if>

                    </ul>
                </div>
            </div>
        </nav>
        <header>
            <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active" style="background-image: url('${pageContext.request.contextPath}/images/banner1.jpg')">
                    </div>
                    <div class="carousel-item" style="background-image: url('${pageContext.request.contextPath}/images/banner4.jpg')">
                    </div>
                    <div class="carousel-item" style="background-image: url('${pageContext.request.contextPath}/images/banner3.jpg')">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </header>
        <!--Contents-->
        <div class="row">
            <div class="col">
                <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />
            </div>
        </div>
        <!--Footer-->
        <section class="">
            <!-- Footer -->
            <footer class="text-center text-white" style="background-color: #0a4275;">
                <!-- Grid container -->
                <div class="container p-4 pb-0">
                    <!-- Section: CTA -->
                    <section class="">
                        <p class="d-flex justify-content-center align-items-center">
                            <span class="me-3">Register for booking</span>
                            <button type="button" class="btn btn-outline-light btn-rounded">
                                Sign up!
                            </button>
                        </p>
                    </section>
                    <!-- Section: CTA -->
                </div>
                <!-- Grid container -->

                <!-- Copyright -->
                <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
                    © 2020 Copyright:
                    <a class="text-white" href="#">OUR TEAM</a>
                </div>
                <!-- Copyright -->
            </footer>
            <!-- Footer -->
        </section>
        <!-- Footer -->
        <!-- End of .container -->
    </body>
</html>
