<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bidPackageList.js"></script>
</head>
<body>	  

	<form id="bidPackageSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="bidPackageGrid" url="BidPackageController.do?method=list&bid.objId=${param.bidId}" queryColumns=""  
			searchZone="bidPackageSearchZone" rp="10" title="投标单位报价包组" width="800"  
			onSubmit="bidPackageList.before" onSuccess="bidPackageList.success">
					<flex:flexCol name="bidPQuoteSum" display="bidPackageForm.bidPQuoteSum" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="subProjId" display="bidPackageForm.subProjId" sortable="true" width="100"align="left"></flex:flexCol>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="bidPackageList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="bidPackageList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
