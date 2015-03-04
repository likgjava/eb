
var resProjectInfo = {};

resProjectInfo.back = function(){
	var from = $('#from').val();
	if (from=='auditList') {
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/resproject/resProjectListForAudit.jsp');
	}else{
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/resproject/resProjectInfoList.jsp');
	}
//	if($('#parentId').val()==''){
//		$('#conBody').loadPage($('#initPath').val()+'/ResProjectController.do?method=loadResProjectManagePage');
//	}else{
//		 $('#conBody').loadPage($('#initPath').val()+"/ResProjectController.do?method=loadDisassemblePage&resProjectId="+$('#parentId').val());
//	}
}
$(document).ready(function(){

	$('#back').click(resProjectInfo.back);
});