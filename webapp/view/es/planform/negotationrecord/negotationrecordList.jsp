<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/negotationrecord/negotationrecordList.js"></script>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>
<input type="hidden" id="subProjectId_" value="${subProjectId}">
<input type="hidden" id="projectId" value="${projectId}">
<form id="negotationRecordZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li class="fullLine">
					投标单位名称：
		    	<input type="text" name="supplierName" class="short" value="">
				<input type="hidden" name="supplierName_op" value="like">
			</li>
			<li class="operationBtnDiv">
				<button type="submit"><span><spring:message code="globe.query"/></span></button>
			</li>
		</ul>
	</div>
</form>

<div id="puaseProjectListView">
	<flex:flexgrid checkbox="false"	id="negotationRecordGrid" url="NegotationRecordController.do?method=searchNegotationRecordForAgent" queryColumns="objId,projProcessStatus"  
		searchZone="negotationRecordZone" rp="10" title="谈判记录列表"  
		onSubmit="negotationrecordList.before" onSuccess="negotationrecordList.success">
				<flex:flexCol name="supplierName" display="投标单位名称" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="recordTotal" display="报价金额" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexCol name="recordTime" display="报价时间" format="date" sortable="true" width="170"align="left"></flex:flexCol>
				<flex:flexBtn name="添加" bclass="add" onpress="negotationrecordList.add"></flex:flexBtn>	
	</flex:flexgrid>
</div>

<div class="conOperation">
	<button id="finshSaveButton" type="button" tabindex="18"><span>完成</span></button>
   	<span><font color="red">(点击完成，方可进入下一步操作！)</font></span>
</div>
