$(document).ready(function(){
	
	$("#beginTime").epsDatepicker();
	$("#endTime").epsDatepicker();
	
	$("#preNoticeForm").validate();
	
	//提交采购预告
	$("#preNotice").click(function(){
		$("#preNoticeContent").toggle("slow");	
		$("#preNotice span").toggleClass("collapsable");
	})
	
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	$("#subBtn").click(function(){
		if($("#preNoticeForm").valid()){
			alert('采购预告提交成功!');
		}
	})
	
	$("#next").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/bid_notice_audit.jsp");
	})
})