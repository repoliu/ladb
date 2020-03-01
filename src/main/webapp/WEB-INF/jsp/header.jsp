<%--
  Created by IntelliJ IDEA.
  User: YangSL
  Date: 2017/10/13
  Time: 11:48
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>

<body class="layui-layout-body ">

<div class="layui-layout layui-layout-admin ">
    <div class="layui-header" style="background-color: #E3EBED">
        <div class="layui-logo"><i class="layui-icon" style="font-size: 23px; color: #5BB2C3;">大数据负荷分析</i>

        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/periodicity/perIndex"><i class="layui-icon" style="font-size: 18px; color: #5BB2C3;">周期性分析</i></a>
                <%--<dl class="layui-nav-child"> <!-- 二级菜单 -->
                    <dd><a href="${pageContext.request.contextPath}/periodicity/perIndex">周期性分析</a></dd>
                    <dd><a href="">待定</a></dd>
                    <dd><a href="">待定</a></dd>
                </dl>--%>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/influence/influIndex"><i class="layui-icon" style="font-size: 18px; color: #5BB2C3;">影响因素分析</i></a>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/generatrix/generatrixIndex"><i class="layui-icon" style="font-size: 18px; color: #5BB2C3;">母线负荷分析</i></a>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/forecast/day/forecast"><i class="layui-icon" style="font-size: 18px; color: #5BB2C3;">日负荷预测</i></a>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/define/defineIndex"><i class="layui-icon" style="font-size: 18px; color: #5BB2C3;">系统设置</i></a>
            </li>
        </ul>
        <%--<ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:">
                    <img src="${pageContext.request.contextPath}/images/user.jpg" class="layui-nav-img">
                    <i class="layui-icon" style="font-size: 14px; color: #828A8C;">测试人员</i>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
        </ul>--%>
    </div>
</div>
</body>
</html>
