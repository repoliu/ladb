<%--
  Created by IntelliJ IDEA.
  User: 夏利
  Date: 2018/1/22
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">


    <script>
        var a = "${pageContext.request.contextPath}";

    </script>

</head>
<body>
<div style="width: 100%;height: 10px"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button id="rain" onclick="rain()" value="小雨">小雨<span id="rainSpan"></span></button>
<button id="rain1" onclick="rain1()" value="中雨">中雨<span id="rainSpan1"></span></button>
<button id="rain2" onclick="rain2()" value="大雨">大雨<span id="rainSpan2"></span></button>
<button id="rain3" onclick="rain3()" value="暴雨">暴雨<span id="rainSpan3"></span></button>
<button id="rain4" onclick="rain4()" value="大暴雨">大暴雨<span id="rainSpan4"></span></button>
<button id="rain5" onclick="rain5()" value="特大暴雨">特大暴雨<span id="rainSpan5"></span></button>

<button class="layui-btn layui-btn-radius " id="rain6" onclick="rain6()">全部查询</button>
&nbsp;&nbsp;&nbsp;

<div class="layui-inline">
    <label class="layui-form-label">按时间查询</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="test2" placeholder="请输入时间">
    </div>
</div>


<div style="width: 100%;height: 10px"></div>
<div style="overflow: auto;height: 90%" >

    <div >
        <%--这个是表格--%>
        <table class="layui-hide" id="demo"></table>
    </div>

</div>


<input type="hidden" id="areaname" value="<%=(String)request.getAttribute("areaname")%>"/>
<input type="hidden" id="mdate" value="<%=(String)request.getAttribute("mdate")%>"/>
<script>
    $("#menu").show();
    var areanametemp = "<%=(String)request.getAttribute("areaname")%>";
    var mdateTemp="<%=(String)request.getAttribute("mdate")%>"
</script>
<script src="${pageContext.request.contextPath}/js/influ/rainSelect.js"></script>


</body>
</html>
