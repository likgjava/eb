
var bailRecordList={};

$(document).ready(function(){
	
	 $('#finshSaveButton').click(function(){
//		 alert($('#projectId').val());
    	$.getJSON($('#initPath').val()+'/BailRecordController.do?method=finshConfirmBailRecord&projectId='+$('#projectId').val(),function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		 planTemplateTask.clickMethod($("#projectTaskId").val()+"");
    		 planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		});
	  });
	
	bailRecordList.viewBailRecord = function(bailRecordId){//查看
		$.epsDialog({
	        title:"查看保证金",
	        url:$("#initPath").val()+"/BailRecordController.do?method=toViewBailRecord&bailRecordId="+bailRecordId,
	        width: 650,
	        height: 400,
	        isReload: false
		});
	}
	
	bailRecordList.deleteBailRecord = function(bailRecordId){//删除
		if (window.confirm('确认删除？')) {
			$.getJSON($('#initPath').val()+'/BailRecordController.do?method=removeBailRecord&bailRecordId='+bailRecordId,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			});
		}
	}
	
	bailRecordList.addBailRecord = function(projectId){//新增
		$.epsDialog({
	        title:"补录保证金",
	        url:$("#initPath").val()+"/view/es/planform/bid/bailRecordFormForAgent.jsp?projectId="+projectId,
	        width: 750,
	        height: 400,
	        isReload: false
		});
	}
	bailRecordList.confirmBailRecord = function(bailRecordId){//确认
		$.epsDialog({
	        title:"确认保证金",
	        url:$("#initPath").val()+"/BailRecordController.do?method=toConfirmBailRecordForm&isComplete=true&bailRecordId="+bailRecordId,
	        width: 650,
	        height: 400,
	        isReload: false
		});
	}
});