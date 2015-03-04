<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchaseDocListForAudit.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<flex:flexgrid checkbox="false"
	id="purchaseDocGrid" url="PurchaseDocController.do?method=list&auditStatus=05&fileType=07" queryColumns=""  
		searchZone="purchaseDocSearchZone" rp="10" title="待监察局审核的招标文件" onSuccess="purchaseDocList.success">
		<flex:flexCol name="keyWord" display="purchaseDocForm.keyWord" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexCol name="content" display="purchaseDocForm.content" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexCol name="createTime" display="purchaseDocForm.createTime" sortable="true" width="200"align="left"></flex:flexCol>
		<flex:flexCol name="project.projName" display="招标名称" sortable="true" width="200"align="left"></flex:flexCol>		
		<flex:flexCol name="project.projCode" display="招标编号" sortable="true" width="200"align="left"></flex:flexCol>	
</flex:flexgrid>
