<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/auditVariationBulletinList.js"></script>

 <div id="variation_list">
<form id="variationSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		  <ul>
	      <li>
	        <label><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">采购公告</dm:out>编号：</label>
			<input name="bulletinNo" type="text" >	
			<input type="hidden" name="bulletinNo_op" value="like">
	      </li>
	      <li>
	        <label><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>标题：</label>
		  	<input name="bullTitle" type="text" >	
		  	<input name="bullTitle_op" type="hidden" value="like">		
	      </li>
	      <li class="operationBtnDiv right">
	       	<button type="submit" id="query"><span><spring:message code="globe.query"/></span></button>
	      </li>
	    </ul>
	</div>
</form>
<div id="variationTabId">
  <ul>
 	<li>
     <a href="#variationListInfo" id = "tabs_toAudit" class="refreshData"><span>待审核</span></a>
   </li>
   <li>
     <a href="#variationListInfo" id = "tabs_toAlAudit" class="refreshData"><span>已审核</span></a>
   </li>
</ul>
  <div id="variationListInfo">
	  <flex:flexgrid checkbox="false"
		id="variationBulletinGrid" url="BulletinController.do?method=list" queryColumns=""  
			searchZone="variationSearchZone" rp="10"  title="公告列表" onSuccess="auditVariationList.success" 
			onSubmit="auditVariationList.before">
			<flex:flexCol name="bulletinNo" display="公告编号" sortable="true" width="80" align="left"></flex:flexCol>
			<flex:flexCol name="bullTitle"  display="公告标题" sortable="true" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="project.projName" display="招标名称" sortable="true" width="200" align="left"></flex:flexCol>
			<flex:flexCol name="project.projCode" display="招标编号" sortable="true" width="200" align="left"></flex:flexCol>
	</flex:flexgrid>
  </div>
</div>
	<input type="hidden" name="" id="projectId" value="${param.projectId}"/>
</div>