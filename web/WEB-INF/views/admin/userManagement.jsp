<%-- 
    Document   : userManagement
    Created on : Jun 20, 2022, 10:12:09 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2 class="d-flex justify-content-center mt-5">Quản lý người dùng</h2>
<div class="px-4 px-lg-5 mb-3 mx-auto mt-3 table-responsive mt-5">
    <div class="row">
        <div class="col-md-12 mb-3">
            <div class="card">
                <div class="card-header">
                    <span><i class="bi bi-table me-2"></i></span> Dữ liệu người dùng
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table
                            id="example"
                            class="table table-striped data-table"
                            style="width: 100%"
                            >
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Tên</th>
                                    <th>Vai trò</th>
                                    <th>Xem thông tin người dùng</th>
                                    <th>Số lần hủy sân</th>
                                    <th>Xử lý</th>
                                </tr>
                            </thead>
                            <tbody id="myContent">
                                <c:forEach var="u" items="${listU}" varStatus="count">
                                    <tr id="row_${u.userID}">
                                        <td>${count.index + 1}</td>
                                        <td id="row_${cp.childrenPitchID}_name">${u.fullName}</td>
                                        <td>${u.roleID}</td>
                                        <td>
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="GetBecomingOwnerInfo('${u.userID}')">
                                                Xem thông tin
                                            </button>
                                        </td>
                                        <td>
                                            <c:forEach var="c" items="${countCancel}">
                                                <c:if test="${c.userID == u.userID}">${c.countCancel}</c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <a  class="btn btn-outline-danger btn-sm"href="#" onclick="ConfirmDelete('${u.userID}')"><i class="bi bi-x-circle-fill">Xóa tài khoản</i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.css"></script>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thông tin tài khoản</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="result">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Xóa tài khoản người dùng</h3>
                <a href="#location" class="close" data-dismiss="modal" onclick="closeForm()" style="text-decoration: none">X</a>
            </div>
            <div class="modal-body">
                <h4>Bạn có chắc muốn xóa người dùng không?</h4>
                <form>
                    <div class="mb-3">
                        <label for="message-text" class="col-form-label">Lý do xóa tài khoản:</label>
                        <textarea class="form-control" id="message-text"></textarea>
                        <div class="invalid-feedback" id="invalid-feedback">
                            Vui lòng điền lý do.
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="#location" class="btn btn-default" data-dismiss="modal" onclick="closeForm()">Hủy</a>
                <a href="#location" class="btn btn-success" onclick="DeleteEmployee()">Xác nhận</a>
            </div>

        </div>
    </div>
</div>
<!--        @*hidden field for storing current employeeId*@-->
<input type="hidden" id="hiddenEmployeeId" />

<script>
    function GetBecomingOwnerInfo(UserID) {
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/getBecomingOwnerInfo.do",
            type: 'get',
            data: {
                UserID: UserID
            },
            success: function (responseData) {
                document.getElementById("result").innerHTML
                        = responseData;
            }
        });
    }

    function closeForm() {
        $("#myModal").modal('hide');
    }

    var ConfirmDelete = function (EmployeeId) {
        /*var test = $("#mytable tr").find("#test").html();*/
        $("#hiddenEmployeeId").val(EmployeeId);
        $("#myModal").modal('show');
    }

    var DeleteEmployee = function () {
        var empId = $("#hiddenEmployeeId").val();
        var reason = $("#message-text").val();
        if (reason === '') {
//                        reason = 'Không có lý do';
            document.getElementById("message-text").classList.add("border");
            document.getElementById("message-text").classList.add("border-danger");
            $("#invalid-feedback").show();
        } else {
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/deleteUser.do",
                type: 'get',
                data: {Id: empId,
                    Reason: reason
                },
                success: function () {
                    $("#myModal").modal("hide");
                    $("#row_" + empId).remove();
                }

            });
        }
    }
</script>
