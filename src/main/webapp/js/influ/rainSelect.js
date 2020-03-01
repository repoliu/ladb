var areaname = areanametemp;//这个是地区
var mdate =mdateTemp;//这个是一上来默认的时间
var pathContent = a;//这个是项目的webapp的目录
var str =  areaname;//这个是代表了查询雨量大小的，是小雨，还是大雨，还是暴雨等等等


layui.use(['laydate','layer'], function(){
    var laydate = layui.laydate;
    var layer = layui.layer;
    laydate.render({
        elem: '#test2'
        , type: 'year'
        ,value:mdate.substring(0,4)
        , done: function (value) {
            mdate=value;
            ajax(value,str);
        }
    });
});

/*这是几个按钮的颜色控制*/
function btn7(bat) {
    var bats = ["rain", "rain1", "rain2", "rain3", "rain4", "rain5", "rain6"];
    for (var i = 0; i < bats.length; i++) {
        if (bat == bats[i]) {
            $('#' + bats[i]).attr('class', 'layui-btn layui-btn-radius layui-btn-primary');//代表了白的
        } else {
            $('#' + bats[i]).attr('class', 'layui-btn layui-btn-radius  ');//代表了深绿色
        }
    }
}
/*这是几个按钮*/
function rain() {
    str = $("#rain").val();
    btn7("rain");
    ajax(mdate,str);
}
function rain1() {
    str = $("#rain1").val();
    btn7("rain1");
    ajax(mdate,str);
}
function rain2() {
    str = $("#rain2").val();
    btn7("rain2");
    ajax(mdate,str);
}
function rain3() {
    str = $("#rain3").val();
    btn7("rain3");
    ajax(mdate,str);
}

function rain4() {
    str = $("#rain4").val();
    btn7("rain4");
    ajax(mdate,str);
}
function rain5() {
    str = $("#rain5").val();
    btn7("rain5");
    ajax(mdate,str);
}
function rain6() {
      str = null;
    btn7("rain6");
    ajax(mdate,str);
}

//加载的时候的
str = null;
btn7("rain6");
ajax(mdate,str);
// $(function () {
//
// })


function ajax(mdate,rainfall) {
    layui.use(['laydate','layer'], function(){
        var laydate = layui.laydate;
        var layer = layui.layer;

    $.ajax({
        type: "post", async: true,
        traditional: true,
        beforeSend: function () {
            index = layer.msg('正在查询数据....', {
                icon: 7,
                time: 0
            });
        },
        url: pathContent + '/influence/weather/rainSelect',
        data: {"areaname": areaname, "date": mdate, "rainfall": rainfall},
        dataType: "json",
        success: function (result) {

            if (result.listListData != null && result.listListData != "") {
                $("#rainSpan").html(result.dayNumber[0]);
                $("#rainSpan1").html(result.dayNumber[1]);
                $("#rainSpan2").html(result.dayNumber[2]);
                $("#rainSpan3").html(result.dayNumber[3]);
                $("#rainSpan4").html(result.dayNumber[4]);
                $("#rainSpan5").html(result.dayNumber[5]);
                layui.use('table', function () {
                    var table = layui.table;
                    //展示已知数据
                    table.render({
                        elem: '#demo'
                        , width: 1200 //这个是表格的宽度
                        , even: true
                        , page: true //是否显示分页
                        ,limits: [200, 300, 400]//可以选择每页显示多少条
                        , limit: 200//每页默认显示的数量
                        , size: 'lg'
                        , cols: result.listList
                        , data: result.listListData
                    });
                });

            } else {
                layer.msg('没有数据！', {icon: 2});//1：对勾，2：X，3：问号
                $("#rainSpan").html(result.dayNumber[0]);
                $("#rainSpan1").html(result.dayNumber[1]);
                $("#rainSpan2").html(result.dayNumber[2]);
                $("#rainSpan3").html(result.dayNumber[3]);
                $("#rainSpan4").html(result.dayNumber[4]);
                $("#rainSpan5").html(result.dayNumber[5]);
                layui.use('table', function () {
                    var table = layui.table;
                    //展示已知数据
                    table.render({
                        elem: '#demo'
                        , width: 1200 //这个是表格的宽度
                        , even: true
                        , page: true //是否显示分页
                        ,limits: [200, 300, 400]//可以选择每页显示多少条
                        , limit: 200//每页默认显示的数量
                        , size: 'lg'
                        , cols: result.listList
                        , data: ""
                    });
                });
            }
        }, complete: function () {
            layer.close(index);
        }
    });
    });
}