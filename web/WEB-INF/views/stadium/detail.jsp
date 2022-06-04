<%-- 
    Document   : Detail
    Created on : May 12, 2022, 6:59:56 PM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- Product section-->
<section class="py-5">
    <div class="container px-4 px-lg-5 my-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="${pageContext.request.contextPath}/images/${pitch.pitchID}.jpg" alt="..." /></div>
            <div class="col-md-6">
                <div class="small mb-1">
                    ${pitch.pitchAddress},
                    <c:forEach var="w" items="${listW}" >
                        <c:if test="${w.wardID == pitch.wardID}">${w.wardName}</c:if>
                    </c:forEach>
                    ,
                    <c:forEach var="d" items="${listD}" >
                        <c:if test="${d.districtID == pitch.districtID}">${d.districtName}</c:if>
                    </c:forEach>
                </div>
                <h1 class="display-5 fw-bolder">${pitch.pitchName}</h1>
                <c:forEach var="cp" items="${listCP}" >
                    <div class="fs-5 mt-3">
                        <i class="bi bi-arrow-return-right"></i>
                        <span>${cp.childrenPitchName}</span> - 
                        Loại sân: <span>${cp.childrenPitchType}</span> người - 
                        Giá: <span><fmt:formatNumber value="${cp.price}" pattern="#,##0VNĐ" /></span>
                    </div>
                </c:forEach>
                <p class="lead mt-5">${pitch.pitchDescription}</p>
            </div>
            <hr class="mt-5">
        </div>
        <div class="container px-4 px-lg-5 my-5">
            <div class="row gx-4 gx-lg-5 align-items-center">
                ${pitch.pitchLocation}
                <c:if test="${user == null || user.roleID == 'US'}">
                    <div class="mt-5">
                        <form action="${pageContext.request.contextPath}/booking/goToConfirmBooking.do" method="post" class="row">
                            <div class="col-md-6">
                                <input type="hidden" name="pitchID" value="${pitch.pitchID}" />
                                <label class="fs-5 mb-2 lead fw-bold">Chọn sân: </label>
                                <select id="selectBox" name="cpType" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                                    <c:forEach var="cp" items="${listCP}" >
                                        <option value="${cp.childrenPitchID}">${cp.childrenPitchName}</option>
                                    </c:forEach>
                                </select>
                                <div class="d-flex mt-3">
                                    <label class="fs-5 me-1 lead fw-bold">Chọn ngày: </label>
                                    <input class="form-control text-center me-3" id="inputDate" name="dateBooking" type="date" style="max-width: 18rem" />
                                    <button class="btn btn-outline-dark flex-shrink-0" type="button" onclick="findDate()">
                                        <i class="bi bi-search"></i>
                                        Find date
                                    </button>
                                </div>
                            </div>
                            <div class="mt-3 col-md-6" id="time">

                            </div>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
</section>

<c:forEach var="c" items="${listCO}">
    <c:forEach var="u" items="${listU}">
        <c:if test="${c.userID == u.userID}">
            <div class="mt-5  px-4 px-lg-5 my-5">

                <div class="row  d-flex justify-content-center">
                    <div class="col-md-8">
                        <div class="card p-3">

                            <div class="d-flex justify-content-between align-items-center">

                                <div class="user d-flex flex-row align-items-center">

                                    <img src="${u.imgLink}" width="30" class="user-img rounded-circle mr-2">
                                    <span><small class="font-weight-bold text-primary">${u.fullName}</small> <small class="font-weight-bold">${c.content}</small></span>

                                </div>


                                <small><fmt:formatDate value="${c.commentDate}" pattern="dd-MM-yyyy" /></small>

                            </div>


                            <div class="action d-flex justify-content-between mt-2 align-items-center">

                                <div class="icons align-items-center">
                                    <c:forEach begin="1" step="1" end="${c.rating}">
                                        <i class="bi-star-fill text-warning"></i>
                                    </c:forEach>
                                    <c:forEach begin="1" step="1" end="${5 - c.rating}">
                                        <i class="bi-star-fill"></i>
                                    </c:forEach>
                                </div>

                            </div>



                        </div>
                    </div>
                </div>

            </div>
        </c:if>
    </c:forEach>
</c:forEach>

<!-- Related items section-->
<section class="py-5 bg-light">
    <div class="container px-4 px-lg-5 mt-5">
        <h2 class="fw-bolder mb-4">Related products</h2>
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Fancy Product</h5>
                            <!-- Product price-->
                            $40.00 - $80.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Sale badge-->
                    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Special Item</h5>
                            <!-- Product reviews-->
                            <div class="d-flex justify-content-center small text-warning mb-2">
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                            </div>
                            <!-- Product price-->
                            <span class="text-muted text-decoration-line-through">$20.00</span>
                            $18.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Sale badge-->
                    <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Sale Item</h5>
                            <!-- Product price-->
                            <span class="text-muted text-decoration-line-through">$50.00</span>
                            $25.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
            <div class="col mb-5">
                <div class="card h-100">
                    <!-- Product image-->
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                    <!-- Product details-->
                    <div class="card-body p-4">
                        <div class="text-center">
                            <!-- Product name-->
                            <h5 class="fw-bolder">Popular Item</h5>
                            <!-- Product reviews-->
                            <div class="d-flex justify-content-center small text-warning mb-2">
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                                <div class="bi-star-fill"></div>
                            </div>
                            <!-- Product price-->
                            $40.00
                        </div>
                    </div>
                    <!-- Product actions-->
                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    function findDate() {
        var delay = 1000;
        var selectBox = document.getElementById("selectBox");
        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
        var date = document.getElementById("inputDate").value;
        $.ajax({
            url: "${pageContext.request.contextPath}/stadium/findDate.do",
            type: 'post',
            data: {
                childrenPitchID: selectedValue,
                date: date
            },
            beforeSend: function () {
                document.getElementById("time").innerHTML
                        = "<div class=\"d-flex justify-content-center\">\n" +
                        "  <div class=\"spinner-border\" role=\"status\">\n" +
                        "    <span class=\"visually-hidden\">Loading...</span>\n" +
                        "  </div>\n" +
                        "</div>";
            },

            success: function (responseData) {
                setTimeout(function () {
                    document.getElementById("time").innerHTML
                            = responseData;
                }, delay);
            },
        });
    }
    document.getElementById('inputDate').valueAsDate = new Date();
    var today = new Date().toISOString().split('T')[0];
    document.getElementsByName("dateBooking")[0].setAttribute('min', today);
</script>


