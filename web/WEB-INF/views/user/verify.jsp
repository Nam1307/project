<%-- 
    Document   : verify
    Created on : May 26, 2022, 11:04:41 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    .height-100{height:50vh}
    .card{width:400px;border:none;height:300px;box-shadow: 0px 5px 20px 0px #d2dae3;z-index:1;display:flex;justify-content:center;align-items:center}
    .card h6{color:red;font-size:20px}
    .inputs input{width:40px;height:40px}
    input[type=number]::-webkit-inner-spin-button,
    input[type=number]::-webkit-outer-spin-button{-webkit-appearance: none;-moz-appearance: none;appearance: none;margin: 0}
    .card-2{background-color:#fff;padding:10px;width:350px;height:100px;bottom:-50px;left:20px;position:absolute;border-radius:5px}
    .card-2 .content{margin-top:50px}
    .card-2 .content a{color:red}
    .form-control:focus{box-shadow:none;border:2px solid red}
    .validate{border-radius:20px;height:40px;background-color:red;border:1px solid red;width:140px}
</style>
<form action="${pageContext.request.contextPath}/user/verify.do" method="post">
    <div class="container height-100 d-flex justify-content-center align-items-center"> 
        <div class="position-relative"> 
            <div class="card p-2 text-center"> 
                <h6>Please enter the one time password <br> to verify your account</h6> 
                <div> 
                    <span>A code has been sent to</span> <small>${email}</small> 
                </div> 
                <input type="hidden" value="${username}" name="username" />
                <input type="hidden" value="${email}" name="email" />
                <input type="hidden" value="${password}" name="password" />
                <input type="hidden" value="${fullname}" name="fullname" />
                <input type="hidden" value="${phone}" name="phone" />
                <input type="hidden" value="${districtID}" name="districtID" />
                <input type="hidden" value="${ward}" name="ward" />
                <input type="hidden" value="${address}" name="address" />
                <div id="otp" class="inputs d-flex flex-row justify-content-center mt-2"> 
                    <input class="m-2 text-center form-control rounded" name="num1" type="text" id="first" maxlength="1" /> 
                    <input class="m-2 text-center form-control rounded" name="num2" type="text" id="second" maxlength="1" /> 
                    <input class="m-2 text-center form-control rounded" name="num3" type="text" id="third" maxlength="1" /> 
                    <input class="m-2 text-center form-control rounded" name="num4" type="text" id="fourth" maxlength="1" /> 
                    <input class="m-2 text-center form-control rounded" name="num5" type="text" id="fifth" maxlength="1" /> 
                    <input class="m-2 text-center form-control rounded" name="num6" type="text" id="sixth" maxlength="1" /> 
                </div> 
                <div class="mt-4"> 
                    <button class="btn btn-danger px-4 validate">Validate</button> 
                </div> 
            </div> 
        </div>
    </div>
</form>
<script>
    document.addEventListener("DOMContentLoaded", function (event) {

        function OTPInput() {
            const inputs = document.querySelectorAll('#otp > *[id]');
            for (let i = 0; i < inputs.length; i++) {
                inputs[i].addEventListener('keydown', function (event) {
                    if (event.key === "Backspace") {
                        inputs[i].value = '';
                        if (i !== 0)
                            inputs[i - 1].focus();
                    } else {
                        if (i === inputs.length - 1 && inputs[i].value !== '') {
                            return true;
                        } else if (event.keyCode > 47 && event.keyCode < 58) {
                            inputs[i].value = event.key;
                            if (i !== inputs.length - 1)
                                inputs[i + 1].focus();
                            event.preventDefault();
                        } else if (event.keyCode > 64 && event.keyCode < 91) {
                            inputs[i].value = String.fromCharCode(event.keyCode);
                            if (i !== inputs.length - 1)
                                inputs[i + 1].focus();
                            event.preventDefault();
                        }
                    }
                });
            }
        }
        OTPInput();
    });
</script>
