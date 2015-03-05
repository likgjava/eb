<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script>
var roleListDefault = {};

roleListDefault.success=function(){
	$("#defaultRoleGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">选择</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#defaultRoleGrid").flexGetRowJsonById(rowId);
				$('[name=role.objId]').val(rowId);
				$('[name=role.chName]').val(json.chName);
				$('#select_role .epsDialogCloseReload').click();
			}).appendTo(obj);
		}
	});
}
roleListDefault.clear = function (){
	$('[name=role.objId]').val('');
	$('[name=role.chName]').val('');
	$('#select_role .epsDialogCloseReload').click();
}
</script>
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
	<flex:flexgrid checkbox="false" id="defaultRoleGrid" url="RoleController.do?method=list&action=defaultRoleList" queryColumns=""  
			searchZone="defaultRoleSearchZone" rp="10" title="角色信息"  
			 onSuccess="roleListDefault.success">
				<flex:flexCol name="chName" display="名称" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="enName" display="英文名称" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="status" display="状态" sortable="true" width="50"align="left"></flex:flexCol>
				<flex:flexCol name="memo" display="备注" sortable="true" width="150"align="left"></flex:flexCol>
				<flex:flexCol name="objId" display="操作" sortable="true" width="30"align="center"></flex:flexCol>
				<flex:flexBtn name="清空" bclass="add" onpress="roleListDefault.clear"></flex:flexBtn>
	</flex:flexgrid>