$(document).ready(function(){
	//指定经办人
	$("#sure").click(function(){
		if(document.getElementById("linkGovMan").options[0].selected){
			alert("请选择经办人!");
			return;
		}
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=addProjectLinkMan',formToJsonObject('agentAssignForm'),function(json){
			if(json.failure)return;
			//跳转到招标中心项目首页
    		$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForAgent&objId='+json.projectId);
		});
	})
	
	//上一步
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/branch_assign.jsp");
	})
	
	//下一步
	$("#next").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/agent_accept.jsp");
	})
})