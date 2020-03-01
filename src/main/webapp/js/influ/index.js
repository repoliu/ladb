var layid;
var area;
var mdate;
var areaname;
var pathContent = a;
var analysisType;
var factorLoading;
var year;
$("body").on("mousedown", ".layui-tree a cite", function () {
    $(".layui-tree a cite").css('color', 'black');
    $(this).css('color', 'red')

});
layui.use(['element', 'layer'], function () {
    var element = layui.element;
    var layer = layui.layer;
    layid = location.hash.replace('#influ=', '');
    element.tabChange('influ', layid);
    element.on('tab(influ)', function (data) {
        //jumpTabTemp 此变量用来影响因素分析- 全年气象因素分析- 页面的柱状图点击事件，点击后进入气象因素分析table页时所改变lay-id时所用的
        //jumpTabTemp就是用来判断是不是点击柱状图进到这里边的
        if(jumpTabTemp==null) {
            location.hash = 'influ=' + this.getAttribute('lay-id');
        }else{
            location.hash = 'influ=' + layid;
            jumpTabTemp=null;
            //此变量用来影响因素分析- 全年气象因素分析- 页面的柱状图点击事件，点击后进入气象因素分析table页时所改变lay-id时所用的
            //在这个柱状图点击后必须赋null
        }
        if (data.index === 0) {
            factorLoading = layer.load(1);
            changeData();
        } else if (data.index === 1) {
            factorLoading = layer.load(1);
            changeData();
        } else if (data.index === 2) {
            factorLoading = layer.load(1);
            changeData();
        } else if (data.index === 3) {
            factorLoading = layer.load(1);
            changeData();
        } else if (data.index === 4) {
            $('#LongTermContent').load(pathContent + '/longTermContonller/load', {'areaname': areaname, 'anNiu': 0});
        } else {
            factorLoading = layer.load(1);
            changeData();
        }
    });
});
$(function () {
    pathContent = a;
    if (location.hash === '') {
        location.hash = 'influ=relationPage';
    }
    layid = location.hash.replace(/^#influ=/, '');
    $.ajax({
        type: 'post',
        async: 'true',
        url: pathContent + '/area/loadTree',
        success: function (result) {
            createTree(result);
        }
    });
    layui.use('layer', function () {
        var layer = layui.layer;
        if (layid === "relationPage") {
            factorLoading = layer.load(1);
            changeData();
        }else if (layid === "airConditionerLoadAnalysis") {
            factorLoading = layer.load(1);
            changeData();
        }else if (layid === "weatherFactorPageAnalysis") {
            factorLoading = layer.load(1);
            changeData();
        } else if (layid === "weatherFactorPage") {
            factorLoading = layer.load(1);
            changeData();
        } else if (layid === "LongTermFactorPage") {
            $('#LongTermContent').load(pathContent + '/longTermContonller/load', {'areaname': areaname, 'anNiu': 0});
        } else {
            factorLoading = layer.load(1);
            changeData();
        }
    })
});


function changeData() {
    layid = location.hash.replace(/^#influ=/, '');
    document.getElementById("currentArea").innerHTML = areaname;
    if (layid === 'relationPage') {
        $(".layui-body").css("left","200px");
        $('#relationContent').load(pathContent + '/influence/relation/load', {
            'area': area,
            'year': year
        }, function (response, status, xhr) {
            layer.close(factorLoading);
            if (status === 'error') {
                layer.msg('没有该地区的数据');
                //area = temparea;
            }
        });
    } else if (layid === 'airConditionerLoadAnalysis') {
        $(".layui-body").css("left","0px");
        $('#airConditionerLoadContent').load(pathContent + '/influence/airConditionerLoad/load', {
            'area': area,
            'year': year
        }, function (response, status, xhr) {
            layer.close(factorLoading);
            if (status === 'error') {
                layer.msg('没有该地区的数据');
                //area = temparea;
            }
        });
    } else if (layid === 'weatherFactorPage') {
        $(".layui-body").css("left","200px");
        $('#weatherContent').load(pathContent + '/influence/weather/load', {
            'area': area,
            'mdate': mdate
        }, function (response, status, xhr) {
            layer.close(factorLoading);
            if (status === 'error') {
                layer.msg('没有该地区的数据');
                //area = temparea;
            }
        });
    } else if (layid === 'weatherFactorPageAnalysis') {
        $(".layui-body").css("left","200px");
        $('#weatherAnalysisContent').load(pathContent + '/influence/weatherAnalysis/load', {
            'area': area,
            'mdate': mdate,
            'analysisType':analysisType
        }, function (response, status, xhr) {
            layer.close(factorLoading);
            if (status === 'error') {
                layer.msg('没有该地区的数据');
            }
        });
    } else if (layid === 'LongTermFactorPage') {
        $(".layui-body").css("left","200px");
        $('#LongTermContent').load(pathContent + '/longTermContonller/load', {
            "areaname": areaname,
            "queRenShiJain": queRenShiJain,
            "nianXian": nianXian,
            'anNiu': 0
        });
    }
}