<%-- 
    Document   : viewBecomingOwner
    Created on : Jun 14, 2022, 10:05:20 PM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2 class="d-flex justify-content-center mt-5">Xét duyệt chủ sân</h2>
<div class="px-4 px-lg-5 mb-3 mx-auto mt-3 table-responsive mt-5">
    <div class="row">
        <div class="col-md-12 mb-3">
            <div class="card">
                <div class="card-header">
                    <span><i class="bi bi-table me-2"></i></span> Dữ liệu đăng ký
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
                                    <th>Xem thông tin đăng ký</th>
                                    <th>Xử lý</th>
                                </tr>
                            </thead>
                            <tbody id="myContent">
                                <c:forEach var="u" items="${listU}" varStatus="count">
                                    <tr id="row_${u.userID}">
                                        <td>${count.index + 1}</td>
                                        <td id="row_${cp.childrenPitchID}_name">${u.fullName}</td>
                                        <td>
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="GetBecomingOwnerInfo('${u.userID}')">
                                                Xem thông tin
                                            </button>
                                        </td>
                                        <td>
                                            <a  class="btn btn-outline-success btn-sm"href="#" onclick="ConfirmBecomingOwner('${u.userID}')"><i class="bi bi-x-circle-fill">Xác nhận</i></a>
                                            <a  class="btn btn-outline-danger btn-sm"href="#" onclick="DenyBecomingOwner('${u.userID}')"><i class="bi bi-x-circle-fill">Từ chối</i></a>
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

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thông tin người đặt sân</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="result">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Xác nhận trở thành chủ sân</h3>
                <a href="#location" class="close" data-dismiss="modal" onclick="closeForm()">X</a>
            </div>
            <div class="modal-body">
                <h4>Bạn có chắc xác nhận cho người này trở thành chủ sân không?</h4>
            </div>
            <div class="modal-footer">
                <a href="#location" class="btn btn-default" data-dismiss="modal" onclick="closeForm()">Hủy</a>
                <a href="#location" class="btn btn-success" onclick="Confirm()">Xác nhận</a>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="myModal1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Từ chối trở thành chủ sân</h3>
                <a href="#location" class="close" data-dismiss="modal" onclick="closeForm1()">X</a>
            </div>
            <div class="modal-body">
                <h4>Bạn có chắc từ chối cho người này trở thành chủ sân không?</h4>
            </div>
            <form>
                <div class="mb-3">
                    <label for="message-text" class="col-form-label">Lý do hủy sân:</label>
                    <textarea class="form-control" id="message-text"></textarea>
                    <div class="invalid-feedback" id="invalid-feedback">
                        Vui lòng điền lý do.
                    </div>
                </div>
            </form>
            <div class="modal-footer">
                <a href="#location" class="btn btn-default" data-dismiss="modal" onclick="closeForm1()">Hủy</a>
                <a href="#location" class="btn btn-success" onclick="Deny()">Xác nhận</a>
            </div>

        </div>
    </div>
</div>
<!--        @*hidden field for storing current employeeId*@-->
<input type="hidden" id="hiddenEmployeeId" />

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.css"></script>

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

                    var ConfirmBecomingOwner = function (EmployeeId) {
                        /*var test = $("#mytable tr").find("#test").html();*/
                        $("#hiddenEmployeeId").val(EmployeeId);
                        $("#myModal").modal('show');
                    }

                    var Confirm = function () {
                        var empId = $("#hiddenEmployeeId").val();

                        $.ajax({
                            url: "${pageContext.request.contextPath}/admin/confirmOwner.do",
                            type: 'get',
                            data: {Id: empId,
                            },
                            success: function () {
                                $("#myModal").modal("hide");
                                $("#row_" + empId).remove();
                            }
                        });
                    };

                    function closeForm1() {
                        $("#myModal1").modal('hide');
                    }

                    var DenyBecomingOwner = function (EmployeeId) {
                        /*var test = $("#mytable tr").find("#test").html();*/
                        $("#hiddenEmployeeId").val(EmployeeId);
                        $("#myModal1").modal('show');
                    };

                    var Deny = function () {
                        var empId = $("#hiddenEmployeeId").val();
                        var reason = $("#message-text").val();

                        if (reason === '') {
                            document.getElementById("message-text").classList.add("border");
                            document.getElementById("message-text").classList.add("border-danger");
                            $("#invalid-feedback").show();
                        } else {
                            $.ajax({
                                url: "${pageContext.request.contextPath}/admin/denyOwner.do",
                                type: 'get',
                                data: {Id: empId,
                                    Reason: reason
                                },
                                success: function () {
                                    $("#myModal1").modal("hide");
                                    $("#row_" + empId).remove();
                                }
                            });
                        }
                    };

                    function searchByName(param) {
                        var name = param.value;
                        $.ajax({
                            url: "${pageContext.request.contextPath}/admin/searchForBecomingOwner.do",
                            type: 'get',
                            data: {
                                name: name
                            },
                            success: function (responseData) {
                                document.getElementById("myContent").innerHTML
                                        = responseData;
                            }
                        });
                    }
</script>
