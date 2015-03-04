
$(document).ready(function(){

	var timeBucket = $('#timeBucket').val();
	var meetroomId = $("#rid").val();
	var rtime = $('#rtime').val();
	
	$('#isOff').click(function(){//点击禁用：显示输入意见
		$('#remarkId').show();
	});

	$('#isOpen').click(function(){//点击启用：隐藏输入意见
		$('#remarkId').hide();
	});
	
	$('#openBidRoomIdS').click(function(){//点击保存
		var item = $(":radio:checked").val();//
		if (item=='2') {//启用
			$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=removeBidRoom&meetroomId='+meetroomId+'&timeBucket='+timeBucket+'&rtime='+rtime,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#epsDialogClose').click();
				$("#conBody").loadPage($("#initPath").val()+"/view/es/planform/projrule/meetRoomcgbShowView.jsp");
			});
		}else{//禁用
			if ($('#remark').val()==''||$('#remark').val()==undefined||$('#remark').val()==null) {
				alert('请填写原由！');
				return false;
			}
			$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=saveForIsOff',formToJsonObject('openBidRoomIsOffFrom'),function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#epsDialogClose').click();
				$("#conBody").loadPage($("#initPath").val()+"/view/es/planform/projrule/meetRoomcgbShowView.jsp");
			});
		}
	});

	
	$('#openBidRoomIdR').click(function(){//点击关闭
		$('#epsDialogClose').click();
	});
	
	 //通过主键查询标评室信息
	$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=getBidRoomsByMeetRoomId&meetroomId='+meetroomId+'&timeBucket='+timeBucket+'&rtime='+rtime, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		if (json.bidRoom!=null&&json.bidRoom!=undefined) {
			$("#remark").text(json.bidRoom.remark);
			var useStatus = json.bidRoom.useStatus;
			if (useStatus=='3') {
				$('#isOff').attr("checked",true);
				$('#remarkId').show();
			}else{
				$('#isOpen').attr("checked",true);
				$('#remarkId').hide();//隐藏输入意见
			}
		}else{
			$('#isOpen').attr("checked",true);
			$('#remarkId').hide();//隐藏输入意见
		}
	});
	
});
   