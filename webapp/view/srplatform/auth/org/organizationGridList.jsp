<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/org/organizationGridList.js"></script>
	
	<input type="hidden" id="currentCompany" name="currentCompany" value="${currentUser.emp.company.objId}"/>
	
		<c:if test="${currentUser.usrIsAdmin!=50}">

		
		<br/>
		<!-- 操作 -->
		<div class="formTips attention">
		
			列表结构不够清晰？您可以
	  		<span class="contentTools">
				<button id="chooseModel" type="button" class="sysicon siApp_side_tree"><span>切换树形显示</span></button>
			</span>
		</div>
		
		</c:if>
  		<div class="epsContentMenu" >
		  <ul>
		  </ul>
		</div>
		<form id="userSearchZone" >
			<div class="conSearch">
				<h4><span><spring:message code="globe.query"/></span></h4>
				<ul>
					<li><label><spring:message code="organizationForm.name" />:</label>
						<input type="text" name="name" value="" />
						<input type="hidden" name="name_op" value="like" />
					</li>
					<li><label>类型:</label>
						<select  name="type"  >
		          			<option value="1">公司</option>
		          			<option value="2">部门</option>
		          		</select>
						 <input type="hidden" name="type_op" value="like" />
					 </li>
					<li class="operationBtnDiv">
				      <button type="submit"><span><spring:message code="globe.search"/></span></button>
				    </li>
				</ul>
			</div>
		</form>
		<flex:flexgrid  showTableToggleBtn="true"  onKeyRight="organizationGridList.keyRight" 
			id="organizationGrid" url="OrganizationController.do?method=list&status=1&order=createTime&order_flag=true" queryColumns="name,shortName,type,parent.shortName,parent.objId,createTime"  
				searchZone="userSearchZone" rp="10" title="机构列表"   
				onSubmit="organizationGridList.before" onSuccess="organizationGridList.success" checkbox="true">
			<flex:flexCol name="name" display="名称" sortable="true" width="150"></flex:flexCol>
			<flex:flexCol name="shortName" display="简称" sortable="true" align="left" width="100"></flex:flexCol>
			<flex:flexCol name="type" display="类型" sortable="true" align="left" width="30"></flex:flexCol>
			<flex:flexCol name="contact" display="联系人" width="150"></flex:flexCol>
			<flex:flexCol name="tel" display="联系电话" width="150"></flex:flexCol>
			<flex:flexCol name="parent.shortName" display="上级机构简称" sortable="true" align="left" width="100"></flex:flexCol>
			<flex:flexCol name="createTime" display="创建时间" sortable="true" align="left" width="150"></flex:flexCol>
			<c:if test="${currentUser.usrIsAdmin==50}">
			<flex:flexBtn name="新增" bclass="add" onpress="organizationGridList.add"></flex:flexBtn>
			</c:if>
			<flex:flexBtn name="新增下级公司" bclass="add_company" onpress="organizationGridList.addChildCom"></flex:flexBtn>
			<c:if test="${currentUser.usrIsAdmin==50}">
			<flex:flexBtn name="新增下级部门" bclass="add_agency" onpress="organizationGridList.addChildDep"></flex:flexBtn>
			</c:if>
			<flex:flexBtn name="修改" bclass="modify" onpress="organizationGridList.modify"></flex:flexBtn>
			<flex:flexBtn name="变更上级机构" bclass="setting" onpress="organizationGridList.changeParent"></flex:flexBtn>
			<flex:flexBtn name="删除" bclass="delete" onpress="organizationGridList.remove"></flex:flexBtn>	
		</flex:flexgrid>

