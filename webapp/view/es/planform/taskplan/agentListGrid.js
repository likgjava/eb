var agentList={};

//查询条件过滤
agentList.before=function(){
	var option={"agencyId":"null","agencyId_op":"!="}
	$('#agentListGrid').flexOptions({params:option});
	return true;
}

//加载数据成功之后调用的函数
agentList.success=function(){
	$("#agentListGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
		 var json = $("#agentListGrid").flexGetRowJsonById(rowId); //得到JSON对象
		btn.click(function(){
			var num = $('#num').val();
			var count_id = 0;
			$("input[name^=taskAgentId]").each(function(){
				if(num == count_id){
					$(this).val(json["orgInfo.objId"]);
				}
				count_id ++;
		     })
		     var count_name = 0;
		     $("input[name^=taskAgentName]").each(function(){
				if(num == count_name){
					$(this).val(json["orgInfo.orgName"]);
				}
				count_name ++;
		     })
			//关闭弹出层
			$('#epsDialogCloseNoReload').click();
		}).appendTo(obj);
	}
	});
}

$(document).ready(function(){
	
});



