<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/purchaseDocDetail.js"></script>
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
     		<h5>
     		<span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span>
     		<%--
     		<span style="text-align: left;"><a href="#" onclick="purchaseDocDetail.delFile();">删除文件</a></span>
     		--%>
     		</h5>
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
								<a class="sysicon siDownBtn" href="<%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadDocFile&purchaseDocId=${purchaseDoc.objId}"  id="downLoad2" title="点击下载【${attrName2}】"><span>ooooo${attrName2}</span></a>
								</c:if>
						</c:if>
						
						<c:if test="${!empty attList && fn:length(attList) > 0}">
						qqqqqqq
							<c:forEach items="${attList}" var="att" varStatus="status">
								<a href="javascript:void(0);" name="downFile" id="${att.objId}" title="点击下载【${att.viewName}】"><span>${att.viewName}</span></a>
							</c:forEach>
						</c:if>
						<c:if test="${project.tenderType == 04}">
							<a class="" href="#" onclick="viewJZGCPurchaseDoc();"><span>查看</span></a>
						</c:if>
					</li>
			</ul>
			</div>
			
</form>
</div>
<!-- 招标文件下载记录 -->
<div id="congrousFactor" style="float: right; width: 100%;"></div>
<div id="downRecordListDiv" style="float: left; width: 100%;">
	<table class="tableList" id="downRecordList">
		<caption>招标文件下载记录</caption>
		<thead>
			<tr>
				<th>下载单位</th>
				<th>下载人</th>
				<th>下载日期</th>
				<th>下载IP</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
<div id="congrousFactor" style="float: right; width: 100%;">
</div>





 		