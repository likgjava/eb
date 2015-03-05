<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/util/exportFile.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/userSelfList.js"></script>
		<form id="userSearchZone" >
			<div class="conSearch">
				<h4><span><spring:message code="globe.query"/></span></h4>
				<ul>
					<li><label><spring:message code="userForm.usName" />:</label>
						<input type="text" name="usName" value="" class="sysicon autoSearch">
						<input type="hidden" name="usName_op" value="like">
					</li>
					<li><label><spring:message code="employeeForm.name" />:</label>
						  <input type="text" name="emp.name" value="" class="sysicon autoSearch">
						  <input type="hidden" name="emp.name_op" value="like">
					</li>
					<li><label>所属机构:</label>
						 <input type="text" name="emp.company.name" value="" class="sysicon autoSearch"/>
						 <input type="hidden" name="emp.company.name_op" value="like">
					 </li>
					
					<li class="operationBtnDiv">
       				 <button type="button" id="query"><span><spring:message code="globe.query" /></span></button>
     			 	</li>
				</ul>
			</div>
		</form>

<div class="operationBtnDiv r">
	<button id="add"><span>新增</span></button>
	<button id="delete"><span>删除</span></button>
</div>

<table class="frontTableList" id="userList">
		<thead>
			<tr>
				<th class="omission" omiLength="10">帐号</th>
				<th class="omission" omiLength="3">姓名</th>
				<th class="omission" omiLength="10">所属机构</th>
				<th class="center">类型</th>
				<th class="center">状态</th>
				<th class="date">有效期</th>
				<th class="date">创建时间</th>
				<th class="operation">操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
</table>