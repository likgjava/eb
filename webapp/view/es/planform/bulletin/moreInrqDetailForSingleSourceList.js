//定义文件全局变量 处理方法名重复问题
var InrqDetailList={};
//查询条件过滤
InrqDetailList.before=function(){
	var option={"auditStatus":$('#auditStatus').val(),"bullType":$('#bullType').val()};
	$('#InrqDetailGrid').flexOptions({params:option});
	return true;
}


//加载数据成功之后调用的函数
InrqDetailList.success=function(){
	$("#InrqDetailGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">查看</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#InrqDetailGrid").flexGetRowJsonById(rowId);
				var projectId = json["project"]["objId"];
				InrqDetailList.view(projectId);
			}).appendTo(obj);
			}
			});
}

InrqDetailList.view=function(projectId){
	$('#conBody').loadPage($('#initPath').val()+'/InrqDetailController.do?method=toViewInrqDetailForSupplier&projectId='+projectId);
}