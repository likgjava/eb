$(document).ready(function(){
	$("#noticeDiv").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/bid_notice_detail.jsp");
	
	$("#affirmDetail").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/purchase_file_leader_process.jsp");
	
	
	//采购文件
	$("#taskbook").click(function (){
		$("#noticeDiv").toggle("slow");
		$("#taskbook span").toggleClass("collapsable");	
	})
	//开始审批
		$("#beginAffirm").click(function(){
		$("#affirmDetail").toggle("slow");
		$("#beginAffirm span").toggleClass("collapsable");
	})
    
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/purchase_file_affirm.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/purchase_file_release.jsp");
	})
	
	
	
})
