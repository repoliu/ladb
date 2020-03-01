<%--
  Created by IntelliJ IDEA.
  User: 夏利
  Date: 2018/1/22
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>


</head>
<body>
<div style="width: 100%;height: 10px"></div>


&nbsp;&nbsp;&nbsp;




<div class="layui-row layui-col-space5">
    <div class="layui-col-md4">
        <div class="grid-demo grid-demo-bg1" style="background: #d0d0d0">
            <table class="layui-table" lay-filter="demoEvent">
                <thead>
                <tr>
                    <th lay-data="{field: 'serviceName' width:80,page: true }">服务名</th>
                </tr>

                </thead>
                <tr>
                    <td><a onclick="addinfo()" href="" >电网总加特征值服务</a></td>
                </tr>
            </table>

        </div>
    </div>
    <div class="layui-col-md4">
        <div class="grid-demo" style="background: #d0d0d0">
            <div class="grid-demo grid-demo-bg1" style="background: #d0d0d0">
                <table class="layui-table" lay-filter="demoEvent">
                    <thead>
                    <tr>
                        <th lay-data="{field: 'serviceName' width:80}">新建</th>
                    </tr>

                    </thead>
                        <tr>
                            <td>
                                服务名:
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" id="servicename"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            数据源编码设置:
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="templateid1"/>
                            </div>
                            </td>
                        </tr>
                        <tr><td>熟数据设置:
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="templateid2" />
                            </div>
                        </td>
                        </tr>
                        <tr><td>量测编码设置:
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="templateid3" />
                            </div>
                        </td>
                        </tr>
                        <tr><td>开始时间设置:
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="templateid4" />
                            </div>
                        </td>
                        </tr>
                        <tr><td>结束时间设置:
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="templateid5" />
                            </div>
                        </td>
                        </tr>
                        <tr><td>数据周期设置:
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="templateid6" />
                            </div>
                        </td>
                        </tr>
                        <tr><td>数据步长设置:
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input"  id="templateid7"/>
                            </div>
                        </td>
                        </tr>
                        <tr><td>特征值类型设置:
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="templateid8"/>
                            </div>
                        </td>
                        </tr>
                        <tr><td>特征值统计周期设置:
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="templateid9"/>
                            </div>
                        </td>
                        </tr>

                </table>
            </div>
            <button class="layui-btn layui-btn-radius " id="rain6" onclick="addinfo()" style="float: right">确定</button>
        </div>
    </div>
    <div class="layui-col-md4">
        <div class="grid-demo grid-demo-bg1" style="background: #d0d0d0">
            <div class="grid-demo grid-demo-bg1" style="background: #d0d0d0">
                <table class="layui-hide" id="demo2" lay-filter="demo2"></table>
            </div>
        </div>
    </div>
</div>



<script>
    //日期选择器js
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#templateid4'//指定元素
            , format: 'yyyy-MM-dd', //可任意组合
        });
        laydate.render({
            elem: '#templateid5'//指定元素
            , format: 'yyyy-MM-dd', //可任意组合
        });
    });



    function addinfo() {
        var layer = layui.layer;
        var servicename=$("#servicename").val();
        var templateid1=$("#templateid1").val();
        var templateid2=$("#templateid2").val();
        var templateid3=$("#templateid3").val();
        var templateid4=$("#templateid4").val();
        var templateid5=$("#templateid5").val();
        var templateid6=$("#templateid6").val();
        var templateid7=$("#templateid7").val();
        var templateid8=$("#templateid8").val();
        var templateid9=$("#templateid9").val();

        $.ajax({
            type: "post", async: true, url:"/templateOperController/addTemplateInfo",    //请求发送到ShowInfoIndexServlet处
            dataType: "json",
            async: false,
            data: {'servicename': servicename,'templateid1': templateid1,'templateid2': templateid2,'templateid3': templateid3,
                'templateid4': templateid4,'templateid5': templateid5,'templateid6': templateid6,'templateid7': templateid7,
                'templateid8': templateid8,'templateid9': templateid9},
            success: function (result) {
                layer.msg(result[0]);
            }
        });
        //采用ajax同步操作，先插入后加载信息
        loadTemplateInfo()
    }

    function loadTemplateInfo(){

        $.ajax({
            type: "get", async: true, url:"/templateOperController/templateInfo",    //请求发送到ShowInfoIndexServlet处
            dataType: "json",
            async: false,
            success: function (result) {
                layui.use('table', function(){
                    var table = layui.table;
                table.render({
                    elem: '#demo2'
                    ,cols: [[ //标题栏
                        {field: 'servicename', title: '服务名',  sort: true},
                        {field: 'servicenumber', title: '服务号',sort: true}
                    ]],
                    data: result
                    //,skin: 'line' //表格风格
                    //,even: true
                    ,page: true //是否显示分页
                    //,limits: [5, 7, 10]
                    //,limit: 5 //每页默认显示的数量
                });
                });
            }
        });
    }
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#demo1'
            ,cols: [[ //标题栏
                {field: 'serviceName', title: '服务名称', event: 'refresh', sort: true}
            ]]
            ,data: [{
                "serviceName": "电网总加特征值服务"
            }]
            //,skin: 'line' //表格风格
            ,even: true
            ,page: true //是否显示分页
            //,limits: [5, 7, 10]
            //,limit: 5 //每页默认显示的数量
        });




    });
    loadTemplateInfo()



</script>



</body>
</html>
