
var workgMemberForm={};

$(document).ready(function(){
	$('#workgMemberForm').validate();
    if($('#workgMemberId').val()!=''){
  
    	$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=createOrUpdate',{objId:$('#workgMemberId').val(), includedProperties:'workGroup'},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('workgMemberForm', json.workgMember);
    	});
    }
	//返回
	$('#workgMemberReturn').click(function(){
		$('#epsDialogClose').click();
	});
	//提交
	$('#workgMemberSave').click(function(){
		if(!$('#workgMemberForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=save', formToJsonObject('workgMemberForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		    }
		});
	});

});
