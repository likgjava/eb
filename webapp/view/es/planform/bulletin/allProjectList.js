
var allprojectList={};
	allprojectList.modifyItem=function(i){
		$('#conBody').loadPage($('#initPath').val()+'/ChangebulletinController.do?method=toInputTenderInfo&projectId='+i);
	}
	

	//列表操作验证
	allprojectList.validation=function(name,grid){
		//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
		//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
		if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个用户'+name);return false;}//是否只选中一个
		return true;
	}
	//查询条件过滤
	allprojectList.before=function(){
		var option={	
			//'manager.objId':$("#managerId").val()
		}
		$('#projectGrid').flexOptions({params:option});
		return true;
	}
	//加载数据成功之后调用的函数
	allprojectList.success=function(){
		$("#projectGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">发布</a></span>' : function(btn,rowId,obj){
				btn.click(function(){
					allprojectList.modifyItem(rowId);
				}).appendTo(obj);
			}
	});

	}
$(document).ready(function(){
});

