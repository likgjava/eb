
var noticeForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/NoticeController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		var noticeType=json.notice.noticeType;
    		if(noticeType=="00"){
    			noticeType="成交通知书";
	 		}else if(noticeType=="01"){
	 			noticeType="结果通知书";
	 		}
    		json.notice.noticeType=noticeType;
    		json2Object('noticeDetailForm',json.notice);
    	});
    }
	//返回
	$('#noticeReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/NoticeController.do');
	});
});