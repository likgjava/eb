
$(document).ready(function(){

	$("#noticeDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/bid_notice_detail.jsp");
	$("#auditHistory").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	//采购预告
	$("#preNoticeText").click(function (){
		$("#noticeDiv").toggle("slow");	
		$("#preNoticeText span").toggleClass("collapsable");
	})
	//审批采购预告
	$("#preNoticeAffirmText").click(function(){
		$("#preNoticeAffirm").toggle("slow");	
		$("#preNoticeAffirmText span").toggleClass("collapsable");
	})
	
	$("#sure").click(function(){
		if($("#refuse").attr("checked")==true){
			alert("请填写审批不通过的原因!");
		}else{
		    alert("采购预告审批成功!");
		}
	})
	
	//上一步
	$("#pre").click(function(){
			$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/bid_notice_affirm.jsp");
	})
	
	//下一步
	$("#next").click(function(){
		if($("#refuse").attr("checked")==true){
			alert("请填写审批不通过的原因!");
		}else{
			$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/bid_notice_release.jsp");
		}
	})
	
})