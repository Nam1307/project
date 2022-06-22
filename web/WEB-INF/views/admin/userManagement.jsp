<%-- 
    Document   : userManagement
    Created on : Jun 20, 2022, 10:12:09 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="px-4 px-lg-5 mb-3 mx-auto mt-3 table-responsive" style="width: 70%; padding-top: 30px">
    <div class="card mb-3 text-dark bg-info" style="max-width: 300px;">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="/WebsiteOrderStadium/images/user.png" class="img-fluid rounded-start mt-1" alt="...">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h2 class="card-title">${listU.size()}</h2>
                    <p class="card-text text-muted">Người dùng</p>
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="margin-left: -20px">
        <div class="col-4 mt-5" style="padding: 0px; margin: 0px">
            <i style="left: 30px; position: relative" class="bi bi-search"></i>
            <input type="text" style="text-indent: 30px; width: 300px" placeholder="Tìm kiếm" oninput="searchByName(this)" />
        </div>
        <div class="col-4"></div>
        <div class="form-floating col-4 mt-4" style="padding: 0px">
            <select id="selectBox" name="districtID" onchange="chooseRole()" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                <option selected="selected" value="">Tất cả</option>
                <option value="OW">Chủ sân</option>
                <option value="US">Khách hàng</option>
            </select>
            <label for="floatingSelect">Người dùng:</label>
        </div>
    </div>
</div>
<div class="px-4 px-lg-5 mb-3 mx-auto mt-3 table-responsive" style="width: 70%; padding-top: 50px; padding-bottom: 50px">
    <table class="table table-striped ">
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
                        <a  class="btn btn-outline-danger btn-sm"href="#" onclick="DenyBecomingOwner('${u.userID}')"><i class="bi bi-x-circle-fill">Từ chối</i></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function chooseRole() {
        var selectBox = document.getElementById("selectBox");
        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
        $.ajax({
            url: "${pageContext.request.contextPath}/admin/chooseRole.do",
            type: 'post',
            data: {
                role: selectedValue
            },
            success: function (responseData) {
                document.getElementById("myContent").innerHTML
                        = responseData;
            }
        });
    }
</script>
