var mydesktop_supervise = {};
	
	mydesktop_supervise.getResultBulletin = function(subProject,i){
		$('#resultTabUl').find("li").removeClass();
		$("#tab"+i).addClass('selected');
		$('#superviseTabDiv').loadPage($('#initPath').val()+'/ResultBulletinController.do?method=toViewResultBulletin&projectId='+subProject);
	}

	$(document).ready(function(){
		$("#superviseDoDiv").tabs();
		$('#resultTabUl a:first').click();
	});