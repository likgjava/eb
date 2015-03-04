<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/craeteProjForTaskPlanSubForAgent.js"></script> 
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="formLayout form2Pa">
	<form id="craeteProjForTaskPlanSubForm" name="projectForm" method="post" >
	<input type="hidden" id="taskPlanSubIds" name="taskPlanSubIds" value="${taskPlanSubIds }"/>
	<h5 align="center"><span>录入招标项目信息</span></h5>
	<ul>
		<li>
			<label class="short"  for="projCode"><spring:message code="projectForm.projCode"/>：</label>
			${projCode}
			<input type="hidden" name="projCode" id="projCode" value="${projCode}"/>
		</li>
		<li>
			<label class="short"  for="projName"><spring:message code="projectForm.projName"/>：</label>
			<input type="text" name="projName" id="projName" class="required" value="" />
			<span class="eleRequired">*</span>
		</li>
		<li>
			<label class="short"  for="projName"><spring:message code="projectForm.ebuyMethod"/>：</label>
			<input type="hidden" name="ebuyMethod" id="ebuyMethod" value="${ebuyMethod}"/>
			${ebuyMethodCN}
		</li>
		<li>
			<label class="short">招标单位项目负责人：</label>
			<select name="manager.objId" id="managerId" class="required">
				<option value="-1">--请选择--</option>
		        <c:forEach items="${empList}" var="empe" >	
		        	<option value="${empe.objId}" <c:if test="${departmentLinker==empe.name}">selected="selected"</c:if>>${empe.name}</option>
		       	</c:forEach>
			</select>
		</li>
		<li>
			<label class="short">联系方式：</label>
			<input type="text" name="manager.mobile" id="mobile" class="required" value="${departmentLinkerTel}" />
		</li>
		<li class="fullLine">
			<label class="short">业主单位：</label>
			${agentName}
		</li>
		<li>
			<label class="short">业主单位项目负责人：</label>
			${budgetLeader.name}
		</li>
		<li>
			<label class="short">业主单位项目负责人联系方式：</label>
			${budgetLeader.mobile}
		</li>
		<li>
			<label class="short">招标方式：</label>
			<input type="hidden" name="ebuyMethod" id="ebuyMethod" value="${ebuyMethod}"/>
			${ebuyMethodCN}
		</li>
		<li>
			<label class="short">项目类型：</label>
			<input type="hidden" name="tenderType" id="tenderType" value="${taskType}"/>
			${taskTypeCN}
		</li>
		<li>
			<label class="short">评标方式：</label>
			<select name="evalMethod" id="evalMethod">
				<option value="01">综合评分法</option>
				<option value="02">综合评价法</option>
			</select>
		</li>
		<li>
			<label class="short">招标类型：</label>
			<select name="purCategoryIds" id="purCategoryIds">
				<option value="A">货物类</option>
				<option value="B">工程类</option>
				<option value="C">服务类</option>
			</select>
		</li>		
		<li class="formTextarea">
			<label class="short">招标内容：</label>
			<textarea name="content" id="content" class="texFull" maxlength="200"></textarea>
		</li>
	</ul>
	</form>
</div>

<input name="taskPlanId" id="objId" type="hidden" value="${param.taskPlanId}"/>

<div id="taskplanListView">
	<flex:flexgrid checkbox="true"	id="consignGrid" url="TaskPlanController.do?method=getTaskPlanSubNotInProject" queryColumns=""  
		rp="5" title="申报书明细"  onSuccess="craeteProjForTaskPlanSub.success"
		onSubmit="craeteProjForTaskPlanSub.before">
				<flex:flexCol name="budgetName" display="taskPlanSubForm.budgetName" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="purchaseName" display="taskPlanSubForm.purchase"  sortable="true" width="130"align="left"></flex:flexCol>
				<flex:flexCol name="totalPrice" display="taskPlanSubForm.totalPrice" sortable="true" width="150"align="right" format="money"></flex:flexCol>
				
			<flex:flexBtn name="globe.new" bclass="add" onpress="craeteProjForTaskPlanSub.addSub"></flex:flexBtn>
			<flex:flexBtn name="追加申报书明细" bclass="add" onpress="craeteProjForTaskPlanSub.add"></flex:flexBtn>
			<flex:flexBtn name="删除申报书明细" bclass="delete" onpress="craeteProjForTaskPlanSub.remove"></flex:flexBtn>	
	</flex:flexgrid>
</div>
<div class="formLayout form2Pa">
	<div class="conOperation">
		<button id="projectSaveButton" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
	</div>
</div>
