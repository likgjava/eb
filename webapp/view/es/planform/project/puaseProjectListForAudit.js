
var puaseProjectList={};

puaseProjectList.success = function(){//加载成功后

	$("#puaseProjectGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">审核</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').loadPage($('#initPath').val()+'/ProjectExceptionApplyController.do?method=toAuditProjectException&projectId='+rowId);
			}).appendTo(obj);
		}
	});
}

$(document).ready(function(){
	
	
})