<%-- 
    Document   : index
    Created on : May 12, 2022, 11:11:51 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<div class="row">
    <div class="col-3 px-4 px-lg-5 mt-5 py-5 ">
        <div class="mb-3">
            <label for="district" class="form-label">Quận:</label>
            <div class="box">
                <select id="selectBox" name="district" onchange="ward()" class="form-control">
                    <option selected="" disabled="">Quận</option>
                    <c:forEach var="d" items="${listD}" >
                        <option value="${d.districtID}">${d.districtName}</option>
                    </c:forEach>
                </select>
            </div>
            <form action="${pageContext.request.contextPath}/home/search.do" method="post">
                <label for="ward" class="form-label">Phường:</label>
                <div id="test"></div>
                <div class="box">
                    <select id="ward" name="ward" class="form-control">
                        <option selected="" disabled="">Phường</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-sm btn-outline-success"><i class="bi bi-check-circle"></i> Search</button> 
            </form>
        </div>
        <div class="list-group">
            <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
                The current link item
            </a>
            <a href="#" class="list-group-item list-group-item-action list-group-item-light">A second link item</a>
            <a href="#" class="list-group-item list-group-item-action list-group-item-light">A third link item</a>
            <a href="#" class="list-group-item list-group-item-action list-group-item-light">A fourth link item</a>
            <a href="#" class="list-group-item list-group-item-action list-group-item-light" tabindex="-1" aria-disabled="true">A disabled link item</a>
        </div>
    </div>
    <div class="col-9">
        <section class="py-5">
            <div class=" px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="https://www.sgtiepthi.vn/wp-content/uploads/2016/06/DSC06119.jpg" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">Name of stadium</h5>
                                    <!-- Product price-->
                                    <!--                                    <span class="text-muted text-decoration-line-through">$50.00</span>-->
                                    <p>Price</p>
                                    <p>Location</p>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="${pageContext.request.contextPath}/stadium/detail.do">View Detail</a></div>
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
                                    <h5 class="fw-bolder">Name of stadium</h5>
                                    <!-- Product price-->
                                    <!--                                    <span class="text-muted text-decoration-line-through">$50.00</span>-->
                                    <p>Price</p>
                                    <p>Location</p>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View Detail</a></div>
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
                                    <h5 class="fw-bolder">Name of stadium</h5>
                                    <!-- Product price-->
                                    <!--                                    <span class="text-muted text-decoration-line-through">$50.00</span>-->
                                    <p>Price</p>
                                    <p>Location</p>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View Detail</a></div>
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
                                    <h5 class="fw-bolder">Name of stadium</h5>
                                    <!-- Product price-->
                                    <!--                                    <span class="text-muted text-decoration-line-through">$50.00</span>-->
                                    <p>Price</p>
                                    <p>Location</p>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View Detail</a></div>
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
                                    <h5 class="fw-bolder">Name of stadium</h5>
                                    <!-- Product price-->
                                    <!--                                    <span class="text-muted text-decoration-line-through">$50.00</span>-->
                                    <p>Price</p>
                                    <p>Location</p>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View Detail</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<script>
    function ward() {
        var selectBox = document.getElementById("selectBox");
        var selectedValue = selectBox.options[selectBox.selectedIndex].value;
        $.ajax({
            url: "${pageContext.request.contextPath}/home/ward.do",
            type: 'get',
            data: {
                districtID: selectedValue
            },
            success: function (responseData) {
                document.getElementById("ward").innerHTML
                        = responseData;
                document.getElementById("test").innerHTML
                        = "<input type=\"hidden\" name=\"districtID\" value=\"" + selectedValue + "\"/>";
            }
        });
    }
</script>