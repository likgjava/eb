<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpdoc/inqpBuyRecordForm.js"></script>
<div class="formLayout form2Pa">        
	<form id="dosBuyRecordForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${dosBuyRecord.objId}"/>"/>
		<input type="hidden" name="fileId" id="fileId" value="${purchaseDoc.objId}"/>
     		<h5><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>购买记录</span></h5>
     		<ul>
     			<li class="fullLine">
				   <label>投标单位名称：</label>
				   <select name="supplierId" id="supplierId" class="required">
				        <c:forEach items="${singUpList}" var="singUp" >	
				        <option value="${singUp.supplier.objId}" <c:if test="${singUp.supplier.objId==dosBuyRecord.supplierId}">selected="selected"</c:if>>${singUp.supplier.orgName}</option>
				        </c:forEach>
				    </select>
				        <span class='eleRequired'>*</span>
				</li>
					<li class="fullLine">
						<label for="docBuyStatus"><spring:message code="dosBuyRecordForm.docBuyStatus"/></label>
						<select name="docBuyStatus" id="docBuyStatus" class="required" style="width: 153px;height: 23px">
						<option value="00" <c:if test="${00==dosBuyRecord.docBuyStatus}">selected="selected"</c:if>>购买申请</option>
						<option value="01" <c:if test="${01==dosBuyRecord.docBuyStatus}">selected="selected"</c:if>>支付</option>
						</select>	 	  	  
							<span class="eleRequired">*</span>
					</li>
				   <li class="fullLine"><label>附件：</label>
      					<div id="attachRelaId" class="uploadFile" >${dosBuyRecord.attachRelaId}</div>
      				</li>	
		</ul>
		<div class="conOperation">
			<button id="dosBuyRecordSave" type="button" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="dosBuyRecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>