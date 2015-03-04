
var noticeList={};
noticeList.rows=null;//列表查询的结果集
	//新增
	noticeList.add=function(name,grid){
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+"/NoticeController.do?method=toCreateOrUpdate");
	}   
	//修改
	noticeList.update=function(name,grid){
		if(!noticeList.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').loadPage($('#initPath').val()+'/NoticeController.do?method=toCreateOrUpdate&objId='+$(grid).getSelect());
	}   
	//查看
	noticeList.look=function(name,grid){
		if(!noticeList.validation(name,grid))return;
		$('#conBody').loadPage($('#initPath').val()+'/NoticeController.do?method=toShowView&objId='+$(grid).getSelect());
	}   
	//删除
	noticeList.remove=function(name,grid){
		if(!noticeList.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/NoticeController.do?method=remove',{objId:$(grid).getSelect()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	//列表操作验证
	noticeList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	noticeList.before=function(){
		var option={
		}
		$('#noticeGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	noticeList.success=function(){
		$("#noticeGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').empty().loadPage($('#initPath').val()+'/NoticeController.do?method=toReceipt&fromType=fromDesk&objId='+rowId);
				}).appendTo(obj);
			}
	});
	}
$(document).ready(function(){
	
	
});

