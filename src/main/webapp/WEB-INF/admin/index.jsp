<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>后天管理首页</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap-3.3.0/css/bootstrap.min.css">
</head>
<body>
<a class="h3" href="/admin/other.do">其他</a>
<br/>
<shiro:user>
    欢迎[<shiro:principal/>]登录，<a href="${pageContext.request.contextPath}/admin/logout.do">退出</a>
</shiro:user>
<br/>
<shiro:authenticated>
    用户[<shiro:principal/>]已身份验证通过
</shiro:authenticated>
<br/>
<shiro:hasRole name="role1">
    用户[<shiro:principal/>]拥有角色role1<br/>
</shiro:hasRole>
<br/>
<shiro:hasRole name="role2">
    用户[<shiro:principal/>]拥有角色role2<br/>
</shiro:hasRole>
<br/>
<shiro:hasPermission name="user1:create">
    用户[<shiro:principal/>]拥有权限user1:create<br/>
</shiro:hasPermission>
<br/>
<shiro:hasPermission name="user2:create">
    用户[<shiro:principal/>]拥有权限user2:create<br/>
</shiro:hasPermission>
<br/>
<shiro:hasPermission name="user2:view">
    用户[<shiro:principal/>]拥有权限user2:view<br/>
</shiro:hasPermission>
</body>
</html>