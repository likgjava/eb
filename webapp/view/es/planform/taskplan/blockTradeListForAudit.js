/*
 * 执行平台，审核采购申报书列表
 * author: 杨兴
 * mail: yangx@gpcsoft.com
 */
var taskPlanListAudit={};

//审核
taskPlanListAudit.audit = function(name,grid)
{
	
	if(!taskPlanListAudit.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/blockTradeFormForAudit.jsp?objId='+$(grid).getSelect());
}


//列表操作验证
taskPlanListAudit.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个申报书');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个申报书');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
taskPlanListAudit.before=function(){
	var option={}
	$('#taskPlanGrid').flexOptions({params:option});
	return true;
}

$(document).ready(function(){
	//日历控件
	$('#applyDate').epsDatepicker();
});

