$(document).ready(function(){
	$("#noticeDiv").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/bid_notice_detail.jsp");
	
	$("#auditDetail").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/purchase_file_audit_process.jsp");
	
	
	
	
	//采购文件
	
	$("#taskbook").click(function (){
		$("#noticeDiv").toggle("slow");
		$("#taskbook span").toggleClass("collapsable");	
	})
	
	//开始审核
	
	$("#beginAudit").click(function(){
		$("#auditDetail").toggle("slow");
		$("#beginAudit span").toggleClass("collapsable");
	})
	
		//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	
	//返回上一步
	$("#sure").click(function(){
		alert("确认成功！");
	})
	
	
})