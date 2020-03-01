<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
</head>
<div class="layui-inline" style="padding:5px 0;">
    <label class="layui-form-label" for="daySelectForCheck">自定义</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" name="daySelectForCheck" id="daySelectForCheck"
               style="width: 100px"/>
    </div>
    <c:forEach items="${dayResult.timeSelectButton}" var="timeTemp">
        <c:if test="${timeTemp.yearTime eq dayResult.mdate}">
            <button class="layui-btn layui-btn-radius" style="background-color: #3DAA9F"
                    onclick="searchByTime('${timeTemp.yearTime}')">${timeTemp.monthTime}</button>
        </c:if>
        <c:if test="${timeTemp.yearTime ne dayResult.mdate}">
            <button class="layui-btn layui-btn-radius layui-btn-primary"
                    onclick="searchByTime('${timeTemp.yearTime}')">${timeTemp.monthTime}</button>
        </c:if>
    </c:forEach>
  <%--  <button class="layui-btn layui-btn-primary layui-btn-lg" onclick="selectLoadHisdataToLoadDaytrait()">大型按钮</button>--%>
</div>
<div class="layui-row" style="border: 10px solid #EBEFF2;">
    <div class="layui-col-md3" style="width:25%;border-right: 10px solid #EBEFF2;height: 200px">
        <div class="wrap">
            <div class="items" id="Item1" style="margin: 0 auto;width: 240px">
                <a href="javascript:" class="fold"></a>
                <div class="itemCon">
                    <div id="ChinaMapOfDay"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-md9" style="height: 200px">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%;">&nbsp;负荷情况</span><br>
        <div class="layui-col-md4" >
            <div style="margin: 20px 10px">
                <div><span>日平均负荷</span><span style="float: right">${dayResult.loadDay.aveload}</span></div>
                <div class="layui-progress" lay-filter="aveload">
                    <div class="layui-progress-bar layui-bg-green"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>日最小负荷（${dayResult.loadDay.mintime}）</span><span
                        style="float: right">${dayResult.loadDay.minload}</span></div>
                <div class="layui-progress" lay-filter="minload">
                    <div class="layui-progress-bar layui-bg-green"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>日最大负荷（${dayResult.loadDay.maxtime}）</span><span
                        style="float: right">${dayResult.loadDay.maxload}</span></div>
                <div class="layui-progress" lay-filter="maxload">
                    <div class="layui-progress-bar layui-bg-green"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div style="margin: 20px 10px">
                <div><span>早高峰负荷</span><span style="float: right">${dayResult.loadDay.fmmaxload}</span></div>
                <div class="layui-progress" lay-filter="fmmaxload">
                    <div class="layui-progress-bar layui-bg-red"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>晚高峰负荷</span><span style="float: right">${dayResult.loadDay.pmmaxload}</span></div>
                <div class="layui-progress" lay-filter="pmmaxload">
                    <div class="layui-progress-bar layui-bg-red"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>日峰谷差</span><span style="float: right">${dayResult.loadDay.differ}</span></div>
                <div class="layui-progress" lay-filter="differ">
                    <div class="layui-progress-bar layui-bg-red"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div style="margin: 20px 10px">
                <div><span>日负荷率</span><span style="float: right">${dayResult.loadDay.loadrate}%</span></div>
                <div class="layui-progress" lay-filter="loadrate">
                    <div class="layui-progress-bar layui-bg-orange"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>日最小负荷率</span><span style="float: right">${dayResult.loadDay.minloadrate}%</span></div>
                <div class="layui-progress" lay-filter="minloadrate">
                    <div class="layui-progress-bar layui-bg-orange"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>日峰谷差率</span><span style="float: right">${dayResult.loadDay.differrate}%</span></div>
                <div class="layui-progress" lay-filter="differrate">
                    <div class="layui-progress-bar layui-bg-orange"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="layui-row" style="border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2">
    <div class="layui-col-md10">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;日负荷曲线</span><br>
    </div>
    <div class="layui-col-md2">
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="daySelectForContrast" placeholder="添加比对日期..."
                   onblur="if(this.value==''){this.value='添加比对日期...';$(this).css({color:'#8E8E8E'})}"
                   onfocus="if(this.value=='添加比对日期...'){this.value='';$(this).css({color:'#000000'})}"
                   style="color:#8E8E8E" value="添加比对日期...">
        </div>
    </div>
</div>
<div style="border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2">
    <div id="graphOfDayLoad" class="nav_list_item" style="height: 400px;margin: 0 auto;"></div>
</div>
<%--<div style="border-right: 10px solid #EBEFF2;border-top: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2">
    <div class="layui-row">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;负荷数据</span><br>
    </div>
    <div id="tableDayLoad" style="border: 20px;height: 320px" class="layui-row">
        <div class="layui-col-md10 layui-col-md-offset1" style="overflow:scroll;height: 100%">
            <table class="dataview" id="dayLoadData"></table>
        </div>
    </div>
</div>--%>
<div style="width: 100%;height: 10px;background-color: #EBEFF2"></div>
<script>
    var dataMdate = [];
    var elementId = "graphOfDayLoad";
    var elementDom = document.getElementById(elementId);
    elementDom.style.width = document.body.clientWidth - 200;

    function selectLoadHisdataToLoadDaytrait() {
        $.ajax({
            type: "post", async: true, url: pathContent + '/periodicity/day/selectLoadHisdataToLoadDaytrait',    //请求发送到ShowInfoIndexServlet处
            data: {"mdate": mdate, "area": area},
            dataType: "json",
            success: function (result) {
                console.log(result);
            }
        });
    }
    function searchByTime(value) {
        mdate = value;
        changeData();
        //loadContent(idOfDayContent,dayControllerHref,point,area,mdate);
    }
    layui.use(['element', 'laydate'], function () {
        var element = layui.element;
        element.progress('aveload', "${dayResult.loadDay.aveload/400}" + "%");
        element.progress('minload', "${dayResult.loadDay.minload/400}" + "%");
        element.progress('maxload', "${dayResult.loadDay.maxload/400}" + "%");
        element.progress('fmmaxload', "${dayResult.loadDay.fmmaxload/400}" + "%");
        element.progress('pmmaxload', "${dayResult.loadDay.pmmaxload/400}" + "%");
        element.progress('differ', "${dayResult.loadDay.differ/400}" + "%");
        element.progress('loadrate', "${dayResult.loadDay.loadrate}" + "%");
        element.progress('minloadrate', "${dayResult.loadDay.minloadrate}" + "%");
        element.progress('differrate', "${dayResult.loadDay.differrate}" + "%");
        var laydate = layui.laydate;
        //执行一个laydate实例，日期选择
        laydate.render({
            elem: '#daySelectForCheck', //指定元素
            value: '${dayResult.mdate}',
            /*min: "{dayResult.minMdate}",
            max: "{dayResult.maxMdate}",*/
            calendar: true,
            done: function (value) {
                mdate = value;
                point = '${dayResult.pointResponse}';
                changeData();
            }
        });
        //添加对比日期
        laydate.render({
            elem: '#daySelectForContrast', //指定元素
            /* min: "{dayResult.minMdate}",
            max: "{dayResult.maxMdate}",*/
            done: function (value) {
                dataMdate.push(value);
                var jsonText = JSON.stringify(dataMdate);
                $.ajax({
                    type: 'post',
                    async: 'true',
                    url: '${pageContext.request.contextPath}/periodicity/day/chartsUpdate',
                    data: {"point": point, "area": area, "dataMdate": jsonText, "mdate": mdate},
                    success: function (result) {
                        var charstOptions = eval(result);
                        /*createTableByData(charstOptions,"dayLoadData");*/
                        createCharts(charstOptions, elementId);
                    },
                    error: function () {
                        //请求失败时执行该函数
                        layer.msg("图表请求数据失败，可能是服务器开小差了");
                    }
                });
            }
        });
    });
    $(function () {
        if (areaname === null || areaname === undefined || areaname === "") {
            areaname = '${dayResult.areaname}';
        }
        document.getElementById("currentArea").innerHTML = areaname;
        $('#ChinaMapOfDay').SVGMap({
            mapName: 'china',
            mapWidth: 220,
            mapHeight: 200
        });
        if ('${isEmpty}' === 'true') {
            layer.msg("${errorMsg}");
        } else {
            mdate = '${dayResult.mdate}';
            dataMdate.push('${dayResult.mdate}');
            var charstOptions = eval(${chartsList});
            /*createTableByData(charstOptions,"dayLoadData");*/
            createCharts(charstOptions, elementId);
        }
    });
</script>
<style>
    .layui-col-md4{
        width: 33.3%\0;
        float: left;
    }
    .layui-col-md3{
        width: 25%\0;
        float: left;
    }
    .layui-col-md9{
        width: 75%\0;
        float: left;
    }
    .layui-col-md2{
        width: 16.6%\0;
        float: right;
    }

    :-moz-placeholder {
        color: #8E8E8E; opacity:1;
    }

    ::-moz-placeholder {
    color: #8E8E8E;opacity:1;
    }

    input:-ms-input-placeholder{
        color: #8E8E8E;opacity:1\0;
    }

    input::-webkit-input-placeholder{
        color: #8E8E8E;opacity:1\0;
    }

</style>