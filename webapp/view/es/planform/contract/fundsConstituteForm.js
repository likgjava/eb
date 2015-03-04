
var fundsConstituteForm={};

$(document).ready(function(){
	$('#fundsConstituteForm').validate();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/FundsConstituteController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('fundsConstituteForm', json.fundsConstitute);
    	});
    }
	//返回
	$('#fundsConstituteReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/FundsConstituteController.do");
	});
	//提交
	$('#fundsConstituteSave').click(function(){
		if(!$('#fundsConstituteForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/FundsConstituteController.do?method=save', formToJsonObject('fundsConstituteForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/FundsConstituteController.do');
		});
	});

});
