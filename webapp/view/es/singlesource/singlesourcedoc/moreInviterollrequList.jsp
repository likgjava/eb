<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/singlesource/singlesourcedoc/moreInviterollrequList.js"></script>
<input type="hidden" id="useStatus" value="${param.useStatus}"></input>
<input type="hidden" id="auditStatus" value="${param.auditStatus}">
<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="inviterollrequSearchZone">
	    <ul>
	      <li>
	        <label>招标编号：</label>
			<input name="projCode" type="text" >	
	      </li>
	      <li>
	        <label>招标名称：</label>
		  	<input name="projName" type="text" >	
	      </li>
	      <li class="operationBtnDiv right">
	       	<button type="submit" id="query"><span><spring:message code="globe.query"/></span></button>
	      </li>
	    </ul>
    </form>
</div>
	
	<flex:flexgrid checkbox="false"
		id="inviterollrequGrid" url="InviterollrequController.do?method=searchInviterollrequByQueryObject" queryColumns=""  
			searchZone="inviterollrequSearchZone" rp="10" title="邀请函列表" onSubmit="moreInviterollrequList.before" onSuccess="moreInviterollrequList.success" >
					<flex:flexCol name="projCode" display="inviterollrequForm.projCode" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="projName" display="inviterollrequForm.projName" sortable="true" width="150"align="left"></flex:flexCol>	
	</flex:flexgrid>