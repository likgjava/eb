
var addTaskPlanListForSupe={};



//新增
addTaskPlanListForSupe.add=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择申报书明细');return false;}//是否选中
	var selectIds=$(grid).getSelects().split(',');
	var taskPlanSubIds = $("#taskPlanSubIds").val();
	if (taskPlanSubIds!=null&&''!=taskPlanSubIds) {
		var selectId = new Array();
		$.each(selectIds,function(i,n){
			selectId.push(n);
		});
		taskPlanSubIds += ','+selectId.toString();
	}
	$("#epsDialogCloseNoReload").click();
	$("#taskPlanSubIds").val(taskPlanSubIds);
	$("#consignGrid").reload();
}

//列表操作验证
addTaskPlanListForSupe.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一份申报书条目');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择只一份申报书条目');return false;}//是否只选中一个
	return true;
}
//查询条件过滤
addTaskPlanListForSupe.before=function(){
	var option={budgetName:$("#budgetName").val()};
	$('#addTaskPlanSubGrid').flexOptions({params:option});
	return true;
}

$(document).ready(function(){
	
});

