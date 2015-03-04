
var openBidForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/OpenBidController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('openBidDetailForm',json.openBid);
    	});
    }
	//返回
	$('#openBidReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/OpenBidController.do');
	});
});