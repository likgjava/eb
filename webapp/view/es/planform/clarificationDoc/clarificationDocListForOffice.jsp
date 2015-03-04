<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/clarificationDoc/clarificationDocListForOffice.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
 <div id="variation_list">
<form id="purchaseDocSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		  <ul>
	      <li>
	        <label>关键字：</label>
			<input name="keyWord" type="text" >	
			<input type="hidden" name="bullTitle_op" value="like">
	      </li>
	      <li>
	        <label>内容：</label>
		  	<input name="content" type="text" >	
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
   <!--  
 	<li>
     <a href="#variationListInfo" id = "tabs_toAdjust" class="refreshData"><span>已退回</span></a>
   </li>
   -->
</ul>
  <div id="variationListInfo">
	  <flex:flexgrid checkbox="false"
		id="purchaseDocGrid" url="PurchaseDocController.do?method=list" queryColumns=""  
			searchZone="purchaseDocSearchZone" rp="10"  title="澄清文件列表" onSuccess="purchaseDocList3.success" 
			onSubmit="purchaseDocList3.before">
			<flex:flexCol name="keyWord"  display="关键字" sortable="true" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="content"  display="摘要" sortable="true" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="filePrice"  display="文件价格" sortable="true" width="150" align="left"></flex:flexCol>
	</flex:flexgrid>
  </div>
</div>
	<input type="hidden" name="" id="projectId" value="${param.projectId}"/>
</div>