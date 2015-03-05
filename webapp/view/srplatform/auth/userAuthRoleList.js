var userRole={}
userRole.init=function(){
	$.getJSON($('#initPath').val()+'/UserController.do?method=getUserAuthRoles',{userId:$('#userId').val() },function(json){
		if(json.failure){if(json.result)alert(json.result,{inco:'info'});;return;}
		//var rows=$('#userRoleTable').flexOptions()[0].p.rows;//获取所有结果集
		var rows=$('#userRoleGird').getSelects();//获取所有结果集
		if(json.result.length>0)
			$('#userRoleGird').selectedRows(json.result);//选中用户已拥有的角色
	});
}

userRole.before=function(){//查询条件过滤
	//这个条件在后台已经过滤了
//	var option={};
//	if(PlatForm.user.usrIsAdmin!='2'){
//		option['emp.org.objId']=PlatForm.user.emp.org.objId;
//	}
//	$('#userRoleTable').flexOptions({params:option});
	return true;
}
$(document).ready(function(){
	
	//userRole.init();
	
	$('#saveUserAuthRole').click(function(){
		var roleIds=$('#userRoleGird').getSelects();
		if(roleIds == ''){
			alert('至少选择一个角色进行授权！');
			return ;
		}
		if(window.confirm("是否授予选中的权限?")){	
			$.getJSON($('#initPath').val()+'/UserController.do?method=userAuthRole',{userId:$('#userId').val(), roleIds:roleIds.split(',')},function(json){
				if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
				$('#epsDialogClose').click();
			});
		}	
	});
	
	$('#closeUserAuthRole').click(function(){
		$('#epsDialogClose').click();
	});
	
});
