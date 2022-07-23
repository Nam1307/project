<%-- 
    Document   : uploadPitchPictureCreate
    Created on : Jul 2, 2022, 9:24:22 AM
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
<div class="container">
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
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Đăng ký</h5>
                    <form action="${pageContext.request.contextPath}/owner/createNewPitch.do" method="post" class="needs-validation" novalidate enctype="multipart/form-data">
                        <input type="hidden" value="${pitchID}" name="pitchID"/>
                        <div class="mb-3">
                            <label for="formFile" class="form-label">Tải file ảnh</label>
                            <input class="form-control" type="file" id="formFile" name="link" accept="image/jpeg" required/>
                            <div class="invalid-feedback">
                                Vui lòng chọn ảnh.
                            </div>
                        </div>

                        <img id="image" class="img-thumbnail" src="${pageContext.request.contextPath}/images/${pitchID}.jpg" alt="..." />

                        <div class="d-grid mb-2 mt-3">
                            <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">Tạo sân</button>
                        </div>

                        <hr class="my-4">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("formFile").onchange = function () {
        var reader = new FileReader();

        reader.onload = function (e) {
// get loaded data and render thumbnail.
            document.getElementById("image").src = e.target.result;
        };

// read the image file as a data URL.
        reader.readAsDataURL(this.files[0]);
    };

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
