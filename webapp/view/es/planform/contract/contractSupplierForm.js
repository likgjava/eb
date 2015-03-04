
var contractSupplierForm={};

$(document).ready(function(){
	$('#contractSupplierForm').validate();
     			$("#signedTime").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ContractSupplierController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('contractSupplierForm', json.contractSupplier);
    	});
    }
	//返回
	$('#contractSupplierReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ContractSupplierController.do");
	});
	//提交
	$('#contractSupplierSave').click(function(){
		if(!$('#contractSupplierForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ContractSupplierController.do?method=save', formToJsonObject('contractSupplierForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ContractSupplierController.do');
		});
	});

});
