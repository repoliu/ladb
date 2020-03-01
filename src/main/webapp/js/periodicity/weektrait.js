var maxloadRate = "";
var minload = "";
var aveloadRate = "";
var minloadRate = "";
var maxdiffer = "";
var maxdifferRate = "";
var avediffer = "";
var avedifferRate = "";
var elementDom1 = document.getElementById('weekMain1');
var elementDom2 = document.getElementById('weekMain2');
elementDom1.style.width = (document.body.clientWidth - 200) / 2;
elementDom2.style.width = (document.body.clientWidth - 200) / 2;
var date = $("#weektimeSelect").val();


var point = $("input[name='point']:checked").val();
/*$("button").click(function () {
    $("button").addClass("layui-btn-primary");
    $(this).toggleClass("layui-btn-primary");
});*/


function updatePage(value,flag) {
    //button,timeSelect
    //此处点击button后value传进来的是带“，”的两个日期 20161205-20161211
    //选择时间选择框后后传入的是2016-12-11，因此要处理的是时间选择框的时间
    var dateArray = [];
    var domId1 = $("#id1");
    var weektimeSelect = $("#weektimeSelect");
   if(flag=="button"){
    if (value !== null && value !== "") {
        dateArray = value.split("-");
        domId1.html(dateArray[0]);
        weektimeSelect.val(dateArray[0].substring(0,4)+"-"+dateArray[0].substring(4,6)+"-"+dateArray[0].substring(6,8));
    } else {
        value = $("#week1").val();
        dateArray = value.split("-");
        domId1.html(dateArray[0].substring(0,4)+"-"+dateArray[0].substring(4,6)+"-"+dateArray[0].substring(6,8));
    }
    updateButton(value);
    graph(value);
    progress(value);
       }else if(flag=="timeSelect"){
       if (value !== null && value !== "") {
           domId1.html($("#week2").val().split("-")[0]);
           weektimeSelect.val(value);
       } else {
           value = $("#week2").val().split("-")[0];
           domId1.html(value);
       }
       graph(value);
       progress(value);
   }


}

function updateButton(value){
    //console.log("======"+value.split("-")[0])
    $.ajax({    //使用JQuery内置的Ajax方法
        type: "post",        //post请求方式
        async: true,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: pathContent + "/periodicity/week/weekInterval",//请求发送到ShowInfoIndexServlet处
        data: {'mdate': value.split("-")[0]},
        success: function (result) {
            for (var i = 0; i < 5; i++) {
                var domWeekI = $("#week" + i);
                domWeekI.html(result[i][0].substring(4, 6) + "." + result[i][0].substring(6, 8) + "-" + result[i][1].substring(4, 6) + "." + result[i][1].substring(6, 8));
                domWeekI.val(result[i][0] + "-" + result[i][1]);

            }
        },
        error: function (errorMsg) {
            //console.log("日期选择器：" + errorMsg);
            //请求失败时执行该函数
        }
    });

}


//为地图加载的js
$(function () {
    $('#ChinaMap1').SVGMap({
        mapName: 'china',
        mapWidth: 230,
        mapHeight: 200
    });
    elementDom1.style.width = (document.body.clientWidth - 200) / 2;
    elementDom2.style.width = (document.body.clientWidth - 200) / 2;
    graph(null);
    progress(null);
});
//日期选择器js
layui.use('laydate', function () {
    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#weektimeSelect'//指定元素
        , format: 'yyyy-MM-dd', //可任意组合
        // min: minDate,
        // max: maxDate,
        value: buttonMaxDate.substring(0,4)+"-"+buttonMaxDate.substring(4,6)+"-"+buttonMaxDate.substring(6,8),
        done: function (value) {
            $.ajax({    //使用JQuery内置的Ajax方法
                type: "post",        //post请求方式
                async: true,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                url: pathContent + "/periodicity/week/weekInterval",//请求发送到ShowInfoIndexServlet处
                data: {'mdate': value.split("-")[0]+value.split("-")[1]+value.split("-")[2]},
                success: function (result) {
                    for (var i = 0; i < 5; i++) {
                        var domWeekI = $("#week" + i);
                        domWeekI.html(result[i][0].substring(4, 6) + "." + result[i][0].substring(6, 8) + "-" + result[i][1].substring(4, 6) + "." + result[i][1].substring(6, 8));
                        domWeekI.val(result[i][0] + "-" + result[i][1]);
                        /*此处是给按钮加的禁用
                        if (result[i][0] < buttonMinDate) {
                            domWeekI.addClass("layui-btn-disabled");
                            domWeekI.html(result[i][0].substring(4, 6) + "." + result[i][0].substring(6, 8) + "-" + result[i][1].substring(4, 6) + "." + result[i][1].substring(6, 8));
                            domWeekI.val(result[i][0] + "-" + result[i][1]);
                            document.getElementById("week" + i).disabled = "disabled";
                        } else {
                            domWeekI.html(result[i][0].substring(4, 6) + "." + result[i][0].substring(6, 8) + "-" + result[i][1].substring(4, 6) + "." + result[i][1].substring(6, 8));
                            domWeekI.val(result[i][0] + "-" + result[i][1]);
                        }*/
                    }
                    updatePage(value,'timeSelect');
                },
                error: function (errorMsg) {
                    //console.log("日期选择器：" + errorMsg);
                    //请求失败时执行该函数
                }
            });
        }
    });
});


function progress(dateArray) {

//拿到上部分进度条数据
    $.ajax({    //使用JQuery内置的Ajax方法
        type: "post",        //post请求方式
        async: true,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: pathContent + "/periodicity/week/selectWeektraitCtl",//请求发送到ShowInfoIndexServlet处
        data: {'area': area, 'date': dateArray},        //请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
        dataType: "json",        //返回数据形式为json
        success: function (result) {

            if (result[0] !== null && result.length > 0) {
                for (i = 0; i < result.length; i++) {
                    //$("#id1").html(result[i].startDay);
                    $("#id2").html(result[i].maxload.toFixed(2));
                    $("#id3").html(result[i].minload.toFixed(2));
                    $("#id4").html(result[i].aveload.toFixed(2));
                    $("#id5").html(result[i].minloadrate.toFixed(2) + "%");
                    $("#id6").html(result[i].maxdiffer.toFixed(2));
                    $("#id7").html(result[i].maxdifferrate.toFixed(2) + "%");
                    $("#id8").html(result[i].avediffer.toFixed(2));
                    $("#id9").html(result[i].avedifferrate.toFixed(2) + "%");
                    maxloadRate = result[i].maxload / 40000 * 100 + "%";
                    minload = result[i].minload / 40000 * 100 + "%";
                    aveloadRate = result[i].aveload / 40000 * 100 + "%";
                    minloadRate = result[i].minloadrate + "%";
                    maxdiffer = result[i].maxdiffer / 40000 * 100 + "%";
                    maxdifferRate = result[i].maxdifferrate + "%";
                    avediffer = result[i].avediffer / 40000 * 100 + "%";
                    avedifferRate = result[i].avedifferrate + "%";
                    //进度条js
                    layui.use('element', function () {
                        var element = layui.element;
                        element.init();
                        element.progress('maxload', maxloadRate);
                        element.progress('minload', minload);
                        element.progress('aveload', aveloadRate);
                        element.progress('minloadrate', minloadRate);
                        element.progress('maxdiffer', maxdiffer);
                        element.progress('maxdifferrate', maxdifferRate);
                        element.progress('avediffer', avediffer);
                        element.progress('avedifferrate', avedifferRate);
                    });
                }
            } else {
                $("#id2").html("");
                $("#id3").html("");
                $("#id4").html("");
                $("#id5").html("");
                $("#id6").html("");
                $("#id7").html("");
                $("#id8").html("");
                $("#id9").html("");
                maxloadRate = "0%";
                minload = "0%";
                aveloadRate = "0%";
                minloadRate = "0%";
                maxdiffer = "0%";
                maxdifferRate = "0%";
                avediffer = "0%";
                avedifferRate = "0%";
                //进度条js
                layui.use('element', function () {
                    var element = layui.element;
                    element.init();
                    element.progress('maxload', maxloadRate);
                    element.progress('minload', minload);
                    element.progress('aveload', aveloadRate);
                    element.progress('minloadrate', minloadRate);
                    element.progress('maxdiffer', maxdiffer);
                    element.progress('maxdifferrate', maxdifferRate);
                    element.progress('avediffer', avediffer);
                    element.progress('avedifferrate', avedifferRate);
                });
            }
        },
        error: function (errorMsg) {
            //console.log("errorMsg:"+errorMsg)
            //请求失败时执行该函数
        }
    })
}

function graph(dateArray) {
    var tems = [];        //曲线值数组
    var dates = [];        //时间数组
    var legend = [];//放的典型工作日图例的数组
    var tems1 = [];        //曲线值数组
    var dates1 = [];        //时间数组
    var legend1;
    legend1 = [];//放的典型休息日图例的数组


// 基于准备好的dom，初始化echarts实例
    var domWeekMain1 = $("#weekMain1");
    echarts.dispose(document.getElementById('weekMain1'));
    echarts.dispose(document.getElementById('weekMain2'));
    var myChart = echarts.init(document.getElementById('weekMain1'));
    //domWeekMain1.height('410px');
    //domWeekMain1.width((document.body.clientWidth-200)/2);
// 指定图表的配置项和数据
    var option = {
        title: {
            text: '典型工作日',
                textStyle:{
                fontWeight:'normal'
                }
        },
        tooltip: {
            trigger: 'axis' //坐标轴触发提示框，多用于柱状、折线图中
        },
        dataZoom: [
            {
                type: 'slider',    //支持鼠标滚轮缩放
                start: 0,            //默认数据初始缩放范围为10%到90%
                end: 100
            },
            {
                type: 'inside',    //支持单独的滑动条缩放
                start: 0,            //默认数据初始缩放范围为10%到90%
                end: 100
            }
        ],
        legend: {    //图表上方的类别显示
            show: false,
            data: []
        },
        color: [
            '#FF3333',    //典型工作日曲线颜色
            '#53FF53',
            '#B15BFF',
            '#68CFE8',
            '#FFDC35'
        ],
        toolbox: {    //工具栏显示
            show: false,
            feature: {
                saveAsImage: {},//显示“另存为图片”工具
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {}
            },
            right: '10%'
        },

        xAxis: {    //X轴
            data: []  //先设置数据值为空，后面用Ajax获取动态数据填入
        },
        yAxis: [    //Y轴（这里我设置了两个Y轴，左右各一个）
            {
                name:'负荷/MW',
                //第一个（左边）Y轴，yAxisIndex为0
                type: 'value'
                /* max: 120,
                min: -40, */
                /* axisLabel: {
                     formatter: '{value} MW'    //控制输出格式
                 }*/
            }

        ],
        series: [    //系列（内容）列表
            {
                name: [],
                type: 'line',    //折线图表示（生成温度曲线）
                symbol: 'emptycircle',    //设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
                data: []        //数据值通过Ajax动态获取
            }
        ]
    };

    myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画


    $.ajax({    //使用JQuery内置的Ajax方法
        type: "post",        //post请求方式
        async: true,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: pathContent + "/periodicity/week/commonGraph",    //请求发送到ShowInfoIndexServlet处
        data: {'point': point, 'area': area, 'date': dateArray, 'daytype': 1},
        dataType: "json",        //返回数据形式为json
        success: function (result) {

            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result !== null && result.length > 0) {

                for (var i = 0; i < result.length; i++) {
                    tems.push(result[i].series);
                    legend.push(result[i].legend);
                }
                for (var j = 0; j < result[0].hengZhou.length; j++) {
                    dates.push(result[0].hengZhou[j]);
                }
                myChart.hideLoading();    //隐藏加载动画

                myChart.setOption({        //载入数据
                    legend: {
                        data: legend
                    },
                    xAxis: {
                        minInterval:12,
                        data: dates,    //填入X轴数据
                        axisLabel:{//显示最后一个点
                            showMaxLabel:true
                        }
                    },
                    series: tems
                });
            }
            else {
                //返回的数据为空时显示提示信息
               // console.log("图表请求数据为空，可能服务器暂未录入近七天的观测数据，您可以稍后再试！");
                myChart.hideLoading();
            }
            //曲线图出现后再为日指标赋值
            dayIndex(point,1);
        },
        error: function () {
            //请求失败时执行该函数
           // console.log("图表请求数据失败，可能是服务器开小差了");
            myChart.hideLoading();
        }
    });
    myChart.setOption(option);    //载入图表

//================================================================================
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('weekMain2'));
    var domWeekMain2 = $("#weekMain2");
// 指定图表的配置项和数据
    var option1 = {
        title: {
            text: '典型休息日',
            textStyle:{
                fontWeight:'normal'
            }
        },
        tooltip: {
            trigger: 'axis' //坐标轴触发提示框，多用于柱状、折线图中
        },
        dataZoom: [
            {
                type: 'slider',    //支持鼠标滚轮缩放
                start: 0,            //默认数据初始缩放范围为10%到90%
                end: 100
            },
            {
                type: 'inside',    //支持单独的滑动条缩放
                start: 0,            //默认数据初始缩放范围为10%到90%
                end: 100
            }
        ],
        legend: {    //图表上方的类别显示
            show: false,
            data: []
        },
        color: [
            '#FF3333',    //典型工作日曲线颜色
            '#53FF53',
            '#B15BFF',
            '#68CFE8',
            '#FFDC35'
        ],
        toolbox: {    //工具栏显示
            show: false,
            feature: {
                saveAsImage: {},//显示“另存为图片”工具
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {}
            },
            right: '10%'
        },

        xAxis: {    //X轴
            data: []   //先设置数据值为空，后面用Ajax获取动态数据填入
        },
        yAxis: [    //Y轴（这里我设置了两个Y轴，左右各一个）
            {
                //第一个（左边）Y轴，yAxisIndex为0
                name:'负荷/MW',
                type: 'value'
            }

        ],
        series: [    //系列（内容）列表
            {
                name: [],
                type: 'line',    //折线图表示（生成温度曲线）
                symbol: 'emptycircle',    //设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
                data: []        //数据值通过Ajax动态获取
            }
        ]
    };

    myChart1.showLoading();    //数据加载完之前先显示一段简单的loading动画
    $.ajax({    //使用JQuery内置的Ajax方法
        type: "post",        //post请求方式
        async: true,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: pathContent + "/periodicity/week/commonGraph",    //请求发送到ShowInfoIndexServlet处
       data: {'point': point, 'area': area, 'date': dateArray, 'daytype': 0},
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result !== null && result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    tems1.push(result[i].series);
                    legend1.push(result[i].legend);
                }
                for (var j = 0; j < result[0].hengZhou.length; j++) {
                    dates1.push(result[0].hengZhou[j]);
                }
                myChart1.hideLoading();    //隐藏加载动画

                myChart1.setOption({        //载入数据
                    legend: {
                        data: legend1
                    },
                    xAxis: {
                        data: dates1 ,  //填入X轴数据
                        axisLabel:{//显示最后一个点
                            showMaxLabel:true
                        }
                    },
                    series: tems1
                });
            }
            else {
                //返回的数据为空时显示提示信息
               // console.log("图表请求数据为空，可能服务器暂未录入近七天的观测数据，您可以稍后再试！");
                myChart1.hideLoading();
            }
            //曲线图出现后再为日指标赋值
            dayIndex(point,0);
        },
        error: function () {
            //请求失败时执行该函数
            //console.log("图表请求数据失败，可能是服务器开小差了");
            myChart1.hideLoading();
        }
    });
    myChart1.setOption(option1);    //载入图表

}

function dayIndex(point,daytype) {
    $.ajax({    //使用JQuery内置的Ajax方法
        type: "post",        //post请求方式
        async: true,        //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: pathContent + "/periodicity/week/selectDaytraitCtl",//请求发送到ShowInfoIndexServlet处
        data: {
            'point':point,
            'area': area,
            'daytype':daytype
        },        //请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
        dataType: "json",        //返回数据形式为json
        success: function (result) {

            if (daytype === 1) {
                /* var domTable1 = $("#table1");
                 domTable1.empty();
                 domTable1.append(
                     "<tr>" +
                     " <th class='titleColor'>日期</th>" +
                     "<th class='titleColor'>日最大负荷</th>" +
                     " <th class='titleColor'>日最小负荷</th>" +
                     " <th class='titleColor'>早高峰负荷</th>" +
                     " <th class='titleColor'>晚高峰负荷</th>" +
                     " </tr>"
                 );*/
                if (result['1'] != null && result['1'].length > 0){
                    for (var j = 0; j < result['1'].length; j++) {
                        /*domTable1.append(
                            "<tr>" +
                            "<td>" + result['1'][j].mdate + "</td>" +
                            "<td style='text-align:left'>" + result['1'][j].maxload.toFixed(2) + "(" + result['1'][j].maxtime + ")</td>" +
                            "<td style='text-align:left'>" + result['1'][j].minload.toFixed(2) + "(" + result['1'][j].mintime + ")</td>" +
                            "<td style='text-align:left'>" + result['1'][j].fmmaxload.toFixed(2) + "(" + result['1'][j].fmmaxtime + ")</td>" +
                            "<td style='text-align:left'>" + result['1'][j].pmmaxload.toFixed(2) + "(" + result['1'][j].pmmaxtime + ")</td>" +
                            "</tr>"
                        )*/
                        $("#maxload").html(result['1'][j].maxload.toFixed(2) + "(" + result['1'][j].maxtime + ")");
                        $("#minload").html(result['1'][j].minload.toFixed(2) + "(" + result['1'][j].mintime + ")");
                        $("#fmmaxload").html(result['1'][j].fmmaxload.toFixed(2) + "(" + result['1'][j].fmmaxtime + ")");
                        $("#pmmaxload").html(result['1'][j].pmmaxload.toFixed(2) + "(" + result['1'][j].pmmaxtime + ")");
                        var maxloadRateDay1 = result['1'][j].maxload / 40000 * 100 + "%";
                        var minloadRateDay1 = result['1'][j].minload / 40000 * 100 + "%";
                        var fmmaxloadRateDay1 = result['1'][j].fmmaxload / 40000 * 100 + "%";
                        var pmmaxloadRateDay1 = result['1'][j].pmmaxload / 40000 * 100 + "%";
                        layui.use('element', function () {
                            var element = layui.element;
                            element.init();
                            element.progress('maxloadDay1', maxloadRateDay1);
                            element.progress('minloadDay1', minloadRateDay1);
                            element.progress('fmmaxloadDay1', fmmaxloadRateDay1);
                            element.progress('pmmaxloadDay1', pmmaxloadRateDay1);
                        });
                    }
            }else{
                    $("#maxload").html('');
                    $("#minload").html('');
                    $("#fmmaxload").html('');
                    $("#pmmaxload").html('');
                    var maxloadRateDay1 = "0%";
                    var minloadRateDay1 = "0%";
                    var fmmaxloadRateDay1 = "0%";
                    var pmmaxloadRateDay1 = "0%";
                    layui.use('element', function () {
                        var element = layui.element;
                        element.init();
                        element.progress('maxloadDay1', maxloadRateDay1);
                        element.progress('minloadDay1', minloadRateDay1);
                        element.progress('fmmaxloadDay1', fmmaxloadRateDay1);
                        element.progress('pmmaxloadDay1', pmmaxloadRateDay1);
                    });
                }

            }
            if (daytype === 0) {
                /*var domTable2 = $("#table2");
                domTable2.empty();
                domTable2.append(
                    "<tr>" +
                    " <th class='titleColor'>日期</th>" +
                    "<th class='titleColor'>日最大负荷</th>" +
                    " <th class='titleColor'>日最小负荷</th>" +
                    " <th class='titleColor'>早高峰负荷</th>" +
                    " <th class='titleColor'>晚高峰负荷</th>" +
                    " </tr>"
                );*/
                if (result['0'] != null && result['0'].length > 0){
                for (var j = 0; j < result['0'].length; j++) {
                    /*domTable2.append(
                        "<tr>" +
                        "<td>" + result['0'][j].mdate + "</td>" +
                        "<td>" + result['0'][j].maxload.toFixed(2) + "(" + result['0'][j].maxtime + ")</td>" +
                        "<td>" + result['0'][j].minload.toFixed(2) + "(" + result['0'][j].mintime + ")</td>" +
                        "<td>" + result['0'][j].fmmaxload.toFixed(2) + "(" + result['0'][j].fmmaxtime + ")</td>" +
                        "<td>" + result['0'][j].pmmaxload.toFixed(2) + "(" + result['0'][j].pmmaxtime + ")</td>" +
                        "</tr>"
                    )*/

                    $("#maxload2").html(result['0'][j].maxload.toFixed(2) + "(" + result['0'][j].maxtime + ")");
                    $("#minload2").html(result['0'][j].minload.toFixed(2) + "(" + result['0'][j].mintime + ")");
                    $("#fmmaxload2").html(result['0'][j].fmmaxload.toFixed(2) + "(" + result['0'][j].fmmaxtime + ")");
                    $("#pmmaxload2").html(result['0'][j].pmmaxload.toFixed(2) + "(" + result['0'][j].pmmaxtime + ")");
                    var maxloadRateDay0 = result['0'][j].maxload / 40000 * 100 + "%";
                    var minloadRateDay0 = result['0'][j].minload / 40000 * 100 + "%";
                    var fmmaxloadRateDay0 = result['0'][j].fmmaxload / 40000 * 100 + "%";
                    var pmmaxloadRateDay0 = result['0'][j].pmmaxload / 40000 * 100 + "%";
                    layui.use('element', function () {
                        var element = layui.element;
                        element.init();
                        element.progress('maxloadDay0', maxloadRateDay0);
                        element.progress('minloadDay0', minloadRateDay0);
                        element.progress('fmmaxloadDay0', fmmaxloadRateDay0);
                        element.progress('pmmaxloadDay0', pmmaxloadRateDay0);
                    });
                }
                }else{
                    $("#maxload2").html('');
                    $("#minload2").html('');
                    $("#fmmaxload2").html('');
                    $("#pmmaxload2").html('');
                    var maxloadRateDay0 = "0%";
                    var minloadRateDay0 = "0%";
                    var fmmaxloadRateDay0 = "0%";
                    var pmmaxloadRateDay0 = "0%";
                    layui.use('element', function () {
                        var element = layui.element;
                        element.init();
                        element.progress('maxloadDay0', maxloadRateDay0);
                        element.progress('minloadDay0', minloadRateDay0);
                        element.progress('fmmaxloadDay0', fmmaxloadRateDay0);
                        element.progress('pmmaxloadDay0', pmmaxloadRateDay0);
                    });
                }
            }
        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            //console.log(errorMsg);
        }
    })

}