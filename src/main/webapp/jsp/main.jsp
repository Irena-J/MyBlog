<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/8
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerName();
%>

<html>
<head>
    <title>Title</title>
    <base href="<%=basePath%>">
    <link href="static/css/app.css" rel="stylesheet">

</head>
<body>
    <form action="login">
        <input type=text" name="username" placeholder="请输入账号">
        <input type="password" name="password" placeholder="请输入密码">
        <input type="submit" value="提交">
    </form>
</body>
</html>
