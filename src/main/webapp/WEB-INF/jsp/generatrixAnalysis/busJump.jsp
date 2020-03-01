<%--
  Created by IntelliJ IDEA.
  User: 夏利
  Date: 2018/6/6
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script>




        var diaHeight = document.body.scrollHeight;
        var diaWidth = document.body.scrollWidth;
        document.getElementById('form').style.height = diaHeight*0.82;
        document.getElementById('form').style.width = diaWidth  -200;
    </script>

</head>
<body>
<input type="hidden" id="busName" value="<%=(String)request.getAttribute("busName")%>"/>


    <div  id="form"  class="layui-row" style="overflow: auto;margin-left: 40px">
        <div class="layui-col-xs6 layui-col-sm6 layui-col-md4" style="overflow: auto;" >
            <table class="layui-hide" id="busJumpTable4"></table>
        </div>
        <div class="layui-col-xs6 layui-col-sm6 layui-col-md4" style="overflow: auto;">
            <table class="layui-hide" id="busJumpTable5"></table>
        </div>
        <div class="layui-col-xs4 layui-col-sm12 layui-col-md4" style="overflow: auto;" >
            <table class="layui-hide" id="busJumpTable6"></table>
        </div>
    </div>











<script>
    var a = "${pageContext.request.contextPath}";
    $(function () {
        a = "${pageContext.request.contextPath}";
    });

</script>
<script src="${pageContext.request.contextPath}/js/generatrix/busJump.js"></script>

</body>
</html>
