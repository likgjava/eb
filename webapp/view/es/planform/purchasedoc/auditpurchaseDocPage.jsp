<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/auditpurchaseDocPage.js"></script>
<input type="hidden" id="projectId"   name="projectId"  value="${purchaseDoc.project.objId}">
<input type="hidden" id="purchaseDocId" name="purchaseDocId" value="${purchaseDoc.objId}">
<input type="hidden" name="auditStatus" id="auditStatus" value="Y"/>
<input type="hidden" id="divTarget"   name="divTarget"  value="${divTarget}">
<input type="hidden" id="divTargetUrl"   name="divTargetUrl"  value="${divTargetUrl}">
<div class="formLayout">
      <h5><span>项目信息</span></h5>
      <ul>
        <li>
           <label for="projName"><spring:message code="projectForm.projName"/>：</label>
							<span id="projName">${purchaseDoc.project.projName}</span>
        </li>
        <li>
           <label for="projCode"><spring:message code="projectForm.projCode"/>：</label>
							<span id="projCode">${purchaseDoc.project.projCode}</span>
        </li>
      </ul>           



</div>
<div id="purchaseDoc"></div>
<div class="formLayout">        

		<form id="purchaseDocConfigForm" method="post">
     		<ul>
			<li>			
			 <div class="conOperation">
	       	  	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
				<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
	   	    </div>
			</li>
			</ul>
			 
	</form>
	
</div>