<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page import="com.gpcsoft.epp.common.domain.EbuyMethodEnum"%>
<%@ page import="com.gpcsoft.epp.taskplan.domain.TaskPlanTypeEnum"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanListForCreateProj.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/util/otherUtils.js"></script>
<form id="taskPlanSubForCreateProjZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li class="fullLine">
				<label class="short" for="purchaseName"><spring:message code="taskPlanSubForm.purchase"/>：</label>
		    	<input type="text" name="purchase.name" id="purchase.name" value="${takeExpertRule.purCategory.categoryName}" class="required sysicon siSearch"  onblur="taskPlanSubForCreateProj.contentOnchange();"/>
				<input type="hidden" name="purchase.objId" id="purchase.objId" value="${takeExpertRule.purCategory.objId}"/>
			</li>
			<li class="fullLine">
					<label class="short" for="taskCode"><spring:message code="taskPlanForm.ebuyMethod"/>：</label>
					<html:select styleClass="required short" id="ebuyMethod" name="ebuyMethod" code="ebuyMethod" selectedValue="${taskPlan.ebuyMethod}">
					</html:select>
			</li>
			<li class="operationBtnDiv" style="margin-top: 6px;">
				<button type="submit"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
	</div>
</form>
<form id="recordFormSearchZone" style="display: none">
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
  <c:choose>
	  <c:when test="${mc.code == '04'}">
	  	  <a  href="#consignListViewJZGC" id="project${mc.code}" class="refreshData"> ${mc.message}</a>
	  </c:when>
	  <c:otherwise>
	  	  <a  href="#consignListView" id="project${mc.code}" class="refreshData"> ${mc.message}</a>
	  </c:otherwise>
  </c:choose>
 </li>
 </c:forEach>
</ul>
<div id="consignListView">
	<flex:flexgrid checkbox="true" 	id="taskPlanSubForCreateProjGrid" url="TaskPlanMSubController.do?method=getAllTaskPlanSubListForCreateProj&taskType=01&isComplete=true" queryColumns="objId"  
		searchZone="taskPlanSubForCreateProjZone" rp="10" title="申报书明细"  
		onSubmit="taskPlanSubForCreateProj.before" onSuccess="taskPlanSubForCreateProj.success" sortname="taskPlan.createTime" sortorder="desc" height="195" minGridHeight="190">
				<flex:flexCol name="taskPlanSub.budgetName" display="预算单位名称" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.purchaseName" display="采购品目名称" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="taskPlanSub.totalPrice" display="预算" format="money" sortable="true" width="130"align="right"></flex:flexCol>
				<flex:flexCol name="taskPlan.ebuyMethod" alias="taskPlan.ebuyMethodCN" display="采购方式" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlan.taskType" alias="taskPlan.taskTypeCN" display="类型" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="taskPlan.taskName"  display="所属申报书" sortable="true" width="100"align="left" ></flex:flexCol>
				<flex:flexCol name="taskPlan.createTime"  display="创建时间" sortable="true" width="100"align="center" format="date"></flex:flexCol>
	</flex:flexgrid>
</div>  

<div id="consignListViewJZGC">
	<flex:flexgrid checkbox="true" 
		id="recordFormGrid" url="RecordFormController.do?method=list&order=createTime&order_flag=true" queryColumns=""  
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
</div>

<div id="chooseId"><span style="color: red;">已选择的申报书明细：</span>
</div>
<div class="conOperation">
	<input type="hidden" name="taskPlanMSubId" id="taskPlanMSubId" value=""/>
	<input type="hidden" name="taskPlanSubId" id="taskPlanSubId" value=""/>
	<input type="hidden" name="ebuyMethodId" id="ebuyMethodId" value=""/>
	<input type="hidden" name="ebuyMethodCNId" id="ebuyMethodCNId" value=""/>
	<input type="hidden" name="taskTypeId" id="taskTypeId" value=""/>
	<input type="hidden" name="taskTypeCNId" id="taskTypeCNId" value=""/>
	<input type="hidden" name="buyerName" id="buyerName" value=""/>
	<button id="nextId" type="button" tabindex="18"><span>下一步</span></button>
</div>
</div>

