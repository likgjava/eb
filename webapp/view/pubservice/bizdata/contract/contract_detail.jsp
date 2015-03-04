<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formLayout form2Pa">
	<form id="contractDetailForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
     	<h4><span>合同</span></h4>
			     	<ul>
						<li>
			 				<label for="contractForm.contractName"><spring:message code="contractForm.contractName"/>：</label>
							<span id="contractName">${contract.contractName }</span>
						</li>
						<li>
			 				<label for="contractForm.contractNo"><spring:message code="contractForm.contractNo"/>：</label>
							<span id="contractNo">${contract.contractNo }</span>
						</li>
						<li>
			 				<label for="contractForm.buyer"><spring:message code="contractForm.buyerName"/>：</label>
							<span id="buyer.orgName"><a href="javascript:void(0);" onclick="linkInfo('${contract.buyerId}');return false;">${contract.buyerName}</a></span>
						</li>
						<li>
			 				<label for="contractForm.supplier"><spring:message code="contractForm.supplierName"/>：</label>
							<span id="supplier.orgName"><a href="javascript:void(0);" onclick="linkInfo('${contract.supplierId}');return false;">${ contract.supplierName}</a></span>
						</li>
						<li>
			 				<label for="contractForm.total"><spring:message code="contractForm.total"/>：</label>
							<span id="total"><strong>￥<fmt:formatNumber value="${contract.total }" pattern="#,##0.00#" /></strong> 万元</span>
						</li>
						<li>
			 				<label for="contractForm.contractType"><spring:message code="contractForm.contractType"/>：</label>
							<span id="contractType">${contract.contractTypeCN }</span>
						</li>
						<li>
			 				<label for="contractForm.useStatus"><spring:message code="contractForm.useStatus"/>：</label>
							<span id="useStatus">${contract.useStatusCN }</span>
						</li>
						<li>
			 				<label for="contractForm.contractSignedTime"><spring:message code="contractForm.contractSignedTime"/>：</label>
							<span><fmt:formatDate value="${contract.contractSignedTime }" pattern= 'yyyy-MM-dd '/></span>
						</li>
						<li>
			 				<label for="contractForm.deliveryTime"><spring:message code="contractForm.deliveryTime"/>：</label>
							<span id="deliveryTime">${contract.deliveryTime }</span>
						</li>
						<li>
			 				<label for="contractForm.deliveryPlace"><spring:message code="contractForm.deliveryPlace"/>：</label>
							<span id="deliveryPlace">${contract.deliveryPlace }</span>
						</li>
						<li>
			 				<label for="contractForm.contractBeginTime"><spring:message code="contractForm.contractBeginTime"/>：</label>
							<span >${contract.contractBeginTime }</span>
						</li>
						<li>
			 				<label for="contractForm.contractEndTime"><spring:message code="contractForm.contractEndTime"/>：</label>
							<span >${contract.contractEndTime }</span>
						</li>
						<li>
			 				<label for="contractForm.createUser"><spring:message code="contractForm.createUser"/>：</label>
							<span id="createUser">${contract.createUser.usName }</span>
						</li>
						<li>
			 				<label for="contractForm.createTime"><spring:message code="contractForm.createTime"/>：</label>
							<span id="createTime"><fmt:formatDate value="${contract.contractSignedTime }" pattern= 'yyyy-MM-dd '/></span>
						</li>
						<li>
			 				<label for="contractForm.buyerConfirmUser"><spring:message code="contractForm.buyerConfirmUser"/>：</label>
							<span id="buyerConfirmUser">${contract.buyerConfirmUser.usName }</span>
						</li>
						<li>
			 				<label for="contractForm.supplierConfirmUser"><spring:message code="contractForm.supplierConfirmUser"/>：</label>
							<span id="supplierConfirmUser">${contract.supplierConfirmUser.usName }</span>
						</li>
						<li>
			 				<label for="contractForm.buyerConfirmStatus"><spring:message code="contractForm.buyerConfirmStatus"/>：</label>
							<span id="buyerConfirmStatus">${contract.buyerConfirmStatusCN }</span>
						</li>
						<li>
			 				<label for="contractForm.supplierConfirmStatus"><spring:message code="contractForm.supplierConfirmStatus"/>：</label>
							<span id="supplierConfirmStatus">${contract.supplierConfirmStatusCN }</span>
						</li>
						<li>
			 				<label for="contractForm.buyerConfirmDate"><spring:message code="contractForm.buyerConfirmDate"/>：</label>
							<span id="buyerConfirmDate">${contract.buyerConfirmDate }</span>
						</li>
						<li>
			 				<label for="contractForm.supplierConfirmDate"><spring:message code="contractForm.supplierConfirmDate"/>：</label>
							<span id="supplierConfirmDate">${contract.supplierConfirmDate }</span>
						</li>
						<li class="formTextarea">
			 				<label for="contractForm.contractFile"><spring:message code="contractForm.contractFile"/>：</label>
							<span id="contractFile">${contract.contractFile}</span>
						</li>
						<li class="formTextarea">
			 				<label for="contractForm.memo"><spring:message code="contractForm.memo"/>：</label>
							<span id="memo">${contract.memo }</span>
						</li>
					</ul>
		   <div class="conOperation">
		   		<button type="button" name="enterBackBtn" type="button" tabindex="20" onclick="common.open('${project.srcUrl}','${project.objId}');"><span>进入</span></button>
		   		<button type="button" name="historyBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
		   </div>
	</form>
</div>
<script>
$(document).ready(function(){
	$("#contractFile").loadPage(//加载合同文件页面
		$("#initPath").val()+"/AttachmentController.do?defineSelf=contractFile&isSelect=yes&attachRelaId="+$("#contractFile").innerHTML
	);
});
//弹出机构信息
function linkInfo(id){
	var url = $('#initPath').val()+'/OrgInfoController.do?method=getOrgAllInfo&orgInfoId='+id;
	$.epsDialog({
        title:'机构详情',
        url:url,
        width: '900',
        height: '500'
    }); 
}
</script>