var meetingRoomList = {};
meetingRoomList.oTable;

meetingRoomList.getOperatorStr=function(){
	var operatorHtml = "";
	operatorHtml += '<td class="operation">';
	if($('#viewType').val() && $('#viewType').val() == "createOrUpdate"){
		operatorHtml += '<a name="update" title="修改" href="javascript:void(0);"><span>修改</span></a>';
		operatorHtml += '<a name="delete" title="删除" href="javascript:void(0);"><span>删除</span></a>';
	}
	operatorHtml += '<a name="view" title="查看" href="javascript:void(0);"><span>查看</span></a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

// 新增或修改会议室
meetingRoomList.createOrUpdateMeetingRoom = function(id){
	var title = id==""?"新增会议室":"修改会议室"
	$.epsDialog({
		title:title,
		url:$('#initPath').val()+'/MeetingRoomController.do?method=toCreateOrUpdate&objId='+id+'&hotelId='+$('input[name=hotel.objId]').val()
	});
}


//删除
meetingRoomList.delteRoom = function(id){
	if(confirm("确定删除该会议室吗？")){
		$.getJSON($("#initPath").val()+"/MeetingRoomController.do?method=removeMeetingRoom",{"objId":id,"hotelId":$('input[name=hotel.objId]').val()},function(json){
			if(json.success){
				meetingRoomList.getMeetingRoomList();
			}else{
				alert("操作失败！");
			}
		})
	}
}

//加载会议室列表
meetingRoomList.getMeetingRoomList = function(){
	if(null==meetingRoomList.oTable){
		meetingRoomList.oTable = $('#meetingRoomList').dataTable({
			'queryColumns' : 'picture,marketPrice,unit,meetingRoomType,containNum',
			'alias' : 'picture,marketPrice,unitCN,meetingRoomTypeCN,containNum',
			'hiddenColumns': '',
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=containNum]").attr("align","right");
			$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" CURSOR="hand" width="60px" height="60px"/>');
				$(nRow).append(meetingRoomList.getOperatorStr()).find('a[name=update]').click(function(){
					meetingRoomList.createOrUpdateMeetingRoom(aData.objId);
				}).end().find('a[name=delete]').click(function(){
					meetingRoomList.delteRoom(aData.objId);
				}).end().find('a[name=view]').click(function(){
					$.epsDialog({
						title:"会议室详情",
						url:$('#initPath').val()+'/MeetingRoomController.do?method=toShowView&objId='+aData.objId
					});
				});
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/MeetingRoomController.do?method=list&hotel.objId="+$("#hotelId").val(),
			"params":{},
			'searchZone':'MeetingRoomSearchForm'
		});
	}else{
		meetingRoomList.oTable.fnDraw();
	}
}

$(document).ready(function(){
	
	// 如果是新增或修改
	if($('#viewType').val() && $('#viewType').val() == "createOrUpdate"){
		$('#meetingRoomAttention').show();
	}else{
		$('#meetingRoomAttention').hide();
	}
	
	//加载列表
	meetingRoomList.getMeetingRoomList();
	
	// 新增会议室
	$('#addMeetingRoom').click(function(){
		meetingRoomList.createOrUpdateMeetingRoom("");
	});
	
	// 返回
	$('#historyBackBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	});
})