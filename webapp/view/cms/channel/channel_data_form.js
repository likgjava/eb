var channelDataForm={};

$(document).ready(function(){
	$('#channelDataForm').validate();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ChannelDataController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('channelDataForm', json.channelData);
    	});
    }
    
	// 返回
	$('#channelDataReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ChannelDataController.do");
	});
	
	// 提交
	$('#channelDataSave').click(function(){
		if(!$('#channelDataForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/ChannelDataController.do?method=save', formToJsonObject('channelDataForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#conBody').loadPage($('#initPath').val()+'/ChannelDataController.do');
		});
	});

});
