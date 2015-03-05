
var orgnizationRuleList={};
orgnizationRuleList.rows=null;//列表查询的结果集
	//新增
	orgnizationRuleList.add=function(name,grid){
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/OrganizationRuleController.do?method=toCreateOrUpdate");
	}   
	
	//删除
	orgnizationRuleList.remove=function(name,grid){
		if(!orgnizationRuleList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/OrganizationRuleController.do?method=remove',{objId:$(grid).getSelects()},function(json){
				if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	orgnizationRuleList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('还没选择要删除的项');return false;}//是否选中
		//if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	orgnizationRuleList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#orgnizationRuleGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	orgnizationRuleList.success=function(){
		
		//alert('load complete!');
		//('#orgnizationRuleGrid').selectedRows(['id1', 'id2']);//选中用户已拥有的行	参数ID数组
		//if(orgnizationRuleList.rows==null) orgnizationRuleList.rows=$('#orgnizationRuleGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{id1:1, name1:2},{id2:11, name2:22}]
	}
$(document).ready(function(){
	//事件代码
});

