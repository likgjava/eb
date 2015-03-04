
var fundsConstituteForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/FundsConstituteController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('fundsConstituteDetailForm',json.fundsConstitute);
    	});
    }
	//返回
	$('#fundsConstituteReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/FundsConstituteController.do');
	});
});