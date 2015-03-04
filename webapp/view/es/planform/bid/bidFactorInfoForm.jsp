<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/bid/bidFactorInfoForm.js"></script>
<div class="frameMainSub" id="bid_factor_info_tabs">
 	<c:set var="num1" value="0"></c:set>
	<ul>
	 	<c:forEach items="${checkPackList}" var="pack">
	 		<li><a href="#bid_factor_info_a_${num1}" ><span>${pack.projName}</span></a></li>
  			<c:set var="num1" value="${num1+1}"/>
	 	</c:forEach>
  	</ul>
  	<c:set var="num1" value="0"></c:set>
  	<c:set var="i" value="0"/>
  	<c:forEach items="${checkPackList}" var="pack">
  		<div id="bid_factor_info_a_${num1}">
  		<table class="tableList">
			<tr>
				<th style="width: 20%"><label><spring:message code="congruousFactorForm.factorName"/></label></th>
				<th style="width: 20%"><label><spring:message code="congruousFactorForm.projName"/></label></th>
				<th style="width: 20%"><label><spring:message code="congFactorResponseForm.respValue"/></label></th>
				<th><label><spring:message code="congFactorResponseForm.attr"/></label></th>
			</tr>
			<c:forEach items="${factorTypeList}" var="factorType">
				<tr>
					<td colspan="4">
						<span style="font-weight: bold;">${factorType.typeName}</span>
					</td>
				</tr>
				<c:forEach items="${congruousFactorList}" var="congruousFactor">
					<c:if test="${factorType.objId == congruousFactor.factorTypeId && congruousFactor.packId == pack.objId}">
						<tr>
							<td><span style="margin-left: 30px">${congruousFactor.factorName}</span></td>
							<td>${congruousFactor.packName}</td>
							<td>
								<input name="respValue${i}" value="${congruousFactor.respValue}" <c:if test="${congruousFactor.isNeed == '01'}">class='required' </c:if>  />
								<span class="eleRequired"><c:if test="${congruousFactor.isNeed == '01'}">*</c:if></span>
							</td>
							<td>
								<input type="file" name="attrFile${i}" id="attrFile${i}" />
								<input type="hidden" name="isUploadFile${i}" value="false"/>
								<input type="hidden" name="objId${i}" value="${congruousFactor.responseId}"/>
								<input type="hidden" name="factorId${i}" value="${congruousFactor.factorId}"/>
								<input type="hidden" name="factorName${i}" value="${congruousFactor.factorName}"/>
								<input type="hidden" name="attrId${i}" value="${congruousFactor.attrId}"/>
								<input type="hidden" name="packId${i}" value="${congruousFactor.packId}"/>
								<c:if test='${null != congruousFactor.attrId && "" != congruousFactor.attrId}'>
									<a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${congruousFactor.attrId}">${congruousFactor.viewName}</a>
									&nbsp;&nbsp;
									<a href="#" onClick="bidFactorInfoForm.removeFile('${i}');" id="removeFileEle${i}">删除</a>
								</c:if>
							</td>
							<c:set var="i" value="${i+1}"/>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
		</table>
  		</div>
  		<c:set var="num1" value="${num1+1}"/>
 	</c:forEach>
 	<input type="hidden" name="listNum" value="${i}" id="listNum"/>
</div> 		





