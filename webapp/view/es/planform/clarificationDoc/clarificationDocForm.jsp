<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/clarificationDoc/clarificationDocForm.js"></script>
<!-- 隐藏数据 因为该页面为公有业页，若不是弹出层，则需要重新跳转 -->
<input type="hidden" name="fromDiv" id="fromDiv" value="<c:out value="${param.fromDiv}"/>"/>
<form id="purchaseDocForm" name="purchaseDocForm" method="post"  enctype="multipart/form-data">
<input type="hidden" name="workFlowTaskId" id="workFlowTaskId"></input>
<input type="hidden" name="auditStatus" id="auditStatus" value=""></input>
<input type="hidden" name="useStatus" id="useStatus" value=""></input>
<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
	<div class="formLayout">
		<input type="hidden" name="bizId" id="biz_Id"></input>
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="projectTaskId" ></input>
		<input type="hidden" name="purFileId" value="${purchasedocId }"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${projectId}">
		<h5><span><dm:out value="local__OpenTendering__clarificationDoc_zh">澄清文件</dm:out></span></h5>
  		<ul>
			<li>
				<label class="short" for="keyWord"><spring:message code="purchaseDocForm.keyWord"/>：</label>
				<input type="text" name="keyWord" id="keyWord" 
							class="required"
					  />
					<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short" for="content"><spring:message code="purchaseDocForm.content"/>：</label>
				<input type="text" name="content" id="content" 
							class="required"
					  />
					<span class="eleRequired">*</span>
			</li>
			<li class="formTextarea" >
			<label class="short" for="memeo"  ><spring:message code="purchaseDocForm.memeo"/>：</label>
			<textarea  class="required" name="memeo" id="memeo" maxlength="200"></textarea>
			<span class="eleRequired">*</span>
			</li>
			<li>
				<label class="short" for="attachRelaId"><dm:out value="local__OpenTendering__clarificationDoc_zh">澄清文件</dm:out>附件：</label>
				<input type="hidden" value="" id="attachRelaId" name="attachRelaId" />
				<input type="hidden" value="00" id="confirmStatus" name="confirmStatus"/>
				<input type="file" name="attachFile" id="attachFile" class="required"  />
					<span class="eleRequired">*</span>
			</li>
		</ul>
		<div class="conOperation">
			<button id="purchaseDocSave" type="button" tabindex="18"><span>保存</span></button>
			<button id="purchaseDocSubmit" type="button" tabindex="18"><span>提交</span></button>
			<button id="purchaseDocReturn" type="button" tabindex="19"><span><spring:message code="globe.return"/></span></button>
		</div>
	</div>
</form>