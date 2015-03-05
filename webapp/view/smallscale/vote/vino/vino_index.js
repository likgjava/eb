var vinoIndex={};

//初始化
$(document).ready(function(){
});

//跳转至首页
vinoIndex.toVinoIndex = function(){
	window.location.href = $('#initPath').val()+"/VoteTemplateShowController.do?method=showVoteIndex&templateCode="+$('#templateObjCode').val();
}
	
//跳转至活动介绍
vinoIndex.toVinoHdjs = function(){
	$("#conBody").loadPage($("#initPath").val()+"/view/smallscale/vote/vino/vino_hdjs.jsp");
}

//跳转至活动流程
vinoIndex.toVinoHdlc = function(){
	$("#conBody").loadPage($("#initPath").val()+"/view/smallscale/vote/vino/vino_hdlc.jsp");
}

//跳转至奖项设置
vinoIndex.toVinoJxsz = function(){
	$("#conBody").loadPage($("#initPath").val()+"/view/smallscale/vote/vino/vino_jxsz.jsp");
}

//跳转至评审专家
vinoIndex.toVinoPszj = function(){
	//$("#conBody").loadPage($("#initPath").val()+"/view/smallscale/vote/vino/vino_pszj.jsp");
	var search = "";
	//分页信息
	search += "&rp=20";
	search += "&page=1";

	var voteTemplateId = $('#templateId').val();
	$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=showMoreUnitOrExpertOrOrg&voteTemplateId='+voteTemplateId+'&showMoreType=moreExperts'+search);
}

//跳转至公开投票
vinoIndex.toVinoGktp = function(){
	//$("#conBody").loadPage($("#initPath").val()+"/view/smallscale/vote/vino/vino_gktp.jsp");
	var search = "";
	//分页信息
	search += "&rp=20";
	search += "&page=1";

	var voteTemplateId = $('#templateId').val();
	$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=showVoteResult&templateId='+voteTemplateId+search);
}

//跳转至媒体联盟
vinoIndex.toVinoMtlm = function(){
//	$("#conBody").loadPage($("#initPath").val()+"/view/smallscale/vote/vino/vino_mtlm.jsp");
	var voteTemplateId = $('#templateId').val();
	$("#conBody").loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=showMoreUnitOrExpertOrOrg&voteTemplateId='+voteTemplateId+'&showMoreType=moreMediums');

}

//跳转至在线报名
vinoIndex.toVinoBmfs = function(){
	var userId = $('#userId').val();
	var userName = $('#userName').val();
	//匿名报名
	if(userId == null || userId == ""){
		$("#conBody").loadPage($("#initPath").val()+"/view/smallscale/vote/sign_online_no_user.jsp");
	}else{
		//用户登录
		$("#conBody").loadPage($("#initPath").val()+"/view/smallscale/vote/sign_online_has_user.jsp?userName="+userName);
		//添加品牌页面
//		$('#conBody').loadPage($('#initPath').val()+'/VoteTemplateShowController.do?method=signOnLine&templateId='+$('#templateId').val());
	}
	
}







