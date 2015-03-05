
var dictionaryList={};
dictionaryList.rows=null;//列表查询的结果集
	//新增
	dictionaryList.add=function(name,grid){
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/DictionaryController.do?method=toCreateOrUpdate");
	}   
	
	//新增分组
	dictionaryList.addGroup=function(name,grid){
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/DictionaryTypeController.do?method=toCreateOrUpdate");
	}  
	//修改
	dictionaryList.update=function(name,grid){
		if(!dictionaryList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/DictionaryController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//删除
	dictionaryList.remove=function(name,grid){
		if(!dictionaryList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/DictionaryController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	dictionaryList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	dictionaryList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#dictionaryGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	dictionaryList.success=function(){
		//alert('load complete!');
		//('#dictionaryGrid').selectedRows(['id1', 'id2']);//选中用户已拥有的行	参数ID数组
		//if(dictionaryList.rows==null) dictionaryList.rows=$('#dictionaryGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{id1:1, name1:2},{id2:11, name2:22}]
	}
$(document).ready(function(){
	$('#dicName').autocomplete($('#initPath').val() + '/DictionaryController.do?method=getObjectQuery&queryColumns=objId,dicName', {
		extraParams:{
//			'status':'1',
//			'status_relative':'[and]'
		},//查询条件  例如    {objId:111}
		matchColumn:'dicName',//作为查询显示, 被选中之后匹配的列
		minChars: 1,
		max:10,
		autoFill: true,
		mustMatch: true,
		scrollHeight: 220,
		formatItem: function(data, i, total) {
//			return '<I>'+data.shortSpellName+'</I>';
			return data.dicName;
		},
		formatMatch: function(data, i, total) {
			return data.dicName;
		},
		formatResult: function(data) {
			return data.dicName;
		}
	}).result(function(event,data,formatted){
		if(data){	 
		}
	});   
	
	
	$('#dicTypeGroupName').autocomplete($('#initPath').val() + '/DictionaryController.do?method=getObjectQuery&queryColumns=objId,dicType.groupName', {
		extraParams:{
//		'status':'1',
//		'status_relative':'[and]'
	},//查询条件  例如    {objId:111}
	matchColumn:'dicType.groupName',//作为查询显示, 被选中之后匹配的列
	minChars: 1,
	max:10,
	autoFill: true,
	mustMatch: true,
	scrollHeight: 220,
	formatItem: function(data, i, total) {
		//			return '<I>'+data.shortSpellName+'</I>';
		return data['dicType.groupName'];
	},
	formatMatch: function(data, i, total) {
		return data['dicType.groupName'];
	},
	formatResult: function(data) {
		return data['dicType.groupName'];
	}
	}).result(function(event,data,formatted){
		if(data){	 
		}
	});  
	
	
	$('#dicMemo').autocomplete($('#initPath').val() + '/DictionaryController.do?method=getObjectQuery&queryColumns=objId,dicMemo', {
		extraParams:{
//		'status':'1',
//		'status_relative':'[and]'
	},//查询条件  例如    {objId:111}
	matchColumn:'dicMemo',//作为查询显示, 被选中之后匹配的列
	minChars: 1,
	max:10,
	autoFill: true,
	mustMatch: true,
	scrollHeight: 220,
	formatItem: function(data, i, total) {
		//			return '<I>'+data.shortSpellName+'</I>';
		return data.dicMemo;
	},
	formatMatch: function(data, i, total) {
		return data.dicMemo;
	},
	formatResult: function(data) {
		return data.dicMemo;
	}
	}).result(function(event,data,formatted){
		if(data){	 
		}
	});   
	
	

});

