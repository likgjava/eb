<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpdoc/inqpDocProcurement.js"></script>
<input type="hidden" id="projectId"   name="projectId"  value="${purchaseDoc.project.objId}">
<input type="hidden" id="purchaseDocId" name="purchaseDocId" value="${purchaseDoc.objId}">
<input type="hidden" name="auditStatus" id="auditStatus" value="Y"/>
<input type="hidden" name="fromType" id="fromType" value="${fromType}"/>
<div class="partContainers">
<form id="purchaseDocForm" method="post">
<div class="formLayout" >        
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
     		<h5><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></h5>
     		<ul>
     		<%--
     		         <li>
     		             <label for="keyWord"><spring:message code="purchaseDocForm.keyWord"/>：</label>
							<span id="keyWord">${purchaseDoc.keyWord }</span>
     		        </li>
     		        <li>
						<label for="content"><spring:message code="purchaseDocForm.content"/>：</label>
							<span id="content">${purchaseDoc.content}</span>
					</li>
			--%>
					<li>
     		             <label for="filePrice"><spring:message code="purchaseDocForm.filePrice"/>：</label>
							<span id="filePrice"><fmt:formatNumber value="${purchaseDoc.filePrice }" pattern="￥#,#00.00#"></fmt:formatNumber></span>
     		        </li>
					 <li>
						<label for="auditStatus"><spring:message code="purchaseDocForm.auditStatus"/>：</label>
 								 <span id="auditStatus">${purchaseDoc.auditStatusCN}</span>
					</li>
					<li>
						<label >询价文件下载：</label>
							<div class="hidden" id="downAddr2"><%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadDocFile&purchaseDocId=${purchaseDoc.objId}</div>
								<c:if test="${purchaseDoc!=null}">
								<a class="sysicon siDownBtn" href="<%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadDocFile&purchaseDocId=${purchaseDoc.objId}"  id="downLoad2" title="点击下载【${attrName2}】"><span>${attrName2}</span></a>
								</c:if>
					</li>
		</ul>
	
</div>

</form>
</div>
<div class="formLayout" style="height: 73px;">        
	<h5><span>审核意见</span></h5>
		<form id="purchaseDocConfigForm" method="post">
     		<ul>
     			<li style="height:50px;">
					<textarea name="opinion" id="opinion" class="maxInput" maxlength="200" style="width: 99%;height: 45px;">同意</textarea>
				</li>
			</ul>
	</form>
</div>
<div class="conOperation">
	<button id="passButton" type="button" tabindex="18"><span>通过</span></button>
	<button id="noPassButton" type="button" tabindex="18"><span>不通过</span></button>
	<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
</div>

<div id="historyView"></div>
<div id="congrousFactor" style="float: right; width: 100%;"/>