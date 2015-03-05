<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>

<script type="text/javascript">
var role_to_user={};  //定义文件全局变量 处理方法名重复问题
role_to_user.oTable;	

role_to_user.getList = function(){
	if(role_to_user.oTable){
		
	}else{
		//加载列表
		role_to_user.oTable=$('#UserList').dataTable( {   
			'params':{"roleId":$("#roleId").val()},
			'singleSelect':false,	
			'checkbox':true,		
			'queryColumns':'usName,emp.name,emp.mobile',
			'fnDrawCallback' : function(oSettings) {
				$.each(oSettings.appendData,function(index , obj){
					$("input[type=checkbox][value="+obj+"]").attr("checked",true)
					$("tr[objid="+obj+"]").addClass("odd").addClass("row_selected");
				})
			},
			'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
				return nRow;
			},
			"sAjaxSource": $('#initPath').val()+"/UserController.do?method=getListForAuthRole"
		});
	}
}

//授权
role_to_user.authRole = function(){
	if(confirm("确定授权？")){
		var userIdsRm = [];
		var userIdsAd = [];
		$.each($("#UserList tbody").find("input[name=checkAll]"),function(index , obj){
			if($(obj).attr("checked")){
				userIdsAd.push( $(obj).val() );
			}else{
				userIdsRm.push( $(obj).val() );
			}
		})
		$.getJSON($("#initPath").val()+"/UserController.do?method=updateUserRoles",{ "userIdsAd" : userIdsAd , "userIdsRm":userIdsRm ,"roleId":$("#roleId").val()},function(json){
			if(json.success){
				role_to_user.close();
			}
		})
	}
}

role_to_user.close = function(){
	$('#close').closest('.epsDialog').find('.epsDialogClose').trigger('click');
}



$(document).ready(function(){
	role_to_user.getList();
});
</script>

<input type="hidden" id="roleId" name="roleId" value="${param.roleId}">
<table class="frontTableList" id="UserList">
      <thead>
        <tr>
          <th class="left">用户名称</th>
          <th class="left">联系人</th>
          <th class="center">电话</th>
        </tr>
      </thead>
      <tbody>
      </tbody>
</table>

<div class="conOperation">
				<button onclick="role_to_user.authRole();"><span>确定授权</span></button>
				<button id="close" onclick="role_to_user.close();"><span>关闭</span></button>
</div>