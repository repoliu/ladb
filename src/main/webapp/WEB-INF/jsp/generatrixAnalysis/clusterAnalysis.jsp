<%--
  Created by IntelliJ IDEA.
  User: 海健
  Date: 2017/11/6
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<html>


<head>
    <title>聚类分析</title>
    <script>
        $("body").on("mousedown",".layui-tree li a cite",function(){
            $(".layui-tree li a cite").css('color','black');
            $(this).css('color','red')
        });
    </script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/layui/lay/modules/layer.js"></script>
    <script type="text/javascript"  src="${pageContext.request.contextPath}/js/generatrix/cluster.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clusterAnalysis/cluster.css"/>
    <style >
        body:before{background-color:white}
    </style>
</head>
<body>
<div class="layui-inline" style="margin-bottom: 10px">查询条件
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="date-select"   placeholder="请选择时间..."/>
    </div>
    <button class="layui-btn layui-btn-radius " onclick="clusterAnalysis()">聚类分析</button>
</div>
<div class="layui-row">
    <div class="layui-col-md12" style="border: 10px solid #EBEFF2;height: 340px">
        <div class="layui-col-md6" style="border-right: 10px solid #EBEFF2;">
            <div id="cluster1" style="height:100%;"></div>
            <div style="text-align:center;">
            <span style="position:absolute;bottom:10px;padding:0px;margin:0px;font-size: 17px">曲线图1</span>
            </div>

        </div>

        <div class="layui-col-md6" >
            <div id="cluster2" style="height:100%;"></div>
            <div style="text-align:center;">
                <span style="position:absolute;bottom:10px;padding:0px;margin:0px;font-size: 17px">曲线图2</span>
            </div>
        </div>
    </div>
    <div class="layui-col-md12" style="border: 10px solid #EBEFF2;height: 340px;border-top: 0">
        <div class="layui-col-md6" style="border-right: 10px solid #EBEFF2;">
            <div id="cluster3" style="height:100%;"></div>
            <div style="text-align:center;">
                <span style="position:absolute;bottom:10px;padding:0px;margin:0px;font-size: 17px">曲线图3</span>
            </div>
        </div>
        <div class="layui-col-md6" >
            <div id="cluster4" style="height:100%;"></div>
            <div style="text-align:center;">
                <span style="position:absolute;bottom:10px;padding:0px;margin:0px;font-size: 17px">曲线图4</span>
            </div>
        </div>
    </div>

</div>


</body>
</html>
