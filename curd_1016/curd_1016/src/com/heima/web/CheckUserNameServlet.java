package com.heima.web;

import com.heima.pojo.User;
import com.heima.service.IUserService;
import com.heima.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/checkUserName")
public class CheckUserNameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取需要校验的用户名
        String name = request.getParameter("username");
        //2.调用service
        IUserService userService = new UserServiceImpl();
        String json = userService.checkUserByName(name);
        //3.给出响应
        response.getWriter().write(json);
    }


}
