<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
var taskPlanDetailListForSum={};

//查询条件过滤
taskPlanDetailListForSum.before=function(){
	var option={"taskPlan.objId":$("#objId").val(),
			"status":"01"}
	$('#taskPlanDetailForSumGrid').flexOptions({params:option});
	return true;
}
</script>

<flex:flexgrid checkbox="true"
	id="taskPlanDetailForSumGrid" url="TaskPlanMDetailController.do?method=list" queryColumns="taskPlanDetail.objId" rp="10" title="采购资金明细"  
		onSubmit="taskPlanDetailListForSum.before" usepager="false">
				<flex:flexCol name="taskPlanDetail.approvalNumber" display="taskPlanDetailForm.approvalNumber" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.superiorApp"format="money" display="taskPlanDetailForm.superiorApp" sortable="true" width="76"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.localApp" format="money" display="taskPlanDetailForm.localApp" sortable="true" width="76"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.ownerApp" format="money" display="taskPlanDetailForm.ownerApp" sortable="true" width="76"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.otherApp" format="money" display="taskPlanDetailForm.otherApp" sortable="true" width="76"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.quantity" format="money" display="taskPlanDetailForm.quantity" sortable="true" width="76"align="right"></flex:flexCol>
</flex:flexgrid>
