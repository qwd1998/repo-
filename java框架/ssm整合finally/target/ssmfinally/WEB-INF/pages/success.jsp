<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/5
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<h3 align="center">查询所有account信息</h3>
<c:forEach items="${requestScope.accounts}" var="account" varStatus="s">
    ${s}+${account}
</c:forEach>
${requestScope.accounts}
<hr>
<h3 align="center">查询一个role信息</h3>
${requestScope.role}
<hr>
<h3 align="center">查询所有role和user信息</h3>
<%--${requestScope.roles}--%>
<c:forEach items="${requestScope.roles}" var="role" varStatus="s" begin="1">
    ${role}+${role.users}+${s.index}<br>
</c:forEach>
<hr>
<h3 align="center">查询一个user信息</h3>
${requestScope.user}
<hr>
<h3 align="center">查询所有user和role信息</h3>
${requestScope.users}
<hr>

</body>
</html>
