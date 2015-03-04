<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/projreview/createSyndicEvalBidRecordForm.js"></script>
<div class="formLayout form2Pa">
	<div>
		<form id="evalFactorResultForm" method="post" >
			<table class="tableList">
				<caption></caption>
				<tr>
					<th><spring:message code="congruousFactorForm.factorName"/></th>
					<th><spring:message code="congFactorResponseForm.respValue"/></th>
					<th><spring:message code="congFactorResponseForm.attr"/></th>
					<c:if test="${typeCode!='competencyAudit'}">
					<th>打分</th>
					</c:if>
					<th>意见</th>
				</tr>
				<c:set var="i" value="0"></c:set>
				<c:forEach items="${evalFactorResultList}" var="evalFactorResult" >
					<tr>
						<td>${evalFactorResult.factorName}</td>
						<td>${evalFactorResult.respValue}</td>
						<td>
							<c:if test="${null != evalFactorResult.respAttrId && '' != evalFactorResult.respAttrId}">
								<a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${evalFactorResult.respAttrId}">${evalFactorResult.respAttrName}</a>
							</c:if>
							<input type="hidden" name="evalFactorResult[${i}].objId" value="${evalFactorResult.objId}" ></input>
							<input type="hidden" name="evalFactorResult[${i}].sellerRecId" value="${evalFactorResult.sellerRecId}" ></input>
							<input type="hidden" name="evalFactorResult[${i}].factorTypeId" value="${evalFactorResult.factorTypeId}" ></input>
							<input type="hidden" name="evalFactorResult[${i}].factorTypeName" value="${evalFactorResult.factorTypeName}" ></input>
							<input type="hidden" name="evalFactorResult[${i}].factorId" value="${evalFactorResult.factorId}" ></input>
							<input type="hidden" name="evalFactorResult[${i}].factorName" value="${evalFactorResult.factorName}" ></input>
						</td>
						<c:if test="${typeCode!='competencyAudit'}">
						
						<td>
							<input type="text" name="evalFactorResult[${i}].score" value="${evalFactorResult.score}" style="width: 60px;" max="${evalFactorResult.maxScore }" min="0" class="required"></input>
							<span class="eleRequired">*</span>
						</td>
						</c:if>
						<td>
							<textarea name="evalFactorResult[${i}].memo" style="width: 220px;height: 30px;">${evalFactorResult.memo}</textarea>
						</td>
					</tr>
					<c:set var="i" value="${i+1}"></c:set>
				</c:forEach>
			</table>
			<input type="hidden" name="objId" value="${evaSellerRecord.objId}" ></input>
			<input type="hidden" name="evalId" value="${evaSellerRecord.evalId}" ></input>
			<input type="hidden" id="supplier.objId" value="${evaSellerRecord.supplier.objId}" ></input>
			<input type="hidden" name="supplierName" value="${evaSellerRecord.supplierName}" ></input>
			<input type="hidden" name="factorScore" value="${evaSellerRecord.factorScore}" ></input>
			<input type="hidden" name="factorOpinion" value="${evaSellerRecord.factorOpinion}" ></input>
			<input type="hidden" name="subProjId" value="${evaSellerRecord.subProjId}" ></input>
			<input type="hidden" name="subProjName" value="${evaSellerRecord.subProjName}" ></input>
			<input type="hidden" name="quoteSum" value="${evaSellerRecord.quoteSum}" ></input>
			<input type="hidden" name="subProjCode" value="${evaSellerRecord.subProjCode}" ></input>
			<input type="hidden" name="useStatus" value="${evaSellerRecord.useStatus}" ></input>
		</form>
	</div>
	<div class="conOperation">
		<button id="record_submit" type="button" tabindex="18"><span>提交</span></button>
	</div>
</div>