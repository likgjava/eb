<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/projectExceptionApplyList.js"></script>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>

	<form id="projectExceptionApplySearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
			<li class="fullLine">
				<label>招标编号：</label>
		    	<input type="text" name="projCode" class="short" value="">
				<input type="hidden" name="projCode_op" value="like">
			</li>
			<li class="fullLine">
				<label>招标名称:</label>
				<input type="text" name="projName" class="short" value="">
				<input type="hidden" name="projName_op" value="like">
			</li>
			<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>
	
<div id="epsTabs">
  <ul>
  	<li>
      <a href="#projectExceptionApplyInfo" id = "tabs_toRePurchase" class="refreshData"><span>重新招标</span></a>
    </li>
    <li>
      <a href="#projectExceptionApplyInfo" id = "tabs_toChangeEBuyMemthod" class="refreshData"><span>变更采购方式</span></a>
    </li>
    <li>
      <a href="#projectExceptionApplyInfo" id = "tabs_toStop" class="refreshData"><span>终止项目</span></a>
    </li>
  </ul>
 <div id="projectExceptionApplyInfo">
	<flex:flexgrid checkbox="false" height="268"
		id="projectExceptionApplyGrid" url="ProjectExceptionApplyController.do?method=getProjectExceptionList" queryColumns=""  
			searchZone="projectExceptionApplySearchZone" rp="10" title="维护处理方式列表"
			onSubmit="projectExceptionApplyList.before" onSuccess="projectExceptionApplyList.success">
					<flex:flexCol name="project.projCode" display="招标编号" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="project.projName" display="招标名称" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="project.buyersName" display="招标单位" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="project.createTime" display="立项日期" format="date" sortable="true" width="100"align="center"></flex:flexCol>
					<flex:flexCol name="adviceProcway" display="处理方式" alias="adviceProcwayCN" sortable="true" width="100"align="center"></flex:flexCol>
					<flex:flexCol name="auditStatus" display="状态" alias="auditStatusCN" sortable="true" width="100"align="center"></flex:flexCol>	
	</flex:flexgrid>
 </div>
</div>
