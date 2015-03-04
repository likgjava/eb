<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/oppugnrequisition/oppugnReqSupplierList.js"></script>
<form id="oppugnRequisitionSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
							<li><label><spring:message code="oppugnRequisitionForm.oppuType"/></label>
								<select name="oppuType" id="oppuType" class="required">
									<option value="00"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>质疑</option>
									<option value="01">采购过程质疑</option>
									<option value="02">初定采购结果质疑</option>
									<option value="03">其它</option>
								</select>
							</li>
							<li><label><spring:message code="oppugnRequisitionForm.consBuyerName"/></label>
								<select name="consBuyerName" id="consBuyerName" class="required">
									<option value="00">招标单位</option>
									<option value="01">招标中心</option>
									<option value="02">两者</option>
								</select>
							</li>
			<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
		</ul>
	</div>
</form>
<flex:flexgrid checkbox="true"
	id="oppugnRequisitionGrid" url="OppugnRequisitionController.do?method=list" queryColumns="useStatus"  
		searchZone="oppugnRequisitionSearchZone" rp="10" title="质疑"  onSuccess="oppugnRequisitionList.success"
		onSubmit="oppugnRequisitionList.before">
				<flex:flexCol name="oppuType" alias="oppuTypeCN" display="oppugnRequisitionForm.oppuType" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="project.projName" display="招标名称" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="consBuyerName" alias="consBuyerNameCN" display="oppugnRequisitionForm.consBuyerName" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="oppuContent" alias="oppuContent" display="oppugnRequisitionForm.oppuContent" sortable="true" width="200"align="left"></flex:flexCol>
				<flex:flexCol name="createTime" display="oppugnRequisitionForm.createTime" sortable="true" width="150" align="left"></flex:flexCol>
	<flex:flexBtn name="globe.new" bclass="add" onpress="oppugnRequisitionList.add"></flex:flexBtn>	
	<flex:flexBtn name="批量删除" bclass="delete" onpress="oppugnRequisitionList.removes"></flex:flexBtn>	
</flex:flexgrid>
