//包组区分JS方法、属性的唯一性
var itemValueAdd={};

$(document).ready(function(){
	//“返回”操作对应的事件
	$("#itemValueAddReturn").click(function(){
		$("#sysconfigItemListView").click();
	});
	$("#itemValueAddSave").click(function(){
		if(!$('#itemValueAddForm').valid()){
			alert('请正确填写表单!','提示',{icon:'info'});
			return;
		}
		$.getJSON($('#initPath').val()+'/SysConfigController.do?method=saveSysConfigItem', formToJsonObject('sysConfigTypeItemForm'), function(json){
			if(json.failure){
				alert(json.result);
				return;
			}
			if(json.result){
				alert(json.result,{inco:'info'});
			}
			$("#sysconfigItemListView").click();
		});
	})
});
