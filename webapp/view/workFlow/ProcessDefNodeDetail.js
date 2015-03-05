//定义文件全局变量 处理方法名重复问题
var ProcessDefNodeForm={};
$(document).ready(function(){

	if($("#objId").val()!=""&&$("#objId").val()!="null"){
    	$.getJSON("ProcessDefNodeController.do?method=createOrUpdate",{objId:$("#objId").val()},function(json){
    		json2Object("ProcessDefNodeDetailForm",json.list[0]);
    	})
    }
	$("#close").click(function(){
		$.nyroModalRemove();
	})

})
