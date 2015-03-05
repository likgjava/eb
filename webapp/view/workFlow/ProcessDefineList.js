//定义文件全局变量 处理方法名重复问题
var ProcessDefineList={};
ProcessDefineList.pageContent;

ProcessDefineList.success = function(){
	$("#processDefineGrid").flexAddOptionStr({
		/*'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').empty().loadPage($('#initPath').val()+'/ProcessDefineController.do?method=toCreateOrUpdate&objId='+rowId);
			}).appendTo(obj);
		},*/
		'<span><a href="#" class="abtn">编缉流程节点</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').empty().loadPage($('#initPath').val()+'/ProcessDefNodeController.do?processDefId='+rowId);
			}).appendTo(obj);
		}/*,
		'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#processDefineGrid").flexGetRowJsonById(rowId); 
				if(window.confirm('确定删除['+json.processDefName+']吗?')){
					$.getJSON($('#initPath').val()+'/ProcessDefineController.do?method=removeProcessDefineCascadeDelNodeCascadeDelNodeForm',{'objId':rowId},function(json){
						$("#processDefineGrid").reload();
					});
				}
			}).appendTo(obj);
		}*/
	});
}
ProcessDefineList.add = function(){
	$('#conBody').empty().loadPage($('#initPath').val()+'/ProcessDefineController.do?method=toCreateOrUpdate');
}
$(document).ready(function(){
	$("#returnUrl").val($('#initPath').val()+'/ProcessDefineController.do');
})
