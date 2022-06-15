<%-- 
    Document   : goToBecomingOwnerPage
    Created on : Jun 14, 2022, 2:32:37 PM
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
                    <c:if test="${error != null}">
                        <div class="alert alert-danger alert-dismissible fade show mb-5" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                            <strong>Lỗi!</strong> ${error}.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <c:if test="${success != null}">
                        <div class="alert alert-success alert-dismissible fade show mb-5" role="alert">
                            <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
                            <strong>Thành công</strong> ${success}.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Thông tin đăng ký trở thành chủ sân</h5>
                    <form action="${pageContext.request.contextPath}/user/confirmBecomingOwner.do" method="post" class="needs-validation" novalidate >
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInputUsername" value="${username}" name="username" placeholder="myusername" required autofocus>
                            <label for="floatingInputUsername">Tên đăng nhập</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập tên đăng nhập.
                            </div>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="floatingInputEmail" value="${email}" name="email" placeholder="name@example.com" required>
                            <label for="floatingInputEmail">Email</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập email.
                            </div>
                        </div>

                        <hr>

                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="floatingPassword" value="${password}" name="password" placeholder="Password" required autofocus>
                            <label for="floatingPassword">Mật khẩu</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập mật khẩu.
                            </div>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" id="floatingPasswordConfirm" value="${confirmPassword}" name="confirmPassword" placeholder="Confirm Password" required autofocus>
                            <label for="floatingPasswordConfirm">Xác nhận mật khẩu</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập xác nhận mật khẩu.
                            </div>
                        </div>

                        <hr>

                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInputUsername" value="${fullname}" name="fullname" placeholder="myusername" required autofocus>
                            <label for="floatingInputUsername">Họ và tên</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập họ và tên.
                            </div>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInputUsername" value="${phone}" name="phone" placeholder="myusername" required autofocus maxlength="10" minlength="10">
                            <label for="floatingInputUsername">Điện thoại</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập số điện thoại.
                            </div>
                        </div>
                        <div class="form-floating mb-3">
                            <select id="selectBox" name="districtID" onchange="setWard()" class="form-select" id="floatingSelect" aria-label="Floating label select example" required>
                                <option selected disabled value="">Quận</option>
                                <c:forEach var="d" items="${listD}" >
                                    <option ${districtID == d.districtID?"selected":""} value="${d.districtID}">${d.districtName}</option>
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
                                    <option ${ward == w.wardID?"selected":""} value="${w.wardID}">${w.wardName}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                Vui lòng chọn phường.
                            </div>
                            <label for="floatingSelect">Phường:</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInputUsername" value="${address}" placeholder="myusername" name="address" required autofocus>
                            <label for="floatingInputUsername">Địa chỉ</label>
                            <div class="invalid-feedback">
                                Vui lòng nhập địa chỉ.
                            </div>
                        </div>
                        <div class="d-grid mb-2">
                            <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase ${disabled}" type="submit">Đăng ký</button>
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
