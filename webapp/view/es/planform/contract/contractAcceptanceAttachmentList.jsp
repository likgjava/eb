<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/contract/contractAcceptanceAttachmentList.js"></script>
</head>
<body>	  

	<form id="contractAcceptanceAttachmentSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="contractAcceptanceAttachmentGrid" url="ContractAcceptanceAttachmentController.do?method=list" queryColumns=""  
			searchZone="contractAcceptanceAttachmentSearchZone" rp="10" title="" width="800" height="200" 
			onSubmit="contractAcceptanceAttachmentList.before" onSuccess="contractAcceptanceAttachmentList.success">
					<flex:flexCol name="bizId" display="workFlowModelForm.bizId" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="opinion" display="workFlowModelForm.opinion" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="user" display="workFlowModelForm.user" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="createTime" display="workFlowModelForm.createTime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="workflowAuditStatus" display="workFlowModelForm.workflowAuditStatus" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="contractAcceptanceAttachmentList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="contractAcceptanceAttachmentList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="contractAcceptanceAttachmentList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
