
var meetRoomForm={};

$(document).ready(function(){
	$('#meetRoomForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('meetRoomForm', json.meetRoom);
    	});
    }
	//返回
	$('#meetRoomClose').click(function(){
		$('#epsDialogClose').click();
	});
	//提交
	$('#meetRoomSave').click(function(){
		if(!$('#meetRoomForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/MeetRoomController.do?method=save', formToJsonObject('meetRoomForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#meetRoomClose').click();
		});
	});

});
