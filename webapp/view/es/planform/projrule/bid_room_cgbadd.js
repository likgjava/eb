$(document).ready(function(){
	$("#temporaryRoom").hide();
	
	  $("#checkTemporaryRoom").click(function(){
		  $("#temporaryRoom").slideToggle();
	  })
	
	$("#addRoom").click(function(){
		  if($("#meetRoomName").val()=='') {
			  alert("开标室名称为必填项");
		  }
		  else if($("#meetRoomAddress").val()=='') {
			  alert("地址为必填项");
		  }
		  else {  
		$.getJSON($("#initPath").val()+'/MeetRoomController.do?method=addTemporaryRoom',formToJsonObject('RoomForm'),function(json){
		if(json.result)alert(json.result);if(json.failure)return;
			$("#bidRoomDetail").empty().loadPage($("#initPath").val()+"/view/es/planform/projrule/bid_room_cgbDetail.jsp?projectId="+$("#projectId").val());
		});  
		}
     })
})