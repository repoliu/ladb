var layid;
var area;
var mdate;
var areaname;
var pathContent = a;
var analysisType;
var factorLoading;
var year;

layui.use(['element', 'layer'], function () {
    var element = layui.element;
    var layer = layui.layer;
    layid = location.hash.replace('#define=', '');
    element.tabChange('define', layid);
    element.on('tab(define)', function (data) {
        if (data.index === 0) {
            factorLoading = layer.load(1);
            changeData();
        } else if (data.index === 1) {

        } else if (data.index === 2) {

        } else {
            // factorLoading = layer.load(1);
            // changeData();
        }
    });
});
$(function () {
    pathContent = a;
    if (location.hash === '') {
        location.hash = 'define=serviceDefine';
    }
    layid = location.hash.replace(/^#define=/, '');
    layui.use('layer', function () {
        var layer = layui.layer;
        if (layid === "serviceDefine") {
            factorLoading = layer.load(1);
            changeData();
        }else if (layid === "algoDefine") {

        }else if (layid === "interfaceDefine") {

        } else {
            // factorLoading = layer.load(1);
            // changeData();
        }
    })
});


function changeData() {
    layid = location.hash.replace(/^#define=/, '');
    if (layid === 'serviceDefine') {
        $('#serviceContent').load(pathContent + '/define/formDefine', function (response, status, xhr) {
            layer.close(factorLoading);
            if (status === 'error') {
                layer.msg('没有数据');
            }
        });
    } else if (layid === 'algoDefine') {

    } else if (layid === 'interfaceDefine') {

    }
}