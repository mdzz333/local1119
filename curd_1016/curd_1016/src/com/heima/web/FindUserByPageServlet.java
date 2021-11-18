package com.heima.web;

import com.heima.pojo.PageBean;
import com.heima.service.IUserService;
import com.heima.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/findUserByPage")
public class FindUserByPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;
        int pageSize = 5;
        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr!=null&&!"".equals(currentPageStr)){
            currentPage = Integer.parseInt(currentPageStr);
        }
        String pageSizeStr = request.getParameter("pageSize");
        if (pageSizeStr!=null&&!"".equals(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        IUserService service = new UserServiceImpl();
        PageBean pageBean = service.findUserByPage(currentPage, pageSize);
        request.setAttribute("pageBean",pageBean);
        //request.getContextPath()：是在开发Web项目时，经常用到的方法，是为了解决相对路径的问题，可返回站点的根路径。
        //比如：要生成一个文件放在服务器上得一个目录下,可以使用request.getContextPath()+/dir,组成一个完整得目录结构！
        request.getRequestDispatcher(request.getContextPath()+"list2.jsp").forward(request,response);
    }


}
