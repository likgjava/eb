
var congFactorEvalMethodForm={};

$(document).ready(function(){
	$('#congFactorEvalMethodForm').validate();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/CongFactorEvalMethodController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('congFactorEvalMethodForm', json.congFactorEvalMethod);
    	});
    }
	//返回
	$('#congFactorEvalMethodReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/CongFactorEvalMethodController.do");
	});
	//提交
	$('#congFactorEvalMethodSave').click(function(){
		if(!$('#congFactorEvalMethodForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/CongFactorEvalMethodController.do?method=save', formToJsonObject('congFactorEvalMethodForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/CongFactorEvalMethodController.do');
		});
	});

});
