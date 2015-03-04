<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/proofOpinionForm.js"></script>
<div class="formLayout form2Pa">
     	<h5><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>论证</span></h5>
     	<ul>
     	<form id="proofOpinionForm" method="post">	 
     	<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="tenderId" id="tenderId" value="${projectId}">
		<input type="hidden" name="fileId" id="fileId" value="${fileId}">
	     	<li>
	     		<label class="short" for="proofOpinionForm.name" ><spring:message code="proofOpinionForm.name"/></label>
					<input type="text" name="name" id="name" class="required" /><span class="eleRequired">*</span> 
    	    </li>
	     	<li>
	     		<label class="short" for="proofOpinionForm.phone" ><spring:message code="proofOpinionForm.phone"/> </label>
					<input type="text" name="phone" id="phone" class="required cnPhone" /><span class="eleRequired">*</span>
    	    </li>
    	      </form>	
    	    <li class="fullLine">
    	    	<label class="short">上传附件</label>
    	    	<div class="uploadFile" id="attachRelaId"></div>
    	    </li>
		</ul>

	<h5><span>论证意见</span></h5>
	 <ul> 
	    <li class="formTextarea"  >
				<label class="short" class="short"><spring:message code="proofOpinionForm.opinion"/>：</label>
				<textarea rows="50" cols="50" name="opinion" id="opinion" class="required maxInput" maxlength="200"></textarea>
				<span class="eleRequired">*</span>
		</li>
	</ul>
 <div class="conOperation">
	<button type="button" id="proofOpinionSave"><span><spring:message code="globe.save"/></span></button>
	<button type="button" id="proofOpinionReturn" tabindex="19""><span><spring:message code="globe.return"/></span></button>
  </div>

	</div>

	

