var channelDataList={};
channelDataList.rows=null;//列表查询的结果集

	// 新增
	channelDataList.add=function(name,grid){
		
		// 跳转到新增页面
		$('#conBody').loadPage($('#initPath').val()+"/ChannelDataController.do?method=toCreateOrUpdate");
	}   
	
	// 修改
	channelDataList.update=function(name,grid){
		if(!channelDataList.validation(name,grid))return;
		
		// 跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/ChannelDataController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	
	// 删除
	channelDataList.remove=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择栏目数据'+name);return false;}//是否选中
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/ChannelDataController.do?method=remove',{objId:$(grid).getSelectArray()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	
	// 列表操作验证
	channelDataList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择栏目数据'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个栏目数据'+name);return false;}//是否只选中一个
		return true;
	}
	
	// 查询条件过滤
	channelDataList.before=function(){
		var option={}
		$('#channelDataGrid').flexOptions({params:option});
		return true;
	}
	
$(document).ready(function(){
});

