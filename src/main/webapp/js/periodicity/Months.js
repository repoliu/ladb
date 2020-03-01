var queRenShiJain = $("#shiJianZu").val();    //在jsp页面保存的时间
var id = $("input[name='id']:checked").val();    //id
var dianShu = $("input[name='point']:checked").val();   //96点或者288点
var xianZhiMax = $("#timeMax").val();
var xianZhiMin = $("#timeMin").val();
areaname = $("#areaname").val();
var domOne = $("#one");
var domTwo = $("#two");
var domThree = $("#three");
var domFour = $("#four");
var domFive = $("#five");

//这个是5个时间按钮点击后分别的方法，进行重新加载
function one() {
    var oneButton = domOne.val();
    load(oneButton);
}
function two() {
    var twoButton = domTwo.val();
    load(twoButton);
}
function three() {
    var threeButton = domThree.val();
    load(threeButton);
}
function four() {
    var fourButton = domFour.val();
    load(fourButton);
}
function five() {
    var fiveButton = domFive.val();
   load(fiveButton);
}
function  load(queRenShiJain) {
    $('#months').load(pathContent + '/months/day/load', {
        "area": area,
        "queRenShiJain": queRenShiJain,
        "dianShu": dianShu
    });
}


/*----------------------------------------------------------------------时间选择框------------------------------------------------------------        */
layui.use('laydate', function () {
    var laydate = layui.laydate;
    laydate.render({
        type: 'month',   //只要年月
        elem: '#maxSelect', //指定元素
        format: 'yyyy-MM',   //传到后台的格式
        //min: xianZhiMin, //这俩是时间选择框的最大时间和最小时间，现在是没有数据的时间也能选择，只是选择之后没有显示的东西
        //max: xianZhiMax,
        value: queRenShiJain,
        done: function (value) {
            queRenShiJain=value;
            //时间选择框选择之后进行后台的load方法，进行重新加载
            /*---------进度条数据-------------  */
            jinDuTiao(queRenShiJain, area, dianShu);
            /*---------折线图-------------------*/
            quXianTu(queRenShiJain, area, dianShu);
        }
    });
});

/*---------------------------------------------------------------------------进行加载---------------------------------------------------------------------------------------------------*/
$(function () {
    areaname = $("#areaname").val();
    document.getElementById("currentArea").innerHTML = areaname;
    /*---------进度条数据-------------  */
    jinDuTiao(queRenShiJain, area, dianShu);
    /*---------折线图-------------------*/
    quXianTu(queRenShiJain, area, dianShu);
    /*---------这个是确定地图大小的-----*/
    $('#ChinaMap2').SVGMap({
        mapName: 'china',
        mapWidth: 220,
        mapHeight: 200
    });
})

/*--------------------------------------------------------------------------以下是公共方法----------------------------------------------------------------------------------------*/
/*------------曲线图公共方法---------------------------*/
function quXianTu(queRenShiJain, area, dianShu) {
    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'axis' //坐标轴触发提示框，多用于柱状、折线图中
        },
        legend: {
            /*图表上方的类别显示，好像这个没用，是下边通过ajax获取的*/
            show: true,
            data: []
        },
        color: ['#B15BFF', '#68CFE8', '#B00', '#FF1493', '#FF9432'],
        xAxis: {    //X轴
            type: 'category',
            data: []    //先设置数据值为空，后面用Ajax获取动态数据填入
        },
        yAxis: [{   //Y轴（这里我设置了两个Y轴，左右各一个）
            type: 'value',
            name: '负荷/MW',//左边y轴上方显示的字
            axisLabel: {formatter: '{value}' /*控制输出格式*/}
        }],
        series: [{  //系列（内容）列表
            animation:false,
            name: [],
            type: 'line',    //折线图表示（生成温度曲线）line   //柱状图表示bar     scatter代表散点图
            symbol: 'emptycircle',    //设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
            data: []        //数据值通过Ajax动态获取
        }]
    };
    $(function () {
//6个曲线图的dom  基于准备好的dom，初始化echarts实例
        var gongZuoRi = echarts.init(document.getElementById('main0'));
        var xiuXiRi = echarts.init(document.getElementById('main3'));
        var zuiDaFuHe = echarts.init(document.getElementById('main1'));
        var zuiXiaoFuHe = echarts.init(document.getElementById('main4'));
        var zuiDaFengGu = echarts.init(document.getElementById('main2'));
        var zuiXiaoFengGu = echarts.init(document.getElementById('main5'));
        var listList = [];
        listList.push(gongZuoRi);
        listList.push(xiuXiRi);
        listList.push(zuiDaFuHe);
        listList.push(zuiXiaoFuHe);
        listList.push(zuiDaFengGu);
        listList.push(zuiXiaoFengGu);
        $.ajax({
            type: "post", async: true, url: pathContent + "/loadHisdataController/selectQuery96",    //请求发送到ShowInfoIndexServlet处
            data: {"queRenShiJain": queRenShiJain, "dianShu": dianShu, "area": area},
            dataType: "json",
            success: function (result) {
                if (result !== null) {
                    for (var z = 0; z < listList.length; z++) {
                        var datesXiaoFengGu = [];        //时间数组
                        var dataLegendXiaoFengGu = [];        //图例，上边显示的样例
                        var dataXiaoFengGu = [];         //曲线图的数据
                        listList[z].showLoading();    //数据加载完之前先显示一段简单的loading动画
                        if (result[z] !== null && result[z].length > 0) {
                            for (var i = 0; i < result[z].length; i++) {
                                dataLegendXiaoFengGu.push(result[z][i].legend);
                                dataXiaoFengGu.push(result[z][i].series);
                            }
                            for (var j = 0; j < result[z][0].hengZhou.length; j++) {
                                datesXiaoFengGu.push(result[z][0].hengZhou[j]);
                            }
                            listList[z].hideLoading();    //隐藏加载动画
                            listList[z].setOption({        //载入数据
                                legend: {data: dataLegendXiaoFengGu},
                                xAxis: {data: datesXiaoFengGu},
                                series: dataXiaoFengGu,
                                color: result[z][0].color
                            });
                        } else {
                            listList[z].hideLoading();
                            //返回的数据为空时显示提示信息
                            //alert("图表请求数据为空，可能服务器暂未录入该月的数据，您可以稍后再试！");
                        }
                    }
                }
            }
        });
        for (var z = 0; z < listList.length; z++) {
            /*   notMerge        可选，是否不跟之前设置的option进行合并，默认为false，即合并。*/
            listList[z].setOption(option,true);    //载入图表
        }
    });
}

/*------------进度条公共方法---------------------------*/
function jinDuTiao(queRenShiJain, area, dianShu) {
    layui.use('element', function () {
        var element = layui.element;
        var maxload = "";
        var minload = "";
        var maxloads = "";
        var minloads = "";
        var aveload = "";
        var aveloads = "";
        var loadrate = "";
        var minloadrate = "";
        var differrate = "";
        var differ = "";
        var differs = "";
        var differratePing = "";
        $.ajax({
            type: "post",
            async: true,
            url: pathContent + "/loadHisdataController/selectMDataOneTwo",
            data: {
                "queRenShiJain": queRenShiJain,
                "area": area,
                "dianShu": dianShu
            },
            dataType: "json",
            success: function (result) {
                if (result.one !== null) {
                    //让5个时间按钮显示时间
                    var ones = result.one;
                    ones = ones.substring(0, 4) + "-" + ones.substring(4, 6);
                    domOne.html(ones);
                    domOne.val(ones);

                    var twos = result.two;
                    twos = twos.substring(0, 4) + "-" + twos.substring(4, 6);
                    domTwo.html(twos);
                    domTwo.val(twos);

                    var threes = result.three;
                    threes = threes.substring(0, 4) + "-" + threes.substring(4, 6);
                    domThree.html(threes);
                    domThree.val(threes);

                    var fours = result.four;
                    fours = fours.substring(0, 4) + "-" + fours.substring(4, 6);
                    domFour.html(fours);
                    domFour.val(fours);

                    var fives = result.five;
                    fives = fives.substring(0, 4) + "-" + fives.substring(4, 6);
                    domFive.html(fives);
                    domFive.val(fives);
                }
                if (result.maxload !== null && result.maxload > 0) {
                    maxload = result.maxload;//月最大负荷
                    maxloads = result.maxloads;
                    minload = result.minload; //月最小负荷
                    minloads = result.minloads;
                    aveload = result.aveload;//月平均负荷
                    aveloads = result.aveloads;
                    loadrate = result.loadrate;// 月负荷率
                    minloadrate = result.minloadrate;// 月最小负荷率
                    differrate = result.differrate;// 月最大峰谷差率
                    differ = result.differ;// 月平均峰谷差
                    differs = result.differs;
                    differratePing = result.differratePing;//平均谷峰差率
                    element.init();
                    /* 这是从后台获取的值在页面上显示 */
                    $("#maxload").html(maxload);
                    $("#minload").html(minload);
                    $("#aveload").html(aveload);
                    $("#loadrate").html(loadrate);
                    $("#minloadrate").html(minloadrate);
                    $("#differrate").html(differrate);
                    $("#differ").html(differ);
                    $("#differratePing").html(differratePing);
                    /* 进度条的显示百分比 */
                    element.progress('maxloads', maxloads);
                    element.progress('minloads', minloads);
                    element.progress('aveloads', aveloads);
                    element.progress('loadrate', loadrate);
                    element.progress('minloadrate', minloadrate);
                    element.progress('differrate', differrate);
                    element.progress('differs', differs);
                    element.progress('differratePing', differratePing);
                } else {
                    $("#maxload").html(maxload);
                    $("#minload").html(minload);
                    $("#aveload").html(aveload);
                    $("#loadrate").html(loadrate);
                    $("#minloadrate").html(minloadrate);
                    $("#differrate").html(differrate);
                    $("#differ").html(differ);
                    $("#differratePing").html(differratePing    );
                    /* 进度条的显示百分比 */
                    element.progress('maxloads', "0%");
                    element.progress('minloads', "0%");
                    element.progress('aveloads', "0%");
                    element.progress('loadrate', "0%");
                    element.progress('minloadrate', "0%");
                    element.progress('differrate', "0%");
                    element.progress('differs', "0%");
                    element.progress('differratePing', "0%");
                    //layer.msg('没有该地区的数据!');
                }
            }
        })
    })
}