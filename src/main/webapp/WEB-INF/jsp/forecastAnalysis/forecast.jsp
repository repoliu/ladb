<%--
  Created by IntelliJ IDEA.
  User: 夏利
  Date: 2017/12/25
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style type="text/css">

    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/forecast/css/forecast.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dygraphs/dygraph.css">
    <script src="${pageContext.request.contextPath}/dygraphs/dygraph.js"></script>
    <script src="${pageContext.request.contextPath}/dygraphs/dygraph.min.js"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>

    <script>
        var diaHeight = document.body.scrollHeight;
        var diaWidth = document.body.scrollWidth;
        document.getElementById('draw_div').style.height = 400;
        document.getElementById('draw_div').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('draw_div1').style.height = 400;
        document.getElementById('draw_div1').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('draw_div2').style.height = 400;
        document.getElementById('draw_div2').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('draw_div3').style.height = 400;
        document.getElementById('draw_div3').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('draw_div4').style.height = 400;
        document.getElementById('draw_div4').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('draw_div5').style.height = 400;
        document.getElementById('draw_div5').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('draw_div6').style.height = 400;
        document.getElementById('draw_div6').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('draw_div7').style.height = 400;
        document.getElementById('draw_div7').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('draw_div8').style.height = 400;
        document.getElementById('draw_div8').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('draw_div9').style.height = 400;
        document.getElementById('draw_div9').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('div').style.height = diaHeight * 0.4;
        document.getElementById('div').style.width = (diaWidth - 200) * 0.97;

        /* document.getElementById('tableF').style.height = diaHeight * 0.3;
 */
        document.getElementById('zuoJinMing').style.height = 10;
        document.getElementById('zuoJinMing').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('shiJi').style.height = 10;
        document.getElementById('shiJi').style.width = (diaWidth - 200) * 0.97;

        var widthTable = (diaWidth - 200) * 0.975;
        document.getElementById('widthTable').value = widthTable;

    </script>
    <script src="${pageContext.request.contextPath}/js/forecast/forecast.js"></script>

</head>
<body>
<div style="width: 100%;height: 5px"></div>
<div class="layui-inline">
    <label class="layui-form-label">预测日期</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="test1" placeholder="请选择时间..." style="width: 120px">
    </div>
</div>

<button class="layui-btn layui-btn-radius layui-btn-primary" id="one" value="${dates[0]}"
        onclick="one()">${dates[7]}</button>
<button class="layui-btn layui-btn-radius layui-btn-primary" id="two" value="${dates[1]}"
        onclick="two()">${dates[8]}</button>
<button class="layui-btn layui-btn-radius layui-btn-primary" id="three" value="${dates[2]}"
        onclick="three()">${dates[9]}</button>
<button class="layui-btn layui-btn-radius" id="four" value="${dates[3]}"
        onclick="four()">${dates[10]}</button>
<button class="layui-btn layui-btn-radius layui-btn-primary" id="five" value="${dates[4]}"
        onclick="five()">${dates[11]}</button>
<button class="layui-btn layui-btn-radius layui-btn-primary" id="six" value="${dates[5]}"
        onclick="six()">${dates[12]}</button>
<button class="layui-btn layui-btn-radius layui-btn-primary" id="seven" value="${dates[6]}"
        onclick="seven()">${dates[13]}</button>


<div class="layui-inline">
    <label class="layui-form-label" style="width: 100px">添加实际曲线</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="test2" placeholder="请选择时间..." style="width: 120px">
    </div>
</div>


<input type="hidden" id="date" value="<%=(String)request.getAttribute("date")%>"/>
<input type="hidden" id="area" value="<%=(String)request.getAttribute("area")%>"/>
<input type="hidden" id="areaname" value="<%=(String)request.getAttribute("areaname")%>"/>
<input type="hidden" id="dateScope" value="<%=(String)request.getAttribute("dateScope")%>"/>
<input type="hidden" id="zhuanHuan1s" value="<%=(String)request.getAttribute("zhuanHuan1")%>"/>
<input type="hidden" id="zhuanHuan2s" value="<%=(String)request.getAttribute("zhuanHuan2")%>"/>
<input type="hidden" id="zhuanHuan3s" value="<%=(String)request.getAttribute("zhuanHuan3")%>"/>

<input type="hidden" id="widthTable"/>
<div id="toolbar">
    <div id="tool_zoom"></div>
    <div id="tool_pencil"></div>
    <div id="tool_eraser"></div>
</div>

<div id="div">
    <div class="draw_div" id="draw_div"></div>
    <div class="draw_div" id="draw_div1"></div>
    <div class="draw_div" id="draw_div3"></div>
    <div class="draw_div" id="draw_div4"></div>
    <div class="draw_div" id="draw_div5"></div>
    <div class="draw_div" id="draw_div6"></div>
    <div class="draw_div" id="draw_div7"></div>
    <div class="draw_div" id="draw_div8"></div>
    <div class="draw_div" id="draw_div9"></div>
    <div class="draw_div" id="draw_div2"></div>
    <div id="zuoJinMing" style="padding-left: 150px">
        <span style=" font-size: 18px ">预测曲线:</span>
        <span style="color: red; font-size: 18px" id="yuCe"></span> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;


        <div class="layui-inline" id="dateFan">
            <div class="layui-input-inline">
                <input type="text" class="layui-input" style="width:100px;" id="test9" placeholder="请选择时间">
            </div>
            <div class="layui-input-inline">
                <input type="text" name="phone" style="width: 70px" lay-verify="number" class="layui-input"
                       placeholder="值增加" id="valueAdd" value="<%=(String)request.getAttribute("uiAddValue")%>">
            </div>
            <div class="layui-input-inline">
                <input type="text" name="phone" style="width: 70px" lay-verify="number" class="layui-input"
                       placeholder="值减少" id="valueLess" value="<%=(String)request.getAttribute("uiLessValue")%>">
            </div>
        </div>
        <button class="layui-btn layui-btn-radius" id="yuCeUpdate" onclick="yuCeUpdate()">确认修改</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

        <button class="layui-btn layui-btn-radius" onclick="yuQi()">预期日期修改</button>


    </div>
    <div id="shiJi" style="padding-left: 150px">
        <span style="font-size: 18px ">实际曲线:</span>
        <span style="color: #06d8d7;font-size: 18px" id="jinRi">今日曲线</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="color: #fdd35a ;font-size: 18px" id="zuoRi">昨日曲线</span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="color: darkgrey;font-size: 18px" id="fourSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="color: purple;font-size: 18px" id="fiveSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="color: #119000;font-size: 18px" id="sixSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="color: mediumblue;font-size: 18px" id="sevenSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="color: magenta  ;font-size: 18px" id="eightSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="color: lawngreen;font-size: 18px" id="nineSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="color: #5b5958;font-size: 18px" id="tenSpan"></span>&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
</div>

<div id="tableF" style="overflow: auto ">
    <div class="layui-form" id="tableDiv">
        <table class="layui-table" id="layui-table" style="padding-top: 0px;">
        </table>
    </div>
</div>

</body>
</html>
