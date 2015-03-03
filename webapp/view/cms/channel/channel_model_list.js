var channelModelList={};
channelModelList.rows=null;//列表查询的结果集

	// 新增
	channelModelList.add=function(name,grid){
		
		// 跳转到新增页面
		$('#conBody').loadPage($('#initPath').val()+"/ChannelModelController.do?method=toCreateOrUpdate&isCopy=false");
	}   
	
	// 修改
	channelModelList.update=function(name,grid){
		if(!channelModelList.validation(name,grid))return;
		
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/ChannelModelController.do?method=toCreateOrUpdate&isCopy=false&objId='+$(grid).getSelect());
	}   
	
	// 拷贝
	channelModelList.copyModel=function(name,grid){
		if(!channelModelList.validation(name,grid))return;
		
		// 跳转到拷贝页面
		$('#conBody').loadPage($('#initPath').val()+'/ChannelModelController.do?method=toCreateOrUpdate&isCopy=true&objId='+$(grid).getSelect());
	}   
	
	// 删除
	channelModelList.remove=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择栏目模型'+name);return false;}//是否选中
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/ChannelModelController.do?method=remove',{objId:$(grid).getSelectArray()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	
	// 列表操作验证
	channelModelList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择栏目模型'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个栏目模型'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	channelModelList.before=function(){
		var option={'order':'sort'}
		$('#channelModelGrid').flexOptions({params:option});
		return true;
	}
$(document).ready(function(){
});

