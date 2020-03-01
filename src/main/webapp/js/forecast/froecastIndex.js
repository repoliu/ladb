var pathContent = a;
var mdate;
var year;
var temparea;

var errorDate;//这个在日误差分析用到

//这一步是要生成左侧的地区树的地方
$(function () {
    pathContent = a;
    if (location.hash === '') {
        location.hash = 'forecast=forecastId';
    }
    layid = location.hash.replace(/^#forecast=/, '');
    $.ajax({
        type: 'post',
        async: 'true',
        url: pathContent + '/area/loadTree',//这是进行查询左侧树的地址，在AreaController中，最后换成loadProvinceTree这个就是字查询到省
        success: function (result) {
            createTree(result);
        }
    });
    if (layid === "forecastId") {
        $('#forecastContent').load(pathContent + '/forecast/day/load', {"area": area});
    }else if (layid=="errorAnalysisId"){
        $('#errorAnalysis').load(pathContent + '/errorAnalysis/load', {"area": area,"date":errorDate});
    }
});
layui.use(['element', 'layer'], function () {
    var element = layui.element;
    var layer = layui.layer;
    layid = location.hash.replace(/^#forecast=/, '');
    element.tabChange('forecast', layid);
    element.on('tab(forecast)', function (data) {
        if (data.index === 0) {
            $('#forecastContent').load(pathContent + '/forecast/day/load', {"area": area});
        }else   if (data.index === 1) {
            $('#errorAnalysis').load(pathContent + '/errorAnalysis/load', {"area": area,"date":errorDate});
        }
        location.hash = 'forecast=' + this.getAttribute('lay-id');
    });
});
var layid;
var point = $("input[name='point']:checked").val();


function changeData() {
    //点击左侧的树进入这里边
    layid = location.hash.replace(/^#forecast=/, '');
    point = $("input[name='point']:checked").val();
    document.getElementById("currentArea").innerHTML = areaname;
    if (layid === "forecastId") {
        $('#forecastContent').load(pathContent + '/forecast/day/load', {"area": area});
    }else if (layid=="errorAnalysisId"){
        $('#errorAnalysis').load(pathContent + '/errorAnalysis/load', {"area": area,"date":errorDate});
    }
}