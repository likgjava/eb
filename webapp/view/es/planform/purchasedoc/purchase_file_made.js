$(document).ready(function(){
	
	$("#fileMade").click(function (){
		$("#purchaseFileMadeContent").toggle("slow");
		$("#fileMade span").toggleClass("collapsable");	
	
	})
	
	$("#purchaseFileMadeContent").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/purchase_file_made_content.jsp");
	
	  //加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/bid_room_apply.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/purchase_file_prove.jsp");
	})
	
})