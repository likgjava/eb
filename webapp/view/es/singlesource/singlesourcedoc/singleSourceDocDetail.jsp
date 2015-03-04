<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/singlesource/singlesourcedoc/singleSourceDocDetail.js"></script>
<div class="partContainers">
<form id="purchaseDocForm" method="post">
<div class="formLayout" >        
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="projectId" id="projectId" value="<c:out value="${projectId}"/>"/>
		<input type="hidden" name="attachmentId" id="attachmentId" value="<c:out value="${purchaseDoc.attachRelaId}"/>"/>
     		<h5><span>采购文件</span></h5>
     		<ul>
     		         <li>
     		             <label for="keyWord"><spring:message code="purchaseDocForm.keyWord"/>：</label>
							<span id="keyWord">${purchaseDoc.keyWord }</span>
     		        </li>
     		        <li>
						<label for="content"><spring:message code="purchaseDocForm.content"/>：</label>
							<span id="content">${purchaseDoc.content}</span>
					</li>
					<li>
     		             <label for="filePrice"><spring:message code="purchaseDocForm.filePrice"/>：</label>
							<span id="filePrice">${purchaseDoc.filePrice }</span>
     		        </li>
					 <li>
						<label for="auditStatus"><spring:message code="purchaseDocForm.auditStatus"/>：</label>
 								 <span id="auditStatus">${purchaseDoc.auditStatusCN}</span>
					</li>
		</ul>
	
</div>
<div class="formLayout">
	<h5><span>文件下载</span></h5> 
	<div id="downLoadView">
 	<ul>
	   <li class="fullLine" >
  <span id="fileName"></span><a style="margin-left:25px; " href="#" onclick="singleSourceDocDetail.downloadFile('${purchaseDoc.attachRelaId}');">点击下载</a>
	   </li>
	</ul>
	</div>
</div>
</form>
</div>

 		