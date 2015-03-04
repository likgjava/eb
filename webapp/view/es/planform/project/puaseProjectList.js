
var puaseProjectList={};

puaseProjectList.success = function(){//加载成功后

	$("#puaseProjectGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">进入</a></span>' : function(btn,rowId,obj){
			btn.click(function(){
				$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toPuaseProject&fromType=fromLeft&projectId='+rowId);
			}).appendTo(obj);
		}
	});
}

$(document).ready(function(){
	
	
})