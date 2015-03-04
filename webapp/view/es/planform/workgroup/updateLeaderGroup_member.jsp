<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/workgroup/updateLeaderGroup_member.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/common/planTemplateTask.js"></script>
<div class="partContainers">
<form id="workgMemberFormId" method="post">  
	<div class="formLayout form2Pa">
	    	<ul>
	    		<li>
					<label  class="short">姓名： </label>
					<input type="text" id="workgmName" name="workgmName" value="${workgMember.workgmName}" class="required"/>
					<span class="eleRequired">*</span>
				</li>
				<li >
					<label for="workgmType" class="short"><spring:message code="workgMemberForm.workgmType"/></label>
					<select name="workgmType" id="workgmType" class="required" style="width: 153px;height: 23px">
						<c:forEach items="${workGroupMemberTypeList}" var="memberType">
							<c:if test="${memberType.code == workgMember.workgmType}">
								<option value="${memberType.code}" selected="selected">${memberType.message}</option>
							</c:if>
							<c:if test="${memberType.code != workgMember.workgmType}">
								<option value="${memberType.code}">${memberType.message}</option>
							</c:if>
						</c:forEach>
					</select>	 	  
				</li>
	    		<li>
					<label  class="short">工作单位： </label>
					<input type="text" id="workgmCompany" name="workgmCompany" class="required" value="${workgMember.workgmCompany}"/>
					<span class="eleRequired">*</span>
				</li>
	    	</ul>
	<div class="conOperation">
		<input type="hidden" name="subProject.objId" value="${workgMember.subProject.objId}"/>
		<input type="hidden" name="workGroup.objId" value="${workgMember.workGroup.objId}"/>
		<input type="hidden" name="objId" value="${workgMember.objId}"/>
		<button id="saveBtnId" type="button" ><span><spring:message code="globe.save"/></span></button>
		<button id="closeBtn" type="button" ><span>关闭</span></button>
	</div>
	</div>    
 </form>
</div>    
<script>
var workgMemberFormId = {};

$(document).ready(function(){
	$("#workgMemberFormId").validate({
		
	});	
});
</script>