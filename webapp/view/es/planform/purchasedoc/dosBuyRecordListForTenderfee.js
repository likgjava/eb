
var dosBuyRecordList={};

$(document).ready(function(){
	
	 $('#finshSaveButton').click(function(){
//		 alert($('#projectId').val());
    	$.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=finshConfirmDosBuyRecord&projectId='+$('#projectId').val(),function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		 planTemplateTask.clickMethod($("#projectTaskId").val()+"");
    		 planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		});
	  });
	
	dosBuyRecordList.viewDosBuyRecord = function(dosBuyRecordId){//查看
		$.epsDialog({
	        title:"查看购买记录",
	        url:$("#initPath").val()+"/DosBuyRecordController.do?method=toDosBuyRecordDetail&dosBuyRecordId="+dosBuyRecordId,
	        width: 650,
	        height: 400,
	        isReload: false
		});
	}
	
	dosBuyRecordList.updateDosBuyRecord = function(dosBuyRecordId){//修改
		$.epsDialog({
	        title:"修改购买记录",
	        url:$("#initPath").val()+"/DosBuyRecordController.do?method=toUpdateDosBuyRecordForm&dosBuyRecordId="+dosBuyRecordId,
	        width: 850,
	        height: 400,
	        isReload: false
		});
	}
	
	dosBuyRecordList.deleteDosBuyRecord = function(dosBuyRecordId){//删除
		if (window.confirm('确认删除？')) {
			$.getJSON($('#initPath').val()+'/DosBuyRecordController.do?method=removeDosBuyRecordById&dosBuyRecordId='+dosBuyRecordId,function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			});
		}
	}
	
	dosBuyRecordList.addDosBuyRecord = function(projectId){//新增
		$.epsDialog({
	        title:"添加购买记录",
	        url:$("#initPath").val()+"/DosBuyRecordController.do?method=toDosBuyRecordForm&isComplete=true&projectId="+projectId+"&isSubProject="+$('#isSubProject').val(),
	        width: 650,
	        height: 400,
	        isReload: false
		});
	}
	dosBuyRecordList.confirmDosBuyRecord = function(dosBuyRecordId){//确认
		$.epsDialog({
	        title:"确认购买记录",
	        url:$("#initPath").val()+"/DosBuyRecordController.do?method=toConfirmDosBuyRecordForm&isComplete=true&dosBuyRecordId="+dosBuyRecordId,
	        width: 650,
	        height: 400,
	        isReload: false
		});
	}
});