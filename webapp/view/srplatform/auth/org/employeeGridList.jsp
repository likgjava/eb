<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/org/employeeGridList.js"></script>
  
  		<div class="epsContentMenu" >
		  <ul>
			<li id="edit">
		      <a href="#" class="modify"><span></span>修改</a>
		    </li>
		    <li id="del">
		      <a href="#" class="del"><span></span>删除</a>
		    </li>
		  </ul>
		</div>
		<form id="employeeSearchZone" >
			<input type="hidden" name="orgId">
			<div class="conSearch">
				<h4><span><spring:message code="globe.query"/></span></h4>
				<ul>
					<li><label><spring:message code="employeeForm.number" />：</label>
						<input type="text" name="number" value=""><input type="hidden" name="number_op" value="like">
					</li>
					<li><label><spring:message code="employeeForm.name" />：</label>
						<input type="text" name="name" value="" class="sysicon autoSearch">
						<input type="hidden" name="name_op" value="like">
					</li>
					<li><label>公司名称：</label>
						<input type="text" name="company.name" value="" />
						<input type="hidden" name="company.name_op" value="like" />
					</li>
					<li><label>部门名称：</label>
						<input type="text" name="department.name" value="" />
						<input type="hidden" name="department.name_op" value="like" />
					</li>
					 <li class="operationBtnDiv">
				      <button ><span><spring:message code="globe.search"/></span></button>
				    </li>
				</ul>
			</div>
		</form>
		<flex:flexgrid  showTableToggleBtn="true"  onKeyRight="employeeGridList.keyRight" height="277"
			id="employeeGrid" url="EmployeeController.do?method=list&order=createTime&order_flag=true" queryColumns="name,number,company.shortName,department.shortName,mobile,email,createTime"  
				searchZone="employeeSearchZone" rp="10" title="员工列表"  
				onSubmit="employeeGridList.before" onSuccess="employeeGridList.success" checkbox="true">
			<flex:flexCol name="number" display="员工编号" width="50"></flex:flexCol>
			<flex:flexCol name="name" display="姓名" sortable="true" align="left" width="100"></flex:flexCol>
			<flex:flexCol name="company.shortName" display="公司简称" width="130"></flex:flexCol>
			<flex:flexCol name="department.shortName" display="部门简称" sortable="true" align="left" width="130"></flex:flexCol>
			<flex:flexCol name="email" display="电子邮箱" sortable="true" align="left" width="130"></flex:flexCol>
			<flex:flexCol name="mobile" display="手机" sortable="true" align="left" width="130"></flex:flexCol>
			<flex:flexBtn name="新增" bclass="add" onpress="employeeGridList.add"></flex:flexBtn>	
			<flex:flexBtn name="修改" bclass="modify" onpress="employeeGridList.modify"></flex:flexBtn>	
			<flex:flexBtn name="删除" bclass="delete" onpress="employeeGridList.remove"></flex:flexBtn>	
		</flex:flexgrid>

