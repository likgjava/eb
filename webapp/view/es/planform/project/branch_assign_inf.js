$(document).ready(function(){
	//分配执行部门点击事件
	$("#sure").click(function(){
		if(document.getElementById("department").options[0].selected){
			alert("请选择执行部门!");
			return;
		}
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=addProjectDepart',formToJsonObject('branchAssignForm'),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
		});
	})
	
	//上一步
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/plan_accept.jsp");
	})
	
	//下一步
	$("#next").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/agent_assign.jsp");
	})
})