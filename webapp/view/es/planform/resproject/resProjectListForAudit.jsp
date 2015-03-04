<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<%@page import="com.gpcsoft.srplatform.auth.domain.User"%>
<%@page import="com.gpcsoft.plugin.acegi.AuthenticationHelper"%>
<style>a.abtn {text-decoration:underline}</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/resproject/resProjectListForAudit.js"></script>

<form id="resProjectSeachZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li class="fullLine">建设单位名称：
		    	<input type="text" name="budgetName" class="short" value="" style="width: 200px;">
			</li>
			<li class="fullLine">工程名称：
		    	<input type="text" name="projectName" class="short" value="" style="width: 200px;">
			</li>
			<li class="operationBtnDiv">
				<button type="submit"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
	</div>
</form>
<div id="epsTabs">
	<ul>
	    <li>
	      <a href="#resProjectInfo" id = "tabs_toWait" class="refreshData"><span>待审核</span></a>
	    </li>
	    <li>
	      <a href="#resProjectInfo" id = "tabs_toBack" class="refreshData"><span>被退回</span></a>
	    </li>
	    <li>
	      <a href="#resProjectInfo" id = "tabs_toFormal" class="refreshData"><span>已审核</span></a>
	    </li>
   </ul>
   <div id="resProjectInfo">
	<flex:flexgrid  id="resProjectListGrid" url="ResProjectController.do?method=list&auditStatus=00&useStatus=01" 
					queryColumns="budgetName,projectName,projApproval,objId" searchZone="resProjectSeachZone" rp="5"  title="委托建设项目列表 " onSuccess="resProjectList.success"
					onSubmit="resProjectList.before" checkbox="false">
					<flex:flexCol name="budgetName" display="建设单位名称" sortable="true" width="250" align="center"></flex:flexCol>
					<flex:flexCol name="projectName" display="工程名称" sortable="true" width="100" align="center"></flex:flexCol>
					<flex:flexCol name="projApproval" display="计划批文号" sortable="true" width="140" align="center"></flex:flexCol>
					<flex:flexCol name="objId" display="操作" sortable="true" width="400"align="center"></flex:flexCol>
	</flex:flexgrid>
	</div>
</div>
