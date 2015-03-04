<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/qualifications/qualificationsAppList.js"></script>
</head>
<body>	  

	<form id="qualificationsAppSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="qualificationsAppGrid" url="QualificationsAppController.do?method=list" queryColumns=""  
			searchZone="qualificationsAppSearchZone" rp="10" title="预审申请" width="800"  
			onSubmit="qualificationsAppList.before" onSuccess="qualificationsAppList.success">
					<flex:flexCol name="preAppNo" display="qualificationsAppForm.preAppNo" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="project" display="qualificationsAppForm.project" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="supplyer" display="qualificationsAppForm.supplyer" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="linkMan" display="qualificationsAppForm.linkMan" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="linkPhone" display="qualificationsAppForm.linkPhone" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="email" display="qualificationsAppForm.email" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="address" display="qualificationsAppForm.address" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="zipCode" display="qualificationsAppForm.zipCode" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="auditStatus" display="qualificationsAppForm.auditStatus" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="relStatus" display="qualificationsAppForm.relStatus" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="relDate" display="qualificationsAppForm.relDate" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="relUser" display="qualificationsAppForm.relUser" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="createUser" display="qualificationsAppForm.createUser" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="createTime" display="qualificationsAppForm.createTime" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="useStatus" display="qualificationsAppForm.useStatus" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="qualificationsAppList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="qualificationsAppList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="qualificationsAppList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
