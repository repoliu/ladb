<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 39274
  Date: 2017/10/30
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>场景定义</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/echarts.js"></script>
    <script>
        var a = "${pageContext.request.contextPath}";
        $(function () {
            a = "${pageContext.request.contextPath}";
        })
    </script>
    <style>

    </style>
</head>
<body class="layui-layout-body ">
<div class="layui-layout layui-layout-admin ">
    <c:import url="/header"/>
    <div class="layui-tab layui-tab-brief" lay-filter="define">
        <ul class="layui-tab-title">
            <li class="layui-this" lay-id="serviceDefine">服务定义</li>
            <li lay-id="algoDefine">算法定义</li>
            <li lay-id="interfaceDefine">界面定义</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <div id="serviceContent"></div>
            </div>
            <div class="layui-tab-item layui-show">
                <div id="algoContent"></div>
            </div>
            <div class="layui-tab-item layui-show">
                <div id="interfaceContent"></div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/define/index.js"></script>

</body>
</html>