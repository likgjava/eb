
var evaSellerRecordForm={};

$(document).ready(function(){
	$('#evaSellerRecordForm').validate();
	//初始化页面数据 从evalBidRecordListView.jsp获取数据         start=========
	var count = $("#count").val();
	$("#supplierName").val($("#supplierName_"+count+"").html());
	$("#quoteSum").val($("#quoteSum_"+count+"").html());
	
	//初始化页面数据 从evalBidRecordListView.jsp获取数据         end=========
	if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/EvaSellerRecordController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('evaSellerRecordForm', json.evaSellerRecord);
    	});
    }
	//对于页面初始化完毕后的数据，进行显示处理
	$("#supplierNameView").html($("#supplierName").val());
	$("#quoteSumView").html($("#quoteSum").val());
	
	//返回
	$('#evaSellerRecordReturn').click(function(){
		$('#epsDialogCloseNoReload').click();
	});
	//提交
	$('#evaSellerRecordSave').click(function(){
		if(!$('#evaSellerRecordForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/EvaSellerRecordController.do?method=saveAllEvaSellerRecord', formToJsonObject('evaSellerRecordForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			$('#epsDialogCloseReload').click();
		});
	});

});
