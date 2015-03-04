<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/dosBuyRecordList.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div id="dosBuyRecordList">
    <input type="hidden" id="fileId" name="fileId" value="${purchaseDoc.objId}">
    <input type="hidden" id="projectId" name="projectId" value="${projectId}">
	<flex:flexgrid checkbox="false"
		id="dosBuyRecordGrid" url="DosBuyRecordController.do?method=list&fileId=${fileId}" queryColumns=""  
			searchZone="dosBuyRecordSearchZone" rp="10" title="招标文件购买记录" 
			onSubmit="dosBuyRecordList.before" onSuccess="dosBuyRecordList.success">
					<flex:flexCol name="supplierName" display="dosBuyRecordForm.supplierName" sortable="true" width="200"align="left"></flex:flexCol>
					<flex:flexCol name="docBuyStatus"  alias="docBuyStatusCN" display="dosBuyRecordForm.docBuyStatus" sortable="true" width="100" align="center"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="dosBuyRecordList.add"></flex:flexBtn>	
	</flex:flexgrid>
</div>

