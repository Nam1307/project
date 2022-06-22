<%-- 
    Document   : index
    Created on : May 12, 2022, 11:11:51 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<c:if test="${user == null || user.roleID == 'US'}">
    <section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <h2 class="fw-bolder mb-4">Sân được đánh giá 5 sao</h2>
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">     
                <c:forEach var="p" items="${listHR}" >
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <!--                                <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>-->
                            <!-- Product image-->
                            <img class="card-img-top" src="${pageContext.request.contextPath}/images/${p.pitchID}.jpg" alt="..." height="230px" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${p.pitchName}</h5>
                                    <!-- Product price-->
                                    <c:forEach var="lminp" items="${listMinP}" >
                                        <c:if test="${lminp.pitchID == p.pitchID}"><span class="text-muted"><fmt:formatNumber value="${lminp.price}" pattern="#,##0VNĐ - " /></span></c:if>
                                    </c:forEach>
                                    <c:forEach var="lmaxp" items="${listMaxP}" >
                                        <c:if test="${lmaxp.pitchID == p.pitchID}"><span class="text-muted"><fmt:formatNumber value="${lmaxp.price}" pattern="#,##0VNĐ" /></span></c:if>
                                    </c:forEach>
                                    <p>Giá</p>
                                    <p>
                                        ${p.pitchAddress},
                                        <c:forEach var="w" items="${listWard}" >
                                            <c:if test="${w.wardID == p.wardID}">${w.wardName}</c:if>
                                        </c:forEach>
                                        ,
                                        <c:forEach var="d" items="${listD}" >
                                            <c:if test="${d.districtID == p.districtID}">${d.districtName}</c:if>
                                        </c:forEach>
                                    </p>
                                    <div class="d-flex justify-content-center small mb-2">
                                        <c:forEach begin="1" step="1" end="${p.estimation}">
                                            <div class="bi-star-fill text-warning"></div>
                                        </c:forEach>
                                        <c:forEach begin="1" step="1" end="${5 - p.estimation}">
                                            <div class="bi-star-fill"></div>
                                        </c:forEach>
                                    </div>

                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="${pageContext.request.contextPath}/stadium/detail.do?pitchID=${p.pitchID}">Đặt sân</a></div>
                            </div>
                        </div>
                    </div>
                </c:forEach> 
            </div>
        </div>
    </section>
</c:if>
<div class="px-4 px-lg-5 mb-3 container mx-auto" style="margin-top: -45px">
    <!--    <div class="col-3"></div>-->
    <!--    <div class="form-floating col-4 mt-5">
            <select id="selectBox" name="district" onchange="ward()" class="form-select form-select-lg" id="floatingSelect" aria-label="Floating label select example" style="height: 70px">
                <option selected disabled value="">Quận</option>
    <c:forEach var="d" items="${listD}" >
        <option ${district == d.districtID?"selected":""} value="${d.districtID}">${d.districtName}</option>
    </c:forEach>
</select>
<label for="floatingSelect">Quận:</label>
</div>-->
    <form action="${pageContext.request.contextPath}/home/search.do" method="post" class="mt-3 row border-top border-bottom bg-light" style="padding-top: 50px; padding-bottom: 35px">
        <h2 class="fw-bolder mb-4">Tìm kiếm sân</h2>
        <div class="form-floating col-5" style="padding: 0px">
            <select id="selectBox" name="districtID" onchange="setWard()" class="form-select form-select-lg" id="floatingSelect" aria-label="Floating label select example" style="height: 70px">
                <option selected="selected" disabled value="">Quận</option>
                <c:forEach var="d" items="${listD}" >
                    <option ${district == d.districtID?"selected":""} value="${d.districtID}">${d.districtName}</option>
                </c:forEach>
            </select>
            <label for="floatingSelect">Quận:</label>
        </div>
        <!--        <div id="districtID">
                    <input type="hidden" name="districtID" value="${district}">
                </div>-->
        <div class="form-floating mb-3 col-5">
            <select id="ward" name="ward" class="form-select form-select-lg" id="floatingSelect" aria-label="Floating label select example" style="height: 70px">
                <option selected disabled value="">Phường</option>
                <c:forEach var="w" items="${listW}" >
                    <option ${ward == w.wardID?"selected":""} value="${w.wardID}">${w.wardName}</option>
                </c:forEach>
            </select>
            <label for="floatingSelect">Phường:</label>
        </div>
        <button type="submit" class="btn btn-lg btn-outline-success col-2" style="height: 70px"><i class="bi bi-search"></i> Search</button> 
    </form>

</div>
<section class="py-5">
    <div class="container px-4 px-lg-5">
        <h4 class="fw-lighter fst-italic fw-bolder mb-4">
            <c:forEach var="d" items="${listD}" >
                <c:if test="${d.districtID == district}">${d.districtName}</c:if>
            </c:forEach> 
            <c:if test="${district != null}"> > </c:if>
            <c:forEach var="w" items="${listWard}" >
                <c:if test="${w.wardID == ward}">${w.wardName}</c:if>
            </c:forEach>
        </h4>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">  
            <c:if test="${listP != []}">
                <c:forEach var="p" items="${listP}" >
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <!--                                <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>-->
                            <!-- Product image-->
                            <img class="card-img-top" src="${pageContext.request.contextPath}/images/${p.pitchID}.jpg" alt="..." height="230px" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${p.pitchName}</h5>
                                    <!-- Product price-->
                                    <c:forEach var="lminp" items="${listMinP}" >
                                        <c:if test="${lminp.pitchID == p.pitchID}"><span class="text-muted"><fmt:formatNumber value="${lminp.price}" pattern="#,##0VNĐ - " /></span></c:if>
                                    </c:forEach>
                                    <c:forEach var="lmaxp" items="${listMaxP}" >
                                        <c:if test="${lmaxp.pitchID == p.pitchID}"><span class="text-muted"><fmt:formatNumber value="${lmaxp.price}" pattern="#,##0VNĐ" /></span></c:if>
                                    </c:forEach>
                                    <p>Giá</p>
                                    <p>
                                        ${p.pitchAddress},
                                        <c:forEach var="w" items="${listWard}" >
                                            <c:if test="${w.wardID == p.wardID}">${w.wardName}</c:if>
                                        </c:forEach>
                                        ,
                                        <c:forEach var="d" items="${listD}" >
                                            <c:if test="${d.districtID == p.districtID}">${d.districtName}</c:if>
                                        </c:forEach>
                                    </p>
                                    <div class="d-flex justify-content-center small mb-2">
                                        <c:forEach begin="1" step="1" end="${p.estimation}">
                                            <div class="bi-star-fill text-warning"></div>
                                        </c:forEach>
                                        <c:forEach begin="1" step="1" end="${5 - p.estimation}">
                                            <div class="bi-star-fill"></div>
                                        </c:forEach>
                                    </div>

                                </div>
                            </div>
                            <!-- Product actions-->
                            <c:if test="${user.roleID == 'US'}">
                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                    <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="${pageContext.request.contextPath}/stadium/detail.do?pitchID=${p.pitchID}">Đặt sân</a></div>
                                </div>
                            </c:if>
                            <c:if test="${user.roleID == 'AD' || user.roleID == 'OW'}">
                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                    <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="${pageContext.request.contextPath}/stadium/detail.do?pitchID=${p.pitchID}">Xem sân</a></div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach> 
            </c:if>
            <c:if test="${listP == []}">
                <p class="text-center fst-italic fw-lighter h4 mt-5">Không có dữ liệu</p>
            </c:if>
        </div>

    </div>
</section>
</div>

<c:if test="${listW == null}">
    <div class="row">
        <div class="col" style="text-align: right;">
            <br/>
            <form action="${pageContext.request.contextPath}/home/index.do" />
            <button type="submit" class="btn btn-sm btn-info" name="op" value="FirstPage" title="First Page" <c:if test="${page==1}">disabled</c:if>><i class="bi bi-chevron-bar-left"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="PreviousPage" title="Previous Page" <c:if test="${page==1}">disabled</c:if>><i class="bi bi-chevron-left"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="NextPage" title="Next Page" <c:if test="${page==totalPage}">disabled</c:if>><i class="bi bi-chevron-right"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="LastPage" title="Last Page" <c:if test="${page==totalPage}">disabled</c:if>><i class="bi bi-chevron-bar-right"></i></button>
            <input type="number" min="1" name="gotoPage" value="${page}" class="btn btn-sm btn-outline-default" style="text-align: right;width: 50px;" title="Enter page number"/>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="GotoPage" title="Goto Page"><i class="bi bi-arrow-up-right-circle"></i></button>
            </form>
            Trang: ${page}/${totalPage}
        </div>
    </div>
</c:if>
<c:if test="${listW != null}">
    <div class="row">
        <div class="col" style="text-align: right;">
            <br/>
            <form action="${pageContext.request.contextPath}/home/search.do" />
            <input type="hidden" name="districtID" value="${district}" />
            <input type="hidden" name="ward" value="${ward}" />
            <button type="submit" class="btn btn-sm btn-info" name="op" value="FirstPage" title="First Page" <c:if test="${page==1}">disabled</c:if>><i class="bi bi-chevron-bar-left"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="PreviousPage" title="Previous Page" <c:if test="${page==1}">disabled</c:if>><i class="bi bi-chevron-left"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="NextPage" title="Next Page" <c:if test="${page==totalPage}">disabled</c:if>><i class="bi bi-chevron-right"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="LastPage" title="Last Page" <c:if test="${page==totalPage}">disabled</c:if>><i class="bi bi-chevron-bar-right"></i></button>
            <input type="number" min="1" name="gotoPage" value="${page}" class="btn btn-sm btn-outline-default" style="text-align: right;width: 50px;" title="Enter page number"/>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="GotoPage" title="Goto Page"><i class="bi bi-arrow-up-right-circle"></i></button>
            </form>
            Trang: ${page}/${totalPage}
        </div>
    </div>
</c:if>


<script>
    function setWard() {
        var selectBox = document.getElementById("selectBox");
        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
        $.ajax({
            url: "${pageContext.request.contextPath}/home/ward.do",
            type: 'post',
            data: {
                districtID: selectedValue
            },
            success: function (responseData) {
                document.getElementById("ward").innerHTML
                        = responseData;
//                document.getElementById("districtID").innerHTML
//                        = "<input type=\"hidden\" name=\"districtID\" value=\"" + selectedValue + "\"/>";
            }
        });
    }
</script>