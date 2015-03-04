<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>  
<script>
var projectPlanDetail={};

//查询条件过滤
projectPlanDetail.before=function(){
	var option={"project.objId":$("#projectId").val()}
	$('#projectPlanDetailGrid').flexOptions({params:option});
	return true;
}

//进入项目页面
projectPlanDetail.toProject=function(planId,projectId){
	menuTree.selectItem(planId);
	planTemplateTask.refresh(planId);
	$('#epsDialogCloseNoReload').click();
}

//查询完毕
projectPlanDetail.success=function(){
	//指定列加入html,回调(id,列dom)   例如给一个列加超级链接、弹出窗口等
	$("#projectPlanDetailGrid").flexGetColByName({
		'name':function(id,t){
			if($("#projectPlanDetailGrid").getRowById(id)["isLeaf"] == "true")
				$(t).html('<a href=javascript:projectPlanDetail.toProject(\''+id+'\',\''+$("#projectId").val()+'\')>'+$(t).html()+'</a>')
			},
		'status':function(id,t){
			if($("#projectPlanDetailGrid").getRowById(id)["isLeaf"] == "true"){
				if($(t).html() == "02")
					$(t).html("<img alt='已完成' src='"+$('#initPath').val()+"/view/resource/images/d.png'");
				else if($(t).html() == "01")
					$(t).html("<img alt='进行中' src='"+$('#initPath').val()+"/view/resource/images/p.png'");
				else	
					$(t).html("<img alt='未开始' src='"+$('#initPath').val()+"/view/resource/images/a.png'");
			}
			else
				$(t).html("");
		}
	});
}
$(document).ready(function(){
	$("#print").click(
			function(){
				var projectId= $("#projectId").val();
				window.open($('#initPath').val()+'/ProjectPlanController.do?method=toPrintProjectPlan&projectId='+projectId);
			}
	)
})
</script>
<input type="hidden" id="projectId" value="${param.projectId}" />
<div align="right"><button id="print">打印</button></div>
<div id="content">
<flex:flexgrid checkbox="false"
	id="projectPlanDetailGrid" url="ProjectPlanController.do?method=list" queryColumns="isLeaf,path,level,parent.objId,resource.objId"  
		usepager="false" title="项目计划"  height="400" tree="true"
		onSubmit="projectPlanDetail.before" onSuccess="projectPlanDetail.success">
				<flex:flexCol name="name" display="projectPlanForm.name" sortable="true" width="200"align="left"></flex:flexCol>
				<flex:flexCol name="status" display="projectPlanForm.status" sortable="true" width="40"align="left"></flex:flexCol>
				<flex:flexCol name="planStartDate" display="projectPlanForm.planStartDate" sortable="true" width="80"align="center"></flex:flexCol>
				<flex:flexCol name="planEndDate" display="projectPlanForm.planEndDate" sortable="true" width="80"align="center"></flex:flexCol>
				<flex:flexCol name="startDate" display="projectPlanForm.startDate" sortable="true" width="80"align="center"></flex:flexCol>
				<flex:flexCol name="endDate" display="projectPlanForm.endDate" sortable="true" width="80"align="center"></flex:flexCol>
				<flex:flexCol name="prePlan.name" display="projectPlanForm.prePlan" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="role.chName" display="projectPlanForm.role" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="employee.name" display="projectPlanForm.employee" sortable="true" width="100"align="left"></flex:flexCol>
</flex:flexgrid>
</div>
