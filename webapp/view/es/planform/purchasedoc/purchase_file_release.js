$(document).ready(function(){
	$("#noticeDiv").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/bid_notice_detail.jsp");
	
	
	$("#fileRelease").click(function(){
		alert("成功发布");
	})
	//采购文件
	$("#taskbook").click(function (){
	$("#noticeDiv").toggle("slow");
	$("#taskbook span").toggleClass("collapsable");	
	})
	
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/purchase_file_leader.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/bid_notice_submit.jsp");
	})
	
})