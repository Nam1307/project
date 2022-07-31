<%-- 
    Document   : commentManagement
    Created on : Jun 28, 2022, 9:03:33 PM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2 class="d-flex justify-content-center mt-5">Quản lý bình luận</h2>
<div class="px-4 px-lg-5 mb-3 mx-auto mt-3 table-responsive mt-5">
    <div class="row">
        <div class="col-md-12 mb-3">
            <div class="card">
                <div class="card-header">
                    <span><i class="bi bi-table me-2"></i></span> Danh sách bình luận
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
                                    <th>Tên người bình luận</th>
                                    <th>Bình luận</th>
                                    <th>Đánh giá</th>
                                    <th>Xử lý</th>
                                </tr>
                            </thead>
                            <tbody id="myContent">
                                <c:forEach var="c" items="${listC}" varStatus="count">
                                    <c:forEach var="u" items="${listU}">
                                        <c:if test="${c.userID == u.userID}">
                                            <tr id="row_${c.commentID}">
                                                <td>${count.index + 1}</td>
                                                <td>${u.fullName}</td>
                                                <td>${c.content}</td>
                                                <td>

                                                        <c:forEach begin="1" step="1" end="${c.rating}">
                                                            <i class="bi-star-fill text-warning"></i>
                                                        </c:forEach>
                                                        <c:forEach begin="1" step="1" end="${5 - c.rating}">
                                                            <i class="bi-star-fill"></i>
                                                        </c:forEach>

                                                </td>
                                                <td>
                                                    <a  class="btn btn-outline-danger btn-sm"href="#" onclick="ConfirmDelete('${c.commentID}', '${c.pitchID}')"><i class="bi bi-x-circle-fill">Xóa bình luận</i></a>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
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
                <h3 class="modal-title">Xóa bình luận</h3>
                <a href="#location" class="close" data-dismiss="modal" onclick="closeForm()" style="text-decoration: none">X</a>
            </div>
            <div class="modal-body">
                <h4>Bạn có chắc muốn xóa bình luận này không?</h4>
            </div>
            <div class="modal-footer">
                <a href="#location" class="btn btn-default" data-dismiss="modal" onclick="closeForm()">Hủy</a>
                <a href="#location" class="btn btn-success" onclick="DeleteEmployee()">Xác nhận</a>
            </div>

        </div>
    </div>
</div>

<!--        @*hidden field for storing current employeeId*@-->
<input type="hidden" id="pitchID" />
<input type="hidden" id="commentID" />

<script>
    function closeForm() {
        $("#myModal").modal('hide');
    }

    var ConfirmDelete = function (commentId, pitchId) {
        $("#pitchID").val(pitchId);
        $("#commentID").val(commentId);
        $("#myModal").modal('show');
    };
    
    var DeleteEmployee = function () {
    var pitchID = $("#pitchID").val();
    var commentID = $("#commentID").val();
        
            $.ajax({
                url: "${pageContext.request.contextPath}/admin/deleteComment.do",
                type: 'get',
                data: {commentID: commentID,
                    pitchID: pitchID
                },
                success: function () {
                   $("#myModal").modal("hide");
                   $("#row_" + commentID).remove();
                }

            });
    };
</script>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.css"></script>
