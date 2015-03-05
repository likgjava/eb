var HotelList = {};
HotelList.currentTabID = "auditStatus_00";
HotelList.getOperatorStr=function(objId,auditStatus){
	var operatorHtml = "";
	operatorHtml += '<td align="center">';
	if(auditStatus != "01"){
		operatorHtml += '<a href="javascript:void(0);" onclick="HotelList.toCreateOrUpdate(\''+objId+'\');return false;" title="修改">修改</a>';
	}
	operatorHtml += '<a href="javascript:void(0);" onclick="HotelList.viewHotel(\''+objId+'\');return false;" title="查看酒店" >查看酒店</a>';
	operatorHtml += '<a href="javascript:void(0);" onclick="HotelList.viewGuestRoom(\''+objId+'\');return false;" title="管理客房">管理客房</a>';
	operatorHtml += '<a href="javascript:void(0);" onclick="HotelList.viewMeetingRoom(\''+objId+'\');return false;" title="管理会议室" >管理会议室</a>';
	operatorHtml += '<a href="javascript:void(0);" onclick="HotelList.remove(\''+objId+'\');return false;" title="删除">删除</a>';
	operatorHtml += '</td>';
	return operatorHtml;
}

//新增或修改
HotelList.toCreateOrUpdate=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/HotelController.do?method=toCreateOrUpdate&objId="+id);
}

//管理客房
HotelList.viewGuestRoom=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/GuestRoomController.do?method=toGuestRoomListView&objId="+id+"&viewType=createOrUpdate");
}

//管理会议室
HotelList.viewMeetingRoom=function(id){
	$('#conBody').loadPage($('#initPath').val()+"/MeetingRoomController.do?method=toMeetingRoomListView&objId="+id+"&viewType=createOrUpdate");
}  

//查看酒店
HotelList.viewHotel=function(id,auditType){
	$('#conBody').loadPage($('#initPath').val()+"/HotelController.do?method=toShowView&objId="+id);
}  


//删除
HotelList.remove=function(ids){
	if(window.confirm("确定删除该酒店吗？")){
		$.getJSON($('#initPath').val()+'/HotelController.do?method=remove',{"objId":ids},function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			HotelList.oTable.fnDraw();
		});
	}
} 


//创建或刷新列表数据
HotelList.getTableList = function() {
	if(!HotelList.oTable) {
		HotelList.oTable = $('#hotelList').dataTable({
			'singleSelect' : false,
			'checkbox' : false,
			'queryColumns' : 'picture,hotelName,orgInfo.orgName,district,useStatus',
			'alias' : 'picture,hotelName,orgInfo.orgName,district,useStatusCN',
			'hiddenColumns':'useStatus,auditStatus,objId,district.parent.parent.name,district.parent.name,district.name',
			'fnInitComplete' : function(oSettings) {
			},
			'fnDrawCallback' : function(oSettings) {
				HotelList.oTable.oSettings = oSettings;
			},
			'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
				
				$(nRow).find("td[name=picture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData["picture"]+'" CURSOR="hand" width="60px" height="60px"/>');
				var area = "";
				if(aData.district){
					area = aData['district.name']
				}
				if(aData['district.parent.name']){
					area = aData['district.parent.name']+area
				}
				if(aData['district.parent.parent.name']){
					area = aData['district.parent.parent.name']+area
				}
				
				$(nRow).find("td[name=district]").empty().append(area);
				//添加操作按钮
				$(nRow).append(HotelList.getOperatorStr(aData.objId,aData.auditStatus));
				return nRow;
			},
			"sAjaxSource" : $('#initPath').val()+ "/HotelController.do?method=list&orgInfo.objId="+$('#orgInfoId').val(),
			"params":{"auditStatus":HotelList.currentTabID.replace("auditStatus_","")},
			'searchZone':'HotelSearchForm'
		});
	}else {
		$(HotelList.oTable.dataTableSettings).attr("params", {"auditStatus":HotelList.currentTabID.replace("auditStatus_","")});
		HotelList.oTable.fnDraw();
	}
}

$(document).ready(function(){
    
	$('#returnUrl').val($('#initPath').val()+ "/HotelController.do");
	
	//开始时间
    $("#startDate").epsDatepicker();  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker();  //增加开始时间的规则
    
    //高级查询
	$("#hightSearchSwitch").click(function(){
		$(".conSearch .hightSearch").toggle("slow");
		$(this).toggleClass("collapsable");
		$(".operationBtnDiv").toggleClass("hightSearchBtn");
	});
	
	//加载tabs,绑定选中事件为加载列表
	var $tabs = $('#epsTabs').tabs({
		select: function(event, ui) {
			HotelList.currentTabID = ui.tab.id;
			$("#currentTab").val(ui.index); //当前tab的index
			HotelList.getTableList();
		}
	});
	
	//指定某一个tab被选中，默认值为0
	$tabs.tabs('select', parseInt($("#currentTab").val()));
	
	//tab无法触发第一个选中，所以需要手动加载一次
	if($("#currentTab").val() == "0") {
		HotelList.getTableList();
	}
	
	//新增
	$("#addHotelBtn").click(function() {
		HotelList.toCreateOrUpdate("");
	})
	// 查询
	$("#queryHotel").click(function() {
		if($("#startDate").val() != "" && $("#endDate").val() != ""){
			if($("#endDate").val() < $("#startDate").val()){
				alert("请输入正确的创建时间范围!");return;
			}
			if($("#startDate").val().length > 0 && $("#endDate").val().length == 0){
				$(HotelList.oTable.dataTableSettings).attr("params",
						$.extend(HotelList.oTable.dataTableSettings[0].params,{"startTime":$("#startDate").val(),"startTime_op":"ge"}));
			}
			else if($("#endDate").val().length > 0 && $("#startDate").val().length == 0){
				$(HotelList.oTable.dataTableSettings).attr("params",
						$.extend(HotelList.oTable.dataTableSettings[0].params,{"startTime":$("#startDate").val(),"startTime_op":"le"}));
			}
			else if($("#endDate").val().length > 0 && $("#startDate").val().length > 0){
				$(HotelList.oTable.dataTableSettings).attr("params",
						$.extend(HotelList.oTable.dataTableSettings[0].params,{"startTime":$("#startDate").val()+","+$("#endDate").val(),"startTime_op":"bt"}));
			}
		}
		HotelList.oTable.fnDraw();
	})
	
	//行政区域联动
	var option = {parameter:"objId"};
    $('#province').CascadingSelect($('#city'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});
    $('#city').CascadingSelect($('select[id=district.objId]'),$('#initPath').val()+'/DistrictController.do?method=getSubDistricts',option,function(datas){});

})