<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/recordFormFormTd.js"></script>
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
			<label class="short"  for="projCode">招标编号：</label>
			${projCode}
			<input type="hidden" name="projCode" id="projCode" value="${projCode}"/>
		</li>
		<li>
			<label class="short"  for="projName">招标名称：</label>
			<input type="text" name="projName" id="projName" class="required" value="" />
			<span class="eleRequired">*</span>
		</li>
		<li>
			<label class="short"  for="projName">采购方式：</label>
			<input type="hidden" name="ebuyMethod" id="ebuyMethod" value="${ebuyMethod}"/>
			${ebuyMethodCN}
		</li>
		<li>
			<label class="short"  for="projName">项目负责人：</label>
			<select id="managerId" name="manager.objId">
				<c:forEach items="${empList}" var="emp_">
					<option value="${emp_.objId}">${emp_.name }</option>
				</c:forEach>
			</select>
			<span class="eleRequired">*</span>
		</li>
		<li class="fullLines">
			<label class="short"  for="taskType"><spring:message code="taskPlanForm.taskType"/>：</label>
			<input type="hidden" name="tenderType" id="tenderType" value="${taskType}"/>
			${taskTypeCN}
		</li>
	</ul>
		<input type="hidden" name="objId" id="objId" />
     	<h5 align="center"><span>录入备案书信息</span></h5>
     	<ul>
     		 <li>
	     		<label for="" class="short">招管备案号:</label>
					<input type="text"  class="required" 
						      id="recFormNo" name="recFormNo"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
	     	<li>
	     		<label for="" class="short">建设规模:</label>
					<input type="text"  class="required" 
						    id="recFormScale"  name="recFormScale" />
    	   			<span class="eleRequired">*</span>
    	    </li>
    	       	<li>
	     		<label for="" class="short">建设地点:</label>
					<input type="text"  class="required" 
						      id="recFormAddr" name="recFormAddr"/>
    	   			<span class="eleRequired">*</span>
    	    </li>
    	    <li>
	     		<label for="" class="short">工程名称:</label>
					<input type="text"  class="required" 
						   id="engineeringName" name="engineeringName"   />
    	   			<span class="eleRequired">*</span>
    	    </li>
    	     <li>
	     		<label for="" class="short">建设工程规划许可证:</label>
					<input type="text"  class="required" 
						   id="planPermit" name="planPermit"   />
    	   			<span class="eleRequired">*</span>
    	    </li>
    	     	    <li >
	     		<label for="" class="short">招标人证明:</label>
				<input type="file" id="tendererProve"  name="tendererProve1" contentEditable="false" >
	     		<input type="hidden" id="isUploadFile1" name="isUploadFile1">
    	   			<span class="eleRequired">*</span>
    	    </li>
    	     <li >
	     	<label for="" class="short">立项证明:</label>
	     	<input type="file" id="projApproval"  name="projApproval1" contentEditable="false" >
		     	<input type="hidden" id="isUploadFile2" name="isUploadFile2">
    	   			<span class="eleRequired">*</span>
    	    </li>
    	    <li >
	     		<label for="" class="short">资金落实证明:</label>
	     			<input type="file" id="fundsProof"  name="fundsProof1" contentEditable="false" >
	     			<input type="hidden" id="isUploadFile3" name="isUploadFile3">
    	   			<span class="eleRequired">*</span>
    	    </li>
		</ul>
</form>
</div>
<div id="taskplanListView">
	<flex:flexgrid checkbox="true"	id="consignGrid" url="TaskPlanController.do?method=getTaskPlanSubListByNotblockAndPass" queryColumns=""  
		rp="5" title="申报书明细"  onSuccess="craeteProjForTaskPlanSub.success"
		onSubmit="craeteProjForTaskPlanSub.before">
				<flex:flexCol name="budgetName" display="taskPlanSubForm.budgetName" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="purchaseName" display="taskPlanSubForm.purchase"  sortable="true" width="130"align="left"></flex:flexCol>
				<flex:flexCol name="totalPrice" display="taskPlanSubForm.totalPrice" sortable="true" width="150"align="right" format="money"></flex:flexCol>
			<flex:flexBtn name="追加申报书明细" bclass="add" onpress="craeteProjForTaskPlanSub.add"></flex:flexBtn>
			<flex:flexBtn name="删除申报书明细" bclass="delete" onpress="craeteProjForTaskPlanSub.remove"></flex:flexBtn>	
	</flex:flexgrid>
</div>
<div class="formLayout form2Pa">
		   <div class="conOperation">
				<button type="button" id="recordFormSave"><span><spring:message code="globe.save"/></span></button>
				<button type="button" id="recordFormReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
				<button type="button" id="recordFormReset" type="button" tabindex="20""><span><spring:message code="globe.reset"/></span></button>
		   </div>
</div>
<div class="formTips">
      <ul>
          <li><spring:message code="globe.input.required.prompt"/></li>
	  </ul>
</div>