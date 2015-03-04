/*
 * 执行平台，采购申报书列表
 * author: yangx
 * mail: yangx@gpcsoft.com
 */
var taskPlanListForSum={};

//修改
taskPlanListForSum.update=function(name,grid){
	if(!taskPlanListForSum.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/taskplan/blockTradeFormForSum.jsp?objId='+$(grid).getSelect());
} 

//删除
taskPlanListForSum.remove=function(name,grid){
	if(!taskPlanListForSum.validation(name,grid))return;
	if(window.confirm('确定'+name+'?')){
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=remove',{objId:$(grid).getSelect()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$(grid).reload();//刷新
		});
	}
}

//查看详情
taskPlanListForSum.showDetail=function(name,grid)
{
	if(!taskPlanListForSum.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/BlockTradeController.do?method=showDetail&type=forSum&objId='+$(grid).getSelect());
}

//提交审核
taskPlanListForSum.auditAgian = function(name,grid)
{
	$.getJSON($('#initPath').val()+'/BlockTradeController.do?method=blockSubmitTaskPlan', 
			{objIds:$(grid).getSelect(),"block_check_status":"02"}, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		
		alert("已提交成功，请等待审核！")
		$(grid).reload();//刷新
	});
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
				"confirmStatus":"02",
				"useStatus":"01"}
	$('#taskPlanGrid').flexOptions({params:option});
	return true;
}

//查询下级条件过滤
taskPlanListForSum.beforeSub=function(){
	var option={"orgId":PlatForm.user.emp.companyobjId}
	$('#taskPlanSubGrid').flexOptions({params:option});
	return true;
}

$(document).ready(function(){
	//日历控件
	$('#applyDate').epsDatepicker();
	
	//加载tabs
	$('#epsTabs').tabs();
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		taskPlanListForSum.currentTabID = $(this).attr("id");
		//参数值
		var paramValue = "";
		var buttons = [];
		if(taskPlanListForSum.currentTabID == "tabs_toSubmit"){//待提交	
			buttons = [{name:"详情",bclass:"look",onpress:taskPlanListForSum.showDetail},
			           {name:"提交审核",bclass:"audit",onpress:taskPlanListForSum.auditAgian}
			];
			$('#taskPlanGrid').attr("p").url = "BlockTradeController.do?method=getBlockSubNotSumSubsByOrg&createUser.objId="+PlatForm.user.objId;
		}else if(taskPlanListForSum.currentTabID == "tabs_toSure"){//待审核
			
			buttons = [{name:"详情",bclass:"look",onpress:taskPlanListForSum.showDetail}];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=01&confirmStatus=02&taskType=01&block_check_status=02";
		}else if(taskPlanListForSum.currentTabID == "tabs_toAdjust"){//被退回
			buttons = [{name:"详情",bclass:"look",onpress:taskPlanListForSum.showDetail},
			           {name:"修改",bclass:"modify",onpress:taskPlanListForSum.update},
			           {name:"删除",bclass:"delete",onpress:taskPlanListForSum.remove},
			           {name:"提交审核",bclass:"audit",onpress:taskPlanListForSum.auditAgian}
			];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=01&confirmStatus=02&taskType=01&block_check_status=03&createUser.objId="+PlatForm.user.objId;
		}else if(taskPlanListForSum.currentTabID == "tabs_done"){//已通过
			buttons = [{name:"详情",bclass:"look",onpress:taskPlanListForSum.showDetail}
			];
			$('#taskPlanGrid').attr("p").url = "TaskPlanController.do?method=list&useStatus=01&confirmStatus=02&taskType=01&block_check_status=04&createUser.objId="+PlatForm.user.objId;
		}
		$('#taskPlanGrid').flexReDrawButtons(buttons);
		$('#taskPlanGrid').reload();
	})
});

