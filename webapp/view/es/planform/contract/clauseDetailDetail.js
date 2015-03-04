
var clauseDetailForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ClauseDetailController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('clauseDetailDetailForm',json.clauseDetail);
    	});
    }
	//返回
	$('#clauseDetailReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ClauseDetailController.do');
	});
});