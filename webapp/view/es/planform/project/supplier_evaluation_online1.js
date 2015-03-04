$(document).ready(function(){
	
	$("#supplierEvaluation").click(function (){
		$("#supplierEvaluationDetail").toggle("slow");
		$("#supplierEvaluation span").toggleClass("collapsable");	
		})
	
		//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	$("#complete").click(function(){
		alert("评审完毕");
	})
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/purchase_file_down.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/supplier_seniority_affirm.jsp");
	})
	
})