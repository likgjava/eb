var SyndicEvalBidRecordList={};
// 注册投标单位列表点击事件
SyndicEvalBidRecordList.initSupplierListClickEvent = function(){
	$('#supplier_list li').click(function(){
		if($(this).hasClass('selected')){
			return false;
		}else{
			$('#supplier_list li[class=selected]').removeClass('selected');
			$(this).addClass('selected');
			var supplierId = $(this).attr("supplierId");
			SyndicEvalBidRecordList.loadFactorList(supplierId);
		}
	})
}
// 加载指标列表
SyndicEvalBidRecordList.loadFactorList = function(supplierId){
	var url = $('#initPath').val();
	url += "/EvalBidRecordController.do?method=getSyndicEvalTypeBidRecord";
	url += "&supplierId="+supplierId;
	url += "&packId="+SyndicEvalBidRecordList.packId;
	url += "&factorTypeId="+SyndicEvalBidRecordList.factorTypeId;
	$('#syndicEvalRecordList').empty().loadPage(url);
}
$(document).ready(function(){
	SyndicEvalBidRecordList.factorTypeId = $('#factor_type_id').val();
	SyndicEvalBidRecordList.packId = $('#project_pack_id').val();
	SyndicEvalBidRecordList.initSupplierListClickEvent();//加载列表事件
	$('#supplier_list li:first').click();// 默认点击第一个
})