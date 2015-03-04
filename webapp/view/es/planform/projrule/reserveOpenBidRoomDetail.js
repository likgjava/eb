var reserveRoomFrom={};
$(document).ready(function(){
	$('#projectForm').validate();
		 //通过标评室名称，日期，是否上下午查询包组信息
	    $.getJSON($('#initPath').val()+'/MeetRoomController.do?method=getProjectByRoomIdAndTime&meetroomId='+$("#rid").val()+'&time='+$("#startDate").text()+'&timeBucket='+$("#timeBucket").val(), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$("#projectName").text(json.ProjPack.projName);
	});	
    //通过主键查询标评室信息
	$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=getMeetRoomsByObjId&meetroomId='+$("#rid").val(), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$("#bidRoomName").text(json.meetRoom.meetRoomName);
		$("#bidRoomAddress").text(json.meetRoom.meetRoomAddress);
	});
	//取消预订
	$('#projectRemove').click(function(){
		$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=removePresetTimeByojbId&objId='+$("#isSelectId").val(),function(json){
			$('#epsDialogCloseReload').click();
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		});
	});
});
