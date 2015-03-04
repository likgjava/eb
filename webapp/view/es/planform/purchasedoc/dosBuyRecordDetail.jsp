<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/dosBuyRecordDetail.js"></script>

<div class="formLayout form2Pa">        
	<h5><span>标书费</span></h5>
		<ul>
			<li>
				<label class="short">投标单位：</label>
				<span>${dosBuyRecord.supplierName}</span>
			</li>
			<li>
				<label class="short">标书费（元）：</label>
				<span>${dosBuyRecord.amount}</span>
			</li>
			<li>
				<label class="short">支付方式：</label>
				<span>${dosBuyRecord.payTypeCN}</span>
			</li>
			<li>
				<label class="short">审核状态：</label>
				<span>${dosBuyRecord.useStatusCN}</span>
			</li>
			<li>
				<label class="short">联系人：</label>
				<span>${dosBuyRecord.linker}</span>
			</li>
			<li>
				<label class="short">联系方式：</label>
				<span>${dosBuyRecord.linkerTel}</span>
			</li>
			<li class="fullLine">
				<label class="short">缴纳证明：</label>
				<div id="attachRelaId3" class="uploadFile"></div>
				<input type="hidden" id="attachRelaId2" value="${dosBuyRecord.docBuyFile}"/>
			</li>
			<li class="fullLine">
				<label class="short">发票附件：</label>
				<input type="hidden" id="invoiceFile2" value="${dosBuyRecord.invoiceFile}"/>	    
				<div id="invoiceFile3" class="uploadFile"></div>
			</li>
			<li class="fullLine">
				<label class="short">备注：</label>
				<span>${dosBuyRecord.remark}</span>
			</li>
		</ul>
		<c:if test="${fromDljg=='YES'}">
			<div class="conOperation">
				<button id="dosBuyRecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
			</div>
		</c:if>		
</div>