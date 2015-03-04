/*
 * 执行平台，采购申报书列表
 * author: yangx
 * mail: yangx@gpcsoft.com
 */
var taskPlanList={};

//指定招标中心
taskPlanList.leader = function(name,grid)
{
	if(!taskPlanList.validation(name,grid))return;
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/project/agentassignList4.jsp");
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=getAgents&objId='+$(grid).getSelect());
}

//列表操作验证
taskPlanList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个申报书');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个申报书');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
taskPlanList.before=function(){
	var option={"auditDetail":"02","confirmStatus":"02"}
	$('#taskPlanGrid').flexOptions({params:option});
	return true;
}

$(document).ready(function(){
	//日历控件
	$('#applyDate').epsDatepicker();
	
	//加载tabs
	$('#epsTabs').tabs();
	
});

