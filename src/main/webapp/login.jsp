<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登陆</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap-3.3.0/css/bootstrap.min.css">
</head>
<body>
<div>
    <form id="saveform" method="post" action="login.do" class="form-horizontal" role="form">
        <div class="form-group" style="margin-top:5px">
            <label for="title" class="col-sm-2 control-label text-left">用户名:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="title" name="title" placeholder="用户名" value="">
            </div>
        </div>
        <div class="form-group" style="margin-top:5px">
            <label for="password" class="col-sm-2 control-label text-left">密码:</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="password" name="password"  value="">
            </div>
        </div>
        <br/>
        <div class="text-center">
            <input type="submit" class="form-control bt"  value="提交">
        </div>
    </form>
</div>
</body>
</html>