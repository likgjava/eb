
var projectForm={};
$(document).ready(function(){
	 if($('#objId').val()!=''){
	    	$.getJSON($('#initPath').val()+'/ProjectController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				json2ObjectSpan('projectForm', json.project);  	
	    	});
	    }
	 
	//返回
	$('#projectReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ProjectController.do?method=toSearchProject&userType=agent");
	});
	
	
	
});