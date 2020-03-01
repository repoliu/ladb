var pathContent = a;
//日期选择器js
$(function(){
    pathContent = a;
    //clusterAnalysis();
});

layui.use('laydate', function () {
    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#date-select'//指定元素
        , format: 'yyyy-MM-dd' //可任意组合
    });
});
function clusterAnalysis() {
    var date = $("#date-select").val();
    var index = null;
    if(date.length>0) {
        $.ajax({
            type: "post",
            url: pathContent + "/ClusterController/getSelectHisdata",
            async: true,
            beforeSend: function () {
                index = layer.msg('正在加载....', {
                    icon: 1,
                    time: 0 //30秒关闭,必须大于包括数据库查询时间+前段js加载数据时间，
                    // 因为ajax执行完毕后会layer会执行关闭方法，
                    // （如果不配置，默认是3秒）
                });
            },
            data: {
                'mdate': date,
                'province': areaname
            },
            success: function (result) {
                $("#date-select").val(result['date']);
                createClusterCharts(result['cluster1'], 'cluster1', '曲线图1');
                createClusterCharts(result['cluster2'], 'cluster2', '曲线图2');
                createClusterCharts(result['cluster3'], 'cluster3', '曲线图3');
                createClusterCharts(result['cluster4'], 'cluster4', '曲线图4');

            },
            complete: function () {
                layer.close(index);
            },
            error: function (errorMsg) {
                console.log("错误：" + errorMsg);
            }
        });
    }else{
        layer.msg('请选择时间', {
            icon: 1,
            time: 1000 //30秒关闭,必须大于包括数据库查询时间+前段js加载数据时间，
            // 因为ajax执行完毕后会layer会执行关闭方法，
            // （如果不配置，默认是3秒）
        });
    }
}

function createClusterCharts(charstOptions,elementId,title) {

    var myChart = echarts.init(document.getElementById(elementId));
    $("#"+elementId).height('330px');
    $("#"+elementId).width('100%');

    //数据加载完之前先显示一段简单的loading动画
    myChart.showLoading();
    myChart.setOption({
        legend: {
            data: [],
            show:false
        },
        xAxis: {
            data: []
        },
        yAxis: {},

        tooltip:{
            show:false
        },
        color: [
            '#FF3333',
            '#53FF53',
            '#B15BFF',
            '#68CFE8',
            '#FFDC35',
            '#B00',
            '#7E98BB'
        ],
        series: []
    });


    var dataLegend = [];
    var dataXAxis = [];
    var dataSeries = [];

    for (var i = 0;i < charstOptions.length ;i++){
        dataLegend.push(charstOptions[i].legend);
        dataXAxis = charstOptions[i].hengZhou;
        var series = charstOptions[i].series;
        dataSeries.push(series);
    }

    var option = {

        legend: {
            data: dataLegend
        },
        xAxis: {
            data: dataXAxis
        },
        yAxis: {},
        series: dataSeries
    };
    window.addEventListener("onresize", function () {
        option.chart.resize();
    });
    myChart.hideLoading();//隐藏加载动画
    myChart.setOption(option);

}