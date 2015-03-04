
$(document).ready(function(){
	
	//经办人接收
	$("#sure").click(function(){
		if($("#acceptno").attr("checked")==true){
			if(($("textarea[name='agentAcceptRemark']").val()==null || $("textarea[name='agentAcceptRemark']").val()=="")){
				alert("请填写拒绝接受意见!");
				return;
			}else{
				$.getJSON($('#initPath').val()+'/ProjectController.do?method=updateAgentAccept',formToJsonObject('agentAcceptForm'),function(json){
					if(json.result)alert(json.result);if(json.failure)return;
				});
			}
		}else{
			$.getJSON($('#initPath').val()+'/ProjectController.do?method=updateAgentAccept',formToJsonObject('agentAcceptForm'),function(json){
				if(json.result)alert(json.result);if(json.failure)return;
			});
		}
	})
	
	//上一步
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/agent_assign.jsp");
	})
	
	//下一步
	$("#next").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/sub_entrust.jsp");
	})
	
})