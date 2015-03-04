var project_item_manager={};
project_item_manager.rows=null;//列表查询的结果集
//查询条件过滤
project_item_manager.before=function(){
	return true;
}
//加载数据成功之后调用的函数
project_item_manager.success=function(){
	 $("#itemGrid").flexGetColByName(
			 {
	        'options':function(id,t){
				// alert(id); 这里是一个遍历
				 $(t).html(
	        		'<button onclick="modifyItem(\''+id+'\')"><span>修改</span></button><button onclick="deleteItem(\''+id+'\')"><span>删除</span></button>'
	        		)}
			 }
			 );
}
function modifyItem(objId){
	$.getJSON($("#initPath").val()+'/ProjectPackageController.do?method=beforeUpdateView&objId='+objId,function(json){
	jsonObjectToForm("projectItemForm", json.projectPackage);
	});	
}
function deleteItem(objId){
	if(window.confirm('确定要删除当前数据吗？')){
		$.getJSON(
				$('#initPath').val()+'/ProjectPackageController.do?method=removeProjectPackage&objId='+objId,function(json){
		    if(json.result)alert(json.result);if(json.failure)return;
		    $("#itemGrid").reload();
				}
				);		
	}
}

$(document).ready(function(){
	
	$("#projectItemForm").validate();
	$("#submitBtn").click(function(){
		if($("#projectItemForm").valid()){     
	if(window.confirm('确定要保存当前信息?')){
		$.getJSON($('#initPath').val()+'/ProjectPackageController.do?method=saveOrUpdateProjectPackage',formToJsonObject('projectItemForm'),function(json){
			   if(json.result)alert(json.result);if(json.failure)return;
			   $("#itemGrid").reload();
					});
				}
		}
		
		$("#taskPlanDetailReset").click(function(){
			$("#objId").attr('value','');
		})
	})
	
	//录入分包信息
	$("#PackageInfo").click(function (){
	$("#PackageInfoDetail").toggle("slow");
	$("#PackageInfo span").toggleClass("collapsable");	
	})
	
})