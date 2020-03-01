
$("body").on("mousedown",".layui-tree li a cite",function(){
    $(".layui-tree li a cite").css('color','black');
    $(this).css('color','red')
});
$(function () {
    busName = $("#busName").val();
    areaname = busName;
    $("#selectedArea").html("选择的地区:" + areaname);
    $.ajax({
        type: "post", async: true, url:pathContent +  "/busClassificationController/treeTrueFalse",    //请求发送到ShowInfoIndexServlet处
        data: {"areaname": areaname},
        dataType: "json",
        success: function (result) {
            if (result){
                selectDataBusJump("/busClassificationController/busJumpData");
            }else {
                clickBusJumpTreeTwo();
             /*   layui.use(['element','layer' ], function() {
                    var layer = layui.layer;
                    layer.msg('请选择地区！', {icon: 3});//1：对勾，2：X，3：问号
                });*/
            }
        }
    });
});



//这个方法时点击地区树判断时在哪个table块。如果在分类数据查询的花，就跳到这
function clickBusJumpTree() {
    selectDataBusJump("/busClassificationController/busJumpData");
}
//这个方法时点击地区树判断时在哪个table块。如果在分类数据查询的花，就跳到这
function clickBusJumpTreeTwo() {
    selectDataBusJump("/busClassificationController/busJumpDataTwo");
}


function selectDataBusJump(urlName) {
    $.ajax({
        type: "post", async: true, url:pathContent +  urlName,    //请求发送到ShowInfoIndexServlet处
        data: {"areaname": areaname},
        dataType: "json",
        success: function (result) {
            layui.use('table', function () {
                var table = layui.table;
                //展示已知数据
                table.render({
                    elem: '#busJumpTable4'
                    ,height:diaHeight*0.8
                    , width: 400 //这个是表格的宽度
                    , even: true
                    , page: true //是否显示分页
                    ,limits: [200, 300, 400]//可以选择每页显示多少条
                    , limit: 200//每页默认显示的数量
                    , size: 'lg'
                    , cols: result.dygraphs1
                    , data: result.busReturnValue1
                });
                table.render({
                    elem: '#busJumpTable5'
                    ,height:diaHeight*0.8
                    , width: 400 //这个是表格的宽度
                    , even: true
                    , page: true //是否显示分页
                    ,limits: [200, 300, 400]//可以选择每页显示多少条
                    , limit: 200//每页默认显示的数量
                    , size: 'lg'
                    , cols: result.dygraphs2
                    , data: result.busReturnValue2
                });
                table.render({
                    elem: '#busJumpTable6'
                    ,height:diaHeight*0.8
                    , width: 400 //这个是表格的宽度
                    , even: true
                    , page: true //是否显示分页
                    ,limits: [200, 300, 400]//可以选择每页显示多少条
                    , limit: 200//每页默认显示的数量
                    , size: 'lg'
                    , cols: result.dygraphs3
                    , data: result.busReturnValue3
                });
            });
        }
    });
}