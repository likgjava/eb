
var conRefValueForm={};

$(document).ready(function(){
	$('#conRefValueForm').validate();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ConRefValueController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('conRefValueForm', json.conRefValue);
    	});
    }
	//返回
	$('#conRefValueReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ConRefValueController.do");
	});
	//提交
	$('#conRefValueSave').click(function(){
		if(!$('#conRefValueForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ConRefValueController.do?method=save', formToJsonObject('conRefValueForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ConRefValueController.do');
		});
	});

});
