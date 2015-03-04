<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/craeteProjForResProject.js"></script> 
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div class="formLayout form2Pa">
	<form id="craeteProjForTaskPlanSubForm" name="projectForm" method="post" >
	<input type="hidden" id="resProjectId" name="resProjectId" value="${resProjectId }"/>
	<h5 align="center"><span>录入分标段信息</span></h5>
	<ul>
		<li>
			<label class="short"  for="projectNo">项目编号：</label>
			<span>${resProject.projectNo}</span>
		</li>
		<li>
			<label class="short"  for="projName">项目名称：</label>
			<span>${resProject.projectName}</span>
		</li>
			<li>
			<label class="short"  for="projName">招标人：</label>
			<span>${resProject.departmentName}</span>
		</li>
			<li>
			<label class="short"  for="projName">计划批文号：</label>
			<span>${resProject.projApproval}</span>
		</li>
				<li>
			<label class="short"  for="projName">规划许可证号：</label>
			<span>${resProject.planPermit}</span>
		</li>
		<li>
			<label class="short"  for="projName">计划总投资：</label>
			<span>${resProject.amt}</span>
		</li>
		<li>
			<label class="short"  for="projName">合同估算价：</label>
			<span>${resProject.contratPrice}</span>
		</li>
	</ul>
	</form>
</div>
<div id="taskplanListView">
	<flex:flexgrid checkbox="false"	id="consignGrid" url="ResProjectItemController.do?method=list" queryColumns="objId"  
		rp="5" title="项目明细"  onSuccess="craeteProjForTaskPlanSub.success"
		onSubmit="craeteProjForTaskPlanSub.before">
				<flex:flexCol name="itemName" display="招标内容" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="contractPrice" display="合同估算价" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="isEbuy" display="是否招标" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="ebuyMethod" display="招标方式" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="ebuyStyle" display="招标组织形式" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="remark" display="备注" sortable="true" width="170"align="left"></flex:flexCol>
	</flex:flexgrid>
</div>
