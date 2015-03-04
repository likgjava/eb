
var dosBuyRecordList={};

$(document).ready(function(){
	
	dosBuyRecordList.viewDosBuyRecord = function(dosBuyRecordId){//查看
		$.epsDialog({
	        title:"查看购买记录",
	        url:$("#initPath").val()+"/DosBuyRecordController.do?method=toDosBuyRecordDetail&dosBuyRecordId="+dosBuyRecordId,
	        width: 650,
	        height: 400,
	        isReload: false
		});
	}
});