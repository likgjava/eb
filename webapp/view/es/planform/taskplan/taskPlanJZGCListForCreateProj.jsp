<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page import="com.gpcsoft.epp.common.domain.EbuyMethodEnum"%>
<%@ page import="com.gpcsoft.epp.taskplan.domain.TaskPlanTypeEnum"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanJZGCListForCreateProj.js"></script>

<form id="recordFormSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li>
		        <label><spring:message code="recordFormForm.engineeringName"/>：</label>
			  	<input name="engineeringName" type="text" >	
			  	<input name="engineeringName_op" type="hidden" value="like">	
	        </li>
			<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>
<div id="epsTabs">
<ul>
<c:forEach items="${messageList}" var="mc">
  <li>
  <a  href="#consignListView" id="project${mc.code}" class="refreshData"> ${mc.message}</a>
 </li>
 </c:forEach>
</ul>
<div id="consignListView">
	<flex:flexgrid checkbox="true" 
		id="recordFormGrid" url="RecordFormController.do?method=list" queryColumns=""  
			searchZone="recordFormSearchZone" rp="10" title="备案书" height="280" 
			onSubmit="recordFormList.before" onSuccess="recordFormList.success">
					<flex:flexCol name="recFormBuyMethod" alias="recFormBuyMethodCn"  display="recordFormForm.recFormBuyMethod" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="ebuyMethod" alias="ebuyMethodCn" display="recordFormForm.ebuyMethod" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="engineeringName" display="recordFormForm.engineeringName" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="recFormScale" display="recordFormForm.recFormScale" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="recFormAddr" display="recordFormForm.recFormAddr" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="recFormStructureType" display="recordFormForm.recFormStructureType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="recFormContent" display="recordFormForm.recFormContent" sortable="true" width="100"align="left"></flex:flexCol>
	</flex:flexgrid>
	<div id="chooseId"><span style="color: red;">已选择的任务书明细：</span>
	</div> 
	<div class="conOperation">
		<input type="hidden" name="taskPlanMSubId" id="taskPlanMSubId" value=""/>
		<input type="hidden" name="taskPlanSubId" id="taskPlanSubId" value=""/>
		<input type="hidden" name="ebuyMethodId" id="ebuyMethodId" value=""/>
		<input type="hidden" name="ebuyMethodCNId" id="ebuyMethodCNId" value=""/>
		<input type="hidden" name="taskTypeId" id="taskTypeId" value=""/>
		<input type="hidden" name="taskTypeCNId" id="taskTypeCNId" value=""/>
		<button id="nextId" type="button" tabindex="18"><span>下一步</span></button>
	</div>
</div>
</div>

