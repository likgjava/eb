
var operLogsForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/OperLogsController.do?method=createOrUpdate', {objId:$('#objId').val()}, function(json){
    		json2Object('operLogsDetailForm',json.operLogs);
    	});
    }
	//返回
	$('#operLogsReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/OperLogsController.do');
	});
});