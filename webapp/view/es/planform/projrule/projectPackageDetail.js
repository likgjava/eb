
var projectPackageForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/ProjectPackageController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('projectPackageDetailForm',json.projectPackage);
    	});
    }
	//返回
	$('#projectPackageReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/ProjectPackageController.do');
	});
});