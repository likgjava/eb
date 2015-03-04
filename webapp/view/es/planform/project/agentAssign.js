
$(document).ready(function(){
	$("#linkGovMan").val($("#projManager").val());
	//指定经办人
	$("#sure").click(function(){
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=addProjectLinkMan',formToJsonObject('agentAssignForm'),function(json){
			if(json.failure)return;
			$('#epsDialogCloseReload').click();
		});
	})
	//关闭
	$('#closeButton').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
})