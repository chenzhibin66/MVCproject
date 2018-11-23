<%@ page import="cn.binean.mvcproject.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Binean
  Date: 2018/11/17
  Time: 19:45
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
<% User user = (User) request.getAttribute("user");%>
<form action="<%=request.getContextPath()%>/updatedo.udo" method="post">
    <input type="hidden" name="id" value="<%=user.getId()%>">
    <table style="margin-left: 100px" border="1" cellpadding="1" cellspacing="1">
        <%
            String note = (String) request.getAttribute("note");
            if (note != null && !"".equals(note)) {
        %>
        <tr>
            <td colspan="2" style="text-align: right" bgcolor="red"><%=note%>
            </td>
        </tr>
        <% }%>
        <tr>
            <th style="text-align: right">用户名：</th>
            <td style="text-align: left"><input type="text" name="username" value="<%=user.getUsername()%>"></td>
        </tr>
        <tr>
            <th style="text-align: right">用户密码：</th>
            <td style="text-align: left"><input type="text" name="pasword" value="<%=user.getPasword()%>"></td>
        </tr>
        <tr>
            <th style="text-align: right">用户地址：</th>
            <td style="text-align: left"><input type="text" name="address" value="<%=user.getAddress()%>"></td>
        </tr>
        <tr>
            <th style="text-align: right">用户电话：</th>
            <td style="text-align: left"><input type="text" name="phoneNo" value="<%=user.getPhoneNo()%>"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit" value="修改用户"/></td>
        </tr>
    </table>
</form>
</body>
</html>
