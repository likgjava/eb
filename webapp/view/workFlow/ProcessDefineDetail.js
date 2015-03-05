//定义文件全局变量 处理方法名重复问题
var ProcessDefineForm={};
$(document).ready(function(){

	if($("#objId").val()!=""&&$("#objId").val()!="null"){
    	$.getJSON("ProcessDefineController.do?method=createOrUpdate",{objId:$("#objId").val()},function(json){
    		json2Object("ProcessDefineDetailForm",json.list[0]);
    	})
    }
	$("#close").click(function(){
		$.nyroModalRemove();
	})

})
