<%@ page import="java.util.List" %>
<%@ page import="cn.binean.mvcproject.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Binean
  Date: 2018/11/11
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
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
<form action="<%=request.getContextPath()%>/query.udo" method="post">
    <table style="margin-left: 100px" border="1" cellpadding="1" cellspacing="1">
        <tr>
            <th style="text-align: right">用户名：</th>
            <td style="text-align: left"><input type="text" name="username"></td>
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
            <td colspan="2" style="text-align: center"><input type="submit" value="查询用户"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><a href="<%=request.getContextPath()%>/add.jsp">注册新用户</a> </td>
        </tr>
    </table>
</form>

<table style="margin-left: 100px" padding="50px" border="1" cellpadding="0" cellspacing="0">
    <tr>
        <td>用户id</td>
        <td>用户名称</td>
        <td>用户密码</td>
        <td>用户电话</td>
        <td>用户地址</td>
        <td>注册日期</td>
        <td>操作记录</td>
    </tr>
    <%
        List<User> list = (List<User>) request.getAttribute("userList");
        if (list != null && list.size() > 0) {
            for (User user:list) {

    %>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getUsername()%></td>
        <td><%=user.getPasword()%></td>
        <td><%=user.getPhoneNo()%></td>
        <td><%=user.getAddress()%></td>
        <td><%=user.getRegDate()%></td>
        <td><a href="<%=request.getContextPath()%>/update.udo?id=<%=user.getId()%>">编辑</a> | <a href="<%=request.getContextPath()%>/delete.udo?id=<%=user.getId()%>">删除</a></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
