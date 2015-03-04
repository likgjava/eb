
var projMemberForm={};

$(document).ready(function(){
	$('#projMemberForm').validate();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ProjMemberController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('projMemberForm', json.projMember);
    	});
    }
	//返回
	$('#projMemberReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ProjMemberController.do");
	});
	//提交
	$('#projMemberSave').click(function(){
		if(!$('#projMemberForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ProjMemberController.do?method=save', formToJsonObject('projMemberForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ProjMemberController.do');
		});
	});

});
