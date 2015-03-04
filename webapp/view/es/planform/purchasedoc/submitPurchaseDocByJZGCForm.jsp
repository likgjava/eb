<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/purchasedoc/submitPurchaseDocByJZGCForm.js"></script>
<!-- 隐藏数据 因为该页面为公有业页，若不是弹出层，则需要重新跳转 -->
<input type="hidden" name="fromDiv" id="fromDiv" value="<c:out value="${param.fromDiv}"/>"/>
<input type="hidden" id="projectProjCode" value="<c:out value="${project.projCode}"/>"/>
<div class="functionBtnDiv">
	<c:choose>
	<c:when test="${divided==true}">
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
						操作
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
						<button  type="button" tabindex="17" onclick="makeBusiness('${subPrj.projCode}')"><span>制作商务标</span></button>
						<button  type="button" tabindex="17" onclick="makeTechnical('${subPrj.projCode}')"><span>制作技术标</span></button>
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
	</c:when>
	<c:otherwise>
		<table class="tableList">
			<thead>
				<tr>
					<th width="15%">
						项目编号
					</th>
					<th width="60%">
						项目名称
					</th>
					<th width="25%">
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"  width="15%">
					${project.projCode }
					</td>
					<td align="right"  width="60%">${project.projName }</td>
					<td align="center"  width="25%">
						<button  type="button" tabindex="17" onclick="makeBusiness('${project.projCode}')"><span>制作商务标</span></button>
						<button  type="button" tabindex="17" onclick="makeTechnical('${project.projCode}')"><span>制作技术标</span></button>
					</td>
				</tr>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
</div>
<form id="purchaseDocForm" name="purchaseDocForm" method="post"  enctype="multipart/form-data">
<input type="hidden" name="workFlowTaskId" id="workFlowTaskId"></input>
<input type="hidden" name="auditStatus" id="auditStatus" value="Y"></input>
<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
	<div class="formLayout">
		<input type="hidden" name="bizId" id="biz_Id"></input>
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="projectTaskId" ></input>
		<input type="hidden" name="project.objId" id="project.objId" value="${projectId}">
				<h5><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>
		</span></h5>
  		<ul>
  		<%--
			<li>
				<label class="short"for="keyWord">关键字</label>
				<input type="text" name="keyWord" id="keyWord" 
							class="required"
					  value="${purchaseDoc.keyWord }"/>
					<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short"for="content"><spring:message code="purchaseDocForm.content"/>内容</label>
				<input type="text" name="content" id="content" 
							class="required"
					 value="${purchaseDoc.content }" />
					<span class="eleRequired">*</span>
			</li>
		--%>
			<li>
				<label class="short"for="attachRelaId"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></label>
				<input type="hidden" value="" id="attachRelaId" name="attachRelaId" />
				<input type="file" name="attachFile" id="attachFile" class="required"  />
					<span class="eleRequired">*</span>
					
			</li>
			<c:if test="${purchaseDoc!=null}">
					<li>
						<label class="short">文件下载：</label>
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
		<div class="conOperation">
			<button id="purchaseDocSubmit"  tabindex="18"><span>提交</span></button>
			<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
		</div>
	</div>
</form>