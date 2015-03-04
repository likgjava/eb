
var ServiceSupplierForm={};

//立即订购
ServiceSupplierForm.purchaseNow=function(objId,ctrl) {
	$.getJSON($('#initPath').val()+"/ServiceSubscribeController.do?method=isServcieSubscribed", {"serviceId":objId} , function(json){
		if(json.result) {
			if(json.result > 0) {
				$(ctrl).parent().html('已订阅...')
			} else {
				$('#conBody').loadPage($('#initPath').val() + '/ServiceBaseController.do?method=toServiceSubscribeForm&objId='+objId);
			}
		}
	});
}

//取消订阅
ServiceSupplierForm.cancelSubscribe=function(objId) {
	if(window.confirm("将删除该订阅信息,确定要取消此服务的订阅?")){
		$.getJSON($('#initPath').val()+"/ServiceSubscribeController.do?method=remove", {"objId":objId} , function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			alert('取消成功');
			$('#conBody').loadPage($('#initPath').val()+'/ServiceBaseController.do?method=toServiceSubscribeList');
		});
	}
}

//tip提示
ServiceSupplierForm.getFee=function(serviceId,ctrl) {
	//获取各个级别最小时长的计费(第一次请求，其他次不再请求)
	var feeStr = $(ctrl).find('div[class=unity-arrow-box]').text();
	var htmlStr = '';
	if(!feeStr) {
		$.getJSON($('#initPath').val()+"/ServiceBaseController.do?method=getFeeByServiceId", {"serviceId":serviceId} , function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			$.each(json.feeList,function(i,n){
				if(n.memberClass) {
					htmlStr += '<p>'+n.memberClass.memberClassName+'</p>' + '<p><strong>'+formatAmount(n.amount,2)+'元'+'</strong></p>';
				}else {
					htmlStr += '<p>网站会员</p>' + '<p><strong>'+formatAmount(n.amount,2)+'元'+'</strong></p>';
				}
			})
			if(!htmlStr) {
				$(ctrl).parent().find('div[class=unity-arrow-box]').html('暂时没有计费信息');
			} else {
				$(ctrl).parent().find('div[class=unity-arrow-box]').html(htmlStr);
			}
		});
	}
}

$(document).ready(function(){
	var $tabs=$("#epsTabs").tabs();
	
	//我的服务-跳转到我已订阅的服务列表页面
	$('#tabs_myservice').click(function(){
		$('#myService').loadPage($('#initPath').val() + '/ServiceBaseController.do?method=toMySubscribeList');
	})
	
	//各会员级别计费信息鼠标悬停
	$("div[id^=fee_]").hover(
			function(){
				//显示各会员计费信息
				ServiceSupplierForm.getFee($(this).attr('id').replace('fee_',''),$(this));
				$(this).find('div[class=unity-btn-down]').addClass('unity-btn-zindex');
				$(this).find('div[class=unity-arrow-box]').css('display','block');
			}
			,
			function(){
				$(this).find('div[class=unity-btn-down]').removeClass('unity-btn-zindex');
				$(this).find('div[class=unity-arrow-box]').css('display','none');
			}
	)
});
