$(document).ready(function(){
	
	// 加载项目任务书
	$("#taskInfoDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/task.jsp");
	
	// 加载任务书明细
	$("#purchaseDetailList").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/task_detail.jsp");
	
	// 加载任务书明细
	$("#bidtype").loadPage($("#initPath").val()+"/view/es/planform/project/bidtype_set_inf.jsp");
	
	// 加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	//任务书
	$("#taskbook").click(function(){
		$("#taskInfoDiv").toggle("slow");
		$("#taskbook span").toggleClass("collapsable");
	})
	
	//任务书明细
	$("#purchaseDetailList").hide();
	$("#purchaseDetail").click(function(){
		$("#purchaseDetailList").toggle("slow");
		$("#purchaseDetail span").toggleClass("collapsable");
	})
	
	//设置采购方式
	$("#bidtypeset").click(function(){
		$("#bidtype").toggle("slow");
		$("#bidtypeset span").toggleClass("collapsable");
	})
	
})