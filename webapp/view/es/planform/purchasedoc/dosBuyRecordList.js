
var dosBuyRecordList={};
dosBuyRecordList.rows=null;//列表查询的结果集
	dosBuyRecordList.add=function(name,grid){//新增
		var fileId = $("#fileId").val();
		var projectId = $("#projectId").val();
		$.epsDialog({
	        title:'文件购买记录',
	        url:$('#initPath').val()+"/DosBuyRecordController.do?method=toDosBuyRecordForm&projectId="+projectId+"&isComplete=true&fileId="+fileId,
	        width: '650',
	        height: '230',
	        isReload:false,
	        onClose: function(){ 
				$('#dosBuyRecordGrid').reload();
	        }
		});
}   
//列表操作验证
dosBuyRecordList.validation=function(name,grid){
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
				$.epsDialog({
			        title:'文件购买记录详情',
			        url:$('#initPath').val()+'/DosBuyRecordController.do?method=toUpdateDosBuyRecordDetail&projectId='+projectId+'&fileId='+fileId+'&objId='+rowId,
			        width: '650',
			        height: '230',
			        isReload:false,
			        onClose: function(){ 
						$('#dosBuyRecordGrid').reload();
			        }
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
			btn.click(function(){
				var fileId = $("#fileId").val();
				var projectId = $("#projectId").val();
				$.epsDialog({
			        title:'修改文件购买记录',
			        url:$('#initPath').val()+'/DosBuyRecordController.do?method=toUpdateDosBuyRecordForm&projectId='+projectId+'&fileId='+fileId+'&objId='+rowId,
			        width: '650',
			        height: '230',
			        isReload:false,
			        onClose: function(){ 
						$('#dosBuyRecordGrid').reload();
			        }
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>'  : function(btn,rowId,obj){
			btn.click(function(){
				if(window.confirm('确定删除吗?')){
					$.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=removeDosBuyRecordById',{'objIds':rowId},function(json){
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