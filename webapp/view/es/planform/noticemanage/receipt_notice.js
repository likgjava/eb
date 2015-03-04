
var receipt_notice={};

$(document).ready(function(){
	$('#passButton').click(function(){
		$.getJSON($('#initPath').val()+'/NoticeController.do?method=sureNotice&noticeId='+$('#noticeId').val(), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			if($("#fromType").val()=='fromDesk'){  //如果从待办任务进入，则返回我的桌面
				$("#myDesktop").click();
			}
			$("#tabform7").click();
		})
	});
});
