<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
var addTaskPlanList={}
$(document).ready(function(){

     //增加申报书
addTaskPlanList.addTaskPlan = function(name,grid)
	 {
	 	if($(grid).isSelectEmpty()){alert('请至少选择一个申报书！');return false;}
	 	var taskPlanIds = $(grid).getSelects();
	     $.getJSON($('#initPath').val()+'/ConsignController.do?method=addTaskPlans&taskPlanIds='+$(grid).getSelects()+'&consignId='+$("#consignId").val(),function(json){
	 		if(json.result)alert(json.result);if(json.failure)return;
	 		alert("添加成功！！");
	 		$("#epsDialogCloseReload").click();
	 	});
	     
	 }
	
});

</script>	
 
  <input type="hidden" id="consignId" name="consignId" value="${param.consignId}">
	  <flex:flexgrid checkbox="true" 
		id="blockTradeGrid" url="BlockTradeController.do?method=getBlockTradeList&isComplete=true" queryColumns=""  
			searchZone="taskPlanSearchZone" rp="10"  title="待处理采购申报书列表">
					<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="150" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="60" align="center"></flex:flexCol>
					<flex:flexCol name="departmentName" display="taskPlanForm.department" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="80" align="center"></flex:flexCol>
					<flex:flexCol name="finishDate" format="date" display="taskPlanForm.finishDate" sortable="true" width="80" align="center"></flex:flexCol>
        <flex:flexBtn name="添加" bclass="add" onpress="addTaskPlanList.addTaskPlan"></flex:flexBtn>	
	</flex:flexgrid>
	

	
	