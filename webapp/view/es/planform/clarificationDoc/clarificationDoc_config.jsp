<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/clarificationDoc/clarificationDoc_config.js"></script>
<input type="hidden" id="projectId"   name="projectId"  value="${projectId}">
<input type="hidden" id="purchaseDocId" name="purchaseDocId" value="${purchaseDoc.objId}">
<input type="hidden" name="attachmentId" id="attachmentId" value="<c:out value="${purchaseDoc.attachRelaId}"/>"/>
<input type="hidden" name="fromType" id="fromType" value="${fromType}">	
<input type="hidden" name="auditStatus" id="auditStatus" value="Y"/>
<div class="partContainers">
<div class="formLayout" >        
     		<h5><span><dm:out value="local__OpenTendering__clarificationDoc_zh">澄清文件</dm:out></span></h5>
     			<ul>
     		        <li>
     		             <label class="short"><spring:message code="purchaseDocForm.keyWord"/>：</label>
						 <span id="keyWord">${purchaseDoc.keyWord }</span>
     		        </li>
     		        <li>
						<label class="short" for="content"><spring:message code="purchaseDocForm.content"/>：</label>
						<span id="content">${purchaseDoc.content}</span>
					</li>
					<li>
     		            <label class="short" for="filePrice"><spring:message code="purchaseDocForm.filePrice"/>：</label>
						<span id="filePrice">${purchaseDoc.filePrice }</span>
     		        </li>
					<li>
						<label class="short" for="auditStatus"><spring:message code="purchaseDocForm.auditStatus"/>：</label>
 						<span id="auditStatus">${purchaseDoc.auditStatusCN}</span>
					</li>
					<li class="formTextarea">
					<label class="short" for="memeo"><spring:message code="purchaseDocForm.memeo"/>：</label>
 						<span id="auditStatus">${purchaseDoc.memeo}</span>
					</li>
					<li>
						<label class="short">已经上传的文件：</label>
	     				<a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId}"><span>${attrName}</span></a>
					</li>
				</ul>
	
</div></div>
<div class="formLayout">        
        <h5><span>审核意见</span></h5>
		<form id="purchaseDocConfigForm" method="post">
					<textarea name="opinion" id="opinion" style="width: 99%;height: 45px;">同意</textarea>
			 <div class="conOperation">
	       	  	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
				<button id="noPassButton" type="button" tabindex="18"><span>退回</span></button>
				<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
				<button id="close" name="historyName" type="button" tabindex="20"><span>关闭</span></button>
	   	    </div>
			 
	</form>
	
</div>

<div id="historyView"></div>