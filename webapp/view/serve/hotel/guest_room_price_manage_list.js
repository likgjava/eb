/**
 * 客房价格管理列表页面
 * create by likg
 */
var GuestRoomPriceManageList={};

//获得操作字符串
GuestRoomPriceManageList.getOperationStr = function(guestRoomPriceId){
	var operStr = '<td class="operation">';
	operStr += '<a href="javascript:void(0);" onclick="GuestRoomPriceManageList.view(\'view\',\'' + guestRoomPriceId + '\');return false;">查看</a>';
	//如果是查看
	if($('#viewType_grpm').val()!="view"){
		operStr += '<a href="javascript:void(0);" onclick="GuestRoomPriceManageList.view(\'update\',\'' + guestRoomPriceId + '\');return false;">修改</a>';
		operStr += '<a href="javascript:void(0);" onclick="GuestRoomPriceManageList.view(\'delete\',\'' + guestRoomPriceId + '\');return false;">删除</a>';
	}
	operStr += '</td>';
	return operStr;
}

//操作
GuestRoomPriceManageList.view = function(operName, guestRoomPriceId) {
	if(operName == 'view'){
		var url = $('#initPath').val()+'/GuestRoomPriceController.do?method=toGuestRoomPriceDetailView&dialogId=viewGuestRoomPriceDialogId&guestRoomPriceId='+guestRoomPriceId;
		$.epsDialog({
			id:'viewGuestRoomPriceDialogId',
	        title:'客房价格详细信息',
	        url:url,
	        width: '500',
	        height: '300'
	    });
	}else if(operName == 'update'){
		var url = $('#initPath').val()+'/GuestRoomPriceController.do?method=toGuestRoomPriceModifyView&dialogId=updateGuestRoomPriceDialogId&guestRoomPriceId='+guestRoomPriceId;
		$.epsDialog({
			id:'updateGuestRoomPriceDialogId',
	        title:'修改客房价格',
	        url:url,
	        width: '500',
	        height: '300',
	        onClose: function(){
				GuestRoomPriceManageList.oTable.fnDraw();
			}
	    });
	}else if(operName == 'delete'){
		if(confirm('确定删除？')) {
			$.getJSON($("#initPath").val()+'/GuestRoomPriceController.do?method=remove',{'objId':guestRoomPriceId},function(json){
				if(json.success){
					GuestRoomPriceManageList.oTable.fnDraw();
				}else{
					alert("操作失败！");
				}
			});
		}
	}
}

$(document).ready(function(){
	//如果是查看
	if($('#viewType_grpm').val() && $('#viewType_grpm').val()=="view"){
		$('#createGuestRoomPriceAttention').hide();
		$('#showGuestRoomPriceCloseBut').show();
		$('#historyBackBtn_view').hide();
	}else{
		$('#createGuestRoomPriceAttention').show();
		$('#showGuestRoomPriceCloseBut').hide();
		$('#historyBackBtn_view').show();
	}
	
	$("#startTimeSearch").epsDatepicker();
	$("#endTimeSearch").epsDatepicker();
	
	var $tabs = $('#epsTabs').tabs({}); 
	
	//加载客房价格信息列表
	GuestRoomPriceManageList.oTable = $('#guestRoomPriceManageList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'agreePrice,startTime,endTime,hasBreakfast',
		//'alias' : 'agreePrice,startTime,endTime,hasBreakfast',
		'hiddenColumns':'objId',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			GuestRoomPriceManageList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			if(aData.hasBreakfast=='true'){
				$(nRow).find("td[name=hasBreakfast]").html('含早餐');
			}else{
				$(nRow).find("td[name=hasBreakfast]").html('不含早餐');
			}
			var str = GuestRoomPriceManageList.getOperationStr(aData.objId);
			$(nRow).append(str);
			
			return nRow;
		},
		"params":{},
		"sAjaxSource" : $('#initPath').val()+ "/GuestRoomPriceController.do?method=list&guestRoom.objId="+$("#guestRoomId").val(),
		'searchZone':'guestRoomPriceManageListForm'
	});
	
	//查询
	$("#query").click(function() {
		GuestRoomPriceManageList.oTable.fnDraw();
	})
	
	//新增客房价格
	$("#createGuestRoomPriceBtn").click(function(){
		var url = $('#initPath').val()+'/view/serve/hotel/create_guest_room_price.jsp?dialogId=createGuestRoomPriceDialogId&guestRoomId='+$("#guestRoomId").val();
		$.epsDialog({
			id:'createGuestRoomPriceDialogId',
	        title:'新增客房价格',
	        url:url,
	        width: '500',
	        height: '300',
	        hasBg:true,
	        onClose: function(){
				GuestRoomPriceManageList.oTable.fnDraw();
			}
	    });
	});
	
	//返回
	$('#historyBackBtn_view').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	});
	
	//关闭
	$("#showGuestRoomPriceCloseBut").click(function() {
		if($("#_dialogID").val() != ""){
			$(document.getElementById($("#_dialogID").val())).find('.epsDialogClose').trigger('click');
		}else{
			$('.epsDialogClose').trigger('click');
		}
	})
});
