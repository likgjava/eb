/*
 * 执行平台，采购申报书列表
 * author: 杨兴
 * mail: yangx@gpcsoft.com
 */
var taskPlanListForSum={};

//查看详情
taskPlanListForSum.showDetail=function(name,grid)
{
	if(!taskPlanListForSum.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/BlockTradeController.do?method=showDetail&objId='+$(grid).getSelect());
}


//列表操作验证
taskPlanListForSum.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个申报书');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个申报书');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
taskPlanListForSum.before=function(){
	var option={"createUser.objId":PlatForm.user.objId,
				"auditDetail":"00",
				"useStatus":"01"}
	$('#taskPlanGrid').flexOptions({params:option});
	return true;
}

$(document).ready(function(){
	//日历控件
	$('#applyDate').epsDatepicker();
	
	//加载tabs
	$('#epsTabs').tabs();
	
});

