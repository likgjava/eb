var mydesktop_supervise = {};
	
	mydesktop_supervise.getResultBulletin = function(subProject,i){
		$('#resultTabUl').find("li").removeClass();
		$("#tab"+i).addClass('selected');
		$('#superviseTabDiv').loadPage($('#initPath').val()+'/ResultPublicityController.do?method=toPublicityAudit&projectId='+subProject);
	}

	$(document).ready(function(){
		$('#resultTabUl a:first').click();
	});