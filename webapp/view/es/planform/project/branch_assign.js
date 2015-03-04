var list={};
//列表查询的结果集
list.rows=null;
//查询条件过滤
list.before=function(){
	return true;
}
//加载数据成功之后调用的函数
list.success=function(){
}

$(document).ready(function(){
	
	// 加载项目任务书
	$("#taskInfoDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/task.jsp");
	
	// 加载任务书明细
	$("#taskDetailList").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/task_detail.jsp");
	
	// 加载分配执行部门
	$("#branchAssignDetail").loadPage($("#initPath").val()+"/view/es/planform/project/branch_assign_inf.jsp");
	
	// 加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");

	//任务书
	$("#taskbook").click(function(){
		$("#taskInfoDiv").toggle("slow");
		$("#taskbook span").toggleClass("collapsable");
	})
	
	//任务书明细
	$("#taskDetailList").hide();
	$("#taskDetail").click(function(){
		$("#taskDetailList").toggle("slow");
		$("#taskDetail span").toggleClass("collapsable");
	})
	
	//分配执行部门
	$("#branchAssign").click(function(){
		$("#branchAssignDetail").toggle("slow");
		$("#branchAssign span").toggleClass("collapsable");
	})
	
})