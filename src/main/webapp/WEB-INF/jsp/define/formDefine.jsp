<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<script>

</script>
<div style="margin: 15px 0px 0px 15px">
    <div class="layui-btn-group">
        <button class="layui-btn" id="add" onclick="add()">添加</button>
    </div>
    <div class="layui-row" style="overflow: auto;">
        <table class="layui-hide" id="defineTable" lay-filter="defineEvent"></table>
    </div>
</div>

<script>
    $(function () {
        $.ajax({
            type: "post", async: true, url: pathContent + "/define/findAll",    //请求发送到ShowInfoIndexServlet处
            dataType: "json",
            success: function (result) {
                layui.use('table', function () {
                    var table = layui.table;
                    //展示已知数据
                    table.render({
                        elem: '#defineTable'
                        , height: 600
                        , width: 1500 //这个是表格的宽度
                        , even: true
                        , page: true //是否显示分页
                        , limits: [100, 150, 200]//可以选择每页显示多少条
                        , limit: 50//每页默认显示的数量
                        , size: 'lg'
                        , cols: result.dygraphs
                        , data: result.defineReturnValue
                    });
                });
            }
        });
    });

    function writes(val) {
        layer.open({
            type: 2,
            title: '编辑操作',
            shadeClose: true,
            shade: 0.8,
            area: ['800px', '500px'],
            content: '${pageContext.request.contextPath}/define/writes?id=' + val,
            end: function () {
                // 刷新页面
                location.reload();
            }
        });
    }

    function add() {
        layer.open({
            type: 2,
            title: '添加操作',
            shadeClose: true,
            shade: 0.8,
            area: ['800px', '400px'],
            content: '${pageContext.request.contextPath}/define/add',
            end: function () {
                // 刷新页面
                location.reload();
            }
        });
    }

    function popup() {
        layer.open({
            type: 2,
            title: '模板操作',
            shadeClose: true,
            shade: 0.8,
            area: ['1260px', '650px'],//这个1030是宽度，一共传过来5列数据，每一列数据的宽度是200px，200这个数是后台传过来的
            content: '${pageContext.request.contextPath}/define/templateOperator',
            end: function () {
                // 刷新页面
                location.reload();
            }
        });
    }

    function del(moduleName, val) {
        var defineConfirm = layer.confirm('删除应用模块' + moduleName + '的数据？', {
            btn: ['确定', '取消']
        }, function () {
            layer.close(defineConfirm);
            $.ajax({
                beforeSend: function () {
                    index = layer.msg('正在删除数据....', {
                        icon: 7,
                        time: 0
                    });
                },
                url: '${pageContext.request.contextPath}/define/delete',
                type: 'post',
                data: {'id': val},
                dataType: "json",
                success: function (result) {
                    if (result != null) {
                        layer.msg(result[0]);
                        setTimeout(function () {
                            // 刷新页面
                            location.reload();
                        }, 1000);
                    }
                }
            });
        });
    }

</script>