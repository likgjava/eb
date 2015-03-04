
var contractFundDetailForm={};

$(document).ready(function(){
	$('#contractFundDetailForm').validate();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractFundDetailController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractFundDetailForm', json.contractFundDetail);
    	});
    }
	//返回
	$('#contractFundDetailReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractFundDetailController.do");
	});
	//提交
	$('#contractFundDetailSave').click(function(){
		if(!$('#contractFundDetailForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractFundDetailController.do?method=save', formToJsonObject('contractFundDetailForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractFundDetailController.do');
		});
	});

});
