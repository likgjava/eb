$(function(){
	$("#workHistory").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	$("#makeSubmissionForm").validate();
	
	$("#next").click(function(){	
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/tender_submission.jsp");
	})
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/opening_tenders_group_list.jsp");
	})
	
	$("#reportResults").click(function (){
		$("#makeSubmissionContent").toggle("slow");
		$("#reportResults span").toggleClass("collapsable");	
	})
	
	$("#save").click(function(){
		if($("#makeSubmissionForm").valid()){
			alert("保存成功!")
		}
	})
})