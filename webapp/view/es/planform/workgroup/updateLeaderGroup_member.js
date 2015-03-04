var updateOpenBidInfoForm ={};

updateOpenBidInfoForm.reflushPage = function(){
	if($("#fromType").val()=='fromDesk'){
		$('#updateOpenBidMember').find(".epsDialogClose").click();
		$('#workMemberDiv').empty().loadPage($('#initPath').val()+'/WorkGroupController.do?method=toOpenBidWorkGroupForProject&fromType=fromDesk&groupType=03&projectId='+$("#projectId").val());
	}else{
		$('#epsDialogClose').click();
		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	}
}

$(document).ready(function(){
	
	
	$('#closeBtn').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	
	$('#saveBtnId').click(function(){
		if(!$('#workgMemberFormId').valid()){alert('请正确填写表单!');return;}
		//alert(JSON.stringify(formToJsonObject('workgMemberFormId')));
		$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=saveLeaderGroupMember&projectId='+$('#projectId').val(),formToJsonObject('workgMemberFormId'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			updateOpenBidInfoForm.reflushPage();
		});
	});
});