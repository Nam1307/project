<%-- 
    Document   : pitchManagement
    Created on : Jun 25, 2022, 8:25:56 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2 class="d-flex justify-content-center mt-5 mb-5">Quản lý sân chính</h2>
<div class="px-4 px-lg-5 mb-3 mx-auto mt-3 table-responsive">
    <div class="row">
        <div class="col-md-12 mb-3">
            <a class="btn btn-outline-success btn-lg mt-5 mb-5" href="${pageContext.request.contextPath}/owner/createPitch.do"><i class="bi bi-plus-circle"></i> Tạo mới sân chính</a>
            <div class="card">
                <div class="card-header">
                    <span><i class="bi bi-table me-2"></i></span> Danh sách sân chính
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
                                    <th>Tên sân</th>
                                    <th>Xử lý</th>
                                </tr>
                            </thead>
                            <tbody id="myContent">
                                <c:forEach var="p" items="${listP}" varStatus="count">
                                    <tr id="row_${p.pitchID}">
                                        <td>${count.index + 1}</td>
                                        <td id="row_${p.pitchID}_name">${p.pitchName}</td>
                                        <td>
                                            <a  class="btn btn-outline-success btn-sm"href="${pageContext.request.contextPath}/owner/editPitch.do?PitchID=${p.pitchID}"><i class="bi bi-pen-fill"></i> Chỉnh sửa sân</i></a>
                                            <a  class="btn btn-outline-danger btn-sm"href="#" onclick="ConfirmDelete('${p.pitchID}')"><i class="bi bi-x-circle-fill"> Xóa sân</i></a>
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

<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Xóa sân chính</h3>
                <a href="#location" class="close" data-dismiss="modal" onclick="closeForm()" style="text-decoration: none">X</a>
            </div>
            <div class="modal-body">
                <h4>Bạn có chắc muốn xóa sân này không?</h4>
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
        $.ajax({
            url: "${pageContext.request.contextPath}/owner/deletePitch.do",
            type: 'get',
            data: {Id: empId,
            },
            success: function () {
                $("#myModal").modal("hide");
                $("#row_" + empId).remove();
            }

        });
    }
</script>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.css"></script>


