<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/3
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--请求参数绑定-->
<%--<a href="param/test?username=我带你们打">请求参数绑定</a>--%>
<%--<form action="param/saveAccount" method="post">
    用户名<input name="username" type="text"/><br/>
    密码<input name="password" type="text"/><br/>
    籍贯<input name="address" type="text"/><br/>
    姓名<input name="user.name" type="text"/><br/>
    性别<input name="user.sex" type="text"/><br/>

    姓名<input name="users[0].name" type="text"/><br/>
    性别<input name="users[0].sex" type="text"/><br/>

    姓名<input name="map['zz'].name" type="text"/><br/>
    性别<input name="map['zz'].sex" type="text"/><br/>
    <input value="提交" type="submit"/><br/>
</form>--%>
<form action="param/saveUser" method="post">
    用户名<input name="name" type="text"/><br/>
    密码<input name="sex" type="text"/><br/>
    生日<input name="birthday" type="date"/><br/>
    <input value="提交" type="submit"/><br/>
</form>

</body>
</html>
