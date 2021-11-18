package com.heima.web;

//ctrl+ alt+ o 清除不用的import

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

@WebServlet( urlPatterns = "/queryList")
public class FindAllUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求中的参数
        //2.调用service处理请求
        IUserService userService = new UserServiceImpl();
        List<User> userList = userService.findAllUsers();
        //3.转发到list.jsp页面
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("list1.jsp").forward(request,response);
    }


}