<%-- 
    Document   : createChildrenPitch
    Created on : Jun 11, 2022, 8:54:26 AM
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
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Tạo sân con mới</h5>
                    <form action="${pageContext.request.contextPath}/owner/create.do" method="post" class="needs-validation" novalidate >
                        <div class="form-floating mb-3">
                            <select id="selectBox" name="pitchID" class="form-select" id="floatingSelect" required aria-label="select example">
                                <option selected="selected" disabled value="">Sân chính</option>
                                <c:forEach var="d" items="${listP}" >
                                    <option ${pitchID == d.pitchID?"selected":""} value="${d.pitchID}">${d.pitchName}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Vui lòng chọn sân chính</div>
                            <label for="floatingSelect">Sân chính:</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInputUsername" name="cpName" placeholder="myusername" required autofocus/>
                            <label for="floatingInputUsername">Tên sân con</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập tên sân con
                            </div>
                        </div>
                        <hr>
                        <div class="form-floating mb-3">
                            <input type="number" class="form-control" id="floatingInputUsername" name="cpPrice" placeholder="myusername" required autofocus/>
                            <label for="floatingInputUsername">Giá</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập giá
                            </div>
                        </div>
                        <div class="form-floating mb-3">
                            <select id="selectBox" name="cpType" class="form-select" id="floatingSelect" required aria-label="select example">
                                <option selected="selected" disabled value="">Số lượng người</option>
                                <option value="5">5 người</option>
                                <option value="7">7 người</option>
                            </select>
                            <div class="invalid-feedback">Vui lòng chọn số người</div>
                            <label for="floatingSelect">Số người:</label>
                        </div>
                        <div class="d-grid mb-2">
                            <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase ${disabled}" type="submit">Tạo sân</button>
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
