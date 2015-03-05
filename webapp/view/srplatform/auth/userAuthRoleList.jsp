<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/userAuthRoleList.js"></script>
	<input type="hidden" name="userId" id="userId" value="<c:out value="${param.userId}"/>"/>
	
	
	<div class="conSearch" >
	<h4><span><spring:message code="globe.query"/></span></h4>
		<form id="roleSearchZone" >
			<ul>
			   <li><lable> <spring:message code="roleForm.chName" />:</lable>
			    <input type="text" name="chName" value="">
				<input type="hidden" name="chName_op" value="like"></li>
				 <li>
				 <lable><spring:message code="roleForm.enName" />:</lable>
	   			    <input type="text" name="enName" value="">
					<input type="hidden" name="enName_op" value="like">
				</li>
				<li class="operationBtnDiv">
			      <button type="submit"><span><spring:message code="globe.search"/></span></button>
			    </li>
			</ul>
		</form>
	</div>
		<flex:flexgrid usepager="false"
			id="userRoleGird" url="RoleController.do?method=list&authUserId=${param.userId}&currentCompanyId=${param.currentCompanyId}"  queryColumns="objId,chName,enName,status,memo" onSuccess="userRole.init" onSubmit="userRole.before"  
				searchZone="roleSearchZone" rp="50" title="角色列表" height="180" checkbox="true">
			<flex:flexCol name="chName" display="CN名称" sortable="true" width="100"align="left"></flex:flexCol>
			<flex:flexCol name="enName" display="EN名称" sortable="true" width="100"align="left"></flex:flexCol>
			<flex:flexCol name="status" display="状态" width="100"></flex:flexCol>
			<flex:flexCol name="memo" display="描述" sortable="true" width="100" ></flex:flexCol>
		</flex:flexgrid>
	   <div class="conOperation">
			<button type="reset" id="saveUserAuthRole"><span><spring:message code="globe.save"/></span></button>
			<button type="reset" id="closeUserAuthRole"><span><spring:message code="globe.close"/></span></button>
	   </div>
