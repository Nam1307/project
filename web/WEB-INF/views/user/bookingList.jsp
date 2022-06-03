<%-- 
    Document   : bookingList
    Created on : May 12, 2022, 9:54:34 PM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="card container mt-5 mb-5">
    <table class="table table-hover shopping-cart-wrap">
        <thead class="text-muted" id="location">
            <tr>
                <th scope="col">Stadium</th>
                <th scope="col" width="120">Number of people</th>
                <th scope="col" width="120">Status</th>
                <th scope="col" width="200" class="text-right">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="b" items="${listPlayedAfter}">
                <c:forEach var="cp" items="${listCP1}">
                    <c:forEach var="p" items="${listP1}">
                        <c:if test="${b.childrenPitchID eq cp.childrenPitchID && cp.pitchID eq p.pitchID}">
                            <tr id="row_${b.bookingID}">
                                <td>
                                    <figure class="media">
                                        <div class="img-wrap"><img src="${pageContext.request.contextPath}/images/${p.pitchID}.jpg" class="img-thumbnail img-sm" width="200px" height="200px"></div>
                                        <figcaption class="media-body">
                                            <h6 class="title text-truncate">Name of stadium </h6>
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
                                    <input type="number" class="form-control" id="exampleInputEmail1" value="3" disabled="">
                                </td>
                                <td> 
                                    <div class="price-wrap"> 
                                        <var class="price">Chưa đá</var> 
                                    </div> <!-- price-wrap .// -->
                                </td>
                                <td class="text-right"> 
                                    <a href="#" class="btn btn-outline-danger" onclick="ConfirmDelete('${b.bookingID}')"> × Hủy sân</a>
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
                                            <h6 class="title text-truncate">Name of stadium </h6>
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
                                    <input type="number" class="form-control" id="exampleInputEmail1" value="3" disabled="">
                                </td>
                                <td> 
                                    <div class="price-wrap"> 
                                        <var class="price">Đã đá</var> 
                                    </div> <!-- price-wrap .// -->
                                </td>
                                <td class="text-right"> 
                                    <a href="" class="btn btn-outline-danger">Bình luận</a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>
</div> <!-- card.// -->
</div> 
<div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">Hủy đặt sân</h3>
                    <a href="#" class="close" data-dismiss="modal" onclick="closeForm()">X</a>
                </div>
                <div class="modal-body">
                    <h4>Bạn có chắc hủy lịch đặt sân này không?</h4>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal" onclick="closeForm()">Hủy</a>
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
            url: "${pageContext.request.contextPath}/booking/deleteBooking.do",
            type: 'get',
            data: {Id: empId},
            success: function () {
                $("#myModal").modal("hide");
                $("#row_" + empId).remove();
            }

        })

    }
</script>
