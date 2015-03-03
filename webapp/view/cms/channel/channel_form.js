var channelForm={};

$(document).ready(function(){
	$('#channelForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ChannelController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('channelForm', json.channel);
    	});
    }
    
	// 返回
	$('#channelReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ChannelController.do");
	});
	
	// 提交
	$('#channelSave').click(function(){
		if(!$('#channelForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ChannelController.do?method=save', formToJsonObject('channelForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ChannelController.do');
		});
	});

});
