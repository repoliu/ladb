area = $("#areaError").val();//地区编号

areaname = null;
var errorDate = $("#errorDate").val();//
var dateChoose = $("#dateChoose").val();//
$(function () {
    selectContrastCurve(area, errorDate);
});

//查询预测值与实际值对比曲线
function selectContrastCurve(area, errorDate) {
    //这个是时间选择框的选择
    dateChooseDevice();
    //这个是获取实际曲线和预测曲线的
    $.ajax({
        type: "post", async: true, url: pathContent + '/errorAnalysis/selectContrastCurve',
        traditional: true,
        data: {"area": area, "date": errorDate},
        dataType: "json",
        success: function (result) {
            if (result!=null){
                actualCurve(result.listList, result.date96);
                // layer.msg('请输入正确的数值！', {icon: 2});//1：对勾，2：X，3：问号
            }
        }
    });
    //这个是获取误差曲线和，表格数据的
    $.ajax({
        type: "post", async: true, url: pathContent + '/errorAnalysis/selectErrorCurve',
        traditional: true,
        data: {"area": area, "date": errorDate},
        dataType: "json",
        success: function (result) {
            if(result!=null){
                errorCurve(result.listError, result.date96);
                errorTableAdd(result.listList, result.listListData);
                // layer.msg('请输入正确的数值！', {icon: 2});//1：对勾，2：X，3：问号
            }
        }
    });
    //这个获得的是误差的，最大误差时间，最大误差，准确率等等
    $.ajax({
        type: "post", async: true, url: pathContent + '/errorAnalysis/selectLoadDayerrvalue',
        traditional: true,
        data: {"area": area, "date": errorDate},
        dataType: "json",
        success: function (result) {
            if(result!=null){
                document.getElementById("errorGrid").style.display="block";
                $("#aveRate").val(result.aveRate);
                $("#maxErr").val(result.maxErr);
                $("#peakErr").val(result.peakErr);
                $("#maxtime").val(result.maxtime);
                $("#valeyErr").val(result.valeyErr);
                // layer.msg('请输入正确的数值！', {icon: 2});//1：对勾，2：X，3：问号
            }else {
                document.getElementById("errorGrid").style.display="none";
            }
        }
    });

}

//这个是显示预测和实际的曲线图的
function actualCurve(list, date96) {

    var actualCurve = echarts.init(document.getElementById('actualCurve'));
    var legends = [];//这个是图例
    var date96s = [];//这个是x轴的时间点
    var seriesErr = [];
    if (list != null) {
        if (list.legends!=null){
            for (var i = 0; i < list.legends.length; i++) {
                legends.push(list.legends[i])
                seriesErr.push(list.listSeries[i]);
            }

            for (var i = 0; i < date96.length; i++) {
                date96s.push(date96[i]);
            }
        }
        // 指定图表的配置项和数据
        var option = {
            tooltip: {
                trigger: 'axis' //坐标轴触发提示框，多用于柱状、折线图中
            },
            color: ['lawngreen','red'],
            series: [{}]
        };
        actualCurve.setOption({        //载入数据
            legend: {
                show: true,
                data: legends
            },
            xAxis: {
                type: 'category',
                data: date96s
            },
            yAxis: {   //Y轴（这里我设置了两个Y轴，左右各一个）
                type: 'value',
                name: '',//左边y轴上方显示的字
                max:list.number,
                axisLabel: {formatter: '{value}' /*控制输出格式*/}
            },
            series: seriesErr
        });
    }
    /*   notMerge        可选，是否不跟之前设置的option进行合并，默认为false，即合并。*/
    actualCurve.setOption(option);
}

//这个显示的误差数据曲线
function errorCurve(list, date96) {
    var errorCurve = echarts.init(document.getElementById('errorCurve'));
    var date96s = [];//这个是x轴的时间点
    var series3=[];
    series3.push(list.series3);
    if (list != null ) {
        for (var i = 0; i < date96.length; i++) {
            date96s.push(date96[i]);
        }
        var option = {
            tooltip: {
                trigger: 'axis' //坐标轴触发提示框，多用于柱状、折线图中
            },
            color: ['red', '#68CFE8', '#B00', '#FF1493', '#FF9432'],
            series: []
        };
        errorCurve.setOption({        //载入数据
            legend: {
                show: true,
                data:[ "误差曲线"]
            },
            xAxis: {
                type: 'category',
                data: date96s
            },
            yAxis: {   //Y轴（这里我设置了两个Y轴，左右各一个）
                type: 'value',
                name: '',//左边y轴上方显示的字
                axisLabel: {formatter: '{value}' /*控制输出格式*/}
            },
            series:series3
        });
    }
    /*   notMerge        可选，是否不跟之前设置的option进行合并，默认为false，即合并。*/
    errorCurve.setOption(option);
}

//这个是展示数据表格
function errorTableAdd(listList, listListData) {

    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#layui-tableError'
            , even: true
            , width: 10100
            , cols: listList
            , data: listListData
        });
    });
}


//这个是时间选择框的选择
function  dateChooseDevice() {
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#errorDateChoose'
            , format: 'yyyy-MM-dd'
            , value:dateChoose
            , done: function (value) {
                dateChoose=value;
                errorDate=value;
                selectContrastCurve(area, value);

            }
        });
    });
}