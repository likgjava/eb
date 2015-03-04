var goodsPriceAuditForm = {};

//审核行情  auditType: pass/nopass
goodsPriceAuditForm.audit = function(auditType){
	var goodsPrice = formToJsonObject("goodsPriceForm");
	goodsPrice.auditType = auditType;
	$.getJSON($("#initPath").val()+"/GoodsPriceController.do?method=updateAuditStatus",goodsPrice ,function(json){
		if(json.success){
			alert("操作成功！");
			goodsPriceAuditList.getPriceList();
			goodsPriceAuditForm.close();
		}else{
			alert("操作失败！");
		}
	})
}

//关闭
goodsPriceAuditForm.close = function(){
	$('.epsDialogClose').trigger('click');
}

$(document).ready(function(){
	
})