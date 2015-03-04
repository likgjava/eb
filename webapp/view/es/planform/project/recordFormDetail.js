
var recordFormForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/RecordFormController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('recordFormDetailForm',json.recordForm);
    	});
    }
	//返回
	$('#recordFormReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/RecordFormController.do');
	});
});