<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/allProjectList.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<input type="hidden" id="managerId" value="${managerId}">
<div class="partContainers" style="width: 100%">
	<form id="projectSearchZone" >
		<div class="conSearch">
			<ul>
			<li>
				<spring:message code="projectForm.projName" />：
		    	<input type="text" name="projName" value="">
				<input type="hidden" name="projName_op" value="like">
			</li>
			<li>
				<spring:message code="projectForm.projCode" />：
		    	<input type="text" name="projCode" value="">
				<input type="hidden" name="projCode_op" value="like">
			</li>
			<li class="operationBtnDiv">
				<button type="submit"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="false"
	id="projectGrid" url="ChangebulletinController.do?method=getProjectList" queryColumns=""  
	searchZone="projectSearchZone" rp="10" title="招标项目"  minGridHeight="297" height="297" 
	onSubmit="allprojectList.before" onSuccess="allprojectList.success">
		<flex:flexCol name="projCode" display="projectForm.projCode" sortable="true" width="150"align="center"></flex:flexCol>
		<flex:flexCol name="projName" display="projectForm.projName" sortable="true" width="200"align="left"></flex:flexCol>
		<flex:flexCol name="ebuyMethod" alias="ebuyMethodCN" display="projectForm.ebuyMethod" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexCol name="tenderEndTime" format="date" display="开标时间" sortable="true" width="150"align="center"></flex:flexCol>
		<flex:flexCol name="auditStatus" display="projectForm.auditStatus" sortable="true" width="100"align="center"></flex:flexCol>
	</flex:flexgrid>
</div>