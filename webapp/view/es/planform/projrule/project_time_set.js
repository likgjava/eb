$(document).ready(function(){
	
	$("#projectTimeContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_time_content.jsp");
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	$("#timeSet").click(function (){
		$("#projectTimeContent").toggle("slow");
		$("#timeSet span").toggleClass("collapsable");	
	
	})
	
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_item_manager.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/bid_room_apply.jsp");
	})
})



