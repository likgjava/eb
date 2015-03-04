	var groupMemberList={};
	groupMemberList.rows=null;//列表查询的结果集
	 
	//修改
	groupMemberList.update=function(name,grid){
		if(!groupMemberList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/projrule/groupMemberForm.jsp?objId='+$(grid).getSelect());
	}   
	//删除
	groupMemberList.remove=function(name,grid){
		if(!groupMemberList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/GroupMemberController.do?method=removeGroupMember',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//返回项目列表界面
	groupMemberList.returnback=function(name,grid){
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/project/projectList.jsp');
	}
	//查看详情
	groupMemberList.showDetail=function(name,grid)
	{
		if(!groupMemberList.validation(name,grid))return;
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/projrule/groupMemberDetail.jsp?objId='+$(grid).getSelect());
	}
	
	//列表操作验证
	groupMemberList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	groupMemberList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#groupMemberGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	groupMemberList.success=function(){
		//alert('load complete!');
		//('#groupMemberGrid').selectedRows(['id1', 'id2']);//选中用户已拥有的行	参数ID数组
		//if(groupMemberList.rows==null) groupMemberList.rows=$('#groupMemberGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{id1:1, name1:2},{id2:11, name2:22}]
	}
	$(document).ready(function(){

	});

