<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projrule/projProcessRuleList.js"></script>
</head>
<body>	  

	<form id="projProcessRuleSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>

	<flex:flexgrid checkbox="true"
		id="projProcessRuleGrid" url="ProjProcessRuleController.do?method=list" queryColumns=""  
			searchZone="projProcessRuleSearchZone" rp="10" title="项目规则" width="800"  
			onSubmit="projProcessRuleList.before" onSuccess="projProcessRuleList.success">
					<flex:flexCol name="docDemonStrate" display="projProcessRuleForm.docDemonStrate" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="localEsurvey" display="projProcessRuleForm.localEsurvey" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="qualification" display="projProcessRuleForm.qualification" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="multiphaseBid" display="projProcessRuleForm.multiphaseBid" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="openBudget" display="projProcessRuleForm.openBudget" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="bidSinglePack" display="projProcessRuleForm.bidSinglePack" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="needEvaluateGroup" display="projProcessRuleForm.needEvaluateGroup" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="needBuyErackPurchaseDoc" display="projProcessRuleForm.needBuyErackPurchaseDoc" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="evaluateOnline" display="projProcessRuleForm.evaluateOnline" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="publishPurchaseBulletin" display="projProcessRuleForm.publishPurchaseBulletin" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="publishResultReview" display="projProcessRuleForm.publishResultReview" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="publishResultBulletin" display="projProcessRuleForm.publishResultBulletin" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="upLoadAfterSignUp" display="projProcessRuleForm.upLoadAfterSignUp" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="evalFillScoreType" display="projProcessRuleForm.evalFillScoreType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="openPackType" display="projProcessRuleForm.openPackType" sortable="true" width="100"align="left"></flex:flexCol>
					<flex:flexCol name="openProcessType" display="projProcessRuleForm.openProcessType" sortable="true" width="100"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="projProcessRuleList.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="add" onpress="projProcessRuleList.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="projProcessRuleList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</body>
</html>
