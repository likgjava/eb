
var userForm={};
userForm.getDate=function(){
	if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/UserController.do?method=getUserByUsName', {'usName':$('#objId').val(),'includedProperties':'emp,emp.company,emp.department'}, function(json){
    		json2Object('userDetailForm',json.user);
    	});
    }
}
$(document).ready(function(){
	//alert($('#objId').val())
	
	//返回
	$('#userReturn').click(function(){
		$('#epsDialogClose').click();
	});
	
	setTimeout('userForm.getDate()',100);
});