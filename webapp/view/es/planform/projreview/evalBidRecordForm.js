
var evalBidRecordForm={};

$(document).ready(function(){
	$('#evalBidRecordForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#updateTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/EvalBidRecordController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('evalBidRecordForm', json.evalBidRecord);
    	});
    }
	//返回
	$('#evalBidRecordReturn').click(function(){
		var projectId = $("#projectId").val();
		$('#evalBidRecordFormView').empty().loadPage($('#initPath').val()+"/EvalBidRecordController.do?projectId="+projectId);
	});
	//提交
	$('#evalBidRecordSave').click(function(){
		if(!$('#evalBidRecordForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/EvalBidRecordController.do?method=save', formToJsonObject('evalBidRecordForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			checkProjectMenu("menu_evalbid");
		});
	});

});
