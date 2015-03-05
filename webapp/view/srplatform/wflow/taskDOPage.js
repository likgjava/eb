//包组区分JS方法、属性的唯一性
var taskDOPage={};

$(document).ready(function(){
	//alert("调用了JS====sysconfigtypeView.js");
	$("#viewHistoryTaskPage").hide();
	if($('#auditTaskId').val()!='' && $('#auditTaskId').val()!='-1'){
    	$.getJSON($('#initPath').val()+'/WorkFlowController.do?method=getTaskModel',{taskId:$('#auditTaskId').val(), includedProperties:'name'},function(json){
    		var bizId = json.taskModel.bizId;
    		var url = json.taskModel.formUrl;
    		//alert("bizId="+bizId+"||"+json.taskModel.parentBizId+"||");
    		if(bizId){//若bizId不为空
    			url += "&objId="+bizId;
    		}
    		$("#taskDoBizId").val(bizId);
    		$("#taskDoParentBizId").val(json.taskModel.parentBizId);
    		$("#viewPage").empty().loadPage($('#initPath').val()+"/"+url+"&taskId="+$('#auditTaskId').val());
    	});
    }
	$("#viewHistoryTaskPageButton").click(function(){
		
		//根据已有效果判断隐藏还是显示
		//alert("||"+$("#viewHistoryTaskPage").attr("style")+"||");
		if("display: none;"==$("#viewHistoryTaskPage").attr("style")){
			//alert("显示");
			$("#viewHistoryTaskPage").attr("style","display:block");
		}else{
			//alert("隐藏");
			$("#viewHistoryTaskPage").attr("style","display:none");
		}
		var $table=$("#viewHistoryTaskPageTable tr");
		var len=$table.length;
		//根据已有数据判断是否再去加载数据
		if(len <= 1){
			$.getJSON($('#initPath').val()+'/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId',{taskId:$('#auditTaskId').val(),bizId:$("#taskDoBizId").val(), includedProperties:'name'},function(json){
	    		//alert("JSON="+JSON.stringify(json.completedWorkTaskList));
	    		//若是枚举类型，则需要把数据添加到表单中
	    		$.each(json.completedWorkTaskList,function(i,completedWorkTask){
					
					var opinion = completedWorkTask.opinion;
					if(!completedWorkTask.opinion){
						opinion = "&nbsp;";
					}
					$("#viewHistoryTaskPageTable").append(
							"<tr id="+(len+1)+">" +
							"	<td align=\'center\'>" +
							completedWorkTask.taskName  +
							"	</td>"+
							"	<td align=\'center\'>" +
							opinion +
							"	</td>"+
							"	<td align=\'center\'>" +completedWorkTask.createTime+
							"	</td>" +
							"</tr>");
				});
	    	});
		}
	});
});

