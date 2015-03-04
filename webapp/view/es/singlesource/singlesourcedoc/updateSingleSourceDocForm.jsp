<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/singlesource/singlesourcedoc/updateSingleSourceDocForm.js"></script>
<!-- 隐藏数据 因为该页面为公有业页，若不是弹出层，则需要重新跳转 -->
<input type="hidden" name="fromDiv" id="fromDiv" value="<c:out value="${param.fromDiv}"/>"/>
<input type="hidden" name="fromDesk" id="fromDesk" value="<c:out value="${fromDesk}"/>"/>
<form id="purchaseDocForm" name="purchaseDocForm" method="post"  enctype="multipart/form-data">
<input type="hidden" name="isUploadFile" id="isUploadFile" value="false"></input>
	<div class="formLayout">        
		<input type="hidden" name="objId" id="objId" value="<c:out value="${purchaseDoc.objId}"/>"/>
		<input type="hidden" name="taskId" id="taskId" value="<c:out value="${taskId}"/>"/>
		<input type="hidden" name="project.objId" id="project.objId" value="${projectId}">
		<h5><span>修改采购文件</span></h5>
  		<ul>
			<li>
				<label for="keyWord"><spring:message code="purchaseDocForm.keyWord"/>：</label>
				<input type="text" name="keyWord" id="keyWord" value="${purchaseDoc.keyWord}"
							class="required"
					  />
					<span class="eleRequired">*</span>
			</li>
			<li>
				<label for="content"><spring:message code="purchaseDocForm.content"/>：</label>
				<input type="text" name="content" id="content" value="${purchaseDoc.content}"
							class="required"
					  />
					<span class="eleRequired">*</span>
			</li>
			<li>
				<label for="filePrice"><spring:message code="purchaseDocForm.filePrice"/>：</label>
				<input type="text" name="filePrice" id="filePrice" 
							class="required money" maxlength="16"
					  />
					<span class="eleRequired">*</span>
			</li>
			 <li>
				<label for="auditStatus"><spring:message code="purchaseDocForm.auditStatus"/>：</label>
 				<span id="auditStatus">${purchaseDoc.auditStatusCN}</span>
			 </li>
			<li>
				<label for="attachRelaId">采购文件：</label>
				<input type="hidden" value="" id="attachRelaId" name="attachRelaId" value="${purchaseDoc.attachRelaId}"/>
				<input type="hidden" value="00" id="confirmStatus" name="confirmStatus"/>
				<input type="file" name="attachFile" id="attachFile"/>
			</li>
			<li>已经上传的文件：
			    <c:import url="/view/srplatform/auth/attachment.jsp">
          		<c:param name="attachRelaId" value="${purchaseDoc.attachRelaId}"/>
          		<c:param name="isSingle" value="yes"/>
          		<c:param name="isSelect" value="yes"/>
   				 </c:import>
			</li>
		</ul>
		<div class="conOperation">
			<button id="purchaseDocSave" type="submit" tabindex="18"><span><spring:message code="globe.save"/></span></button>
			<button id="historyId" name="historyName" type="button" tabindex="20"><span>操作历史</span></button>
		</div>
	</div>
</form>
	   	    <div id="historyView"></div>
