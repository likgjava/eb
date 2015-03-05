var voteTemplateList={};
voteTemplateList.oTable;

//新增
$("#voteTemplateAdd").click(function(){	
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do?method=toCreateOrUpdateVoteTemplate");
});

//修改
voteTemplateList.modifyVoteTemplate = function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do?method=toCreateOrUpdateVoteTemplate&objId="+objId);	
}

//刷新
voteTemplateList.reload = function(){
	voteTemplateList.oTable.fnDraw();
}

//查询
$("#voteTemplateSearchBtn").click(function(){
	voteTemplateList.oTable.fnDraw();
});

//指标维护
voteTemplateList.votePointerMaintenace = function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do?method=viewVoteTemplate&operateType=pointerMaintenace&objId="+objId);
}
//评选组
voteTemplateList.voteUnitGroup = function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do?method=viewVoteTemplate&operateType=unitGroup&objId="+objId);
}
//评审专家
voteTemplateList.voteAssesseExpert = function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do?method=viewVoteTemplate&operateType=assesseExpert&objId="+objId);
}
//参选对象
voteTemplateList.voteAssessThingMaintenace = function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do?method=viewVoteTemplate&operateType=assessedThingMaintenace&objId="+objId);
}

//合作媒体
voteTemplateList.voteTemplateMedium = function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do?method=viewVoteTemplate&operateType=templateMedium&objId="+objId);
}

//投票管理
voteTemplateList.voteAssessManager = function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/VoteTemplateController.do?method=viewVoteTemplate&operateType=voteAssessList&objId="+objId);
}

//主题统计
voteTemplateList.voteDataStatistic = function(objId){
	$.epsDialog({
		title:'主题统计',
		url:$('#initPath').val()+'/VoteTemplateController.do?method=toTemplateStatistic&objId='+objId,
		onClose:function(){}
	});
}

//查看详情
voteTemplateList.viewVoteTemplate = function(objId){
	$.epsDialog({
		title:'投票评选主题查看',
		url:$('#initPath').val()+'/VoteTemplateController.do?method=viewVoteTemplate&objId='+objId,
		onClose:function(){}
	});
}

//删除
voteTemplateList.removeVoteTemplate = function(objId){
	if(confirm('确定删除主题吗？若确定则将删除此主题下的指标和评选单位')){
		$.getJSON($('#initPath').val()+'//VoteTemplateController.do?method=removeVoteTemplate',{objId:objId},function(json){
			if(json.failure) {
				alert(json.result);
				return;
			}else{
				alert("删除成功");
				voteTemplateList.reload();
			}
		});
	}
}

//获取列表
voteTemplateList.getVoteTemplateList = function(){
	if(null == voteTemplateList.oTable){
		voteTemplateList.oTable = $('#voteTemplateList').dataTable({
			'singleSelect':true,
			'checkbox':false,
			'searchZone':'voteTemplateSearchForm',
			'sAjaxSource':$('#initPath').val()+"/VoteTemplateController.do?method=list",
			 params:{},
			'queryColumns':'templateName,startTime,endTime,createTime,isSingleVote',
			'alias':'templateName,startTime,endTime,createTime,isSingleVoteCN',
			'hiddenColumns':'objId,appraiseCommissioner',
			'fnInitComplete':function(oSettings){},
			'fnDrawCallback':function(oSettings){
				voteTemplateList.oTable.oSettings = oSettings;
			},
			'fnRowCallback':function(nRow,aData,iDisplayIndex){
				//修改
				$(nRow).append('<td><a href="javascript:voteTemplateList.modifyVoteTemplate(\''+aData.objId+'\')"><span>修改</span></a></td>');
				
				//删除
				$(nRow).find("td:last").append('<a href="javascript:voteTemplateList.removeVoteTemplate(\''+aData.objId+'\')"><span>删除</span></a>');
				
				//评选分组
				$(nRow).find("td:last").append('<a href="javascript:voteTemplateList.voteUnitGroup(\''+aData.objId+'\')"><span>评选分组</span></a>');
				
				//指标维护
				$(nRow).find("td:last").append('<a href="javascript:voteTemplateList.votePointerMaintenace(\''+aData.objId+'\')" ><span>指标维护<span></a>');
				
				//参选对象
				$(nRow).find("td:last").append('<a href="javascript:voteTemplateList.voteAssessThingMaintenace(\''+aData.objId+'\')"><span>参选对象</span></a>');

				//评审专家
				$(nRow).find("td:last").append('<a href="javascript:voteTemplateList.voteAssesseExpert(\''+aData.objId+'\')"><span>评审专家</span></a>');
				
				//查看
				$(nRow).find("td:last").append('<a href="javascript:voteTemplateList.viewVoteTemplate(\''+aData.objId+'\')"><span>查看</span></a>');
				
				//统计
				$(nRow).find("td:last").append('<a href="javascript:voteTemplateList.voteDataStatistic(\''+aData.objId+'\')" ><span>统计<span></a>');
				
				//合作媒体
				$(nRow).find("td:last").append('<a href="javascript:voteTemplateList.voteTemplateMedium(\''+aData.objId+'\')" ><span>合作媒体<span></a>');
				
				//投票管理
				$(nRow).find("td:last").append('<a href="javascript:voteTemplateList.voteAssessManager(\''+aData.objId+'\')" ><span>投票管理<span></a>');
				
				return nRow;
			}
		});
	}else{
		voteTemplateList.oTable.fnDraw();
	}
}

$(document).ready(function(){	
	voteTemplateList.getVoteTemplateList();
});