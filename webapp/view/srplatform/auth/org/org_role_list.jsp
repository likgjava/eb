<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/org/org_role_list.js"></script>
  
		<form id="userSearchZone" >
			<div class="conSearch">
				<h4><span><spring:message code="globe.query"/></span></h4>
				<ul>
					<li><label>公司名称：</label>
						<input type="text" name="company.name" value="" />
						<input type="hidden" name="company.name_op" value="like" />
					</li>
					<li class="operationBtnDiv">
				      <button type="submit"><span><spring:message code="globe.search"/></span></button>
				    </li>
				</ul>
			</div>
		</form>
		<flex:flexgrid  showTableToggleBtn="true"   
			id="organizationGrid" url="OrgInfoController.do?method=list&company.status=1&company.type=1&company.auditStatus=2&useStatus=01&order=company.createTime&order_flag=true" queryColumns="objId,company.objId,buyerId,supplierId,supervisionId,agencyId"  
				searchZone="userSearchZone" rp="10" title="机构列表"   
				onSubmit="organizationGridList.before" onSuccess="organizationGridList.success" checkbox="false">
			<flex:flexCol name="company.name" display="公司名称" sortable="true" width="150"></flex:flexCol>
			<flex:flexCol name="company.objId" display="公司角色" sortable="true" width="150"></flex:flexCol>
			<flex:flexCol name="company.contact" display="联系人" width="150"></flex:flexCol>
			<flex:flexCol name="company.tel" display="联系电话" width="150"></flex:flexCol>
			<flex:flexCol name="company.createTime" display="创建时间" sortable="true" align="center" width="150" format="date"></flex:flexCol>
		</flex:flexgrid>