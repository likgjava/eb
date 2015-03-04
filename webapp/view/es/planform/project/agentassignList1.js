/*
 * 执行平台，采购申报书列表
 * author: yangx
 * mail: yangx@gpcsoft.com
 */
var taskPlanList={};
var option={"auditDetail":"02","confirmStatus":"01","leader.objId":null,"leader.objId_op":"is"};

//列表操作验证
taskPlanList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个申报书');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个申报书');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
taskPlanList.before=function(){
	$('#taskPlanGrid').flexOptions({params:option});
	return true;
}

//加载数据成功之后调用的函数
taskPlanList.success=function(){
	if(taskPlanList.currentTabID == "tabs_toTemp"){
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">指定项目负责人</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$("#returnUrl").val($('#initPath').val()+"/view/es/planform/project/agentassignList1.jsp");//设定返回路径
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=getAuthUser&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}else if(taskPlanList.currentTabID == "tabs_toFormal"){
		$("#taskPlanGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$("#returnUrl").val($('#initPath').val()+"/view/es/planform/project/agentassignList1.jsp");//设定返回路径
					$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=getAuthUser&css=false&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
}

$(document).ready(function(){
	//日历控件
	$('#applyDate').epsDatepicker();
	//加载tabs
	$('#epsTabs').tabs();
	taskPlanList.currentTabID = "tabs_toTemp";
	$("li a.refreshData").click(function(){
		taskPlanList.currentTabID = $(this).attr("id");
		if(taskPlanList.currentTabID == "tabs_toTemp"){//待指定	
			option={"auditDetail":"02","confirmStatus":"01","leader.objId":null,"leader.objId_op":"is"}
		}else if(taskPlanList.currentTabID == "tabs_toFormal"){//已指定
			option={"leader.objId":null,"leader.objId_op":"!="}
		}
		$('#taskPlanGrid').reload();
	})
});