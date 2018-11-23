<%--
  Created by IntelliJ IDEA.
  User: Binean
  Date: 2018/11/23
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" session="false" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    //获取客户端发来的cookie
    Cookie[] cookieArr = request.getCookies();
    if (cookieArr != null && cookieArr.length > 0) {
        for (int i = 0; i < cookieArr.length; i++) {
            out.println(cookieArr[i].getName());
            out.println(cookieArr[i].getValue());
        }
    }else
    {
        ///新建了一个Cookie
        Cookie cookie = new Cookie("uuid", "uuidvalue");
        cookie.setMaxAge(1*24*60*60);
        response.addCookie(cookie);
    }
%>
</body>
</html>
