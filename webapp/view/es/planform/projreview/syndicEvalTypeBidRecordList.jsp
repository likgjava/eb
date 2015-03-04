<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/syndicEvalTypeBidRecordList.js"></script>
<div id="projectMain">
	<div id="projectBtnDiv" class="BtnDiv" style="text-align: left;" >
		<div id="syndicEvalTypeBidRecord_div">
			<c:forEach items="${congruousFactorTypeList}" var="congruousFactorType">
				<button class="siNext siAccept" style="height:23px;" factorTypeId="${congruousFactorType.objId}" title="评审 &nbsp;  ${congruousFactorType.typeName}" packId="${packId}" supplierId="${supplierId}" typeCode="${congruousFactorType.typeCode}"><span>${congruousFactorType.typeName}</span></button>
			</c:forEach>
		</div>
	</div>
	<div class="partContainers">
		<div id="syndicEvalBidRecord_form_div"></div>
	</div>
</div>