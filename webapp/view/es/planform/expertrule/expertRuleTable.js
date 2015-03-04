var expertRule_table = {};

expertRule_table.getExpertRule=function(subProjectId,projectId){
//	$('#subProjectTabDiv').loadPage($('#initPath').val()+'/view/es/planform/expertrule/experts_drawn.jsp?objId='+objId+'&projectId='+projectId);
	$('#subProjectTabDiv').empty().loadPage($('#initPath').val()+'/ExpertruleController.do?method=getExpertRuleBySubProjectId&isAJAX=false&isComplete=true&subProjectId='+subProjectId+'&projectId='+projectId);
}


$(document).ready(function(){
	alert(1)
	$('#superviseDoDiv a').click(function(){
		$('#superviseDoDiv a').parent().removeClass();
		$(this).parent().addClass('selected');
	});
	$('#superviseDoDiv a:first').click();
});