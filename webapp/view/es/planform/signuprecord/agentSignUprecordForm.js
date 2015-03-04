
var signUprecordForm={};

$(document).ready(function(){
	var projectId=$("input[name='project.objId']").val();
	$('#signUprecordForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#modifyTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('signUprecordForm', json.signUprecord);
    	});
    }
	//返回
	$('#signUprecordReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/SignUprecordController.do");
	});
	//提交
	$('#signUprecordSave').click(function(){
		if(!$('#signUprecordForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/SignUprecordController.do?method=saveSignUprecord&projectId='+projectId, formToJsonObject('signUprecordForm'), function(json){
			if(json.result){
				//投标单位报名
				checkProjectMenu('menu_signUp');
			}
			if(json.failure)return;
		});
	});

});
