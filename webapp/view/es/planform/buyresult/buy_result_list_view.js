
var buy_result_list={};
//查看winner记录
buy_result_list.viewWinner = function(i,resultId,subProjectId){
		$('#resultTabUl').find("li").removeClass();
		$('#tabs'+i).addClass('selected');
		$('#resultTabDiv').loadPage($('#initPath').val()+'/BuyWinnerController.do?method=toViewListBuyResult&subProjectId='+subProjectId+'&resultId='+resultId)
}
$(document).ready(function(){
	 $("#superviseDoDiv1").tabs();
	$('#resultTabUl a:first').click();
});