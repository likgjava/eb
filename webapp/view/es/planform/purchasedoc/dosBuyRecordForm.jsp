<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/dosBuyRecordForm.js"></script>
<div class="formLayout form2Pa">
	<form id="dosBuyRecordForm" method="post">
		<input type="hidden" value="${dosBuyRecord.objId}" id="objId" name="objId"/>        
		<input type="hidden" value="${dosBuyRecord.tenderId}" id="tenderId" name="tenderId"/>
		<input type="hidden" value="${dosBuyRecord.isSubProject}" id="isSubProject" name="isSubProject"/>
		<h5><span>录入标书费</span></h5>
		<ul>
			<li>
				<label class="short">投标单位：</label>
				<input type="text" name="supplierName" id="supplierName" class="required" value="${dosBuyRecord.supplierName}"/>
				<span class="eleRequired">*</span>
     			<input type="hidden" value="${dosBuyRecord.supplierId}" name="supplierId" id="supplierId"/>
			</li>
			<li>
				<label class="short">标书费（元）：</label>
				<input type="text" value="${dosBuyRecord.amount}" id="amount" name="amount" class="required money"/>
				<span class="eleRequired">*</span>
			</li>
			<li class="fullLine">
				<label class="short">支付方式：</label>
				<input type="radio" id="payType" name="payType" value="01" <c:if test="${dosBuyRecord.payType==01}">checked="checked"</c:if>/>汇票&nbsp;
				<input type="radio" id="payType" name="payType" value="02" <c:if test="${dosBuyRecord.payType==02}">checked="checked"</c:if>/>支票&nbsp;
				<input type="radio" id="payType" name="payType" value="03" <c:if test="${dosBuyRecord.payType==03}">checked="checked"</c:if>/>电汇&nbsp;
				<input type="radio" id="payType" name="payType"  value="04" <c:if test="${dosBuyRecord.payType==04}">checked="checked"</c:if>/>现金&nbsp;
				<input type="radio" id="payType" name="payType" value="05" <c:if test="${dosBuyRecord.payType==05}">checked="checked"</c:if>/>其他
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">联系人：</label>
				<input type="text" value="${dosBuyRecord.linker}" id="linker" name="linker" class="required"/>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short">联系方式：</label>
				<input type="text" value="${dosBuyRecord.linkerTel}" id="linkerTel" name="linkerTel" class="required"/>
				<span class="eleRequired">*</span>
			</li>
			
			<c:choose>
				<c:when test="${dosBuyRecord!=null&&dosBuyRecord.objId!=null}">
					<li>
					<label class="short">缴纳证明：</label>
						<input type="hidden" name="attachRelaId1" id="attachRelaId1" value=""/>	      
						<input type="file" name="attachRelaIdBak" id="attachRelaIdBak"/>
					</li>
					<li>
						<label class="short">原文件：</label>
						<div id="attachRelaId3" class="uploadFile"></div>
						<input type="hidden" id="attachRelaId2" value="${dosBuyRecord.docBuyFile}"/>	      
					</li>
					<li>
						<label class="short">发票附件：</label>
						<input type="hidden" value="" id="invoiceFile1" name="invoiceFile1"/>
						<input type="file" name="invoiceFileBak" id="invoiceFileBak"/>
					</li>
					<li>
						<label class="short">原文件：</label>
						<input type="hidden" id="invoiceFile2" value="${dosBuyRecord.invoiceFile}"/>	    
						<div id="invoiceFile3" class="uploadFile"></div>
					</li>
				</c:when>
				<c:otherwise>
					<li class="fullLine">
						<label class="short">缴纳证明：</label>
						<input type="hidden" name="attachRelaId1" id="attachRelaId1" value=""/>	      
						<input type="file" name="attachRelaIdBak" id="attachRelaIdBak"/>
					</li>
					<li class="fullLine">
						<label class="short">发票附件：</label>
						<input type="hidden" value="" id="invoiceFile1" name="invoiceFile1"/>
						<input type="file" name="invoiceFileBak" id="invoiceFileBak"/>
					</li>
				</c:otherwise>
			</c:choose>
			<li class="fullLine">
				<label class="short">备注：</label>
				<textarea rows="2" cols="2" id="remark" name="remark" style="height: 70px;width: 500px;text-align: left" maxlength="1000" class="maxInput">
				${dosBuyRecord.remark}
				</textarea>
				<span class="eleRequired"></span>
			</li>
		</ul>
		<div class="conOperation">
			<button id="dosBuyRecordSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="dosBuyRecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>