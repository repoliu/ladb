<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liuq
  Date: 2018/8/10
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <script>
        $("body").on("mousedown", ".layui-tree li a cite", function () {
            $(".layui-tree li a cite").css('color', 'black');
            $(this).css('color', 'red')
        });

        var diaHeight = document.body.scrollHeight;
        var diaWidth = document.body.scrollWidth;
        document.getElementById('form').style.height = diaHeight*0.82;
        document.getElementById('form').style.width = diaWidth-220;

        var a = "${pageContext.request.contextPath}";
        $(function () {
            a = "${pageContext.request.contextPath}";
        });
    </script>
</head>
<body>
<input type="hidden" id="busName" value="<%=(String)request.getAttribute("busName")%>"/>
<input type="hidden" id="defaultDate" value="${defaultDate}"/>

<div style="height: 5px;width: 100%"></div>
<div class="layui-inline">
    <label class="layui-form-label">日期选择:</label>
    <div class="layui-input-inline">
        <span class="input-icon inverted">
            <input type="text" class="layui-input" id="layuiDateSelection" placeholder="请选择日期....">
            <i class="fa fa-calendar bg-blue"></i>
        </span>
    </div>
</div>
<div style="height: 5px;width: 100%"></div>

<div  id="form"  class="layui-row" style="overflow: auto;margin-left: 30px">
    <div class="layui-col-xs6 layui-col-sm6 layui-col-md4" style="overflow: auto;" >
        <table class="layui-hide" id="busJumpTable1" lay-filter="demoEvent"></table>
    </div>
    <div class="layui-col-xs6 layui-col-sm6 layui-col-md4" style="overflow: auto;">
        <table class="layui-hide" id="busJumpTable2"></table>
    </div>
    <div class="layui-col-xs6 layui-col-sm6 layui-col-md4" style="overflow: auto;">
        <table class="layui-hide" id="busJumpTable3"></table>
    </div>
</div>
</body>
<script>
    //这个是时间选择框
    function layuiDateSelection(layuiDateSelection) {
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#layuiDateSelection'
                ,value: layuiDateSelection
                ,theme: 'grid'
                , done: function (value) {
                    selectDataCharaJump(areaname, value);
                }
            });
        });
    }

    var defaultDate = null;
    $(function () {
        busName = $("#busName").val();
        areaname = busName;
        $("#selectedArea").html("选择的地区:" + areaname);
        defaultDate = $("#defaultDate").val();
        layuiDateSelection(defaultDate);
        $.ajax({
            type: "post", async: true, url: pathContent + "/characteristicIndexController/findAll",    //请求发送到ShowInfoIndexServlet处
            data: {"year": defaultDate},
            dataType: "json",
            success: function (result) {
                layui.use('table', function () {
                    var table = layui.table;
                    //展示已知数据
                    table.render({
                        elem: '#busJumpTable1'
                        , height: diaHeight*0.79
                        , width: 520 //这个是表格的宽度
                        , even: true
                        , page: true //是否显示分页
                        , limits: [150, 200, 250]//可以选择每页显示多少条
                        , limit: 100//每页默认显示的数量
                        , size: 'lg'
                        , cols: result.dygraphs1
                        , data: result.charaReturnValue1
                    });
                    table.render({
                        elem: '#busJumpTable2'
                        , height: diaHeight*0.79
                        , width: 520 //这个是表格的宽度
                        , even: true
                        , page: true //是否显示分页
                        , limits: [150, 200, 250]//可以选择每页显示多少条
                        , limit: 100//每页默认显示的数量
                        , size: 'lg'
                        , cols: result.dygraphs2
                        , data: result.charaReturnValue2
                    });
                    table.render({
                        elem: '#busJumpTable3'
                        , height: diaHeight*0.79
                        , width: 520 //这个是表格的宽度
                        , even: true
                        , page: true //是否显示分页
                        , limits: [150, 200, 250]//可以选择每页显示多少条
                        , limit: 100//每页默认显示的数量
                        , size: 'lg'
                        , cols: result.dygraphs3
                        , data: result.charaReturnValue3
                    });
                });
            }
        });
    });

function selectDataCharaJump(area, year) {
    $.ajax({
        type: "post", async: true, url:pathContent +  "/characteristicIndexController/findAllByAreaAndYear",    //请求发送到ShowInfoIndexServlet处
        data: {"area": area,"year":year},
        dataType: "json",
        success: function (result) {
            layui.use('table', function () {
                var table = layui.table;
                //展示已知数据
                table.render({
                    elem: '#busJumpTable1'
                    , height: diaHeight*0.79
                    , width: 520 //这个是表格的宽度
                    , even: true
                    , page: true //是否显示分页
                    , limits: [150, 200, 250]//可以选择每页显示多少条
                    , limit: 100//每页默认显示的数量
                    , size: 'lg'
                    , cols: result.dygraphs1
                    , data: result.charaReturnValue1
                });
                table.render({
                    elem: '#busJumpTable2'
                    , height: diaHeight*0.79
                    , width: 520 //这个是表格的宽度
                    , even: true
                    , page: true //是否显示分页
                    , limits: [150, 200, 250]//可以选择每页显示多少条
                    , limit: 100//每页默认显示的数量
                    , size: 'lg'
                    , cols: result.dygraphs2
                    , data: result.charaReturnValue2
                });
                table.render({
                    elem: '#busJumpTable3'
                    , height: diaHeight*0.79
                    , width: 520 //这个是表格的宽度
                    , even: true
                    , page: true //是否显示分页
                    , limits: [150, 200, 250]//可以选择每页显示多少条
                    , limit: 100//每页默认显示的数量
                    , size: 'lg'
                    , cols: result.dygraphs3
                    , data: result.charaReturnValue3
                });
            });
        }
    });
}

function queryGeneratrixAnalysis(id,devDescr){
//    defaultDate = $("#layuiDateSelection").val();
//    console.log(defaultDate, areaname, id, devDescr);
    var element = layui.element;
    layid = 'generatrixId';
    element.tabChange('generatrix', layid);
    factorLoading = layer.load(1);
//    setSelectChartData(id, defaultDate, devDescr);
}
</script>
</html>
