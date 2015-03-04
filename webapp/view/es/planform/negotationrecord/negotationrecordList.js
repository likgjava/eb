
var negotationrecordList={};

negotationrecordList.success = function(){//加载成功后

	$("#negotationRecordGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">详情</a></span>' : function(btn,rowId,obj){
		btn.click(function(){
			//$('#conBody').loadPage($('#initPath').val()+'/NegotationRecordController.do?method=toNegotationRecordDetail&projectId='+$("#projectId").val()+'&subProjectId='+$("#subProjectId").val()+'&objId='+rowId);
			$.epsDialog({
			    title:'谈判记录详情',
			    url:$('#initPath').val()+'/NegotationRecordController.do?method=toNegotationRecordDetail&projectId='+$("#projectId").val()+'&subProjectId='+$("#subProjectId_").val()+'&objId='+rowId,
			    width: '800',
			    height: '420',
				    isReload:true,
			    onOpen: function(){ },
			    afterLoad: function(){ },
			    onClose: function(){ }
					});
		
		}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">修改</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				//$('#conBody').loadPage($('#initPath').val()+'/NegotationRecordController.do?method=toUpdateNegotationRecordDetail&projectId='+$("#projectId").val()+'&subProjectId='+$("#subProjectId").val()+'&objId='+rowId);
				$.epsDialog({
				    title:'修改谈判记录',
				    url:$('#initPath').val()+'/NegotationRecordController.do?method=toUpdateNegotationRecordDetail&projectId='+$("#projectId").val()+'&subProjectId='+$("#subProjectId_").val()+'&objId='+rowId,
				    width: '800',
				    height: '420',
					    isReload:true,
				    onOpen: function(){ },
				    afterLoad: function(){ },
				    onClose: function(){ }
						});
			
			}).appendTo(obj);
		},
		'<span><a href="#" class="abtn">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
			btn.click(function(){
				if(window.confirm('确定要删除?')){
					$.getJSON($('#initPath').val()+'/NegotationRecordController.do?method=removeNegotationRecord',{objId:rowId},function(json){
						if(json.result)alert(json.result);if(json.failure)return;
						$("#negotationRecordGrid").reload();//刷新
					});
				}	
			}).appendTo(obj);
		}
	});
}

negotationrecordList.before=function(){
	option={"subProjectId":$("#subProjectId_").val(),
			"projectId":$("#projectId").val()};
	$('#negotationRecordGrid').flexOptions({params:option});
	return true;
}

negotationrecordList.add = function(){//增加谈判记录
$.epsDialog({
    title:'增加谈判记录',
    url:$('#initPath').val()+'/NegotationRecordController.do?method=toNewNegotationRecordForm&subProjectId='+$("#subProjectId_").val()+'&projectId='+$("#projectId").val(),
    width: '800',
    height: '420',
	    isReload:true,
    onOpen: function(){ },
    afterLoad: function(){ },
    onClose: function(){ }
		});

}



$(document).ready(function(){
	
    $('#finshSaveButton').click(function(){
		$.getJSON($('#initPath').val()+'/NegotationRecordController.do?method=finishNegotationrecord&projectId='+$('#projectId').val(),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		});
	  });
})