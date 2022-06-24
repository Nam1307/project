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
                                    <th>Xử lý</th>
                                </tr>
                            </thead>
                            <tbody id="myContent">
                                <c:forEach var="u" items="${listU}" varStatus="count">
                                     ${u.wardID}
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
                                            <a  class="btn btn-outline-danger btn-sm"href="#" onclick="DenyBecomingOwner('${u.userID}')"><i class="bi bi-x-circle-fill">Xóa</i></a>
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
</script>
