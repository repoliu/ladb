var pathContent = a;
var layid;

var area="";
var generatrixHref=pathContent + "/DbAreaData/show";
var generatrixContentId="#generatrixContent";

var clusterAnalysisHref=pathContent + "/generatrix/clusterIndex";
var clusterAnalysisIdContent="#clusterAnalysisContent";

var characteristicIndexHref=pathContent+"/characteristicIndexController/jump";
var characteristicIdIndex="#characteristicIndex";
var dataContent;
var busName=null;

//xia add
var busClassification="#busClassification";//代表了table页的id
var busClassificationHome=pathContent+"/busClassificationController/show";//代表了要访问的Controller
var areaNameM;//母线中读取地区树的时候用的不是一个，用来显示的
var areaNameTrue;

var busJumpHome=pathContent+"/busClassificationController/tobusJump";
var busJumpTableId="#busJumpTest";




//点击小table模块进这里
layui.use(['element','layer' ], function(){
    var element = layui.element;
    var layer = layui.layer;
    layid = location.hash.replace('#generatrix=', '');
    element.tabChange('generatrix', layid);
    element.on('tab(generatrix)', function(data){
        if(data.index === 1){
            layid="busClassificationId";
            $("#selectedArea").html("选择的地区:"+"");
            treeJump();
            //layer.msg('请选择地区！', {icon: 3});//1：对勾，2：X，3：问号
            loadContent(busClassification,busClassificationHome,busName);

        }else if(data.index === 3){
            layid="generatrixId";
            areaname=areaNameM;
            $("#selectedArea").html("选择的地区:"+areaname);
            treeJump();
            loadContent(generatrixContentId,generatrixHref,dataContent);
        }else if(data.index === 4){
            layid="clusterAnalysisId";
            areaname=areaNameM;
            $("#selectedArea").html("选择的地区:"+areaname);
            tree();
            loadContent(clusterAnalysisIdContent,clusterAnalysisHref,dataContent);
        }else if(data.index === 2){
            layid="busJumpId";
            busJumpNameX=areaname;
            $("#selectedArea").html("选择的地区:"+busJumpNameX);
            treeJump();
            loadContent(busJumpTableId,busJumpHome,dataContent);
        }else if(data.index === 0){
            layid="characteristicIndexId";
            areaname=areaNameM;
            $("#selectedArea").html("选择的地区:"+areaname);
            treeJump();
            loadContent(characteristicIdIndex,characteristicIndexHref,dataContent);
        }
        location.hash = 'generatrix='+ this.getAttribute('lay-id');
    });
});


//点击母线模块加载的时候进这里
$(function(){
    areaNameM=areaname;
    pathContent = a;
    if(location.hash===''){
        location.hash = 'generatrix=characteristicIndexId';
    }
    layid = location.hash.replace(/^#generatrix=/, '');
    if (layid === "busClassificationId") {
        $("#selectedArea").html("选择的地区:"+"");
        treeJump();
        /*layui.use(['element','layer' ], function(){
            var layer = layui.layer;
            layer.msg('请选择地区！', {icon: 3});//1：对勾，2：X，3：问号
        }); */
    }else if(layid === "busJumpId"){
        $("#selectedArea").html("选择的地区:"+"");
        treeJump();
    }else if(layid === "generatrixId"){
        $("#selectedArea").html("选择的地区:"+"");
        treeJump();
    }else if(layid === "characteristicIndexId"){
        $("#selectedArea").html("选择的地区:"+"");
        treeJump();
    }else {
        tree();
    }
    if (layid === "busClassificationId") {//busClassificationId,这个是后加的母线负荷分类
        documentTree();
        //loadContent(busClassification,busClassificationHome);
        loadContent(busClassification,busClassificationHome,busName);
       // $('#busClassification').load(pathContent + 'busClassificationController/show', {"busName": busName});
    }else if(layid === "generatrixId"){
        documentTree();
        loadContent(generatrixContentId,generatrixHref);
    }else if (layid === "clusterAnalysisId"){
        tree();
        loadContent(clusterAnalysisIdContent,clusterAnalysisHref);
    }else if (layid=="busJumpId"){
        documentTree();
        loadContent(busJumpTableId,busJumpHome,busName);
    }else if (layid=="characteristicIndexId"){
        documentTree();
        loadContent(characteristicIdIndex,characteristicIndexHref,busName);
    }
});
//由于母线中母线负荷分类和其他的使用的不是一个地区树，
//这里是先把之前的展示地区树的标签删掉，在创建个新的
function documentTree() {
    var ele=document.getElementById("treeXia");
    var del_son=document.getElementById("tree01");
    // var del_son=ele.lastElementChild;  ie8不支持,以上均支持
    ele.removeChild(del_son);
    var add_son=document.createElement("ul");   // 这是一个创建P标签的方法
    add_son.id='tree01';
    ele.appendChild(add_son);
}
function tree() {//专门是地区树的方法
    $.ajax({
        type: 'post',
        async: 'true',
        url: pathContent + '/area/loadProvinceTree',
        success: function (result) {
            documentTree();
            createTree(result);

        }
    });
}

function treeJump() {
    $.ajax({
        type: 'post',
        async: 'true',
        url: pathContent + '/busClassificationController/tree',
        success: function (result) {
            documentTree();
            createTree(result);
        }
    });
}
var  trueFalseName="";
function changeData() {
 if (areaNameTrue!=""&&areaNameTrue!=null){
     //layid = location.hash.replace(/^#generatrix=/, '');
     point = $("input[name='point']:checked").val();
     if (layid=="busClassificationId"){
         trueFalseName=areaname;
         //这个是点击母线负荷分类进入的方法
         $("#selectedArea").html("选择的地区:"+areaname);
         clickTree();
     }else    if (layid === "generatrixId") {
         $("#selectedArea").html("选择的地区:"+areaname);
         areaNameM=areaname;
         createGeneraTrixTree();
     } else if (layid === "clusterAnalysisId") {
         $("#selectedArea").html("选择的地区:"+areaname);
         areaNameM=areaname;
     } else if (layid === "busJumpId") {
         areaNameM=areaname;
         $("#selectedArea").html("选择的地区:"+areaname);
         clickBusJumpTree();
     } else if (layid === "characteristicIndexId") {
         $("#selectedArea").html("选择的地区:"+areaname);
         areaNameM=areaname;
         date = $("#defaultDate").val();
         selectDataCharaJump(areaname,date);
     }
 }else {//
     point = $("input[name='point']:checked").val();
     if (layid=="busClassificationId"){
         trueFalseName=areaname;
         //这个是点击母线负荷分类进入的方法
         $("#selectedArea").html("选择的地区:"+areaname);
         clickTreeTwo();
     }else    if (layid === "generatrixId") {
         areaNameM=areaname;
         $("#selectedArea").html("选择的地区:"+areaname);
         createGeneraTrixTree();
     } else if (layid === "clusterAnalysisId") {
         $("#selectedArea").html("选择的地区:"+areaname);
         areaNameM=areaname;
     }
     else if (layid === "busJumpId") {
         areaNameM=areaname;
         $("#selectedArea").html("选择的地区:"+areaname);
         clickBusJumpTreeTwo();
     }else if (layid === "characteristicIndexId") {
         $("#selectedArea").html("选择的地区:"+areaname);
         areaNameM=areaname;
         date = $("#defaultDate").val();
         selectDataCharaJump(areaname,date);
     }
 /*    layui.use(['element','layer' ], function(){
         var layer = layui.layer;
         layer.msg('请选择最后一级地区！', {icon: 2});//1：对勾，2：X，3：问号
     });*/
 }

}

