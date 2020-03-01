var list = [];
var numberList = [];
var dateList = [];
var date24 = [];
var valueAdd = "";
var valueLess = "";
var widthTable = $("#widthTable").val();//
var dateScope = $("#dateScope").val(); //这个是用来确定修改曲线的时间范围

var zhuanHuan1 = $("#zhuanHuan1s").val(); //
var zhuanHuan2 = $("#zhuanHuan2s").val(); //
var zhuanHuan3 = $("#zhuanHuan3s").val(); //
var zhuanHuan4 = $("#zhuanHuan2s").val(); //

areaname = $("#areaname").val();//地区名称
area = $("#area").val();//地区编号
var date = $("#date").val();//地区编号
var layuiDate = $("#four").val(); //这个是时间选择框上显示的时间
document.getElementById("currentArea").innerHTML = areaname;//这个是显示当前的地区
function yuQiUpdate(num) {
    var index=null;
    $.ajax({
        type: "post", async: true,
        traditional: true,
        beforeSend: function () {
            index = layer.msg('正在修改数据....', {
                icon: 7,
                time: 0
            });
        },
        url: pathContent + '/forecast/day/updatePredict',    //请求发送到ShowInfoIndexServlet处
        data: {
            "area": area,
            "areaname": areaname,
            "zhuanHuan1": zhuanHuan1,
            "zhuanHuan2": zhuanHuan2,
            "zhuanHuan3": zhuanHuan3,
            "zhuanHuan4":zhuanHuan4,
            "num":num
        },
        dataType: "json",
        success: function (result) {
            var number = "";
            number = result.value;
            if (number == "0") {
                layer.msg('修改失败！', {icon: 2});//1：对勾，2：X，3：问号
            } else if (number == "1") {
                layer.msg('修改成功！', {icon: 1});//1：对勾，2：X，3：问号
            } else if (number == "2") {
                layer.msg('没有找到目标曲线！', {icon: 3});//1：对勾，2：X，3：问号
            }
        }, complete: function () {
            layer.close(index);
        }
    });
}

function yuQi() {
    layer.open({
        type: 1,
        skin: 'layui-layer-demo', //样式类名
        closeBtn: 0, //不显示关闭按钮
        anim: 2,
        shadeClose: true, //开启遮罩关闭
        area: ['800px', '300px'],
        content: '<div style=" padding-top: 50px">\n' +
        '            <div class="layui-input-inline" style="padding-left: 90px ">\n' +
        '                <label class="layui-form-label">预测日期:</label>\n' +
        '                <input type="text" class="layui-input" id="zhuanHuan1" placeholder="预测日期" style="width: 90px">\n' +
        '            </div>\n' +
        '            <div class="layui-input-inline">\n' +
        '                <label class="layui-form-label">目标日期:</label>\n' +
        '                <input type="text" class="layui-input" id="zhuanHuan2" placeholder="目标日期" style="width: 180px">\n' +
        '            </div>\n' +
        '            <button class="layui-btn layui-btn-radius" onclick="yuQiUpdate(2)">预期日期修改</button>\n' +
        '        </div>\n' +
        '        <div style=" padding-top: 50px">\n' +
        '            <div class="layui-input-inline" style="padding-left: 90px">\n' +
        '                <label class="layui-form-label">实际日期:</label>\n' +
        '                <input type="text" class="layui-input" id="zhuanHuan3" placeholder="实际日期" style="width: 90px">\n' +
        '            </div>\n' +
        '            <div class="layui-input-inline">\n' +
        '                <label class="layui-form-label">目标日期:</label>\n' +
        '                <input type="text" class="layui-input" id="zhuanHuan4" placeholder="目标日期" style="width: 180px">\n' +
        '            </div>\n' +
        '            <button class="layui-btn layui-btn-radius" onclick="yuQiUpdate(4)">预期日期修改</button>\n' +
        '        </div>  ' +
        '<script>\n' +
        '     layui.use("laydate", function () {\n' +
        '         var laydate = layui.laydate;\n' +
        '         laydate.render({\n' +
        '             elem: "#zhuanHuan1"\n' +
        '             , value: zhuanHuan1\n' +
        '             , done: function (value) {\n' +
        '                 zhuanHuan1 = "";\n' +
        '                 zhuanHuan1 = value;\n' +
        '             }\n' +
        '         });\n' +
        '         laydate.render({\n' +
        '             elem: "#zhuanHuan2"\n' +
        '             , value: zhuanHuan2\n' +
        '             , String: "yyyy-MM-dd"\n'+
        '             ,range: true\n' +
        '             , done: function (value) {\n' +
        '                 zhuanHuan2 = "";\n' +
        '                 zhuanHuan2 = value;\n' +
        '             }\n' +
        '         });\n' +
        '         laydate.render({\n' +
        '             elem: "#zhuanHuan3"\n' +
        '             , value: zhuanHuan3\n' +
        '             , done: function (value) {\n' +
        '                 zhuanHuan3 = "";\n' +
        '                 zhuanHuan3 = value;\n' +
        '             }\n' +
        '         });\n' +
        '         laydate.render({\n' +
        '             elem: "#zhuanHuan4"\n' +
        '             , value: zhuanHuan4\n' +
        '             , String: "yyyy-MM-dd"\n'+
        '             ,range: true\n' +
        '             , done: function (value) {\n' +
        '                 zhuanHuan4 = "";\n' +
        '                 zhuanHuan4 = value;\n' +
        '             }\n' +
        '         });\n' +
        '     });\n' +
        ' </script>'

    });
}

function yuCeUpdate() {
    valueAdd = null;
    valueLess = null;
    valueAdd = $("#valueAdd").val();//值增加
    valueLess = $("#valueLess").val();//值减少

    var reg = /^\d*\.{0,1}\d{0,1}$/;
    //  var reg = new RegExp("^[0-9]*$");

    var valueAddNumber = 0;
    var valueLessNumber = 0;
    if (valueAdd != "") {
        if (!reg.test(valueAdd)) {
        } else {
            valueAddNumber = 2;
        }
    } else {
        valueAddNumber = 1;
    }
    if (valueLess != "") {
        if (!reg.test(valueLess)) {
        } else {
            valueLessNumber = 2;
        }
    } else {
        valueLessNumber = 1;
    }
    //判断输入的没有字母，0代表是有字母，1代表是""，2代表是数值
    if (valueAddNumber != 0 && valueLessNumber != 0) {

        $.ajax({
            type: "post", async: true,
            traditional: true,
            beforeSend: function () {
                index = layer.msg('正在修改数据....', {
                    icon: 7,
                    time: 0
                });
            },
            url: pathContent + '/forecast/day/update',    //请求发送到ShowInfoIndexServlet处
            data: {
                "area": area,
                "areaname": areaname,
                "date": date,
                "numberList": numberList,
                "dateList": dateList,
                "dateScope": dateScope,
                "valueAdd": valueAdd,
                "valueLess": valueLess
            },
            dataType: "json",
            success: function (result) {
                var number = "";
                number = result.value;
                if (number == "0") {
                    layer.msg('修改失败！', {icon: 2});//1：对勾，2：X，3：问号
                } else if (number == "1") {
                    layer.msg('修改成功！', {icon: 1});//1：对勾，2：X，3：问号
                } else if (number == "2") {
                    layer.msg('请修改曲线！', {icon: 3});//1：对勾，2：X，3：问号
                }

                valueAdd = $("#valueAdd").val();//
                valueLess = $("#valueLess").val();//

                $('#forecastContent').load(pathContent + '/forecast/day/load', {
                    "area": area,
                    "areaname": areaname,
                    "date": date,
                    "dateScope": dateScope,
                    "uiAddValue": valueAdd,
                    "uiLessValue": valueLess
                });
            }, complete: function () {
                layer.close(index);
            }
        });
    } else {
        layer.msg('请输入正确的数值！', {icon: 2});//1：对勾，2：X，3：问号
    }
}


//这个是7个时间按钮点击后分别的方法，进行重新加载
function one() {
    var oneButton = $("#one").val();
    load(oneButton);
}

function two() {
    var twoButton = $("#two").val();
    load(twoButton);
}

function three() {
    var threeButton = $("#three").val();
    load(threeButton);
}

function four() {
    var fourButton = $("#four").val();
    load(fourButton);
}

function five() {
    var fiveButton = $("#five").val();
    load(fiveButton);
}

function six() {
    var sixButton = $("#six").val();
    load(sixButton);
}

function seven() {
    var sevenButton = $("#seven").val();
    load(sevenButton);
}

function load(date) {
    $('#forecastContent').load(pathContent + '/forecast/day/load', {"area": area, "areaname": areaname, "date": date});
}


/*------------------------------点击时间选择框进这里边----------------------------------------------------------------------------------------------------------------------------*/


layui.use('laydate', function () {
    var laydate = layui.laydate;

    laydate.render({//这个是修改曲线选择的时间范围
        elem: '#test9'
        , type: 'time'
        , range: true
        , format: 'HH:mm'
        , value: dateScope
        , done: function (value) {
            dateScope = null;
            dateScope = value;
        }
    });
    laydate.render({
        elem: '#test1'
        , value: layuiDate
        , String: 'yyyy-MM-dd'
        , showBottom: false
        , done: function (value) {
            $('#forecastContent').load(pathContent + '/forecast/day/load', {
                "area": area,
                "areaname": areaname,
                "date": value,
                "dateList": dateList
            });
        }
    });

    laydate.render({
        elem: '#test2'
        , value: layuiDate
        , String: 'yyyy-MM-dd'
        , showBottom: false
        , done: function (value) {
            dateList.push(value);
            if (dateList.length < 7) {
                $.ajax({
                    type: "post", async: true, url: pathContent + '/forecast/day/select',    //请求发送到ShowInfoIndexServlet处
                    traditional: true,
                    data: {"area": area, "areaname": areaname, "date": date, "dateList": dateList},
                    dataType: "json",
                    success: function (result) {
                        date24 = [];
                        for (var i = 0; i < 97; i++) {
                            date24[i] = result.date24[i];
                            numberList[i] = result.mingTian[i];
                        }
                        tableAdd(result.listList, result.listListData);
                        quXian(result.tableList, result.maxNumber, result.dates);//调用下边的方法
                    }
                });
            } else {
                layer.msg('最多显示9条曲线！', {icon: 2});//1：对勾，2：X，3：问号
            }

        }
    });
});

/*------------------------------加载的时候------------------------------------------------------------------------------------------------------------------------------------------*/
$(function () {
    ajax(date);
})

/*------------------------------公共方法------------------------------------------------------------------------------------------------------------------------------------------*/

function ajax(date) {
    //var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    $.ajax({
        type: "post", async: true, url: pathContent + '/forecast/day/select',    //请求发送到ShowInfoIndexServlet处
        traditional: true,
        data: {"area": area, "areaname": areaname, "date": date},
        dataType: "json",
        success: function (result) {
           // console.log(result);

            date24 = [];
            for (var i = 0; i < 97; i++) {
                date24[i] = result.date24[i];
                numberList[i] = result.mingTian[i];

            }
            tableAdd(result.listList, result.listListData);
            quXian(result.tableList, result.maxNumber, result.dates);//调用下边的方法
        }
    });
}

function quXian(tableList, max, dates) {
   // console.log(tableList);
    var change_tool;  // defined below.
    var zoom = document.getElementById('tool_zoom');
    zoom.onclick = function () {
        change_tool(zoom);
    };
    var pencil = document.getElementById('tool_pencil');
    pencil.onclick = function () {
        change_tool(pencil);
    };
    var eraser = document.getElementById('tool_eraser');
    eraser.onclick = function () {
        change_tool(eraser);
    };
    var end_date; // = new Date("2017/12/28").getTime();//这个是曲线图的结束时间
    var oDate = new Date(); //实例一个时间对象；
    var mytime=oDate.toLocaleDateString(); //获取当前时间
    var start_date = new Date(mytime).getTime();//这个是曲线图的开始时间
    end_date = start_date + 1000 * 60 * 60 * 24;
    start_date = start_date - 1000 * 60 * 15;
    var data = [];
    var area0 = [];
    var area1 = [];
    var area2 = [];
    var area3 = [];
    var area4 = [];
    var area5 = [];
    var area6 = [];
    var area7 = [];
    var area8 = [];
    var area9 = [];
    var number = 0;

    for (var d = start_date; d < end_date; d += 900 * 1000) {
        var millis = 0;//每个点间隔15分钟
        if (i == start_date) {
            millis = d;//每个点间隔15分钟
        } else {
            millis = d + 900 * 1000;//每个点间隔15分钟
        }
        if (tableList.length == 3) {
            area0.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[0][number])]);//
            area1.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[1][number])]);//
            area2.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[2][number])]);//
        } else if (tableList.length == 4) {
            area2.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[2][number])]);
            area0.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[0][number])]);
            area1.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[1][number])]);
            area3.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[3][number])]);
        } else if (tableList.length == 5) {
            area2.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[2][number])]);
            area0.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[0][number])]);
            area1.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[1][number])]);
            area3.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[3][number])]);
            area4.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[4][number])]);
        } else if (tableList.length == 6) {
            area2.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[2][number])]);
            area0.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[0][number])]);
            area1.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[1][number])]);
            area3.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[3][number])]);
            area4.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[4][number])]);
            area5.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[5][number])]);
        } else if (tableList.length == 7) {
            area2.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[2][number])]);
            area0.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[0][number])]);
            area1.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[1][number])]);
            area3.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[3][number])]);
            area4.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[4][number])]);
            area5.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[5][number])]);
            area6.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[6][number])]);
        } else if (tableList.length == 8) {
            area2.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[2][number])]);
            area0.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[0][number])]);
            area1.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[1][number])]);
            area3.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[3][number])]);
            area4.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[4][number])]);
            area5.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[5][number])]);
            area6.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[6][number])]);
            area7.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[7][number])]);
        } else if (tableList.length == 9) {
            area2.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[2][number])]);
            area0.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[0][number])]);
            area1.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[1][number])]);
            area3.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[3][number])]);
            area4.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[4][number])]);
            area5.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[5][number])]);
            area6.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[6][number])]);
            area7.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[7][number])]);
            area8.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[8][number])]);
        } else if (tableList.length == 10) {
            area2.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[2][number])]);
            area0.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[0][number])]);
            area1.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[1][number])]);
            area3.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[3][number])]);
            area4.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[4][number])]);
            area5.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[5][number])]);
            area6.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[6][number])]);
            area7.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[7][number])]);
            area8.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[8][number])]);
            area9.push([new Date(Dygraph.dateString_(millis)), parseFloat(tableList[9][number])]);
        }
        number++;
    }
    if (tableList.length == 3) {
        data.push(area0);//
        data.push(area1);//
        data.push(area2);//
    } else if (tableList.length == 4) {
        data.push(area0);//
        data.push(area1);//
        data.push(area2);//
        data.push(area3);
    } else if (tableList.length == 5) {
        data.push(area0);//
        data.push(area1);//
        data.push(area2);//
        data.push(area3);
        data.push(area4);
    } else if (tableList.length == 6) {
        data.push(area0);//
        data.push(area1);//
        data.push(area2);//
        data.push(area3);
        data.push(area4);
        data.push(area5);
    } else if (tableList.length == 7) {
        data.push(area0);//
        data.push(area1);//
        data.push(area2);//
        data.push(area3);
        data.push(area4);
        data.push(area5);
        data.push(area6);
    } else if (tableList.length == 8) {
        data.push(area0);//
        data.push(area1);//
        data.push(area2);//
        data.push(area3);
        data.push(area4);
        data.push(area5);
        data.push(area6);
        data.push(area7);
    } else if (tableList.length == 9) {
        data.push(area0);//
        data.push(area1);//
        data.push(area2);//
        data.push(area3);
        data.push(area4);
        data.push(area5);
        data.push(area6);
        data.push(area7);
        data.push(area8);
    } else if (tableList.length == 10) {
        data.push(area0);//
        data.push(area1);//
        data.push(area2);//
        data.push(area3);
        data.push(area4);
        data.push(area5);
        data.push(area6);
        data.push(area7);
        data.push(area8);
        data.push(area9);
    }
    var isDrawing = false;
    var lastDrawRow = null, lastDrawValue = null;
    var tool = 'pencil';
    var valueRange = [0, max];//值的范围

    function setPoint(event, g, context) {
        var graphPos = Dygraph.findPos(g.graphDiv);
        var canvasx = Dygraph.pageX(event) - graphPos.x;
        var canvasy = Dygraph.pageY(event) - graphPos.y;
        var xy = g.toDataCoords(canvasx, canvasy);
        var x = xy[0], value = xy[1];
        var rows = g.numRows();
        var closest_row = -1;
        var smallest_diff = -1;
        // TODO(danvk): binary search
        for (var row = 0; row < rows; row++) {
            var date = g.getValue(row, 0);  // millis
            var diff = Math.abs(date - x);
            if (smallest_diff < 0 || diff < smallest_diff) {
                smallest_diff = diff;
                closest_row = row;//这个不能改，改了线就不动了
            }
        }

        if (closest_row != -1) {
            if (lastDrawRow === null) {
                lastDrawRow = closest_row;
                lastDrawValue = value;
            }
            var coeff = (value - lastDrawValue) / (closest_row - lastDrawRow);
            if (closest_row == lastDrawRow) coeff = 0.0;
            var minRow = Math.min(lastDrawRow, closest_row);
            var maxRow = Math.max(lastDrawRow, closest_row);
            for (var row = minRow; row <= maxRow; row++) {
                if (tool == 'pencil') {
                    var val = lastDrawValue + coeff * (row - lastDrawRow);
                    val = Math.max(valueRange[0], Math.min(val, valueRange[1]));
                    data[0][row][1] = val;
                    if (val === null || value === undefined || isNaN(val)) {
                        // console.log(val);
                    }
                } else if (tool == 'eraser') {
                    data[0][row][1] = null;
                }
            }
            lastDrawRow = closest_row;
            lastDrawValue = value;
            g.updateOptions({file: data[0]});
            g.setSelection(closest_row);  // prevents the dot from being finnicky.
        }
    }

    function finishDraw() {
        isDrawing = false;
        lastDrawRow = null;
        lastDrawValue = null;
    }

    change_tool = function (tool_div) {
        var ids = ['tool_zoom', 'tool_pencil', 'tool_eraser'];
        for (var i = 0; i < ids.length; i++) {
            var div = document.getElementById(ids[i]);
            if (div == tool_div) {
                div.style.backgroundPosition = -(i * 32) + 'px -32px';
            } else {
                div.style.backgroundPosition = -(i * 32) + 'px 0px';
            }
        }
        tool = tool_div.id.replace('tool_', '');

        var dg_div = document.getElementById("draw_div");
        /*if (tool == 'pencil') {
            dg_div.style.cursor = 'url(images/cursor-pencil.png) 2 30, auto';
        } else if (tool == 'eraser') {
            dg_div.style.cursor = 'url(images/cursor-eraser.png) 10 30, auto';
        } else if (tool == 'zoom') {
            dg_div.style.cursor = 'crosshair';
        }*/
    };
    change_tool(document.getElementById("tool_pencil"));
    // console.log(date);
    for (var i = 0; i < tableList.length; i++) {
        var colors = new Array();
        var sta = "";
        if (i == 0) {
            sta = "draw_div2";
            colors.push("red");
            document.getElementById("yuCe").innerHTML = dates[0];
        } else if (i == 1) {
            sta = "draw_div1";
            colors.push("#06d8d7");
            document.getElementById("jinRi").innerHTML = dates[1];
        } else if (i == 2) {
            sta = "draw_div";
            colors.push("#fdd35a");
            document.getElementById("zuoRi").innerHTML = dates[2];
        } else if (i == 3) {
            sta = "draw_div3";
            colors.push("darkgrey");
            document.getElementById("fourSpan").innerHTML = dates[3];
        } else if (i == 4) {
            sta = "draw_div4";
            colors.push("purple");
            document.getElementById("fiveSpan").innerHTML = dates[4];
        } else if (i == 5) {
            sta = "draw_div5";
            colors.push("#119000");
            document.getElementById("sixSpan").innerHTML = dates[5];
        } else if (i == 6) {
            sta = "draw_div6";
            colors.push("mediumblue");
            document.getElementById("sevenSpan").innerHTML = dates[6];
        } else if (i == 7) {
            sta = "draw_div7";
            colors.push("magenta");
            document.getElementById("eightSpan").innerHTML = dates[7];
        } else if (i == 8) {
            sta = "draw_div8";
            colors.push("lawngreen");
            document.getElementById("nineSpan").innerHTML = dates[8];
        } else if (i == 9) {
            sta = "draw_div9";
            colors.push("#5b5958");
            document.getElementById("tenSpan").innerHTML = dates[9];
        }
        new Dygraph(document.getElementById(sta), data[i],
            {
                valueRange: valueRange,
                labels: ['Date', 'value'],
                tickColor: "red",
                interactionModel: {

                    mousedown: function (event, g, context) {
                        if (tool == 'zoom') {
                            Dygraph.defaultInteractionModel.mousedown(event, g, context);
                        } else {
                            // prevents mouse drags from selecting page text.
                            if (event.preventDefault) {
                                event.preventDefault();  // Firefox, Chrome, etc.
                            } else {
                                event.returnValue = false;  // IE
                                event.cancelBubble = true;
                            }
                            isDrawing = true;
                        }
                    },
                    mousemove: function (event, g, context) {

                        if (tool == 'zoom') {
                            Dygraph.defaultInteractionModel.mousemove(event, g, context);

                        } else {
                            if (!isDrawing) return;
                            setPoint(event, g, context);
                        }
                    },


                    mouseup: function (event, g, context) {//当在元素上放松鼠标按钮时，
                        numberList = [];//每次进入给他重新赋予空值
                        list = g.rawData_;
                        for (var i = 0; i < list.length; i++) {
                            numberList.push(list[i][1])
                        }

                        //console.log(list);
                        //console.log(numberList);
                       if (tool == 'zoom') {
                            Dygraph.defaultInteractionModel.mouseup(event, g, context);
                        } else {
                            finishDraw();
                        }
                    },
                },
                strokeWidth: 1.5,//曲线的宽度
                // pointSize: 93,
                colors: colors,
                gridLineColor: 'rgb(196, 196, 196)',//网格线的颜色
                axes: {
                    x: {
                        drawAxis: true
                    },
                    y: {
                        drawGrid: true
                    } ,
                    y2: {
                        // set axis-related properties here
                        labelsKMB: true,
                    }
                }
            });
    }
    window.onmouseup = finishDraw;
}


function tableAdd(listList, listListData) {
    /*console.log(listList);
    console.log(listListData);*/
    layui.use('table', function () {
        var table = layui.table;
        table.render({
            elem: '#layui-table'
            , even: true
            , width: 10100 //9960
            , cols: listList
            , data: listListData
        });
    });
}