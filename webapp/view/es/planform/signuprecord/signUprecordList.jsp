<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/signuprecord/signUprecordList.js"></script>
</head>
<body>	  

	<form id="signUprecordSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
								<li><label><spring:message code="signUprecordForm.projName"/></label>
										<input type="text" name="projName"  >
								</li>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="signUprecordGrid" url="SignUprecordController.do?method=list" queryColumns=""  
			searchZone="signUprecordSearchZone" rp="10" title="投标单位报名记录"  
			onSubmit="signUprecordList.before" onSuccess="signUprecordList.success">
					<flex:flexCol name="projCode" display="signUprecordForm.projCode" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="projName" display="signUprecordForm.projName" sortable="true" width="120"align="left"></flex:flexCol>
					<flex:flexCol name="signLinker" display="signUprecordForm.signLinker" sortable="true" width="80"align="left"></flex:flexCol>
					<flex:flexCol name="signIdcard" display="signUprecordForm.signIdcard" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="linkerTel" display="signUprecordForm.linkerTel" sortable="true" width="120"align="left"></flex:flexCol>	
					<flex:flexCol name="signAddress" display="signUprecordForm.signAddress" sortable="true" width="150"align="left"></flex:flexCol>
					<flex:flexCol name="signType" display="signUprecordForm.signType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="signStatus" display="signUprecordForm.signStatus" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="signUprecordList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="signUprecordList.update"></flex:flexBtn>	
		<flex:flexBtn name="查看" bclass="add" onpress="signUprecordList.look"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="signUprecordList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
