<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchaseDoc_config.js"></script>
<input type="hidden" id="projectId"   name="projectId"  value="${projectId}">
<input type="hidden" id="purchaseDocId" name="purchaseDocId" value="${purchaseDoc.objId}">
<input type="hidden" name="fromType" id="fromType" value="${fromType}">	
<input type="hidden" name="auditStatus" id="auditStatus" value="Y"/>
<div class="formLayout" >        
  		<h5><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></h5>
  		<ul>
  		<%--
	         <li>
	             <label class="short" for="keyWord"><spring:message code="purchaseDocForm.keyWord"/>：</label>
					<span id="keyWord">${purchaseDoc.keyWord }</span>
	         </li>
	        <li>
			<label class="short" for="content"><spring:message code="purchaseDocForm.content"/>：</label>
				<span id="content">${purchaseDoc.content}</span>
		</li>
		--%>
		<li>
	             <label class="short" for="filePrice"><spring:message code="purchaseDocForm.filePrice"/>：</label>
				<span id="filePrice">${purchaseDoc.filePrice }</span>
  		        </li>
		 <li>
			<label class="short" for="auditStatus"><spring:message code="purchaseDocForm.auditStatus"/>：</label>
						 <span id="auditStatus">${purchaseDoc.auditStatusCN}</span>
		</li>
		<!--  
		<li>
			<label class="short">文件下载：</label>
			<div class="hidden" id="downAddr"><%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadDocFile&purchaseDocId=${purchaseDoc.objId}</div>
				<a class="sysicon siDownBtn" href="<%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadDocFile&purchaseDocId=${purchaseDoc.objId}"  id="downLoad" title="点击下载【${attrName2}】"><span>${attrName2}</span></a>
		</li>-->
		<li>
			<label class="short">招标文件下载：</label>
			<c:if test="${message!=''}">
				<span style="color: red">${message}</span>
			</c:if>
			<c:if test="${message==''||message == null ||message == undefined}">
				<div class="hidden" id="downAddr2"><%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadDocFile&purchaseDocId=${purchaseDoc.objId}</div>
					<a class="sysicon siDownBtn" href="<%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadDocFile&purchaseDocId=${purchaseDoc.objId}"  id="downLoad2" title="点击下载【${attrName2}】"><span>${attrName2}</span></a>
			</c:if>
		</li>
  </ul>
</div>
<div class="formLayout formPa" style="height: 73px;">        
    <h5><span>审核意见</span></h5>
    <ul>    
		<li style="height:50px;">
			<form id="purchaseDocConfigForm" method="post">
			<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 99%;height: 45px;">同意</textarea>
			<span class="eleRequired"></span>
			</form>
		</li>
	</ul>
</div>
	<div class="conOperation">
	    <button id="passButton" type="button" tabindex="18"><span>通过</span></button>
		<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
		<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
	</div>
<div id="historyView"></div>
<div id="congrousFactor"></div>
