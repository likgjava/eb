
var meetRoomForm={};
$(document).ready(function(){
	if($('#objId').val()!=''){
    	var queryColumns=[];
    	$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
    		if(json.result)alert(json.result);if(json.failure)return;
    		json2Object('meetRoomDetailForm',json.meetRoom);
    	});
    }
	//返回
	$('#meetRoomReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/MeetRoomController.do');
	});
});