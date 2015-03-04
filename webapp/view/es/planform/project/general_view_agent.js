var general_agent = {};
	//项目实施状态维护
	general_agent.viewProjImplStatus = function(objId){
		$('#agentDoDiv').find("li").removeClass();
		$("#tab9").addClass('selected');
		$("#agentTabDiv").empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toUpdateProjImplStatus&projectId='+objId);
	}
	//设置项目规则
	general_agent.viewProjectRule = function(objId){
		$('#agentDoDiv').find("li").removeClass();
		$("#tab6").addClass('selected');
		$("#agentTabDiv").empty().loadPage($('#initPath').val()+'/ProjProcessRuleController.do?method=toProjProcessRule&projectId='+objId);
	}
	//标段显示页面
	general_agent.viewSubProject = function(objId){
		$('#agentDoDiv').find("li").removeClass();
		$("#tab0").addClass('selected');
		$('#agentTabDiv').loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectCraete&projectId='+objId);
	}
	//显示采购公告
	general_agent.viewPurBulletin = function(objId){
		$('#agentDoDiv').find("li").removeClass();
		$("#tab1").addClass('selected');
		$('#agentTabDiv').loadPage($('#initPath').val()+'/BulletinController.do?method=toDraftPurBulletin&projectId='+objId);
	}
	
	//查看项目对应 的参与投标单位
	general_agent.viewSignupRecord = function(objId){
		$('#agentDoDiv').find("li").removeClass();
		$("#tab2").addClass('selected');
		$('#agentTabDiv').loadPage($('#initPath').val()+'/SignUprecordController.do?method=getSignupRecordList&projectId='+objId);
	}
	//查看项目对应 的投标单位质疑信息
	general_agent.viewOppu = function(objId){
		$('#agentDoDiv').find("li").removeClass();
		$("#tab3").addClass('selected');
		$('#agentTabDiv').loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqAgencyList.jsp?projectId='+objId+'&consBuyerName=01');
	}
	//开标小组
	general_agent.openBidGroup = function(objId){
		$('#agentDoDiv').find("li").removeClass();
		$("#tab4").addClass('selected');
		$('#agentTabDiv').loadPage($('#initPath').val()+'/WorkGroupController.do?method=toWorkGroupForProject&groupType=03&projectId='+objId);
	}
	//评标委员会
	general_agent.evaluateBidGroup = function(objId){
		$('#agentDoDiv').find("li").removeClass();
		$("#tab5").addClass('selected');
		$('#agentTabDiv').loadPage($('#initPath').val()+'/ExpertruleController.do?method=getExpertByProjectId&fromTab=yes&groupType=02&projectId='+objId);
	}
	//标评室
	general_agent.viewBidRoom = function(objId){
		$('#agentDoDiv').find("li").removeClass();
		$("#tab7").addClass('selected');
		$("#agentTabDiv").empty().loadPage($('#initPath').val()+'/view/es/planform/projrule/meetRoomShowView.jsp?projectId='+objId);
	}
	general_agent.viewProTime = function(objId){
		$('#agentDoDiv').find("li").removeClass();
		$("#tab8").addClass('selected');
		$('#agentTabDiv').loadPage($('#initPath').val()+'/ProjectController.do?method=toUpdateProjectTime&projectId='+objId);
	}
	$(document).ready(function(){
		$("#agentTabDoDiv").empty();
		//遍历所有TAB，并点击第一个TAB
		$('#agentDoDiv').find("li").each(function(i,n){
			if(this.id.indexOf('tab')>-1&&i == 0){
				$(this).find(">a").click();
			}
		});
	});