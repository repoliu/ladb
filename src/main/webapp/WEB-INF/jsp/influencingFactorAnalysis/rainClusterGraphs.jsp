<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 39274
  Date: 2018/2/8
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/echarts.js"></script>
</head>
<body>
<div  style="text-align: center">
    <span id="info"></span>
    <button id="buttonChange" class="layui-btn layui-btn-normal" style="float: right" onclick="change()" value="1"></button>
</div>
<div class="layui-row" id="graphDiv">
    <c:forEach items="${result.chartsMap}" varStatus="i">
        <div class="layui-col-md6" style="height: 350px;">
            <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
                style="font-size: 130%">&nbsp;类型${i.index+1}</span>
            <div id="graph${i.index}" style="width: 100%;height:100%;margin:  0 auto"></div>
        </div>
    </c:forEach>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/js/publicMethod.js"></script>
<script>
    $("#menu").show();
    var button = document.getElementById("buttonChange");
    layui.use('element', function () {
    });
    <c:forEach items="${result.chartsMap}" var="chart" varStatus="i">
    var temp = eval(${chart});
    if (temp != null) {
        clusterGraph(temp, 'graph' + '${i.index}');
    }
    </c:forEach>
    var buttonvalue = ${result.loadvalue};
    if (buttonvalue == 0){
        button.value = 1;
        button.innerHTML = "点击查看差值结果";
    } else {
        button.value = 0;
        button.innerHTML = "点击查看负荷结果";
    }
    var domInfo = document.getElementById("info");
    domInfo.innerHTML = '<span style="font-size: 130%">地区：${result.areaname}&nbsp;&nbsp;&nbsp;年：${result.year}年</span>';
    function change() {
        if (button.value == 0){
            button.innerHTML = "点击查看差值结果";
            window.location.href='${pageContext.request.contextPath}/influence/weatherAnalysis/rainClusterGraph?area=' +
                ${result.area} + "&mdate=" + ${result.year} + '&loadvalue' + button.value;
            button.value = 1;
        } else {
            button.innerHTML = "点击查看负荷结果";
            window.location.href='${pageContext.request.contextPath}/influence/weatherAnalysis/rainClusterGraph?area=' +
                ${result.area} + "&mdate=" + ${result.year} + '&loadvalue=' + button.value;
            button.value = 0;
        }
    }
</script>
</body>
</html>
