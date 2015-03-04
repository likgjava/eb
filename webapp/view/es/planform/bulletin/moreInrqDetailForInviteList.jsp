<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bulletin/moreInrqDetailForInviteList.js"></script>
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
	        <label>招标名称：</label>
			<input name="projName" type="text" >	
	      </li>
	      <li>
	        <label>招标编号：</label>
		  	<input name="projCode" type="text" >	
	      </li>
	      <li class="operationBtnDiv right">
	       	<button type="submit" id="query"><span><spring:message code="globe.query"/></span></button>
	      </li>
	    </ul>
    </form>
</div>

<!-- Tab页 -->
<div id="epsTabs" class="">
  <div id="InrqDetailInfo">
    <flex:flexgrid checkbox="false" height="305"
		id="InrqDetailGrid" url="InrqDetailController.do?method=searchInviteByQueryObject" queryColumns=""
			searchZone="contractSearchZone" rp="10"  title="邀请函列表" onSuccess="InrqDetailList.success" 
			onSubmit="InrqDetailList.before">
			<flex:flexCol name="projName" display="招标名称" sortable="true" width="200" align="left"></flex:flexCol>
			<flex:flexCol name="projCode" display="招标编号" sortable="true" width="200" align="left"></flex:flexCol>
			<flex:flexCol name="auditStatus" alias="auditStatusCN" display="审核状态" sortable="true" width="150" align="center"></flex:flexCol>
			<flex:flexCol name="inrqDKind" alias="inrqDKindCN" display="邀请函种类" sortable="true" width="150" align="center"></flex:flexCol>
	</flex:flexgrid>
  </div>
</div>