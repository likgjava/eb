var mydesktop_supervise = {};
	
	mydesktop_supervise.getResultBulletin = function(subProject,i){
		$('#superviseDoDiv').find("li").removeClass();
		$("#tab"+i).addClass('selected');
		$("#superviseTabDiv").empty();
		$('#superviseTabDiv').loadPage($('#initPath').val()+'/ResultBulletinController.do?method=toBulletinAudit&projectId='+subProject);
	}

	$(document).ready(function(){
		$("#superviseDoDiv").tabs();
		//遍历所有TAB，并点击第一个TAB
		$('#superviseDoDiv').find("li").each(function(i,n){
			if(this.id.indexOf('tab')>-1&&i == 0){
				$(this).find(">a").click();
			}
		});
	});