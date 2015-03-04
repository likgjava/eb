
var factorDeForm={};
$(document).ready(function(){
	$("[id=factortypeDe.objId]").val($("#_factortypeId").val());
	$('#factorDeForm').validate();
	//返回
	$('#return_btn').click(function(){
		$('#epsDialogCloseReload').click();
	});
	//提交
	$('#factorDeSave').click(function(){
		if(!$('#factorDeForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/FactorDeController.do?method=updateFactorDe', formToJsonObject('factorDeForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#epsDialogCloseReload').click();
		});
	});

});
