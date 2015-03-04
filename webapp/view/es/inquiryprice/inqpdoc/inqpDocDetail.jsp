<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpdoc/inqpDocDetail.js"></script>
<style type="text/css">
<!--
a.filedown{color: blue;text-decoration:underline}
--> 
</style>
<div class="partContainers">
<form id="purchaseDocForm" method="post">
<div class="formLayout" >        
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="projectId" id="projectId" value="<c:out value="${projectId}"/>"/>
		<input type="hidden" name="attachmentId" id="attachmentId" value="<c:out value="${purchaseDoc.attachRelaId}"/>"/>
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
							<span id="filePrice"><fmt:formatNumber value="${purchaseDoc.filePrice }" type="currency"></fmt:formatNumber></span>
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
<div id="congrousFactor"></div>