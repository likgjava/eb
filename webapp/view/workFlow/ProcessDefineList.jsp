<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/workFlow/ProcessDefineList.js"></script>  
<div class="partContainers">
	<form id="processDefineSearchZone">
			<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li class="fullLine">
					<spring:message code="processDefineForm.processDefName" />： <input type="text" name="processDefName" class="" value="">
					<input type="hidden" name="processDefName_op" value="like">
					<spring:message code="processDefineForm.bizType" />： <input type="text" name="bizType" class="" value="">
					<input type="hidden" name="bizType_op" value="like">
					<spring:message code="processDefineForm.processInstanceName" />： <input type="text" name="processInstanceName" class="" value="">
					<input type="hidden" name="processInstanceName_op" value="like">
				</li>
				<li class="operationBtnDiv">
					<button type="submit"><span><spring:message code="globe.query"/></span></button>
				</li>
			</ul>
		</div>
	</form>
	<flex:flexgrid checkbox="false" id="processDefineGrid" url="ProcessDefineController.do?method=list" 
	onSuccess="ProcessDefineList.success" rp="20" title="流程定义" queryColumns="" searchZone="processDefineSearchZone">
		<flex:flexCol name="processDefName" display="processDefineForm.processDefName" sortable="true" width="300" align="left"></flex:flexCol>
		<flex:flexCol name="bizType" display="processDefineForm.bizType" sortable="true" width="300" align="left"></flex:flexCol>
		<flex:flexCol name="processInstanceName" display="processDefineForm.processInstanceName" sortable="true" width="300" align="left"></flex:flexCol>
		<flex:flexCol name="createTime" display="processDefineForm.createTime" sortable="true" width="120" align="center"></flex:flexCol>
		<flex:flexCol name="objId" display="操作" sortable="true" width="80" align="center"></flex:flexCol>
	</flex:flexgrid>
</div>
