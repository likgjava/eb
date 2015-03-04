$(document).ready(function(){
	//设置采购方式表单提交
	$("#sure").click(function(){
		if(document.getElementById("comWorkName").options[0].selected){
			alert("请选择采购方式!");
			return;
		}
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=addProjectComWork',formToJsonObject('bidtypeForm'),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
		});
	})
	
	//上一步
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/sure_entrust.jsp");
	})
	
	//下一步
	$("#next").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/number_set.jsp");
	})
})