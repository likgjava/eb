<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/oppugnrequisition/oppugnReqBuyerList.js"></script>
<form id="oppugnRequisitionSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
							<li><label><spring:message code="oppugnRequisitionForm.oppuType"/></label>
								<select name="oppuType">
									<option value="00"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}"  >招标文件</dm:out>质疑</option>
									<option value="01">采购过程质疑</option>
									<option value="02">初定采购结果质疑</option>
									<option value="03">其它</option>
								</select>
							</li>
							
			<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>
<input type="hidden" name="projectId" id="projectId" value="${param.projectId}" />
<flex:flexgrid checkbox="false"
	id="oppugnRequisitionGrid" url="OppugnRequisitionController.do?method=list" queryColumns=""  
		searchZone="oppugnRequisitionSearchZone" rp="10" title="质疑列表" height="200" 
		onSubmit="oppugnRequisitionList.before" onSuccess="oppugnRequisitionList.success">
				<flex:flexCol name="oppuType" alias="oppuTypeCN" display="oppugnRequisitionForm.oppuType" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="createUser.emp.company.name" display="投标单位名称" sortable="true" width="150" align="left"></flex:flexCol>
				<flex:flexCol name="oppuContent" alias="oppuContent" display="oppugnRequisitionForm.oppuContent" sortable="true" width="200"align="left"></flex:flexCol>
				<flex:flexCol name="createTime" display="质疑时间" format="date" sortable="true" width="100"align="left"></flex:flexCol>
</flex:flexgrid>
