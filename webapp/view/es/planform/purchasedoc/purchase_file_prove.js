
$(document).ready(function(){ 

	$("#expertInfoDetail").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/expertInfoDetail.jsp");
	
	$("#relateItemDetail").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/relateItemDetail.jsp");
	
	$("#reviewConditionDetail").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/reviewConditionDetail.jsp");
	//关联附件
	$("#relateItem").click(function(){
	$("#relateItemDetail").toggle("slow");
	$("#relateItem span").toggleClass("collapsable");	
		
	})
	//文件论证
	$("#reviewCondition").click(function(){
	$("#reviewConditionDetail").toggle("slow");
	$("#reviewCondition span").toggleClass("collapsable");	
		
	})
	
	//论证小组(专家信息)
	$("#expertInfo").click(function(){
	$("#expertInfoDetail").toggle("slow");
	$("#expertInfo span").toggleClass("collapsable");	
		
	})

	
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/purchase_file_made.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/purchase_file_audit.jsp");
	})
	
	
});


