
var projectForm={};

$(document).ready(function(){
	$('#projectForm').validate();
     			$("#createTime").epsDatepicker();
     			$("#closeProjDate").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ProjectController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('projectForm', json.project);
    	});
    }
	//返回
	$('#projectReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/es/planform/project/projectList.jsp");
	});
	//提交
	$('#projectSave').click(function(){
		if(!$('#projectForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=save', formToJsonObject('projectForm'), function(json){
			
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/project/projectList.jsp');
		});
	});

});
