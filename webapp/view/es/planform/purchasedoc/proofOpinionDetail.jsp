<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/proofOpinionDetail.js"></script>
<div class="formLayout form2Pa">
<input type="hidden" id="purchaseDocId" value="${purchaseDoc.objId}">
<input type="hidden" id="num" value="${fn:length(proofOpinionlist)}">
<h5><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>论证</span></h5>
  	<ul>
  	<c:forEach items="${proofOpinionlist}" var="proofOpinion">
   	<li><label class="short"  for="proofOpinionForm.name"><spring:message code="proofOpinionForm.name"/><span class="eleRequired">*</span> </label>
		<span id="name">${proofOpinion.name}</span></li>
   	<li><label class="short" for="proofOpinionForm.phone"><spring:message code="proofOpinionForm.phone"/><span class="eleRequired">*</span> </label>
		<span id="phone">${proofOpinion.phone}</span></li>
	<c:if test="${!empty proofOpinion.attachRelaId}">
	<li class="fullLine" >
		<label class="short" for="attachRelaId"><spring:message code="oppugnRequisitionForm.attachRelaId"/>：</label>
		<c:forEach items="${proofOpinion.attachmentlist}" var="attachment">
		<a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attachment.objId}"><span>${attachment.viewName}</span></a>&nbsp;&nbsp;&nbsp;&nbsp;	
		</c:forEach>
		</li>	
	</c:if>
   	<li class="formTextarea" style="height:50px;">
<label class="short"><spring:message code="proofOpinionForm.opinion"/>：</label>
<span  >${proofOpinion.opinion}</span>
   </li>
  	</c:forEach>
</ul>
</div>
<div class="formLayout" style="height: 73px;">   
<span></span> 
<form id="purchaseDocForm" method="post">
<input type="hidden" name="objId" id="objId" value="<c:out value="${purchaseDoc.objId}"/>"/>
<ul>
<li class="formTextarea"  style="height:50px;">
<label class="short"  for="deliveryRequire"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>论证结论：</label>
<textarea   name="proofOpinion"  style="height:40px;width:60%"  id="proofOpinion"  class="maxInput" maxlength="250"  style="width: 99%;height: 45px;">${purchaseDoc.proofOpinion}</textarea>
</li>
</ul>
</form>
</div>


<div class="conOperation">
<button type="button" id="proofOpinionSave"><span><spring:message code="globe.save"/></span></button>
<button type="button" id="proofOpinionReturn" type="button" tabindex="20""><span><spring:message code="globe.return"/></span></button>
<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
</div>
	       

<div id="historyView"></div>

