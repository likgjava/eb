//定义文件全局变量 处理方法名重复问题
var SchedulingDetailForm={};

$(document).ready(function(){
	if($("#scheId").val()!=""&&$("#scheId").val()!="null"){
		
    	$.getJSON($("#initPath").val()+ "/SchedulingController.do?method=getBaseObjectListByProperty",{objId:$("#scheId").val()},function(json){
    		json2ObjectDiv("SchedulingDetailForm",json[0]);
    		$("#receiverNameF").text(json[0].receiverName + "委员:");
    	})
    }
})
