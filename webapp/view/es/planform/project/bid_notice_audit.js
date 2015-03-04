
$(document).ready(function(){

	$("#noticeDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/bid_notice_detail.jsp");

	$("#auditHistory").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	//采购预告
	$("#preNoticeText").click(function (){
		$("#noticeDiv").toggle("slow");	
		$("#preNoticeText span").toggleClass("collapsable");
	})
	//审核采购预告
	$("#preNoticeAudText").click(function(){
		$("#preNoticeAud").toggle("slow");	
		$("#preNoticeAudText span").toggleClass("collapsable");
	})
	
	$("#sure").click(function(){
		if($("#refuse").attr("checked")==true){
			if(($("textarea[name='opinion']").val()==null || $("textarea[name='opinion']").val()=="")){
				alert("请填写拒绝接受意见!");
			}else{
				alert("操作成功!");
			}
		}else{
			alert("操作成功!");
		}
	})
	
	//上一步
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/bid_notice_submit.jsp");
	})
	
	//下一步
	$("#next").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/bid_notice_affirm.jsp");
	})
	
})