<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/auth/roleListDefault.js"></script>
	<form id="defaultRoleSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
				<li><label><spring:message code="roleForm.chName" />:</label>
					<input type="text" name="chName" value=""><input type="hidden" name="chName_op" value="like">
				</li>
				<li class="operationBtnDiv"><button type="submit" id="roleQuery"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>
	<flex:flexgrid checkbox="true"
		id="defaultRoleGrid" url="RoleController.do?method=list&action=defaultRoleList" queryColumns=""  
			searchZone="defaultRoleSearchZone" rp="10" title="角色信息"  
			onSubmit="roleList.before" onSuccess="roleList.success">
				<flex:flexCol name="chName" display="名称" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="enName" display="英文名称" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="status" display="状态" sortable="true" width="50"align="left"></flex:flexCol>
				<flex:flexCol name="memo" display="备注" sortable="true" width="150"align="left"></flex:flexCol>
		<flex:flexBtn name="globe.new" bclass="add" onpress="roleListDefault.add"></flex:flexBtn>	
		<flex:flexBtn name="globe.modify" bclass="modify" onpress="roleListDefault.update"></flex:flexBtn>	
		<flex:flexBtn name="globe.delete" bclass="delete" onpress="roleListDefault.remove"></flex:flexBtn>	
		<flex:flexBtn name="roleForm.distribute" bclass="setting" onpress="roleListDefault.saveResource"></flex:flexBtn>
	</flex:flexgrid>