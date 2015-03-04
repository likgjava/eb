
var bailRecordForm={};

$(document).ready(function(){
	$('#bailRecordForm').validate();
     			$("#renderTime").epsDatepicker();
     			$("#returnedTime").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/BailRecordController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('bailRecordForm', json.bailRecord);
    	});
    }
	//返回
	$('#bailRecordReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/BailRecordController.do");
	});
	//提交
	$('#bailRecordSave').click(function(){
		if(!$('#bailRecordForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/BailRecordController.do?method=save', formToJsonObject('bailRecordForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/BailRecordController.do');
		});
	});

});
