<%-- 
    Document   : childrenPitchManagement
    Created on : Jun 9, 2022, 10:27:45 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
${listCP}
<div class="px-4 px-lg-5 mb-5 mx-auto mt-5">
    <a class="btn btn-outline-success btn-lg" href="${pageContext.request.contextPath}/owner/createChildrenPitch.do?userID=${user.userID}"><i class="bi bi-plus-circle"></i> Tạo sân con mới</a>
</div>
<div class="px-4 px-lg-5 mb-3 mx-auto mt-3">
    <form action="${pageContext.request.contextPath}/owner/viewChildrenPitch.do" method="post" class="mt-3 row border-top border-bottom bg-light" style="padding-top: 50px; padding-bottom: 35px">
        <div class="col-3"></div>
        <div class="form-floating col-3 me-3" style="padding: 0px">
            <select id="selectBox" name="pitchID" onchange="setChildrenPitch()" class="form-select form-select-lg" id="floatingSelect" aria-label="Floating label select example" style="height: 70px">
                <option selected="selected" disabled value="">Sân chính</option>
                <c:forEach var="d" items="${listP}" >
                    <option ${pitchID == d.pitchID?"selected":""} value="${d.pitchID}">${d.pitchName}</option>
                </c:forEach>
            </select>
            <label for="floatingSelect">Sân chính:</label>
        </div>
        <input type="hidden" name="userID" value="${userID}"/>
        <button type="submit" class="btn btn-lg btn-outline-success col-3 ms-3" style="height: 70px"><i class="bi bi-search"></i> Hiển thị sân con</button> 
        <div class="col-3"></div>
    </form>
</div>
<div class="px-4 px-lg-5 mb-3 mx-auto mt-3 table-responsive" style="width: 70%">
    <table class="table table-striped ">
        <thead>
            <tr>
                <th>#</th>
                <th>Tên</th>
                <th>Số người</th>
                <th style="text-align: right">Giá</th>
                <th>Xử lý</th>
            </tr>
        </thead>
        <tbody id="myContent">
            <c:forEach var="cp" items="${listCP}" varStatus="count">
                <tr id="row_${cp.childrenPitchID}">
                    <td>${count.index + 1}</td>
                    <td id="row_${cp.childrenPitchID}_name">${cp.childrenPitchName}</td>
                    <td id="row_${cp.childrenPitchID}_type">${cp.childrenPitchType}</td>
                    <td id="row_${cp.childrenPitchID}_price" style="text-align: right"><fmt:formatNumber value="${cp.price}" pattern="#,##0VNĐ" /></td>
                    <td>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="GetEditInfo('${cp.childrenPitchID}')">
                            Điều chỉnh sân
                        </button>
                        <a  class="btn btn-outline-danger btn-sm"href="#" onclick="ConfirmDelete('${cp.childrenPitchID}')"><i class="bi bi-x-circle-fill">Delete</i></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Thông tin sân con</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" id="result">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="EditChildrenPitch()">Save changes</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Hủy đặt sân</h3>
                <a href="#location" class="close" data-dismiss="modal" onclick="closeForm()">X</a>
            </div>
            <div class="modal-body">
                <h4>Bạn có chắc hủy lịch đặt sân này của khách hàng không?</h4>
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
    function GetEditInfo(ChildrenPitchID) {
        $.ajax({
            url: "${pageContext.request.contextPath}/owner/viewEdit.do",
            type: 'get',
            data: {
                ChildrenPitchID: ChildrenPitchID
            },
            success: function (responseData) {
                document.getElementById("result").innerHTML
                        = responseData;
            }
        });
    }

    var EditChildrenPitch = function () {
        var childrenPitchID = $("#childrenPitchID").val();
        var childrenPitchName = $("#childrenPitchName").val();
        var childrenPitchType = $("#childrenPitchType").val();
        var price = $("#price").val();
        let count = 0;
        let arr = [document.getElementById("childrenPitchName"), document.getElementById("price")];
        for (var i = 0; i < arr.length; i++) {
            if (arr[i].value === "") {
                arr[i].classList.add("border");
                arr[i].classList.add("border-danger");
                $("#invalid-feedback-" + i).show();
                count++;
            }
        }
        if (count === 0) {
            $.ajax({
                url: "${pageContext.request.contextPath}/owner/update.do",
                type: 'get',
                data: {childrenPitchID: childrenPitchID,
                    childrenPitchName: childrenPitchName,
                    childrenPitchType: childrenPitchType,
                    price: price,
                },
                success: function () {
                    $("#exampleModal").modal("hide");
                    document.getElementById("row_" + childrenPitchID + "_name").innerHTML = "<td id=row_" + childrenPitchID + "_name>" + childrenPitchName + "</td>";
                    document.getElementById("row_" + childrenPitchID + "_type").innerHTML = "<td id=row_" + childrenPitchID + "_type>" + childrenPitchType + "</td>";
                    document.getElementById("row_" + childrenPitchID + "_price").innerHTML = "<td id=row_" + childrenPitchID + "_price>" + price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") + "VNĐ</td>";
                }

            });
        }
    };

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
            url: "${pageContext.request.contextPath}/owner/deleteChildrenPitch.do",
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

