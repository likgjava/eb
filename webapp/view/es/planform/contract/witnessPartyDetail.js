
var witnessPartyForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/WitnessPartyController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('witnessPartyDetailForm',json.witnessParty);
    	});
    }
	//返回
	$('#witnessPartyReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/WitnessPartyController.do');
	});
});