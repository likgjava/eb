
var channelModelItemList={};
channelModelItemList.rows=null;//列表查询的结果集

	// 新增
	channelModelItemList.add=function(name,grid){
		//跳转到新增页面
		$('#conBody').loadPage($('#initPath').val()+"/ChannelModelItemController.do?method=toCreateOrUpdate");
	}   
	
	// 修改
	channelModelItemList.update=function(name,grid){
		if(!channelModelItemList.validation(name,grid))return;
		
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/ChannelModelItemController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	
	// 删除
	channelModelItemList.remove=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择栏目模型明细'+name);return false;}//是否选中
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/ChannelModelItemController.do?method=remove',{objId:$(grid).getSelectArray()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	
	// 列表操作验证
	channelModelItemList.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择栏目模型明细'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个栏目模型明细'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	channelModelItemList.before=function(){
		var option={}
		$('#channelModelItemGrid').flexOptions({params:option});
		return true;
	}
$(document).ready(function(){
});

