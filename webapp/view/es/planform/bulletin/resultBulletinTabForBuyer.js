var mydesktop_supervise = {};
	
	mydesktop_supervise.getResultBulletin = function(subProject,i){
		$('#resultTabs').find("li").removeClass();
		$("#tab"+i).addClass('selected');
		$("#superviseTabDiv").empty();
		$('#superviseTabDiv').loadPage($('#initPath').val()+'/ResultBulletinController.do?method=toViewResultBulletin&projectId='+subProject);
	}
	
	$(document).ready(function(){
		$("#resultTabs").tabs();
		//遍历所有TAB，并点击第一个TAB
		$('#resultTabs').find("li").each(function(i,n){
			if(this.id.indexOf('tab')>-1&&i == 0){
				$(this).find(">a").click();
			}
		});
	});