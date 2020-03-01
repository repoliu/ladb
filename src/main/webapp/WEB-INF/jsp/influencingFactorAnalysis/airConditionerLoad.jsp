<%--
  Created by IntelliJ IDEA.
  User: 39274
  Date: 2018/1/15
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<meta. http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style type="text/css">
    input[type="radio"] + label::before{
        content: "\a0"; /*不换行空格*/
        display: inline-block;
        vertical-align: middle;
        font-size: 14px;
        width: 1em;
        height: 1em;
        margin-right: .4em;
        border-radius: 50%;
        border: 1px solid #01cd78;
        text-indent: .15em;
        line-height: 1;
    }
    input[type="checkbox"] + label::before {
        content: "\a0"; /*不换行空格*/
        display: inline-block;
        vertical-align: middle;
        font-size: 14px;
        width: 1em;
        height: 1em;
        margin-right: .4em;
        border: 1px solid #01cd78;
        text-indent: .15em;
        line-height: 1;
    }
    input[type="radio"]:checked + label::before,input[type="checkbox"]:checked + label::before {
        background-color: #01cd78;
        background-clip: content-box;
        padding: .2em;
    }
    input[type="radio"],input[type="checkbox"]{
        position: absolute;
        clip: rect(0, 0, 0, 0);
    }
    .layui-col-md6{
        width: 50%\0;
        float: left;
    }
</style>
<%--<div class="layui-inline" style="padding:5px 0;width: 100%">
    <label class="layui-form-label" for="selectDate">自定义</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" name="selectDate" id="selectDate" style="width: 100px">
    </div>
    <c:forEach items="${weather.yearButton}" var="timeTemp">
        <c:if test="${timeTemp.year == weather.year}">
            <button class="layui-btn layui-btn-radius" style="background-color: #3DAA9F"
                    onclick="selectByTime('${timeTemp.year}')">${timeTemp.year}</button>
        </c:if>
        <c:if test="${timeTemp.year != weather.year}">
            <button class="layui-btn layui-btn-radius layui-btn-primary"
                    onclick="selectByTime('${timeTemp.year}')">${timeTemp.year}</button>
        </c:if>
    </c:forEach>
    <button class="layui-btn layui-btn-radius layui-btn-normal" style="float: right" onclick="airConditioner()">空调负荷分析</button>
</div>--%>
<c:import url="/treeHeader"/>
<div style="width: 83%;float: left;">
    <div class="layui-row" style="border: 10px solid #EBEFF2;">
        <div class="layui-col-md6" style="height: 350px;border-right: 10px solid #EBEFF2;">
            <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span style="font-size: 130%">&nbsp;空调负荷最大占比</span>
            <div id="graphOfMaxProportionSupport" style="width: 100%;height:95%;margin:  0 auto;"></div>
        </div>
        <div class="layui-col-md6" style="height: 350px;">
            <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span style="font-size: 130%">&nbsp;最大日电量对比最大电量</span>
            <div id="graphOfMaxEnergySupport" style="width: 100%;height:94%;margin:  0 auto"></div>
        </div>
    </div>
    <div class="layui-row" style="border: 10px solid #EBEFF2;border-top: 0 none #EBEFF2">
        <div class="layui-col-md6" style="height: 350px;border-right: 10px solid #EBEFF2;">
            <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span style="font-size: 130%">&nbsp;空调负荷占比最大日最大负荷</span>
            <div id="graphOfMaxLoadByDaySupport" style="width: 100%;height:100%;margin:  0 auto"></div>
        </div>
        <div class="layui-col-md6" style="height: 350px;">
            <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span style="font-size: 130%">&nbsp;年最大(小)负荷</span>
            <div id="graphOfMaxLoadByYearSupport" style="width: 100%;height:100%;margin:  0 auto"></div>
        </div>
    </div>
</div>
<div style="width: 17%;height:710px;background-color: white;float: right;border-top: 10px solid #EBEFF2;border-bottom: 10px solid #EBEFF2">
    <div style="height: 40px;background-color: #00adee;line-height: 40px;border-bottom: 10px solid #EBEFF2">
        <div style="height: 20px;width: 20px;margin: 10px 10px 10px 10px;border-radius: 5px;background-color: #0C0C0C;float: left;"></div>
        <span style="font-size: 130%;">&nbsp;数据定义</span>
    </div>
    <div style="height: 150px;line-height: 40px;border-bottom: 10px solid #EBEFF2">
        <span style="font-size: 130%;margin-left: 10px;">&nbsp;数据类型</span>
        <div class="layui-row" style="margin-top: 10px" id="selectType">
            <div class="layui-col-xs">
                <div class="grid-demo grid-demo-bg1" style="float: left;margin-left: 30px">
                    <div class="month">
                        <input type="radio" id="month" name="month" onclick="month()" value="月"/>
                        <label for="month">月</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </div>
            <div class="layui-col-xs">
                <div class="grid-demo" style="float: left">
                    <div class="year">
                        <input type="radio" id="year" name="year" onclick="yeara()" value="年"/>
                        <label for="year">年</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </div>
            <div class="layui-col-xs">
                <div class="grid-demo" style="float: left">
                    <div class="custom">
                        <%--<input type="radio" id="custom" name="custom" onclick="custom()" value="自定义"/>--%>
                        <%--<label for="custom">自定义</label>--%>
                        <label class="layui-form-label" for="selectDate">选择日期</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" name="selectDate" id="selectDate" style="width: 100px">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="height: 120px;line-height: 40px;border-bottom: 10px solid #EBEFF2">
        <span style="font-size: 130%;margin-left: 10px;">&nbsp;值类型</span>
        <div class="layui-row" style="margin-top: 10px" id="selectValue">
            <div class="layui-col-xs">
                <div class="grid-demo grid-demo-bg1" style="float: left;margin-left: 30px">
                    <div class="maxValue">
                        <input type="radio" id="maxValue" name="maxValue" onclick="maxValue(1)" value="最大值"/>
                        <label for="maxValue">最大值</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </div>
            <div class="layui-col-xs">
                <div class="grid-demo" style="">
                    <div class="minValue">
                        <input type="radio" id="minValue" name="minValue" onclick="minValue(0)" value="最小值"/>
                        <label for="minValue">最小值</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="height: 150px;line-height: 40px;border-bottom: 10px solid #EBEFF2">
        <span style="font-size: 130%;margin-left: 10px;">&nbsp;统计口径</span>
        <div class="layui-row" style="margin-top: 5px" id="selectCaliber1">
            <div class="layui-col-xs">
                <div class="grid-demo grid-demo-bg1" style="float: left;margin-left: 15px">
                    <div class="caliber1">
                        <span style="font-size: 110%;margin-right: 5px;">&nbsp;调&nbsp;&nbsp;&nbsp;&nbsp;度：</span>
                        <input type="checkbox" id="caliber1" name="caliber1" onclick="caliber1()" value="发受"/>
                        <label for="caliber1">发受</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </div>
            <div class="layui-col-xs">
                <div class="grid-demo" style="float: left">
                    <div class="caliber2">
                        <input type="checkbox" id="caliber2" name="caliber2" onclick="caliber2()" value="发电"/>
                        <label for="caliber2">发电</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </div>
            <div class="layui-col-xs">
                <div class="grid-demo" style="">
                    <div class="caliber3">
                        <input type="checkbox" id="caliber3" name="caliber3" onclick="caliber3()" value="受电"/>
                        <label for="caliber3">受电</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-row" style="margin-top: 5px" id="selectCaliber2">
            <div class="layui-col-xs">
                <div class="grid-demo grid-demo-bg1" style="float: left;margin-left: 15px">
                    <div class="caliber4">
                        <span style="font-size: 110%;margin-right: 5px;">&nbsp;全社会：</span>
                        <input type="checkbox" id="caliber4" name="caliber4" onclick="caliber4()" value="发受"/>
                        <label for="caliber4">发受</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </div>
            <div class="layui-col-xs">
                <div class="grid-demo" style="float: left">
                    <div class="caliber5">
                        <input type="checkbox" id="caliber5" name="caliber5" onclick="caliber5()" value="发电"/>
                        <label for="caliber5">发电</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </div>
            <div class="layui-col-xs">
                <div class="grid-demo" style="">
                    <div class="caliber6">
                        <input type="checkbox" id="caliber6" name="caliber6" onclick="caliber6()" value="受电"/>
                        <label for="caliber6">受电</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="height: 264px;line-height: 40px;"></div>
</div>
<script>
    var eH = 'graphOfMaxLoadByDaySupport';
    var eR = 'graphOfMaxLoadByYearSupport';
    var chartErrorMessage = "<div style='width: 100%;height: 100%;text-align: center;position:relative;'><span style='width:50%;height:50%;margin: auto;  position: absolute;  top: 0; left: 0; bottom: 0; right: 0;font-size: 26px;color: #229FFD;font-family: Arial,Helvetica,sans-serif;'>暂无数据</span></div>";
    $("#menu").hide();
    function commonFun() {
        pieChart("/influence/airConditionerLoad/selectPieChartData");
        maxValue(1);
        maxLoad();
        barGraph("/influence/airConditionerLoad/selectMaxEnergyData");
    }
    function content4(val) {
        $("#content3 span").css("color", "black");
        $("#content4").on("click", "span", function () {
            $("#content4 span").eq($(this).index()).attr("style", "color:red").siblings().removeAttr("style")
        });
        area = val;
        commonFun();
    }
    function selectByTime(value) {
        factorLoading = layer.load(0);
        year = value;
        commonFun();
    }
    function month(){
        $("#year").attr("checked", false);
        $("#custom").attr("checked", false);
    }
    function yeara(){
        $("#month").attr("checked", false);
        $("#custom").attr("checked", false);
    }
    function maxValue(val){
        $("#minValue").attr("checked", false);
        $("#maxValue").attr("checked", true);
        $.ajax({
            type: "post", async: true, url: pathContent + '/influence/airConditionerLoad/selectLoadByLoadDaytrait',
            data: {'area': area,'year': year,'choose':val},
            dataType: "json",
            success: function (result) {
                if (result == null) {
                    layer.msg("该地区没有数据！！！");
                    document.getElementById(eR).innerHTML = chartErrorMessage;
                } else {
                    var chartR = eval(result);
                    if (chartR === null || chartR.length === 0) {
                        document.getElementById(eR).innerHTML = chartErrorMessage;
                    } else {
                        createChartsOfAirConditionerLoadSupport(chartR, eR, '#1E9FFF', '年最大负荷');
                    }
                }
            }
        });
    }
    function minValue(val){
        $("#maxValue").attr("checked", false);
        $.ajax({
            type: "post", async: true, url: pathContent + '/influence/airConditionerLoad/selectLoadByLoadDaytrait',
            data: {'area': area,'year': year,'choose':val},
            dataType: "json",
            success: function (result) {
                if (result == null) {
                    layer.msg("该地区没有数据！！！");
                    document.getElementById(eR).innerHTML = chartErrorMessage;
                } else {
                    var chartR = eval(result);
                    if (chartR === null || chartR.length === 0) {
                        document.getElementById(eR).innerHTML = chartErrorMessage;
                    } else {
                        createChartsOfAirConditionerLoadSupport(chartR, eR, '#1E9FFF', '年最小负荷');
                    }
                }
            }
        });
    }
    function caliber1(){
        $("#caliber2").attr("checked", false);
        $("#caliber3").attr("checked", false);
        $("#caliber4").attr("checked", false);
        $("#caliber5").attr("checked", false);
        $("#caliber6").attr("checked", false);
    }
    function caliber2(){
        $("#caliber1").attr("checked", false);
        $("#caliber3").attr("checked", false);
        $("#caliber4").attr("checked", false);
        $("#caliber5").attr("checked", false);
        $("#caliber6").attr("checked", false);
    }
    function caliber3(){
        $("#caliber2").attr("checked", false);
        $("#caliber1").attr("checked", false);
        $("#caliber4").attr("checked", false);
        $("#caliber5").attr("checked", false);
        $("#caliber6").attr("checked", false);
    }
    function caliber4(){
        $("#caliber2").attr("checked", false);
        $("#caliber3").attr("checked", false);
        $("#caliber1").attr("checked", false);
        $("#caliber5").attr("checked", false);
        $("#caliber6").attr("checked", false);
    }
    function caliber5(){
        $("#caliber2").attr("checked", false);
        $("#caliber3").attr("checked", false);
        $("#caliber4").attr("checked", false);
        $("#caliber1").attr("checked", false);
        $("#caliber6").attr("checked", false);
    }
    function caliber6(){
        $("#caliber2").attr("checked", false);
        $("#caliber3").attr("checked", false);
        $("#caliber4").attr("checked", false);
        $("#caliber5").attr("checked", false);
        $("#caliber1").attr("checked", false);
    }
    $(function () {
        $("#year").attr("checked", true);
        $("#maxValue").attr("checked", true);
        $("#caliber1").attr("checked", true);
        areaname = '${weather.areaname}';
        document.getElementById("currentArea").innerHTML = areaname;
        year = '${weather.year}';
        area = '${weather.area}';
        pieChart("/influence/airConditionerLoad/selectPieChartData")
        if ('${isEmpty}' === 'true') {
            layer.msg("该地区没有数据！！！");
            document.getElementById(eH).innerHTML = chartErrorMessage;
            document.getElementById(eR).innerHTML = chartErrorMessage;
        } else {
            var chartH = eval('${weather.cosnRelations}');
            var chartR = eval('${weather.rainRelations}');
            if (chartH === null || chartH.length === 0) {
                document.getElementById(eH).innerHTML = chartErrorMessage;
            } else {
                createChartsOfAirConditionerLoadSupport(chartH, eH, '#B4C4FF', '最大日最大负荷');
            }
            if (chartR === null || chartR.length === 0) {
                document.getElementById(eR).innerHTML = chartErrorMessage;
            } else {
                createChartsOfAirConditionerLoadSupport(chartR, eR, '#1E9FFF', '年最大负荷');
            }
        }
        barGraph("/influence/airConditionerLoad/selectMaxEnergyData")
    });
    layui.use(['layer', 'laydate',  'jquery'], function () {
        var layer = layui.layer;
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#selectDate', //指定元素
            value: '${weather.year}',
            type: 'year',
            calendar: true,
            done: function (value) {
                year = value;
                factorLoading = layer.load(0);
                commonFun();
                layer.close(factorLoading);
            }
        });
    });

    function maxLoad() {
        $.ajax({
            type: "post", async: true, url: pathContent + '/influence/airConditionerLoad/selectMaxLoad',
            data: {'area': area,'year': year},
            dataType: "json",
            success: function (result) {
                if (result == null) {
                    layer.msg("该地区没有数据！！！");
                    document.getElementById(eH).innerHTML = chartErrorMessage;
                } else {
                    var chartH = eval(result);
                    if (chartH === null || chartH.length === 0) {
                        document.getElementById(eH).innerHTML = chartErrorMessage;
                    } else {
                        createChartsOfAirConditionerLoadSupport(chartH, eH, '#1E9FFF', '空调负荷占比最大日最大负荷');
                    }
                }
            }
        });
    }
    function airConditioner() {
        var airConditionerLayer = layer.confirm(year + '年空调负荷分析？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.close(airConditionerLayer);
            $.ajax({
                type: "post", async: true, url: pathContent + '/influence/airConditionerLoad/airConditionerAnalysis',
                data: {
                    'area': area,
                    'mdate': year
                },
                dataType: "json",
                success: function (result) {
                    if (result == true) {
                        layer.msg("分析成功！！！");
                        $('#airConditionerContent').load(pathContent + '/influence/airConditionerLoad/load', {
                            'area': area,
                            'year': year
                        }, function (response, status, xhr) {
                            layer.close(factorLoading);
                            if (status === 'error') {
                                layer.msg('没有该地区的数据');
                            }
                        });
                        commonFun();
                    } else {
                        layer.msg("分析失败！！！");
                        $('#airConditionerContent').load(pathContent + '/influence/airConditionerLoad/load', {
                            'area': area,
                            'year': year
                        }, function (response, status, xhr) {
                            layer.close(factorLoading);
                            if (status === 'error') {
                                layer.msg('没有该地区的数据');
                            }
                        });
                        commonFun();
                    }
                }
            });
        });
    }

    //饼状图
    function pieChart(urlName) {
        var main0 = echarts.init(document.getElementById('graphOfMaxProportionSupport'));
        var listList = [];
        listList.push(main0);
        var option = {
            title: {
                text: ' ',
                subtext: ' ',
                x: 'center'
            },
            tooltip: {//这个是鼠标悬浮在上边显示具体数的
                trigger: 'item'
            },
            legend: {},
            color: [],
            series: []
        };
        $.ajax({
            type: "post", async: true, url: pathContent + urlName,    //请求发送到ShowInfoIndexServlet处
            data: {"area": area, "year": year},
            dataType: "json",
            success: function (result) {
                if (result.length > 0) {
                    for (var z = 0; z < result.length; z++) {
                        var lenendData = [];//legend中的数据
                        var seriesData = [];//series中的数据
                        var color = [];//这个是饼图显示的颜色
                        for (var i = 0; i < result[z].hengZhou.length; i++) {
                            lenendData.push(result[z].hengZhou[i]);
                        }
                        for (var i = 0; i < result[z].nameValueList.length; i++) {
                            seriesData.push(result[z].nameValueList[i]);
                        }
                        for (var i = 0; i < result[z].color.length; i++) {
                            color.push(result[z].color[i]);
                        }
                        if (result[z].nameValueList[0].value <= 0){
                            listList[z].setOption({ //载入数据
                                legend: {
                                    left: 'left',
                                    data: lenendData
                                },
                                color: color,
                                series: [
                                    {
                                        name: '',
                                        type: 'pie',
                                        radius: '80%',//这个是图的大小
                                        center: ['40%', '55%'],//第一个是x轴，第二个是y轴
                                        data: seriesData,
                                        itemStyle: {
                                            emphasis: {
                                                shadowBlur: 10,
                                                shadowOffsetX: 0,
                                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                                            }
                                        },
                                        label: {            //饼图图形上的文本标签
                                            normal: {
                                                show: false,
                                                position: 'outside', //标签的位置
                                                textStyle: {
                                                    fontWeight: 300,
                                                    fontSize: 12    //文字的字体大小
                                                },
                                                formatter: '{b}  {d}%'
                                            }
                                        },
                                    }
                                ]
                            });
                        }else {
                            listList[z].setOption({        //载入数据
                                legend: {
                                    left: 'left',
                                    data: lenendData
                                },
                                color: color,
                                series: [
                                    {
                                        name: '',
                                        type: 'pie',
                                        radius: '80%',//这个是图的大小
                                        center: ['40%', '55%'],//第一个是x轴，第二个是y轴
                                        data: seriesData,
                                        itemStyle: {
                                            emphasis: {
                                                shadowBlur: 10,
                                                shadowOffsetX: 0,
                                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                                            }
                                        },
                                        label: {            //饼图图形上的文本标签
                                            normal: {
                                                show: true,
                                                position: 'outside', //标签的位置
                                                textStyle: {
                                                    fontWeight: 300,
                                                    fontSize: 12    //文字的字体大小
                                                },
                                                formatter: '{b}  {d}%'
                                            }
                                        },
                                    }
                                ]
                            });
                        }
                    }
                } else {
                    layui.use(['element', 'layer'], function () {
                        var layer = layui.layer;
                        //layer.msg('该地区或地区没有数据！', {icon: 3});//1：对勾，2：X，3：问号
                    });
                }
            }
        });
        //循环载入图表
        for (var z = 0; z < listList.length; z++) {
            listList[z].setOption(option, true);
        }
    }

    //多种柱状图
    function barGraph(urlName) {
        var option1 = {
            tooltip: {
                trigger: 'axis' //坐标轴触发提示框，多用于柱状、折线图中
            },
            legend: {
                /*图表上方的类别显示，好像这个没用，是下边通过ajax获取的*/
                show: true,
                data: []
            },
            xAxis: {     //X轴
                type: 'category',
                data: []    //先设置数据值为空，后面用Ajax获取动态数据填入
            },
            yAxis: [{   //Y轴（这里我设置了两个Y轴，左右各一个）
                type: 'value',
                name: '',//左边y轴上方显示的字
                axisLabel: {formatter: '{value}' /*控制输出格式*/}
            }],
            color: ['#68CFE8', '#FF3333', 'sienna', '#9400d3', 'violet'],
            series: [{  //系列（内容）列表
                name: [],
                type: 'bar',    //折线图表示（生成温度曲线）line   //柱状图表示bar
                symbol: 'emptycircle',    //设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
                data: [],       //数据值通过Ajax动态获取
                /*itemStyle: {
                    normal: {
                        //同一年内多个柱状体颜色不一样
                        color: function (params) {
                            var colorList = ['#68CFE8', '#FFDC35', '#FF3333', 'sienna',    '#FF1493', '#9400d3', '#00F7DE', '#FF5722', 'violet'];
                            return colorList[params.dataIndex];
                        }
                    }
                },*/
            }]
        };
        var barChart = echarts.init(document.getElementById('graphOfMaxEnergySupport'));
        var columnarList = [];
        columnarList.push(barChart);
        $.ajax({
            type: "post", async: true, url: pathContent + urlName,    //请求发送到ShowInfoIndexServlet处
            data: {"area": area, "year": year},
            dataType: "json",
            success: function (result) {
                if (result != null) {
                    for (var z = 0; z < columnarList.length; z++) {
                        for (var a = 0; a < result.length; a++) {
                            if (result[a] !== null && result.length > 0) {
                                var datesXiaoFengGu = [];        //时间数组
                                var dataLegendXiaoFengGu = [];        //图例，上边显示的样例
                                var dataXiaoFengGu = [];         //曲线图的数据
                                var yZhou = [];         //曲线图的数据
                                columnarList[z].showLoading();    //数据加载完之前先显示一段简单的loading动画
                                yZhou.push(result[a].yaxis);
                                for (var i = 0; i < result.length; i++) {
                                    dataLegendXiaoFengGu.push(result[i].series.name);
                                    dataXiaoFengGu.push(result[i].series);
                                }
                                for (var j = 0; j < result[0].hengZhou.length; j++) {
                                    datesXiaoFengGu.push(result[0].hengZhou[j]);
                                }
                                columnarList[z].hideLoading();    //隐藏加载动画
                                columnarList[z].setOption({        //载入数据
                                    legend: {data: dataLegendXiaoFengGu},//不需要显示图例注释了
                                    xAxis: {data: datesXiaoFengGu},
                                    series: dataXiaoFengGu,
                                    yAxis: yZhou
                                });
                            } else {
                                columnarList[z].hideLoading();
                                //返回的数据为空时显示提示信息
                                //alert("图表请求数据为空，可能服务器暂未录入该月的数据，您可以稍后再试！");
                            }
                        }
                    }
                } else {
                     //layer.msg('没有该地区的数据');
                }
            }
        });
        for (var z = 0; z < columnarList.length; z++) {
            columnarList[z].setOption(option1, true);    //载入图表【只是在页面显示一样曲线图的框架，这一步没有显示数据】
        }
    }
</script>