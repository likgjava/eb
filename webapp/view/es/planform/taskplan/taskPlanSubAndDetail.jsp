<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>


<script>
var subAndDetail = {};

//查询条件过滤
subAndDetail.before=function(){
	var option = {};
	option.orgId = PlatForm.user.emp.company.objId;
	option.taskType = $("#taskTypes").val();
	option.ebuyMethod = $("#ebuyMethod").val();
	$('#SubGrid').flexOptions({params:option});
	$('#detailGrid').flexOptions({params:option});
	return true;
};
//汇总
subAndDetail.sum=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请至少选择个条目！');return false;}
	
	//获得选中条目的申报书id
	var ids = $(grid).getSelects().split(",");
	var planIds = "";
	for(var i=0; i< ids.length; i++){
		planIds += $("#SubGrid").getRowById(ids[i]).taskPlan.objId + ",";
	}
	planIds = planIds.substring(0,planIds.length-1);
	$("#subAndDetailBtn").attr("disabled","disabled");
	$.getJSON($('#initPath').val()+'/TaskPlanMSubController.do?method=sumMSubsByTaskPlan',{"taskPlanIds":planIds,sumTaskPlanId:$("#objId").val()}, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('#buttonClose').click();
		$("#taskPlanSubListForSumView").load($('#initPath').val()+'/view/es/planform/taskplan/taskPlanSubListForSum.jsp');
		$("#taskPlanDetailListForSumView").load($('#initPath').val()+'/view/es/planform/taskplan/taskPlanDetailListForSum.jsp');
	});
};

$(document).ready(function(){
	//加载tabs
	$('#epsTabs_1').tabs();
	
	//关闭
	$('#buttonClose').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
})
</script>

<div id="epsTabs_1">
	<ul>
		<li>
	      <a href="#subView"><span>申报书明细</span></a>
	    </li>
	    <li>
	      <a href="#detailView"><span>采购资金明细</span></a>
	    </li>
    </ul>
    <div id="subView">
    	<flex:flexgrid checkbox="true"
			id="SubGrid" url="TaskPlanMSubController.do?method=getSubNotSumSubsByOrg" queryColumns="taskPlanSub.objId" rp="10" title="申报书明细"  
				onSubmit="subAndDetail.before" usepager="false" width="830" height="auto" onSuccess="subAndDetail.success">
				<flex:flexCol name="taskPlanSub.budgetName" display="taskPlanForm.budget" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.purchaseName" display="taskPlanSubForm.purchaseName" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.unit" display="taskPlanSubForm.unit" sortable="true" width="50"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.quantity" format="amount" display="taskPlanSubForm.quantity" sortable="true" width="50"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.totalPrice" format="money" display="taskPlanSubForm.totalPrice" sortable="true" width="100"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlan.taskName" display="申报书名称" sortable="true" width="100"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlan.ebuyMethod" display="采购方式" sortable="true" width="100"align="center" alias="taskPlan.ebuyMethodCN"></flex:flexCol>
		</flex:flexgrid>
    </div>
    <div id="detailView">
    	<flex:flexgrid checkbox="false"
			id="detailGrid" url="TaskPlanMDetailController.do?method=getSubNotSumDetailsByOrg" queryColumns="" rp="10" title="采购资金明细"  
				onSubmit="subAndDetail.before"    usepager="false" width="800" height="auto" >
				<flex:flexCol name="taskPlan.taskCode" display="taskPlanForm.taskCode" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.approvalNumber" display="taskPlanDetailForm.approvalNumber" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.superiorApp"format="money" display="taskPlanDetailForm.superiorApp" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.localApp" format="money" display="taskPlanDetailForm.localApp" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.ownerApp" format="money" display="taskPlanDetailForm.ownerApp" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.otherApp" format="money" display="taskPlanDetailForm.otherApp" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlanDetail.quantity" format="money" display="taskPlanDetailForm.quantity" sortable="true" width="100"align="center"></flex:flexCol>
		</flex:flexgrid>
    </div>
</div>
<div class="partContainers">
	<div class="formLayout form2Pa">  
		<div class="conOperation">
		<button id="subAndDetailBtn" type="button" tabindex="19" onclick="subAndDetail.sum();"><span><spring:message code="globe.save"/></span></button>
		<button id="buttonClose" type="button" tabindex="19"><span><spring:message code="globe.close"/></span></button>
		</div>
	</div>
</div>