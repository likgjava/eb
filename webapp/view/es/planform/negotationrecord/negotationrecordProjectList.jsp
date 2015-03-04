<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/negotationrecord/negotationrecordProjectList.js"></script>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<form id="puaseProjectZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li class="fullLine">
					招标编号：
		    	<input type="text" name="projCode" class="short" value="">
				<input type="hidden" name="projCode_op" value="like">
			</li>
			<li class="fullLine">
					<label>招标名称:</label>
				<input type="text" name="projName" class="short" value="">
				<input type="hidden" name="projName_op" value="like">
			</li>
			<li class="operationBtnDiv">
				<button type="submit"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
	</div>
</form>

<div id="puaseProjectListView">
	<flex:flexgrid checkbox="false"	id="puaseProjectGrid" url="ProjectController.do?method=searchProjectForAgent" queryColumns="objId,projProcessStatus"  
		searchZone="puaseProjectZone" rp="10" title="我的项目列表"  
		onSubmit="puaseProjectList.before" onSuccess="puaseProjectList.success">
				<flex:flexCol name="projCode" display="招标编号" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="projName" display="招标名称" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="buyersName" display="招标单位" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="createTime" display="立项日期" format="date" sortable="true" width="100"align="center"></flex:flexCol>
	</flex:flexgrid>
</div>

