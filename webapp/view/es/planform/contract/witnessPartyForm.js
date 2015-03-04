
var witnessPartyForm={};

$(document).ready(function(){
	$('#witnessPartyForm').validate();
     			$("#signedTime").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/WitnessPartyController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('witnessPartyForm', json.witnessParty);
    	});
    }
	//返回
	$('#witnessPartyReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/WitnessPartyController.do");
	});
	//提交
	$('#witnessPartySave').click(function(){
		if(!$('#witnessPartyForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/WitnessPartyController.do?method=save', formToJsonObject('witnessPartyForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/WitnessPartyController.do');
		});
	});

});
