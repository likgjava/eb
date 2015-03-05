<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="partContainers">
	<form id="roleForm" method="post">
		<input type="hidden" name="objId" id="objId" value="<c:out value="${param.objId}"/>"/>
		<input type="hidden" name="org.objId" id="org.objId" value="">
		<div class="formLayout">
			<h4><span>角色信息</span></h4>
		    <ul>
		    	<li>
			        <label for="input01"><spring:message code="roleForm.chName" />:</label>
				    <span>${role.chName}</span>
		        </li>
		    	<li>
			        <label for="input01"><spring:message code="roleForm.enName" />:</label>
				    <span>${role.enName}</span>
		        </li>
		    	<li class="formTextarea">
			        <label for="input01"><spring:message code="roleForm.memo" />:</label>
			        <textarea id="memo" name="memo" cols="30" rows="4"  tabindex="14" readonly="readonly" disabled="disabled">${role.memo}</textarea>
		       </li>
		    </ul>
		   <h4><span>权限点</span></h4>
		   <div>
	        	<c:set var="line" value="0"></c:set>
				<table calss="tablelist">
					<tr>
					<c:forEach var="resource" items="${resourceList}" >
						<c:if test="${line == 6}">
							</tr><tr>
							<c:set var="line" value="0"></c:set>
						</c:if>
						<td style="width: auto;"><input type="checkbox" name="check_resource" value="${resource.path}" readonly="readonly" disabled="disabled" <c:forEach var="checkedResource" items="${checkedResourceList}" >
							<c:if test="${checkedResource.resource.objId == resource.objId}">
							checked="checked"
							</c:if>
						</c:forEach> ></input>${resource.name}</td>
						<c:set var="line" value="${line+1}"></c:set>
					</c:forEach>
					</tr>
				</table>
		    </div>
		    <div class="conOperation">
				<button type="button" id="roleSave"><span><spring:message code="globe.save"/></span></button>
				<button name="historyBackBtn" tabindex="20"><span><spring:message code="globe.return"/></span></button>
			</div>
	    </div>
	</form>
</div>