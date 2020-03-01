<%--
  Created by IntelliJ IDEA.
  User: 夏利
  Date: 2018/5/25
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> </title>
    <script>
        var diaHeight = document.body.scrollHeight;
        var diaWidth = document.body.scrollWidth;
        document.getElementById('main0').style.width = (diaWidth - 200) * 0.5;
        document.getElementById('main1').style.width = (diaWidth - 200) * 0.5;
        document.getElementById('main2').style.width = (diaWidth - 200) * 0.5;
        document.getElementById('busMain0').style.width = (diaWidth - 209);
        document.getElementById('busMain1').style.width = (diaWidth - 209) ;
        document.getElementById('busMain2').style.width = (diaWidth - 209);

        document.getElementById('main0').style.height = diaHeight * 0.35;
        document.getElementById('main1').style.height = diaHeight * 0.35;
        document.getElementById('main2').style.height = diaHeight * 0.35;
        document.getElementById('main3').style.height = diaHeight * 0.35;
        document.getElementById('busMain0').style.height = diaHeight * 0.35;
        document.getElementById('busMain1').style.height = diaHeight * 0.35;
        document.getElementById('busMain2').style.height = diaHeight * 0.35;

    </script>

</head>
<body>
<input type="hidden" id="busName" value="<%=(String)request.getAttribute("busName")%>"/>
<input type="hidden" id="defaultDate" value="${defaultDate}"/>

 <%-- 这个是上边两个的栅格  --%>
<div class="layui-row" style="border: 10px solid #EBEFF2">
    <div class="layui-col-xs6" style="border-right: 10px solid #EBEFF2">
        <div id="main0" style="padding-top: 30px;"></div>

    </div>
    <div class="layui-col-xs6" >

        <div id="main1" style="padding-top: 30px; "></div>
    </div>
</div>


<%--
  这个是下边两个的栅格图--%>
<div class="layui-row" style="border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2;border-bottom: 10px solid #EBEFF2">
    <div class="layui-col-xs6" style="border-right: 10px solid #EBEFF2">
        <div id="main2" style="padding-top: 30px; "></div>
    </div>
    <div class="layui-col-xs6" >

 <%-- 这个是下边后边的栅格的放了俩图 --%>
     <div id="main3" style="padding-top: 30px;"></div>


        <%--这个是两股饼图重叠的
        --%>
    </div>
</div>
<div style="height: 5px;width: 100%"></div>
<div class="layui-inline">
    <label class="layui-form-label">年选择器</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="layuiDateSelection" placeholder="请选择日期">
    </div>
</div>

&nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" id="dateFuncton0" value="${busloadTypeSupportDate[0]}" onclick="dateFuncton0()">${busloadTypeSupportDate[0]}</button>
&nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" id="dateFuncton1" value="${busloadTypeSupportDate[1]}" onclick="dateFuncton1()">${busloadTypeSupportDate[1]}</button>
&nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" id="dateFuncton2" value="${busloadTypeSupportDate[2]}" onclick="dateFuncton2()">${busloadTypeSupportDate[2]}</button>
&nbsp;&nbsp;<button class="layui-btn layui-btn-radius"                   id="dateFuncton3" value="${busloadTypeSupportDate[3]}" onclick="dateFuncton3()">${busloadTypeSupportDate[3]}</button>
&nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" id="dateFuncton4" value="${busloadTypeSupportDate[4]}" onclick="dateFuncton4()">${busloadTypeSupportDate[4]}</button>
&nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" id="dateFuncton5" value="${busloadTypeSupportDate[5]}" onclick="dateFuncton5()">${busloadTypeSupportDate[5]}</button>
&nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" id="dateFuncton6" value="${busloadTypeSupportDate[6]}" onclick="dateFuncton6()">${busloadTypeSupportDate[6]}</button>
<div style="height: 5px;width: 100%"></div>
<div  id="busMain0"></div>
<div   id="busMain1"></div>
<div   id="busMain2"></div>


</body>


<script src="${pageContext.request.contextPath}/js/generatrix/busClassification.js"></script>
<script>

</script>
</html>
