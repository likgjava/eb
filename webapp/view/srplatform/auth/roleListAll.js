var roleListAll={};
roleListAll.rows=null;//列表查询的结果集
	
	//分配资源
	roleListAll.saveResource=function(name,grid){
		if(!roleListAll.validation(name,grid))return;
		roleList.roleId = $(grid).getSelect();
		$('#roleListAllId').loadPage($('#initPath').val()+"/RoleController.do?method=roleAuthorizeResource");
	}

	//新增
	roleListAll.add=function(name,grid){
		//跳转到新增页面
		$('#roleListAllId').loadPage($('#initPath').val()+"/RoleController.do?method=toCreateOrUpdate");
	}   
	
	//修改
	roleListAll.update=function(name,grid){
		if(!roleListAll.validation(name,grid))return;
		//跳转到修改页面
		$('#roleListAllId').loadPage($('#initPath').val()+'/RoleController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	
	//删除
	roleListAll.remove=function(name,grid){
		if(!roleListAll.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/RoleController.do?method=deleteRole',{'roleId':$(grid).getSelect()},function(json){
				//if(json.result)alert(json.result);
				if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	
	//列表操作验证
	roleListAll.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择角色'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个角色'+name);return false;}//是否只选中一个
		return true;
	}
	
	//查询条件过滤
	roleListAll.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#roleGrid').flexOptions({params:option});
		return true;
	}	

$(document).ready(function(){
	 conbodyHeight();//改变浏览器大小时改变高度
	 $(window).wresize(conbodyHeight);	   
});