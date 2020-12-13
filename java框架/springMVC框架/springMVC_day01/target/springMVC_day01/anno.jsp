<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/3
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>常用注解</h3>
<a href="anno/testRequestParam?name=我的">常用注解</a>

<form action="anno/testRequestBody" method="post">
    用户名<input name="name" type="text"/><br/>
    密码<input name="sex" type="text"/><br/>
    生日<input name="birthday" type="date"/><br/>
    <input value="提交" type="submit"/><br/>
</form>

<a href="anno/testPathVariable/20">PathVariable</a><br/>

<a href="anno/testRequestHeader">RequestHeader</a><br/>

<a href="anno/testCookieValue">CookieValue</a><br/>

<form action="anno/testModelAttribute" method="post">
    用户名<input name="name" type="text"/><br/>
    性别<input name="sex" type="text"/><br/>
    <input value="提交" type="submit"/><br/>
</form>

<%--<a href="anno/testSessionAttributes">SessionAttributes</a><br/>--%>
<form action="anno/testSessionAttributes" method="post">
    用户名<input name="name" type="text"/><br/>
    性别<input name="sex" type="text"/><br/>
    <input value="提交" type="submit"/><br/>
</form>
<a href="anno/getSessionAttributes">getSessionAttributes</a><br/>

<a href="anno/deleteSessionAttributes">deleteSessionAttributes</a><br/>
</body>
</html>
