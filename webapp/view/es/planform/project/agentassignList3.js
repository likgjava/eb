/*
 * 执行平台，采购申报书列表
 * author: yangx
 * mail: yangx@gpcsoft.com
 */
var taskPlanList={};

//指定招标编号
taskPlanList.leader = function(name,grid)
{
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/project/agentassignList3.jsp");
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=getProjectCode&objId='+$(grid).getSelect());
}

//列表操作验证
taskPlanList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个申报书');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个申报书');return false;}//是否只选中一个
	return true;
}

$(document).ready(function(){
	//日历控件
	$('#applyDate').epsDatepicker();
	
	//加载tabs
	$('#epsTabs').tabs();
	
});

