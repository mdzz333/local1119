package com.heima.web;

import com.heima.pojo.Client;
import com.heima.service.IUserService;
import com.heima.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.处理post请求数据乱码
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        IUserService userService = new UserServiceImpl();
        List<Client> clientList = userService.findClient(userName, password);
        //3.给浏览器生成响应信息
        //如果能查到数据
        if (clientList!=null&&clientList.size()>0){
            //跳转到success.html
            request.getRequestDispatcher("/success.jsp").forward(request,response);
        }else{
            //没有查到数据    跳转到error.jsp
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }
    }


}
