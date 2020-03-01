
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 海健
  Date: 2017/11/6
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>母线负荷分析</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script>
        //此处为js提供相对路径，勿删
        var a = "${pageContext.request.contextPath}";
        $(function () {
            a = "${pageContext.request.contextPath}";
        })
        var areaname ="${areaname}";
    </script>
    <script src="${pageContext.request.contextPath}/js/generatrix/statics/assets/js/charts/echarts/echarts.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/generatrix/index.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/publicMethod.js"></script>

</head>
<body class="layui-layout-body ">
<div class="layui-layout layui-layout-admin ">
    <c:import url="/header"/>
    <div class="layui-side" style="border-right:thin solid #BCBCBC; ">
        <div class="layui-side-scroll" >
            <span id="selectedArea" ></span>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test" >
                <li class="layui-colla-item">
                    <div class="layui-colla-content layui-show" id="treeXia" style="background-color: #ffffff;">
                        <ul id="tree01"></ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab layui-tab-brief" lay-filter="generatrix">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="characteristicIndexId">特性指标查询</li>
                <li lay-id="busClassificationId">母线负荷分类</li>
                <li lay-id="busJumpId">分类数据查询</li>
                <li lay-id="generatrixId">母线数据查询</li>
            <%--<li lay-id="clusterAnalysisId">聚类分析</li>--%>
            </ul>
            <div class="layui-tab-content" >
                <div class="layui-tab-item">
                    <div id="characteristicIndex"></div>
                </div>
                <div class="layui-tab-item">
                    <div id="busClassification" ></div>
                </div>
                <div class="layui-tab-item">
                    <div id="busJumpTest"></div>
                </div>
                <div class="layui-tab-item layui-show">
                    <div id="generatrixContent"></div>
                </div>
<%--                <div class="layui-tab-item">
                    <div id="clusterAnalysisContent"></div>
                </div>--%>


            </div>
        </div>
    </div>

</div>


</body>
</html>
