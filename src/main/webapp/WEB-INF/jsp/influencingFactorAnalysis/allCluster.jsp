<%--
  Created by IntelliJ IDEA.
  User: 39274
  Date: 2018/1/31
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>
<div class="layui-inline" style="padding:5px 0;width: 100%">
    <c:forEach items="${allDatas.clusterDatas}" var="c">
        <button class="layui-btn layui-btn-radius"
        onclick="changeType('${c.type}')" value="${c.type}" id="type${c.type}">${c.temperature}</button>
    </c:forEach>
    <button class="layui-btn layui-btn-radius"
            onclick="changeType('all')" value="all" id="typeAll">全部查询
    </button>
    <%--<span>

        <div class="layui-input-inline">
        <label class="layui-form-label" for="yearSelectForClusterAll">选择年：</label>
            <input type="text" class="layui-input" name="yearSelectForClusterAll" id="yearSelectForClusterAll"
                   style="width: 100px">
        </div>
    </span>--%>
</div>
<div>
    <table id="clusterTable" lay-filter="clusterTableFilter"></table>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    $("#menu").show();
    area = ${allDatas.area};
    year = ${allDatas.year};
    mdate = '${allDatas.mdate}';
    clusterType = '${allDatas.type}';
    analysisType = '${allDatas.analysisType}';
    layui.use(['layer', 'laydate', 'table'], function () {
        var layer = layui.layer;
        var laydate = layui.laydate;
        var table = layui.table;
        //执行一个laydate实例
        laydate.render({
            elem: '#yearSelectForClusterAll', //指定元素
            value: '${allDatas.year}',
            type: 'year',
            calendar: true,
            done: function (value) {
                year = value;
                changeType(clusterType);
            }
        });
        var tableIns = table.render({
            elem: '#clusterTable'
            , cols: [[
                {field: 'mdate', title: '日期', align: 'center'}
                , {field: 'temp', title: '温度', align: 'center'}
                , {field: 'maxload', title: '最大负荷', align: 'center'}
                , {field: 'avgload', title: '平均负荷', align: 'center'}
            ]] //设置表头

            , url: '${pageContext.request.contextPath}/influence/weatherAnalysis/tableDatas' //设置异步接口
            , where: {'year': year, 'area': area, 'type': clusterType, 'analysisType': analysisType,'mdate':mdate}
            , page: true
            , limit: 30
            , method:'post'
            , text: {
                none: '暂无数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
            }
            , initSort: {
                field: 'mdate' //排序字段，对应 cols 设定的各字段名
                , type: 'asc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
            }
            , id: 'clusterTable'
            , done: function (res, curr, count) {
                var clusterType = res.type;
                setClassByClass(clusterType);
            }
        });

    });
    function changeType(type) {
        clusterType = type;
        layui.use('table', function () {
            var table = layui.table;
            table.reload('clusterTable', {
                where: {
                    'year': year
                    , 'area': area
                    , 'type': clusterType
                    , 'analysisType': analysisType
                    ,'mdate':mdate
                }
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });
    }
    function setClassByClass(clusterType) {
        switch (clusterType) {
            case "all":
                var tempDoms = document.getElementsByClassName("layui-btn layui-btn-radius layui-btn-primary");
                for (var i = 0; i < tempDoms.length;i++) {
                    tempDoms[i].setAttribute("class", "layui-btn layui-btn-radius");
                }
                var tempDom = document.getElementById("typeAll");
                tempDom.setAttribute("class", "layui-btn layui-btn-radius layui-btn-primary");
                break;
            default:
                var tempDom = document.getElementById("type" + clusterType);
                var tempDoms = document.getElementsByClassName("layui-btn layui-btn-radius layui-btn-primary");
                for (var i = 0; i < tempDoms.length;i++) {
                    tempDoms[i].setAttribute("class", "layui-btn layui-btn-radius");
                }
                tempDom.setAttribute("class", "layui-btn layui-btn-radius layui-btn-primary");
                break;
        }
    }
</script>
</body>
</html>
