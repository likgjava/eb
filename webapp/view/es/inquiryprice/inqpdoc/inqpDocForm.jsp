<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/inquiryprice/inqpdoc/inqpDocForm.js"></script>
<!-- 隐藏数据 因为该页面为公有业页，若不是弹出层，则需要重新跳转 -->
<input type="hidden" name="fromDiv" id="fromDiv" value="<c:out value="${param.fromDiv}"/>"/>
<div class="functionBtnDiv" style="margin-top: 0px">
	<button id="useToolCreatePurchaseDoc" type="button" tabindex="17"><span>使用工具制作招标文件</span></button>
	<a class="abtn" id="downUETool" href="#" onclick="javascript:window.open('<%=request.getContextPath()%>/toolFile/UESetup0916.zip','_self');">(下载制作工具)</a>
</div>
	<input type="hidden" name="projectProjCode" id="projectProjCode" value="${project.projCode}">
<form id="purchaseDocForm" name="purchaseDocForm" method="post"  enctype="multipart/form-data">
<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
	<div class="formLayout">        
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${projectId}">
		<input type="hidden" name="filePrice" id="filePrice" value="${filePrice}"/>
		<h5><span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></span></h5>
  		<ul>
  		<%--
			<li>
				<label for="keyWord" class="short"><spring:message code="purchaseDocForm.keyWord"/>：</label>
				<input type="text" name="keyWord" id="keyWord" 
							class="required"
					  />
					<span class="eleRequired">*</span>
			</li>
			<li>
				<label for="content" class="short"><spring:message code="purchaseDocForm.content"/>：</label>
				<input type="text" name="content" id="content" 
							class="required"
					  />
					<span class="eleRequired">*</span>
			</li>
		--%>
			<li>
				<label for="attachRelaId" class="short"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>：</label>
				<input type="hidden" value="" id="attachRelaId" name="attachRelaId" />
				<input type="hidden" value="00" id="confirmStatus" name="confirmStatus"/>
				<input type="file" name="attachFile" id="attachFile"  />
			</li>
		</ul>
		<div class="conOperation">
			<button id="refreshPur" type="button" tabindex="18"><span>刷新</span></button>
			<button id="purchaseDocSave" type="button" tabindex="18"><span><spring:message code="globe.submit"/></span></button>
			<button id="purchaseDocReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</div>
</form>
<div id="congrousFactor"></div>