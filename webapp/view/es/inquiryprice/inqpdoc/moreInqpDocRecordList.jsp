<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpdoc/moreInqpDocRecordList.js"></script>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<!-- 查询条件 -->
<div class="conSearch">
    <h4><span><spring:message code="globe.query"/></span></h4>
    <form id="purchaseDocSearchZone">
	    <ul>
	      <li>
	        <label>招标编号：</label>
			<input name="projCode" type="text" >	
	      </li>
	      <li>
	        <label>招标名称：</label>
		  	<input name="projName" type="text" >	
	      </li>
	      <li class="operationBtnDiv right">
	       	<button type="submit" id="query"><span><spring:message code="globe.query"/></span></button>
	      	<input type="hidden" name="auditStatus" id="auditStatus" value="${auditStatus}"/>
	      </li>
	    </ul>
    </form>
</div>
	
	<flex:flexgrid checkbox="false"
		id="purchaseDocGrid" url="InqpDocController.do?method=searchPurchaseDocByQueryObject&fileType=${fileType}&auditStatus=${auditStatus}" queryColumns=""  
			searchZone="purchaseDocSearchZone" rp="10" title="询价文件列表"  onSuccess="purchaseDocList.success" minGridHeight="305" height="305">
					<flex:flexCol name="project.projCode" display="projectForm.projCode" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="project.projName" display="projectForm.projName" sortable="true" width="180"align="left"></flex:flexCol>
					<flex:flexCol name="keyWord" display="purchaseDocForm.keyWord" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="content" display="purchaseDocForm.content" sortable="true" width="100"align="left"></flex:flexCol>
	</flex:flexgrid>