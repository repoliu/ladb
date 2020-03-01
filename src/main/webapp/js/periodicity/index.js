var pathContent = a;
$("body").on("mousedown",".layui-tree a cite",function(){
    $(".layui-tree a cite").css('color','black');
    $(this).css('color','red')

});


$(function () {
    //仅在不支持 placeholder 的时候执行
    if (!('placeholder' in document.createElement('input'))) {

        var listenerName = 'attachEvent';
        var listenerPrefix = 'on';
        if ('addEventListener' in window) {
            listenerName = 'addEventListener';
            listenerPrefix = '';
        }

        window[listenerName](listenerPrefix + 'load', function() {
            var placeholderListener = {
                //添加输入项
                add: function(tagName) {
                    var list = document.getElementsByTagName(tagName);
                    for (var i = 0; i < list.length; i++) {
                        this.render(list[i]);
                    }
                    return this;
                },
                //渲染
                render: function(dom) {
                    var text = dom.getAttribute('placeholder');
                    if (!!text) {
                        this.attachEvent(dom, this.getDiv(dom, text));
                    }
                },
                //设置样式
                getDiv: function(dom, text) {
                    var div = document.createElement('div');

                    div.style.position = 'absolute';
                    div.style.width = this.getPosition(dom, 'Width') + 'px';
                    div.style.height = this.getPosition(dom, 'Height') + 'px';
                    div.style.left = this.getPosition(dom, 'Left') + 'px';
                    div.style.top = this.getPosition(dom, 'Top') + 'px';
                    div.style.color = '#91D3F8';
                    div.style.textIndent = '5px';
                    div.style.zIndex = 999;
                    div.style.background = dom.style.background;
                    div.style.border = dom.style.border;
                    div.style.cursor = 'text';
                    div.innerHTML = text;

                    if ('TEXTAREA' == dom.tagName.toUpperCase()) {
                        div.style.lineHeight = '35px';
                    } else {
                        div.style.lineHeight = div.style.height;
                    }
                    document.getElementsByTagName('body')[0].appendChild(div);
                    return div;
                },
                //计算当前输入项目的位置
                getPosition: function(dom, name, parentDepth) {
                    var offsetName = 'offset' + name;
                    var offsetVal = dom[offsetName];
                    var parentDepth = parentDepth || 0;
                    if (!offsetVal && parentDepth < 3) {
                        offsetVal = this.getPosition(dom.parentNode, name, ++parentDepth);
                    }
                    return offsetVal;
                },
                //添加事件
                attachEvent: function(dom, div) {

                    //激活时，隐藏 placeholder
                    dom[listenerName](listenerPrefix + 'focus', function() {
                        div.style.display = 'none';
                    });

                    //失去焦点时，隐藏 placeholder
                    dom[listenerName](listenerPrefix + 'blur', function(e) {
                        if (e.srcElement.value == '') {
                            div.style.display = '';
                        }
                    });

                    //placeholder 点击时，对应的输入框激活
                    div[listenerName](listenerPrefix + 'click', function(e) {
                        e.srcElement.style.display = 'none';
                        dom.focus();
                    });
                },

            };

            //防止在 respond.min.js和html5shiv.min.js之前执行
            setTimeout(function() {
                placeholderListener.add('input').add('textarea');
            }, 50);
        });
    }

    pathContent = a;
    if (location.hash === '') {
        location.hash = 'periodicity=dayLoadPage';
    }
    layid = location.hash.replace(/^#periodicity=/, '');
    $.ajax({
        type: 'post',
        async: 'true',
        //由于要显示湖南下边地区的数据所以该城这个树
        url: pathContent + '/area/loadTree',
//        url: pathContent + '/area/loadProvinceTree',
        success: function (result) {
            createTree(result);
        }
    });
    dataContent = {"point": point, "area": area, "mdate": mdate, "year": year,"startYear":startYear,"endYear":endYear};
    if (layid === "dayLoadPage") {
        loadContent(idOfDayContent, dayControllerHref, dataContent);
    } else if (layid === "yearLoadPage") {
        loadContent(idOfYearContent, yearControllerHref, dataContent);
    } else if (layid === "holidayPage") {
        loadContent(idOfHolidayContent, holidayControllerHref, dataContent);
    } else if (layid === "monthLoadPage") {
        /*  $('#months').load("../months/day/load?area="+area);*/
        $('#months').load(pathContent + '/months/day/load', {"area": area});
    } else if (layid === "weekLoadPage") {
        $('#weekContent').load(pathContent + '/periodicity/week/load', {"point": point, "area": area, "mdate": mdate});
    }
});
layui.use(['element', 'layer'], function () {
    var element = layui.element;
    var layer = layui.layer;

    layid = location.hash.replace(/^#periodicity=/, '');
    element.tabChange('periodicity', layid);
    element.on('tab(periodicity)', function (data) {
        location.hash = 'periodicity=' + this.getAttribute('lay-id');
        if (data.index === 0) {
            loadContent(idOfDayContent, dayControllerHref, dataContent);
        } else if (data.index === 2) {
            /*  $('#months').load("../months/day/load?area="+area);*/
            $('#months').load(pathContent + '/months/day/load', {"area": area});
        }
        else if (data.index === 1) {
            // $('#weekContent').load("../periodicity/week/load");
            loadContent(idOfWeekContent, weekControllerHref, dataContent);
        } else if (data.index === 3) {
            loadContent(idOfYearContent, yearControllerHref, dataContent);
        } else if (data.index === 4) {
            loadContent(idOfHolidayContent, holidayControllerHref, dataContent);
        }

    });
});
var layid;
var point = $("input[name='point']:checked").val();
var startYear;
var endYear;
var holiday;
var areaname;
var mdate;
var year;
var temparea;
var widthDay = document.getElementById('dayContent').style.width;
var idOfHolidayContent = '#holidayContent';
var idOfDayContent = '#dayContent';
var dayControllerHref = pathContent + '/periodicity/day/load';
var holidayControllerHref = pathContent + '/periodicity/holiday/load';
var idOfWeekContent = '#weekContent';
var weekControllerHref = pathContent + '/periodicity/week/load';
var idOfYearContent = '#yearContent';
var yearControllerHref = pathContent + '/periodicity/year/load';
var dataContent;

function changeData() {
    layid = location.hash.replace(/^#periodicity=/, '');
    point = $("input[name='point']:checked").val();
    dataContent = {"point": point, "area": area, "mdate": mdate, "year": year,"startYear":startYear,"endYear":endYear};
    document.getElementById("currentArea").innerHTML = areaname;
    if (layid === "dayLoadPage") {
        loadContent(idOfDayContent, dayControllerHref, dataContent);
    } else if (layid === "yearLoadPage") {
        loadContent(idOfYearContent, yearControllerHref, dataContent);
    } else if (layid === "holidayPage") {
        loadContent(idOfHolidayContent, holidayControllerHref, dataContent);
    } else if (layid === "monthLoadPage") {
        /*  $('#months').load("../months/day/load?area="+area);*/
            $('#months').load(pathContent + '/months/day/load', {"area": area});

    } else if (layid === "weekLoadPage") {
        $('#weekContent').load(pathContent + '/periodicity/week/load', {"point": point, "area": area, "mdate": mdate});
    }
}