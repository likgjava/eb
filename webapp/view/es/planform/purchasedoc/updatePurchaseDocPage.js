
var updatePurchaseDocPage = {};

updatePurchaseDocPage.updatePurchaseDoc = function(objId)
{
$.epsDialog({
    title:'录入',
    url:$('#initPath').val()+'/view/es/planform/purchasedoc/purchaseDocForm.jsp?objId='+objId,
    width: '900',
    height: '200',
    isReload:false,
    onClose: function(){
	
  	}
});	
}


$(document).ready(function(){

});

//查看历史
$('#historyId').click(function(){
	$("#historyView").loadPage($('#initPath').val()+"/CompletedWorkTaskController.do?method=getCompletedWorkTaskByBizId&bizId="+$("#purchaseDocId").val()+'&taskType=03');
});
