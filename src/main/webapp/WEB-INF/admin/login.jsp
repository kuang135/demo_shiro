<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登陆</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/plugin/bootstrap-3.3.0/css/bootstrap.min.css">
    <script src="/static/plugin/jquery/jquery-2.1.4.min.js"></script>
</head>
<body>
    <form method="post" action="/admin/login.do" class="form-horizontal" role="form">
        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">用户名:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="userName" name="userName" placeholder="用户名" value="">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码:</label>
            <div class="col-sm-8">
                <input type="password" class="form-control" id="password" name="password"  value="">
            </div>
        </div>
        <div class="form-group">
            <label for="validateCode" class="col-sm-2 control-label">验证码:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control form-control-inline" id="validateCode" name="validateCode" placeholder="验证码" value="">
                <img id="captchaImage" src="/admin/jcaptcha.jpg" alt="换一张"/>
            </div>
        </div>
        <div class="text-center" style="color:red;">${error}</div>
        <br/>
        <div class="text-center">
            <button class="btn btn-default">登录</button>
        </div>
    </form>
    <script type="text/javascript">
        $(function($) {
            $(window).resize();
            $("#captchaImage").css("cursor", "pointer").click( function() {
                changeValidateCode();
            });
        });

        function changeValidateCode(){
            var captchaImage=$("#captchaImage");
            // 刷新验证码
            var timestamp = (new Date()).valueOf();
            var imageSrc = captchaImage.attr("src");
            if(imageSrc.indexOf("?") >= 0) {
                imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
            }
            imageSrc = imageSrc + "?timestamp=" + timestamp;
            captchaImage.attr("src", imageSrc);
        }
    </script>
</body>
</html>