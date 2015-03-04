var project_create = {};


//查看包组
function viewSubProject(objId){
	$.epsDialog({
        title:"查看包组",
        url:$("#initPath").val()+"/ProjectController.do?method=getSubProjectForSupplier&projectId="+objId+"&tenderType="+$('#tenderType').val(),
        width: 666,
        height: 180,
        isReload: false,
        onClose: function(){
       	}
	});
}

project_create.finshSubProject = function(){
	$.getJSON($('#initPath').val()+'/ProjectController.do?method=finishSubProject', {projectId:$('#parentId').val()}, function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	});
}

$(document).ready(function(){
	/*//将包组关联上页面公有的项目ID
	var parentId= $("#parentId").val();
	$("#parentId").val(parentId);*/
})