//定义文件全局变量 处理方法名重复问题
var updateMyInfo={};


$(document).ready(function(){  
	 
	//加载tabs
	var $tabs = $('#epsTabs').tabs({
		   add: function(event, ui) {
				$tabs.tabs('select', '#' + ui.panel.id);
				}
	}); 
	
	$("#tabs0").click(function(){
			$("#employeeInfo").empty();
			$("#userInfo").load("UserController.do?method=toShowView&objId="+PlatForm.user.objId);
	})
	
	$("#tabs1").click(function(){
		$("#userInfo").empty();
		if(PlatForm.user.emp&&PlatForm.user.emp.objId){
			$("#employeeInfo").load("EmployeeController.do?method=toCreateOrUpdate&isSelf=true&objId="+PlatForm.user.emp.objId);
		}
		else{
			alert("本账号没有关联的任何员工！");
		}
	})
	$("#tabs0").click();

})
