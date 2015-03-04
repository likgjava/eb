
var openBidForm={};

$(document).ready(function(){
	$('#openBidForm').validate();
     			$("#openBStartTime").epsDatepicker();
     			$("#openBEndTime").epsDatepicker();
     			$("#createTime").epsDatepicker();
     			$("#updateTime").epsDatepicker();
    if($('#openBid').val()!=''){
    	$.getJSON($('#initPath').val()+'/OpenBidController.do?method=createOrUpdate',{objId:$('#openBid').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('openBidForm', json.openBid);
    	});
    }
	//返回
	$('#openBidReturn').click(function(){
		$("#projectContent").empty().loadPage($('#initPath').val()+"/view/es/planform/projreview/openBidForm.jsp");
	});
	//提交
	$('#openBidSave').click(function(){
		if(!$('#openBidForm').valid()){alert('请正确填写表单!');return;}
		if($("#openBStartTime").val()>$("#openBEndTime").val()){alert('开标结束时间应大于开标开始时间');return;}
		$.getJSON($('#initPath').val()+'/OpenBidController.do?method=save', formToJsonObject('openBidForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$("#projectContent").empty().loadPage($('#initPath').val()+'/OpenBidController.do?method=toOpenBid&projectId='+$("#projectId").val());
		});
	});

});
