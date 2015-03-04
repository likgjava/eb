	var meetRoomShowView={};

	$(document).ready(function(){
		var roomtype = $("#roomType").val();
		if(roomtype=='00'){//跳转到查看开标室页面
			$("#bidRoomDetail").loadPage($("#initPath").val()+"/view/es/planform/projrule/openbid_room_view.jsp?projectId="+$("#projectId").val());
		//	$("#bidRoomAdd").loadPage($("#initPath").val()+"/view/es/planform/projrule/openbid_room_add.jsp");
		}else{//跳转到预定标评室页面
			$("#bidRoomDetail").loadPage($("#initPath").val()+"/view/es/planform/projrule/bid_room_view.jsp?projectId="+$("#projectId").val());
		//	$("#bidRoomAdd").loadPage($("#initPath").val()+"/view/es/planform/projrule/bid_room_add.jsp");
		}
		
	});
	
