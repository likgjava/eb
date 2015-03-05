
var companyForm={};
companyForm.getDate = function(){
	if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/CompanyController.do?method=createOrUpdate', {objId:$('#objId').val(),'includedProperties':'town,town.parent,town.parent.parent,parent'}, function(json){
    		json2Object('companyDetailForm',json.company);
    		if(json.company.town){
    			var town = json.company.town;
    			var district = town.parent.parent.name+town.parent.name+town.name;
    			$("#district").html(district);
    		}
    	});
    }
}
$(document).ready(function(){
	
	setTimeout('companyForm.getDate()',100);
	
});