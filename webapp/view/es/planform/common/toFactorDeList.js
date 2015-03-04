var factorDeList={};
function before(){
	var option={
		"factortypeDe.objId":$("#_factor_type_id").val()
	}
	$('#factorDeGrid').flexOptions({params:option});
	return true;
}
function success(){
	$("#factorDeGrid").flexAddOptionStr({
		'<button class="enable" type="button"><span>选择</span></button>' : function(btn,rowId,obj){
			btn.click(function(){
				var json = $("#factorDeGrid").flexGetRowJsonById(rowId); 
				SelectFactorDeList.returnValue(rowId,json.factorName,true);
			}).appendTo(obj);
		}
	});
}

$("#CLEAR").click(function(){
	SelectFactorDeList.returnValue("","",true);
})
$("#RETURN").click(function(){
	if(null != $("#_epsDialog_id").val() && "" != $("#_epsDialog_id").val()){
		$('#'+$("#_epsDialog_id").val()+' .epsDialogCloseReload').click();
	}else{
		$('#epsDialogCloseNoReload').click();
	}
})