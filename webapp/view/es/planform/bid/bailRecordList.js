
var bailRecordList={};
bailRecordList.rows=null;//列表查询的结果集
	//新增
	bailRecordList.add=function(name,grid){
		//跳转到新增页面
		$('#conBody').loadPage($('#initPath').val()+"/BailRecordController.do?method=toCreateOrUpdate");
	}   
	//修改
	bailRecordList.update=function(name,grid){
		if(!bailRecordList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/BailRecordController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//删除
	bailRecordList.remove=function(name,grid){
		if(!bailRecordList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/BailRecordController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	bailRecordList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择保证金记录表'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个保证金记录表'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	bailRecordList.before=function(){
		var option={
			//'renderTime':$("#renderTime").val(),
			//'renderTime2':$("#renderTime2").val(),
			//'returnedTime':$("#returnedTime").val(),
			//'returnedTime2':$("#returnedTime2").val(),
		}
		$('#bailRecordGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	bailRecordList.success=function(){
		$("#bailRecordGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/BailRecordController.do?method=toCreateOrUpdate&objId='+rowId);
				}).appendTo(obj);
			},
			'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					if(window.confirm('确定要删除吗?')){
						$.getJSON($('#initPath').val()+'/BailRecordController.do?method=remove',{objId:rowId},function(json){
							if(json.result)alert(json.result);if(json.failure)return;
							$("#bailRecordGrid").reload();//刷新
						});
					}
				}).appendTo(obj);
			}
	});

	
	
	
	}
$(document).ready(function(){
	$("#renderTime").epsDatepicker();
	$("#returnedTime").epsDatepicker();
	$("#renderTime2").epsDatepicker();
	$("#returnedTime2").epsDatepicker();
});

