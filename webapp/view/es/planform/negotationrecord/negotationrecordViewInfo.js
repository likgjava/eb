var negotationrecordInfo ={};


$(document).ready(function(){
	var groupType = $('#groupType').val();
	var projectId = $('#projectId').val();
	var workGroupId = $('#workGroupId').val();
	var projProcessRule = $('#projProcessRule').val();
	$("#subDiv").tabs();
	negotationrecordInfo.getOpenBidUser = function(subProjectId,i){//点击包组
		$('#subDiv').find("li").removeClass();
		$("#tab"+i).addClass('selected');
		$('#openBidList').loadPage($('#initPath').val()+'/NegotationRecordController.do?method=toViewNegotationrecordList&projectId='+projectId+'&subProjectId='+subProjectId);
	}
	
	if (projProcessRule=='TRUE') {//判断是否分包开标
		$('#a1').click();
		$("#tab1").addClass("selected ui-tabs-selected ui-state-active");
	}else{
		$('#openBidList').loadPage($('#initPath').val()+'/NegotationRecordController.do?method=toViewNegotationrecordList&projectId='+projectId);
	}
	
});