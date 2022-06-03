<%-- 
    Document   : confirmBooking
    Created on : May 31, 2022, 10:20:12 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
  <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
  </symbol>
</svg>
<div class="container" style="width: 50%">
    <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto">
            <div class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">
                <div class="card-body p-4 p-sm-5">
                    <c:if test="${success != null}">
                        <div class="alert alert-success alert-dismissible fade show mb-5" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                            <strong>Thành công</strong> ${success}.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Register</h5>
                    <form action="${pageContext.request.contextPath}/booking/confirmBooking.do" method="post" class="needs-validation" novalidate >
                        <div class="form-floating mb-3">
                            <input type="hidden" value="${pitchID}" name="pitchID">
                            <input type="text" disabled="" class="form-control" id="floatingInputUsername" value="${user.fullName}" name="fullName" placeholder="myusername" required autofocus>
                            <label for="floatingInputUsername">Họ và tên</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập tên.
                            </div>
                        </div>
                        <div class="form-floating mb-3">
                            <c:if test="${user.phone == ''}">
                                <input type="text" class="form-control" id="floatingInputUsername" value="${user.phone}" name="phone" placeholder="myusername" required autofocus maxlength="10" minlength="10">
                            </c:if>
                            <c:if test="${user.phone != ''}">
                                <input disabled="" type="text" class="form-control" id="floatingInputUsername" value="${user.phone}" placeholder="myusername" required autofocus maxlength="10" minlength="10">
                                <input type="hidden" value="${user.phone}" name="phone">
                            </c:if>
                            <label for="floatingInputUsername">Phone</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập số điện thoại
                            </div>
                        </div>

                        <hr>

                        <div class="form-floating mb-3">
                            <c:forEach var="cp" items="${listCP}" >
                                <c:if test="${cp.childrenPitchID == type}">
                                    <input type="hidden" name="childrenPitchID" value="${type}">
                                    <input type="text" disabled="" class="form-control" id="floatingInputUsername" value="${cp.childrenPitchName}" name="type" placeholder="myusername" required autofocus>
                                </c:if>
                            </c:forEach>
                            <label for="floatingInputUsername">Tên sân</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập tên sân
                            </div>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="date" class="form-control" id="floatingInputUsername" value="${dateBooking}" disabled="" placeholder="dateBooking" required autofocus>
                            <input type="hidden" value="${dateBooking}" name="dateBooking">
                            <label for="floatingInputUsername">Ngày đặt</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập ngày đặt
                            </div>
                        </div>
                        <div class="form-floating mb-3">
                            <c:forEach var="t" items="${listT}" >
                                <c:if test="${t.timeID == time}">
                                    <input disabled="" type="text" class="form-control" id="floatingInputUsername" value="<fmt:formatDate type="time" value="${t.timeStart}" pattern="HH:mm" />-<fmt:formatDate type="time" value="${t.timeEnd}" pattern="HH:mm" />h" placeholder="myusername" required autofocus>
                                    <input type="hidden" value="${time}" name="timeRent">
                                </c:if>
                            </c:forEach>
                            <label for="floatingInputUsername">Giờ đặt</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập giờ đặt
                            </div>
                        </div>
                        <div class="d-grid mb-2">
                            <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase ${disabled}" type="submit">Xác nhận đặt sân</button>
                        </div>
                        <hr class="my-4">
                    </form>
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
