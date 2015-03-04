<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpdoc/inqpBuyRecordDetail.js"></script>
<div class="formLayout form2Pa">        
	<form id="dosBuyRecordForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${dosBuyRecord.objId}"/>"/>
		<input type="hidden" name="fileId" id="fileId" value="${purchaseDoc.objId}"/>
     		<h5><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>购买记录</span></h5>
     		<ul>
     			<li class="fullLine">
				   <label>供应商名称：</label>
				   <span>${dosBuyRecord.supplierName}</span>
				</li>
					<li class="fullLine">
						<label for="docBuyStatus"><spring:message code="dosBuyRecordForm.docBuyStatus"/></label>
						<span>${dosBuyRecord.docBuyStatusCN}</span>
					</li>
				   <li class="fullLine"><label>附件：</label>
      					<div id="attachRelaId" class="uploadFile" >${dosBuyRecord.attachRelaId}</div>
      				</li>	
		</ul>
		<div class="conOperation">
			<button id="dosBuyRecordReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</form>
</div>