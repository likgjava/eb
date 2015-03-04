

$(document).ready(function(){
	
	$("#groupSet").click(function (){
		$("#ProjectGroupList").toggle("slow");
		$("#groupSet span").toggleClass("collapsable");	
	
	})
	
	$("#ProjectGroupList").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_group_List.jsp");
	
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_rules_set.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_item_manager.jsp");
	})
	
})


