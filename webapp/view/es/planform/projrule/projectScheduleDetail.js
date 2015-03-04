
var projectScheduleForm={};
$(document).ready(function(){
	//获得页面显示
	$.getJSON($("#initPath").val()+'/ProjectScheduleController.do?method=getProjectSchedule&projectId='+$("#projectId").val(),function(json){
		
		jsonObjectToForm("projectScheduleForm", json.projectSchedule);
		})
	
	//返回
	$('#projectScheduleReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/view/es/planform/project/projectList.jsp");
	});
});