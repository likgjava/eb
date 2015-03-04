
var reviewReportGrid = {};

reviewReportGrid.before=function(){
	return true;
}

reviewReportGrid.success=function(){
}

$(document).ready(function(){ 
	
	//加载工作记录
	$("#workHistory").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	//推荐成交候选人情况<
	$("#candidate").click(function(){
		$("#candidateContent").toggle("slow");
		$("#candidate span").toggleClass("collapsable");
	});
	
	//无效投标（报价）情况
	$("#invalidSubmissionContent").hide();
	$("#invalidSubmission").click(function(){
		$("#invalidSubmissionContent").toggle("slow");
		$("#invalidSubmission span").toggleClass("collapsable");
	});
	
	//关联附件
	$("#attachContent").hide();
	$("#attach").click(function(){
		$("#attachContent").toggle("slow");
		$("#attach span").toggleClass("collapsable");
	});
	
	//评审情况
	$("#reviewReportContent").hide();
	$("#reviewReport").click(function(){
		$("#reviewReportContent").toggle("slow");
		$("#reviewReport span").toggleClass("collapsable");
	});
	
	//投标单位名单
	$("#supplyContent").hide();
	$("#supply").click(function(){
		$("#supplyContent").toggle("slow");
		$("#supply span").toggleClass("collapsable");
	});
	
	//评审小组(专家信息)
	$("#evaluationContent").hide();
	$("#evaluation").click(function(){
		$("#evaluationContent").toggle("slow");
		$("#evaluation span").toggleClass("collapsable");
	});
	
	//采购代表人信息
	$("#purchaseContent").hide();
	$("#purchase").click(function(){
		$("#purchaseContent").toggle("slow");
		$("#purchase span").toggleClass("collapsable");
	});
	
	$("#upload").click(function(){
		alert("上传成功!");
	});
	
	$("#sub").click(function(){
		if(window.confirm("确定要提交?")){
			alert("提交成功!");
		}
	});
});


