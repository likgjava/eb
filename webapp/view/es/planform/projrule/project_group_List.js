var leadGroup={};
leadGroup.rows=null;
leadGroup.before=function(){
	return true;
}

leadGroup.success=function(){
	 $("#leadGroupGrid").flexGetColByName({
	        'options':function(id,t){
		         $(t).html(
	        		'<button onclick="modifyItem(\''+id+'\')"><span>修改</span></button><button onclick="deleteItem(\''+id+'\')"><span>删除</span></button>'
	        		)
	        }
	 });
}

var workGroup={};
workGroup.rows=null;
workGroup.before=function(){
	return true;
}
workGroup.success=function(){
	 $("#workGroupGrid").flexGetColByName({
	        'options':function(id,t){$(t).html(
	        		'<button onclick="modifyItem(\''+id+'\')"><span>修改</span></button><button onclick="deleteItem(\''+id+'\')"><span>删除</span></button>'
	        		)}
	 });
}


function modifyItem(objId){
	$.getJSON($("#initPath").val()+'/GroupMemberController.do?method=beforeUpdateView&objId='+objId,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
	    		jsonObjectToForm('groupMemberForm', json.groupMember);
	    	});
	}



	function deleteItem(objId){
		if(window.confirm('确定要删除当前数据吗?')){
			 $.getJSON($('#initPath').val()+'/GroupMemberController.do?method=removeGroupMember&objId='+objId,function(json){
			    if(json.result)alert(json.result);if(json.failure)return;
			    $("#leadGroupGrid").reload();
			    $("#workGroupGrid").reload();
				});			
		}
	}



$(document).ready(function(){
	$("#infoDiv").tabs();
	
	$('#addGroupForm').validate();
	$("#submitBtn").click(function(){
		//保存或修改小组成员事件
	//	alert(obj2str(formToJsonObject('groupMemberForm')));
		$.getJSON($('#initPath').val()+'/GroupMemberController.do?method=saveOrUpdateGroupMember',formToJsonObject('groupMemberForm'),function(json){
	    if(json.result)alert(json.result);if(json.failure)return;
	    $("#leadGroupGrid").reload();
	    $("#workGroupGrid").reload();
		});	
	})
	
	$("#taskPlanDetailReset").click(function(){
		$("#objId").attr('value','');
	})
	
	//组建项目小组
	$("#buildGroup").click(function (){
	$("#buildGroupDetail").toggle("slow");
	$("#buildGroup span").toggleClass("collapsable");	
	})
})