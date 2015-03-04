<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/workgMemberList.js"></script>
	<div id="workgMemberList">
	<input type="hidden" id="workGroup" name="workGroup"  value="${workGroup.objId}">
	<input type="hidden" id="workGroupType" name="workGroupType"  value="${workGroup.workgType}">
	<flex:flexgrid checkbox="true"
		id="workgMemberGrid" url="WorkgMemberController.do?method=list&workGroup.objId=${workGroup.objId}&workGroup.workgType=${workGroup.workgType}" queryColumns=""  
			searchZone="workgMemberSearchZone" rp="10" title="小组成员" width="1100"  
			onSubmit="workgMemberList.before" onSuccess="workgMemberList.success">
					<flex:flexCol name="workgmName" display="workgMemberForm.workgmName" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="workgmType" display="workgMemberForm.workgmType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="workgmDuty" display="workgMemberForm.workgmDuty" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="workgmSpeciality" display="workgMemberForm.workgmSpeciality" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="workgmAccount" display="workgMemberForm.workgmAccount" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="workgmPassWord" display="workgMemberForm.workgmPassWord" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="workgMemberList.add"></flex:flexBtn>
		<flex:flexBtn name="globe.modify" bclass="modify" onpress="workgMemberList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="workgMemberList.remove"></flex:flexBtn>	
	</flex:flexgrid>
    </div>