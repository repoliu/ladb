<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 海健
  Date: 2017/10/18
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>周</title>
    <script>
        //var buttonMaxDate = "${weekMaxDate}";
        var buttonMaxDate = "${daytraitResult.maxMdate}";
        var week = eval(${weekJson});
        var area ="${area}";
        document.getElementById("currentArea").innerHTML ="${areaname}";
        $(function () {
            if (week!=null){
                $("#weektimeSelect").val(week[2][0].substring(0, 4)+"-"+week[2][0].substring(4, 6)+"-"+week[2][0].substring(6, 8))
                $("#id1").html(week[2][0]);
            for (var i = 0; i < 5; i++) {
                var domWeek = $("#week" + i);
                domWeek.html(week[i][0].substring(4, 6) + "." + week[i][0].substring(6, 8) + "-" + week[i][1].substring(4, 6) + "." + week[i][1].substring(6, 8));
                domWeek.val(week[i][0] + "-" + week[i][1]);

                /*给按钮加的禁用限制
                if (week[i][0] < buttonMinDate) {
                     //document.getElementById("week" + i).style.display="none";
                     domWeek.addClass("layui-btn-disabled");

                     domWeek.html(week[i][0].substring(4, 6) + "." + week[i][0].substring(6, 8) + "-" + week[i][1].substring(4, 6) + "." + week[i][1].substring(6, 8));
                     domWeek.val(week[i][0] + "-" + week[i][1]);
                     document.getElementById("week" + i).disabled = "disabled";
                 } else {
                     domWeek.html(week[i][0].substring(4, 6) + "." + week[i][0].substring(6, 8) + "-" + week[i][1].substring(4, 6) + "." + week[i][1].substring(6, 8));
                     domWeek.val(week[i][0] + "-" + week[i][1]);
                 }*/

            }
        }else{
                $("#id1").html("");
                for (var i = 0; i < 5; i++) {
                    var domWeek = $("#week" + i);
                    domWeek.html("");
                    domWeek.val("");
                }
            }


            }
        );
    </script>
    <script src="${pageContext.request.contextPath}/js/periodicity/weektrait.js"></script>
    <style type="text/css">
        /*用于周页面周指标进度条指定属性*/
        div.bar {
            margin: 20px 10px;
        }
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
        .layui-col-md10{
            width: 83.33333333%\0;
            float: left;\0
        }

        .layui-col-md6{
            width: 50%\0;
            float: left;\0
        }

    </style>
</head>
<body>
<div class="layui-inline" id="weektraitContent" style="margin-bottom: 10px;">
    <label class="layui-form-label">开始日期：</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="weektimeSelect" placeholder="请选择时间..."/>
    </div>
    <button class="layui-btn layui-btn-radius layui-btn-primary" onclick="updatePage($(this).val(),'button')"
            id="week4"></button>
    <button class="layui-btn layui-btn-radius layui-btn-primary" onclick="updatePage($(this).val(),'button')"
            id="week3"></button>
    <button class="layui-btn layui-btn-radius " onclick="updatePage($(this).val(),'button')"
            id="week2"></button>
    <button class="layui-btn layui-btn-radius  layui-btn-primary" onclick="updatePage($(this).val(),'button')"
            id="week1"></button>
    <button class="layui-btn layui-btn-radius  layui-btn-primary" onclick="updatePage($(this).val(),'button')"
            id="week0"></button>
</div>

<div class="layui-row layui-col-space1">

    <div class="layui-row" >
        <div id="chinamap" class="layui-col-md3 " style="border: 10px solid #EBEFF2;border-right: 0;">
            <div class="wrap">
                <div class="items" id="Item1" style="margin: 0 auto;width: 260px;">
                    <a href="javascript:" class="fold"></a>
                    <div class="itemCon">
                        <div id="ChinaMap1" style="width: 260px; height: 200px; overflow: hidden; position: relative;"></div>
                    </div>
                </div>
            </div>
        </div >

        <div id="progressbar1" class="layui-col-md9" style="height:220px;border: 10px solid #EBEFF2;">
            <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
                style="font-size: 130%">&nbsp;负荷情况</span><br>
            <div class="layui-col-md4" style="margin-top: 6px">
                <div class="bar">
                    <div><span>周开始日期:</span>
                        <span id="id1"></span></div>
                </div>
                <div class="bar">
                    <div><span>周最大负荷</span>
                        <span style="float: right" id="id2"></span></div>
                    <div class="layui-progress " lay-filter="maxload">
                        <div class="layui-progress-bar layui-bg-green"></div>
                    </div>
                </div>

                <div class="bar">
                    <div><span>周最小负荷</span>
                        <span style="float: right" id="id3"></span></div>
                    <div class="layui-progress " lay-filter="minload">
                        <div class="layui-progress-bar layui-bg-green"></div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md4">
                <div class="bar">
                    <div><span>周平均负荷</span>
                        <span style="float: right" id="id4"></span></div>
                    <div class="layui-progress " lay-filter="aveload">
                        <div class="layui-progress-bar layui-bg-red"></div>
                    </div>
                </div>

                <div class="bar">
                    <div><span>周最小负荷率</span>
                        <span style="float: right" id="id5"></span></div>
                    <div class="layui-progress " lay-filter="minloadrate">
                        <div class="layui-progress-bar layui-bg-red"></div>
                    </div>
                </div>

                <div class="bar">
                    <div><span>周最大峰谷差</span>
                        <span style="float: right" id="id6"></span></div>
                    <div class="layui-progress " lay-filter="maxdiffer">
                        <div class="layui-progress-bar layui-bg-red"></div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md4">
                <div class="bar">
                    <div><span>周最大峰谷差率</span>
                        <span style="float: right" id="id7"></span></div>
                    <div class="layui-progress " lay-filter="maxdifferrate">
                        <div class="layui-progress-bar layui-bg-orange"></div>
                    </div>
                </div>

                <div class="bar">
                    <div><span>周平均峰谷差</span>
                        <span style="float: right" id="id8"></span></div>
                    <div class="layui-progress " lay-filter="avediffer">
                        <div class="layui-progress-bar layui-bg-orange"></div>
                    </div>
                </div>

                <div class="bar">
                    <div><span>周平均峰谷率</span>
                        <span style="float: right" id="id9">
                        </span></div>
                    <div class="layui-progress " lay-filter="avedifferrate">
                        <div class="layui-progress-bar layui-bg-orange"></div>
                    </div>
                </div>
            </div>
        </div>

        <div id="weekgraph" class="layui-col-md12" style="height:70%;">
            <div class="layui-tab-content">
                <div class="layui-col-md6" style="border: 10px solid #EBEFF2;border-right: 0;border-top: 0px;">
                    <!-- 显示Echarts图表 -->
                    <div style="height:410px;margin:0 auto;" id="weekMain1"></div>
                    <%--<div style="overflow-x: scroll;">--%>
                    <%--<table class="layui-table" id="table1" lay-filter="test"></table>--%>
                    <%--</div>--%>
                    <div class="layui-col-md10" style="margin-left:10%;">
                        <div class="layui-col-md6">
                            <div class="bar">
                                <div><span>日最大负荷</span>
                                    <span style="float: right" id="maxload">
                        </span></div>
                                <div class="layui-progress " lay-filter="maxloadDay1">
                                    <div class="layui-progress-bar layui-bg-green"></div>
                                </div>
                            </div>
                            <div class="bar">
                                <div><span>日最小负荷</span>
                                    <span style="float: right" id="minload">
                        </span></div>
                                <div class="layui-progress " lay-filter="minloadDay1">
                                    <div class="layui-progress-bar layui-bg-green"></div>
                                </div>
                            </div>
                        </div>

                        <div class="layui-col-md6">
                            <div class="bar">
                                <div><span>早高峰负荷</span>
                                    <span style="float: right" id="fmmaxload">
                        </span></div>
                                <div class="layui-progress " lay-filter="fmmaxloadDay1">
                                    <div class="layui-progress-bar layui-bg-green"></div>
                                </div>
                            </div>
                            <div class="bar">
                                <div><span>晚高峰负荷</span>
                                    <span style="float: right" id="pmmaxload">
                        </span></div>
                                <div class="layui-progress " lay-filter="pmmaxloadDay1">
                                    <div class="layui-progress-bar layui-bg-green"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layui-col-md6" style="border: 10px solid #EBEFF2;border-top: 0px;">
                    <div style="height:410px;margin:0 auto;" id="weekMain2"></div>
                    <%--<div style="overflow-x: scroll;">--%>
                    <%--<table class="layui-table" id="table2" lay-filter="test"></table>--%>
                    <%--</div>--%>
                    <div class="layui-col-md10" style="margin-left:10%;">
                        <div class="layui-col-md6">
                            <div class="bar">
                                <div><span>日最大负荷</span>
                                    <span style="float: right" id="maxload2">
                        </span></div>
                                <div class="layui-progress " lay-filter="maxloadDay0">
                                    <div class="layui-progress-bar layui-bg-green"></div>
                                </div>
                            </div>
                            <div class="bar">
                                <div><span>日最小负荷</span>
                                    <span style="float: right" id="minload2">
                        </span></div>
                                <div class="layui-progress " lay-filter="minloadDay0">
                                    <div class="layui-progress-bar layui-bg-green"></div>
                                </div>
                            </div>
                        </div>

                        <div class="layui-col-md6">
                            <div class="bar">
                                <div><span>早高峰负荷</span>
                                    <span style="float: right" id="fmmaxload2">
                        </span></div>
                                <div class="layui-progress " lay-filter="fmmaxloadDay0">
                                    <div class="layui-progress-bar layui-bg-green"></div>
                                </div>
                            </div>
                            <div class="bar">
                                <div><span>晚高峰负荷</span>
                                    <span style="float: right" id="pmmaxload2">
                        </span></div>
                                <div class="layui-progress " lay-filter="pmmaxloadDay0">
                                    <div class="layui-progress-bar layui-bg-green"></div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
