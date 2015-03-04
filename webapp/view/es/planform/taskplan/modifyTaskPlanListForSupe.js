
var addTaskPlanListForSupe={};



//新增
addTaskPlanListForSupe.add=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择申报书明细');return false;}//是否选中
	var selectIds=$(grid).getSelects().split(',');
	var taskPlanSubIds = $("#taskPlanSubIds").val();
	
	for(var j = 0 ; j < selectIds.length ; j ++){
		var temp = selectIds[j];
		var old_taskPlanSubIds = taskPlanSubIds.split(',');
		
		if(old_taskPlanSubIds.length>0){//若主页面原有数据存在，则做匹配判断
			var checkHas = false;
			for(var i = 0 ; i < old_taskPlanSubIds.length ; i ++){
				if(temp == old_taskPlanSubIds[i]){//若相等，则标识为有
					checkHas = true;
				}
			}
			if(!checkHas){
				taskPlanSubIds += ","+temp;
			}
		}else{
			taskPlanSubIds = temp;
		}
	}
	$("#epsDialogCloseNoReload").click();
	//将主页面的taskPlanSubIds重置,并重刷主页面的grid
	$("#taskPlanSubIds").val(taskPlanSubIds);
	$("#consignGrid").reload();
	//alert($("#taskPlanSubIds").val());
}

//列表操作验证
addTaskPlanListForSupe.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一份申报书条目');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请选择只一份申报书条目');return false;}//是否只选中一个
	return true;
}
//查询条件过滤
addTaskPlanListForSupe.before=function(){
	var option={};
	$('#consignGrid').flexOptions({params:option});
	return true;
}

$(document).ready(function(){
	
});

