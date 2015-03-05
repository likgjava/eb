//定义文件全局变量 处理方法名重复问题
var OrgDetailForm={};
$(document).ready(function(){

	if($("#objId").val()!=""&&$("#objId").val()!="null"){
    	$.getJSON("OrgDetailController.do?method=createOrUpdate",{objId:$("#objId").val()},function(json){
    		json2Object("OrgDetailDetailForm",json.list[0]);
    	})
    }
	$("#close").click(function(){
		$.nyroModalRemove();
	})

})