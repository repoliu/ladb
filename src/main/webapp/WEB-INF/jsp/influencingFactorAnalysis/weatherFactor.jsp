<%--
  Created by IntelliJ IDEA.
  User: 39274
  Date: 2017/11/1
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*" %>
<head>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
</head>
<div class="layui-inline" style="padding:5px 0;">
    <label class="layui-form-label" for="daySelectForCheck1">自定义</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" name="daySelectForCheck1" id="daySelectForCheck1" style="width: 100px">
    </div>
    <c:forEach items="${weather.timeButton}" var="timeTemp">
        <c:if test="${timeTemp.yearTime == weather.mdate}">
            <button class="layui-btn layui-btn-radius" style="background-color: #3DAA9F"
                    onclick="searchByTime('${timeTemp.yearTime}')">${timeTemp.monthTime}</button>
        </c:if>
        <c:if test="${timeTemp.yearTime != weather.mdate}">
            <button class="layui-btn layui-btn-radius layui-btn-primary"
                    onclick="searchByTime('${timeTemp.yearTime}')">${timeTemp.monthTime}</button>
        </c:if>
    </c:forEach>
    <button class="layui-btn layui-btn-radius layui-btn-normal" style="float: right" onclick="relationSupport()">支持度分析</button>
</div>

<div class="layui-row" style="border: 10px solid #EBEFF2;">
    <div class="layui-col-md9" style="height: 350px">
        <div class="layui-col-md6" style="border-right: 10px solid #EBEFF2;height: 350px">
            <div class="layui-row">
                <div class="layui-col-md6">
                    <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
                        style="font-size: 130%" id="spanFir">&nbsp;温度</span>
                </div>
                <div class="layui-col-md6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">选择框</label>
                        <div class="layui-input-block" style="padding-top: 8px">
                            <select name="city" lay-verify="required" id="selOfFirst">
                                <option value="0" selected="selected">温度</option>
                                <option value="1">降雨量</option>
                                <option value="2">风力</option>
                                <option value="3">湿度</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div id="graphOfTemperature" style="width: 100%;height:90%;"></div>
        </div>
        <div class="layui-col-md6" style="border-right: 10px solid #EBEFF2;height: 350px">
            <div class="layui-row">
                <div class="layui-col-md6">
                    <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
                        style="font-size: 130%" id="spanSec">&nbsp;
            <button class="layui-btn layui-btn-radius" id="four" onclick="rainLoad()"
                    value="10"><span>降雨量 </span></button>
        </span>
                </div>
                <div class="layui-col-md6">
                    <div class="layui-form-item">
                        <label class="layui-form-label">选择框</label>
                        <div class="layui-input-block" style="padding-top: 8px">
                            <select name="city" lay-verify="required" id="selOfSecond">
                                <option value="0">温度</option>
                                <option value="1" selected="selected">降雨量</option>
                                <option value="2">风力</option>
                                <option value="3">湿度</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <div id="graphOfRainfall" style="width: 100%;height:90%;"></div>
        </div>
    </div>
    <div class="layui-col-md3">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;气象信息<span style="color: #229FFD">${weather.mdate}</span></span>
        <div style="width:100%;">
            <div class="layui-col-md4" style="text-align: center">
                <div style="background-image:url('${pageContext.request.contextPath}/images/influ/weather_type.png');background-size:91px 140px;width: 91px;height: 140px;margin:0 auto">
                    <div style="font-size:16px;padding-top: 52px">
                        天气类型
                    </div>
                    <div style="font-size:16px;padding-top: 28px">
                        <c:if test="${empty weather.forWeather ||empty weather.forWeather.weatherType}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.forWeather  &&!empty weather.forWeather.weatherType}">
                            ${weather.forWeather.weatherType}
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-col-md4" style="text-align: center">
                <div style="background-image:url('${pageContext.request.contextPath}/images/influ/max_temprature.png');background-size:91px 140px;width: 91px;height: 140px;margin:0 auto">
                    <div style="font-size:16px;padding-top: 52px">
                        <c:if test="${weather.tempSelect}">
                            最高温度
                        </c:if>
                        <c:if test="${!weather.tempSelect}">
                            最低温度
                        </c:if>
                    </div>
                    <div style="font-size:16px;padding-top: 32px">
                        <c:if test="${empty weather.forWeather}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.forWeather && weather.tempSelect}">
                            <fmt:formatNumber type="number" value="${weather.forWeather.maxTemperature}" pattern="0.0"
                                              maxFractionDigits="1"/>℃
                        </c:if>
                        <c:if test="${!empty weather.forWeather && !weather.tempSelect}">
                            <fmt:formatNumber type="number" value="${weather.forWeather.minTemperature}" pattern="0.0"
                                              maxFractionDigits="1"/>℃
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-col-md4" style="text-align: center">
                <div style="background-image:url('${pageContext.request.contextPath}/images/influ/min_temprature.png');background-size:91px 140px;width: 91px;height: 140px;margin:0 auto">
                    <div style="font-size:16px;padding-top: 52px">
                        降雨量
                    </div>
                    <div style="font-size:16px;padding-top: 32px">
                        <c:if test="${empty weather.forWeather}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.forWeather}">
                            ${weather.forWeather.rainfall}
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div style="width:100%;">
            <div class="layui-col-md4" style="text-align: center;margin-top: 20px">
                <div style="background-image:url('${pageContext.request.contextPath}/images/influ/pressure.png');background-size:91px 140px;width: 91px;height: 140px;margin:0 auto">
                    <div style="font-size:16px;padding-top: 52px">
                        风速
                    </div>
                    <div style="font-size:16px;padding-top: 28px">
                        <c:if test="${empty weather.forWeather}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.forWeather}">
                            ${weather.forWeather.wp}
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-col-md4" style="text-align: center;margin-top: 20px">
                <div style="background-image:url('${pageContext.request.contextPath}/images/influ/humidity.png');background-size:91px 140px;width: 91px;height: 140px;margin:0 auto">
                    <div style="font-size:16px;padding-top: 52px">
                        湿度
                    </div>
                    <div style="font-size:16px;padding-top: 32px">
                        <c:if test="${empty weather.forWeather}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.forWeather}">
                            <fmt:formatNumber type="number" value="${weather.forWeather.humidity}" pattern="0.00"
                                              maxFractionDigits="0"/>%
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="layui-col-md4" style="text-align: center;margin-top: 20px">
                <div style="background-image:url('${pageContext.request.contextPath}/images/influ/cosiness.png');background-size:91px 140px;width: 91px;height: 140px;margin:0 auto">
                    <div style="font-size:16px;padding-top: 52px">
                        舒适度
                    </div>
                    <div style="font-size:16px;padding-top: 32px">
                        <c:if test="${empty weather.forWeather}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.forWeather}">
                            <fmt:formatNumber type="number" value="${weather.forWeather.cosiness}" pattern="0.00"
                                              maxFractionDigits="0"/>级
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="layui-row"
     style="border-left: 10px solid #EBEFF2;border-right: 10px solid #EBEFF2;border-bottom: 10px solid #EBEFF2;">
    <div class="layui-col-md7" style="border-right: 10px solid #EBEFF2;height: 390px">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;用电负荷与气象影响因子支持度</span>
        <div id="graphOfWSupport" style="width: 100%;height:100%;margin:  0 auto"></div>
    </div>
    <div class="layui-col-md5">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;气象信息导入记录</span>
        <div class="layui-btn-group" style="float: right">
            <button type="button" class="layui-btn layui-btn-normal"
                    onclick='window.location.href="${pageContext.request.contextPath}/download/weatherModel.zip"'>
                <i class="layui-icon">&#xe601;</i>下载模板
            </button>
            <button type="button" class="layui-btn" id="upload">
                <i class="layui-icon">&#xe67c;</i>导入记录
            </button>
        </div>
        <div>
            <table id="importRecord" class="layui-table" lay-skin="row"></table>
        </div>
    </div>
</div>

<style>
    .layui-col-md3{
        width: 25%\0;
        float: left;
    }
    .layui-col-md4{
        width: 33.3%\0;
        float: left;
    }
    .layui-col-md5{
        width: 41.6%\0;
        float: left;
    }
    .layui-col-md6{
        width: 50%\0;
        float: left;
    }
    .layui-col-md7{
        width: 58.3%\0;
        float: left;
    }
    .layui-col-md9{
        width: 75%\0;
        float: left;
    }

</style>

<script>

    var eT = 'graphOfTemperature';
    var eR = 'graphOfRainfall';
    var eS = 'graphOfWSupport';
    var chartErrorMessage = "<div style='width: 100%;height: 100%;text-align: center;position:relative;'><span style='width:50%;height:50%;margin: auto;  position: absolute;  top: 0; left: 0; bottom: 0; right: 0;font-size: 30px;color: #229FFD;font-family: Arial,Helvetica,sans-serif;'>暂无数据</span></div>";
    $("#menu").show();
    function searchByTime(value) {
        factorLoading = layer.load(1);
        mdate = value;
        changeData();
    }

    $(function () {
        areaname = '${weather.areaname}';
        document.getElementById("currentArea").innerHTML = areaname;
        mdate = '${weather.mdate}';
        area = '${weather.area}';
        var firDom = document.getElementById('selOfFirst');
        var secDom = document.getElementById('selOfSecond');
        firDom.onchange = function () {
            var selValue = firDom.options[firDom.selectedIndex].value;
            var spanFir = document.getElementById("spanFir");
            changeSpan (spanFir,selValue);
            changeGraphAjax(selValue, eT);
        };
        secDom.onchange = function () {
            var selValue2 = secDom.options[secDom.selectedIndex].value;
            var spanSec = document.getElementById("spanSec");
            changeSpan (spanSec,selValue2);
            changeGraphAjax(selValue2, eR);
        };
        if ('${isEmpty}' === 'true') {
            Seclayer.msg("该地区没有数据！！！");
            document.getElementById(eS).innerHTML = chartErrorMessage;
            document.getElementById(eR).innerHTML = chartErrorMessage;
            document.getElementById(eT).innerHTML = chartErrorMessage;
        } else {
            var chartT = eval('${weather.TList}');
            var chartR = eval('${weather.RList}');
            var chartS = eval('${weather.SList}');
            if (chartS == null || chartS.length === 0) {
                document.getElementById(eS).innerHTML = chartErrorMessage;
            } else {
                createChartsOfLoadSupportOnly(chartS, eS);
            }
            if (chartR == null || chartR.length === 0) {
                document.getElementById(eR).innerHTML = chartErrorMessage;
            } else {
                createChartsOfWeather(chartR, eR, '降雨量/mm');
            }
            if (chartT == null || chartT.length === 0) {
                document.getElementById(eT).innerHTML = chartErrorMessage;
            } else {
                createChartsOfWeather(chartT, eT, '温度/℃');
            }
        }

    });
    layui.use(['upload', 'layer', 'table', 'laydate'], function () {
        var upload = layui.upload;
        var layer = layui.layer;
        var table = layui.table;
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#daySelectForCheck1', //指定元素
            /*  min: '{weather.minDate}',
            max: '{weather.maxDate}',*/
            value: '${weather.mdate}',
            calendar: true,
            done: function (value) {
                factorLoading = layer.load(1);
                mdate = value;
                changeData();
            }
        });
        var tableData = eval('${weather.IList}');
        //展示已知数据
        var tableOptions = {
            elem: '#importRecord'
            , data: tableData
            , height: 330
            , cols: [[
                {field: 'verTime', title: '时间', width: 170}
                , {field: 'weatherResult', title: '状态'}
                , {field: 'areaName', title: '所属区域'}
            ]]
            , skin: 'row' //表格风格
            , even: true
            , page: true //是否显示分页
            , limits: [4, 6, 8]
            , limit: 6 //每页默认显示的数量

        };
        var tableIn = table.render(tableOptions);
        //执行实例
        var uploadInst = upload.render({
            elem: '#upload' //绑定元素
            , url: '${pageContext.request.contextPath}/influence/upload' //上传接口
            , accept: 'file'  //上传类型
            , exts: 'xls|xlsx'
            , multiple: false   //多文件上传
            , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                layer.load(1); //上传loading
            }
            , done: function (res) {
                layer.closeAll('loading');
                var result = eval(res);
                var position = res.position;
                var content = "&nbsp;&nbsp;成功上传了" + result.successRows + "条记录；<br>";
                var msg = "";
                var i = 0;
                if (result.updateRows > 0) {
                    content = content + "&nbsp;&nbsp;其中更新了" + result.updateRows + "条记录；<br>";
                    i++;
                    msg = msg + result.updateMsg;
                }
                if (result.errorRows + result.readErrorRows > 0) {
                    content = content + "&nbsp;&nbsp;上传失败了" + (result.errorRows + result.readErrorRows) + "条记录。<br>";
                    i++;
                    msg = msg + result.errorMsg + result.readMsg;
                }
                if (i > 0) {
                    content = content + "&nbsp;&nbsp;详细信息：<br>" + msg;
                }
                layer.open({
                    type: 1 //此处以iframe举例
                    , title: res.type
                    , closeBtn: 0
                    , shade: 0
                    , offset: [ //为了演示，随机坐标
                        200 - position
                        , 500 - position
                    ]
                    , content: content
                    , area: ['700px', '500px']
                    , btn: ['确定', '全部关闭'] //只是为了演示
                    , btn2: function () {
                        layer.closeAll();
                    }
                    , zIndex: layer.zIndex //重点1
                    , success: function (layero) {
                        layer.setTop(layero); //重点2
                    }
                });
                table.render(tableOptions);
                tableOptions.data = result.iList;
            }
            , error: function () {
                layer.msg("上传了非法文件！！！<br>请检查上传文件是否为模板格式！！！");
            }
        });
    });

    function rainLoad() {
        var rainDate = $("#daySelectForCheck1").val().substr(0,4);
        console.log(rainDate);
        layer.open({
            type: 2,
            title: '降雨量查询',
            shadeClose: true,
            shade: 0.8,
            area: ['1230px', '88%'],
            content: '${pageContext.request.contextPath}/influence/weather/rainload?areaname=' + areaname + "&mdate=" + rainDate
        });
    }

    function changeGraphAjax(selValue, elementId) {
        var graphType ="";
        var shaftTitle ="";
        switch (selValue) {
            case '0':
                graphType = 'temperature';
                shaftTitle = '温度/℃';
                break;
            case '1':
                graphType = 'rain';
                shaftTitle = '降雨量/mm';
                break;
            case '2':
                graphType = 'wp';
                shaftTitle = '风力/级';
                break;
            case '3':
                graphType = 'humidity';
                shaftTitle = '湿度/%';
                break;
            default:
                break;
        }
        $.ajax({
            type: "post", async: true, url: pathContent + '/influence/weather/changeGraph',
            data: {
                'area': area,
                'mdate': mdate,
                'type': graphType
            },
            dataType: "json",
            success: function (result) {
                var chart = eval(result);
                if (chart === null || chart.length === 0) {
                    document.getElementById(elementId).innerHTML = chartErrorMessage;
                } else {
                    createChartsOfWeather(chart, elementId, shaftTitle);
                }
            }
        });
    }
    function changeSpan (spanDom,selValue){
        if(selValue === '0'){
            spanDom.innerHTML = '&nbsp;温度';
        } else if(selValue === '1'){
            spanDom.innerHTML = '&nbsp;' +
                '<button class="layui-btn layui-btn-radius" id="four" onclick="rainLoad()"' +
                'value="10"><span>降雨量 </span></button>';
        }else if(selValue === '2'){
            spanDom.innerHTML = '&nbsp;风力';
        }else if(selValue === '3'){
            spanDom.innerHTML = '&nbsp;湿度';
        }
    }
    function relationSupport() {
        var relationLayer = layer.confirm(mdate + '' + areaname + '地区气象因素支持度分析？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.close(relationLayer);
            $.ajax({
                type: "post", async: true, url: pathContent + '/influence/weather/clusterAnalysis',
                data: {
                    'areaname': areaname,
                    'mdate': mdate
                },
                dataType: "json",
                success: function (result) {
                    if (result == true) {
                        layer.msg("分析成功！！！");
                    } else {
                        layer.msg("分析失败！！！");
                    }
                }
            });
        });
    }
</script>