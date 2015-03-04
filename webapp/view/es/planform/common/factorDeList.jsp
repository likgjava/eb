<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/common/factorDeList.js"></script>
</head>
<input type="hidden" id="_factortypeId" value="${param.factortypeId}"></input>
<body>
	<flex:flexgrid checkbox="false"
		id="factorDeGrid" url="FactorDeController.do?method=list" queryColumns=""  
		searchZone="factorDeSearchZone" rp="10" title="" height="200" 
		onSubmit="factorDeList.before" onSuccess="factorDeList.success" >
		<flex:flexCol name="factorName" display="factorDeForm.factorName" sortable="true" width="220" align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="factorDeList.add"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
