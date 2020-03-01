var jumpTabTemp = null;//此变量用来影响因素分析- 全年气象因素分析- 页面的柱状图点击事件，点击后进入气象因素分析table页时所改变lay-id时所用的
var opt = {};

function createCharts(charstOptions, elementId) {
    echarts.dispose(document.getElementById(elementId));
    var myChart = echarts.init(document.getElementById(elementId));
    var dataLegend = [];
    var dataXAxis = [];
    var dataSeries = [];
    for (var i = 0; i < charstOptions.length; i++) {
        dataLegend.push(charstOptions[i].legend);
        dataXAxis = charstOptions[i].hengZhou;
        var series = charstOptions[i].series;
        dataSeries.push(series);
    }

    var option = {
        legend: {data: dataLegend},
        xAxis: {
            data: dataXAxis,
            boundaryGap: false,
            axisLabel: {
                showMaxLabel: true
            }
        },
        yAxis: {
            name: '负荷/MW',
            max: function (value) {
                var result;
                if (value.max > 0) {
                    result = parseInt(value.max * 1.5);
                } else {
                    result = parseInt(value.max / 1.5);
                }
                return result;
            }
        },
        dataZoom: [{
            type: 'inside'
        }, {
            type: 'slider'
        }],
        tooltip: {
            trigger: 'axis'
        },
        color: [
            '#FF3333',
            '#53FF53',
            '#B15BFF',
            '#68CFE8',
            '#FFDC35',
            '#B00'
        ],
        series: dataSeries,
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
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
                },
                restore: {},
                saveAsImage: {}
            },
            right: '10%'
        }
    };
    myChart.setOption(option);
}

function clusterGraph(charstOptions, elementId) {
    echarts.dispose(document.getElementById(elementId));
    var myChart = echarts.init(document.getElementById(elementId));
    var dataLegend = [];
    var dataXAxis = [];
    var dataSeries = [];
    for (var i = 0; i < charstOptions.length; i++) {
        dataLegend.push(charstOptions[i].legend);
        dataXAxis = charstOptions[i].hengZhou;
        var series = charstOptions[i].series;
        series.showAllSymbol = false;
        series.showSymbol = false;
        dataSeries.push(series);
    }

    var option = {
        legend: {
            data: dataLegend
            , show: false
        },
        xAxis: {
            data: dataXAxis,
            boundaryGap: false,
            axisLabel: {
                showMaxLabel: true
            }
        },
        yAxis: {
            name: '负荷/MW',
            max: function (value) {
                var result;
                if (value.max > 0) {
                    result = parseInt(value.max * 1.5);
                } else {
                    result = parseInt(value.max / 1.5);
                }
                return result;
            }
        },
        color: [
            '#FF3333',
            '#53FF53',
            '#B15BFF',
            '#68CFE8',
            '#FFDC35',
            '#B00'
        ],
        series: dataSeries
    };
    myChart.setOption(option);
}

function createChartsOfWeather(charstOptions, elementId, yAxisName) {
    var dataLegend = [];
    var dataXAxis = [];
    var dataSeries = [];
    for (var i = 0; i < charstOptions.length; i++) {
        dataLegend.push(charstOptions[i].legend);
        dataXAxis = charstOptions[i].hengZhou;
        var series = charstOptions[i].series;
        dataSeries.push(series);
    }
    echarts.dispose(document.getElementById(elementId));
    var myChart = echarts.init(document.getElementById(elementId));
    var option = {
        legend: {data: dataLegend},
        xAxis: {
            boundaryGap: false,
            data: dataXAxis,
            axisLabel: {
                showMaxLabel: true
            }
        },
        yAxis: {
            name: yAxisName,
            max: function (value) {
                var result;
                if (value.max > 0) {
                    result = parseInt(value.max * 1.5);
                } else {
                    result = parseInt(value.max / 1.5);
                }
                return result;
            }
        },
        tooltip: {
            trigger: 'axis'
        },
        color: [
            '#FF3333',
            '#68CFE8'
        ],
        series: dataSeries
    };
    myChart.setOption(option);
}

function createChartsOfWeatherSupport(charstOptions, elementId) {
    var dataLegend = [];
    var dataXAxis = [];
    var dataSeries = [];
    for (var i = 0; i < charstOptions.length; i++) {
        dataLegend.push(charstOptions[i].legend);
        dataXAxis = charstOptions[i].hengZhou;
        var series = charstOptions[i].series;
        var color = function (params) {
            var colorList = ['#70D9F9',
                '#EB5FA7',
                '#B4C4FF',
                '#1E9FFF',
                '#00FFFF',
                '#0000FF',
                '#8B00FF'];
            return colorList[params.dataIndex];
        };
        var itemStyleNormal = {};
        itemStyleNormal.color = color;
        var itemStyle = {};
        itemStyle.normal = itemStyleNormal;
        series.itemStyle = itemStyle;
        var labelNormal = {};
        labelNormal.show = true;
        labelNormal.position = 'inside';
        labelNormal.fontSize = 16;
        var label = {};
        label.normal = labelNormal;
        series.label = label;
        dataSeries.push(series);
    }
    echarts.dispose(document.getElementById(elementId));
    var myChart = echarts.init(document.getElementById(elementId));
    var option = {
        legend: {
            data: dataLegend,
            show: false
        },
        xAxis: {
            data: dataXAxis,
            axisLabel: {
                showMaxLabel: true
            }
        },
        yAxis: {
            name: '支持度/%'
        },
        tooltip: {
            trigger: 'axis'
        },
        series: dataSeries
    };
    myChart.setOption(option);
}

function createTree(result) {

    layui.use('tree', function () {
        layui.tree({
            elem: '#tree01',
            skin: 'sh',
            nodes: result,
            click: function (node) {
                area = node.area;
                areaname = node.name;
                areaNameTrue = node.number;
                changeData();
            }
        });
    });
}

function loadContent(idOfContent, controllerHref, dataContent) {
    $(idOfContent).load(controllerHref, dataContent, function (response, status, xhr) {
        if (xhr.status === 404) {
            layer.msg('没有该地区的数据！');
        } else if (xhr.status === 500) {
            layer.msg('程序好像出错了！');
        }
    });
}

function createChartsOfLoadAndWeather(charstOptions, elementId, analysisType) {
    var dataLegend = [];
    var dataXAxis = [];
    var dataSeries = [];
    var unit;
    var colorOfType;
    if (analysisType === "温度") {
        colorOfType = "#70D9F9";
        unit = "℃";
    } else if (analysisType === "人体舒适度") {
        colorOfType = "#EB5FA7";
        unit = "级";
    } else if (analysisType === "湿度") {
        colorOfType = "#B4C4FF";
        unit = "%";
    } else if (analysisType === "气压") {
        colorOfType = "#8B00FF";
        unit = "kPa";
    } else if (analysisType === "降雨量") {
        colorOfType = "#1E9FFF";
        unit = "mm";
    }
    var colorAll = ['#FF0000', '#5CDA9B', colorOfType];
    for (var i = 0; i < charstOptions.length; i++) {
        dataLegend.push(charstOptions[i].legend);
        dataXAxis = charstOptions[i].hengZhou;
        var series = charstOptions[i].series;
        //series.animation = false;
        dataSeries.push(series);
    }
    echarts.dispose(document.getElementById(elementId));
    var myChart = echarts.init(document.getElementById(elementId));
    var option = {
        legend: {data: dataLegend},
        xAxis: {
            boundaryGap: false,
            data: dataXAxis,
            axisLabel: {
                showMaxLabel: true
            }
        },
        yAxis: [{
            name: '负荷/MW',
            max: function (value) {
                var result;
                if (value.max > 0) {
                    result = parseInt(value.max * 1.5);
                } else {
                    result = parseInt(value.max / 1.5);
                }
                return result;
            }
        }, {
            name: analysisType + '/' + unit,
            max: function (value) {
                var result;
                if (value.max > 0) {
                    result = parseInt(value.max * 1.5);
                } else {
                    result = parseInt(value.max / 1.5);
                }
                return result;
            }
        }],
        tooltip: {
            trigger: 'axis'
        },
        color: colorAll,
        series: dataSeries
    };
    myChart.setOption(option);
}

function createChartsOfRelationSupport(charstOptions, elementId, color, type) {
    var dataLegend = [];
    var dataXAxis = [];
    var dataSeries = [];
    for (var i = 0; i < charstOptions.length; i++) {
        dataLegend.push(charstOptions[i].legend);
        dataXAxis = charstOptions[i].hengZhou;
        var series = charstOptions[i].series;
        var labelNormal = {};
        labelNormal.show = true;
        labelNormal.position = 'inside';
        labelNormal.fontSize = 16;
        var label = {};
        label.normal = labelNormal;
        series.label = label;
        dataSeries.push(series);
    }
    echarts.dispose(document.getElementById(elementId));
    var myCharts = echarts.init(document.getElementById(elementId));
    var option = {
        legend: {
            data: dataLegend,
            show: false
        },
        xAxis: {
            data: dataXAxis,
            axisLabel: {
                showMaxLabel: true
            }
        },
        color: [color],
        yAxis: {
            name: type + '/%'
        },
        tooltip: {
            trigger: 'axis'
        },
        series: dataSeries
    };
    myCharts.setOption(option);
    myCharts.on('click', function (param) {
        //factorLoading = layer.load(1);
        analysisType = type;
        var yue = param.name.substring(0, 2);

        var element = layui.element;
        layid = 'weatherFactorPageAnalysis';
        jumpTabTemp = 'weatherFactorPageAnalysis';//此变量用来影响因素分析- 全年气象因素分析- 页面的柱状图点击事件，点击后进入气象因素分析table页时所改变lay-id时所用的
        element.tabChange('influ', layid);
        factorLoading = layer.load(1);
        if (yue != null && yue != "") {
            $.ajax({
                type: "post", async: true, url: pathContent + "/periodicity/day/selectMaxDateString",    //请求发送到ShowInfoIndexServlet处
                data: {"area": area, "mdate": year + yue},
                dataType: "json",
                success: function (result) {

                    if (result != null && result != "") {
                        mdate = result;
                    } else {
                        mdate = year + yue + "01";
                    }
                    changeData();
                }
            });
        }


    });
}

function createChartsOfAirConditionerLoadSupport(charstOptions, elementId, color, type) {
    var dataLegend = [];
    var dataXAxis = [];
    var dataSeries = [];
    for (var i = 0; i < charstOptions.length; i++) {
        dataLegend.push(charstOptions[i].legend);
        dataXAxis = charstOptions[i].hengZhou;
        var series = charstOptions[i].series;
        var labelNormal = {};
        labelNormal.show = true;
        labelNormal.position = 'inside';
        labelNormal.fontSize = 16;
        var label = {};
        label.normal = labelNormal;
        series.label = label;
        dataSeries.push(series);
    }
    echarts.dispose(document.getElementById(elementId));
    var myCharts = echarts.init(document.getElementById(elementId));
    var option = {
        legend: {
            data: dataLegend,
            show: false
        },
        xAxis: {
            data: dataXAxis,
            axisLabel: {
                showMaxLabel: true
            }
        },
        color: [color],
        yAxis: {
            name: type + '/%'
        },
        tooltip: {
            trigger: 'axis'
        },
        series: dataSeries
    };
    myCharts.setOption(option);
}

function createChartsOfLoadSupport(charstOptions, elementId) {
    var dataLegend = [];
    var dataXAxis = [];
    var dataSeries = [];
    for (var i = 0; i < charstOptions.length; i++) {
        dataLegend.push(charstOptions[i].legend);
        dataXAxis = charstOptions[i].hengZhou;
        var series = charstOptions[i].series;
        var color = function (params) {
            var colorList = ['#70D9F9',
                '#EB5FA7',
                '#B4C4FF',
                '#1E9FFF',
                '#8B00FF',
                '#00FFFF'
            ];
            return colorList[params.dataIndex];
        };
        var itemStyleNormal = {};
        itemStyleNormal.color = color;
        var itemStyle = {};
        itemStyle.normal = itemStyleNormal;
        series.itemStyle = itemStyle;
        var labelNormal = {};
        labelNormal.show = true;
        labelNormal.position = 'inside';
        labelNormal.fontSize = 16;
        var label = {};
        label.normal = labelNormal;
        series.label = label;
        dataSeries.push(series);
    }

    echarts.dispose(document.getElementById(elementId));
    var supportCharts = echarts.init(document.getElementById(elementId));
    var option = {
        legend: {
            data: dataLegend,
            show: false
        },
        xAxis: {
            data: dataXAxis,
            axisLabel: {
                showMaxLabel: true
            }
        },
        yAxis: {
            name: '支持度/%'
        },
        tooltip: {
            trigger: 'axis'
        },
        series: dataSeries
    };
    supportCharts.setOption(option);
    supportCharts.on('contextmenu', function (param) {
        switch (param.name) {
            case '降雨量':
                layer.open({
                    type: 2,
                    title: '降雨量聚类曲线',
                    skin: 'layui-layer-rim', //加上边框
                    area: ['1080px', '640px'], //宽高
                    content: pathContent + '/influence/weatherAnalysis/rainClusterGraph?area=' + area + "&mdate=" + mdate
                });
                break;
            default:
                break;
        }
    });
    supportCharts.on('click', function (param) {
        factorLoading = layer.load(1);
        analysisType = param.name;
        /* ------------------------------- 通过点击不同柱状图，触发不同请求 ------------------------------- */
        if (analysisType === "温度") {
            /* ------------------------------- 更改左侧两个曲线图 ------------------------------- */
            $.ajax({
                type: "post", async: true, url: pathContent + '/influence/weatherAnalysis/changeTwoCharts',
                data: {
                    'area': area,
                    'mdate': mdate,
                    'analysisType': analysisType
                },
                dataType: "json",
                success: function (result) {

                    if (result != null) {
                        var chart = eval(result);
                        var nowList = chart.nowList;
                        var lastList = chart.nowList;
                        var chartNow = JSON.parse(nowList);
                        var chartLast = JSON.parse(lastList);
                        var titleContrast = document.getElementById("titleOfContrast");
                        titleContrast.style.display = "";
                        var titleSupport = document.getElementById("titleOfSupport");
                        titleSupport.style.display = "none";
                        var selectContrast = document.getElementById("daySelectForContrast");
                        selectContrast.style.display = "";
                        var labelSelect = document.getElementById("labelOfSelect");
                        labelSelect.style.display = "";
                        if (chartNow === null || chartNow.length === 0) {
                            document.getElementById(eWN).innerHTML = chartErrorMessage;
                        } else {
                            createChartsOfLoadAndWeather(chartNow, eWN, analysisType);
                        }
                        if (chartLast === null || chartLast.length === 0) {
                            document.getElementById(eWC).innerHTML = chartErrorMessage;
                        } else {
                            createChartsOfLoadAndWeather(chartLast, eWC, analysisType);
                        }
                    }
                }
            });
            /* ------------------------------- 聚类结果的表格展示 ------------------------------- */
            $.ajax({
                type: "post", async: true, url: pathContent + '/influence/weatherAnalysis/cluster',
                data: {
                    'area': area,
                    'mdate': mdate,
                    'analysisType': analysisType
                },
                dataType: "json",
                success: function (result) {
                    var tableData = eval(result);

                    if (result != null && result != "" && result.length > 0) {
                        if (tableData === null || tableData.length === 0) {
                            layer.close(factorLoading);
                            layer.msg("当前地区无温度聚类数据");
                            return;
                        }
                        var tempSelect = tableData[0].tempSelect;
                        var title = "";
                        if (tempSelect) {
                            title = "升温1度增长负荷";
                        } else {
                            title = "降温1度增长负荷";
                        }
                        var domTable = document.getElementById("weatherClusterTable");
                        var domInfo = document.getElementById("weatherInfo");
                        domTable.style.display = "";
                        domInfo.style.display = "none";
                        var table1 = '<img src="' + pathContent + '/images/title_log.png" height="27" width="4"><span' +
                            ' style="font-size: 130%">&nbsp;聚类指标</span>';
                        var table = '<table class="dataview"><tbody><tr class="dataview" style="width:50px"><th class="dataview">温度区间</th>';
                        table += '<th class="dataview">天数</th><th class="dataview">平均负荷</th><th class="dataview">' + title + '</th>';
                        table += '</tr>';
                        /*contentEditable="true"  如果要求表格可编辑，td后添加这个*/
                        for (var j = 0; j < tableData.length; j++) {
                            table += '<tr class="dataview"><td class="dataview">' + tableData[j].temperature + '</td>';
                            table += '<td class="dataview" style="text-decoration:underline;cursor:pointer" onclick="clusterForDay(' + tableData[j].type + ',\'' + analysisType + '\')">' + tableData[j].countDays + '</td>';
                            table += '<td class="dataview">' + tableData[j].intAvgload + '</td>';
                            table += '<td class="dataview">' + tableData[j].reload + '</td>';
                            table += '</tr>';
                        }
                        table += '</table>';
                        domTable.innerHTML = table1 + table;
                        layer.close(factorLoading);
                    } else {
                        layer.close(factorLoading);
                    }


                }
            });
        } else if (analysisType === "降雨量") {

            $.ajax({
                type: "post", async: true, url: pathContent + '/influence/weatherAnalysis/rainGraphs',
                data: {
                    'area': area,
                    'mdate': mdate
                },
                dataType: "json",
                success: function (result) {
                    if (result == null || result == "") {
                        layer.msg("无数据");
                        return;
                    }
                    echarts.dispose(document.getElementById(eWN));
                    var domEcharts = echarts.init(document.getElementById(eWN));
                    domEcharts.setOption({
                        legend: {
                            data: ['今日负荷', '未降雨日负荷', '温度', '风力', '降雨', '差值']
                        },
                        axisPointer: {
                            link: {
                                // 表示所有 xAxisIndex 为 0、3、4 和 yAxisName 为 'someName' 的坐标轴联动。
                                xAxisIndex: [0, 1]
                            }
                        },
                        xAxis: [{
                            type: 'category',
                            data: result.xAxis,
                            boundaryGap: false,
                            axisPointer: {
                                show: true
                            },
                            axisLabel: {
                                showMaxLabel: true
                            }
                        }, {
                            type: 'category',
                            gridIndex: 1,
                            data: result.xAxis,
                            boundaryGap: false,
                            axisLabel: {
                                show: false
                            },
                            axisTick: {show: false},
                            axisLine: {lineStyle: {color: '#777'}}
                        }],
                        yAxis: [{
                            name: '负荷/MW',
                            position: 'left',
                            max: function (value) {
                                var result;
                                if (value.max > 0) {
                                    result = parseInt(value.max * 1.5);
                                } else {
                                    result = parseInt(value.max / 1.5);
                                }
                                return result;
                            }
                        }, {
                            name: '影响因素',
                            position: 'right'
                        }, {
                            gridIndex: 1,
                            axisLabel: {show: false},
                            axisLine: {show: false},
                            axisTick: {show: false},
                            splitLine: {show: false}
                        }],
                        tooltip: {
                            trigger: 'axis',
                            formatter: function (params) {
                                var resultTip = params[0].axisValue + '<br/>';
                                for (var pIndex = 0; pIndex < params.length; pIndex++) {
                                    if (typeof params[pIndex].data === "object") {
                                        resultTip = resultTip + '<span style="color:' + params[pIndex].color + '">' + params[pIndex].seriesName + '</span>：' + params[pIndex].data[1];
                                    } else {
                                        resultTip = resultTip + '<span style="color:' + params[pIndex].color + '">' + params[pIndex].seriesName + '</span>：' + params[pIndex].data;
                                    }
                                    if (pIndex !== params.length - 1) {
                                        resultTip = resultTip + "<br/>";
                                    }
                                }
                                return resultTip;
                            }
                        },
                        grid: [{
                            left: '10%',
                            right: '10%',
                            top: 60,
                            height: 180
                        }, {
                            left: '10%',
                            right: '10%',
                            height: 30,
                            top: 260
                        }],
                        color: [
                            '#FF3333',
                            '#53FF53',
                            '#68CFE8',
                            '#FFDC35',
                            '#FFAEC9',
                            '#68CFE8'

                        ],
                        series: [{
                            name: '今日负荷',
                            type: 'line',
                            smooth: true,
                            data: result.rain
                        }, {
                            name: '未降雨日负荷',
                            type: 'line',
                            smooth: true,
                            data: result.nRain
                        }, {
                            name: '差值',
                            type: 'bar',
                            xAxisIndex: 1,
                            yAxisIndex: 2,
                            data: result.cRain
                        }, {
                            name: '温度',
                            data: result.tempValue,
                            yAxisIndex: 1,
                            type: 'scatter',
                            symbolSize: function (data) {
                                return data[1] * 20;
                            }
                        }, {
                            name: '风力',
                            data: result.wpValue,
                            yAxisIndex: 1,
                            type: 'scatter',
                            symbolSize: function (data) {
                                return data[1] * 20;
                            }
                        }, {
                            name: '降雨',
                            data: result.rainValue,
                            yAxisIndex: 1,
                            type: 'scatter',
                            symbolSize: function (data) {
                                return data[1] * 20;
                            }
                        }]
                    });
                    var domTable = document.getElementById("weatherClusterTable");
                    var domInfo = document.getElementById("weatherInfo");
                    domInfo.style.display = "none";
                    domTable.style.display = "";
                    var table1 = '<img src="' + pathContent + '/images/title_log.png" height="27" width="4"><span' +
                        ' style="font-size: 130%">&nbsp;待定</span>';
                    var table = '<table class="dataview"><tbody><tr class="dataview" style="width:50px"><th class="dataview" style="width: 25%">因素系数</th>';
                    table += '<th class="dataview" style="width:45%">值增加/减少</th><th class="dataview" style="width: 30%">负荷改变结果（MW）</th>';
                    table += '</tr>';
                    /*contentEditable="true"  如果要求表格可编辑，td后添加这个*/
                    table += '<tr class="dataview"><td class="dataview">温度（℃）</td>';
                    table += '<td class="dataview" >' +
                        '<div style="float: left;width: 33%"><button class="layui-btn" onclick="changeValue(\'tempValue\',-1)">\-</button></div>' +
                        '<div style="float: left;width: 30%;text-align:center"><span style="width: 38px"><input type="text" id="tempValue" name="tempValue" value="0" class="layui-input" style="width: 38px;"></span></div>' +
                        '<div style="float: left;width: 33%"><button class="layui-btn" onclick="changeValue(\'tempValue\',1)">\+</button></div></td>';
                    table += '<td class="dataview" rowspan="3" ><span id="resultValue"></span></td>';
                    table += '</tr>';
                    table += '<tr class="dataview"><td class="dataview">降雨量（mm）</td>';
                    table += '<td class="dataview" >' +
                        '<div style="float: left;width: 33%"><button class="layui-btn" onclick="changeValue(\'rainValue\',-1)">\-</button></div>' +
                        '<div style="float: left;width: 30%;text-align:center"><span style="width: 38px"><input type="text" id="rainValue" name="rainValue" value="0" class="layui-input" style="width: 38px;"></span></div>' +
                        '<div style="float: left;width: 33%"><button class="layui-btn" onclick="changeValue(\'rainValue\',1)">\+</button></div></td>';
                    table += '</tr>';
                    table += '<tr class="dataview"><td class="dataview">风力（级）</td>';
                    table += '<td class="dataview" >' +
                        '<div style="float: left;width: 33%"><button class="layui-btn" onclick="changeValue(\'wpValue\',-1)">\-</button></div>' +
                        '<div style="float: left;width: 30%;text-align:center"><span style="width: 38px"><input type="text" id="wpValue" name="wpValue" value="0" class="layui-input" style="width: 38px;"></span></div>' +
                        '<div style="float: left;width: 33%"><button class="layui-btn" onclick="changeValue(\'wpValue\',1)">\+</button></div></td>';
                    table += '</tr>';
                    table += '<tr class="dataview"><td colspan="3"><button class="layui-btn layui-btn-normal" style="width: 100%" onclick="commit()">计算</button></td></tr>';
                    table += '</table>';
                    domTable.innerHTML = table1 + table;
                    layer.close(factorLoading);
                },
                error: function () {
                    layer.msg("非暴雨天气，不展示综合因素曲线");
                    layer.close(factorLoading);
                    changeData();
                }
            });
            $.ajax({
                type: "post", async: true, url: pathContent + '/influence/weatherAnalysis/dLoadSpot',
                data: {
                    'area': area,
                    'mdate': mdate
                },
                dataType: "json",
                success: function (result) {
                    if (result !== null && result !== "") {
                        var titleContrast = document.getElementById("titleOfContrast");
                        titleContrast.style.display = "none";
                        var titleSupport = document.getElementById("titleOfSupport");
                        titleSupport.style.display = "";
                        titleSupport.innerHTML = "&nbsp;" + result.name;
                        var selectContrast = document.getElementById("daySelectForContrast");
                        selectContrast.style.display = "none";
                        var labelSelect = document.getElementById("labelOfSelect");
                        labelSelect.style.display = "none";
                        echarts.dispose(document.getElementById(eWC));
                        var domEcharts2 = echarts.init(document.getElementById(eWC));
                        domEcharts2.setOption({
                            tooltip: 'axis',
                            xAxis: {
                                name: '支持度/%'
                            },
                            yAxis: {
                                data: result.yAxis
                            },
                            color: ['#FFDC35',
                                '#68CFE8',
                                '#FFAEC9',
                                '#1E9FFF',
                                '#00FFFF',
                                '#0000FF',
                                '#8B00FF'],
                            series: {
                                name: '支持度分析',
                                type: 'bar',
                                data: result.data,
                                itemStyle: {
                                    normal: {
                                        color: function (params) {
                                            var colorList = ['#FFDC35',
                                                '#68CFE8',
                                                '#FFAEC9',
                                                '#1E9FFF',
                                                '#00FFFF',
                                                '#0000FF',
                                                '#8B00FF'];
                                            return colorList[params.dataIndex];
                                        }
                                    }
                                },
                                label: {
                                    normal: {
                                        show: true,
                                        position: 'inside',
                                        fontSize: 16
                                    }
                                }
                            }
                        });
                    }
                },
                error: function () {
                    echarts.dispose(document.getElementById(eWC));
                }
            });
        } else {
            changeData();
        }
    });
}

function createChartsOfLoadSupportOnly(charstOptions, elementId) {
    var dataLegend = [];
    var dataXAxis = [];
    var dataSeries = [];
    for (var i = 0; i < charstOptions.length; i++) {
        dataLegend.push(charstOptions[i].legend);
        dataXAxis = charstOptions[i].hengZhou;
        var series = charstOptions[i].series;
        var color = function (params) {
            var colorList = ['#70D9F9',
                '#EB5FA7',
                '#B4C4FF',
                '#1E9FFF',
                '#8B00FF',
                '#00FFFF'
            ];
            return colorList[params.dataIndex];
        };
        var itemStyleNormal = {};
        itemStyleNormal.color = color;
        var itemStyle = {};
        itemStyle.normal = itemStyleNormal;
        series.itemStyle = itemStyle;
        var labelNormal = {};
        labelNormal.show = true;
        labelNormal.position = 'inside';
        labelNormal.fontSize = 16;
        var label = {};
        label.normal = labelNormal;
        series.label = label;
        dataSeries.push(series);
    }
    echarts.dispose(document.getElementById(elementId));
    var supportCharts = echarts.init(document.getElementById(elementId));
    var option = {
        legend: {
            data: dataLegend,
            show: false
        },
        xAxis: {
            data: dataXAxis,
            axisLabel: {
                showMaxLabel: true
            }
        },
        yAxis: {
            name: '支持度/%'
        },
        tooltip: {
            trigger: 'axis'
        },
        series: dataSeries
    };
    supportCharts.setOption(option);
}

function clusterForDay(type, analysisType) {
    layer.open({
        type: 2,
        title: '聚类信息',
        skin: 'layui-layer-rim', //加上边框
        area: ['840px', '480px'], //宽高
        content: pathContent + '/influence/weatherAnalysis/allClusterData?type=' + type + '&area=' + area + "&mdate=" + mdate + "&analysisType=" + analysisType
    });
}

function changeValue(id, value) {
    var dom = document.getElementById(id);
    var temValue = dom.value * 1;
    temValue += value;
    dom.value = temValue;
}

function commit() {
    var tempValue = document.getElementById("tempValue").value * 1;
    var rainValue = document.getElementById("rainValue").value * 1;
    var wpValue = document.getElementById("wpValue").value * 1;
    $.ajax({
        type: "post", async: true, url: pathContent + '/influence/weatherAnalysis/multCalcul',
        data: {
            'area': area,
            'mdate': mdate,
            'tempValue': tempValue,
            'rainValue': rainValue,
            'wpValue': wpValue
        },
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "text",
        success: function (result) {
            document.getElementById("resultValue").innerHTML = result;
        }
    })
}

