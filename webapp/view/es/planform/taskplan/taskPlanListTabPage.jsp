<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
	  <flex:flexgrid checkbox="true"
		id="blockTradeGrid" url="BlockTradeController.do?method=getBlockTradeList&isComplete=true" queryColumns=""  
			searchZone="taskPlanSearchZone" rp="10"  title="待处理采购申报书列表"
			onSubmit="taskPlanListForSum.before" onSuccess="taskPlanListForSum.success">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="150" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="60" align="center"></flex:flexCol>
					<flex:flexCol name="departmentName" display="taskPlanForm.department" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="finishDate" format="date" display="taskPlanForm.finishDate" sortable="true" width="80" align="center"></flex:flexCol>
        <flex:flexBtn name="创建委托" bclass="add" onpress="taskPlanListForSum.submitConsign"></flex:flexBtn>	
	</flex:flexgrid>