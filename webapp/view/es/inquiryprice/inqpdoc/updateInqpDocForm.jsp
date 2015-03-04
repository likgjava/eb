<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpdoc/updateInqpDocForm.js"></script>
	<div class="formLayout">
	<div class="functionBtnDiv" style="margin-top: 0px">
	<button id="useToolCreatePurchaseDoc" type="button" tabindex="17"><span>使用工具制作招标文件</span></button>
	<a class="abtn" id="downUETool" href="#" onclick="javascript:window.open('<%=request.getContextPath()%>/toolFile/UESetup0916.zip','_self');">(下载制作工具)</a>
</div>   
	<form id="purchaseDocForm" name="purchaseDocForm" method="post"  enctype="multipart/form-data">   
		<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>  
		<input type="hidden" name="objId" id="objId" value="<c:out value="${purchaseDoc.objId}"/>"/>
		<input type="hidden" name="projectProjCode" id="projectProjCode" value="${project.projCode}">
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${projectId}">
		<input type="hidden" name="fromType" id="fromType" value="${fromType}">	
  		<ul>
  		<%--
			<li>
				<label for="keyWord" class="short"><spring:message code="purchaseDocForm.keyWord"/>：</label>
				<input type="text" name="keyWord" id="keyWord" value="${purchaseDoc.keyWord}"
							class="required"
					  value="${purchaseDoc.keyWord}" />
					<span class="eleRequired">*</span>
			</li>
			<li>
				<label for="content" class="short"><spring:message code="purchaseDocForm.content"/>：</label>
				<input type="text" name="content" id="content" value="${purchaseDoc.content}"
							class="required"
					   value="${purchaseDoc.content}"/>
					<span class="eleRequired">*</span>
			</li>
		--%>
			<li>
				<label for="filePrice" class="short"><spring:message code="purchaseDocForm.filePrice"/>：</label>
				<input type="text" name="filePrice" id="filePrice" 
							class="required money" maxlength="16"
					 value="${filePrice}" />
					<span class="eleRequired">*</span>
			</li>
			 <li>
				<label for="auditStatus" class="short"><spring:message code="purchaseDocForm.auditStatus"/>：</label>
 				<span id="auditStatus">${purchaseDoc.auditStatusCN}</span>
			 </li>
			<li>
				<label for="attachRelaId" class="short"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>：</label>
				<input type="hidden" value="" id="attachRelaId" name="attachRelaId" value="${purchaseDoc.attachRelaId}"/>
				<input type="hidden" value="00" id="confirmStatus" name="confirmStatus"/>
				<input type="file" name="attachFile" id="attachFile"/>
			</li>
				<c:if test="${purchaseDoc.useStatus!='00'}">
			<li>
			  <label for="attachRelaId" class="short">已经上传的文件：</label>
			  	  <a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId}"><span>${attrName}</span></a>	
			</li>
			</c:if>
		</ul>
		</form> 	
		<div class="conOperation">
		<button id="purchase_doc_save" type="submit" tabindex="18"><span>提交</span></button>
		<button id="historyId" name="historyName" type="button" tabindex="20"><span>查看历史</span></button>
		</div>
	</div>

<div id="historyView"></div>
<!--<div id="congrousFactor"></div>-->
