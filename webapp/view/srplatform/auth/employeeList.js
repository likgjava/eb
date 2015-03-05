
var employeeList={};
	//新增
	employeeList.add=function(name,grid){
		//跳转到修改页面
		loadPage_toContent($('#initPath').val()+"/EmployeeController.do?method=toCreateOrUpdate");
	}   
	//修改
	employeeList.update=function(name,grid){
		if(!employeeList.validation(name,grid))return;
		//跳转到修改页面
		loadPage_toContent('EmployeeController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//删除
	employeeList.remove=function(name,grid){
		if(!employeeList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/EmployeeController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	employeeList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	employeeList.before=function(){
		var option={
			'objId':'objId',
			'objId_op':'like'
		}
		$('#employeeGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	employeeList.success=function(){
		alert('load complete!');
		//('#employeeGrid').selectedRows(['id1', 'id2']);//选中用户已拥有的行	参数ID数组
		//var rows=$('#employeeGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{id1:1, name1:2},{id2:11, name2:22}]
	}
$(document).ready(function(){
	//事件代码
});

