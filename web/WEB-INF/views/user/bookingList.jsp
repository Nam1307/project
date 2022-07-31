<%-- 
    Document   : bookingList
    Created on : May 12, 2022, 9:54:34 PM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="card container mt-5 mb-5">
    <table class="table table-hover shopping-cart-wrap" id="example"
           class="table table-striped data-table"
           style="width: 100%">
        <thead class="text-muted" id="location">
            <tr>
                <th scope="col">Sân</th>
                <th scope="col" width="120">Loại sân</th>
                <th scope="col" width="200" class="text-right">Giá</th>
                <th scope="col" width="120">Tình trạng</th>
                <th scope="col" width="200" class="text-right">Xử lý</th>
                <th scope="col" width="120">Lý do</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="b" items="${listPlayedAfter}">
                <c:forEach var="cp" items="${listCP1}">
                    <c:forEach var="p" items="${listP1}">
                        <c:if test="${b.childrenPitchID eq cp.childrenPitchID && cp.pitchID eq p.pitchID}">
                            <tr>
                                <td>
                                    <figure class="media">
                                        <div class="img-wrap"><img src="${pageContext.request.contextPath}/images/${p.pitchID}.jpg" class="img-thumbnail img-sm" width="200px" height="200px"></div>
                                        <figcaption class="media-body">
                                            <h6 class="title text-truncate">Sân chính: ${p.pitchName} - <c:if test="${cp.status != false}">Sân con: ${cp.childrenPitchName}</c:if> 
                                                <c:if test="${cp.status == false}">
                                                    <c:forEach var="u" items="${listU}">
                                                        <c:if test="${u.userID == p.userID}">
                                                            Sân đã bị xóa. Liên hệ chủ sân (${u.phone})
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </h6>
                                            <dl class="param param-inline small">
                                                <dt>Ngày: </dt>
                                                <dd><fmt:formatDate value="${b.bookingDate}" pattern="dd-MM-yyyy" /></dd>
                                            </dl>
                                            <dl class="param param-inline small">
                                                <dt>Giờ: </dt>
                                                <dd><fmt:formatDate type="time" value="${b.timeStart}" pattern="HH:mm" />-<fmt:formatDate type="time" value="${b.timeEnd}" pattern="HH:mm" />h</dd>
                                            </dl>
                                        </figcaption>
                                    </figure> 
                                </td>
                                <td class="col-sm-1 col-md-1" style="text-align: center"> 
                                    <input type="number" class="form-control" id="exampleInputEmail1" value="${cp.childrenPitchType}" disabled="">
                                </td>
                                <td class="col-sm-1 col-md-1" style="text-align: center"> 
                                    <div class="price-wrap"> 
                                        <var class="price"><fmt:formatNumber value="${cp.price}" pattern="#,##0VNĐ" /></var> 
                                    </div> <!-- price-wrap .// -->
                                </td>
                                <td id="row_${b.bookingID}_status"> 
                                    <c:if test="${b.status == false}">
                                        <div class="price-wrap"> 
                                            <var class="price">Đã hủy sân</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                    <c:if test="${b.status == true}">
                                        <div class="price-wrap"> 
                                            <var class="price">Chưa đá</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                </td>
                                <td class="text-right"> 
                                    <c:if test="${b.status == true}">
                                        <a id="row_${b.bookingID}_button" href="#" class="btn btn-outline-danger" onclick="ConfirmDelete('${b.bookingID}')"> × Hủy sân</a>
                                    </c:if>
                                </td>
                                <td class="text-right" id="row_${b.bookingID}_content"> 
                                    <c:if test="${b.status == false}">
                                        <div class="price-wrap"> 
                                            <var class="price">${b.reasonContent}</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </c:forEach>
            <c:forEach var="b" items="${listPlayedEqualAfter}">
                <c:forEach var="cp" items="${listCP1}">
                    <c:forEach var="p" items="${listP1}">
                        <c:if test="${b.childrenPitchID eq cp.childrenPitchID && cp.pitchID eq p.pitchID}">
                            <tr id="row_${b.bookingID}">
                                <td>
                                    <figure class="media">
                                        <div class="img-wrap"><img src="${pageContext.request.contextPath}/images/${p.pitchID}.jpg" class="img-thumbnail img-sm" width="200px" height="200px"></div>
                                        <figcaption class="media-body">
                                            <h6 class="title text-truncate">Sân chính: ${p.pitchName} - <c:if test="${cp.status != false}">Sân con: ${cp.childrenPitchName}</c:if> 
                                                <c:if test="${cp.status == false}">
                                                    <c:forEach var="u" items="${listU}">
                                                        <c:if test="${u.userID == p.userID}">
                                                            Sân đã bị xóa. Liên hệ chủ sân (${u.phone})
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </h6>
                                            <dl class="param param-inline small">
                                                <dt>Ngày: </dt>
                                                <dd><fmt:formatDate value="${b.bookingDate}" pattern="dd-MM-yyyy" /></dd>
                                            </dl>
                                            <dl class="param param-inline small">
                                                <dt>Giờ: </dt>
                                                <dd><fmt:formatDate type="time" value="${b.timeStart}" pattern="HH:mm" />-<fmt:formatDate type="time" value="${b.timeEnd}" pattern="HH:mm" />h</dd>
                                            </dl>
                                        </figcaption>
                                    </figure> 
                                </td>
                                <td class="col-sm-1 col-md-1" style="text-align: center"> 
                                    <input type="number" class="form-control" id="exampleInputEmail1" value="${cp.childrenPitchType}" disabled="">
                                </td>
                                <td class="col-sm-1 col-md-1" style="text-align: center"> 
                                    <div class="price-wrap"> 
                                        <var class="price"><fmt:formatNumber value="${cp.price}" pattern="#,##0VNĐ" /></var> 
                                    </div> <!-- price-wrap .// -->
                                </td>
                                <td id="row_${b.bookingID}_status"> 
                                    <c:if test="${b.status == false}">
                                        <div class="price-wrap"> 
                                            <var class="price">Đã hủy sân</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                    <c:if test="${b.status == true}">
                                        <div class="price-wrap"> 
                                            <var class="price">Chưa đá</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                </td>
                                <td class="text-right"> 
                                    <c:if test="${b.status == true}">
                                        <a href="#" id="row_${b.bookingID}_button" class="btn btn-outline-danger" onclick="ConfirmDelete('${b.bookingID}')"> × Hủy sân</a>
                                    </c:if>
                                </td>
                                <td class="text-right" id="row_${b.bookingID}_content"> 
                                    <c:if test="${b.status == false}">
                                        <div class="price-wrap"> 
                                            <var class="price">${b.reasonContent}</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </c:forEach>
            <c:forEach var="b" items="${listPlayedEqualBefore}">
                <c:forEach var="cp" items="${listCP1}">
                    <c:forEach var="p" items="${listP1}">
                        <c:if test="${b.childrenPitchID eq cp.childrenPitchID && cp.pitchID eq p.pitchID}">
                            <tr id="row_${b.bookingID}">
                                <td>
                                    <figure class="media">
                                        <div class="img-wrap"><img src="${pageContext.request.contextPath}/images/${p.pitchID}.jpg" class="img-thumbnail img-sm" width="200px" height="200px"></div>
                                        <figcaption class="media-body">
                                            <h6 class="title text-truncate">Sân chính: ${p.pitchName} - <c:if test="${cp.status != false}">Sân con: ${cp.childrenPitchName}</c:if> 
                                                <c:if test="${cp.status == false}">
                                                    <c:forEach var="u" items="${listU}">
                                                        <c:if test="${u.userID == p.userID}">
                                                            Sân đã bị xóa. Liên hệ chủ sân (${u.phone})
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </h6>
                                            <dl class="param param-inline small">
                                                <dt>Ngày: </dt>
                                                <dd><fmt:formatDate value="${b.bookingDate}" pattern="dd-MM-yyyy" /></dd>
                                            </dl>
                                            <dl class="param param-inline small">
                                                <dt>Giờ: </dt>
                                                <dd><fmt:formatDate type="time" value="${b.timeStart}" pattern="HH:mm" />-<fmt:formatDate type="time" value="${b.timeEnd}" pattern="HH:mm" />h</dd>
                                            </dl>
                                        </figcaption>
                                    </figure> 
                                </td>
                                <td class="col-sm-1 col-md-1" style="text-align: center"> 
                                    <input type="number" class="form-control" id="exampleInputEmail1" value="${cp.childrenPitchType}" disabled="">
                                </td>
                                <td class="col-sm-1 col-md-1" style="text-align: center"> 
                                    <div class="price-wrap"> 
                                        <var class="price"><fmt:formatNumber value="${cp.price}" pattern="#,##0VNĐ" /></var> 
                                    </div> <!-- price-wrap .// -->
                                </td>
                                <td> 
                                    <c:if test="${b.status == false}">
                                        <div class="price-wrap"> 
                                            <var class="price">Đã hủy sân</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                    <c:if test="${b.status == true}">
                                        <div class="price-wrap"> 
                                            <var class="price">Đã đá</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                </td>
                                <td class="text-right"> 
                                    <c:if test="${b.status == true && p.pitchStatus == true}">
                                        <a href="${pageContext.request.contextPath}/user/goToComment.do?userID=${user.userID}&pitchID=${p.pitchID}" class="btn btn-outline-success"><i class="bi bi-pen-fill"></i> Đánh giá</a>
                                    </c:if>
                                </td>
                                <td class="text-right"> 
                                    <c:if test="${b.status == false}">
                                        <div class="price-wrap"> 
                                            <var class="price">${b.reasonContent}</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </c:forEach>
            <c:forEach var="b" items="${listPlayedBefore}">
                <c:forEach var="cp" items="${listCP1}">
                    <c:forEach var="p" items="${listP1}">
                        <c:if test="${b.childrenPitchID eq cp.childrenPitchID && cp.pitchID eq p.pitchID}">
                            <tr>
                                <td>
                                    <figure class="media">
                                        <div class="img-wrap"><img src="${pageContext.request.contextPath}/images/${p.pitchID}.jpg" class="img-thumbnail img-sm" width="200px" height="200px"></div>
                                        <figcaption class="media-body">
                                            <h6 class="title text-truncate">Sân chính: ${p.pitchName} - <c:if test="${cp.status != false}">Sân con: ${cp.childrenPitchName}</c:if> 
                                                <c:if test="${cp.status == false}">
                                                    <c:forEach var="u" items="${listU}">
                                                        <c:if test="${u.userID == p.userID}">
                                                            Sân đã bị xóa. Liên hệ chủ sân (${u.phone})
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </h6>
                                            <dl class="param param-inline small">
                                                <dt>Ngày </dt>
                                                <dd><fmt:formatDate value="${b.bookingDate}" pattern="dd-MM-yyyy" /></dd>
                                            </dl>
                                            <dl class="param param-inline small">
                                                <dt>Giờ </dt>
                                                <dd><fmt:formatDate type="time" value="${b.timeStart}" pattern="HH:mm" />-<fmt:formatDate type="time" value="${b.timeEnd}" pattern="HH:mm" />h</dd>
                                            </dl>
                                        </figcaption>
                                    </figure>
                                </td>
                                <td class="col-sm-1 col-md-1" style="text-align: center"> 
                                    <input type="number" class="form-control" id="exampleInputEmail1" value="${cp.childrenPitchType}" disabled="">
                                </td>
                                <td class="col-sm-1 col-md-1" style="text-align: center"> 
                                    <div class="price-wrap"> 
                                        <var class="price"><fmt:formatNumber value="${cp.price}" pattern="#,##0VNĐ" /></var> 
                                    </div> <!-- price-wrap .// -->
                                </td>
                                <td> 
                                    <c:if test="${b.status == false}">
                                        <div class="price-wrap"> 
                                            <var class="price">Đã hủy sân</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                    <c:if test="${b.status == true}">
                                        <div class="price-wrap"> 
                                            <var class="price">Đã đá</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                </td>
                                <td class="text-right"> 
                                    <c:if test="${b.status == true && p.pitchStatus == true}">
                                        <a href="${pageContext.request.contextPath}/user/goToComment.do?userID=${user.userID}&pitchID=${p.pitchID}" class="btn btn-outline-success"><i class="bi bi-pen-fill"></i> Đánh giá</a>
                                    </c:if>
                                </td>
                                <td class="text-right"> 
                                    <c:if test="${b.status == false}">
                                        <div class="price-wrap"> 
                                            <var class="price">${b.reasonContent}</var> 
                                        </div> <!-- price-wrap .// -->
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>
</div> <!-- card.// -->

<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Hủy đặt sân</h3>
                <a href="#" class="close" data-dismiss="modal" onclick="closeForm()">X</a>
            </div>
            <div class="modal-body">
                <h4>Bạn có chắc hủy lịch đặt sân này không?</h4>
                <form>
                    <div class="mb-3">
                        <label for="message-text" class="col-form-label">Lý do hủy sân:</label>
                        <textarea class="form-control" id="message-text" required></textarea>
                        <div class="invalid-feedback" id="invalid-feedback">
                            Vui lòng điền lý do.
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn btn-default" data-dismiss="modal" onclick="closeForm()">Hủy</a>
                <button class="btn btn-primary" onclick="DeleteEmployee()">Xác nhận</button>
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
        var reason = $("#message-text").val();
        if (reason === '') {
//                        reason = 'Không có lý do';
            document.getElementById("message-text").classList.add("border");
            document.getElementById("message-text").classList.add("border-danger");
            $("#invalid-feedback").show();
        } else {
            $.ajax({
                url: "${pageContext.request.contextPath}/booking/deleteBooking.do",
                type: 'get',
                data: {Id: empId,
                    Reason: reason
                },
                success: function () {
                    $("#myModal").modal("hide");
                    $("#row_" + empId + "_button").remove();
                    document.getElementById("row_" + empId + "_status").innerHTML
                            = "<div class=\"price-wrap\"> \n" +
                            "                                            <var class=\"price\">Đã hủy sân</var> \n" +
                            "                                        </div> <!-- price-wrap .// -->";
                    document.getElementById("row_" + empId + "_content").innerHTML
                            = "<div class=\"price-wrap\"> \n" +
                            "                                            <var class=\"price\">" + reason + "</var> \n" +
                            "                                        </div> <!-- price-wrap .// -->";
                }

            });
        }
    }
</script>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.css"></script>
