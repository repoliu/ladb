<%--
  Created by IntelliJ IDEA.
  User: 39274
  Date: 2017/10/20
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="layui-inline" style="padding:5px 0;">
    <label class="layui-form-label" for="yearSelectForCheck">自定义</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="yearSelectForCheck" style="width: 100px">
    </div>
    <c:forEach items="${yearResult.timeSelectButton}" var="timeTemp">
        <%--<c:if test="${timeTemp.longTime gt yearResult.longMaxTime || timeTemp.longTime lt yearResult.longMinTime}">
            <button class="layui-btn layui-btn-radius layui-btn-disabled">${timeTemp.year}</button>
        </c:if>--%>
        <c:if test="${timeTemp.year == yearResult.year}">
            <button class="layui-btn layui-btn-radius" style="background-color: #3DAA9F"
                    onclick="searchByTime('${timeTemp.year}')">${timeTemp.year}</button>
        </c:if>
        <c:if test="${timeTemp.year != yearResult.year}">
            <button class="layui-btn layui-btn-radius layui-btn-primary"
                    onclick="searchByTime('${timeTemp.year}')">${timeTemp.year}</button>
        </c:if>
        <%--<c:if test="${timeTemp.year ne yearResult.loadYeartrait.year && timeTemp.longTime le yearResult.longMaxTime && timeTemp.longTime ge yearResult.longMinTime}">
            <button class="layui-btn layui-btn-radius layui-btn-primary"
                    onclick="searchByTime('${timeTemp.year}')">${timeTemp.year}</button>
        </c:if>--%>
    </c:forEach>
</div>
<div class="layui-row" style="border: 10px solid #EBEFF2;">
    <div class="layui-col-md3" style="border-right: 10px solid #EBEFF2;height: 200px">
        <div class="wrap">
            <div class="items" id="Item1" style="margin: 0 auto;width: 260px">
                <a href="javascript:" class="fold"></a>
                <div class="itemCon">
                    <div id="ChinaMapOfYear"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-col-md9" style="height: 200px">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;负荷情况</span><br>
        <div class="layui-col-md4">
            <div style="margin: 20px 10px">
                <div><span>年平均负荷</span><span style="float: right"><fmt:formatNumber type="number"
                                                                                    value="${yearResult.loadYeartrait.aveload}"
                                                                                    pattern="0.000"
                                                                                    maxFractionDigits="2"/></span></div>
                <div class="layui-progress" lay-filter="aveload">
                    <div class="layui-progress-bar layui-bg-green"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>年最小负荷</span><span style="float: right"><fmt:formatNumber type="number"
                                                                                    value="${yearResult.loadYeartrait.minload}"
                                                                                    pattern="0.000"
                                                                                    maxFractionDigits="2"/></span></div>
                <div class="layui-progress" lay-filter="minload">
                    <div class="layui-progress-bar layui-bg-green"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>年最大负荷</span><span style="float: right"><fmt:formatNumber type="number"
                                                                                    value="${yearResult.loadYeartrait.maxload}"
                                                                                    pattern="0.000"
                                                                                    maxFractionDigits="2"/></span></div>
                <div class="layui-progress" lay-filter="maxload">
                    <div class="layui-progress-bar layui-bg-green"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div style="margin: 20px 10px">
                <div><span>年平均峰谷差</span><span style="float: right"><fmt:formatNumber type="number"
                                                                                     value="${yearResult.loadYeartrait.avediffer}"
                                                                                     pattern="0.000"
                                                                                     maxFractionDigits="2"/></span>
                </div>
                <div class="layui-progress" lay-filter="avediff">
                    <div class="layui-progress-bar layui-bg-red"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>年负荷率</span><span style="float: right"><fmt:formatNumber type="number"
                                                                                   value="${yearResult.loadYeartrait.loadrate}"
                                                                                   pattern="0.000"
                                                                                   maxFractionDigits="2"/>%</span></div>
                <div class="layui-progress" lay-filter="loadrate">
                    <div class="layui-progress-bar layui-bg-red"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>年最小负荷率</span><span style="float: right"><fmt:formatNumber type="number"
                                                                                     value="${yearResult.loadYeartrait.minloadrate}"
                                                                                     pattern="0.000"
                                                                                     maxFractionDigits="2"/>%</span>
                </div>
                <div class="layui-progress" lay-filter="minloadrate">
                    <div class="layui-progress-bar layui-bg-red"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div style="margin: 20px 10px">
                <div><span>年平均峰谷差率</span><span style="float: right"><fmt:formatNumber type="number"
                                                                                      value="${yearResult.loadYeartrait.avedifferrate}"
                                                                                      pattern="0.000"
                                                                                      maxFractionDigits="2"/>%</span>
                </div>
                <div class="layui-progress" lay-filter="avediffrate">
                    <div class="layui-progress-bar layui-bg-orange"></div>
                </div>
            </div>
            <div style="margin: 20px 10px">
                <div><span>年最大峰谷差率</span><span style="float: right"><fmt:formatNumber type="number"
                                                                                      value="${yearResult.loadYeartrait.maxdifferrate}"
                                                                                      pattern="0.000"
                                                                                      maxFractionDigits="2"/>%</span>
                </div>
                <div class="layui-progress" lay-filter="maxdifferrate">
                    <div class="layui-progress-bar layui-bg-orange"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="layui-row" style="border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2">
    <div class="layui-col-md10" >
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;年负荷曲线</span><br>
    </div>
    <div class="layui-col-md2">
        <div class="layui-input-inline">
            <input type="text" class="layui-input" id="yearSelectForContrast" placeholder="添加比对年份..."
                   onblur="if(this.value==''){this.value='添加比对年份...';$(this).css({color:'#8E8E8E'})}"
                   onfocus="if(this.value=='添加比对年份...'){this.value='';$(this).css({color:'#000000'})}"
                   style="color:#8E8E8E" value="添加比对年份...">
        </div>
    </div>
</div>
<div style="border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2"><div id="graphOfYearLoad" style="height: 400px;margin: 0 auto"></div></div>
<%--<div style="border-right: 10px solid #EBEFF2;border-top: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2">
    <div class="layui-row">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;负荷数据</span><br>
    </div>
    <div id="tableYearLoad" style="border: 20px;height: 320px" class="layui-row">
        <div class="layui-col-md10 layui-col-md-offset1" style="overflow:scroll;height: 100%">
            <table class="dataview" id="yearLoadData"></table>
        </div>
    </div>
</div>--%>
<div style="width: 100%;height: 10px;background-color: #EBEFF2"></div>
<script>
    /*var minYear;
    var maxYear;*/
    var dataYear = [];
    var elementId = "graphOfYearLoad";
    var domYearContent = $('#yearContent');
    var elementDom = document.getElementById(elementId);
    elementDom.style.width = document.body.clientWidth - 200;

    function searchByTime(value) {
        year = value;
        domYearContent.load('${pageContext.request.contextPath}/periodicity/year/load', {"area": area, "year": year});
    }

    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#yearSelectForCheck'
            , type: 'year',
            /*min: '{yearResult.minYear}',
            max: '{yearResult.maxYear}',*/
            value: '${yearResult.year}',

            done: function (value) {
                year = value;
                domYearContent.load('${pageContext.request.contextPath}/periodicity/year/load', {
                    "area": area,
                    "year": year
                });
            }
        });
        laydate.render({
            elem: '#yearSelectForContrast', //指定元素
            type: 'year',
            /*min: "{yearResult.minYear}",
            max: "{yearResult.maxYear}",*/
            done: function (value) {
                dataYear.push(value);
                var jsonText = JSON.stringify(dataYear);
                $.ajax({
                    type: 'post',
                    async: 'true',
                    url: '${pageContext.request.contextPath}/periodicity/year/chartsUpdate',
                    data: {"area": area, "year": jsonText},
                    success: function (result) {
                        var charstOptions = eval(result);
                        /*createTableByData(charstOptions,"yearLoadData");*/
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
    layui.use('element', function () {
        var element = layui.element;
        element.progress('aveload', "${yearResult.loadYeartrait.aveload/400}" + "%");
        element.progress('minload', "${yearResult.loadYeartrait.minload/400}" + "%");
        element.progress('maxload', "${yearResult.loadYeartrait.maxload/400}" + "%");
        element.progress('loadrate', "${yearResult.loadYeartrait.loadrate}" + "%");
        element.progress('minloadrate', "${yearResult.loadYeartrait.minloadrate}" + "%");
        element.progress('avediff', "${yearResult.loadYeartrait.avediffer/400}" + "%");
        element.progress('avediffrate', "${yearResult.loadYeartrait.avedifferrate}" + "%");
        element.progress('maxdifferrate', "${yearResult.loadYeartrait.maxdifferrate}" + "%");
    });
    $(function () {
        if (areaname === null || areaname === undefined || areaname === "") {
            areaname = '${yearResult.areaname}';
        }
        document.getElementById("currentArea").innerHTML = areaname;
        $('#ChinaMapOfYear').SVGMap({
            mapName: 'china',
            mapWidth: 230,
            mapHeight: 200
        });
        if ('${isEmpty}' === 'true') {
            layer.msg("${errorMsg}");
        } else {
            dataYear.push('${yearResult.loadYeartrait.year}');
            /*minYear = '${yearResult.minYear}';
            maxYear = '${yearResult.maxYear}';*/
            var charstOptions = JSON.parse('${chartsList}');
            /*createTableByData(charstOptions,"yearLoadData");*/
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
        color: #8E8E8E;opacity:1;
    }

    input::-webkit-input-placeholder{
        color: #8E8E8E;opacity:1;
    }


</style>
