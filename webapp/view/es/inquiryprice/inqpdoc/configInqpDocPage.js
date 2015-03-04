
var configPurchaseDocPage = {};

configPurchaseDocPage.configPurchaseDoc = function(objId)
{
$.epsDialog({
    title:'确认采购文件',
    url:$('#initPath').val()+'/view/es/planform/purchasedoc/configPurchaseDocPageForm.jsp?objId='+objId,
    width: '900',
    height: '200',
    isReload:false,
    onClose: function(){
  	}
});	
}


$(document).ready(function(){
   $("#PurchaseDocPageView").loadPage($('#initPath').val()+'/view/es/inquiryprice/inqpdoc/configInqpDocPageForm.jsp');
});
