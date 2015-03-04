
var bidList={};
bidList.rows=null;//列表查询的结果集
	
	//查看详细
	bidList.showDetail=function(name,grid)
	{
		if(!bidList.validation(name,grid))return;
		var id = $('#bidGrid').getRowById($(grid).getSelect())["bidId"];
		$('#bidFormView').empty().loadPage($('#initPath').val()+'/view/es/planform/bid/bidDetail.jsp?bidId='+id);
	}
	//开标
	bidList.openBid=function(name,grid)
	{
		if(!bidList.validationMultiple(name,grid))return;
		var isopen = $(grid).getSelects();
		if(isopen.indexOf("opened")!=-1)   //表示没有开过标
		{
			$.getJSON($('#initPath').val()+'/OpenBidRecordController.do?method=saveOrUpdateOpenBidRecord',{projectId:$("#projectId").val()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
			});	
			checkProjectMenu('menu_openbid');
		}
		else
		{
			alert("已经开标！！！");
		}
	}	

	
	
	//删除
	bidList.remove=function(name,grid){
		if(!bidList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/BidController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//可以多选的列表操作验证
	bidList.validationMultiple=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		//if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	
	//列表操作验证
	bidList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	bidList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#bidGrid').flexOptions({params:option});
		return true;
	}
	
	//加载数据成功之后调用的函数
	bidList.success=function(){
		$("#bidGrid").flexGetColByName({
			'openBRStatus' : function(id,t) 
			{
			    if($(t).text()==0)
			    {
			    	$(t).text('未开标');
			    }
			    if($(t).text()==1)
			    {
			    	$(t).text('开标成功');
			    }
			    if($(t).text()==2)
			    {
			    	$(t).text('开标失败');
			    }
			}
		})
		
		//alert('load complete!');
		//('#bidGrid').selectedRows(['id1', 'id2']);//选中用户已拥有的行	参数ID数组
		//if(bidList.rows==null) bidList.rows=$('#bidGrid').flexOptions()[0].p.rows;//获取列表的查询结果，存储格式[{id1:1, name1:2},{id2:11, name2:22}]
	}
$(document).ready(function(){
});


