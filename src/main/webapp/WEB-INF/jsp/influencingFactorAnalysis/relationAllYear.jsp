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
    /*这个整体是队单选按钮的样式调整*/
    input[type="radio"] + label::before {
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
    input[type="radio"]:checked + label::before {
        background-color: #01cd78;
        background-clip: content-box;
        padding: .2em;
    }
    input[type="radio"] {
        position: absolute;
        clip: rect(0, 0, 0, 0);
    }
    .layui-col-md6{
        width: 50%\0;
        float: left;
    }
</style>
<div class="layui-inline" style="padding:5px 0;width: 100%">
    <label class="layui-form-label" for="relationDaySelect">自定义</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" name="relationDaySelect" id="relationDaySelect" style="width: 100px">
    </div>
    <c:forEach items="${weather.yearButton}" var="timeTemp">
        <c:if test="${timeTemp.year == weather.year}">
            <button class="layui-btn layui-btn-radius" style="background-color: #3DAA9F"
                    onclick="searchByTime('${timeTemp.year}')">${timeTemp.year}</button>
        </c:if>
        <c:if test="${timeTemp.year != weather.year}">
            <button class="layui-btn layui-btn-radius layui-btn-primary"
                    onclick="searchByTime('${timeTemp.year}')">${timeTemp.year}</button>
        </c:if>
    </c:forEach>
    <button class="layui-btn layui-btn-radius layui-btn-normal" style="float: right" onclick="relationSupport()">支持度分析</button>
</div>
<div class="layui-row" style="border: 10px solid #EBEFF2;">
    <div class="layui-col-md6" style="height: 350px;border-right: 10px solid #EBEFF2;">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;温度因素</span>
        <div class="layui-row" id="wenDuRadio">
            <div class="layui-col-xs6">
                <div class="grid-demo grid-demo-bg1" style="float: right;  ">
                    <div class="female"><%--这个div开始时单选按钮--%>
                        <input type="radio" id="female" name="wenDu" onclick="wenDu()" value="温度"/>
                        <label for="female">温度</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="grid-demo" style="left: auto">
                    <div class="male"><%--这个div开始时单选按钮--%>
                        <input type="radio" id="male" name="wenDu" onclick="wenDu()" value="最低温度"/>
                        <label for="male">最低温度</label>
                    </div>
                </div>
            </div>
        </div>
        <div id="graphOfTemperatureSupport" style="width: 100%;height:96%;margin:  0 auto;"></div>
    </div>
    <div class="layui-col-md6" style="height: 350px;">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4">
        <button class="layui-btn layui-btn-radius" id="four" onclick="rainLoad()"
                value="10"><span>降雨量因素 </span></button>
        <div class="layui-row" id="jiangYuRadio">
            <div class="layui-col-xs6">
                <div class="grid-demo grid-demo-bg1" style="float: right;  ">
                    <div class="jiangYuLiang"><%--这个div开始时单选按钮--%>
                        <input type="radio" id="jiangYuLiang" name="jiangYu" onclick="wenDu()" value="降雨量"/>
                        <label for="jiangYuLiang">降雨量</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </div>
            <div class="layui-col-xs6">
                <div class="grid-demo" style="left: auto">
                    <div class="zuiDiJiangYuLiang"><%--这个div开始时单选按钮--%>
                        <input type="radio" id="zuiDiJiangYuLiang" name="jiangYu" onclick="wenDu()" value="最大降雨量"/>
                        <label for="zuiDiJiangYuLiang">最大降雨量</label>
                    </div>
                </div>
            </div>
        </div>
        <div id="graphOfRainSupport" style="width: 100%;height:94%;margin:  0 auto"></div>

    </div>
</div>
<div class="layui-row" style="border: 10px solid #EBEFF2;border-top: 0 none #EBEFF2">
    <div class="layui-col-md6" style="height: 350px;border-right: 10px solid #EBEFF2;">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;舒适度因素</span>
        <div id="graphOfCosinessSupport" style="width: 100%;height:100%;margin:  0 auto"></div>
    </div>
    <div class="layui-col-md6" style="height: 350px;">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;湿度因素</span>
        <div id="graphOfHumiditySupport" style="width: 100%;height:100%;margin:  0 auto"></div>
    </div>
</div>
<input type="hidden" id="wenDu" value="${wenDu}"/><%--XLY加的，从后台获取这个单选按钮的值   --%>
<input type="hidden" id="jiangYu" value="${jiangYu}"/><%--XLY加的，从后台获取这个单选按钮的值   --%>


<script>
    var eT = 'graphOfTemperatureSupport';
    var eC = 'graphOfCosinessSupport';
    var eH = 'graphOfHumiditySupport';
    var eR = 'graphOfRainSupport';
    var chartErrorMessage = "<div style='width: 100%;height: 100%;text-align: center;position:relative;'><span style='width:50%;height:50%;margin: auto;  position: absolute;  top: 0; left: 0; bottom: 0; right: 0;font-size: 26px;color: #229FFD;font-family: Arial,Helvetica,sans-serif;'>暂无数据</span></div>";
    $("#menu").show();
    function searchByTime(value) {
        factorLoading = layer.load(0);
        year = value;
        changeData();
    }

    $(function () {
        //这个是单选按钮
        var danXuan = $("#wenDu").val();
        var jiangYu = $("#jiangYu").val();

        //默认是选中温度
        if (danXuan != null && danXuan != "") {
            if (danXuan == "温度") {
                $("#female").attr("checked", true);
            } else {
                $("#male").attr("checked", true);
            }
        } else {
            $("#female").attr("checked", true);
        }
        if (jiangYu != null && jiangYu != "") {
            if (jiangYu == "降雨量") {
                $("#jiangYuLiang").attr("checked", true);
            } else {
                $("#zuiDiJiangYuLiang").attr("checked", true);
            }
        } else {
            $("#jiangYuLiang").attr("checked", true);
        }
        areaname = '${weather.areaname}';//female
        document.getElementById("currentArea").innerHTML = areaname;
        year = '${weather.year}';
        area = '${weather.area}';
        if ('${isEmpty}' === 'true') {
            layer.msg("该地区没有数据！！！");
            document.getElementById(eT).innerHTML = chartErrorMessage;
            document.getElementById(eC).innerHTML = chartErrorMessage;
            document.getElementById(eH).innerHTML = chartErrorMessage;
            document.getElementById(eR).innerHTML = chartErrorMessage;
        } else {
            var chartT = eval('${weather.tempRelations}');
            var chartC = eval('${weather.cosnRelations}');
            var chartH = eval('${weather.humdRelations}');
            var chartR = eval('${weather.rainRelations}');
            if (chartT === null || chartT.length === 0) {
                document.getElementById(eT).innerHTML = chartErrorMessage;
            } else {
                createChartsOfRelationSupport(chartT, eT, '#70D9F9', '温度');
            }
            if (chartC === null || chartC.length === 0) {
                document.getElementById(eC).innerHTML = chartErrorMessage;
            } else {
                createChartsOfRelationSupport(chartC, eC, '#EB5FA7', '人体舒适度');
            }
            if (chartH === null || chartH.length === 0) {
                document.getElementById(eH).innerHTML = chartErrorMessage;
            } else {
                createChartsOfRelationSupport(chartH, eH, '#B4C4FF', '湿度');
            }
            if (chartR === null || chartR.length === 0) {
                document.getElementById(eR).innerHTML = chartErrorMessage;
            } else {
                createChartsOfRelationSupport(chartR, eR, '#1E9FFF', '降雨量');
            }
        }
    });
    layui.use(['layer', 'laydate',  'jquery'], function () {
        var layer = layui.layer;
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#relationDaySelect', //指定元素
            value: '${weather.year}',
            type: 'year',
            calendar: true,
            done: function (value) {
                year = value;
                factorLoading = layer.load(0);
                changeData();
            }
        });
    });

    //这个是XLY加的，这个是点击降雨量因素按钮进行的弹出框显示数据
    function rainLoad() {
        var rainDate = $("#relationDaySelect").val();
        layer.open({
            type: 2,
            title: '降雨量查询',
            shadeClose: true,
            shade: 0.8,
            area: ['1260px', '650px'],//这个1030是宽度，一共传过来5列数据，每一列数据的宽度是200px，200这个数是后台传过来的
            content: '${pageContext.request.contextPath}/influence/weather/rainload?area=' + area + "&mdate=" + rainDate
        });
    }

    //这个是XLY加的，这个是点击单选按钮的时候进行查询的
    function wenDu() {

        var wenDu = $('#wenDuRadio input[name="wenDu"]:checked ').val();
        var jiangYu = $('#jiangYuRadio input[name="jiangYu"]:checked ').val();

        $('#relationContent').load(pathContent + '/influence/relation/load', {
            'area': area,
            'year': year,
            'wenDu': wenDu,
            'jiangYu':jiangYu
        }, function (response, status, xhr) {
            layer.close(factorLoading);
            if (status === 'error') {
                layer.msg('没有该地区的数据');
                //area = temparea;
            }
        });
    }
    function relationSupport() {
        var relationLayer = layer.confirm(year + '年' + areaname + '地区气象因素支持度分析？', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            layer.close(relationLayer);
            $.ajax({
                type: "post", async: true, url: pathContent + '/influence/relation/clusterAnalysis',
                data: {
                    'areaname': areaname,
                    'mdate': year
                },
                dataType: "json",
                success: function (result) {
                    if (result == true) {
                        layer.msg("分析成功！！！");
                        $('#relationContent').load(pathContent + '/influence/relation/load', {
                            'area': area,
                            'year': year
                        }, function (response, status, xhr) {
                            layer.close(factorLoading);
                            if (status === 'error') {
                                layer.msg('没有该地区的数据');
                                //area = temparea;
                            }
                        });
                       // chong();
                    } else {
                        layer.msg("分析失败！！！");
                        $('#relationContent').load(pathContent + '/influence/relation/load', {
                            'area': area,
                            'year': year
                        }, function (response, status, xhr) {
                            layer.close(factorLoading);
                            if (status === 'error') {
                                layer.msg('没有该地区的数据');
                                //area = temparea;
                            }
                        });
                        //chong();
                    }
                }
            });
        });
    }



    function chong() {
            //这个是单选按钮
            var danXuan = $("#wenDu").val();
            var jiangYu = $("#jiangYu").val();
        console.log("这个是温度----------------------"+danXuan)
        console.log("这个是降雨量----------------------"+jiangYu)
            //默认是选中温度
            if (danXuan != null && danXuan != "") {
                if (danXuan == "温度") {
                    $("#female").attr("checked", true);
                } else {
                    $("#male").attr("checked", true);
                }
            } else {
                $("#female").attr("checked", true);
            }
            if (jiangYu != null && jiangYu != "") {
                if (jiangYu == "降雨量") {
                    $("#jiangYuLiang").attr("checked", true);
                } else {
                    $("#zuiDiJiangYuLiang").attr("checked", true);
                }
            } else {
                $("#jiangYuLiang").attr("checked", true);
            }

            areaname = '${weather.areaname}';//female
            console.log("这个事地区名称----------------------"+areaname)
            document.getElementById("currentArea").innerHTML = areaname;
            year = '${weather.year}';
            area = '${weather.area}';
            if ('${isEmpty}' === 'true') {
                alert("进的是这里");
                layer.msg("该地区没有数据！！！");
                document.getElementById(eT).innerHTML = chartErrorMessage;
                document.getElementById(eC).innerHTML = chartErrorMessage;
                document.getElementById(eH).innerHTML = chartErrorMessage;
                document.getElementById(eR).innerHTML = chartErrorMessage;
            } else {
                alert("jingdeshizheli==============");
                var chartT = eval('${weather.tempRelations}');
                var chartC = eval('${weather.cosnRelations}');
                var chartH = eval('${weather.humdRelations}');
                var chartR = eval('${weather.rainRelations}');
                if (chartT === null || chartT.length === 0) {
                    document.getElementById(eT).innerHTML = chartErrorMessage;
                } else {
                    createChartsOfRelationSupport(chartT, eT, '#70D9F9', '温度');
                }
                if (chartC === null || chartC.length === 0) {
                    document.getElementById(eC).innerHTML = chartErrorMessage;
                } else {
                    createChartsOfRelationSupport(chartC, eC, '#EB5FA7', '人体舒适度');
                }
                if (chartH === null || chartH.length === 0) {
                    document.getElementById(eH).innerHTML = chartErrorMessage;
                } else {
                    createChartsOfRelationSupport(chartH, eH, '#B4C4FF', '湿度');
                }
                if (chartR === null || chartR.length === 0) {
                    document.getElementById(eR).innerHTML = chartErrorMessage;
                } else {
                    createChartsOfRelationSupport(chartR, eR, '#1E9FFF', '降雨量');
                }
            }


    }
</script>