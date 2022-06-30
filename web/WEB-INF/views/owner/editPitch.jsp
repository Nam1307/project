<%-- 
    Document   : editPitch
    Created on : Jun 25, 2022, 9:37:47 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">
    <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto">
            <div class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">
                <div class="card-body p-4 p-sm-5">
                    <c:if test="${error != null}">
                        <div class="alert alert-danger alert-dismissible fade show mb-5" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                            <strong>Lỗi!</strong> ${error}.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Đăng ký</h5>
                    <form action="${pageContext.request.contextPath}/owner/goToLoadPicture.do" method="post" class="needs-validation" novalidate>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInputUsername" value="${p.pitchName}" name="pitchName" placeholder="myusername" required autofocus>
                            <label for="floatingInputUsername">Tên sân</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập tên sân.
                            </div>
                        </div>
                        <input type="hidden" value="${p.pitchID}" name="pitchID"/>
                        <hr>

                        <div class="form-floating mb-3">
                            <select id="selectBox" name="districtID" onchange="setWard()" class="form-select" id="floatingSelect" aria-label="Floating label select example" required>
                                <option selected disabled value="">Quận</option>
                                <c:forEach var="d" items="${listD}" >
                                    <option ${p.districtID == d.districtID?"selected":""} value="${d.districtID}">${d.districtName}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                Vui lòng chọn quận.
                            </div>
                            <label for="floatingSelect">Quận:</label>
                        </div>
                        <div class="form-floating mb-3 col">
                            <select id="ward" name="ward" class="form-select" id="floatingSelect" aria-label="Floating label select example" required="">
                                <option selected disabled value="">Phường</option>
                                <c:forEach var="w" items="${listW}" >
                                    <option ${p.wardID == w.wardID?"selected":""} value="${w.wardID}">${w.wardName}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                Vui lòng chọn phường.
                            </div>
                            <label for="floatingSelect">Phường:</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInputUsername" value="${p.pitchAddress}" placeholder="myusername" name="address" required autofocus>
                            <label for="floatingInputUsername">Địa chỉ</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập địa chỉ.
                            </div>
                        </div>
                        <div class="form-floating mb-3">
                            <textarea type="text" class="form-control" id="floatingInputUsername" placeholder="myusername" name="description" required autofocus style="height: 200px">${p.pitchDescription}</textarea>
                            <label for="floatingInputUsername">Mô tả</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập mô tả.
                            </div>
                        </div>

                        <hr>

                        <div class="d-grid mb-2 mt-3">
                            <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">Cập nhật ảnh cho sân</button>
                        </div>

                        <hr class="my-4">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function setWard() {
        var selectBox = document.getElementById("selectBox");
        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
        $.ajax({
            url: "${pageContext.request.contextPath}/home/ward.do",
            type: 'post',
            data: {
                districtID: selectedValue
            },
            success: function (responseData) {
                document.getElementById("ward").innerHTML
                        = responseData;
//                document.getElementById("districtID").innerHTML
//                        = "<input type=\"hidden\" name=\"districtID\" value=\"" + selectedValue + "\"/>";
            }
        });
    }

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
