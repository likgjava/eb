<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/signuprecord/supplierList.js"></script>
		<input type="hidden" name="projectId" id="projId" value="${param.projectId}">
	<flex:flexgrid  showTableToggleBtn="false"  
			id="signUprecordGrid" url="SignUprecordController.do?method=list&project.objId=${param.projectId}" queryColumns="confirmStatus"  
				rp="1000" title=""   
				onSuccess="signUprecordList.success" checkbox="false">
					<flex:flexCol name="supplierName" display="signUprecordForm.supplierName" sortable="true" width="200" align="left"></flex:flexCol>
					<flex:flexCol name="signLinker" display="signUprecordForm.signLinker" sortable="true" width="180" align="center"></flex:flexCol>
					<flex:flexCol name="createTime" display="signUprecordForm.createTime" sortable="true" width="180" align="center" format="date" ></flex:flexCol>
					<flex:flexCol name="confirmStatus" alias="confirmStatusCN" display="signUprecordForm.confirmStatusCN" sortable="true" width="180" align="center" ></flex:flexCol>
					
	</flex:flexgrid>
