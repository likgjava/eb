var project_create = {};
//移除包组
function removeSubProject(id){
	
	//提交
	if(confirm("确定移除包组?")){
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=removeProjectAndReq', {objId:id}, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			 $('#'+id).remove();
		});
	}
}

//修改包组
function updateSubProject(id,parentId){
	$.epsDialog({
        title:"修改包组",
        url:$("#initPath").val()+"/ProjectController.do?method=toSubProjectUpdate&subProjId="+id,
        width: 550,
        height: 300,
        isReload: false,
        onClose: function(){
			planTemplateTask.refresh($('#projectTaskId').val())
       	}
	});
}

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
	
    $('#finshSaveButton').click(function(){
    	$.getJSON($('#initPath').val()+'/ProjectController.do?method=checkProjectBudget', {projectId:$('#parentId').val()}, function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		if (json.checkResult=='true') {
    			if (window.confirm('该项目预算拆分完整！是否完成分包？')) {
    				project_create.finshSubProject();
    			}
    		}else{
    			if (window.confirm('该项目预算拆分不完整！是否完成分包？')) {
    				project_create.finshSubProject();
    			}
    		}
		});
    });
	
	//新增
	$('#subProjectSave').click(function(){
		$.epsDialog({
	        title:"新增包组",
	        url:$("#initPath").val()+"/ProjectController.do?method=toSubProjectCraete&parentId="+$("#parentId").val()+"&tenderType="+$('#tenderType').val(),
	        width: 800,
	        height: 300,
	        isReload: false,
	        onClose: function(){
			   	var workFlowTaskId = $('[id=auditTaskId]').val();
				if(undefined == workFlowTaskId || null == workFlowTaskId || '' == workFlowTaskId){
					planTemplateTask.refresh($('#projectTaskId').val())
				}else{
					$("#myDesktop").click();
				}
	       	}
		});
	});
})