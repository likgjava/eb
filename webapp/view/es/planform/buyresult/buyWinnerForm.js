
var buyWinnerForm={};

$(document).ready(function(){
	$('#buyWinnerForm').validate();
    if($('#buyWinnerId').val()!=''){
    	$.getJSON($('#initPath').val()+'/BuyWinnerController.do?method=createOrUpdate',{objId:$('#buyWinnerId').val(), includedProperties:'buyResult'},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('buyWinnerForm', json.buyWinner);
    	});
    }
	//关闭
	$('#buyWinnerClose').click(function(){
		$('#epsDialogClose').click();
	});
	//提交
	$('#buyWinnerSave').click(function(){
		if(!$('#buyWinnerForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/BuyWinnerController.do?method=save', formToJsonObject('buyWinnerForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#buyWinnerClose').click();
		});
	});

});
