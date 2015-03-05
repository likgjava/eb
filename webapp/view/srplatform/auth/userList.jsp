<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/util/exportFile.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/userList.js"></script>
  		<div class="epsContentMenu">
			 <ul>
			    <li id="userEdit">
			      <a href="#" class="modify"><span></span>修改</a>
			    </li>
			    <li id="userAuth">
			      <a href="#" class="authorize"><span></span>授权</a>
			    </li>
			    <li id="userresetPassword">
			      <a href="#" class="redo"><span></span>重置密码</a>
			    </li>
			    <li id="userDel">
			      <a href="#" class="del"><span></span>删除</a>
			    </li>
			 </ul>
		  </div>
		<form id="userSearchZone" >
			<div class="conSearch">
				<h4><span><spring:message code="globe.query"/></span></h4>
				<ul>
					<li><label><spring:message code="userForm.usName" />：</label>
						<input type="text" name="usName" value="" class="sysicon autoSearch">
						<input type="hidden" name="usName_op" value="like">
					</li>
					<li><label><spring:message code="employeeForm.name" />：</label>
						  <input type="text" name="emp.name" value="" class="sysicon autoSearch">
						  <input type="hidden" name="emp.name_op" value="like">
					</li>
					<li><label>所属机构：</label>
						 <input type="text" name="emp.company.name" value="" class="sysicon autoSearch"/>
						 <input type="hidden" name="emp.company.name_op" value="like">
					 </li>
					<li><label>所属部门：</label>
						 <input type="text" name="emp.department.name" value="" class="sysicon autoSearch"/>
						 <input type="hidden" name="emp.department.name_op" value="like">
					 </li>
					<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
				</ul>
			</div>
		</form>
		<flex:flexgrid  showTableToggleBtn="true"  height="277"
			id="userGrid" url="UserController.do?method=list" queryColumns="emp.name,emp.company.name,usName,usrIsAdmin,usrIsLocked,usrPeriodOfValidity,createTime"  
				searchZone="userSearchZone" rp="10" title="用户列表"    onKeyRight="userList.keyRight"
				onSubmit="userList.before" onSuccess="userList.success" checkbox="true" >
			<flex:flexCol name="usName" display="帐号" width="100" sortable="true" align="left"></flex:flexCol>
			<flex:flexCol name="emp.name" display="姓名" sortable="false" align="left" width="150"></flex:flexCol>
			<flex:flexCol name="emp.company.name" display="所属机构" width="150" align="left"></flex:flexCol>
			<flex:flexCol name="emp.department.name" display="所属部门" width="100" align="left"></flex:flexCol>
			<flex:flexCol name="usrIsAdmin" display="类型" sortable="true" align="left" width="80"></flex:flexCol>
			<flex:flexCol name="usrIsLocked" display="状态" sortable="true" align="center" width="50"></flex:flexCol>
			<flex:flexCol name="usrPeriodOfValidity" display="有效期" sortable="true" width="120" align="center"></flex:flexCol>
			<flex:flexCol name="createTime" display="创建时间" sortable="true" width="120" align="center"></flex:flexCol>
		</flex:flexgrid>

