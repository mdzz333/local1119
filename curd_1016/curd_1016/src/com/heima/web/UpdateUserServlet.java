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

@WebServlet( urlPatterns = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
      /*
        //根据用户id 更新用户信息
        //1.获取用户数据   封装到User对象中
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String qq = request.getParameter("qq");
        String age = request.getParameter("age").equals("")?"18":request.getParameter("age");

        //2.封装数据
        User user = new User();
        user.setId(Integer.valueOf(id));
        user.setName(name);
        user.setSex(sex);
        user.setAddress(address);
        user.setEmail(email);
        user.setQq(qq);
        user.setAge(Integer.valueOf(age));
        */
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        /**
         *  参数：1 数据将要封装到的对象
         *  参数：2 map集合，数据的来源
         *
         *  作用：将map集合中的所有数据封装到指定的对象属性中去。
         *
         *
         *  原理：
         *      User
         *          name
         *          sex
         *
         *      map   key  value
         *            name  zhangsan
         *            sex   male
         *            age    18
         *      结果：
         *        beanUtils.populate(user,map);
         *        user.getName() = zhangsan
         *        user.getSex() = male
         *
         *
         *        封装数据的依据：map.key = bean.field
         *        根据map的key找到对象中与之名字一模一样的属性（field）
         *        通过字符串找到属性对象，然后调用属性的set方法设置值：反射机制！！！
         *
         */
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        IUserService userService = new UserServiceImpl();
        //ctrl + alt + v 本地参数和 alt + Enter 效果一样
        List<User> userList = userService.updateUser(user);
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("/list1.jsp").forward(request,response);
    }
}
