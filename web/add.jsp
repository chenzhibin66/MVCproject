<%--
  Created by IntelliJ IDEA.
  User: Binean
  Date: 2018/11/17
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        tr {
            height: 40px;
        }

        td {
            padding: 10px;
        }
    </style>
</head>
<body>
<form action="<%=request.getContextPath()%>/add.udo" method="post">
    <table style="margin-left: 100px" border="1" cellpadding="1" cellspacing="1">
        <tr>
            <th style="text-align: right">用户名：</th>
            <td style="text-align: left"><input type="text" name="username"></td>
        </tr>
        <tr>
            <th style="text-align: right">用户密码：</th>
            <td style="text-align: left"><input type="text" name="pasword"></td>
        </tr>
        <tr>
            <th style="text-align: right">用户地址：</th>
            <td style="text-align: left"><input type="text" name="address"></td>
        </tr>
        <tr>
            <th style="text-align: right">用户电话：</th>
            <td style="text-align: left"><input type="text" name="phoneNo"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit" value="注册用户"/></td>
        </tr>
    </table>
</form>
</body>
</html>
