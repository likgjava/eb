
var purchaseDocList={};
purchaseDocList.rows=null;//列表查询的结果集
	//列表操作验证
	purchaseDocList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	purchaseDocList.before=function(){
		var option={
			//'objId':'objId',
			//'objId_op':'like'
		}
		$('#InqpDocGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	purchaseDocList.success1=function(){
		$("#InqpDocGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/InqpDocController.do?method=toPurchaseDocAuditingForJCJ&objId='+rowId);
				}).appendTo(obj);
			}
		});
	}
$(document).ready(function(){
});

