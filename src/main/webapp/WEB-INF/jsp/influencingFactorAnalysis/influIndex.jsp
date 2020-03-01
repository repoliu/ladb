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
    <title>影响因素分析</title>
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
        table.dataview {

            overflow:hidden;
            border:1px solid #d3d3d3;
            background:#fefefe;
            width:100%;
            margin:0 auto 0;
            -moz-border-radius:5px; /* FF1+ */
            -webkit-border-radius:5px; /* Saf3-4 */
            border-radius:5px;
            -moz-box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
            -webkit-box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
        }

        th.dataview, td.dataview {padding:8px 18px 8px; text-align:center; }

        th.dataview {padding-top:12px; text-shadow: 1px 1px 1px #fff; background:#e8eaeb;}

        td.dataview {border-top:1px solid #e0e0e0; border-right:1px solid #e0e0e0;}

        tr.odd-row td.dataview {background:#f6f6f6;}

        td.first, th.first {text-align:left}

        td.last {border-right:none;}

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
</head>
<body class="layui-layout-body ">
<div class="layui-layout layui-layout-admin ">
    <c:import url="/header"/>

    <div class="layui-side" id="menu" style="border-right:thin solid #BCBCBC;">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-colla-item">
                    <h2 class="layui-colla-title">区域<span style="float: right;font-size: 10px">当前：<span style="color: red;font-size: 10" id="currentArea"></span></span></h2>
                    <div class="layui-colla-content layui-show" style="background-color: #ffffff;">
                        <ul id="tree01"></ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab layui-tab-brief" lay-filter="influ">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="relationPage">全年气象因素分析</li>
                <li lay-id="airConditionerLoadAnalysis">空调负荷分析</li>
                <li lay-id="weatherFactorPageAnalysis">气象因素分析</li>
                <li lay-id="weatherFactorPage">气象因素查询</li>
                <li lay-id="LongTermFactorPage">中长期因素</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <div id="relationContent"></div>
                </div>
                <div class="layui-tab-item layui-show">
                    <div id="airConditionerLoadContent"></div>
                </div>
                <div class="layui-tab-item layui-show">
                    <div id="weatherAnalysisContent"></div>
                </div>
                <div class="layui-tab-item layui-show">
                    <div id="weatherContent"></div>
                </div>
                <div class="layui-tab-item">
                    <div id="LongTermContent"></div>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/influ/index.js"></script>
<script src="${pageContext.request.contextPath}/js/publicMethod.js"></script>
</body>
</html>