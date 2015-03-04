var moreProjectListPage = {};

$(document).ready(function(){
	var oldProjectIds =$("#project_Ids").val();
	var oldProjectIdsArray = oldProjectIds.split(',');
	
	$("input[name=project_check]").each(function(i,n){   //跳转到更多页面时选择上的项目画勾
		var index = jQuery.inArray($(n).attr('id'),oldProjectIdsArray);
		if(index!=-1){
			$(n).attr('checked','checked');
		}
		
	})
})

 $("#sureUnitUp").click(function(){
	 $("#project_Ids").val('');
	 $("#project_Names").val('');
	 var projectIds='';
	 var projectNames='';
	$("input[name=project_check]:checked ").each(function(i,n){
		projectNames += $(n).val() +',';
		projectIds += $(n).attr('id') + ',';
	});
	$("#project_Ids").val(projectIds);
	$("#project_Names").val(projectNames); 
	
	
	
	var projectIdsArray = projectIds.split(',');
	var projectNameArray = projectNames.split(',');
	$('#projectTab').find("li").remove();//去掉原来所有的tab页
	for(var i=0;i<projectIdsArray.length-1;i++){
		var html= "<li id='tab"+projectIdsArray[i]+"' class='ui-corner-top ui-state-default'>";
			html+="<a href='#' id='"+projectIdsArray[i]+"' onclick='getProjectByProjectId(\""+projectIdsArray[i]+"\")'><span>"+projectNameArray[i]+"</span></a>";
			html+="<button class='sysicon siExit' onclick='removeProjectTab(\""+projectIdsArray[i]+"\",\""+projectNameArray[i]+"\");'> </button>";
			html+="</li>";
			$('#projectTab').append(html);
	}

	$('#projectTab li>a:first').click();//遍历所有TAB，并点击第一个TAB
	$("#closeUnitUp").click();
	
 })




//关闭弹出层
$("#closeUnitUp").click(function(){
	$('#epsDialogCloseNoReload').click();
});