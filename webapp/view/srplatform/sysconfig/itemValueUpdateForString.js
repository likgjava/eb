//包组区分JS方法、属性的唯一性
var itemValueUpdateForString={};

$(document).ready(function(){
	if($("#objId").val()!="" && $("#objId").val()!="-1"){
    	$.getJSON($("#initPath").val()+"/SysConfigController.do?method=getItemValueDetail",{objId:$("#objId").val()},function(json){
    		jsonObjectToForm("itemValueUpdateForStringForm", json.sysConfigItemValue);
    		
    	});
    }
	
	
	//“返回”操作对应的事件
	$("#itemValueUpdateForStringReturn").click(function(){
		$("#sysconfigItemListView").click();
	});
	//"删除"操作对应的事件
	$("#itemValueUpdateForStringDelete").click(function(){
		if(confirm("确定删除当前配置数据?")){
			$.getJSON($("#initPath").val()+"/SysConfigController.do?method=delItemValue",{objId:$("#objId").val()},function(json){
				$("#sysconfigItemListView").click();
	    	});
		}else{
			return ;
		}
		
	});
	$("#itemValueUpdateForStringSave").click(function(){
		if(!$('#itemValueUpdateForStringForm').valid()){
			alert('请正确填写表单!','提示',{icon:'info'});
			return;
		}
		$.getJSON($('#initPath').val()+'/SysConfigController.do?method=saveItemValueForUpdate', formToJsonObject('itemValueUpdateForStringForm'), function(json){
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
