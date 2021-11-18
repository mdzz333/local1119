<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>"/>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="/updateUser" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <%-- readonly="readonly" --%>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="请输入名字"/>
        </div>
        <!--
              实际开发中，我数据的id有时候需要随着form提交给服务器，但是，这个又需要对用户隐藏，所以
             我们一般是使用隐藏域
           -->
        <input type="hidden" name="id" value="${user.id}">

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="sex" ${user.sex == "男"?"checked='checked'":""} value="男"/>男
            <input type="radio" name="sex" ${user.sex == "女"?"checked='checked'":""} value="女"/>女
            <input type="radio" name="sex" ${user.sex == "变性人"?"checked='checked'":""} value="变性人"/>变性人
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" value="${user.age}" placeholder="请输入年龄"/>
        </div>

        <div class="form-group">
            <label>籍贯：</label>
            <select name="address" class="form-control">
                <option value="广东" <c:if test="${user.address=='广东'}">selected="selected"</c:if>>广东</option>
                <option value="广西" <c:if test="${user.address=='广西'}">selected="selected"</c:if>>广西</option>
                <option value="湖南" <c:if test="${user.address=='湖南'}">selected="selected"</c:if>>湖南</option>
            </select>
        </div>

        <div class="form-group">
            <label>QQ：</label>
            <input type="text" class="form-control" value="${user.qq}" name="qq"  placeholder="请输入QQ号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" value=${user.email} placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" onclick="javascript:window.history.back();" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>