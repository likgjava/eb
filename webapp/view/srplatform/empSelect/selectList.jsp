<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>  
<script src="<%=request.getContextPath()%>/view/srplatform/empSelect/selectList.js"></script>

<input type="hidden" id="_property"  value="<c:out value="${param.property}"/>"/>
<input type="hidden" id="_ID"  value="<c:out value="${param.IDS}"/>"/>
<input type="hidden" id="_NAME"  value="<c:out value="${param.NAMES}"/>"/>
<input type="hidden" id="_checkValues"  value="<c:out value="${param.checkValues}"/>"/>
<input type="hidden" id="_param"  value="<c:out value="${param.params}"/>"/>
<input type="hidden" id="_dialogID"  value="<c:out value="${param.dialogId}"/>"/>

<form id="employeeSearchZone" >
	<div class="conSearch">
		<h4><span><spring:message code="globe.query"/></span></h4>
		<ul>
			<li><label><spring:message code="employeeForm.number" />：</label>
				<input type="text" name="number" value="">
				<input type="hidden" name="number_op" value="like">
			</li>
			<li><label><spring:message code="employeeForm.name" />：</label>
				<input type="text" name="name" value="" >
				<input type="hidden" name="name_op" value="like">
			</li>
			<li><label><spring:message code="employeeForm.company" />：</label>
				<input type="text" name="company.name" value="" >
				<input type="hidden" name="company.name_op" value="like">
			</li>
			<li><label><spring:message code="employeeForm.department" />：</label>
				<input type="text" name="department.name" value="" >
				<input type="hidden" name="department.name_op" value="like">
			</li>
			 <li class="operationBtnDiv">
		      <button type="submit"><span><spring:message code="globe.search"/></span></button>
		    </li>
		</ul>
	</div>
</form>
<flex:flexgrid id="employeeGrid" url="EmployeeController.do?method=list" queryColumns=""  
		searchZone="employeeSearchZone" rp="10" title="员工列表"  
		onSubmit="SelectList.before">
	<flex:flexCol name="number" display="员工编号" width="90"></flex:flexCol>
	<flex:flexCol name="name" display="姓名" sortable="true" align="left" width="100"></flex:flexCol>
	<flex:flexCol name="company.shortName" display="所属公司简称" width="130"></flex:flexCol>
	<flex:flexCol name="department.shortName" display="所属部门简称" sortable="true" align="left" width="130"></flex:flexCol>
	<flex:flexCol name="mobile" display="手机" sortable="true" align="left" width="130"></flex:flexCol>
	<flex:flexBtn name="选定" bclass="add" onpress="SelectList.add"></flex:flexBtn>	
	<flex:flexBtn name="清空" bclass="delete" onpress="SelectList.clear"></flex:flexBtn>	
</flex:flexgrid>

