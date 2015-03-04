/*
 * 执行平台，采购申报书列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanList={};


//设定返回时的路径
taskPlanList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/TaskPlanController.do?method=toTaskPlanListPageForConsign");
}

//新增
taskPlanList.add=function(name,grid){
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+"/TaskPlanController.do?method=toSaveOrUpdate");
}  

//修改
taskPlanList.update=function(name,grid){
	if(!taskPlanList.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSaveOrUpdate&objId='+$(grid).getSelect());
} 

//发起委托
taskPlanList.consign=function(name,grid){
	if(!taskPlanList.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/ConsignController.do?method=toDraft&isSave=NO&taskPlanId='+$(grid).getSelect());
} 

//删除
taskPlanList.remove=function(name,grid){
	if(!taskPlanList.validation(name,grid))return;
	if(window.confirm('确定'+name+'?')){
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=remove',{objId:$(grid).getSelect()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//查看详情
taskPlanList.showDetail=function(name,grid){
	if(!taskPlanList.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+$(grid).getSelect());
}

//提交
taskPlanList.auditAgian = function(name,grid)
{
	if(!taskPlanList.validation(name,grid))return;
	if(window.confirm('确定要保存为正式申报书，并提交上级吗？')){
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=submitTaskPlan', 
				{objIds:$(grid).getSelects(),"useStatus":"01","confirmStatus":"00","auditDetail":"00"}, function(json){
		
			if(json.isTaskPlanSub == 'YES')
			{
				alert("已保存为正式申报书！请等待上级汇总或审核")
			}
			else
			{
				alert("请录入申报书明细！")
			}
			$(grid).reload();//刷新
		});
	}
}

//列表操作验证
taskPlanList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个申报书');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个申报书');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
taskPlanList.before=function(){
	var option={"order":"createTime",
				"order_flag":true,
				"useStatus":"01",
				"auditDetail":"02",
				"confirmStatus":"06",
				"taskAgent.objId":$("#curOrgId_temp").val(),
				"taskType":"00"}
	$('#taskPlanGrid').flexOptions({params:option});
	return true;
}

taskPlanList.success = function(){
	$("#taskPlanGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				taskPlanList.setRetrun();
				$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+rowId);
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">起草委托</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				taskPlanList.setRetrun();
				$('#conBody').loadPage($('#initPath').val()+'/ConsignController.do?method=toCreateConsign&taskPlanId='+rowId);
			}).appendTo(obj);
		}
	});
}

$(document).ready(function(){
	//日历控件
	$('#applyDate').epsDatepicker();
	
	//设定返回时的路径
	taskPlanList.setRetrun();
	
	//加载tabs
	$('#epsTabs').tabs();
	
	
});

