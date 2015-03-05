
var employeeForm={};
employeeForm.getDate=function(){
	if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/EmployeeController.do?method=createOrUpdate', {objId:$('#objId').val(),includedProperties:"company,department,post"}, function(json){
    		json2ObjectSpan('employeeDetailForm',json.employee);
    	});
    }
}
$(document).ready(function(){
	
	setTimeout('employeeForm.getDate()',100);
});