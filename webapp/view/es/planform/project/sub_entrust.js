
$(document).ready(function(){
	
	//加载提交委托
	$("#sub_entrust_inf").loadPage($("#initPath").val()+"/view/es/planform/project/sub_entrust_inf.jsp");
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
})