/* ------------------------------- echart dom元素 ------------------------------- */
var loadGraphs = echarts.init(document.getElementById("graphOfAllLoad"));
var maxLoad = echarts.init(document.getElementById("graphOfMaxLoad"));
var minLoad = echarts.init(document.getElementById("graphOfMinLoad"));
var avgLoad = echarts.init(document.getElementById("graphOfAvgLoad"));
if (holiday != null && holiday != "") {
    var selectDom = document.getElementById("holiday");
    for (var i = 0; i < selectDom.options.length; i++) {
        if (selectDom.options[i].value == holiday){
            selectDom.options[i].selected = true;
        } else {
            selectDom.options[i].selected = false;
        }
    }
} else {
    holiday = document.getElementById("holiday").value;
}
/* ------------------------------- echart 通用设置元素 ------------------------------- */
var color = ['#FF3333',
    '#53FF53',
    '#B15BFF',
    '#68CFE8',
    '#FFDC35',
    '#B00'
];
var max = function (value) {
    var result;
    if (value.max > 0) {
        result = parseInt(value.max * 1.5);
    } else {
        result = parseInt(value.max / 1.5);
    }
    return result;
};
var toolbox = {
    show: true,
    feature: {
        dataView: {
            readOnly: true,
            optionToContent: function (opt) {
                var axisData = opt.xAxis[0].data;
                var series = opt.series;
                var table = '<table class="dataview"><tbody><tr class="dataview" style="width:50px"><th class="dataview">时间</th>';
                for (var i = 0; i < axisData.length; i++) {
                    table += '<th class="dataview">' + axisData[i] + '</th>';
                }
                table += '</tr>';
                /*contentEditable="true"  如果要求表格可编辑，td后添加这个*/
                for (var j = 0; j < series.length; j++) {
                    table += '<tr class="dataview"><td class="dataview">' + series[j].name + '</td>';
                    for (var z = 0; z < axisData.length; z++) {
                        table += '<td class="dataview">' + series[j].data[z] + '</td>';
                    }
                    table += '</tr>';
                }
                return table;
            }
        }
    },
    right: '10%'
};

/* ------------------------------- echart 负荷曲线option ------------------------------- */
var allOptions = {
    legend:{
        type:'scroll'
    },
    xAxis: {
        boundaryGap: false,
        axisLabel: {
            showMaxLabel: true
        },
        type:'category'
    },
    yAxis: {
        //name: '负荷/MW',
        max: max
    },
    tooltip: {
        trigger: 'axis'
    },
    color: color,
    //series: dataSeries,
    toolbox: toolbox
};

/* ------------------------------- echart 各类型负荷与增长率option ------------------------------- */
var loadOptions = {
    legend: {type:'scroll'},
    xAxis: {

        boundaryGap: false,
        //data: dataXAxis,
        axisLabel: {
            showMaxLabel: true
        },
        type:'category'
    },
    yAxis: [{
        //name: '负荷/MW',
        max: max
    }],
    tooltip: {
        trigger: 'axis'
    },
    color: color,
    toolbox: toolbox
    //series: dataSeries
};

/* ------------------------------- echart 初始化 ------------------------------- */
loadGraphs.setOption(allOptions);
maxLoad.setOption(loadOptions);
minLoad.setOption(loadOptions);
avgLoad.setOption(loadOptions);

/*------------------------------- layui组件------------------------------- */
layui.use(['element', 'laydate', 'form'], function () {

    /* ------------------------------- 组件的对象 ------------------------------- */
    var laydate = layui.laydate;
    var form = layui.form;

    /* ------------------------------- 年范围选择器 ------------------------------- */
    laydate.render({
        elem: '#yearSelect'
        , type: 'year'
        , value: startYear + ' - ' + endYear
        , range: true //或 range: '~' 来自定义分割字符
        , done: function (value, date, endDate) {
            startYear = date.year;
            endYear = endDate.year;
            ajaxForAll();
        }
    });

    /* ------------------------------- 监听下拉菜单选中状态 ------------------------------- */
    form.on('select(holiday)', function (data) {
        holiday = data.value; //得到被选中的值
        ajaxForAll();
    });
    form.render();
});

/* ------------------------------- 页面加载事件 ------------------------------- */
$(function () {
    document.getElementById("currentArea").innerHTML = areaname;
    ajaxForAll();
});

/* ------------------------------- 点击切换到增长率/负荷曲线 ------------------------------- */
function toUpLoad(dom,value){
    var urltemp;
    var echart;
    var unittemp;
    switch (value){
        case 'maxLoad':
            echart = maxLoad;
            break;
        case 'minLoad':
            echart = minLoad;
            break;
        case 'aveLoad':
            echart = avgLoad;
            break;
    }
    if (dom.value == 0) {
        dom.value = 1;
        dom.innerHTML = '增长率曲线';
        unittemp = '增长率/%';
        urltemp = value+'up';
    } else {
        dom.value = 0;
        dom.innerHTML = '负荷曲线';
        unittemp = '负荷/MW';
        urltemp = value;
    }
    changeGraphsByAjax(echart,urltemp,unittemp);
}

/* ------------------------------- 图表的异步加载 ------------------------------- */
function changeGraphsByAjax(echartDom, url , unit) {
    var reData = {
        "area": area,
        "startYear": startYear,
        "endYear": endYear,
        "holiday": holiday
    };
    echartDom.showLoading();
    $.ajax({
        type: 'post',
        async: 'true',
        url: pathContent + '/periodicity/holiday/' + url,
        data: reData,
        success: function (result) {
            echartDom.hideLoading();
            if (result === null || result === ""){
                echartDom.clear();
               echartDom.setOption(allOptions);
            } else {
                echartDom.clear();
                echartDom.setOption(allOptions);
                echartDom.setOption({
                    xAxis:{
                        data:result.xAxis
                    },
                    yAxis:{
                      name: unit
                    },
                    legend:{
                        type: 'scroll',
                        data:result.legend
                    },
                    series:result.series
                });
            }
        }
    })
}
/* ------------------------------- 通用调用事件 ------------------------------- */
function ajaxForAll() {
    changeGraphsByAjax(loadGraphs,"allLoad",'负荷/MW');
    changeGraphsByAjax(maxLoad,"maxLoadup",'增长率/%');
    changeGraphsByAjax(minLoad,"minLoadup",'增长率/%');
    changeGraphsByAjax(avgLoad,"aveLoadup",'增长率/%');
}