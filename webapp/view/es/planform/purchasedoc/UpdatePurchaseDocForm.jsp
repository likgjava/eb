<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style>
<!--
a.abtn {text-decoration:underline}
-->
</style>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/UpdatePurchaseDocForm.js"></script>
<div class="functionBtnDiv">
	<button id="useToolCreatePurchaseDoc" type="button" tabindex="17"><span>使用工具制作招标文件</span></button>
	<a class="abtn" id="downUETool" href="#" onclick="javascript:window.open('<%=request.getContextPath()%>/toolFile/UESetup0916.zip', '_self');">(下载制作工具)</a>
</div>
<input type="hidden" name="purchaseDocobjId" id="purchaseDocobjId" value="<c:out value="${purchaseDoc.objId}"/>"/>
<form id="purchaseDocForm" name="purchaseDocForm" method="post"  enctype="multipart/form-data">
<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
	<div class="formLayout">        
		<input type="hidden" name="objId" id="objId" value="<c:out value="${purchaseDoc.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${purchaseDoc.project.objId}">
		<input type="hidden" name="fromType" id="fromType" value="${param.fromType}">	
		<input type="hidden" name="attrId" id="attrId" value="${attrId}"/>
		<input type="hidden" name="attrName" id="attrName" value="${attrName}"/>
		<input type="hidden" id="projectProjCode" value="<c:out value="${purchaseDoc.project.projCode}"/>"/>
		<h5><span>修改<dm:out value="local__PURDOC" tenderType="${purchaseDoc.project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></h5>
  		<ul>
  		<%--
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
		--%>
			 <li>
				<label class="short" for="auditStatus"><spring:message code="purchaseDocForm.auditStatus"/>：</label>
 				<span id="auditStatus">
 				<c:choose><c:when test="${purchaseDoc.auditStatusCN==null}">待提交</c:when>
 				<c:otherwise>${purchaseDoc.auditStatusCN}</c:otherwise>
 				</c:choose>
 				</span>
			 </li>
			 <li>
				<label class="short"for="filePrice"><spring:message code="purchaseDocForm.filePrice"/>：</label>
				<input type="text" name="filePrice" id="filePrice" 
							class="required"
					  value="${filePrice }"/>
					<span class="eleRequired">*</span>
			</li>
		    <!-- 
			<li>
				<label class="short">已经上传的文件：</label>
		     	<div class="hidden" id="downAddr"><%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadPurFile&purchaseDocId=${purchaseDoc.objId}</div>
				<a class="sysicon siDownBtn" href="<%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadPurFile&purchaseDocId=${purchaseDoc.objId}"  id="downLoad" title="点击下载【${attrName}】"><span>${attrName}</span></a>
			</li> -->
			 <li>
				<label class="short" for="attachRelaId"><dm:out value="local__PURDOC" tenderType="${purchaseDoc.project.tenderType}" ebuyMethod="${purchaseDoc.project.ebuyMethod}">招标文件</dm:out>：</label>
				<input type="hidden" value="" id="attachRelaId" name="attachRelaId" value="${purchaseDoc.attachRelaId}"/>
				<input type="hidden" value="00" id="confirmStatus" name="confirmStatus"/>
				<input type="file" name="attachFile" id="attachFile"/>
			</li>
			<c:if test="${purchaseDoc.useStatus!='00'}">
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
			</c:if>
	</ul>
	</div>
</form> 
		<div class="conOperation">
			<button id="refreshPur" type="button" tabindex="18"><span>刷新</span></button>
			<button id="purchaseDocSubmit"  tabindex="18"><span>提交</span></button>
			<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
		</div>
	<div id="historyView"></div>
<div id="congrousFactor">

</div>