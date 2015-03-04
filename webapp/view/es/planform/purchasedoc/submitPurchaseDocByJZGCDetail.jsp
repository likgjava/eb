<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/submitPurchaseDocByJZGCDetail.js"></script>
<style type="text/css">
<!--
a.filedown{color: blue;text-decoration:underline}
--> 
</style>
<div class="partContainers">
<input type="hidden" id="purchaseDocId" value="${purchaseDoc.objId}">
<form id="purchaseDocForm" method="post">
<div class="formLayout" >        
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
     	<input type="hidden" name="projectId" id="projectId" value="${projectId}"/>
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
						<label class="short" for="auditStatus">待招标单位确认：</label>
 							<span id="auditStatus">${purchaseDoc.auditStatusCN}</span>
					</li>
					<!-- 
					<li>
						<label class="short">文件下载：</label>
						<c:if test="${message!=''}">
							<span style="color: red">${message}</span>
						</c:if>
						<c:if test="${message==''||message == null ||message == undefined}">
							<div class="hidden" id="downAddr"><%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadPurFile&purchaseDocId=${purchaseDoc.objId}</div>
								<a class="sysicon siDownBtn" href="<%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadPurFile&purchaseDocId=${purchaseDoc.objId}"  id="downLoad" title="点击下载【${attrName}】"><span>${attrName}</span></a>
						</c:if>
					</li>-->
					<li>
						<label class="short">招标文件下载：</label>
						<c:if test="${message!=''}">
							<span style="color: red">${message}</span>
						</c:if>
						<c:if test="${message==''||message == null ||message == undefined}">
							<div class="hidden" id="downAddr2"><%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadDocFile&purchaseDocId=${purchaseDoc.objId}</div>
								<c:if test="${purchaseDoc!=null}">
								<a class="sysicon siDownBtn" href="<%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadDocFile&purchaseDocId=${purchaseDoc.objId}"  id="downLoad2" title="点击下载【${attrName2}】"><span>${attrName2}</span></a>
								</c:if>
						</c:if>
						<c:if test="${project.tenderType == 04}">
							<a class="" href="#" onclick="viewJZGCPurchaseDoc();"><span>查看</span></a>
						</c:if>
					</li>
			</ul>
			</div>
</form>
		<table class="tableList">
			<thead>
				<tr>
					<th width="15%">
						包组编号
					</th>
					<th width="60%">
						包组名称
					</th>
					<th width="15%">
						 分册文件下载
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${subProjectList }" var="subPrj">
				<tr>
					<td align="center" width="15%">
					${subPrj.projCode }
					</td>
					<td align="right" width="30%">${subPrj.projName }</td>
					<td align="center" width="55%">
							<c:forEach items="${purchaseDocAttList}" var="purchaseDocAtt">
						<c:if test="${purchaseDocAtt.packId==subPrj.objId&&purchaseDocAtt.fileType==00}">
						<button  type="button" tabindex="17" onclick="downFile('${purchaseDocAtt.attRaId}')"><span>商务标文件下载</span></button>
						</c:if>
						</c:forEach>
						<c:forEach items="${purchaseDocAttList}" var="purchaseDocAtt">
						<c:if test="${purchaseDocAtt.packId==subPrj.objId&&purchaseDocAtt.fileType==01}">
						<button  type="button" tabindex="17" onclick="downFile('${purchaseDocAtt.attRaId}')"><span>技术标文件下载</span></button>
						</c:if>
					</c:forEach>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
</div>



 		