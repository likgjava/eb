
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
	
})