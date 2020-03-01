//--Bootstrap Date Picker--

//此处不使用bootstrap版本的时间选择器，使用的layui版本的时间选择器
/*$('.date-picker').datepicker({
    language: "zh-CN",
    todayBtn: "linked",
    autoclose: true
});  */

//日期选择器js
layui.use('laydate', function () {
    var laydate = layui.laydate;
    //执行一个laydate实例
    laydate.render({
        elem: '#date-picker'//指定元素
        , format: 'yyyy-MM-dd', //可任意组合
    });
});

//每100毫秒刷新一次id内容，此处用不到，所以注释
//setInterval("getCurDate()", 100);

function getCurDate() {
    var d = new Date();
    var week;
    switch (d.getDay()) {
        case 1:
            week = "星期一";
            break;
        case 2:
            week = "星期二";
            break;
        case 3:
            week = "星期三";
            break;
        case 4:
            week = "星期四";
            break;
        case 5:
            week = "星期五";
            break;
        case 6:
            week = "星期六";
            break;
        default:
            week = "星期天";
    }
    var years = d.getFullYear();
    var month = add_zero(d.getMonth() + 1);
    var days = add_zero(d.getDate());
    var hours = add_zero(d.getHours());
    var minutes = add_zero(d.getMinutes());
    var seconds = add_zero(d.getSeconds());
//        var ndate = years+"年"+month+"月"+days+"日 "+hours+":"+minutes+":"+seconds+" "+week;
    var ndate = month + "月" + days + "日 " + hours + ":" + minutes + ":" + seconds + " " + week;
    $("#toDay").text(ndate);
}

function add_zero(temp) {
    if (temp < 10) return "0" + temp;
    else return temp;
}



// ---------负荷预测----------
function initCharMethod(){
    var fhChart = echarts.init(document.getElementById('fhchart'), 'wlskin');
    fhChart.showLoading();
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

            series: [{
                name: '实测有功历史',
                type: 'line',
                data: []
            },
                {
                    name: '实测无功历史',
                    type: 'line',
                    data: []
                }
            ]
        });


}







