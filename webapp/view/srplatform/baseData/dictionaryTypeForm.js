
var dictionaryTypeForm={};

$(document).ready(function(){
	$('#dictionaryTypeForm').validate();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/DictionaryTypeController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('dictionaryTypeForm', json.dictionaryType);
    	});
    }
	//返回
	$('#dictionaryTypeReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/DictionaryController.do");
	});
	//提交
	$('#dictionaryTypeSave').click(function(){
		if(!$('#dictionaryTypeForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/DictionaryTypeController.do?method=save', formToJsonObject('dictionaryTypeForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/DictionaryController.do');
		});
	});

});
