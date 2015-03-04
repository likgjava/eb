/*
 * 执行平台，需求条目表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var preqEntryForm={};
	
$(document).ready(function(){
	$('#preqEntryForm').validate();
	
    if($('#preqEntryId').val()!=''){
    	$.getJSON($('#initPath').val()+'/PreqEntryController.do?method=createOrUpdate&objId='+$("#preqEntryId").val(),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('preqEntryForm', json.preqEntry);
    		json2ObjectSpan('preqEntryForm', json.preqEntry);
    	});
    }
    
	//关闭
	$('#preqEntryClose').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	
	//提交
	$('#preqEntrySave').click(function(){
		if(!$('#preqEntryForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/PreqEntryController.do?method=save',formToJsonObject('preqEntryForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#epsDialogCloseReload').click();
		});
	});
});
