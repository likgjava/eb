function getDepDate(){
	if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/DepartmentController.do?method=createOrUpdate', {objId:$('#objId').val(),'includedProperties':'parent'}, function(json){
    		json2Object('departmentDetailForm',json.department);
    	});
    }
}
$(document).ready(function(){
	
	setTimeout('getDepDate()',100);
});