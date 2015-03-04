var openBidInfo ={};


$(document).ready(function(){
	var groupType = $('#groupType').val();
	var projectId = $('#projectId').val();
	var workGroupId = $('#workGroupId').val();
	var projProcessRule = $('#projProcessRule').val();
	
	openBidInfo.getOpenBidUser = function(subProjectId,i){//点击包组
		$('#subDiv').find("li").removeClass();
		$("#tab"+i).addClass('selected');
		$('#openBidList').loadPage($('#initPath').val()+'/WorkgMemberController.do?method=toViewWorkgMemberByProjectIdAndGroupType&workGroupId='+workGroupId+'&groupType='+groupType+'&projectId='+subProjectId);
	}
	
	if (projProcessRule=='TRUE') {//判断是否分包开标
		$('#tab1').click();
	}else{
		$('#openBidList').loadPage($('#initPath').val()+'/WorkgMemberController.do?method=toViewWorkgMemberByProjectIdAndGroupType&workGroupId='+workGroupId+'&groupType='+groupType+'&projectId='+projectId);
	}
	
});