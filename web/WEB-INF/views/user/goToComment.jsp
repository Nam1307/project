<%-- 
    Document   : comment
    Created on : Jun 3, 2022, 10:31:29 AM
    Author     : SE150853 Nguyen Huynh Minh Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    #test{
        display: grid;
        place-items: center;
        font-family: 'Manrope', sans-serif;
    }

    .card {
        margin-top: 100px;
        margin-bottom: 100px;
        position: relative;
        display: flex;
        flex-direction: column;
        min-width: 0;
        padding: 20px;
        width: 550px;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border-radius: 6px;
        -moz-box-shadow: 0px 0px 5px 0px rgba(212, 182, 212, 1)
    }

    .comment-box{

        padding:5px;
    }



    .comment-area textarea{
        resize: none; 
        border: 1px solid #ad9f9f;
    }


    .form-control:focus {
        color: #495057;
        background-color: #fff;
        border-color: #ffffff;
        outline: 0;
        box-shadow: 0 0 0 1px rgb(255, 0, 0) !important;
    }

    .send {
        color: #fff;
        background-color: #ff0000;
        border-color: #ff0000;
    }

    .send:hover {
        color: #fff;
        background-color: #f50202;
        border-color: #f50202;
    }


    .rating {
        display: flex;
        margin-top: -10px;
        flex-direction: row-reverse;
        margin-left: -4px;
        float: left;
    }

    .rating>input {
        display: none
    }

    .rating>label {
        position: relative;
        width: 19px;
        font-size: 25px;
        color: #ff0000;
        cursor: pointer;
    }

    .rating>label::before {
        content: "\2605";
        position: absolute;
        opacity: 0
    }

    .rating>label:hover:before,
    .rating>label:hover~label:before {
        opacity: 1 !important
    }

    .rating>input:checked~label:before {
        opacity: 1
    }

    .rating:hover>input:checked~label:before {
        opacity: 0.4
    }
</style>
<form action="${pageContext.request.contextPath}/user/comment.do" method="post" class="row g-3 needs-validation" novalidate>
    <div id="test">
        <div class="card">

            <div class="row">

                <div class="col-2">


                    <img src="/WebsiteOrderStadium/images/user.jpg" width="70" class="rounded-circle mt-2">


                </div>

                <div class="col-10">

                    <div class="comment-box ml-2">

                        <h4>Thêm bình luận</h4>

                        <div class="rating"> 
                            <input type="radio" name="rating" value="5" id="5" required><label for="5">☆</label>
                            <input type="radio" name="rating" value="4" id="4" required><label for="4">☆</label> 
                            <input type="radio" name="rating" value="3" id="3" required><label for="3">☆</label>
                            <input type="radio" name="rating" value="2" id="2" required><label for="2">☆</label>
                            <input type="radio" name="rating" value="1" id="1" required><label for="1">☆</label>
                            <div class="invalid-feedback mt-2">Vui lòng chọn sao đánh giá: </div>
                        </div>
                        <input type="hidden" value="${userID}" name="userID"/>
                        <input type="hidden" value="${pitchID}" name="pitchID"/>
                        <div class="comment-area">

                            <textarea class="form-control" placeholder="Bình luận của bạn" rows="4" name="content" maxlength="200" required></textarea>
                            <div class="invalid-feedback">
                                Vui lòng điền bình luận.
                            </div>
                        </div>

                        <div class="comment-btns mt-2">

                            <div class="row">

                                <div class="col-6">

                                </div>

                                <div class="col-6">

                                    <div class="pull-right">

                                        <button class="btn btn-success send btn-sm">Gửi <i class="fa fa-long-arrow-right ml-1"></i></button>      

                                    </div>

                                </div>

                            </div>

                        </div>


                    </div>

                </div>


            </div>

        </div>
    </div>
</form>
<script>
    (function () {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation')

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }

                        form.classList.add('was-validated')
                    }, false)
                })
    })()
</script>
