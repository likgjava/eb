$(document).ready(function(){
	$("#department").val($("#projDepartMent").val());
	//分配执行部门点击事件
	$("#sure").click(function(){
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveProjectDepart',formToJsonObject('assignDeptForm'),function(json){
			if(json.result){
				//点击分配执行部门菜单
				checkProjectMenu('menu_assign_dept');
			}	
			if(json.failure)return;
		});
	})
})