<%--
  Created by IntelliJ IDEA.2
  User: 39274
  Date: 2018/1/4
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="layui-inline" style="padding:5px 0;width: 100%">
    <label class="layui-form-label" for="daySelectForCheck">自定义</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" name="daySelectForCheck" id="daySelectForCheck" style="width: 100px">
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
    <button class="layui-btn layui-btn-radius layui-btn-normal" style="float: right" onclick="Cluster()">聚类分析</button>
</div>
<div class="layui-row" style="border: 10px solid #EBEFF2;">
    <div class="layui-col-md8" style="height: 350px;border-right: 10px solid #EBEFF2;">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%;">&nbsp;气象因素对负荷影响</span>
        <div id="graphOfWeatherNow" style="width: 100%;height:100%;margin:  0 auto"></div>
    </div>
    <div class="layui-col-md4" style="height: 350px;">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;支持度分析</span>
        <div id="graphOfSupport" style="width: 100%;height:100%;margin:  0 auto"></div>
    </div>
</div>
<div class="layui-row"
     style="border-bottom: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2;border-right: 10px solid #EBEFF2;">
    <div class="layui-col-md8" style="height: 350px;border-right: 10px solid #EBEFF2;">
        <div class="layui-row">
            <div class="layui-col-md8">
                <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
                    style="font-size: 130%" id="titleOfContrast">&nbsp;气象因素对负荷影响（对比）</span>
                <span style="display: none;font-size: 130%" id="titleOfSupport"></span>
            </div>
            <div class="layui-col-md4" id="selectOfContrast">
                <label class="layui-form-label" for="daySelectForContrast" id="labelOfSelect">对比日期：</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="daySelectForContrast" id="daySelectForContrast"
                           style="width: 100px">
                </div>
            </div>
        </div>
        <div id="graphOfWeatherContrast" style="width: 100%;height:100%;margin:  0 auto"></div>
    </div>
    <div class="layui-col-md4" id="weatherClusterTable">
    </div>
    <div class="layui-col-md4" id="weatherInfo">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;气象信息<span style="color: #229FFD">${weather.mdate}</span></span>
        <div style="width:100%;">
            <div class="layui-col-md4" style="text-align: center">
                <div style="background-image:url('${pageContext.request.contextPath}/images/influ/weather_type.png');background-size:91px 140px;width: 91px;height: 140px;margin:0 auto">
                    <div style="font-size:16px;padding-top: 52px">
                        天气类型
                    </div>
                    <div style="font-size:16px;padding-top: 28px">
                        <c:if test="${empty weather.weatherInfo ||empty weather.weatherInfo.weatherType}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.weatherInfo  &&!empty weather.weatherInfo.weatherType}">
                            ${weather.weatherInfo.weatherType}
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
                        <c:if test="${empty weather.weatherInfo}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.weatherInfo && weather.tempSelect}">
                            <fmt:formatNumber type="number" value="${weather.weatherInfo.maxTemp}" pattern="0.0"
                                              maxFractionDigits="1"/>℃
                        </c:if>
                        <c:if test="${!empty weather.weatherInfo && !weather.tempSelect}">
                            <fmt:formatNumber type="number" value="${weather.weatherInfo.minTemp}" pattern="0.0"
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
                        <c:if test="${empty weather.weatherInfo}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.weatherInfo}">
                            <fmt:formatNumber type="number" value="${weather.weatherInfo.rainfall}" pattern="0.00"
                                              maxFractionDigits="1"/>mm
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
                        <c:if test="${empty weather.weatherInfo}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.weatherInfo}">
                            ${weather.weatherInfo.wp}
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
                        <c:if test="${empty weather.weatherInfo}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.weatherInfo}">
                            <fmt:formatNumber type="number" value="${weather.weatherInfo.humidity}" pattern="0.00"
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
                        <c:if test="${empty weather.weatherInfo}">
                            <span style="color: #229FFD">数据暂缺</span>
                        </c:if>
                        <c:if test="${!empty weather.weatherInfo}">
                            <fmt:formatNumber type="number" value="${weather.weatherInfo.cosiness}" pattern="0.00"
                                              maxFractionDigits="0"/>级
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<style>
    .layui-col-md4{
        width: 33.3%\0;
        float: left;
    }
    .layui-col-md8{
        width: 66.6%\0;
        float: left;
    }

</style>
<script>
    var eWC = 'graphOfWeatherContrast';
    var eWN = 'graphOfWeatherNow';
    var eS = 'graphOfSupport';
    var lastMdate;
    var chartErrorMessage = "<div style='width: 100%;height: 100%;text-align: center;position:relative;'><span style='width:50%;height:50%;margin: auto;  position: absolute;  top: 0; left: 0; bottom: 0; right: 0;font-size: 26px;color: #229FFD;font-family: Arial,Helvetica,sans-serif;'>暂无数据</span></div>";
    $("#menu").show();
    function searchByTime(value) {
        factorLoading = layer.load(1);
        mdate = value;
        changeData();
    }

    function Cluster() {
        var clusterLayer = layer.confirm(mdate.substring(0, 4) + '年' + areaname + '地区数据聚类？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.close(clusterLayer);
            $.ajax({
                type: "post", async: true, url: pathContent + '/influence/weatherAnalysis/clusterAnalysis',
                data: {
                    'areaname': areaname,
                    'mdate': mdate
                },
                dataType: "json",
                success: function (result) {
                    if (result == true) {
                        layer.msg("聚类成功！！！");
                    } else {
                        layer.msg("聚类失败！！！");
                    }
                }
            });
        });
    }

    $(function () {
        analysisType = '${weather.analysisType}';
        areaname = '${weather.areaname}';
        lastMdate = '${weather.lastMdate}';
        document.getElementById("currentArea").innerHTML = areaname;
        mdate = '${weather.mdate}';
        area = '${weather.area}';
        $('#graphOfSupport').bind("contextmenu",function(e){
            return false;
        });
        if ('${isEmpty}' === 'true') {
            layer.msg("该地区没有数据！！！");
            document.getElementById(eS).innerHTML = chartErrorMessage;
            document.getElementById(eWN).innerHTML = chartErrorMessage;
            document.getElementById(eWC).innerHTML = chartErrorMessage;
        } else {
            var chartS = eval('${weather.SList}');
            var chartNow = eval('${weather.nowList}');
            var chartLast = eval('${weather.lastList}');
            if (chartS === null || chartS.length === 0) {
                document.getElementById(eS).innerHTML = chartErrorMessage;
            } else {
                createChartsOfLoadSupport(chartS, eS);
            }
            if (chartNow === null || chartNow.length === 0) {
                document.getElementById(eWN).innerHTML = chartErrorMessage;
            } else {
                createChartsOfLoadAndWeather(chartNow, eWN, analysisType);
            }
            if (chartLast === null || chartLast.length === 0) {
                document.getElementById(eWC).innerHTML = chartErrorMessage;
            } else {
                createChartsOfLoadAndWeather(chartLast, eWC, analysisType);
            }
        }

    });
    layui.use(['layer', 'table', 'laydate'], function () {
        var upload = layui.upload;
        var layer = layui.layer;
        var table = layui.table;
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#daySelectForCheck', //指定元素
            value: '${weather.mdate}',
            calendar: true,
            done: function (value) {
                mdate = value;
                factorLoading = layer.load(1);
                changeData();
            }
        });
        laydate.render({
            elem: '#daySelectForContrast', //指定元素
            value: '${weather.lastMdate}',
            calendar: true,
            done: function (value) {
                lastMdate = value;
                factorLoading = layer.load(1);
                $.ajax({
                    type: "post", async: true, url: pathContent + '/influence/weatherAnalysis/changeCharts',
                    data: {"lastMdate": lastMdate, "analysisType": analysisType, "area": area},
                    dataType: "json",
                    success: function (result) {
                        var chart = eval(result);
                        createChartsOfLoadAndWeather(chart, eWC, analysisType);
                        layer.close(factorLoading);
                    }
                });
            }
        });
    });
</script>
