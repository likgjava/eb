var SyndicEvalBidRecordList={};
// 注册投标单位列表点击事件
SyndicEvalBidRecordList.initSupplierListClickEvent = function(){
	$('#supplier_list li').click(function(){
		if($(this).hasClass('selected')){
			return false;
		}else{
			$('#supplier_list li[class=selected]').removeClass('selected');
			$(this).addClass('selected');
			var signUprecordId = $(this).attr("signUprecordId");
			SyndicEvalBidRecordList.loadFactorList(signUprecordId);
		}
	})
}
// 加载指标列表
SyndicEvalBidRecordList.loadFactorList = function(signUprecordId){
	var url = $('#initPath').val();
	url += "/BidController.do?method=toUnderLineBidForm";
	url += "&signUprecordId="+signUprecordId;
	url += "&projectId="+$("#projectId").val();
	$('#syndicEvalRecordList').empty().loadPage(url);
}
$(document).ready(function(){
	SyndicEvalBidRecordList.initSupplierListClickEvent();//加载列表事件
	$('#supplier_list li:first').click();// 默认点击第一个
})