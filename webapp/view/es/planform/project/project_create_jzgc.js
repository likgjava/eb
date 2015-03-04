var project_create = {};
//移除包组
function removeSubProject(id){
	
	//提交
	if(confirm("确定删除标段?")){
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=removeSubProject', {subProjectId:id}, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			 $('#'+id).remove();
		});
	}
}

//修改包组
function updateSubProject(id,parentId){
	$.epsDialog({
        title:"修改标段",
        url:$("#initPath").val()+"/ProjectController.do?method=toSubProjectUpdateJZGC&subProjId="+id,
        width: 800,
        height: 280,
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
        width: 800,
        height: 280,
        isReload: false,
        onClose: function(){
       	}
	});
}


$(document).ready(function(){
	/*//将包组关联上页面公有的项目ID
	var parentId= $("#parentId").val();
	$("#parentId").val(parentId);*/
	
	//新增
	$('#subProjectSave').click(function(){
		$.epsDialog({
	        title:"新增标段",
	        url:$("#initPath").val()+"/ProjectController.do?method=toJZGCSubProjectCraete&parentId="+$("#parentId").val()+"&tenderType="+$('#tenderType').val(),
	        width: 800,
	        height: 280,
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