<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/workFlow/ProcessDefNodeList.js"></script>  
<input type="hidden" id="processDefId" value="${param.processDefId}"/>
<div class="partContainers">	  
	<flex:flexgrid checkbox="false" id="processDefNodeGrid" url="ProcessDefNodeController.do?method=list&processDefine.objId=${param.processDefId}&order=nodeSort" 
	onSuccess="ProcessDefNodeList.success" rp="200" title="流程节点" queryColumns="" usepager="false">
		<flex:flexCol name="nodeSort" display="processDefNodeForm.nodeSort" sortable="true" width="50" align="center"></flex:flexCol>
		<flex:flexCol name="nodesName" display="processDefNodeForm.nodesName" sortable="true" width="150" align="left"></flex:flexCol>
		<flex:flexCol name="nodeType" display="processDefNodeForm.nodeType" alias="nodeTypeCN" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="department.name" display="processDefNodeForm.department" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="role.chName" display="processDefNodeForm.role" sortable="true" width="100" align="left"></flex:flexCol>
		<flex:flexCol name="nodeDes" display="processDefNodeForm.nodeDes" sortable="true" width="120" align="left"></flex:flexCol>
		<flex:flexCol name="nodeSign" display="processDefNodeForm.nodeSign" alias="nodeSignCN" sortable="true" width="50" align="center"></flex:flexCol>
		<flex:flexCol name="isShowTodo" display="processDefNodeForm.isShowTodo" alias="isShowTodoCN" sortable="true" width="50" align="center"></flex:flexCol>
		<flex:flexCol name="auditType" display="processDefNodeForm.auditType" alias="auditTypeCN" sortable="true" width="50" align="center"></flex:flexCol>
		<flex:flexCol name="objId" display="操作"  width="150" align="center" sortable="true"></flex:flexCol>
		<flex:flexBtn name="添加流程节点" bclass="add" onpress="ProcessDefNodeList.add"></flex:flexBtn>
	</flex:flexgrid>
	<div class="conOperation">
		<button name="historyBackBtn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
	</div>
</div>
