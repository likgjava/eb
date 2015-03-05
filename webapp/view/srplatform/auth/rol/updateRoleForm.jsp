<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/rol/updateRoleForm.js"></script>

<input type="hidden" name="resourceId" value="${resourceId }" id="resourceId">

<div class="treePage frameMainSub frameMs12 fullScreen">
	<!-- 组织机构树 -->
	<div class="treeOutside frameMain">
	   	<c:forEach var ="roleCell" items="${roleSet}">
   			${roleCell.chName}资源:
   			<c:set var="resourceIdCell" value=""/>
   			<c:forEach var ="roleResource" items="${roleCell.roleResources}">
   				<c:set var="resourceIdCell" value="${resourceIdCell}${fn:length(resourceIdCell)>0?',':'' }${roleResource.resource.objId}"></c:set>
   			</c:forEach>
   			<a href="javascript:void(0);" name="checkResourceByRole" id="${resourceIdCell}" onclick="roleForm.checkResourceByRole('${resourceIdCell}','select');" >选择</a>
   			<a href="javascript:void(0);" name="checkResourceByRole" id="${resourceIdCell}" onclick="roleForm.checkResourceByRole('${resourceIdCell}','noselect');" >除去</a>
   			
   			<br>
   		</c:forEach>
   		
   		
   		<c:set var="resourceIdMy" value=""/>
   		<c:forEach var ="roleResource" items="${role.roleResources}"><c:set var="resourceIdMy" value="${resourceIdMy}${fn:length(resourceIdMy)>0?',':'' }${roleResource.resource.objId}"></c:set></c:forEach>
   		<textarea id="resourceIdMy" class="hidden" name="resourceIdMy">${resourceIdMy}</textarea>
	
		<div id="roleResourceTreeGrid" style="overflow-x: auto;overflow-y: hidden;" class="treeContentDiv"></div>
	</div>
	<!-- 用户列表 -->
	<div class="treeRight frameSub">
		<form id="roleForm" method="post">
			<input type="hidden" name="objId" id="objId" value="${role.objId}"/>
			<input type="hidden" name="org.objId" id="org.objId" value="">
			<div class="formLayout">
				<h4><span>角色信息</span></h4>
			    <ul>
			    	<li>
				        <label for="input01"><spring:message code="roleForm.chName" />:</label>
				        <input type="text" name="chName" id="chName" class="required" maxlength="50" value="${role.chName}"/>
					    <span class="eleRequired">*</span>
			        </li>
			    	<li>
				        <label for="input01"><spring:message code="roleForm.enName" />:</label>
				        <input type="text" name="enName" id="enName" class="required" maxlength="50" value="${role.enName}"/>
					    <span class="eleRequired">*</span>
			        </li>
			    	<li class="hidden">
				        <label for="input01"><spring:message code="roleForm.status" />:</label>
				        <input type="text" name="status" id="status" class="required" value="1" />
			        </li>
			    	<li class="formTextarea">
				        <label for="input01"><spring:message code="roleForm.memo" />:</label>
				        <textarea id="memo" name="memo" cols="30" rows="4"  tabindex="14" >${role.memo}</textarea>
			       </li>
			    </ul>
			    <div class="conOperation">
					<button type="button" id="roleSave"><span><spring:message code="globe.save"/></span></button>
					<button type="button" name="historyBackBtn" tabindex="20"><span><spring:message code="globe.return"/></span></button>
				</div>
		    </div>
		</form>
	</div>
</div>