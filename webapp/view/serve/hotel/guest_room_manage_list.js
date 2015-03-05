/**
 * 客房管理列表页面
 * create by likg
 */
var GuestRoomManageList={};

//获得操作字符串
GuestRoomManageList.getOperationStr = function(guestRoomId){
	var operStr = '<td class="operation">';
	operStr += '<a href="javascript:void(0);" onclick="GuestRoomManageList.view(\'view\',\'' + guestRoomId + '\');return false;">查看</a>';
	operStr += '<a href="javascript:void(0);" onclick="GuestRoomManageList.view(\'viewPrice\',\'' + guestRoomId + '\');return false;">查看价格</a>';
	if($('#viewType').val() && $('#viewType').val()=="createOrUpdate"){
		operStr += '<a href="javascript:void(0);" onclick="GuestRoomManageList.view(\'update\',\'' + guestRoomId + '\');return false;">修改</a>';
		operStr += '<a href="javascript:void(0);" onclick="GuestRoomManageList.view(\'delete\',\'' + guestRoomId + '\');return false;">删除</a>';
	}
	operStr += '</td>';
	return operStr;
}

//操作
GuestRoomManageList.view = function(operName, guestRoomId) {
	if(operName == 'view'){
		var url = $('#initPath').val()+'/GuestRoomController.do?method=toGuestRoomDetailView&guestRoomId='+guestRoomId;
		$.epsDialog({
	        title:'客房详细信息',
	        url:url
	    });
	}else if(operName == 'update'){
		var url = $('#initPath').val()+'/GuestRoomController.do?method=toGuestRoomModifyView&guestRoomId='+guestRoomId;
		$.epsDialog({
			title:'修改客房信息',
			url:url,
			onClose: function(){
				GuestRoomManageList.oTable.fnDraw();
			}
		});
	}else if(operName == 'delete'){
		if(confirm('确定删除？')) {
			$.getJSON($("#initPath").val()+'/GuestRoomController.do?method=remove',{'objId':guestRoomId},function(json){
				if(json.success){
					GuestRoomManageList.oTable.fnDraw();
				}else{
					alert("操作失败！");
				}
			});
		}
	}else if(operName == 'viewPrice'){
		//$("#conBody").loadPage($("#initPath").val()+'/GuestRoomController.do?method=toGuestRoomPriceManageListView&guestRoomId='+guestRoomId);
		var url = $("#initPath").val()+'/GuestRoomController.do?method=toGuestRoomPriceManageListView&dialogId=showGuestRoomPriceDialogId&viewType=view&guestRoomId='+guestRoomId;
		$.epsDialog({
			id:'showGuestRoomPriceDialogId',
			title:'客房价格信息',
			url:url
		});
	}
}

//图片放大控制
GuestRoomManageList.zoomInPicture = function(picId){
	new ImageZoom( picId, "imgViewer", {scale: 10, delay: 0});
}

$(document).ready(function(){
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
		'queryColumns' : 'picture,guestRoomType,retailPrice,breakfastType,bedType,broadband',
		'alias' : 'picture,guestRoomTypeCN,retailPrice,breakfastTypeCN,bedTypeCN,broadband',
		'hiddenColumns':'',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			GuestRoomManageList.oTable.oSettings = oSettings;
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData.picture+'" style="cursor:pointer" width="30px" height="30px" id="'+aData.picture+'" onmouseover="GuestRoomManageList.zoomInPicture(\''+aData.picture+'\');" />');
			var str = GuestRoomManageList.getOperationStr(aData.objId);
			$(nRow).append(str);
			
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GuestRoomController.do?method=list&hotel.objId="+$("#hotelId").val(),
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
			url:$('#initPath').val()+'/view/serve/hotel/create_guest_room.jsp?hotelId='+$('#hotelId').val(),
			onClose: function(){
				GuestRoomManageList.oTable.fnDraw();
			}
		});
	});
	
	//返回
	$('#historyBackBtn').click(function(){
		$('#conBody').loadPage($('#returnUrl').val());
	});
});
