/*
 * 执行平台，编辑需求列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var purRequirementList={};

//设定返回时的路径
purRequirementList.setRetrun=function(){
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/requirement/purRequirementList.jsp");
}

//新增
purRequirementList.add=function(name,grid){
	if(!purRequirementList.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/requirement/purRequirementDraft.jsp?objId='+$(grid).getSelect());
}
  
//修改
purRequirementList.update=function(name,grid){
	if(!purRequirementList.validation(name,grid))return;
	//跳转到修改页面
	$('#conBody').loadPage($('#initPath').val()+'/PurRequirementController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
}  

//查看申报书详细
purRequirementList.showTaskDetail = function(name,grid)
{
	if(!purRequirementList.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+$(grid).getSelect());
}

//查看需求详细
purRequirementList.showRequDetail = function(name,grid)
{
	if(!purRequirementList.validation(name,grid))return;
	$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/requirement/purRequirementDetail.jsp?objId='+$(grid).getSelect());
}

//列表操作验证
purRequirementList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一条数据');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一条数据');return false;}//是否只选中一个
	return true;
}
//申报书查询条件过滤
purRequirementList.beforeT=function(){
	var option={
			"createUser.objId":PlatForm.user.objId,
			"requeStatus":"00"
	}
	$('#taskPlanGrid').flexOptions({params:option});
	return true;
}
//需求查询条件过滤
purRequirementList.beforeP=function(){
	var option={
			"createUser.objId":PlatForm.user.objId
	}
	$('#purRequirementGrid').flexOptions({params:option});
	return true;
}
$(document).ready(function(){
	//日历控件
	$('#applyDate').epsDatepicker();
	$('#submitTime').epsDatepicker();
	
	//设定返回时的路径
	purRequirementList.setRetrun();
	
	//加载tabs
	$('#epsTabs').tabs();
	
	//隐藏需求的搜索框
	$("#purRequirementDiv").hide();
	
	//tabs的点击事件
	$("li a.refreshData").click(function(){
		purRequirementList.currentTabID = $(this).attr("id");
		if(purRequirementList.currentTabID == "tabs_toNone"){//未编辑需求
			$("#purRequirementDiv").hide();
			$("#taskPlanDiv").show();
		}else if(purRequirementList.currentTabID == "tabs_toDone"){//已编辑需求
			$("#taskPlanDiv").hide();
			$("#purRequirementDiv").show();
		}
	})
});

