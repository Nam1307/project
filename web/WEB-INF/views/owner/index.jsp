<%-- 
    Document   : index
    Created on : Jun 22, 2022, 8:22:37 PM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container-fluid mt-5">
    <div class="row">
        <div class="col-md-12">
            <h4>Tổng quan</h4>
        </div>
    </div>
    <div class="row mt-5 d-flex justify-content-center">
        <div class="col-md-3 mb-3">
            <div class="card bg-primary text-white h-100">
                <h5 class="mt-3">Số sân con</h5>
                <div class="card-body py-5 fs-1 d-flex justify-content-center">${allChildrenPitch}</div>
                <a href="${pageContext.request.contextPath}/owner/childrenPitchManagement.do?userID=${user.userID}" style="text-decoration: none; color: white">
                    <div class="card-footer d-flex">
                        Xem chi tiết
                        <span class="ms-auto">
                            <i class="bi bi-chevron-right"></i>
                        </span>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-md-3 mb-3">
            <div class="card bg-warning text-dark h-100">
                <h5 class="mt-3">Số lịch đặt sân</h5>
                <div class="card-body py-5 fs-1 d-flex justify-content-center">${allBooking}</div>
                <a href="${pageContext.request.contextPath}/owner/viewBooking.do?userID=${user.userID}" style="text-decoration: none">
                    <div class="card-footer d-flex text-dark">
                        Xem chi tiết
                        <span class="ms-auto">
                            <i class="bi bi-chevron-right"></i>
                        </span>
                    </div>
                </a>
            </div>
        </div>
        <div class="col-md-3 mb-3">
            <div class="card bg-success text-dark h-100">
                <h5 class="mt-3">Số sân chính</h5>
                <div class="card-body py-5 fs-1 d-flex justify-content-center">${listP.size()}</div>
                <a href="${pageContext.request.contextPath}/owner/pitchManagement.do?userID=${user.userID}" style="text-decoration: none">
                    <div class="card-footer d-flex text-dark">
                        Xem chi tiết
                        <span class="ms-auto">
                            <i class="bi bi-chevron-right"></i>
                        </span>
                    </div>
                </a>
            </div>
        </div>
        <!--                <div class="col-md-3 mb-3">
                            <div class="card bg-danger text-white h-100">
                                <div class="card-body py-5">Danger Card</div>
                                <div class="card-footer d-flex">
                                    View Details
                                    <span class="ms-auto">
                                        <i class="bi bi-chevron-right"></i>
                                    </span>
                                </div>
                            </div>
                        </div>-->
    </div>
    <div class="card chart-container mt-5">
        <canvas id="chart"></canvas>
    </div>
    <h4 class="d-flex justify-content-center mt-3">Thống kê đặt sân</h4>
    <style>
        .chart-container {
            width: 50%;
            height: 50%;
            margin: auto;
        }
    </style>
    <script>
        const ctx = document.getElementById("chart").getContext('2d');
        const myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ["Tháng 6", "Tháng 7", "Tháng 8",
                    "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
                datasets: [{
                        label: 'lượt',
                        backgroundColor: 'rgba(161, 198, 247, 1)',
                        borderColor: 'rgb(47, 128, 237)',
                        data: [${june}, ${july}, ${august}, ${september}, ${october}, ${november}, ${december}],
                    }]
            },
            options: {
                scales: {
                    yAxes: [{
                            ticks: {
                                beginAtZero: true,
                            }
                        }]
                }
            },
        });
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/script.css"></script>

