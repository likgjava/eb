
$(document).ready(function(){
	
	//加载项目任务书
	$("#taskInfoDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/task.jsp");
	
	//加载设置招标编号
	$("#taskDetailList").loadPage($("#initPath").val()+"/view/es/planform/project/number_set_inf.jsp");
	
	//加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	//任务书
	$("#taskInfoDiv").show();
	$("#taskbook").click(function(){
		$("#taskInfoDiv").toggle("slow");
		$("#taskbook span").toggleClass("collapsable");
	})
	
	//设置招标编号
	$("#taskDetailList").show();
	$("#numberset").click(function(){
		$("#taskDetailList").toggle("slow");
		$("#numberset span").toggleClass("collapsable");
	})
	
})