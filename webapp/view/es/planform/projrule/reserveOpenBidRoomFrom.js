var reserveRoomFrom={};
var startDateStr = "";
var endDateStr = "";
$(document).ready(function(){
	 startDateStr = $("#startDate").val()+"00:00:00";
	 endDateStr = $("#endDate").val()+"23:59:59";
	 if($("#timeBucket").val()=='0'){  //表示为上午
		 $("#startDate").val($("#startDate").val()+" 09:00:00");
	 	 $("#endDate").val($("#endDate").val()+" 12:00:00");
	 }else{  //表示为下午
		 $("#startDate").val($("#startDate").val()+" 12:00:00");
	     $("#endDate").val($("#endDate").val()+" 23:59:59");
	 }
	 $("#startDate").epsDatepicker({ timeShow:true});
	 $("#endDate").epsDatepicker({ timeShow:true});
	$('#projectForm').validate();
	reserveRoomFrom.checkTime=function(checkDate,date1,date2){
		 return (checkDate>date1&&checkDate<date2);
	 }
    //通过主键查询标评室信息
$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=getMeetRoomsByObjId&meetroomId='+$("#rid").val(), function(json){
	if(json.result)alert(json.result);if(json.failure)return;

	$("#bidRoomName").val(json.meetRoom.meetRoomName);
	$("#bidRoomAddress").val(json.meetRoom.meetRoomAddress);
});
//通过主键查询标评室信息
$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=getBidRooms&startDate='+startDateStr+'&endDate='+endDateStr+'&meetroomId='+$("#rid").val(), function(json){
	if(json.result)alert(json.result);if(json.failure)return;
	var str = "";
	for ( var int = 0; int < json.list.length; int++) {
		var bidRoom = json.list[int];
		if(int==0){
			str+="该评标室在以下时间段已被占用:<br/>";
		}
		str+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<span class='eleRequired'>*</span>"+bidRoom.startDate.substring(0,16);
		str+="至"+bidRoom.endDate.substring(0,16)+"<span name='date' startDate='"+bidRoom.startDate.substring(0,16)+"' endDate='"+bidRoom.endDate.substring(0,16)+"'></span><br/>";
	}
	$("#spanAppen").append(str);
});
	//提交
	$('#projectSave').click(function(){
		if($("#startDate").val().substring(0,10)!=startDateStr.substring(0,10)||$("#endDate").val().substring(0,10)!=endDateStr.substring(0,10)){
			alert("请选择选定日期"+startDateStr.substring(0,10)+"！");return false;
		}
		if($("#startDate").val()>$("#endDate").val()){
			alert("截止时间要在开始时间之后！");return false;
		}
		var a = true;
		var c = $("#startDate").val();
		var d = $("#endDate").val();
		$("span[name='date']").each(function(i,n){
			var e = $(this).attr("startDate");
			var f=  $(this).attr("endDate");
			if(reserveRoomFrom.checkTime(c,e,f)||reserveRoomFrom.checkTime(d,e,f)||reserveRoomFrom.checkTime(e,c,d)||reserveRoomFrom.checkTime(e,c,d)){
				alert("该评标室在您选择的时间段已被占用,请选择其它时间段！");a=false;return  false;
			}
		});
		if(a){
			var jsonObj = formToJsonObject('reserveRoomFrom');
			if (undefined == jsonObj.objId || null == jsonObj.objId || '' == jsonObj.objId) {
				var workFlowTaskId = $('[id=auditTaskId]').val();
				if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
					workFlowTaskId = $('[id=auditTask_Id]').val();
				}
				jsonObj.workFlowTaskId = workFlowTaskId;
				jsonObj.projectTaskId = $('#projectTaskId').val();
				jsonObj.auditStatus = 'Y';
			}
			$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=saveOpenBidPresetTime',jsonObj, function(json){
				$('#epsDialogCloseReload').click();
				if($("#projectTaskId") && $("#projectTaskId").val() != ""){
			   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			   		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			   	}
			});
		}
	
	});
});
