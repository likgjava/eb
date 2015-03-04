
var clauseDetailForm={};

$(document).ready(function(){
	$('#clauseDetailForm').validate();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ClauseDetailController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('clauseDetailForm', json.clauseDetail);
    	});
    }
	//返回
	$('#clauseDetailReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ClauseDetailController.do");
	});
	//提交
	$('#clauseDetailSave').click(function(){
		if(!$('#clauseDetailForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ClauseDetailController.do?method=save', formToJsonObject('clauseDetailForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ClauseDetailController.do');
		});
	});

});
