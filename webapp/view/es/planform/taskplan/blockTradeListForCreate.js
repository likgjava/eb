/*
 * 执行平台，采购申报书列表
 * author: 杨兴
 * mail: yangx@gpcsoft.com
 */
var taskPlanListForSum={};

//查看申报书详情
taskPlanListForSum.showDetail=function(name,grid)
{
	if(!taskPlanListForSum.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/BlockTradeController.do?method=showDetail&objId='+$(grid).getSelect());
}

//查看大宗项目详情
taskPlanListForSum.showProjectDetail=function(name,grid)
{
	if(!taskPlanListForSum.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toAuditBlockTaskProjectDetail&objId='+$(grid).getSelect());
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


//提交委托
taskPlanListForSum.submitConsign = function(name,grid)
{
	//跳转到修改页面
	if($(grid).isSelectEmpty()){alert('请至少选择一个申报书！');return false;}
	var taskPlanIds = $(grid).getSelects();
	$('#conBody').loadPage($('#initPath').val()+'/ConsignController.do?method=toCreateBlockTradeDraft&taskPlanIds='+$(grid).getSelects());
}



//创建大宗项目
taskPlanListForSum.createProject = function(name,grid)
{
	if($(grid).isSelectEmpty()){alert('请至少选择一个申报书！');return false;}
	var taskPlanIds = $(grid).getSelects();
    $('#conBody').loadPage($('#initPath').val()+'/BlockTradeController.do?method=toBlockTradeListForCreatePage&taskPlanIds='+$(grid).getSelects());
}

//提交审核
taskPlanListForSum.submitAudit = function(name,grid)
{
	if($(grid).isSelectEmpty()){alert('请至少选择一个项目！');return false;}
	var projectId = $(grid).getSelects();
	$.getJSON($('#initPath').val()+'/ProjectController.do?method=submitForAuditBlockTrade',{objId:projectId},function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		alert("已提交领导审核！！");
		$('#projectGrid').reload();
		$('#blockTradeGrid').reload();
	});
}


//删除大宗项目
taskPlanListForSum.deleteProject = function(name,grid)
{
	if($(grid).isSelectEmpty()){alert('请至少选择一个项目！');return false;}
	var projectId = $(grid).getSelects();
	$.getJSON($('#initPath').val()+'/ProjectController.do?method=deleteBlockTradeProject',{objId:projectId},function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('#projectGrid').reload();
		$('#blockTradeGrid').reload();
	});
}

//修改大宗项目
taskPlanListForSum.updateProject = function(name,grid)
{
	if($(grid).isSelectEmpty()){alert('请至少选择一个项目！');return false;}
	var projectId = $(grid).getSelects();
	  $('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toUpdateBlockTaskProject&objId='+projectId);
}

taskPlanListForSum.success = function(){
	$("#blockTradeGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').loadPage($('#initPath').val()+'/BlockTradeController.do?method=showDetail&objId='+rowId);
			}).appendTo(obj);
		}
	});
}

$(document).ready(function(){
	$("#taskPlanAndProjectView").loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanListTabPage.jsp');
	//日历控件
	$('#applyDate').epsDatepicker();
	
	//加载tabs
	$('#epsTabs').tabs();
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		taskPlanListForSum.currentTabID = $(this).attr("id");
		
		if(taskPlanListForSum.currentTabID == "tabs_new"){//创建项目	
			$("#li_afterSubmit").removeClass();
			$("#li_forSubmit").removeClass();
			$("#li_new").addClass('selected');
			$("#taskPlanAndProjectView").loadPage($('#initPath').val()+'/view/es/planform/taskplan/taskPlanListTabPage.jsp');
		}
	})
	
});

