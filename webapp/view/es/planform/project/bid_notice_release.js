$(document).ready(function(){
	
	$("#noticeDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/bid_notice_detail.jsp");
	
	$("#auditHistory").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	//采购预告内容
	$("#preNotice").click(function(){
		$("#noticeDiv").toggle("slow");	
		$("#preNotice span").toggleClass("collapsable");
	})
	
	//发布采购预告
	$("#sub").click(function(){
		alert("发布预告成功!");
	})
	
	//上一步
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/bid_notice_leader_affirm.jsp");
	})
	
	//下一步
	$("#next").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/requirement/purchase_need_submit.jsp");
	})
})