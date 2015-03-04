<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projectplan/selectSysconfigitemList.js"></script>
	<flex:flexgrid checkbox="false"
		id="sysConfigItemGrid" url="SysConfigController.do?method=getSysConfigItemForPage" queryColumns=""  
			searchZone="sysConfigItemSearchZone" rp="10" title="系统配置条目" width="800"  
			onSubmit="sysConfigItemList.before" onSuccess="sysConfigItemList.success">
			<flex:flexCol name="name" display="sysConfigItemForm.name" sortable="true" width="160"align="left"></flex:flexCol>
			<flex:flexCol name="code" display="sysConfigItemForm.code" sortable="true" width="160"align="left"></flex:flexCol>
			<flex:flexCol name="dataType" display="sysConfigItemForm.dataType" sortable="true" width="100"align="left"></flex:flexCol>
			<flex:flexCol name="createTime" display="sysConfigItemForm.createTime" sortable="true" width="120"align="left"></flex:flexCol>
			<flex:flexCol name="notes" display="sysConfigItemForm.notes" sortable="true" width="120"align="left"></flex:flexCol>
	</flex:flexgrid>
   <div class="conOperation">
   		<input type="hidden" id="sysConfigTypeId" value="${sysConfigTypeId}"/>
		<button type="button" id="_clean"><span>清空</span></button>
		<button type="button" id="_close"><span><spring:message code="globe.close"/></span></button>
   </div>