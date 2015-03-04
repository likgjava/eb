
var bailRecordForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/BailRecordController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('bailRecordDetailForm',json.bailRecord);
    	});
    }
	//返回
	$('#bailRecordReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/BailRecordController.do');
	});
});