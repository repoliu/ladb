
var queRenShiJain = $("#shiJianZu").val();    //在jsp页面保存最大数据是哪一年的时间
var nianXian = $("#nianXian").val();    //在jsp页面保存时间的年限，默认是5年
var areaname = $("#areaname").val();
var shiJianKuangMax = $("#shiJianKuangMax").val();
var shiJianKuangMin = $("#shiJianKuangMin").val();
var anNiu = $("#anNiu").val();
var colour = null;
var  jspHeight=document.body.scrollHeight*0.4;
/*------------------------------这个是4个时间按钮点击后分别的方法，进行重新加载----------------------------------------------------------------------------------------------------------------------------*/
function one() {
    load(1,1,queRenShiJain);
}
function two() {
    load(3,1,queRenShiJain);
}
function three() {
    load(5,1,queRenShiJain);
}
function four() {
    load(10,1,queRenShiJain);
}
function  load(nianXian,anNiu,queRenShiJain) {
    $('#LongTermContent').load(pathContent + '/longTermContonller/load', {
        "areaname": areaname,
        "queRenShiJain": queRenShiJain,
        "nianXian": nianXian,
        "anNiu": anNiu
    });
}

//http://www.scvv.com/sddd?qwe=123
/*------------------------------点击时间选择框进这里边----------------------------------------------------------------------------------------------------------------------------*/
layui.use('laydate', function () {
    var laydate = layui.laydate;
//=======================================================这个是年选择框==================================================================
    laydate.render({
        elem: '#test2'
        , type: 'year'
        ,value:queRenShiJain
        , done: function (value) {
            load(1,0,value);
        }
    });
//----------------------------------------------------这个是年范围选择框---------------------------------------------------
    laydate.render({
        elem: '#test7'
        , type: 'year'
        , range: true
        // , min: shiJianKuangMin
        //  , max: shiJianKuangMax
        , done: function (value) {
            public(value,0);//这个地方的0是用来区分/load/selectQuery方法中查询用电总量的时候，是单年的还是多年的
        }
    });
});


/*-----------------------------------------------------------------以下是进入页面的时候进行加载---------------------------------------------------------------------------------------------------*/
$(function () {
    public(queRenShiJain,nianXian);
});


/*-------------------------------------------------------------------以下是公共方法------------------------------------------------------------------------------------*/
function  public(queRenShiJain,nianXian) {
//========================================================折线图================================================
    var option = {
        tooltip: {
            trigger: 'axis' //坐标轴触发提示框，多用于柱状、折线图中
        },
        legend: {
            /*图表上方的类别显示，好像这个没用，是下边通过ajax获取的*/
            show: true,
            data: []
        },
        color: [ '#B15BFF', '#68CFE8', '#B00', '#FF1493', '#FF9432'],
        xAxis: {     //X轴
            type: 'category',
            data: []    //先设置数据值为空，后面用Ajax获取动态数据填入
        },
        yAxis: [{   //Y轴（这里我设置了两个Y轴，左右各一个）
            type: 'value',
            name: '',//左边y轴上方显示的字
            axisLabel: {formatter: '{value}' /*控制输出格式*/}
        }],
        series: [{  //系列（内容）列表
            name: [],
            type: 'line',    //折线图表示（生成温度曲线）line   //柱状图表示bar
            symbol: 'emptycircle',    //设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
            data: []      //数据值通过Ajax动态获取
        }]
    };
//曲线图的dom  基于准备好的dom，初始化echarts实例

    var twoChart = echarts.init(document.getElementById('longTermmain2'));
    var pentaChart = echarts.init(document.getElementById('longTermmain4'));
    var oneChart = echarts.init(document.getElementById('longTermmain0'));
    var listList = [];
    listList.push(twoChart);
    listList.push(pentaChart);
    listList.push(oneChart);

    $.ajax({
        type: "post", async: true, url: pathContent + "/longTermContonller/selectQuery",    //请求发送到ShowInfoIndexServlet处
        data: {"areaname": areaname, "queRenShiJain": queRenShiJain, "nianXian": nianXian,"anNiu": 0},
        dataType: "json",
        success: function (result) {
            if (result != null) {
                for (var z = 0; z < listList.length; z++) {
                    if (result[z] !== null && result[z].length > 0) {
                        var datesXiaoFengGu = [];        //时间数组
                        var dataLegendXiaoFengGu = [];        //图例，上边显示的样例
                        var dataXiaoFengGu = [];         //曲线图的数据
                        var yZhou = [];         //曲线图的数据
                        listList[z].showLoading();    //数据加载完之前先显示一段简单的loading动画
                        yZhou.push(result[z][0].yaxis);
                        for (var i = 0; i < result[z].length; i++) {
                            dataLegendXiaoFengGu.push(result[z][i].legend);
                            dataXiaoFengGu.push(result[z][i].series);
                        }
                        for (var j = 0; j < result[z][0].hengZhou.length; j++) {
                            datesXiaoFengGu.push(result[z][0].hengZhou[j]);
                        }
                        listList[z].hideLoading();    //隐藏加载动画
                        listList[z].setOption({        //载入数据
                            //legend: {data: dataLegendXiaoFengGu},//不需要显示图例注释了
                            xAxis: {data: datesXiaoFengGu},
                            series: dataXiaoFengGu,
                            yAxis: yZhou
                        });

                    } else {
                        listList[z].hideLoading();
                        //返回的数据为空时显示提示信息
                        //alert("图表请求数据为空，可能服务器暂未录入该月的数据，您可以稍后再试！");
                    }
                }
            } else {
                //layer.msg('没有该地区的数据');
            }
        }
    });
    for (var z = 0; z < listList.length; z++) {
        listList[z].setOption(option,true);    //载入图表【只是在页面显示一样曲线图的框架，这一步没有显示数据】
    }

//==================================================================这个是加载的时候查寻出来的柱状图========================================

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
        color:['#68CFE8', '#FF3333', 'sienna',   '#9400d3',  'violet'],
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
    var threeChart = echarts.init(document.getElementById('longTermmain1'));
    var fourChart = echarts.init(document.getElementById('longTermmain3'));
    var sixChart = echarts.init(document.getElementById('longTermmain5'));
    var columnarList = [];
    columnarList.push(threeChart);
    columnarList.push(fourChart);
    columnarList.push(sixChart);
    $.ajax({
        type: "post", async: true, url: pathContent + "/longTermContonller/selectColumnar",    //请求发送到ShowInfoIndexServlet处
        data: {"areaname": areaname, "queRenShiJain": queRenShiJain, "nianXian": nianXian},
        dataType: "json",
        success: function (result) {
            if (result != null) {
                for (var z = 0; z < columnarList.length; z++) {
                    if (result[z] !== null && result[z].length > 0) {
                        var datesXiaoFengGu = [];        //时间数组
                        var dataLegendXiaoFengGu = [];        //图例，上边显示的样例
                        var dataXiaoFengGu = [];         //曲线图的数据
                        var yZhou = [];         //曲线图的数据
                        columnarList[z].showLoading();    //数据加载完之前先显示一段简单的loading动画
                        yZhou.push(result[z][0].yaxis);
                        for (var i = 0; i < result[z].length; i++) {
                            dataLegendXiaoFengGu.push(result[z][i].series.name);
                            dataXiaoFengGu.push(result[z][i].series);
                        }
                        for (var j = 0; j < result[z][0].hengZhou.length; j++) {
                            datesXiaoFengGu.push(result[z][0].hengZhou[j]);
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
            } else {
                // layer.msg('没有该地区的数据');
            }
        }
    });
    for (var z = 0; z < columnarList.length; z++) {
        columnarList[z].setOption(option1,true);    //载入图表【只是在页面显示一样曲线图的框架，这一步没有显示数据】
    }

//========================================================雷达图================================================
    var seven = echarts.init(document.getElementById('longTermmain6'));
    var values = [];
    var indicators = [];
    var legendData = [];
    option2 = {
        tooltip: {},
        radar: {
            name: {
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            indicator: [],
            center: ['50%', '48%'],    //代表雷达图的位置
            radius: '63%'                 //代表雷达图的大小
        },
        color: [ '#B15BFF', '#68CFE8', '#B00', '#FF1493', '#FF9432','#096','#3398DB','#cc0033'],
        series: [{
            type: 'radar',
            data: values
        }]
    };
    $.ajax({
        type: "post", async: true, url: pathContent + "/longTermContonller/selectRelationSupport",    //请求发送到ShowInfoIndexServlet处
        data: {"areaname": areaname, "queRenShiJain": queRenShiJain, "nianXian": nianXian},
        dataType: "json",
        success: function (result) {
            if (result != null) {
                values = result.dataInfluence;
                indicators = result.indicatorList;
                legendData = result.hengZhou;
                seven.setOption({
                    legend: {
                        data: legendData
                    },
                    series: [{
                        type: 'radar',
                        data: values
                    }],
                    radar: {
                        name: {
                            textStyle: {
                                color: '#fff',
                                backgroundColor: '#999',
                                borderRadius: 3,
                                padding: [3, 5]
                            }
                        },
                        indicator: indicators
                    }
                });
            } else {
                // layer.msg('没有该地区的数据');
            }
        }
    });
    seven.setOption(option2,true);
}

