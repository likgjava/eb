<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/clarificationDoc/ClarificationDocListForAudit.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
  <div id="variationListInfoC">
	  <flex:flexgrid checkbox="true"
		id="purchaseCDocGrid" url="PurchaseDocController.do?method=list&auditStatus=05&fileType=08&project.objId=${param.projectId}" queryColumns=""  
			searchZone="purchaseDocSearchZone" rp="10"  title="澄清文件列表" onSuccess="purchaseDocList.success" 
			onSubmit="purchaseDocList.before">
			<flex:flexCol name="keyWord"  display="关键字" sortable="true" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="content"  display="摘要" sortable="true" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="filePrice"  display="文件价格" sortable="true" width="150" align="left"></flex:flexCol>
	</flex:flexgrid>
  </div>
