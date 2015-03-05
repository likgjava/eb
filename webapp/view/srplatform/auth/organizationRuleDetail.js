
var orgnizationRuleForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/OrganizationRuleController.do?method=createOrUpdate', {objId:$('#objId').val()}, function(json){
    		json2Object('orgnizationRuleDetailForm',json.organizationRule);
    	});
    }
	//返回
	$('#orgnizationRuleReturn').click(function(){
		$('#content').loadPage($('#initPath').val()+'/OrganizationRuleController.do');
	});
});