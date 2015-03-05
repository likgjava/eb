
var dictionaryForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/DictionaryController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('dictionaryDetailForm',json.dictionary);
    	});
    }
	//返回
	$('#dictionaryReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/DictionaryController.do');
	});
});