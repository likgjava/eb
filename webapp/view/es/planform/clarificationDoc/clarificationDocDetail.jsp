<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/clarificationDoc/clarificationDocDetail.js"></script>
<div class="partContainers">
<form id="purchaseDocForm" method="post">
<div class="formLayout" >        
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
     	<input type="hidden" name="attachmentId" id="attachmentId" value="<c:out value="${purchaseDoc.attachRelaId}"/>"/>	
     	<input type="hidden" name="projectId" id="projectId" value="${projectId}"/>
     		<h5><span><dm:out value="local__OpenTendering__clarificationDoc_zh">澄清文件</dm:out></span></h5>
     		<ul>
   		        <li>
  		             <label class="short"><spring:message code="purchaseDocForm.keyWord"/>：</label>
					 <span id="keyWord">${purchaseDoc.keyWord }</span>
   		     	</li>
   		     	<li>
					<label class="short"><spring:message code="purchaseDocForm.content"/>：</label>
					<span id="content">${purchaseDoc.content}</span>
				</li>
				<li>
   		             <label class="short"><spring:message code="purchaseDocForm.filePrice"/>：</label>
					 <span id="filePrice">${purchaseDoc.filePrice }</span>
   		   	     </li>
				 <li>
					<label class="short"><spring:message code="purchaseDocForm.auditStatus"/>：</label>
					<span id="auditStatus">${purchaseDoc.auditStatusCN}</span>
				</li>
				 <li class="formTextarea">
					<label class="short"><spring:message code="purchaseDocForm.memeo"/>：</label>
					<span id="memeo">${purchaseDoc.memeo}</span>
				</li>
				<li>
					<label class="short">已经上传的文件：</label>
		     		<a href="<%=request.getContextPath()%>/AttachmentController.do?method=downLoadFile&objId=${attrId}"><span>${attrName}</span></a>
				</li>
			</ul>
				<div class="conOperation">
				<button id="close" name="historyName" type="button" tabindex="20"><span>关闭</span></button>
				</div>
</div>
</form>
</div>

 		