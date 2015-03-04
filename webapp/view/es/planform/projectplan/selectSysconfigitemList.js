var sysConfigItemList={};
sysConfigItemList.before=function(){
	var option={
		'sysConfigType.objId':$('#sysConfigTypeId').val()
	}
	$('#sysConfigItemGrid').flexOptions({params:option});
	return true;
}
sysConfigItemList.success=function(){
	$("#sysConfigItemGrid").flexAddOptionStr({
		'<button class="enable" type="button"><span>选择</span></button>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#sysConfigItemGrid").flexGetRowJsonById(rowId);
				$("#bizRuleNameId").val(json.name);
				$("#bizRuleId").val(json.objId);
				$("#rule_span").empty().append(json.code);
				$('#project_rule .epsDialogCloseReload').click();
			}).appendTo(obj);
		}
	});
}
$(document).ready(function(){
	$("#_close").click(function(){
		$('#project_rule .epsDialogCloseReload').click();
	})
	$("#_clean").click(function(){
		$("#bizRuleId").val("");
		$("#bizRuleNameId").val("");
		$("#rule_span").empty().append("");
		$('#project_rule .epsDialogCloseReload').click();
	})
});

