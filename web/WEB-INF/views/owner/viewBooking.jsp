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
<div class="px-4 px-lg-5 mb-3 mx-auto mt-3" style="width: 50%">
    <table class="table table-striped">
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
                                <td>Đã được đặt</td>
                                <td>
                                    <a class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/toy/edit.do?id=${toy.id}"><i class="bi bi-pencil-square"></i> Edit</a> 
                                </td>
                                <td>
                                    <a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/owner/delete.do?bookingID=${listPlayedEqualAfter[count-1].bookingID}&userID=${listPlayedEqualAfter[count-1].userID}"><i class="bi bi-x-circle-fill"></i> Delete</a>
                                </td>
                                ${listPlayedAfter[count].bookingID}
                            </c:when>
                            <c:when test="${listPlayedEqualBefore[count1].timeID == t.timeID}">
                                <c:set var="count1" value="${count1 + 1}"/>
                                <td>Đã được đặt</td>
                                <td>
                                    <a class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/toy/edit.do?id=${toy.id}"><i class="bi bi-pencil-square"></i> Edit</a> 
                                </td>
                                <td>

                                </td>
                            </c:when>
                            <c:when test="${listPlayedBefore[count2].timeID == t.timeID}">
                                <c:set var="count2" value="${count2 + 1}"/>
                                <td>Đã được đặt</td>
                                <td>
                                    <a class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/toy/edit.do?id=${toy.id}"><i class="bi bi-pencil-square"></i> Edit</a> 
                                </td>
                                <td>

                                </td>
                            </c:when>
                            <c:when test="${listPlayedAfter[count3].timeID == t.timeID}">
                                <c:set var="count3" value="${count3 + 1}"/>
                                <td>Đã được đặt</td>
                                <td>
                                    <a class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/toy/edit.do?id=${toy.id}"><i class="bi bi-pencil-square"></i> Edit</a> 
                                </td>
                                <td>
                                    <a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/owner/delete.do?bookingID=${listPlayedAfter[count3-1].bookingID}&userID=${listPlayedAfter[count3-1].userID}"><i class="bi bi-x-circle-fill"></i> Delete</a>
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
</script>

