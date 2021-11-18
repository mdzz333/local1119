package com.heima.web;

import com.heima.pojo.User;
import com.heima.service.IUserService;
import com.heima.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/addUser")
public class AddUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取请求中所有的信息
        Map<String, String[]> map = request.getParameterMap();
        //2.封装到user对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service完成添加功能
        IUserService userService = new UserServiceImpl();
        List<User> userList = userService.addUser(user);
        //4.转发到list.jsp
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/list1.jsp").forward(request, response);
    }
}
