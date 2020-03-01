<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 39274
  Date: 2018/2/26
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-inline" style="padding:5px 0;">
    <label class="layui-form-label" for="yearSelect">选择年范围</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" name="yearSelect" id="yearSelect"
               style="width: 100px"/>
    </div>
    <form class="layui-form" style="float: right">
        <div class="layui-form-item">
            <label class="layui-form-label">节假日选择</label>
            <div class="layui-input-block" style="text-align: center;">
                <select name="holiday" lay-verify="required" lay-filter="holiday" id="holiday" style="width: 100px">
                    <c:forEach items="${result.holiday}" var="h" >
                        <option value="${h}">${h}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </form>
</div>
<div class="layui-row" style="border: 10px solid #EBEFF2;border-bottom: none">
    <div class="layui-col-md6" style="border-right: 10px solid #EBEFF2;height: 350px">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;负荷曲线</span>
        <div id="graphOfAllLoad" style="width: 100%;height:90%;"></div>
    </div>
    <div class="layui-col-md6" style="height: 350px">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;最大负荷与增长率</span>
        <button class="layui-btn layui-btn-radius layui-btn-normal" style="float:right;" value="1" onclick="toUpLoad(this,'maxLoad')">增长率曲线</button>
        <div id="graphOfMaxLoad" style="width: 100%;height:90%;"></div>
    </div>
</div>
<div class="layui-row" style="border: 10px solid #EBEFF2;">
    <div class="layui-col-md6" style="border-right: 10px solid #EBEFF2;height: 350px">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;最小负荷与增长率</span>
        <button class="layui-btn layui-btn-radius layui-btn-normal" style="float:right;" value="1" onclick="toUpLoad(this,'minLoad')">增长率曲线</button>
        <div id="graphOfMinLoad" style="width: 100%;height:90%;"></div>
    </div>
    <div class="layui-col-md6" style="height: 350px">
        <img src="${pageContext.request.contextPath}/images/title_log.png" height="27" width="4"><span
            style="font-size: 130%">&nbsp;平均负荷与增长率</span>
        <button class="layui-btn layui-btn-radius layui-btn-normal" style="float:right;" value="1" onclick="toUpLoad(this,'aveLoad')">增长率曲线</button>
        <div id="graphOfAvgLoad" style="width: 100%;height:90%;"></div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/periodicity/holiday.js"></script>
<script>
    area = ${result.area};
    areaname = '${result.areaname}';
    startYear = '${result.startYear}';
    endYear = '${result.endYear}';
    if (areaname === null || areaname === undefined || areaname === "") {
        areaname = '${result.areaname}';
    }
</script>

