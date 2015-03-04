<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpbulletin/moreForinqpBulletinList.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="contractSearchZone">
	    <ul>
	      <li>
	        <label><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>编号：</label>
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
	      	<input type="hidden" name="auditStatus" id="auditStatus" value="${auditStatus}" />
	      	<input type="hidden" name="bullType" id="bullType" value="${bullType}" />
	      </li>
	    </ul>
    </form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
  <div id="contractInfo">
    <flex:flexgrid checkbox="false" height="308"
		id="BulletinGrid" url="InqpbulletinController.do?method=getBulletinList" queryColumns=""
			searchZone="contractSearchZone" rp="10"  title="公告列表" onSuccess="BulletinList.success" 
			onSubmit="BulletinList.before">
			<flex:flexCol name="bulletinNo" display="公告编号" sortable="true" width="150" align="center"></flex:flexCol>
			<flex:flexCol name="bullTitle"  display="公告标题" sortable="true" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="project.projName" display="招标名称" sortable="true" width="200" align="left"></flex:flexCol>
			<flex:flexCol name="project.projCode" display="招标编号" sortable="true" width="200" align="center"></flex:flexCol>
			<flex:flexCol name="bullType" alias="bullTypeCN" display="公告类型" sortable="true" width="100" align="center"></flex:flexCol>
	</flex:flexgrid>
  </div>
</div>