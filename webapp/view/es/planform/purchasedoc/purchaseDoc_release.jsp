<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchaseDoc_release.js"></script>
<input type="hidden" id="projectId"   name="projectId"  value="${param.projectId}">
<input type="hidden" id="nodoc" name="nodoc" value="${NopurDoc}">
<input type="hidden" id="purchaseDocId" name="purchaseDocId" value="${purchaseDoc.objId}">
<div id="purchaseDoc"></div>
<div class="formLayout">        

		<div class="conOperation">
		 <button type="button" id="submitBtn"><span>发布<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></button>
		</div>
	
</div>