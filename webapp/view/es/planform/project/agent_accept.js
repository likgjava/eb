var list={};
//列表查询的结果集
list.rows=null;
//查询条件过滤
list.before=function(){
	return true;
}
//加载数据成功之后调用的函数
list.success=function(){}
$(document).ready(function(){
	
	// 加载项目任务书
	$("#taskInfoDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/task.jsp");
	
	// 加载任务书明细
	$("#purchaseDetailList").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/task_detail.jsp");
	
	// 加载经办人接收
	$("#agentAcceptDetail").loadPage($("#initPath").val()+"/view/es/planform/project/agent_accept_inf.jsp");
	
	// 加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	//任务书
	$("#taskInfoDiv").show();
	$("#taskbook").click(function(){
		$("#taskInfoDiv").toggle("slow");
		$("#taskbook span").toggleClass("collapsable");
	})
	
	//采购品目明细
	$("#purchaseDetailList").hide();
	$("#purchaseDetail").click(function(){
		$("#purchaseDetailList").toggle("slow");
		$("#purchaseDetail span").toggleClass("collapsable");
	})
	
	//经办人接收
	$("#agentAcceptDetail").show();
	$("#agentAccept").click(function(){
		$("#agentAcceptDetail").toggle("slow");
		$("#agentAccept span").toggleClass("collapsable");
	})
	
})