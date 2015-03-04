$(document).ready(function(){
	$("#noticeDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/bid_notice_detail.jsp");
	$("#auditHistory").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/audit_history.jsp");
	
	$("#submitBtn").click(function(){
		//经办部门审核事件
		alert("经办部门审核事件");
		$.getJSON($('#initPath').val()+'/PurchaseDocController.do?method=addPurchaseDocStatus',formToJsonObject('auditForm'),function(json){
	    if(json.result)alert(json.result);if(json.failure)return;
		});			
	})
	
	//采购文件
	$("noticeDiv").hide();
	$("#rules").click(function (){
		$("#noticeDiv").slideToggle();	
	})
	//查看历史记录
	$("auditHistory").hide();
	$("#lookhistory").click(function(){
		$("#auditHistory").slideToggle();		
	})
	//开始审核
	$("auditDetail").show();
	$("#beginAudit").click(function(){
		$("#auditDetail").slideToggle();
	})
})