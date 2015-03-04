var mydesktop_supervise = {};
	//获取招标公示
	mydesktop_supervise.getPublicitybulletin = function(){
		$('#superviseDoDiv').find("li").removeClass();
		$("#tab1").addClass('selected');
		$("#superviseTabDiv").empty();
		$('#superviseTabDiv').loadPage($('#initPath').val()+'/BulletinController.do?method=toResultPublicity&projectId='+$('#projectId').val());
	}
	//获取采购公告
	mydesktop_supervise.getPurBulletin = function(){
		$('#superviseDoDiv').find("li").removeClass();
		$("#tab0").addClass('selected');
		$("#superviseTabDiv").empty();
		var agent = $("#agencyId").val();
		var supervise = $("#supervisionId").val();
		if(agent!=""){
			$('#superviseTabDiv').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=toDraftPurBulletin&projectId='+$('#projectId').val());
		}else if(supervise!=""){
			$('#superviseTabDiv').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=toPurBulletinRead&projectId='+$('#projectId').val());
		}
	}
	
	//tab点击事件
	mydesktop_supervise.tabClick = function(id){
		$('#superviseDoDiv').find("li").removeClass();
		$("#"+id).addClass('selected');
	}
	$(document).ready(function(){
		
		//遍历所有TAB，并点击第一个TAB
		$('#superviseDoDiv').find("li").each(function(i,n){
			if(this.id.indexOf('tab')>-1&&i == 0){
				$(this).find(">a").click();
			}
		});
		
		$("#tab1").click(function(){
			$('#tab0').removeClass();
	   		$('#tab1').addClass('selected');
	   		mydesktop_agent.getPublicitybulletin();
		});
		
	});