<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/projectPackageList.js"></script>
</head>
<body>	  

	<form id="projectPackageSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="projectPackageGrid" url="ProjectPackageController.do?method=list" queryColumns=""  
			searchZone="projectPackageSearchZone" rp="10" title="项目拆分" width="800"  
			onSubmit="projectPackageList.before" onSuccess="projectPackageList.success">
					<flex:flexCol name="project" display="projectPackageForm.project" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="packCode" display="projectPackageForm.packCode" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="packName" display="projectPackageForm.packName" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="packContent" display="projectPackageForm.packContent" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="remark" display="projectPackageForm.remark" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="creator" display="projectPackageForm.creator" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="createTime" display="projectPackageForm.createTime" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="projectPackageList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="projectPackageList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="projectPackageList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
