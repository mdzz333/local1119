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

@WebServlet(urlPatterns = "/deleteUserById")
public class DeleteUserByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // int id = Integer.parseInt(request.getParameter("id"));
        Integer id = Integer.valueOf(request.getParameter("id"));
        IUserService userService = new UserServiceImpl();
        //ctrl+q 查看注释
        List<User> userList = userService.deleteUserById(id);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("list1.jsp").forward(request, response);
    }


}
