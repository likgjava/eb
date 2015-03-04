
var own_notice_list={};
own_notice_list.receipt = function(i,objId){
	$('#resultTabUl').find("li").removeClass();
		$('#tabs'+i).addClass('selected');
		$('#resultTabDiv').loadPage($('#initPath').val()+'/NoticeController.do?method=toReceipt&objId='+objId);
}
$(document).ready(function(){
	$("#superviseDoDiv").tabs();
	$('#resultTabUl a:first').click();
});
