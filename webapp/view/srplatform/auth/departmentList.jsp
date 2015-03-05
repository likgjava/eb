<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/departmentList.js"></script>
</head>
<body>	  
	<div id="searchZone" class="form-container" >
		<div>
			<label><spring:message code="globe.query"/>:</label>
		</div>
		<div>
			<label><spring:message code="departmentForm.objId" />:</label> 
			<input type="text" name="objId" value="like">
			<input type="hidden" name="objId_op" value="like">
			<input type="submit" value="<spring:message code="globe.query"/>" />
		</div>
	</div>
	<flex:flexgrid
		id="departmentGrid" url="DepartmentController.do?method=list" queryColumns=""  
			searchZone="searchZone" rp="10" title="列表" width="800" height="200" 
			onSubmit="departmentList.before" onSuccess="departmentList.success">
		<flex:flexCol name="objId" display="ID" sortable="true" width="50"align="left"></flex:flexCol>
		<flex:flexBtn name="新增" bclass="add" onpress="departmentList.add"></flex:flexBtn>	
		<flex:flexBtn name="修改" bclass="add" onpress="departmentList.update"></flex:flexBtn>	
		<flex:flexBtn name="删除" bclass="delete" onpress="departmentList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
