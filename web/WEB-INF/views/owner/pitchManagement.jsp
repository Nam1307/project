<%-- 
    Document   : pitchManagement
    Created on : Jun 25, 2022, 8:25:56 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="px-4 px-lg-5 mb-3 mx-auto mt-3 table-responsive">
    <div class="row">
        <div class="col-md-12 mb-3">
            <div class="card">
                <div class="card-header">
                    <span><i class="bi bi-table me-2"></i></span> Dữ liệu sân chính
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
                                            <a  class="btn btn-outline-danger btn-sm"href="${pageContext.request.contextPath}/owner/editPitch.do?PitchID=${p.pitchID}"><i class="bi bi-x-circle-fill">Chỉnh sửa sân</i></a>
                                            <a  class="btn btn-outline-danger btn-sm"href="#" onclick="ConfirmDelete('${cp.childrenPitchID}')"><i class="bi bi-x-circle-fill">Delete</i></a>
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


