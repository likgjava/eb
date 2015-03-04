
var contractClauseList={};
contractClauseList.rows=null;//列表查询的结果集
	//新增
	contractClauseList.add=function(name,grid){
		//跳转到新增页面
		$('#conBody').loadPage($('#initPath').val()+"/ContractClauseController.do?method=toCreateOrUpdate");
	}   
	//修改
	contractClauseList.update=function(name,grid){
		if(!contractClauseList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/ContractClauseController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//删除
	contractClauseList.remove=function(name,grid){
		if(!contractClauseList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/ContractClauseController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	contractClauseList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	contractClauseList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#contractClauseGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	contractClauseList.success=function(){
		//alert('load complete!');
		//('#contractClauseGrid').selectedRows(['id1', 'id2']);//选中用户已拥有的行	参数ID数组
		//if(contractClauseList.rows==null) contractClauseList.rows=$('#contractClauseGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{id1:1, name1:2},{id2:11, name2:22}]
	}
$(document).ready(function(){
});

