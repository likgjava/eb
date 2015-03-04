
$(document).ready(function(){
	$("#projectItemList").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_item_List.jsp");
	
	$("#PackageSet").click(function (){
		$("#projectItemList").toggle("slow");
		$("#PackageSet span").toggleClass("collapsable");	
	
	})
	
	
	
	
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_group_build.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_time_set.jsp");
	})
	
	
})