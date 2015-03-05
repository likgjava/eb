
var userForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/UserController.do?method=getUserDetail', {objId:$('#objId').val()}, function(json){
    		json2Object('userDetailForm',json.user);
    	});
    }
	//返回
	$('#userReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/UserController.do');
	});
});