<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/18
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<style type="text/css">
    /*用于周页面周指标进度条指定属性*/
    div.bar {
        margin: 20px 10px;
    }
    .layui-col-md4{
        width: 33.3%\0;
        float: left;
    }
    .layui-col-md3{
        width: 25%\0;
        float: left;
    }
    .layui-col-md9{
        width: 75%\0;
        float: left;
    }
    .layui-col-xs4{
        width: 33.3%\0;
        float: left;
    }
</style>
<html>
<head>
    <title></title>
    <script src="${pageContext.request.contextPath}/js/periodicity/Months.js"></script>
    <script>
        var  diaHeight=document.body.scrollHeight;
        var diaWidth = document.body.scrollWidth;
        document.getElementById('main0').style.height=diaHeight*0.4;
        document.getElementById('main1').style.height=diaHeight*0.4;
        document.getElementById('main2').style.height=diaHeight*0.4;
        document.getElementById('main3').style.height=diaHeight*0.4;
        document.getElementById('main4').style.height=diaHeight*0.4;
        document.getElementById('main5').style.height=diaHeight*0.4;

        document.getElementById('main0').style.width=(diaWidth-200)*0.33;
        document.getElementById('main1').style.width=(diaWidth-200)*0.33;
        document.getElementById('main2').style.width=(diaWidth-200)*0.33;
        document.getElementById('main3').style.width=(diaWidth-200)*0.33;
        document.getElementById('main4').style.width=(diaWidth-200)*0.33;
        document.getElementById('main5').style.width=(diaWidth-200)*0.33;


    </script>
</head>
<body>
<div style="margin-bottom: 0px">
    <label class="layui-form-label">查询月期：</label>
    <div class="layui-inline">
        <input type="text" class="layui-input" id="maxSelect" placeholder="请选择时间..." name="shiJian">
    </div>
    &nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" id="one" value="${one}" onclick="one()">${one}</button>
    &nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" id="two" value="${two}" onclick="two()">${two}</button>
    &nbsp;&nbsp;<button class="layui-btn layui-btn-radius" id="three" value="${three}" onclick="three()">${three}</button>
    &nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" id="four" value="${four}" onclick="four()">${four}</button>
    &nbsp;&nbsp;<button class="layui-btn layui-btn-radius layui-btn-primary" id="five" value="${five}" onclick="five()">${five}</button>

    <div style="width: 10%;height: 20px"></div>
    <%--<button class="layui-btn layui-btn-radius" onclick="shiJian()" id="shiJian">确定</button>--%>
</div>
<div class="layui-row"  style="border: 10px solid #EBEFF2">
    <div class="layui-col-md3"  style="border-right: 10px solid #EBEFF2"><%--地图    --%>
        <div class="items" id="Item1" style="margin: 0 auto;width: 240px">
            <a href="javascript:" class="fold"></a>
            <div class="itemCon">
                <div id="ChinaMap2"></div>
            </div>
        </div>
    </div>
    <div class="layui-col-md9"><%--进度条    --%>
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
                style="font-size: 130%">&nbsp;负荷情况</span><br>
        <div class="layui-col-md4">
            <div class="grid-demo grid-demo-bg1" style="margin: 20px 10px">
                <span>月平均负荷</span><span style="float: right" id="aveload"></span>
                <div class="layui-progress" lay-filter="aveloads">
                    <div class="layui-progress-bar layui-bg-green"></div>
                </div>
                <br/>
                <span>月最小负荷</span><span style="float: right" id="minload"></span>
                <div class="layui-progress" lay-filter="minloads">
                    <div class="layui-progress-bar layui-bg-green"></div>
                </div>
                <br/>
                <span>月最大负荷</span><span style="float: right" id="maxload"></span>
                <div class="layui-progress" lay-filter="maxloads">
                    <div class="layui-progress-bar layui-bg-green"></div>
                </div>
                <br/>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="grid-demo" style="margin: 20px 10px">
                <span>月平均峰谷差</span><span style="float: right" id="differ"></span>
                <div class="layui-progress" lay-filter="differs">
                    <div class="layui-progress-bar layui-bg-red"></div>
                </div>
                <br/>
                <span>月最大峰谷差率</span><span style="float: right" id="differrate"></span>
                <div class="layui-progress" lay-filter="differrate">
                    <div class="layui-progress-bar layui-bg-red" lay-percent="45"></div>
                </div>
                <br/>
                <span>月负荷率</span><span style="float: right" id="loadrate"></span>
                <div class="layui-progress" lay-filter="loadrate">
                    <div class="layui-progress-bar layui-bg-red"></div>
                </div>
                <br/>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="grid-demo grid-demo-bg1" style="margin: 20px 10px">
                <span>月最小负荷率</span><span style="float: right" id="minloadrate"></span>
                <div class="layui-progress" lay-filter="minloadrate">
                    <div class="layui-progress-bar layui-bg-orange"></div>
                </div>

                <br/>
                <span>月平均谷差率</span><span style="float: right" id="differratePing"></span>
                <div class="layui-progress" lay-filter="differratePing">
                    <div class="layui-progress-bar  layui-bg-orange"></div>
                </div>
                <br/>
            </div>
        </div>
    </div>
</div>
<div style="width: 1065px  " id="zhexian">
    <%-- 折线图  --%>

</div>
<%-- 这是个隐藏域，用来保存时间的  --%>
<input type="hidden" id="shiJianZu" value="<%=(String)request.getAttribute("shiJian")%>"/>
<input type="hidden" id="areaname" value="<%=(String)request.getAttribute("areaname")%>"/>
<input type="hidden" id="timeMin" value="<%=(String)request.getAttribute("xianZhiMin")%>"/>
<input type="hidden" id="timeMax" value="<%=(String)request.getAttribute("xianZhiMax")%>"/>
<%--<div class="layui-row"  style="height: 400px;border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2;border-bottom: 10px solid #EBEFF2;">
    <div class="layui-col-md4"  style="height: 400px;border-right: 10px solid #EBEFF2">
        <div class="grid-demo grid-demo-bg1">
            <div style="height: 380px" id="main0"></div>
        </div>
        <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">典型工作日曲线图</span></div>
        <div style="height: 30px ;width: 10%"></div>
    </div>
    <div class="layui-col-md4" style="height: 400px;border-right: 10px solid #EBEFF2">
        <div class="grid-demo grid-demo-bg1">
            <div style="height: 380px" id="main1"></div>
        </div>
        <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">最大负荷日曲线图</span></div>
        <div style="height: 30px ;width: 10%"></div>
    </div>
    <div class="layui-col-md4">
        <div class="grid-demo">
            <div style="height: 380px" id="main2"></div>
        </div>
        <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">最大峰谷日曲线图</span></div>
        <div style="height: 30px ;width: 10%"></div>
    </div>

</div>--%>



<div class="layui-row"   style="height: 400px;border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2;border-bottom: 10px solid #EBEFF2;">
    <div class="layui-col-xs4" style="border-right: 10px solid #EBEFF2">
        <div class="grid-demo grid-demo-bg1" style="height: 410px">
            <div id="main0"  style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">典型工作日曲线图</span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
    <div class="layui-col-xs4"  style="border-right: 10px solid #EBEFF2">
        <div class="grid-demo" style="height: 410px">
            <div id="main1"  style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">最大负荷日曲线图 </span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
    <div class="layui-col-xs4">
        <div class="grid-demo" style="height: 410px">
            <div id="main2"  style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">最大峰谷日曲线图 </span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
</div>


<%--

<div class="layui-row" style="height: 400px;border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2;border-bottom: 10px solid #EBEFF2">
    <div class="layui-col-md4" style="border-right: 10px solid #EBEFF2;height: 400px">
        <div class="grid-demo">
            <div style="height: 380px" id="main3"></div>
        </div>
        <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">典型休息日曲线图</span></div>
        <div style="height: 30px ;width: 10%"></div>
    </div>
    <div class="layui-col-md4" style="border-right: 10px solid #EBEFF2;height: 400px">
        <div class="grid-demo grid-demo-bg1">
            <div style="height: 380px" id="main4"></div>
        </div>
        <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">最小负荷日曲线图</span></div>
        <div style="height: 30px ;width: 10%"></div>
    </div>
    <div class="layui-col-md4">
        <div class="grid-demo grid-demo-bg1">
            <div style="height: 380px" id="main5"></div>
        </div>
        <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">最小峰谷日曲线图</span></div>
        <div style="height: 30px ;width: 10%"></div>
    </div>
</div>
--%>


<div class="layui-row"      style="height: 400px;border-right: 10px solid #EBEFF2;border-left: 10px solid #EBEFF2;border-bottom: 10px solid #EBEFF2">
    <div class="layui-col-xs4" style="border-right: 10px solid #EBEFF2">
        <div class="grid-demo grid-demo-bg1" style="height: 410px">
            <div id="main3" style="height: 100%" ></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">典型休息日曲线图</span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
    <div class="layui-col-xs4"  style="border-right: 10px solid #EBEFF2">
        <div class="grid-demo"  style="height: 410px">
            <div id="main4"  style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">最小负荷日曲线图 </span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
    <div class="layui-col-xs4">
        <div class="grid-demo"  style="height: 410px">
            <div id="main5"  style="height: 100%"></div>
            <div style="margin:-30px auto;text-align:center"><span style="font-size: 17px">最小峰谷日曲线图 </span></div>
            <div style="height: 30px ;width: 100%"></div>
        </div>
    </div>
</div>

</body>
</html>
