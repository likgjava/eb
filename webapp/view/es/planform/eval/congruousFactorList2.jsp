<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/congruousFactorList2.js"></script>
	<script type="text/javascript">
	$("#back").click(function(){
		if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
	   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	    }
	})
	</script>
</head>
<body>
	<input type="hidden" id="factorTypeId" value="${param.factorTypeId}"></input>
	<input type="hidden" id="project_objId" value="${param.projectId}"></input>
	<flex:flexgrid checkbox="false"
		id="congruousFactorGrid" url="CongruousFactorController.do?method=list" queryColumns=""  
			searchZone="congruousFactorSearchZone" rp="10" title="" 
			onSubmit="congruousFactorList.before" onSuccess="congruousFactorList.success">
			<flex:flexCol name="factorName"  display="congruousFactorForm.factorName" sortable="true" width="100" align="left"></flex:flexCol>
			<flex:flexCol name="projName"  display="适用包组" sortable="true" width="160" align="left"></flex:flexCol>
			<c:if test="${param.isShowScore == 'true'}">
				<flex:flexCol name="score"  display="分值" sortable="true" width="75" align="right"></flex:flexCol>
			</c:if>
			<flex:flexCol name="remark"  display="评审标准" sortable="true" width="160" align="left"></flex:flexCol>
		<flex:flexBtn name="指标分类详情" bclass="sysicon siDisk" onpress="congruousFactorList.updateType"></flex:flexBtn>
	</flex:flexgrid>
	<div class="operation" align="right">
	</div>
</body>
</html>
