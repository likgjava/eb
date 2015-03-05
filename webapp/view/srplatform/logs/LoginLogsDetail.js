
var loginLogsForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/LoginLogsController.do?method=createOrUpdate', {objId:$('#objId').val()}, function(json){
    		json2Object('loginLogsDetailForm',json.loginLogs);
    	});
    }
	//返回
	$('#loginLogsReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/LoginLogsController.do');
	});
});