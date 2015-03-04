<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/consign/consignListForAccept.js"></script>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<form id="consignSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li>
				<label>
					<spring:message code="consignForm.consCode" />：
				</label>
		    	<input type="text" name="consCode" value="">
				<input type="hidden" name="consCode_op" value="like">
			</li>
			<li>
				<label>
					<spring:message code="consignForm.consName" />：
				</label>
		    	<input type="text" name="consName" value="">
				<input type="hidden" name="consName_op" value="like">
			</li>
			<li>
				<label>
					<spring:message code="consignForm.consTime" />：
				</label>
		    	<input id="consTime" name="consTime">
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
      <a href="#consignListView" id = "tabs_toAccept" class="refreshData"><span>待确认</span></a>
    </li>
    <li>
      <a href="#consignListView" id = "tabs_toDone" class="refreshData"><span>已确认</span></a>
    </li>
  </ul>
  <div id="consignListView">
	  <flex:flexgrid checkbox="false"
		id="consignGrid" url="ConsignController.do?method=getConsignList" queryColumns=""  
			searchZone="consignSearchZone" rp="10" title="委托协议"  
			onSubmit="consignListForAccept.before" onSuccess="consignListForAccept.success">
					<flex:flexCol name="consCode" display="consignForm.consCode" sortable="true" width="130"align="center"></flex:flexCol>
					<flex:flexCol name="consName" display="consignForm.consName" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="consAgentName" display="consignForm.consAgent" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="consAgentLinker" display="consignForm.consAgentLinker" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="consTime" display="consignForm.consTime" format="date" sortable="true" width="80"align="center"></flex:flexCol>
					<flex:flexCol name="consFinishTime" display="consignForm.consFinishTime" format="date" sortable="true" width="80"align="center"></flex:flexCol>
	</flex:flexgrid>
  </div>
</div>   
