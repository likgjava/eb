
var buy_result_list={};
//查看winner记录
buy_result_list.viewWinner = function(i,resultId,subProjectId){
		$('#resultTabUl').find("li").removeClass();
		$('#tabs'+i).addClass('selected');
		$('#resultTabDiv').loadPage($('#initPath').val()+'/BuyWinnerController.do?method=toListBuyResult&subProjectId='+subProjectId+'&resultId='+resultId)
}

$(document).ready(function(){
	 $("#superviseDoDiv1").tabs();
	$('#resultTabUl a:first').click();
	
	$("#finishBtn").click(function(){ //完成发送结果通知
		$.getJSON($('#initPath').val()+'/NoticeController.do?method=finishBuyResultNotice&projectId='+$('#projectId').val(),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		});	
	});
});