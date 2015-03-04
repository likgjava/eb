var ServiceListForm={};

//操作
ServiceListForm.view = function(objId,viewName,domainType,serviceBaseId){
	if("showView" == viewName){
		if(domainType == 'ServiceBase'){
			$.epsDialog({
				title:"服务详细信息",
				width: 800,
				height: 500,
				url:$('#initPath').val()+'/ServiceBaseController.do?method=toShowView&viewType=allInfo&objId='+objId
			});
		}else if(domainType == 'MemberClass'){
			$.epsDialog({
				title:"会员级别详细信息",
				width: 700,
				height: 400,
				url:$('#initPath').val()+'/MemberClassController.do?method=toShowView&objId='+objId
			});
		}
	}else if("updateView" == viewName){
		if(domainType == 'ServiceBase'){
			$.epsDialog({
				title:"修改服务信息",
				width: 800,
				height: 500,
				url:$('#initPath').val()+'/ServiceBaseController.do?method=toCreateOrUpdate&objId='+objId,
				onClose: function(){
					ServiceListForm.reload();//刷新列表
				}
			});
		}else if(domainType == 'ServiceCharging'){
			var url = $('#initPath').val()+'/ServiceChargingController.do?method=toCreateOrUpdate&dialogId=addServiceChargingDialogId&objId='+objId+'&serviceBaseId='+serviceBaseId;
			$.epsDialog({
				id:'addServiceChargingDialogId',
				title:'修改服务计费标准',
				url:url,
				width: '700',
				height: '400',
				onClose: function(){
				  $("#stardFee"+serviceBaseId).loadPage($('#initPath').val()+"/ServiceChargingController.do?method=toServiceChargingListView&serviceBaseId="+serviceBaseId);
				}
			});
		}else if(domainType == 'ServiceGroup'){
			var url = $('#initPath').val()+'/ServiceGroupController.do?method=toCreateOrUpdate&dialogId=serviceGroupFormDialogId&objId='+objId+'&serviceBaseId='+serviceBaseId;
			$.epsDialog({
				id:'serviceGroupFormDialogId',
		        title:'修改搭配服务',
		        url:url,
		        width: '700',
		        height: '400',
		        onClose: function(){
				  $("#groupFee"+serviceBaseId).loadPage($('#initPath').val()+"/ServiceGroupController.do?method=toServiceGroupListView&serviceBaseId="+serviceBaseId);
				}
		    });
		}
	}else if("manageChargingGroup" == viewName){
		$('#conBody').loadPage($('#initPath').val()+'/ServiceBaseController.do?method=toServiceChargingGroupManage&serviceId='+serviceId);
	}
}

//删除服务(假删)
ServiceListForm.deleteService = function(serviceIds){
	if(confirm("确定删除该服务吗？")){
		$.getJSON($("#initPath").val()+"/ServiceBaseController.do?method=updateStatus",{"objId":serviceIds.split(","),"statusName":"useStatus","statusValue":"02"},function(json){
			if(json.success){
				alert("删除成功！");
				ServiceListForm.reload();//刷新列表
			}else{
				alert("操作失败！");
			}
		});
	}
}

//修改服务状态
ServiceListForm.updateStatus = function(serviceId,statusName,statusValue){
	var tipMsg = '';
	if(statusName=='isRecommendation' && statusValue=='1'){
		tipMsg = "确认推荐该服务吗？";
	}else if(statusName=='isRecommendation' && statusValue=='0'){
		tipMsg = "确认取消推荐该服务吗？";
	}
	if(confirm(tipMsg)){
		$.getJSON($("#initPath").val()+"/ServiceBaseController.do?method=updateStatus",{"objId":serviceId,"statusName":statusName,"statusValue":statusValue},function(json){
			if(json.success){
				alert("操作成功！");
				ServiceListForm.reload();//刷新列表
			}else{
				alert("操作失败！");
			}
		});
	}
}

//删除服务计费
ServiceListForm.deleteServiceCharging = function(serviceChargingId,serviceBaseId){
	if(confirm("确定删除该服务计费吗？")){
		$.getJSON($("#initPath").val()+'/ServiceChargingController.do?method=remove',{'objId':serviceChargingId},function(json){
			if(json.success){
				$("#stardFee"+serviceBaseId).loadPage($('#initPath').val()+"/ServiceChargingController.do?method=toServiceChargingListView&serviceBaseId="+serviceBaseId);
				ServiceListForm.updateSpanNum('stardFeeNum'+serviceBaseId,'-');
			}else{
				alert("删除失败！");
			}
		});
	}
}

//删除搭配服务
ServiceListForm.deleteServiceGroup = function(serviceGroupId,serviceBaseId){
	if(confirm("确定删除该服务组合吗？")){
		$.getJSON($("#initPath").val()+'/ServiceGroupController.do?method=remove',{'objId':serviceGroupId},function(json){
			if(json.success){
				$("#groupFee"+serviceBaseId).loadPage($('#initPath').val()+"/ServiceGroupController.do?method=toServiceGroupListView&serviceBaseId="+serviceBaseId);
				ServiceListForm.updateSpanNum('groupFeeNum'+serviceBaseId,'-');
			}else{
				alert("删除失败！");
			}
		});
	}
}

//新增服务计费
ServiceListForm.addServiceCharging = function(serviceBaseId){
	var url = $('#initPath').val()+'/ServiceChargingController.do?method=toCreateOrUpdate&dialogId=addServiceChargingDialogId&serviceBaseId='+serviceBaseId;
	$.epsDialog({
		id:'addServiceChargingDialogId',
        title:'新增服务计费标准',
        url:url,
        width: '700',
        height: '400',
        onClose: function(){
		  //新增成功
		  if($("#addFeeSuccess").val() == '1'){
			  $("#addFeeSuccess").val('0'); //还原标记
			  ServiceListForm.updateSpanNum('stardFeeNum'+serviceBaseId,'+');
			  $("#stardFee"+serviceBaseId).loadPage($('#initPath').val()+"/ServiceChargingController.do?method=toServiceChargingListView&serviceBaseId="+serviceBaseId);
		  }
		}
    });
}

//新增搭配服务
ServiceListForm.addServiceGroup = function(serviceBaseId){
	var url = $('#initPath').val()+'/ServiceGroupController.do?method=toCreateOrUpdate&dialogId=serviceGroupFormDialogId&serviceBaseId='+serviceBaseId;
	$.epsDialog({
		id:'serviceGroupFormDialogId',
        title:'新增搭配服务',
        url:url,
        width: '700',
        height: '400',
        onClose: function(){
		  //新增成功
		  if($("#addFeeSuccess").val() == '1'){
			  $("#addFeeSuccess").val('0'); //还原标记
			  ServiceListForm.updateSpanNum('groupFeeNum'+serviceBaseId,'+');
			  $("#groupFee"+serviceBaseId).loadPage($('#initPath').val()+"/ServiceGroupController.do?method=toServiceGroupListView&serviceBaseId="+serviceBaseId);
		  }
		}
    });
}

//重新加载服务列表数据
ServiceListForm.reload = function(){
	$('#conBody').loadPage($('#initPath').val()+'/ServiceBaseController.do?method=toServiceBaseListView');
}

//增加或减少指定id的span中数字
ServiceListForm.updateSpanNum = function(spanId,oper){
	var num = Number($("#"+spanId).html());
	if(oper == '+'){
		num = num + 1;
	}else{
		num = num - 1;
	}
	$("#"+spanId).html(num);
}

$(document).ready(function(){
	
	//查询
	$("#query").click(function() {
		$('#conBody').loadPage($('#initPath').val()+'/ServiceBaseController.do?method=toServiceBaseListView',{'serviceName':$("#serviceName").val()});
	})
	
	//新增服务
	$('#addServiceBtn').click(function(){
		$.epsDialog({
			title:"新增服务信息",
			width: 800,
			height: 500,
			url:$('#initPath').val()+'/ServiceBaseController.do?method=toCreateOrUpdate',
			onClose: function(){
				ServiceListForm.reload();//刷新列表
			}
		});
	})

});