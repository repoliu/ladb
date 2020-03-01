<%--
  Created by IntelliJ IDEA.
  User: lq
  Date: 2018/9/13
  Time: 11:48
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<style type="text/css">
    .line1,.line2,.line3,line4{border-bottom:1px dashed #8E8E8E;width: 95%;margin-left: 10px;}
    .cont1,.cont2,.cont3,.cont4{width: 95%;margin: 10px 5px 10px 10px;cursor:pointer;}
</style>
<body class="layui-layout-body ">
    <div class="layui-inline" style="border-top: 10px solid #EBEFF2;width: 100%;">
        <button class="layui-btn layui-btn-radius layui-btn-normal" style="float: right;margin-top: 2.5px" onclick="airConditioner()">空调负荷分析</button>
        <div class="cont1">
            <span>&nbsp;调度&nbsp;机构：<span id="content1" style="margin-left: 10px" onclick="content(1)">国网</span></span>
        </div>
        <div class="line1"></div>
        <div class="cont2">
            <span id="content2">&nbsp;区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;域：</span>
        </div>
        <div class="line2"></div>
        <div class="cont3">
            <span id="content3">&nbsp;省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：</span>
        </div>
        <div class="line3"></div>
        <div class="cont4">
            <span style="float: left;">&nbsp;市&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</span><p id="content4" style="margin-left: 88px;"></p>
        </div>
        <div class="line4"></div>
    </div>
</body>
<script>
    $(function () {
        content(1);
        content2(9);
        content3(11);
    })
    function content(val){
        $("#content1").css("color", "red");
//        $(".line2,.cont2").show();
        $("#content2 span").remove();
        $.ajax({
            type: "post", async: true, url: pathContent + '/area/findAreaByArea',
            data: {'area': val},
            dataType: "json",
            success: function (result) {
                for (var z = 0; z < result.length; z++){
                    $("#content2").append("<span onclick='content2("+result[z].area+")'><b style='margin-left: 10px'></b>"+result[z].dname+"<b style='margin-left: 10px'></b></span>");
                }
            }
        });
    }
    function content2(val){
        $("#content1").css("color", "black");
//        $(".line3,.cont3").show();
        $("#content3 span").remove();
        $("#content2").on("click","span",function(){
            $("#content2 span").eq($(this).index()).attr("style","color:red").siblings().removeAttr("style")
        });
        $.ajax({
            type: "post", async: true, url: pathContent + '/area/findAreaByArea',
            data: {'area': val},
            dataType: "json",
            success: function (result) {
                for (var z = 0; z < result.length; z++){
                    $("#content3").append("<span onclick='content3("+result[z].area+")'><b style='margin-left: 10px'></b>"+result[z].dname+"<b style='margin-left: 10px'></b></span>");
                }
            }
        });
    }
    function content3(val){
        $("#content2 span").css("color", "black");
//        $(".line4,.cont4").show();
        $("#content4 span").remove();
        $("#content3").on("click","span",function(){
            $("#content3 span").eq($(this).index()).attr("style","color:red").siblings().removeAttr("style")
        });
        $.ajax({
            type: "post", async: true, url: pathContent + '/area/findAreaByArea',
            data: {'area': val},
            dataType: "json",
            success: function (result) {
                for (var z = 0; z < result.length; z++){
                    $("#content4").append("<span onclick='content4("+result[z].area+")'><b style='margin-left: 10px'></b>"+result[z].dname+"<b style='margin-left: 10px'></b></span>");
                }
            }
        });
    }
</script>
</html>
