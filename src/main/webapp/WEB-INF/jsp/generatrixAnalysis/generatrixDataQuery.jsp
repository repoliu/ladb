<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<!-- Head -->
<head>
    <meta charset="utf-8"/>
    <title>Dashboard</title>

    <meta name="description" content="Dashboard"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/js/generatrix/statics/img/favicon.png"
          type="image/x-icon">
    <!--Basic Styles-->
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/bootstrap.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/font-awesome.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/weather-icons.min.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/bootstrap-datepicker.css"
          rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/morris.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/bootstrap-treeview.min.css"
          rel="stylesheet"/>

    <!--Beyond styles-->
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/beyond.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/demo.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/typicons.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/animate.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/js/generatrix/statics/css/es.css" rel="stylesheet"/>

    <script src="${pageContext.request.contextPath}/js/generatrix/statics/assets/js/jquery-1.8.3.min.js"></script>
    <!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
    <script src="${pageContext.request.contextPath}/js/generatrix/statics/assets/js/skins.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/clusterAnalysis/generatrixIndex.css" rel="stylesheet"/>
    <script>
        $("body").on("mousedown", ".layui-tree li a cite", function () {
            $(".layui-tree li a cite").css('color', 'black');
            $(this).css('color', 'red')
        });
    </script>
</head>
<!-- /Head -->
<!-- Body -->
<body>
<!-- Page Header -->
<div class="page-header position-relative">
    <div class="row">
<%--        <div class="col-md-4 col-lg-4" style="padding-left:0;height:40px;">
            <div class="col-md-3" style="padding: 0">
                <h5  style="float: right">地区搜索:</h5>
            </div>
            <div class="col-md-6">
                &lt;%&ndash;此处按回车同样可以搜索,此处为了兼容IE和firefox，使用两个键盘监听事件&ndash;%&gt;
                <input   style="" type="text" onkeypress="EnterPress(event)" onkeydown="EnterPress()" class="layui-input" id="fuzzy" placeholder="请输入地区或电厂"/>
            </div>
            <div class="col-md-2">
            <button class="layui-btn layui-btn-radius " onclick="fuzzyQuery()"
                    id="fuzzyQuery">地区查询
            </button>
            </div>
        </div>--%>



        <div class="col-md-4 col-lg-3" style="height:40px;margin-left:50px;">
            <div class="col-md-5" style="padding-right: 0">
                <h5 style="float:right;">历史日期:</h5>
            </div>

            <div class="col-md-7">
            <span class="input-icon inverted" >

                <input type="text" class="layui-input" name="date-picker" id="date-picker" placeholder="请选择时间..."/>
                <i class="fa fa-calendar bg-blue"></i>
            </span>
            </div>
        </div>


        <%--<div class="col-md-4 col-lg-4">
            <div class="row">
                <div class="col-md-4">
                    <h5 style="margin:10px 0px -10px 20px;float: right" >
                        数据来源:
                    </h5>
                </div>
                <div class="col-md-8">

                    <div class="row">
                        <div class="col-md-5" style="height: 30px;padding-top: 10px;padding-left: 0">
                            <label>
                                <input type="checkbox" id="checkbox_fixedb" checked='checked' value="实测数据"
                                       onchange="getstate(this)">
                                <span class="text" style="float: left">实测数据</span>
                            </label>
                        </div>

                        <div class="col-md-5" style="height: 30px;padding-top: 10px;padding-left: 0">
                            <label>
                                <input type="checkbox" id="checkbox_state" checked='checked' value="状态估计">
                                <span class="text">状态估计</span>
                            </label>
                        </div>

                    </div>

                </div>


            </div>
        </div>--%>

    </div>
    <!--Header Buttons-->
    <div class="header-buttons">
        <a class="fullscreen" id="fullscreen-toggler" href="#">
            <i class="glyphicon glyphicon-fullscreen"></i>
        </a>
    </div>
    <!--Header Buttons End-->

</div>
<div class="page-body" style="padding: 1px 20px 24px;">


    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="widget">
                <div class="widget-header bordered-bottom bordered-blue">
                    <i class="widget-icon fa fa-tags blue"></i>
                    <span class="widget-caption blue">历史负荷数据</span>

                </div><!--Widget Header-->


                <div class="col-lg-4 col-sm-4 col-xs-4">
                    <div class="pre-scrollable">
                        <div id="tree">

                        </div>
                    </div>

                </div>

                <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                    <div class="widget-body">
                        <div class="widget-main no-padding">
                            <div id="fhchart" style="width: 100%;height:600px"></div>

                            <div class="col-md-2 col-lg-2"></div>

                            <div class="row pre-scrollable" style="margin: 10px 10px;">
                                <table class="table table-hover">
                                    <thead class="bordered-blue">
                                    <tr class="time-name">
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr id="fhtable-data">
                                    </tr>

                                    <%--<tr id="rfhtable-data">--%>
                                    <%--</tr>--%>

                                    <%--<tr id="fstatefhtable-data">--%>
                                    <%--</tr>--%>

                                    <%--<tr id="rstatefhtable-data">--%>
                                    <%--</tr>--%>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>


</div>
<!--Basic Scripts-->
<script src="${pageContext.request.contextPath}/js/generatrix/statics/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/generatrix/statics/assets/css/bootstrap-treeview.min.js"></script>

<!--Beyond Scripts-->
<script src="${pageContext.request.contextPath}/js/generatrix/statics/assets/js/beyond.min.js"></script>

<!--Page Related Scripts-->

<script src="${pageContext.request.contextPath}/js/generatrix/statics/assets/js/charts/echarts/wlskin.js"></script>
<script src="${pageContext.request.contextPath}/js/generatrix/statics/js/public.js"></script>
<script src="${pageContext.request.contextPath}/js/generatrix/statics/js/index.js"></script>


<!--Page Related Scripts-->
<script src="${pageContext.request.contextPath}/js/generatrix/statics/assets/js/nestable/jquery.nestable.min.js"></script>
<script type="text/javascript">

    jQuery(function ($) {
//       add();
        createGeneraTrixTree();
        initCharData();
        initDate();
    });

    function getValue() {
        createGeneraTrixTree();
//        add();
    }
    // 初始化日期时间
    function initDate() {
        var index = null;
        $.ajax({
            type: 'post',
            url: '${pageContext.request.contextPath }/HisData/maxDate',
            data: {},
            dataType: "json",
            success: function (data) {
                maxDate(data);
            },
            complete: function () {
                layer.close(index);
            },
            error: function (errorMsg) {
                console.log(errorMsg);
            }
        });
    }
    function maxDate(data) {
        //日期选择器js
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            //执行一个laydate实例
            laydate.render({
                elem: '#date-picker',//指定元素
                value: data.createDatetime
            });
        });
    }

    // ---------负荷预测----------
    function initCharData(data, aa) {

        var fhChart = echarts.init(document.getElementById('fhchart'), 'wlskin');
//        fhChart.showLoading();
        // 指定图表的配置项和数据
        fhChart.setOption({
            title: {
                show: true,
                text: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            grid: [{
                top: '12%',
                right: '6%',
                bottom: '12%'
            }],
            dataZoom: [{
                show: true,
                realtime: true,
                start: 0,
                end: 100
            },
                {
                    type: 'inside',
                    realtime: true,
                    start: 0,
                    end: 100
                }],
            xAxis: {
                type: 'category',
                data: []
            },
            yAxis: {
                name: '单位：MW'
            },

            series: []
        });
        if (data != null) {

//            if (data['whisdata'] || data['rhisdata'] || data['zwhisdata'] || data['zrhisdata']) {
//                if (checkbox_fixedb && checkbox_state) {
                    var option = {
                        title: {
                            x: 'center',
                            y: 'top',
                            show: true,
                            text: aa.text
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            orient: 'horizontal',
                            data: ['实测有功历史'],
                            right: 10,
                            top: 20,
                            textStyle: {
                                color: '#404a59',
                                fontSize: 12
                            },
                        },

                        grid: [{
                            top: '12%',
                            right: '6%',
                            bottom: '12%'
                        }],
                        dataZoom: [{
                            show: true,
                            realtime: true,
                            start: 0,
                            end: 100
                        },
                            {
                                type: 'inside',
                                realtime: true,
                                start: 0,
                                end: 100
                            }],

                        xAxis: {
                            data: data['createDatetime']
                        },
                        yAxis: {
                            name: '单位：MW'
                        },
                        series: [{
                            name: '实测有功历史',
                            type: 'line',
                            data: data['measValue']
                        }/*,
                            {
                                name: '实测无功历史',
                                type: 'line',
                                data: data['rhisdata'].vectorf288change96
                            }
                            ,
                            {
                                name: '状态有功历史',
                                type: 'line',
                                data: data['zwhisdata'].vectorf288change96
                            }
                            ,
                            {
                                name: '状态无功历史',
                                type: 'line',
                                data: data['zrhisdata'].vectorf288change96
                            }*/
                        ]

                    }
                    fhChart.setOption(option);
               /* } else if (checkbox_fixedb && !checkbox_state) {

                    var option = {
                        title: {
                            x: 'center',
                            y: 'top',
                            show: true,
                            text: aa.text
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            show: true,
                            orient: 'horizontal',
                            data: ['实测有功历史', '实测无功历史'],
                            right: 10,
                            top: 20,
                            textStyle: {
                                color: '#404a59',
                                fontSize: 12
                            }
                        },

                        grid: [{
                            top: '12%',
                            right: '6%',
                            bottom: '12%'
                        }],
                        dataZoom: [{
                            show: true,
                            realtime: true,
                            start: 0,
                            end: 100
                        },
                            {
                                type: 'inside',
                                realtime: true,
                                start: 0,
                                end: 100
                            }],
                        xAxis: {
                            data: data['whisdata']
                        },
                        yAxis: {
                            name: '单位：MW'
                        },
                        series: [{
                            name: '实测有功历史',
                            type: 'line',
                            data: data['whisdata'].vectorf288change96
                        },
                            {
                                name: '实测无功历史',
                                type: 'line',
                                data: data['rhisdata'].vectorf288change96
                            }
                        ]
                    };

                    fhChart.setOption(option);


                } else if (!checkbox_fixedb && checkbox_state) {

                    var option = {

                        title: {
                            x: 'center',
                            y: 'top',
                            show: true,
                            text: aa.text
                        },
                        tooltip: {
                            trigger: 'axis'
                        },

                        legend: {
                            x: 'center',
                            y: 'top',
                            show: true,
                            orient: 'horizontal',
                            data: ['状态有功历史', '状态无功历史'],
                            right: 10,
                            top: 20,
                            textStyle: {
                                color: '#404a59',
                                fontSize: 12
                            },
                        },

                        grid: [{
                            top: '12%',
                            right: '6%',
                            bottom: '12%'
                        }],
                        dataZoom: [{
                            show: true,
                            realtime: true,
                            start: 0,
                            end: 100
                        },
                            {
                                type: 'inside',
                                realtime: true,
                                start: 0,
                                end: 100
                            }],
                        xAxis: {
                            data: data['zwhisdata']
                        },
                        yAxis: {
                            name: '单位：MW'
                        },
                        series: [
                            {
                                name: '状态有功历史',
                                type: 'line',
                                data: data['zwhisdata'].vectorf288change96
                            }
                            ,
                            {
                                name: '状态无功历史',
                                type: 'line',
                                data: data['zrhisdata'].vectorf288change96
                            }
                        ]
                    }

                    fhChart.setOption(option);
                }
                fhChart.hideLoading();
              } else {
              }*/

            fhChart.hideLoading();
        }
    }

    function add() {
        $('#tree').html("");
        var content = "  <div id='test-tree' class='dd'><ol class='dd-list'>" +
            "<li class='dd-item' data-id='2'><div class='dd-handle'>Second Level</div><ol class='dd-list' >" +
            "<li class='dd-item bordered-blue' ><div class='dd-handle'> Subitem 1 </div></li>" +
            "<li class='dd-item bordered-palegreen' ><div class='dd-handle'> Subitem 2</div></li> </ol> " +
            "</li> </ol></div>";
        $('#tree').append(
            content
        );
        $('.dd').nestable();
        $('.dd-handle a').on('mousedown', function (e) {
            e.stopPropagation();
        });

    }

    function setForChartData(aa) {
        //var id = aa.innerHTML;
        var id = aa.id;
        var datepicker = $('#date-picker').val();
//        var checkbox_fixedb = getChecked('checkbox_fixedb');
//        var checkbox_state = getChecked('checkbox_state');

        //alert(checkbox_fixedb + "   " + checkbox_state+"  "+id)

//        setSelectChartData(id, datepicker, checkbox_fixedb, checkbox_state, aa);
        setSelectChartData(id, datepicker, aa);

    }

    //初始化tree
    function treeView(data) {

            $('#tree').treeview({
                data: data,
                selectedBackColor: '#5DB2FF',
                //事件
                onNodeSelected: function (event, data) {

                    if($('#date-picker').val().length > 0) {
                        setForChartData(data);
                    }else{
                        layer.msg('请选择时间', {
                            offset: 200,
                            time: 2000
                        });
                    }
                }

            });

        if(data.length>0) {
            //确保点击一级树时不被阻塞
            var selectdata = data[0].nodes[0];
            setForChartData(selectdata);
        }
       /* if($('#date-picker').val().length>0) {
            //默认选择二级树第一个节点下子节点的子节点数据
            var selectdata = data[0].nodes[0].nodes[0];
            setForChartData(selectdata);
        }else{
            layer.msg('请选择时间', {
                offset: 200,
                time: 2000
            });
        }*/
    }

    function createGeneraTrixTree() {
        if(areaname=="国网"){
            areaname="国调";
        }
        var index=null;
            $.ajax({
                async: true,
                type: 'post',
                beforeSend:function(){
                     index = layer.msg('正在加载....', {
                        icon: 1,
                        time: 30000 //30秒关闭,必须大于包括数据库查询时间+前段js加载数据时间，
                        // 因为ajax执行完毕后会layer会执行关闭方法，
                        // （如果不配置，默认是3秒）
                    });
                },
                url: '${pageContext.request.contextPath }/HisData/show',
                data: {
                    // area: $('.form-control').val(), //原来为从选择器选择
                    area: areaname,
                    time: $('#date-picker').val()
                },

                success: function (data) {
                        treeView(data);
                },
                complete: function() {
                    layer.close(index);
                },
                error: function (errorMsg) {
                    console.log(errorMsg);
                }
            });



    }

    function fuzzyQuery() {
        if(areaname=="国网"){
            areaname="国调";
        }
        var index=null;
        $.ajax({
            async: true,
            type: 'post',
            beforeSend:function(){
                 index = layer.msg('正在查询....', {
                    icon: 1,
                    time: 30000 //30秒关闭,必须大于包括数据库查询时间+前段js加载数据时间，
                    // 因为ajax执行完毕后会layer会执行关闭方法，
                    // （如果不配置，默认是3秒）
                });
            },
            url: '${pageContext.request.contextPath }/HisData/fuzzyQuery',
            data: {
                // area: $('.form-control').val(), //原来为从选择器选择
                keyword: $("#fuzzy").val(),
                area: areaname,
                time: $('#date-picker').val()
            },

            success: function (data) {

                if (data.length > 0) {
                    if($("#fuzzy").val().length != 0) {
                        treeView(data);
                    }else{
                        layer.msg('请输入地区或电厂', {
                            offset: 200,
                            time: 2000
                        });
                    }

                } else if ($("#fuzzy").val().length != 0 & data.length == 0) {

                    layer.msg('缺少数据', {
                        offset: 200,
                        time: 2000
                    });

                } else {
                    layer.msg('请输入地区或电厂', {
                        offset: 200,
                        time: 2000
                    });
                }
            },
            complete:function(){
                layer.close(index);
            },
            error: function (errorMsg) {
                console.log(errorMsg);
            }
        });
    }


    function EnterPress(e){
        var theEvent = window.event || e;
        var code = theEvent.keyCode || theEvent.which;
        if (code==13) {  //回车键的键值为13
            fuzzyQuery();  //调用搜索事件
        }
    }

    function serachdata(aa) {
        //var id = aa.innerHTML;
        var id = aa.id;
        var datepicker = $('#date-picker').val();
        var checkbox_fixedb = getChecked('checkbox_fixedb');
        var checkbox_state = getChecked('checkbox_state');

        //alert(checkbox_fixedb + "   " + checkbox_state)

    }

    function getChecked(id) {
        var element = $('#' + id);
        var state = element[0].checked;
        //var astate =  element.getElementById(id).checked;
        return state;

    }


    function setSelectChartData(id, datepicker, aa) {
        //var time = id+"_"+datepicker;
        // alert(checkbox_fixedb + "   " + checkbox_state)
        $.ajax({
            async: true,
            type: 'post',
            url: "${pageContext.request.contextPath }/HisData/search",
            data: {
                id: id,
                time: datepicker,
//                checkbox_fixedb: checkbox_fixedb,
//                checkbox_state: checkbox_state
            },
            success: function (data) {
                var msg = '';
                if (data) {
                    // refreshFhChart(data);
                    //  addChecked(checkbox_fixedb, checkbox_state, data,aa)
//                    initCharData(checkbox_fixedb, checkbox_state, data, aa);
                    initCharData(data, aa);
                    downCheckLoad(data);
                } else {
                    //fhChart.hideLoading();
                    msg += '系统负荷,';
                }

                if (msg) {
                    layer.msg('缺少该日数据', {
                        offset: 200,
                        time: 2000
                    });
                }
            },
            error: function (e) {
                // fhChart.hideLoading();
                layer.msg('发生了未知异常', {
                    offset: 200,
                    time: 1500
                });
            }
        });
    }

//    function downCheckLoad(data) {
    function downCheckLoad(data) {
        $('.time-name').html("");
        $('#fhtable-data').html("");
//      动态追加时间和数值
        var time = '';
        time += '<td>数据类型名称</td>';
        for (var i = 0; i < 96; i++) {
            <c:forEach items="data['createDatetime']" var="t">
            time += '<th>' + ${t}[i] + '</th>';
            </c:forEach>
        }
        $('.time-name').append(time);

        var datah = '';
            datah += '<td>实测有功历史</td>';
            for (var i = 0; i < 96; i++) {
                <c:forEach items="data['measValue']" var="t">
                    datah += '<td>' + ${t}[i] + '</td>';
                </c:forEach>
            }
        $('#fhtable-data').append(datah);
        /*var rdatah = '';
        if (data['rhisdata']) {
            rdatah += '<td>实测无功历史</td>';
            for (var i = 0; i < 97; i++) {
                rdatah += '<td>' + data['rhisdata'].vectorf288change96[i] + '</td>';
            }
        }
        $('#rfhtable-data').append(rdatah);

        var statedatah = '';
        if (data['zwhisdata']) {
            statedatah += '<td>状态有功历史</td>';
            for (var i = 0; i < 97; i++) {
                statedatah += '<td>' + data['zwhisdata'].vectorf288change96[i] + '</td>';
            }
        }
        $('#fstatefhtable-data').append(statedatah);

        var dstatedatah = '';
        if (data['zrhisdata']) {
            dstatedatah += '<td>状态无功历史</td>';
            for (var i = 0; i < 97; i++) {
                dstatedatah += '<td>' + data['zrhisdata'].vectorf288change96[i] + '</td>';
            }
        }
        $('#rstatefhtable-data').append(dstatedatah);*/
    }


    function addChecked(checkbox_fixedb, checkbox_state, data, aa) {
        fhChart.hideLoading();

        if (checkbox_fixedb && checkbox_state) {
//            fhChart.getOption();
            var option = {
                title: {
                    x: 'center',
                    y: 'top',
                    show: true,
                    text: aa.text

                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    orient: 'horizontal',
                    data: ['实测有功历史', '实测无功历史', '状态有功历史', '状态无功历史'],
                    right: 10,
                    top: 20,
                    textStyle: {
                        color: '#404a59',
                        fontSize: 12
                    },
                },

                xAxis: {
                    data: data['whisdata']
                },
                series: [{
                    name: '实测有功历史',
                    type: 'line',
                    data: data['whisdata'].vectorf288change96
                },
                    {
                        name: '实测无功历史',
                        type: 'line',
                        data: data['rhisdata'].vectorf288change96
                    }
                    ,
                    {
                        name: '状态有功历史',
                        type: 'line',
                        data: data['zwhisdata'].vectorf288change96
                    }
                    ,
                    {
                        name: '状态无功历史',
                        type: 'line',
                        data: data['zrhisdata'].vectorf288change96
                    }
                ]

            }

            fhChart.setOption(option);
        } else if (checkbox_fixedb && !checkbox_state) {
            var option = {
                title: {
                    x: 'center',
                    y: 'top',
                    show: true,
                    text: aa.text
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    show: true,
                    orient: 'horizontal',
                    data: ['实测有功历史', '实测无功历史'],
                    right: 10,
                    top: 20,
                    textStyle: {
                        color: '#404a59',
                        fontSize: 12
                    },
                },


                xAxis: {
                    data: data['whisdata']
                },
                series: [{
                    name: '实测有功历史',
                    type: 'line',
                    data: data['whisdata'].vectorf288change96
                },
                    {
                        name: '实测无功历史',
                        type: 'line',
                        data: data['rhisdata'].vectorf288change96
                    }
                ]
            }
            fhChart.setOption(option);
        } else if (!checkbox_fixedb && checkbox_state) {
            var option = {

                title: {
                    show: true,
                    text: aa.text
                },
                tooltip: {
                    trigger: 'axis'
                },

                legend: {
                    x: 'center',
                    y: 'top',
                    show: true,
                    orient: 'horizontal',
                    data: ['状态有功历史', '状态无功历史'],
                    right: 10,
                    top: 20,
                    textStyle: {
                        color: '#404a59',
                        fontSize: 12
                    },
                },

                xAxis: {
                    data: data['zwhisdata']
                },
                series: [
                    {
                        name: '状态有功历史',
                        type: 'line',
                        data: data['zwhisdata'].vectorf288change96
                    }
                    ,
                    {
                        name: '状态无功历史',
                        type: 'line',
                        data: data['zrhisdata'].vectorf288change96
                    }
                ]
            }
//            fhChart.setOption(option,true);

//            fhChart.clear();
//            fhChart = echarts.init(document.getElementById('fhchart'), 'wlskin');
            fhChart.setOption(option);
        }

        $('#fhtable-data').html("");
        $('#rfhtable-data').html("");

        $('#fstatefhtable-data').html("");
        $('#rstatefhtable-data').html("");

        var datah = '';
        for (var i = 0; i < 97; i++) {
            datah += '<td>' + data['whisdata'].vectorf288change96[i] + '</td>';
        }
        $('#fhtable-data').append(datah);
        var rdatah = '';
        for (var i = 0; i < 97; i++) {
            rdatah += '<td>' + data['rhisdata'].vectorf288change96[i] + '</td>';
        }
        $('#rfhtable-data').append(rdatah);

        var statedatah = '';
        for (var i = 0; i < 97; i++) {
            statedatah += '<td>' + data['zwhisdata'].vectorf288change96[i] + '</td>';
        }
        $('#fstatefhtable-data').append(statedatah);

        var dstatedatah = '';
        for (var i = 0; i < 97; i++) {
            dstatedatah += '<td>' + data['zrhisdata'].vectorf288change96[i] + '</td>';
        }
        $('#rstatefhtable-data').append(dstatedatah);

    }

    function getstate(ele) {
        var element = $("#checkbox_fixedb");
        var state = element.attr('checked');
        console.info(ele);
        console.info(ele.checked);
        console.info(state);
    }
</script>

</body>
<!--  /Body -->
</html>


