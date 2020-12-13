<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/4
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>文件上传</h2>
    <form action="user/testFileUpload" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload"/><br>
                <input type="submit" value="上传"/>
    </form>
<form action="user/testFileUpload2" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br>
    <input type="submit" value="上传"/>
</form>
<h3>跨服务器文件上传</h3><br>
<form action="user/testFileUpload3" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
