<#import "parts/UnLoginPart.ftl" as p>
<@p.page "registration">
    <div class="container col-5">
        <form action="/forum/registerProcess" modelAtribute="user" method="post">
            <div class="form-group">
                <input autocomplete="off" autofocus class="form-control" id="login" path="login" name="login"
                       placeholder="login"
                       type="text"/>
            </div>
            <div class="form-group">
                <input autocomplete="off" autofocus class="form-control" id="name" path="name" name="name"
                       placeholder="First name"
                       type="text"/>
            </div>
            <div class="form-group">
                <input autocomplete="off" autofocus class="form-control" id="surname" path="surname" name="surname"
                       placeholder="Second name"
                       type="text"/>
            </div>
            <div class="form-group">
                <input class="form-control" id="password" path="passwd" name="passwd" placeholder="Password"
                       type="password"/>
            </div>
            <div class="form-group">
                <input class="form-control" id="conform" name="confirmation" placeholder="Password again" type="password"/>
            </div>
            <button class="btn btn-primary" id="register" type="submit">Register</button>

            <p><span id="error"></span></p>
        </form>
    </div>
    <script>
        $(document).ready(function () {
            let span = $('#error');
            let btn = $('#register');

            $('#login').keyup(function () {
                $.ajax({
                    url: '/forum/checkLogin',
                    method: 'GET',
                    data: 'login='+$('#login').val(),
                    success: function (data) {
                        if (data==='free') {
                            span.empty();
                            btn.removeAttr("disabled");
                        }
                        else{
                            span.text("user with this login was registered");
                            btn.attr('disabled', 'disabled');
                        }
                    }
                });

            });

            $('#password').keyup(function () {
                var pass = $('#password').val();
                var conform = $('#conform').val();
                btn.removeAttr("disabled");
                span.empty();
                if (!pass.match(/[A-Z]/)) {
                    span.text("password must contain at least one capital letter");
                    btn.attr('disabled', 'disabled');
                }
                else if (!pass.match(/[a-z]/)) {
                    span.text("password must contain at least one letter");
                    btn.attr('disabled', 'disabled');
                }
                else if (!pass.match(/[0-9]/)) {
                    span.text("password must contain at least one digit");
                    btn.attr('disabled', 'disabled');
                }
                else if (!pass.match(/[!@#$%)]/g)) {
                    span.text("password must contain at least one simbol(!@#$%)");
                    btn.attr('disabled', 'disabled');
                }
                else if (pass.length < 8 ) {
                    span.text("password lenght must be > 8");
                    btn.attr('disabled', 'disabled');
                }
                else {
                    if (conform === pass) {
                        btn.removeAttr("disabled");
                        span.empty();
                    }
                    else {
                        span.text("password and conform are different");
                        btn.attr('disabled', 'disabled');
                    }
                }

            });

            $('#conform').keyup(function () {
                var conform = $('#conform').val();
                var pass = $('#password').val();
                if (conform === pass) {
                    btn.removeAttr("disabled");
                    span.empty();
                }
                else {
                    span.text("password and conform are different");
                    btn.attr('disabled', 'disabled');
                }
            })
        });
    </script>
</@p.page>