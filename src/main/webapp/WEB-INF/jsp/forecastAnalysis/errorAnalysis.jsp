<%--
  Created by IntelliJ IDEA.
  User: 夏利
  Date: 2018/8/30
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/forecast/errorAnalysis.js"></script>
    <script>
        var diaHeight = document.body.scrollHeight;
        var diaWidth = document.body.scrollWidth;

        document.getElementById('errorTopDate').style.width = (diaWidth - 200) * 0.97;

        document.getElementById('actualCurve').style.height = diaHeight*0.3;
        document.getElementById('actualCurve').style.width = (diaWidth - 200) * 0.97;


        document.getElementById('errorCurve').style.height = diaHeight*0.3;
        document.getElementById('errorCurve').style.width = (diaWidth - 200) * 0.97;
    </script>
</head>
<body>
<input type="hidden" id="areaError" value="<%=(String)request.getAttribute("area")%>"/>
<input type="hidden" id="errorDate" value="<%=(String)request.getAttribute("date")%>"/>
<input type="hidden" id="dateChoose" value="<%=(String)request.getAttribute("dateChoose")%>"/>


<div id="errorTopDate" style="height: 45px" >
    <div style="height: 6px;width: 100%"></div>
    <div class="layui-inline">
        <label class="layui-form-label">日期选择：</label>
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="errorDateChoose" placeholder="请选择时间！">
        </div>
    </div>
</div>

<%--这个是展示实际曲线和预测曲线的 --%>

<div id="actualCurve" ></div>

<%--这个是展示误差曲线的 --%>
<div id="errorCurve"></div>

<%--这个是展示数据表格 --%>
<div id="tableError" style="overflow: auto ">
    <div class="layui-form" id="tableDivError">
        <table class="layui-table" id="layui-tableError" style="padding-top: 0px;">
        </table>
    </div>
</div>

<div id="errorGrid" style="display: none" >
<div style="height: 10px;width: 100%"></div>
    <form class="layui-form" action="">
        <div class="layui-row">
            <div class="layui-col-xs2">
                <div class="grid-demo grid-demo-bg1">

                    <%--这开始时内容，上边的时栅格  --%>
                    <label class="layui-form-label">准确率</label>
                    <div class="layui-input-block">
                        <input style="width: 90px" type="text" id="aveRate" lay-verify="title"  class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs2">
                <div class="grid-demo">
                    <label class="layui-form-label">最大误差</label>
                    <div class="layui-input-block">
                        <input style="width: 90px" type="text" id="maxErr" lay-verify="title"  readonly="readonly" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs2">
                <div class="grid-demo grid-demo-bg1">
                    <label class="layui-form-label">最大误差出现时间</label>
                    <div class="layui-input-block">
                        <input style="width: 90px" type="text" id="maxtime" lay-verify="title"  readonly="readonly" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs2">
                <div class="grid-demo">
                    <label class="layui-form-label">尖峰准确率</label>
                    <div class="layui-input-block">
                        <input style="width: 90px" type="text" id="peakErr" lay-verify="title"  readonly="readonly" class="layui-input">
                    </div>

                </div>
            </div>
            <div class="layui-col-xs2">
                <div class="grid-demo">
                    <label class="layui-form-label">低谷准确率</label>
                    <div class="layui-input-block">
                        <input style="width: 90px" type="text" id="valeyErr" lay-verify="title"  readonly="readonly" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs2">
                <div class="grid-demo"></div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
