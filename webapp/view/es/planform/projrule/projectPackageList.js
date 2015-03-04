	var projectPackageList={};
	projectPackageList.rows=null;//列表查询的结果集
	//新增
	projectPackageList.add=function(name,grid){
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/ProjectPackageController.do?method=toCreateOrUpdate");
	}   
	//修改
	projectPackageList.update=function(name,grid){
		if(!projectPackageList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/ProjectPackageController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//删除
	projectPackageList.remove=function(name,grid){
		if(!projectPackageList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/ProjectPackageController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	projectPackageList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	projectPackageList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#projectPackageGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	projectPackageList.success=function(){
		//alert('load complete!');
		//('#projectPackageGrid').selectedRows(['id1', 'id2']);//选中用户已拥有的行	参数ID数组
		//if(projectPackageList.rows==null) projectPackageList.rows=$('#projectPackageGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{id1:1, name1:2},{id2:11, name2:22}]
	}
	$(document).ready(function(){
	});

