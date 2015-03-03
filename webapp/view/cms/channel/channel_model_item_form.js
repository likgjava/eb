var channelModelItemForm={};

$(document).ready(function(){
	$('#channelModelItemForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/ChannelModelItemController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('channelModelItemForm', json.channelModelItem);
    		
    		var keyValArry = json.channelModelItem.keyVal.split(",");
    		for(var i = 0; i <  keyValArry.length; i++) {
    			var nrow = $('#keyValTemplate tr').clone(true);
    			$(nrow).find("td:eq(0) input").val(keyValArry[i].split("#")[0]);
    			if(keyValArry[i].split("#")[1] == undefined || keyValArry[i].split("#")[1] == "" ){
    				$(nrow).find("td:eq(1) input").val(keyValArry[i].split("#")[0]);
    			}else {
    				$(nrow).find("td:eq(1) input").val(keyValArry[i].split("#")[1]);
    			}
    			$(nrow).appendTo('#KeyAndValTB'); 
    		}
    	});
    }
    
	// 关闭
	$('#closeChannelModelItem').click(function(){
		$('#epsDialogClose').click();
	});
	
	// 提交
	$('#channelModelItemSave').click(function(){
		if(!$('#channelModelItemForm').valid()){alert('请正确填写表单!');return;}
		
		var keyValStr = "";
		// 将资键值按一定的格式封装好
		$('#KeyAndValTB tr').each(function(i,n) {
			if(keyValStr == "") {
				keyValStr = $(n).find("td:eq(0) input").val()+"#"+$(n).find("td:eq(1) input").val();
			}else {
				keyValStr = keyValStr + "," + $(n).find("td:eq(0) input").val()+"#"+$(n).find("td:eq(1) input").val();
			}
		});
		$('#keyVal').val(keyValStr);
		
		$.getJSON($('#initPath').val()+'/ChannelModelItemController.do?method=save', formToJsonObject('channelModelItemForm'), function(json){
			if(json.failure){alert(json.result);return;}
			$('#epsDialogClose').click();
		});
	});

//添加级别 
	$('#addKeyAndVal').click(function() {
		$('#keyValTemplate tr').clone(true).appendTo('#KeyAndValTB'); 
	});
	
// 删除级别
	$('[name=deleteKeyVal]').click(function() {
		$(this).parent().parent().remove();
	});
});
