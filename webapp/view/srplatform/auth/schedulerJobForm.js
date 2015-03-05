
var schedulerJobForm={};

$(document).ready(function(){
	$('#schedulerJobForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/SchedulerJobController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('schedulerJobForm', json.schedulerJob);
    	});
    }
	//返回
	$('#schedulerJobReturn').click(function(){
		$('#contentMainBox').loadPage($('#initPath').val()+"/SchedulerJobController.do");
	});
	//提交
	$('#schedulerJobSave').click(function(){
		if(!$('#schedulerJobForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/SchedulerJobController.do?method=save', formToJsonObject('schedulerJobForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#contentMainBox').loadPage($('#initPath').val()+'/SchedulerJobController.do');
		});
	});

});
