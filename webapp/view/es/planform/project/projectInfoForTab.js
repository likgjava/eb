var projectInfoForTab = {};

function getProjectByProjectId(id){
	$("#projectTab").find("li").removeClass();
	$("#cur_selectId").val(id);
	$("#tab"+id).addClass('ui-corner-top ui-state-focus ui-tabs-selected ui-state-active');
	$("#project_div").empty().loadPage($("#initPath").val()+"/ProjectController.do?method=toProjectInfoDetailForTab&objId="+id);
	$("#project_div").addClass('ui-tabs-panel ui-widget-content ui-corner-bottom');
}

function removeProjectTab(objId,projectName){//删除所选项目
	var num = $('#projectTab li').length;
	if(num=='1'){
		alert("至少保留一个项目！");
		return;
	}
	
	$("#tab"+objId).remove(); //删除tab页	
	$("#project_Ids").val('');	//修改input的值
	$("#project_Names").val('');   
	 var projectIds='';
	 var projectNames='';
	 $('#projectTab').find("li").each(function(i,n){
		projectNames += $(this).find('a').find('span').html()+',';
		projectIds += $(this).find('a').attr('id')+',';
	});
	$("#project_Ids").val(projectIds);
	$("#project_Names").val(projectNames); 
	
	
	$('#projectTab li>a:first').click();
}

$(document).ready(function(){
	$("#tabsHeaderContent").tabs();
	$('#projectTab li>a:first').click();//遍历所有TAB，并点击第一个TAB
	$("#project_Ids").val($('#projectTab li>a:first').attr("id")+",");
	$("#project_Names").val($('#projectTab li>a:first').find("span").html()+",");
	
	
	$("#moreProject").click(function(){
		$.epsDialog({
	        title:"项目信息",
	        url:$("#initPath").val()+"/ProjectController.do?method=toMoreProjectPageForTab",
	        width: 700,
	        height: 280,
	        isReload: false,
	        onClose: function(){
				planTemplateTask.refresh($('#projectTaskId').val())
	       	}
		});
	})
	
	
})