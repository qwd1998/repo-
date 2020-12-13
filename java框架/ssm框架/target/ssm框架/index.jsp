<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/5
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h3 align="center">测试</h3>
<a href="account/findAll" >点我啊</a>

<form action="account/findAll" method="post">
    用户名<input name="name" type="text"/><br/>
    金额  <input name="money" type="text"/><br/>
    <input value="提交" type="submit"/><br/>
</form>
</body>
</html>
