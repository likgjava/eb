
var dosBuyRecordList={};
dosBuyRecordList.rows=null;//列表查询的结果集
	//新增
	dosBuyRecordList.add=function(name,grid){
		//跳转到修改页面
		var fileId = $("#fileId").val();
		var projectId = $("#projectId").val();
		$('#dosBuyRecordList').empty().loadPage($('#initPath').val()+"/InqpDocBuyRecordController.do?method=toDosBuyRecordForm&projectId="+projectId+"&isComplete=true&fileId="+fileId);
	}   
	//修改
	dosBuyRecordList.update=function(name,grid){
		if(!dosBuyRecordList.validation(name,grid))return;
		//跳转到修改页面
		var fileId = $("#fileId").val();
		var projectId = $("#projectId").val();
		$('#dosBuyRecordList').empty().loadPage($('#initPath').val()+'/InqpDocBuyRecordController.do?method=toUpdateDosBuyRecordForm&projectId='+projectId+'&fileId='+fileId+'&objId='+$(grid).getSelect());
	}   
	//删除
	dosBuyRecordList.remove=function(name,grid){
		if(!dosBuyRecordList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	dosBuyRecordList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	dosBuyRecordList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#dosBuyRecordGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	dosBuyRecordList.success=function(){
		$("#dosBuyRecordGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
				btn.click(function(){
					var fileId = $("#fileId").val();
					var projectId = $("#projectId").val();
					$('#dosBuyRecordList').empty().loadPage($('#initPath').val()+'/InqpDocBuyRecordController.do?method=toUpdateDosBuyRecordDetail&projectId='+projectId+'&fileId='+fileId+'&objId='+rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
				btn.click(function(){
					var fileId = $("#fileId").val();
					var projectId = $("#projectId").val();
					$('#dosBuyRecordList').empty().loadPage($('#initPath').val()+'/InqpDocBuyRecordController.do?method=toUpdateDosBuyRecordForm&projectId='+projectId+'&fileId='+fileId+'&objId='+rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定删除吗?')){
						$.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=remove',{objId:rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$('#dosBuyRecordGrid').reload();//刷新
						});
					}
				}).appendTo(obj);
			}
		});
	}
	
	

	
	
	
$(document).ready(function(){
	
	
});

