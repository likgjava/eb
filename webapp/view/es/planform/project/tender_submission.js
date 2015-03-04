
var tenderSubmission ={};

$(document).ready(function(){
	$("#workHistory").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	$("#sure").click(function(){
		alert("递交成功!");
		return;
	});
	
	$("#next").click(function(){	
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/send_fair_invitation.jsp");
	})
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/esdemo/projectmanager/make_submission.jsp");
	})
});