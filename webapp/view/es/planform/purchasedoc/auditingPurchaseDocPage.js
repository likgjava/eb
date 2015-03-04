
var auditingPurchaseDocPage = {};

auditingPurchaseDocPage.auditingPurchaseDoc = function(objId)
{
$.epsDialog({
    title:'审核采购文件',
    url:$('#initPath').val()+'/view/es/planform/purchasedoc/auditingPurchaseDocPageForm.jsp?objId='+objId,
    width: '900',
    height: '200',
    isReload:false,
    onClose: function(){
  	}
});	
}


$(document).ready(function(){
	$("#PurchaseDocPageView").loadPage($('#initPath').val()+'/view/es/planform/purchasedoc/auditingPurchaseDocPageForm.jsp');
});

