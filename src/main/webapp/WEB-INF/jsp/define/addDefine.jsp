<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/29
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加界面</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/js/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<form class="layui-form" style="margin: 20px auto; width: 50%;">
    <div class="layui-form-item">
        <label class="layui-form-label">应用模块</label>
        <div class="layui-input-block">
            <input type="text" name="appModule" required  lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">功能组件</label>
        <div class="layui-input-block">
            <input type="text" name="funComponent" required  lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
        </div>
    </div> <div class="layui-form-item">
        <label class="layui-form-label">服务名</label>
        <div class="layui-input-block">
            <input type="text" name="serviceName" required  lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
        </div>
    </div> <div class="layui-form-item">
        <label class="layui-form-label">服务号</label>
        <div class="layui-input-block">
            <input type="text" name="serviceNumber" required  lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
        </div>
    </div> <div class="layui-form-item">
        <label class="layui-form-label">程序名</label>
        <div class="layui-input-block">
            <input type="text" name="programName" required  lay-verify="required" placeholder="请输入内容" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type = "button" class="layui-btn" lay-submit lay-filter="formAdd" id="enterSubmit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formAdd)', function (data) {
            $.ajax({
                url: '${pageContext.request.contextPath}/define/addDate',
                type: 'post',
                data: $(data.form).serialize(),
                dataType: "json",
                async: true,
                beforeSend: function () {
                    index = layer.msg('正在添加数据....', {
                        icon: 7,
                        time: 0
                    });
                },
                success: function (result) {
                    if (result != null) {
                        layer.msg(result[0]);
                        setTimeout(function () {
                            // 关闭弹窗，刷新数据表格
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }, 2000);
                    }
                }
            });
            return false;
        });
    });
    //回车键触发提交      
    $("input").keydown(function (event) {
        if (event.keyCode == 13) {
            document.getElementById("enterSubmit").click();
        }
    })

</script>
</body>
</html>
