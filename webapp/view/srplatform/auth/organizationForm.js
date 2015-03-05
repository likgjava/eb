//定义文件全局变量 处理方法名重复问题

var orgnizationForm={};

$(document).ready(function(){
	$('#orgnizationForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/OrganizationController.do?method=createOrUpdate',{objId:$('#objId').val()},function(json){
    		jsonObjectToForm('orgnizationForm', json.organization);
    	});
    }
	//返回
	$('#orgnizationReturn').click(function(){
		$('#content').loadPage($('#initPath').val()+"/OrganizationController.do");
	});
	//提交
	$('#orgnizationSave').click(function(){
		if(!$('#orgnizationForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		$.getJSON($('#initPath').val()+'/OrganizationController.do?method=save', formToJsonObject('orgnizationForm'), function(json){
			if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
			$('#content').loadPage($('#initPath').val()+'/OrganizationController.do');
		});
	});

});
