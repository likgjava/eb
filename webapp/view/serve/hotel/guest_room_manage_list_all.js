/**
 * 维护客房价格列表页面
 * create by likg
 */
var GuestRoomManageList={};

//获得操作字符串
GuestRoomManageList.getOperationStr = function(guestRoomId){
	var operStr = '<td class="operation">';
	operStr += '<a href="javascript:void(0);" onclick="GuestRoomManageList.view(\'viewPrice\',\'' + guestRoomId + '\');return false;">维护客房价格</a>';
	operStr += '</td>';
	return operStr;
}

//操作
GuestRoomManageList.view = function(operName, guestRoomId) {
	if(operName == 'viewPrice'){
		$("#conBody").loadPage($("#initPath").val()+'/GuestRoomController.do?method=toGuestRoomPriceManageListView&guestRoomId='+guestRoomId);
	}
}

$(document).ready(function(){
	$('#returnUrl').val($('#initPath').val()+ "/GuestRoomController.do?method=toGuestRoomListView&listType=listAll");
	//如果是新增或修改
	if($('#viewType').val() && $('#viewType').val()=="createOrUpdate"){
		$('#guestRoomAttention').show();
	}else{
		$('#guestRoomAttention').hide();
	}
	
	var $tabs = $('#epsTabs').tabs({}); 
	
	//加载客房信息列表
	GuestRoomManageList.oTable = $('#guestRoomManageList').dataTable({
		'singleSelect' : true,
		'checkbox' : false,
		'queryColumns' : 'hotel.hotelName,guestRoomType,retailPrice,breakfastType,bedType,broadband',
		'alias' : 'hotel.hotelName,guestRoomTypeCN,retailPrice,breakfastTypeCN,bedTypeCN,broadband',
		'hiddenColumns':'',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			GuestRoomManageList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			var str = GuestRoomManageList.getOperationStr(aData.objId);
			$(nRow).append(str);
			
			return nRow;
		},
		"params":{},
		"sAjaxSource" : $('#initPath').val()+ "/GuestRoomController.do?method=list",
		'searchZone':'guestRoomManageListForm'
	});
	
	//查询
	$("#query").click(function() {
		GuestRoomManageList.oTable.fnDraw();
	})
	
	//新增客房
	$("#createGuestRoomBtn").click(function(){
		$.epsDialog({
			title:'新增客房',
			url:$('#initPath').val()+'/view/serve/hotel/create_guest_room.jsp?hotelId='+$('#hotelId').val()
		});
	});
	
	//返回
	$('#historyBackBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	});
});
