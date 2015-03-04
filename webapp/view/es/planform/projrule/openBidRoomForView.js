
$(document).ready(function(){

	var timeBucket = $('#timeBucket').val();
	var meetroomId = $("#rid").val();
	var rtime = $('#rtime').val();
	
	$('#openBidRoomIdR').click(function(){//点击关闭
		$('#epsDialogClose').click();
	});
	
	 //通过主键查询标评室信息
	$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=getBidRoomsByMeetRoomId&meetroomId='+meetroomId+'&timeBucket='+timeBucket+'&rtime='+rtime, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		if (json.bidRoom!=null&&json.bidRoom!=undefined) {
			$("#remark").text(json.bidRoom.remark);
		}
	});
	
});
   