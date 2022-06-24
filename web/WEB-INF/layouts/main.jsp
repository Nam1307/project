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
        <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
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
                        <c:if test="${user != null && user.roleID == 'US'}">
                            <li class="nav-item active">
                                <a class="nav-link" href="${pageContext.request.contextPath}/home/index.do">Trang chủ</a>
                            </li>
                        </c:if>
                        <c:if test="${user.roleID == 'OW'}">
                            <li class="nav-item active">
                                <a class="nav-link" href="${pageContext.request.contextPath}/owner/index.do">Trang chủ</a>
                            </li>
                        </c:if>
                        <c:if test="${user.roleID == 'AD'}">
                            <li class="nav-item active">
                                <a class="nav-link" href="${pageContext.request.contextPath}/admin/index.do">Trang chủ</a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/home/about.do">Giới thiệu</a>
                        </li>
                        <c:if test="${user != null && user.roleID == 'US'}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/user/bookingList.do?userID=${user.userID}">Xem sân đã đặt</a>
                            </li>
                        </c:if>
                        <c:if test="${user == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/user/goToBecomingOwnerPage.do">Đăng ký trờ thành chủ sân</a>
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
                                                <c:if test="${n.childrenPitchID eq cp.childrenPitchID && cp.pitchID eq p.pitchID && cp.status == true}">
                                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/user/bookingList.do?userID=${user.userID}">
                                                        <div class="form-check pt-4">
                                                            <i class="bi bi-bell-fill"></i>
                                                            <label class="form-check-label fst-italic">Trận đấu sắp diễn ra</label>
                                                            <p class="form-check-label"><h6>${p.pitchName}</h6></p>
                                                            <p class="justify fst-italic"><c:if test="${cp.status != false}">Sân con: ${cp.childrenPitchName}</c:if><c:if test="${cp.status == false}">Sân con: Đã bị xóa</c:if></p>
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
                        <c:if test="${user != null && user.roleID == 'AD'}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle notification" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <span><i class="bi bi-bell"></i>Thông báo</span><span class="badge">${countNoAdmin}</span> 
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown" id="notification" style="padding: 0px; margin: 0px;">
                                    <div class="text-center fs-5 fw-bolder">Thông báo</div>
                                    <hr class="dropdown-divider" style="padding: 0px; margin: 0px">
                                    <c:forEach var="u" items="${listNoAdmin}">
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/viewBecomingOwner.do">
                                            <div class="form-check pt-4">
                                                <i class="bi bi-bell-fill"></i>
                                                <label class="form-check-label fst-italic">Đang chờ xác nhận trở thành quản lý sân</label>
                                                <p class="justify">Họ và tên: <span class="fw-bold">${u.fullName}</span></p>
                                                <p class="justify">Mail: <span class="fw-bold">${u.email}</span></p>
                                            </div>
                                        </a>
                                        <hr class="dropdown-divider" style="padding: 0px; margin: 0px">
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
        <c:if test="${user == null || user.roleID == 'US'}">
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
        </c:if>

        <!-- offcanvas -->
        <c:if test="${user.roleID == 'OW'}">
            <script src="${pageContext.request.contextPath}/js/script.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.2/dist/chart.min.js"></script>
            <div
                class="offcanvas offcanvas-start sidebar-nav bg-dark"
                tabindex="-1"
                id="sidebar" style="margin-top: 10px"
                >
                <div class="offcanvas-body p-0 mt-5">
                    <nav class="navbar-dark">
                        <ul class="navbar-nav">

                            <li>
                                <div class="text-muted small fw-bold text-uppercase px-3">
                                    ĐẶT SÂN
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/owner/viewBooking.do?userID=${user.userID}" class="nav-link px-3 active">
                                    <span class="me-2"><i class="bi bi-speedometer2"></i></span>
                                    <span>Quản lý đặt sân</span>
                                </a>
                            </li>

                            <li class="my-4"><hr class="dropdown-divider bg-light" /></li>
                            <li>
                                <div class="text-muted small fw-bold text-uppercase px-3 mb-3">
                                    Quản lý sân
                                </div>
                            </li>
                            <li>
                                <a
                                    class="nav-link px-3 sidebar-link"
                                    data-bs-toggle="collapse"
                                    href="#layouts"
                                    >
                                    <span class="me-2"><i class="bi bi-layout-split"></i></span>
                                    <span>Quản lý sân con</span>
                                    <span class="ms-auto">
                                        <span class="right-icon">
                                            <i class="bi bi-chevron-down"></i>
                                        </span>
                                    </span>
                                </a>
                                <div class="collapse" id="layouts">
                                    <ul class="navbar-nav ps-3">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/owner/createChildrenPitch.do?userID=${user.userID}" class="nav-link px-3">
                                                <span class="me-2"
                                                      ><i class="bi bi-speedometer2"></i
                                                    ></span>
                                                <span>Thêm mới</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="collapse" id="layouts">
                                    <ul class="navbar-nav ps-3">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/owner/childrenPitchManagement.do?userID=${user.userID}" class="nav-link px-3">
                                                <span class="me-2"
                                                      ><i class="bi bi-speedometer2"></i
                                                    ></span>
                                                <span>Điều chỉnh sân</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <a href="#" class="nav-link px-3">
                                    <span class="me-2"><i class="bi bi-book-fill"></i></span>
                                    <span>Pages</span>
                                </a>
                            </li>
                            <li class="my-4"><hr class="dropdown-divider bg-light" /></li>
                            <li>
                                <div class="text-muted small fw-bold text-uppercase px-3 mb-3">
                                    Bình luận
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/home/index.do" class="nav-link px-3">
                                    <span class="me-2"><i class="bi bi-graph-up"></i></span>
                                    <span>Xem bình luận</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link px-3">
                                    <span class="me-2"><i class="bi bi-table"></i></span>
                                    <span>Tables</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <main class="mt-5 pt-3">
                <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />
            </main>
        </c:if>
        <!-- offcanvas -->

        <!-- offcanvas -->
        <c:if test="${user.roleID == 'AD'}">
            <script src="${pageContext.request.contextPath}/js/script.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.2/dist/chart.min.js"></script>
            <div
                class="offcanvas offcanvas-start sidebar-nav bg-dark"
                tabindex="-1"
                id="sidebar" style="margin-top: 10px"
                >
                <div class="offcanvas-body p-0 mt-5">
                    <nav class="navbar-dark">
                        <ul class="navbar-nav">

                            <li>
                                <div class="text-muted small fw-bold text-uppercase px-3">
                                    CHỦ SÂN
                                </div>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/viewBecomingOwner.do" class="nav-link px-3 active">
                                    <span class="me-2"><i class="bi bi-speedometer2"></i></span>
                                    <span>Xác nhận trở thành chủ sân</span>
                                </a>
                            </li>

                            <li class="my-4"><hr class="dropdown-divider bg-light" /></li>
                            <li>
                                <div class="text-muted small fw-bold text-uppercase px-3 mb-3">
                                    NGƯỜI DÙNG
                                </div>
                            </li>
                            <li>
                                <a
                                    class="nav-link px-3 sidebar-link"
                                    data-bs-toggle="collapse"
                                    href="#layouts"
                                    >
                                    <span class="me-2"><i class="bi bi-layout-split"></i></span>
                                    <span>Quản lý</span>
                                    <span class="ms-auto">
                                        <span class="right-icon">
                                            <i class="bi bi-chevron-down"></i>
                                        </span>
                                    </span>
                                </a>
                                <div class="collapse" id="layouts">
                                    <ul class="navbar-nav ps-3">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/admin/userManagement.do" class="nav-link px-3">
                                                <span class="me-2"
                                                      ><i class="bi bi-speedometer2"></i
                                                    ></span>
                                                <span>Quản lý người dùng</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="collapse" id="layouts">
                                    <ul class="navbar-nav ps-3">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/owner/childrenPitchManagement.do?userID=${user.userID}" class="nav-link px-3">
                                                <span class="me-2"
                                                      ><i class="bi bi-speedometer2"></i
                                                    ></span>
                                                <span>Quản lý bình luận</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="my-4"><hr class="dropdown-divider bg-light" /></li>
                        </ul>
                    </nav>
                </div>
            </div>
            <main class="mt-5 pt-3">
                <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />
            </main>
        </c:if>
        <!-- offcanvas -->


        <!--Footer-->
        <c:if test="${user == null || user.roleID == 'US'}">
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
        </c:if>
        <!-- Footer -->
        <!-- End of .container -->
    </body>
</html>
