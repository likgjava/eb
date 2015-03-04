<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script>
var taskPlanListRole={};

taskPlanListRole.success=function(){
	$("#taskPlanGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&noConsign=true&objId='+rowId);
			}).appendTo(obj);
		}
	});

	$("#taskPlanGrid").flexGetColByName({
		'confirmStatus':function(id,t){
			var json = $("#taskPlanGrid").flexGetRowJsonById(id);
			var confirmStatus = json['confirmStatus'];
			var useStatus = json['useStatus'];
			var auditDetail = json['auditDetail'];
			var leaderId = json['leader.objId'];
			$(t).html(taskPlanListRole.getStatus(confirmStatus,useStatus,auditDetail,leaderId));
		}
	});
}

taskPlanListRole.getStatus = function(confirmStatus,useStatus,auditDetail,leaderId){
	var confirm = confirmStatus;
	var status = useStatus;
	var audit = auditDetail;
	var leader = leaderId;
	if ("00"==confirm&&"01"==status&&"00"==audit) {//待上级招标单位审核申报书
		return "待上级招标单位审核";
	} else if ("00"==confirm&&"01"==audit) {//待业务处室审核采购资金
		return "待业务处室审核资金";
	} else if ("01"==confirm&&"02"==audit&&(leader==null||leader=='')) {//待采购办管理员审核采购方式
		return "待采购办审核采购方式";
	} else if ("01"==confirm&&"02"==audit&&(leader!=null&&leader!='')) {//待采购办审核申报书
		return "待采购办审核申报书";
	} else if ("02"==confirm&&"02"==audit&&(leader!=null&&leader!='')) {//待招标单位抽取招标中心
		return "待招标单位抽取招标中心";
	} else if ("05"==confirm&&"02"==audit&&(leader!=null&&leader!='')) {//招标单位指定招标中心不通过
		return "招标单位指定招标中心不通过";
	} else if ("07"==confirm&&"02"==audit&&(leader!=null&&leader!='')) {//待采购办审核招标中心
		return "待采购办审核招标中心";
	} else if ("06"==confirm&&"02"==audit&&(leader!=null&&leader!='')) {//审核通过
		return "审核通过";
	} else {//待提交申报书
		return "待提交";
	}
}

$(document).ready(function(){
	//日历控件
	$('#applyDate').epsDatepicker();
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/view/es/planform/taskplan/taskPlanListForRole.jsp");
	
});
</script>
<style type="text/css">
<!--
a.abtn {
text-decoration:underline;
text-align: center;
}
--> 
</style>
<form id="taskPlanSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li>
				<spring:message code="taskPlanForm.taskCode" />：
		    	<input type="text" name="taskCode" value="">
				<input type="hidden" name="taskCode_op" value="like">
				<spring:message code="taskPlanForm.taskName" />：
		    	<input type="text" name="taskName" value="">
				<input type="hidden" name="taskName_op" value="like">
				<spring:message code="taskPlanForm.applyDate" />：
		    	<input class="sysicon siDate" id="applyDate" name="applyDate" style="width: 70px;">
			</li>
			<li class="operationBtnDiv">
				<button type="submit"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
	</div>
</form>
<flex:flexgrid checkbox="false"
	id="taskPlanGrid" url="TaskPlanController.do?method=list" queryColumns="useStatus,auditDetail,confirmStatus,leader.objId"  
		searchZone="taskPlanSearchZone"  rp="10" title="采购申报书列表"   
		onSubmit="taskPlanListRole.before" onSuccess="taskPlanListRole.success">
				<flex:flexCol name="taskCode" display="taskPlanForm.taskCode" sortable="true" width="150" align="center"></flex:flexCol>
					<flex:flexCol name="taskName" display="taskPlanForm.taskName" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN"  display="taskPlanForm.ebuyMethod" sortable="true" width="100" align="center"></flex:flexCol>
					<flex:flexCol name="budgetName" display="taskPlanForm.budget" sortable="true" width="150" align="left"></flex:flexCol>
					<flex:flexCol name="departmentName" display="taskPlanForm.department" sortable="true" width="150" align="left"></flex:flexCol>
					<flex:flexCol name="governmentName" display="taskPlanForm.government" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="confirmStatus" alias="confirmStatusCN" display="taskPlanForm.confirmStatus" sortable="true" width="100" align="left"></flex:flexCol>
					<flex:flexCol name="applyDate" format="date" display="taskPlanForm.applyDate" sortable="true" width="53" align="center"></flex:flexCol>
</flex:flexgrid>
