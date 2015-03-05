//包组区分JS方法、属性的唯一性
var itemValueAddForString={};

$(document).ready(function(){
	 
	var dataType = $('#dataType').val();
	if(dataType == 'Date'){
		$('#value').epsDatepicker();
		if(null == $('#value').val() || '' == $('#value').val()){
			$('#value').val(new Date().Format('yyyy-MM-dd'));
		}
	}
	if(dataType == 'Boolean'){
		$('[name=value]').each(function(i,n){
			if($('#_value').val() == $(n).val()){
				$(n).attr("checked",true);
			}
		})
	}
	
	//“返回”操作对应的事件
	$("#itemValueAddForStringReturn").click(function(){
		$("#sysconfigItemListView").click();
	});
	$("#itemValueAddForStringSave").click(function(){
		if(!$('#itemValueAddForStringForm').valid()){
			alert('请正确填写表单!','提示',{icon:'info'});
			return;
		}
		$.getJSON($('#initPath').val()+'/SysConfigController.do?method=saveItemValue', formToJsonObject('itemValueAddForStringForm'), function(json){
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
