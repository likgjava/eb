<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/clarificationDoc/UpdateClarificationDocForm.js"></script>
<form id="purchaseDocForm" name="purchaseDocForm" method="post"  enctype="multipart/form-data">
<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
	<div class="formLayout">        
		<input type="hidden" name="objId" id="objId" value="<c:out value="${purchaseDoc.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${projectId}">
		<input type="hidden" name="auditStatus" id="auditStatus" value=""></input>
		<input type="hidden" name="useStatus" id="useStatus" value=""></input>
		<input type="hidden" name="purFileId" value="${purchaseDoc.purFileId}"/>	
		<input type="hidden" name="fromType" id="fromType" value="${param.fromType}">	
		<h5><span>修改<dm:out value="local__OpenTendering__clarificationDoc_zh">澄清文件</dm:out></span></h5>
  		<ul>
			<li>
				<label class="short" for="keyWord"><spring:message code="purchaseDocForm.keyWord"/>：</label>
				<input type="text" name="keyWord" id="keyWord" value="${purchaseDoc.keyWord}"
							class="required"
					  />
					<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short" for="content"><spring:message code="purchaseDocForm.content"/>：</label>
				<input type="text" name="content" id="content" value="${purchaseDoc.content}"
							class="required"
					  />
					<span class="eleRequired">*</span>
			</li>
			 <li>
				<label class="short" for="auditStatus"><spring:message code="purchaseDocForm.auditStatus"/>：</label>
 				<span id="auditStatus">${purchaseDoc.auditStatusCN}</span>
			 </li>
			 <li class="formTextarea" >
				<label class="short" for="memeo"  ><spring:message code="purchaseDocForm.memeo"/>：</label>
				<textarea  class="required" name="memeo" id="memeo" maxlength="200"></textarea>
				<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short" for="attachRelaId"><dm:out value="local__OpenTendering__clarificationDoc_zh">澄清文件</dm:out>附件：</label>
				<input type="hidden" value="" id="attachRelaId" name="attachRelaId" value="${purchaseDoc.attachRelaId}"/>
				<input type="hidden" value="00" id="confirmStatus" name="confirmStatus"/>
				<input type="file" name="attachFile" id="attachFile"/>
			</li>
			<li>
				<label class="short">已经上传的文件：</label>
	     		<a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId}"><span>${attrName}</span></a>
			</li>
		</ul>
	</div>
</form> 
		<div class="conOperation">
			<button id="purchaseDocSave" type="button" tabindex="18"><span>保存</span></button>
			<button id="purchaseDocSubmit" type="submit" tabindex="18"><span>提交</span></button>
			<button id="close" name="historyName" type="button" tabindex="20"><span>关闭</span></button>
		</div>
	<div id="historyView"></div>
