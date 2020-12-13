<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/4
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            $("#but").click(function () {
                $.ajax({
                    url:"user/testAjax",
                    contentType:"application/json;charset=utf-8",
                    data:'{"name":"我带","address":"安徽","age":"18"}',
                    type:"post",
                    dataType:"json",
                    success:function (date) {
                        alert(date.name);
                        alert(date.address);
                        alert(date.age);
                    }
                })
            });
        });


    </script>
</head>
<body>
<a href="user/testString">testString</a><br>

<a href="user/testVoid">testVoid</a><br>

<a href="user/testModeAndView">testModeAndView</a><br>

<a href="user/testForwardOrSendRedirect">testForwardOrSendRedirect</a><br>
<input id="but" type="button" value="发送Ajax请求"></input><br>
</body>
</html>
