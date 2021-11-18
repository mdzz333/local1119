<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>首页</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<div align="center">
    <a
            href="/queryList" style="text-decoration:none;font-size:33px">查询所有用户信息
    </a>
</div>
<div align="center">
    <a
            href="/findUserByPage" style="text-decoration:none;font-size:33px">分页查询用户信息
    </a>
</div>
<div align="center">
    <a
            href="login.html" style="text-decoration:none;font-size:33px">客户登录
    </a>
</div>
<div align="center">
    <a
            href="download.html" style="text-decoration:none;font-size:33px">文件下载
    </a>
</div>
</body>
</html>