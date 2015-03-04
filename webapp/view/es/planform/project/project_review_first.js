
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
	$("#invalidSubmission").click(function(){
		$("#invalidSubmissionContent").toggle("slow");
		$("#invalidSubmission span").toggleClass("collapsable");
	});
	
	$("#sub").click(function(){
		if(window.confirm("确定要提交?")){
			alert("提交成功!");
		}
	});
});


