/*
 * 品牌审核页面
 * created by yucy
 */
var brandAdudit = {}

//审核商品
brandAdudit.audit = function(auditStatus){
	var brandForm = formToJsonObject("brandForm");
	brandForm.auditStatus = auditStatus;
	$.getJSON($("#initPath").val()+"/GoodsBrandController.do?method=updateBrandAuditStatus",
			brandForm,
			function(json){
		if(json.success){
			alert("操作成功！");
			$("button[name=historyBackBtn]").click();
		}
	})
}

//审核变更
brandAdudit.auditChange = function(auditType){
	$.getJSON($("#initPath").val()+"/GoodsBrandController.do?method=updateChangeAuditStatus",{"brandId":$("#objId").val(),"auditType":auditType},	function(json){
		if(json.success){
			alert("操作成功！");
			$("button[name=historyBackBtn]").click();
		}
	})
}


$(document).ready(function(){
})

