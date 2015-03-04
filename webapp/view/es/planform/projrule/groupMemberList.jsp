<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/groupMemberList.js"></script>
</head>
<body>	  

	<form id="groupMemberSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
			   	<li><label><spring:message code="groupMemberForm.name"/>
										<input type="text" name="name"  >
									   <input type="hidden" name="name_op" value="like"/>
									</label></li>
			    <li><label><spring:message code="groupMemberForm.orgName"/>
									<input type="text" name="orgName"  >
								<input type="hidden" name="orgName_op" value="like"/>
									</label></li>
			    <li><label><spring:message code="groupMemberForm.groupType"/>
									<input type="text" name="groupType"  >
								<input type="hidden" name="groupType_op" value="like"/>
									</label></li>
				<li><button type="submit" id="querySubmit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="groupMemberGrid" url="GroupMemberController.do?method=getAllGroupMember&project.objId=${param.projectId}" queryColumns=""  
			searchZone="groupMemberSearchZone" rp="10" title="工作小组成员" width="1000"  
			onSubmit="groupMemberList.before" onSuccess="groupMemberList.success">
					<flex:flexCol name="name" display="groupMemberForm.name" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="groupType" display="groupMemberForm.groupType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="duty" display="groupMemberForm.duty" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="orgName" display="groupMemberForm.orgName" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="dept" display="groupMemberForm.dept" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="memberTitle" display="groupMemberForm.memberTitle" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="tel" display="groupMemberForm.tel" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="remark" display="groupMemberForm.remark" sortable="true" width="100"align="left"></flex:flexCol>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="groupMemberList.update"></flex:flexBtn>	
		<flex:flexBtn name="返回" bclass="add" onpress="groupMemberList.returnback"></flex:flexBtn>
		<flex:flexBtn name="详情" bclass="add" onpress="groupMemberList.showDetail"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="groupMemberList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
