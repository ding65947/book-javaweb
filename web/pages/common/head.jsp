<%--
  Created by IntelliJ IDEA.
  User: ding65947
  Date: 2022/7/6
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()
                    +"://"
                    +request.getServerName() //服务器IP
                    +":"
                    +request.getServerPort()  //服务器端口号
                    +request.getContextPath()  //工程路径
                    +"/";
    pageContext.setAttribute("basePath", basePath);
%>

<!--base标签，永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
