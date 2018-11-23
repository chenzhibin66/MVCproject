**MVC开发模式设计的简单对数据库进行CRUD操作**

![](http://ww1.sinaimg.cn/large/005WjvZYly1fxia1tnpl0j30oz0c00sz.jpg)

MVC开发主要分为三层结构：dao层，service层和Controll层![](http://ww1.sinaimg.cn/large/005WjvZYly1fxia2p3oc6j30ru0g40ti.jpg)

简单模糊查询：

```
private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String address = req.getParameter("address");
    String phoneNo = req.getParameter("phoneNo");
    username = username.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]", "");
    address = address.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]", "");
    phoneNo = phoneNo.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]", "");
    List<User> list = userService.query(username, address, phoneNo);
    req.setAttribute("userList", list);  //把结果集放到了req的属性空间
    req.getRequestDispatcher("/index.jsp").forward(req, resp); //把整个req，reqs注入到jsp页面
}

```

![](http://ww1.sinaimg.cn/large/005WjvZYly1fxia3in0l4j30dl09tt8r.jpg)

注册新用户：

```
private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    User user = new User();
    user.setUsername(req.getParameter("username"));
    user.setPasword(req.getParameter("pasword"));
    user.setAddress(req.getParameter("address"));
    user.setPhoneNo(req.getParameter("phoneNo"));
    user.setRegDate(new Date());
    int rows = userService.save(user);
    if (rows > 0) {
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    } else {
        resp.sendRedirect(req.getContextPath() + "/error.jsp");
    }
}
```

![](http://ww1.sinaimg.cn/large/005WjvZYly1fxia3rbsccj30fq09cglr.jpg)

修改用户信息：

```
private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));
    User user = userService.get(id);
    req.setAttribute("user", user);
    req.getRequestDispatcher("/update.jsp").forward(req, resp);
}

private void updatedo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id = Integer.parseInt(req.getParameter("id"));
    //通过id把数据库中原来的user信息拿到
    User user = userService.get(id);
    String yUsername = user.getUsername();
    String xUsername = req.getParameter("username");
    long cout = userService.getCountByName(xUsername);
    if (!xUsername.equals(yUsername) && cout > 0) {   //首先新名字和老名字不一样，还能在数据库中找到同名记录,证明名字是有冲突的
        req.setAttribute("note", xUsername + ",这个名字已被占用，请换一个名字！");
        req.getRequestDispatcher("/update.udo?id=" + id).forward(req, resp);
        return;
    }
    user.setUsername(xUsername);
    user.setPasword(req.getParameter("pasword"));
    user.setAddress(req.getParameter("address"));
    user.setPhoneNo(req.getParameter("phoneNo"));
    int rows = userService.updateUserById(user);
    if (rows > 0) {
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    } else {
        resp.sendRedirect(req.getContextPath() + "/error.jsp");
    }
}
```

![](http://ww1.sinaimg.cn/large/005WjvZYly1fxia3z2pucj30f40b5aaa.jpg)