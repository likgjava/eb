
var sysConfigItemValueForm={};

$(document).ready(function(){
	$('#sysConfigItemValueForm').validate();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/SysConfigItemValueController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('sysConfigItemValueForm', json.sysConfigItemValue);
    	});
    }
	//返回
	$('#sysConfigItemValueReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/SysConfigItemValueController.do");
	});
	//提交
	$('#sysConfigItemValueSave').click(function(){
		if(!$('#sysConfigItemValueForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/SysConfigItemValueController.do?method=save', formToJsonObject('sysConfigItemValueForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/SysConfigItemValueController.do');
		});
	});

});
