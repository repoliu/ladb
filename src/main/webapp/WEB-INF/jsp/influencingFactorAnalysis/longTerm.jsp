<%--
  Created by IntelliJ IDEA.
  User: 夏利
  Date: 2017/11/6
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/js/influ/LongTerm.js"></script>
    <script>
    /*    var diaHeight = document.body.scrollHeight;
        var diaWidth = document.body.scrollWidth;
        document.getElementById('longTermmain0').style.width = (diaWidth - 200) * 0.5;
        document.getElementById('longTermmain1').style.width = (diaWidth - 200) * 0.5;
        document.getElementById('longTermmain6').style.width = (diaWidth - 200) * 0.5;
        document.getElementById('longTermmain3').style.width = (diaWidth - 200) * 0.5;
        document.getElementById('longTermmain4').style.width = (diaWidth - 200) * 0.5;
        document.getElementById('longTermmain5').style.width = (diaWidth - 200) * 0.5;
        document.getElementById('longTermmain2').style.width = (diaWidth - 200) * 0.5;

        document.getElementById('longTermmain0').style.height = diaHeight * 0.5;
        document.getElementById('longTermmain1').style.height = diaHeight * 0.5;
        document.getElementById('longTermmain6').style.height = diaHeight * 0.5;
        document.getElementById('longTermmain3').style.height = diaHeight * 0.5;
        document.getElementById('longTermmain4').style.height = diaHeight * 0.4;
        document.getElementById('longTermmain5').style.height = diaHeight * 0.4;
        document.getElementById('longTermmain2').style.height = diaHeight * 0.4;

        $(function () {
              diaHeight = document.body.scrollHeight;
              diaWidth = document.body.scrollWidth;
        });
*/
        document.getElementById("currentArea").innerHTML = areaname;
        $("#menu").show();
    </script>
</head>
<body>
<div style="width: 100% ;height: 7px"></div>
<div class="layui-inline">
    <label class="layui-form-label">年选择</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="test2" placeholder="请选择时间..." value="">
    </div>
</div>
&nbsp;&nbsp;<button class="layui-btn layui-btn-radius" id="one" onclick="one()" value="1"><span>近1年   </span></button>
&nbsp;&nbsp;<button class="layui-btn layui-btn-radius" id="two" onclick="two()" value="3"><span>近3年 </span></button>
&nbsp;&nbsp;<button class="layui-btn layui-btn-radius" id="three" onclick="three()" value="5"><span>近5年 </span></button>
&nbsp;&nbsp;<button class="layui-btn layui-btn-radius" id="four" onclick="four()" value="10"><span>近10年 </span></button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%-- 时间范围选择框   --%>
<div class="layui-inline">
    <label class="layui-form-label">年范围</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="test7" placeholder="请选择年范围...">
    </div>
</div>
<input type="hidden" id="shiJianKuangMin" value="<%=(String)request.getAttribute("shiJianKuangMin")%>"/><%--限制时间选择框的最小时间   --%>
<input type="hidden" id="shiJianKuangMax" value="<%=(String)request.getAttribute("shiJianKuangMax")%>"/><%--限制时间选择框的最大时间   --%>
<input type="hidden" id="shiJianZu" value="<%=(String)request.getAttribute("shiJian")%>"/><%--   --%>
<input type="hidden" id="nianXian" value="<%=(String)request.getAttribute("nianXian")%>"/><%-- 代表了默认的年限  --%>
<input type="hidden" id="areaname"
       value="<%=(String)request.getAttribute("areaname")%>"/><%-- 代表了默认的地区  --%> <%--   --%>
<input type="hidden" id="anNiu" value="<%=(String)request.getAttribute("anNiu")%>"/><%--   --%>


<%--中上的两个图        --%>
<div class="layui-row"     style="height: 40%;border: 10px solid #EBEFF2">
    <div class="layui-col-xs6" style="border-right: 10px solid #EBEFF2">
        <div class="grid-demo grid-demo-bg1">
            <div id="longTermmain6"  style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">关联分析</span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
    <div class="layui-col-xs6">
        <div class="grid-demo">
            <div id="longTermmain3"  style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">人均GDP </span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
</div>


<%--中上的两个图        --%>
<div class="layui-row"
     style="height: 33%;border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2;border-bottom: 10px solid #EBEFF2">
    <div class="layui-col-xs6" style="border-right: 10px solid #EBEFF2">
        <div class="grid-demo grid-demo-bg1">
            <div id="longTermmain0"  style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">全社会用电总量</span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
    <div class="layui-col-xs6">
        <div class="grid-demo">
            <div id="longTermmain1"  style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">各行业用电量 </span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
</div>


<%--中下的两个图        --%>
<div class="layui-row"
     style="height: 33%;pxborder-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2;border-bottom: 10px solid #EBEFF2">
    <div class="layui-col-xs6" style="border-right: 10px solid #EBEFF2">
        <div class="grid-demo grid-demo-bg1">
            <div id="longTermmain4" style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">城镇居民人均收入</span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
    <div class="layui-col-xs6">
        <div class="grid-demo">
            <div id="longTermmain5" style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">三产产值占比 </span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
</div>


<%--下边的图        --%>
<div class="layui-row"  style="height: 33%"  >
    <div class="layui-col-md6"  style="border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2;border-bottom: 10px solid #EBEFF2">
        <div class="grid-demo grid-demo-bg1">
            <div id="longTermmain2" style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">生产总值</span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
</div>


</body>
</html>
