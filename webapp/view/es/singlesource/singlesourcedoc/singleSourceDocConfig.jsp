<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/singlesource/singlesourcedoc/singleSourceDocConfig.js"></script>
<input type="hidden" id="projectId"   name="projectId"  value="${projectId}">
<input type="hidden" id="purchaseDocId" name="purchaseDocId" value="${purchaseDoc.objId}">
<input type="hidden" name="auditStatus" id="auditStatus" value="Y"/>
<input type="hidden" name="attachmentId" id="attachmentId" value="<c:out value="${purchaseDoc.attachRelaId}"/>"/>
<input type="hidden" name="fromType" id="fromType" value="${fromType}"/>
<div class="partContainers">
<form id="purchaseDocForm" method="post">
<div class="formLayout" >        
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
     		<h5><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></h5>
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
  <span id="fileName"></span><a style="margin-left:25px; " href="#" onclick="InqpDocConfig.downloadFile('${purchaseDoc.attachRelaId}');">点击下载</a>
	   </li>
	</ul>
	</div>
</div>
</form>
</div>
<div class="formLayout">        
         <h5>审核意见</h5>
		<form id="purchaseDocConfigForm" method="post">
     		<ul style="padding-top:0px">
     			<li class="formTextarea">
					<label>意见</label>
					<textarea name="opinion" id="opinion"></textarea>
				</li>
			<li>			
			 <div class="conOperation">
	       	  	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
				<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
				<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
	   	    </div>
			</li>
			</ul>
			 
	</form>
	
</div>

<div id="historyView"></div>