package cn.binean.mvcproject.controller;

import cn.binean.mvcproject.model.User;
import cn.binean.mvcproject.service.FactoryService;
import cn.binean.mvcproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class UesrController extends HttpServlet {

    private static final long serialVersionUID = 1;
    UserService userService = FactoryService.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //在这一个方法里边能处理增删改查的功能

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String mn = req.getServletPath();
        mn = mn.substring(1);
        mn = mn.substring(0, mn.length() - 4);
        try {
            Method method = this.getClass().getDeclaredMethod(mn, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    //实现首页的模糊查询
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

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int rows = userService.deleteUserById(id);
        if (rows > 0) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

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
}
