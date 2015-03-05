//定义文件全局变量 处理方法名重复问题
var ProcessDefNodeList={};

$(document).ready(function(){
	
})

ProcessDefNodeList.add = function(){
	$.epsDialog({
		id:'process_node_form',
        title:'新增流程节点',
        url:$("#initPath").val()+"/ProcessDefNodeController.do?method=toCreateOrUpdate&processDefId="+$('#processDefId').val(),
        width: '800',
        height: '500',
        maxWin:false,
        onClose: function(){
        	
        }
	});
}

ProcessDefNodeList.success = function(){
	$("#processDefNodeGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$.epsDialog({
					id:'process_node_form',
			        title:'更新流程节点',
			        url:$("#initPath").val()+"/ProcessDefNodeController.do?method=toCreateOrUpdate&processDefId="+$('#processDefId').val()+'&objId='+rowId,
			        width: '800',
			        height: '500',
			        maxWin:false,
			        onClose: function(){
			        	
			        }
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">编辑审核表单</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				$.epsDialog({
					id:'process_node_form_list',
			        title:'编辑流程节点审核表单',
			        url:$("#initPath").val()+'/view/workFlow/ProcessDefNodeFormList.jsp?processDefNodeId='+rowId,
			        width: '800',
			        height: '500',
			        maxWin:false,
			        onClose: function(){
			        	
			        }
				});
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">删除</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#processDefNodeGrid").flexGetRowJsonById(rowId); 
				if(window.confirm('确定删除['+json.nodesName+']吗?')){
					$.getJSON($('#initPath').val()+'/ProcessDefNodeController.do?method=removeProcessDefNodeCascadeRemoveProcessDefNodeForm',{'objId':rowId},function(json){
						$("#processDefNodeGrid").reload();
					});
				}
			}).appendTo(obj);
		}
	});
}
