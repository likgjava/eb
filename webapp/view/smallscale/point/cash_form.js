
var cashForm={};

$(document).ready(function(){
	
	$('#cashForm').validate();
    $("#cashDate").epsDatepicker();
    $("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/CashController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('cashForm', json.cash);
    	});
    }
	//返回
	$('#cashReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ExchangeController.do?method=manager");
	});
	//提交
	$('#cashSave').click(function(){
		//查询兑现积分是否超过有效积分
		var none=parseInt($('#nonce').val());
		var cashNumber=parseInt($('#cashNumber').val());
		if(cashNumber>none){
			alert('注意：兑现积分的额度不能大于当前有效积分！');
			return;
		}
		
		if(!$('#cashForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/CashController.do?method=cashSave', formToJsonObject('cashForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+"/ExchangeController.do?method=manager");
		});
	});
	
	//兑换额度框失去焦点
	$('#cashNumber').blur(function(){
		 var va=$('#cashNumber').val();
	     $('#cashMoney').val(va);   //为兑现金额框赋值
	     
	     //查询兑现积分是否超过有效积分
	     var none=parseInt($('#nonce').val());
		 var cashNumber=parseInt($('#cashNumber').val());
		 if(cashNumber > none){
			alert('注意：兑现积分的额度不能大于当前有效积分！');
			return;
		 }
	});
	

	//附件
	$("#cashFileDiv").loadPage($('#initPath').val()+'/AttachmentController.do?defineSelf=cashFile');
	//---------------附件
});
