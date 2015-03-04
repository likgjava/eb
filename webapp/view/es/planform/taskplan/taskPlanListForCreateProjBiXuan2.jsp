<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page import="com.gpcsoft.epp.common.domain.EbuyMethodEnum"%>
<%@ page import="com.gpcsoft.epp.taskplan.domain.TaskPlanTypeEnum"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/taskplan/taskPlanListForCreateProjBiXuan2.js"></script>
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
	  	  <a  href="#consignListView" id="project${mc.code}" class="refreshData"> ${mc.message}</a>
 </li>
 </c:forEach>
</ul>
<div id="consignListView">
	<flex:flexgrid checkbox="false" 	id="taskPlanSubForCreateProjGrid" url="ResProjectController.do?method=list&auditStatus=01" queryColumns="objId"  
		searchZone="taskPlanSubForCreateProjZone" rp="10" title="比选项目"  
		onSubmit="taskPlanSubForCreateProj.before" onSuccess="taskPlanSubForCreateProj.success" sortname="createTime" sortorder="desc" height="195" minGridHeight="190">
				<flex:flexCol name="projectName" display="项目名称" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="projectType" display="项目性质" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="budgetName" display="建设单位名称" sortable="true" width="130"align="right"></flex:flexCol>
				<flex:flexCol name="ebuyMethod" display="招标方式" sortable="true" width="100"align="center"></flex:flexCol>
				<flex:flexCol name="bidInviStyle"  display="招标组织形式" sortable="true" width="100"align="center"></flex:flexCol>
	</flex:flexgrid>
</div>  

</div>

