<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: YangSL
  Date: 2017/10/13
  Time: 11:48
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>日负荷预测</title>

    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/SyntaxHighlighter.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/SyntaxHighlighter.js"></script>


    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lib/raphael-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/res/chinaMapConfig.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/map.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/publicMethod.js"></script>
</head>
<script>
    var dataLoadForesult;
    var area = "${area}";
    var areaname ="${areaname}";
    var a = "${pageContext.request.contextPath}";
    $(function () {
        a = "${pageContext.request.contextPath}";
    });

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/forecast/froecastIndex.js"></script>
<style>
    table.dataview {
        overflow: hidden;
        border: 1px solid #d3d3d3;
        background: #fefefe;
        width: 100%;
        margin: 0 auto 0;
        -moz-border-radius: 5px; /* FF1+ */
        -webkit-border-radius: 5px; /* Saf3-4 */
        border-radius: 5px;
        -moz-box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
        -webkit-box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
    }
    th.dataview, td.dataview {
        padding: 8px 18px 8px;
        text-align: center;
    }
    th.dataview {
        padding-top: 12px;
        text-shadow: 1px 1px 1px #fff;
        background: #e8eaeb;
    }
    td.dataview {
        border-top: 1px solid #e0e0e0;
        border-right: 1px solid #e0e0e0;
    }

    tr.odd-row td.dataview {
        background: #f6f6f6;
    }
    td.first, th.first {
        text-align: left
    }
    td.last {
        border-right: none;
    }

    /*
    Background gradients are completely unnecessary but a neat effect.
    */

    td.dataview {
        white-space: nowrap;
        background: -webkit-gradient(linear, 0% 0%, 0% 25%, from(#f9f9f9), to(#fefefe));
    }
    tr.odd-row td.dataview {
        background: -webkit-gradient(linear, 0% 0%, 0% 25%, from(#f1f1f1), to(#f6f6f6));
    }
    th.dataview {
        background: -webkit-gradient(linear, 0% 0%, 0% 20%, from(#ededed), to(#e8eaeb));
    }
</style>
<body class="layui-layout-body ">
<input id="pageContent" type="hidden" value="${pageContext.request.contextPath}"/>
<div class="layui-layout layui-layout-admin ">
    <c:import url="/header"/>
    <div class="layui-side" style="border-right:thin solid #BCBCBC;">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-colla-item">
                    <h2 class="layui-colla-title">区域 ：<span style="float: right;font-size: 10"><%--当前：--%></span><span style="color: red;font-size: 10" id="currentArea"></span></h2>
                    <div class="layui-colla-content layui-show" style="background-color: #ffffff;">
                        <ul id="tree01"></ul><%--这个生成的地区树--%>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <div class="layui-tab layui-tab-brief" lay-filter="forecast">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="forecastId">日负荷预测</li>
                <li class="layui-this" lay-id="errorAnalysisId">日误差分析</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item">
                    <div id="forecastContent"></div>
                </div>
                <div class="layui-tab-item">
                    <div id="errorAnalysis"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
