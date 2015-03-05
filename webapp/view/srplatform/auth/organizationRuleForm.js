
var orgnizationRuleForm={};

$(document).ready(function(){
	$('#orgnizationRuleForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/OrganizationRuleController.do?method=createOrUpdate',{objId:$('#objId').val()},function(json){
    		jsonObjectToForm('orgnizationRuleForm', json.organizationRule);
    	});
    }
	//返回
	$('#orgnizationRuleReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/OrganizationRuleController.do");
	});
	//提交
	$('#orgnizationRuleSave').click(function(){
		if(!$('#orgnizationRuleForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		$.getJSON($('#initPath').val()+'/OrganizationRuleController.do?method=saveRule', formToJsonObject('orgnizationRuleForm'), function(json){
			if(json.result)
				alert(json.result,'提示',{inco:'info'});;
			if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/OrganizationRuleController.do');
		});
	});

});
