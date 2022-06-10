<%-- 
    Document   : viewBooking
    Created on : Jun 4, 2022, 2:04:40 PM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="px-4 px-lg-5 mb-3 mx-auto mt-3">
    <form action="${pageContext.request.contextPath}/owner/search.do" method="post" class="mt-3 row border-top border-bottom bg-light" style="padding-top: 50px; padding-bottom: 35px">
        <div class="form-floating col-3" style="padding: 0px">
            <select id="selectBox" name="pitchID" onchange="setChildrenPitch()" class="form-select form-select-lg" id="floatingSelect" aria-label="Floating label select example" style="height: 70px">
                <option selected="selected" disabled value="">Sân chính</option>
                <c:forEach var="d" items="${listP}" >
                    <option ${pitchID == d.pitchID?"selected":""} value="${d.pitchID}">${d.pitchName}</option>
                </c:forEach>
            </select>
            <label for="floatingSelect">Sân chính:</label>
        </div>
        <div class="form-floating mb-3 col-3">
            <select id="ward" name="childrenPitchID" class="form-select form-select-lg" id="floatingSelect" aria-label="Floating label select example" style="height: 70px">
                <option selected disabled value="">Sân con</option>
                <c:forEach var="w" items="${listCP}" >
                    <option ${cpID == w.childrenPitchID?"selected":""} value="${w.childrenPitchID}">${w.childrenPitchName}</option>
                </c:forEach>
            </select>
            <label for="floatingSelect">Sân con:</label>
        </div>
        <div class="form-floating mb-3 col-3">
            <input class="form-control form-control-lg text-center me-3" style="height: 70px" id="inputDate" name="dateBooking" type="date" value="${dateBooking}" />
            <label for="floatingSelect">Ngày:</label>
        </div>
        <input type="hidden" name="userID" value="${userID}"/>
        <button type="submit" class="btn btn-lg btn-outline-success col-3" style="height: 70px"><i class="bi bi-search"></i> Search</button> 
    </form>
</div>
${listB}
<div class="px-4 px-lg-5 mb-3 mx-auto mt-3 table-responsive" style="width: 70%">
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>Thời gian</th>
                <th>Trạng thái</th>
                <th>Xem chi tiết</th>
                <th>Hủy đặt sân</th>
            </tr>
        </thead>
        <tbody>
            <c:set var = "count" value = "0"/>
            <c:set var = "count1" value = "0"/>
            <c:set var = "count2" value = "0"/>
            <c:set var = "count3" value = "0"/>
            <c:forEach var="t" items="${listT}">
                <tr>
                    <td><fmt:formatDate type="time" value="${t.timeStart}" pattern="HH:mm" />-<fmt:formatDate type="time" value="${t.timeEnd}" pattern="HH:mm" />h</td>
                    <c:if test="${listB == []}">
                        <td>Chưa được đặt</td>
                        <td>

                        </td>
                        <td>

                        </td>
                    </c:if>
                    <c:if test="${listB != []}">
                        <c:choose>
                            <c:when test="${listPlayedEqualAfter[count].timeID == t.timeID}">
                                <c:set var="count" value="${count + 1}"/>
                                <td id="row_${listPlayedEqualAfter[count-1].bookingID}_title">Đã được đặt</td>
                                <td>
                                    <button id="row_${listPlayedEqualAfter[count-1].bookingID}_info" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="GetBookingInfo('${listPlayedEqualAfter[count-1].bookingID}')">
                                        Thông tin người đặt
                                    </button>
                                </td>
                                <td>
                                    <a id="row_${listPlayedEqualAfter[count-1].bookingID}_delete" class="btn btn-sm btn-outline-danger" href="#" onclick="ConfirmDelete('${listPlayedEqualAfter[count-1].bookingID}')"><i class="bi bi-x-circle-fill"></i> Delete</a>
                                </td>
                                ${listPlayedAfter[count].bookingID}
                            </c:when>
                            <c:when test="${listPlayedEqualBefore[count1].timeID == t.timeID}">
                                <c:set var="count1" value="${count1 + 1}"/>
                                <td>Đã được đặt</td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="GetBookingInfo('${listPlayedEqualBefore[count1-1].bookingID}')">
                                        Thông tin người đặt
                                    </button>
                                </td>
                                <td>

                                </td>
                            </c:when>
                            <c:when test="${listPlayedBefore[count2].timeID == t.timeID}">
                                <c:set var="count2" value="${count2 + 1}"/>
                                <td>Đã được đặt</td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="GetBookingInfo('${listPlayedBefore[count2-1].bookingID}')">
                                        Thông tin người đặt
                                    </button>
                                </td>
                                <td>

                                </td>
                            </c:when>
                            <c:when test="${listPlayedAfter[count3].timeID == t.timeID}">
                                <c:set var="count3" value="${count3 + 1}"/>
                                <td id="row_${listPlayedAfter[count3-1].bookingID}_title">Đã được đặt</td>
                                <td>
                                    <button id="row_${listPlayedAfter[count3-1].bookingID}_info" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="GetBookingInfo('${listPlayedAfter[count3-1].bookingID}')">
                                        Thông tin người đặt
                                    </button>
                                </td>
                                <td>
                                    <a id="row_${listPlayedAfter[count3-1].bookingID}_delete" class="btn btn-sm btn-outline-danger" href="#" onclick="ConfirmDelete('${listPlayedAfter[count3-1].bookingID}')"><i class="bi bi-x-circle-fill"></i> Delete</a>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>Chưa được đặt</td>
                                <td>

                                </td>
                                <td>

                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
        </tbody>
    </table>
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
                <h3 class="modal-title">Hủy đặt sân</h3>
                <a href="#location" class="close" data-dismiss="modal" onclick="closeForm()">X</a>
            </div>
            <div class="modal-body">
                <h4>Bạn có chắc hủy lịch đặt sân này của khách hàng không?</h4>
                <form>
                    <div class="mb-3">
                        <label for="message-text" class="col-form-label">Lý do hủy sân:</label>
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
    function setChildrenPitch() {
        var selectBox = document.getElementById("selectBox");
        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
        $.ajax({
            url: "${pageContext.request.contextPath}/owner/childrenPitch.do",
            type: 'post',
            data: {
                pitchID: selectedValue
            },
            success: function (responseData) {
                document.getElementById("ward").innerHTML
                        = responseData;
            }
        });
    }

    if (document.getElementById('inputDate').value === '') {
        document.getElementById('inputDate').valueAsDate = new Date();
    }

    function GetBookingInfo(BookingID) {
        $.ajax({
            url: "${pageContext.request.contextPath}/owner/detailBooking.do",
            type: 'get',
            data: {
                BookingID: BookingID
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
                url: "${pageContext.request.contextPath}/owner/delete.do",
                type: 'get',
                data: {Id: empId,
                    Reason: reason
                },
                success: function () {
                    $("#myModal").modal("hide");
//                $("#row_" + empId + "_title").remove();
                    document.getElementById("row_" + empId + "_title").innerHTML = "<td>Chưa được đặt</td>";
                    $("#row_" + empId + "_info").remove();
                    $("#row_" + empId + "_delete").remove();
                }

            });
        }
    }
</script>

