
var dealForm={};

$(document).ready(function(){
	$('#dealForm').validate();
     			$("#dealDate").epsDatepicker();
     			$("#createTime").epsDatepicker();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/DealController.do?method=createOrUpdate',{objId:$('#objId').val(), includedProperties:''},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
    		jsonObjectToForm('dealForm', json.deal);
    	});
    }
	//返回
	$('#dealReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/ExchangeController.do?method=manager");
	});
	//提交
	$('#dealSave').click(function(){
		//查询交易积分是否超过有效积分
		var none=parseInt($('#nonce').val());
		var dealNumber=parseInt($('#dealNumber').val());
		if(dealNumber>none){
			alert('注意：交易的积分不能大于当前有效积分！');
			return;
		}
		
		if(!$('#dealForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/DealController.do?method=dealSave', formToJsonObject('dealForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/ExchangeController.do?method=manager');
		});
	});
	
	//查询交易积分是否超过有效积分
	$('#dealNumber').blur(function(){
		var none=parseInt($('#nonce').val());
		var dealNumber=parseInt($('#dealNumber').val());
		if(dealNumber>none){
			alert('注意：交易的积分不能大于当前有效积分！');
			return;
		}
	});
	
	//补全用户
	$("#toUser").autocomplete($('#initPath').val() + '/UserController.do?method=getObjectQuery&queryColumns=usName', {
		extraParams:{},//查询条件  例如    {objId:111}
		matchColumn:'usName',//作为查询显示, 被选中之后匹配的列
		minChars: 0,
		max: 8,
		autoFill: false,
		mustMatch: false,
		scrollHeight: 220,
		formatItem: function(data, i, total) {
			return data.usName;
		},
		formatMatch: function(data, i, total) {
			alert(data.objId);
			return data.usName;
		},
		formatResult: function(data) {
			return data.usName;
		}
	});
});

