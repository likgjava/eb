
var puaseProjectList={};

puaseProjectList.success = function(){//加载成功后

	$("#puaseProjectGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">录入谈判记录</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').loadPage($('#initPath').val()+'/NegotationRecordController.do?method=toNegotationRecordForProject&projectId='+rowId);
			}).appendTo(obj);
		}
	});
}

$(document).ready(function(){
	
	
})