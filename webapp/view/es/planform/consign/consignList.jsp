<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/consign/consignList.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<input type="hidden" id="param_type" value="${param.paramType}"></input>
<form id="consignSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li>
					<spring:message code="consignForm.consCode" />：
		    	<input type="text" name="consCode" value="">
				<input type="hidden" name="consCode_op" value="like">
					<spring:message code="consignForm.consName" />：
		    	<input type="text" name="consName" value="">
				<input type="hidden" name="consName_op" value="like">
					<spring:message code="consignForm.consTime" />：
		    	<input id="consTime" name="consTime" style="width: 70px;">
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
      <a href="#consignListView" id = "tabs_toSubmit" class="refreshData"><span>待提交</span></a>
    </li>
    <li>
      <a href="#consignListView" id = "tabs_toSure" class="refreshData"><span>待招标单位确认</span></a>
    </li>
     <li>
      <a href="#consignListView" id = "tabs_toAdjust" class="refreshData"><span>被退回</span></a>
    </li>
     <li>
      <a href="#consignListView" id = "tabs_done" class="refreshData"><span>已确认</span></a>
    </li>
  </ul>
  <div id="consignListView">
	  <flex:flexgrid checkbox="true"
		id="consignGrid" url="ConsignController.do?method=list" queryColumns=""  
			searchZone="consignSearchZone" rp="10" title="委托协议"  
			onSubmit="consignList.before" onSuccess="consignList.success">
					<flex:flexCol name="consCode" display="consignForm.consCode" sortable="true" width="80"align="center"></flex:flexCol>
					<flex:flexCol name="consName" display="consignForm.consName" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="consAgentName" display="consignForm.consAgent" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="consAgentLinker" display="consignForm.consAgentLinker" sortable="true" width="60"align="left"></flex:flexCol>
					<flex:flexCol name="consTime" display="consignForm.consTime" format="date" sortable="true" width="53"align="center"></flex:flexCol>
					<flex:flexCol name="consFinishTime" display="consignForm.consFinishTime" format="date" sortable="true" width="53"align="center"></flex:flexCol>
		<flex:flexBtn name="批量提交" bclass="audit" onpress="consignList.submit"></flex:flexBtn>			
	</flex:flexgrid>
  </div>
</div>   
