<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchaseDocList.js"></script>
</head>
<body>	  

	<form id="purchaseDocSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="purchaseDocGrid" url="PurchaseDocController.do?method=list" queryColumns=""  
			searchZone="purchaseDocSearchZone" rp="10" title="招标文件" width="800"  
			onSubmit="purchaseDocList.before" onSuccess="purchaseDocList.success">
					<flex:flexCol name="attachRelaId" display="purchaseDocForm.attachRelaId" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="price" display="purchaseDocForm.price" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="purchaseDocList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="purchaseDocList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="purchaseDocList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
