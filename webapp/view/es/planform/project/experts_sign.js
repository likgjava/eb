var expertsSign={};

$(document).ready(function(){
	//加载工作记录
	$("#workHistory").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	$("#sureBtn").click(function(){
		alert("签到成功");
	});
})