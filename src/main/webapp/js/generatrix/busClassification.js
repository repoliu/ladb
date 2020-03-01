$("body").on("mousedown", ".layui-tree li a cite", function () {
    $(".layui-tree li a cite").css('color', 'black');
    $(this).css('color', 'red')
});
var defaultDate = null;

//因为这个地区树和别的页面的都不一样，所以这里设置的是进入页面必须点击地区树才进行查询并展示
function clickTree() {
    //这个是查询饼图的方法
    urlName("/busClassificationController/selectData");
    var dateFuncton3 = $("#dateFuncton3").val();
    //这个是查询柱状图的方法
    selectBusloadTypeSupport(areaname,dateFuncton3);
}

function clickTreeTwo() {
    //这个是查询饼图的方法
    urlName("/busClassificationController/selectDataTwo");
    var dateFuncton3 = $("#dateFuncton3").val();
    //这个是查询柱状图的方法
    selectBusloadTypeSupport(areaname,dateFuncton3);
}

$(function () {
    busName = $("#busName").val();
    defaultDate = $("#defaultDate").val();
    layuiDateSelection(defaultDate);







    $.ajax({
        type: "post", async: true, url: pathContent + "/busClassificationController/selectDcc5",    //请求发送到ShowInfoIndexServlet处
        data: {"areaName": busName},
        dataType: "json",
        success: function (result) {
            $("#selectedArea").html("选择的地区:" + busName);
            areaname = busName;
/*            var dateFuncton3 = $("#dateFuncton3").val();
            selectBusloadTypeSupport(busName, dateFuncton3);*/
            if (result.dcc == "dcc1_descr") {
                clickTree();
            } else {
                clickTreeTwo();
            }
        }
    });

})

function dateFuncton0() {
    var dateFuncton0 = $("#dateFuncton0").val();
    layuiDateSelection(dateFuncton0);
    selectBusloadTypeSupport(areaname, dateFuncton0);
}

function dateFuncton1() {
    var dateFuncton1 = $("#dateFuncton1").val();
    layuiDateSelection(dateFuncton1);
    selectBusloadTypeSupport(areaname, dateFuncton1);
}

function dateFuncton2() {
    var dateFuncton2 = $("#dateFuncton2").val();
    layuiDateSelection(dateFuncton2);
    selectBusloadTypeSupport(areaname, dateFuncton2);
}

function dateFuncton3() {
    var dateFuncton3 = $("#dateFuncton3").val();
    layuiDateSelection(dateFuncton3);
    selectBusloadTypeSupport(areaname, dateFuncton3);
}

function dateFuncton4() {
    var dateFuncton4 = $("#dateFuncton4").val();
    layuiDateSelection(dateFuncton4);
    selectBusloadTypeSupport(areaname, dateFuncton4);
}

function dateFuncton5() {
    var dateFuncton5 = $("#dateFuncton5").val();
    layuiDateSelection(dateFuncton5);
    selectBusloadTypeSupport(areaname, dateFuncton5);
}

function dateFuncton6() {
    var dateFuncton6 = $("#dateFuncton6").val();
    layuiDateSelection(dateFuncton6);
    selectBusloadTypeSupport(areaname, dateFuncton6);
}

/*这个是母线分类查询下边的柱状图查询  */
function selectBusloadTypeSupport(areaName, date) {
    var option = {
        tooltip: {
            show: true,
            trigger: 'axis' //坐标轴触发提示框，多用于柱状、折线图中
        },

        legend: {
            /*图表上方的类别显示，好像这个没用，是下边通过ajax获取的*/
            show: true,
            data: []
        },
/*        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },*/
        color: [],
        xAxis: {     //X轴
            type: 'category',
            data: []    //先设置数据值为空，后面用Ajax获取动态数据填入
        },
        yAxis: [{   //Y轴（这里我设置了两个Y轴，左右各一个）
            type: 'value',
            name: '',//左边y轴上方显示的字
            axisLabel: {formatter: '{value}' /*控制输出格式*/}
        }],
        series: []
    };
    var busMain0 = echarts.init(document.getElementById('busMain0'));
    var busMain1 = echarts.init(document.getElementById('busMain1'));
    var busMain2 = echarts.init(document.getElementById('busMain2'));
    var listList = [];
    listList.push(busMain0);
    listList.push(busMain1);
    listList.push(busMain2);
    $.ajax({
        type: "post", async: true, url: pathContent + "/busClassificationController/selectBusloadTypeSupport",    //请求发送到ShowInfoIndexServlet处
        data: {"areaName": areaName, "date": date},
        dataType: "json",
        success: function (result) {
            $("#dateFuncton0").html(result[0].data[0]);
            $("#dateFuncton1").html(result[0].data[1]);
            $("#dateFuncton2").html(result[0].data[2]);
            $("#dateFuncton3").html(result[0].data[3]);
            $("#dateFuncton4").html(result[0].data[4]);
            $("#dateFuncton5").html(result[0].data[5]);
            $("#dateFuncton6").html(result[0].data[6]);
            $("#dateFuncton0").val(result[0].data[0]);
            $("#dateFuncton1").val(result[0].data[1]);
            $("#dateFuncton2").val(result[0].data[2]);
            $("#dateFuncton3").val(result[0].data[3]);
            $("#dateFuncton4").val(result[0].data[4]);
            $("#dateFuncton5").val(result[0].data[5]);
            $("#dateFuncton6").val(result[0].data[6]);

            for (var z = 0; z < listList.length; z++) {


                var datesXiaoFengGu = [];        //时间数组
                var dataLegendXiaoFengGu = [];        //图例，上边显示的样例
                var dataXiaoFengGu = [];         //曲线图的数据
                listList[z].showLoading();    //数据加载完之前先显示一段简单的loading动画
                if (result[z] !== null&&result[z].hengZhou!=null) {
                    for (var j = 0; j < result[z].hengZhou.length; j++) {
                        datesXiaoFengGu.push(result[z].hengZhou[j]);
                    }
                    dataLegendXiaoFengGu.push(result[z].legend);

                    dataXiaoFengGu.push(result[z].series);
                    listList[z].hideLoading();    //隐藏加载动画
                    listList[z].setOption({        //载入数据
                        legend: {data: dataLegendXiaoFengGu},
                       /* title: {//这个是整个图左上角的显示
                            text: dataLegendXiaoFengGu[0].split("年")[1]
                        },*/
                        xAxis: {data: datesXiaoFengGu},
                        series: dataXiaoFengGu,
                        yAxis: [
                            {   //Y轴（这里我设置了两个Y轴，左右各一个）
                                type: 'value',
                                name: dataLegendXiaoFengGu[0].split("年")[1],//左边y轴上方显示的字
                                data: ['33','33','33','33','33','33','33','33','33','33','33','33'],
                                axisLabel: {formatter: '{value}' /*控制输出格式*/}
                            }
                        ],
                        color: [result[z].color[z]]//因为就3个柱状图，我就在后端放了3个颜色
                    });
                } else {
                    listList[z].hideLoading();
                }
            }
        }
    });
     for (var z = 0; z < listList.length; z++) {
             listList[z].setOption(option, true);    //载入图表

     }
}

//这个是时间选择框
function layuiDateSelection(layuiDateSelection) {
    var dateSerlect=layuiDateSelection.substr(0,4);
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //年选择器
        laydate.render({
            elem: '#layuiDateSelection'
            ,type: 'year'
            , value: dateSerlect
            , done: function (value) {
                selectBusloadTypeSupport(areaname, value);
            }
        });
    });
}

//这是查饼图的方法
function urlName(urlName) {


    var main0 = echarts.init(document.getElementById('main0'));
    var main1 = echarts.init(document.getElementById('main1'));
    var main2 = echarts.init(document.getElementById('main2'));
    var main3 = echarts.init(document.getElementById('main3'));
    var listList = [];
    listList.push(main0);
    listList.push(main1);
    listList.push(main2);
    listList.push(main3);
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
        data: {"areaName": areaname},
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
                                radius: '65%',//这个是图的大小
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
            } else {
                layui.use(['element', 'layer'], function () {
                    var layer = layui.layer;
                    layer.msg('该地区没有数据！', {icon: 3});//1：对勾，2：X，3：问号
                });
            }
        }
    });
    //循环载入图表
    for (var z = 0; z < listList.length; z++) {
        listList[z].setOption(option, true);
    }
    main0.on('click', function (params) {
        clickMethods();
    });
    main1.on('click', function (params) {
        clickMethods();
    });
    main2.on('click', function (params) {
        clickMethods();
    });
    main3.on('click', function (params) {
        clickMethods();
    });
}
//这个是改变layuiId，用来跳转table页的
function clickMethods() {
    var element = layui.element;
    layid = 'busJumpId';
    jumpTabTemp = 'busJumpId';//此变量用来影响因素分析- 全年气象因素分析- 页面的柱状图点击事件，点击后进入气象因素分析table页时所改变lay-id时所用的
    element.tabChange('generatrix', layid);
    factorLoading = layer.load(1);
}

//这个后来没有用到
function jumpHtml(params) {//这个是之前点击后弹出框显示母线数据的，现在是跳转页面，所以这个现在这个方法没有用到
    layui.use(['element', 'layer'], function () {
        var layer = layui.layer;
        layer.open({
            type: 2,
            title: '降雨量查询',
            shadeClose: true,
            shade: 0.8,
            area: ['1260px', '650px'],//这个1030是宽度，一共传过来5列数据，每一列数据的宽度是200px，200这个数是后台传过来的
            content: '/busClassificationController/busJump?areaname=' + areaname + "&paramsName=" + params.name
        });
    });
}