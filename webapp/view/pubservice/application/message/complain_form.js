
var complainForm={};

$(document).ready(function(){
	$('#complainForm').validate();

    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ComplainController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('complainForm', json.complain);
    		//加载附件    		
    		//$("#attFileDiv").loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attFile&isSelect=yes&attachRelaId='+json.complain.attFile);
    		$("#attFileDiv").loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=attFile&isSelect=yes&attachRelaId='+$("#attFileDiv").text());
    	});
    }
    
    
	//返回
	$('#complainReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ComplainController.do?method=listDeal&currentTabID=" + $('#currentTabID').val());
	});
	
	//提交
	$('#complainSave').click(function(){	
		if(!$('#complainForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ComplainController.do?method=saveDeal', formToJsonObject('complainForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/ComplainController.do?method=listDeal&currentTabID=' + $('#currentTabID').val());
		});
	});

});
