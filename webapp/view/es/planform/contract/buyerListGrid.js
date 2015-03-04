var agentList={};

//查询条件过滤
agentList.before=function(){
	var option={"buyerId":"null","buyerId_op":"!=","isOff":"1",
			"useStatus":"01",
			"auditStatus":"02"
}
	$('#agentListGrid').flexOptions({params:option});
	return true;
}

//加载数据成功之后调用的函数
agentList.success=function(){
	$("#agentListGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
		 var json = $("#agentListGrid").flexGetRowJsonById(rowId); //得到JSON对象
		btn.click(function(){
					$("#cuyerId").val(json["objId"]);
					$("#acquirer").val(json["orgName"]);
					$("#acquirerCorporate").val(json["company.contact"]);
					$("#telephone").val(json["company.tel"]);
					//关闭弹出层
					$('#epsDialogCloseNoReload').click();
		}).appendTo(obj);
	}
	});
}

$(document).ready(function(){
	
});



