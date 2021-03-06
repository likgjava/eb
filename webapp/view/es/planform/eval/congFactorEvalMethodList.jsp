<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/eval/congFactorEvalMethodList.js"></script>
</head>
<body>	  

	<form id="congFactorEvalMethodSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="congFactorEvalMethodGrid" url="CongFactorEvalMethodController.do?method=list" queryColumns=""  
			searchZone="congFactorEvalMethodSearchZone" rp="10" title="" width="800" height="200" 
			onSubmit="congFactorEvalMethodList.before" onSuccess="congFactorEvalMethodList.success">
		<flex:flexBtn name="globe.new" bclass="add" onpress="congFactorEvalMethodList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="congFactorEvalMethodList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="congFactorEvalMethodList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
