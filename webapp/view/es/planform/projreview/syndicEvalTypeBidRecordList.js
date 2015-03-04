var EvalTypeForm = {};
$(document).ready(function(){
	$('#syndicEvalTypeBidRecord_div button').click(function(){
		$('#syndicEvalTypeBidRecord_div button').removeClass('siControl_fastforward_right').addClass('siNext');
		$(this).removeClass('siNext').addClass('siControl_fastforward_right');
		var factorTypeId = $(this).attr('factorTypeId');
		var packId = $(this).attr('packId');
		var supplierId = $(this).attr('supplierId');
		var typeCode = $(this).attr('typeCode');
		var url = $('#initPath').val();
		url += '/EvalBidRecordController.do?method=toSyndicEvalBidRecordFormView';
		url += '&supplierId='+supplierId;
		url += '&packId='+packId;
		url += '&factorTypeId='+factorTypeId;
		url += '&typeCode=' +typeCode;
		$('#syndicEvalBidRecord_form_div').empty().loadPage(url);
	})
	if($('#syndicEvalTypeBidRecord_div button').length>0){
		$('#syndicEvalTypeBidRecord_div button:first').click();
	}
})
// 刷新指标列表
EvalTypeForm.refreshFactorList = function(){
	$('#syndicEvalTypeBidRecord_div button.siControl_fastforward_right').click();
}
