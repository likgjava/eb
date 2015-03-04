$(document).ready(function(){
	
	$("#projectRulesContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_rules_content.jsp");
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	$("#projectRuleSet").click(function (){
		$("#projectRulesContent").toggle("slow");
		$("#projectRuleSet span").toggleClass("collapsable");	
	
	})
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/requirement/purchase_need.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_group_build.jsp");
	})
	
})