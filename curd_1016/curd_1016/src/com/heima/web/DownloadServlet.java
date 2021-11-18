package com.heima.web;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
  【文件下载】两个头+两个流（流拷贝）

1. 设置响应头：Content-Disposition 告诉浏览器以附件的形式来处理文件；
2. 设置响应头： response.setContentType(mimeType) 告诉浏览器下载的文件的类型；
3. 流拷贝：
   1. 将文件读取到流里边： FileInputStream fin = new FileInputStream(file)；
   2. 将流写给浏览器：response.getOutputStream().write(arr,0,len);
*/
@WebServlet(urlPatterns = "/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //文件下载
        //1.获取文件资源
        //获取文件名
        String fileName = request.getParameter("fileName");
        System.out.println("fileName = " + fileName);
        File file = new File(getServletContext().getRealPath("/download/") + fileName);
        //2.设置两个响应头文件
        //给浏览器设置响应头：Content-Disposition   告诉浏览器以附件的形式打开这个文件
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        //给浏览器设置响应头：文件类型    response.setContentType(mimetype);
        //获取文件的mimeType
        String mimeType = getServletContext().getMimeType(fileName);
        response.setContentType(mimeType);

        //3.设置两个流，进行流拷贝操作
        //读取文件资源
        FileInputStream fileInputStream = new FileInputStream(file);
        //从response中获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //流拷贝
        int len = 0;
        byte[] arr = new byte[1024];

        while ((len = fileInputStream.read(arr)) != -1) {
            outputStream.write(arr, 0, len);

            //随着response关闭
             outputStream.close();
        }
    }


}
