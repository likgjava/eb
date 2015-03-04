var reserveRoomFrom={};
$(document).ready(function(){
	$('#projectForm').validate();
    //通过主键查询标评室信息
	$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=getPresetTimeByojbId&objId='+$("#isSelectId").val(), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$("#bidRoomName").text(json.bidroom.bidRoomName);
		$("#bidRoomAddress").text(json.bidroom.bidRoomAddress);
		$("#startDate").text(json.startTime);
		$("#endDate").text(json.endTime);
		//$("#replayRequirement").text(json.bidroom.replayRequirement);
		$("#projectName").text(json.bidroom.project.projName);
	});
	//取消预订
	$('#projectRemove').click(function(){
		$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=removePresetTimeByojbId&objId='+$("#isSelectId").val(),function(json){
			$('#epsDialogCloseReload').click();
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		});
	});
});
