var roleListDefault={};
roleListDefault.rows=null;//列表查询的结果集
	
	//分配资源
	roleListDefault.saveResource=function(name,grid){
		if(!roleListDefault.validation(name,grid))return;
		roleList.roleId = $(grid).getSelect();
		$('#roleListDefaultId').loadPage($('#initPath').val()+"/view/srplatform/auth/roleAuthorizeResource.jsp");
	}

	//新增
	roleListDefault.add=function(name,grid){
		//跳转到修改页面
		$('#roleListDefaultId').loadPage($('#initPath').val()+"/RoleController.do?method=toCreateOrUpdate");
	}   
	
	//修改
	roleListDefault.update=function(name,grid){
		if(!roleListDefault.validation(name,grid))return;
		//跳转到修改页面
		$('#roleListDefaultId').loadPage($('#initPath').val()+'/RoleController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	
	//删除
	roleListDefault.remove=function(name,grid){
		if(!roleListDefault.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/RoleController.do?method=deleteRole',{'roleId':$(grid).getSelect()},function(json){
				//if(json.result)alert(json.result);
				if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	
	//列表操作验证
	roleListDefault.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择角色'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个角色'+name);return false;}//是否只选中一个
		return true;
	}
	
	//查询条件过滤
	roleListDefault.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#roleGrid').flexOptions({params:option});
		return true;
	}
	
	//加载数据成功之后调用的函数
	roleListDefault.success=function(){
		//alert('load complete!');
		//('#roleGrid').selectedRows(['roleListDefaultId', 'roleListDefaultId']);//选中用户已拥有的行	参数ID数组
		//if(roleList.rows==null) roleList.rows=$('#roleGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{roleListDefaultId:1, name1:2},{roleListDefaultId:11, name2:22}]
	}
	

$(document).ready(function(){
});




