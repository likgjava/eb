var giftSupplierList={};
giftSupplierList.oTable;
giftSupplierList.currentTabId = 'tabs_manager';

	//新增
	$("#giftSupplierAdd").click(function(){		
		$('#conBody').loadPage($('#initPath').val()+'/GiftSupplierController.do?method=toGiftSupplierAdd&type=newAdd');
	});
	
	//刷新
	giftSupplierList.reload = function(){
		giftSupplierList.oTable.fnDraw();
	}
	
	//查询
	$("#giftSupplierSearch").click(function(){
		giftSupplierList.oTable.fnDraw();
	});
	
	//添加操作字符串
	giftSupplierList.getOperationStr = function(objId,isUsed){
		var rowOperation = '';		
		if(isUsed == 'true'){
			rowOperation= '<td><a href="javascript:giftSupplierList.viewGiftSupplier(\''+objId+'\')" ><span>查看<span></a>  <a href="javascript:giftSupplierList.modifyGiftSupplier(\''+objId+'\')" ><span>修改</span></a>  <a href="javascript:giftSupplierList.modifyIsUsedStatus(\''+objId+'\',\''+isUsed+'\')" ><span>禁用<span></a></td>';
		}else{
			rowOperation= '<td><a href="javascript:giftSupplierList.viewGiftSupplier(\''+objId+'\')" ><span>查看<span></a>  <a href="javascript:giftSupplierList.removeGiftSupplier(\''+objId+'\')" ><span>删除</span></a>  <a href="javascript:giftSupplierList.modifyIsUsedStatus(\''+objId+'\',\''+isUsed+'\')" ><span>启用<span></a></td>';
		}
		return rowOperation;
	}
	
	//查看详情
	giftSupplierList.viewGiftSupplier = function(objId){
		$.epsDialog({
			title:'礼品供货商查看',
			url:$('#initPath').val()+'/GiftSupplierController.do?method=viewGiftSupplier&objId='+objId,
			onclose:function(){giftSupplierList.reload();}
		});
	}
	
	//修改
	giftSupplierList.modifyGiftSupplier = function(objId){
		$.epsDialog({
			title:'礼品供货商修改',
			url:$('#initPath').val()+'/GiftSupplierController.do?method=toGiftSupplierAdd&objId='+objId+'&type=modify',
			onclose:function(){giftSupplierList.reload();}
		});
	}
	
	//删除
	giftSupplierList.removeGiftSupplier = function(objId){
		if(confirm('确定删除吗？')){
			$.getJSON($('#initPath').val()+'/GiftSupplierController.do?method=removeGiftSupplier',{objId:objId},function(json){
				if(json.failure) {
					alert(json.result);
					return;
				}			
				if(json.isHasGift=="unsuccess"){
					alert("存在礼品属于此供货商,不能删除!");
					return;
				}
				if(json.isHasGift=="success"){
					alert("删除成功");
					giftSupplierList.reload();
				}
			});
		}		
	}
	
	//启用或禁用
	giftSupplierList.modifyIsUsedStatus = function(objId,isUsed){
		var status = '';
		if(isUsed == 'true'){status="禁用"}else{status="启用"}
		
		if(confirm('确定'+status+'吗？')){
			$.getJSON($('#initPath').val()+'/GiftSupplierController.do?method=modifyIsUsedStatus',{objId:objId,isUsed:isUsed},function(json){
				if(json.failure) {
					alert(json.result);
					return;
				}else{
					alert(status+"成功！");
					giftSupplierList.reload();
				}
			});
		}
	}
	
$(document).ready(function(){
	//日期表单验证	
	$("#startTime").epsDatepicker();
	$("#endTime").epsDatepicker();
	
	//加载tab页
	$('#epsTabs').tabs();
	
	//tab页点击事件
	$('li a.refreshData').click(function(){
		giftSupplierList.currentTabId = $(this).attr('id');
		
		if(giftSupplierList.currentTabId=='tabs_manager'){
			$(giftSupplierList.oTable.dataTableSettings).attr('params',{});
			giftSupplierList.oTable.fnDraw();
		}
	});
	
	//填充广告列表
	giftSupplierList.oTable = $('#giftSupplierList').dataTable({
		'singleSelect':true,
		'checkbox':false,
		'searchZone':'giftSupplierSearchForm',
		'sAjaxSource':$('#initPath').val()+"/GiftSupplierController.do?method=list",
		 params:{},
		'queryColumns':'supplierName,startTime,endTime,isUsed',
		'hiddenColumns':'objId',
		'alias':'supplierName,startTime,endTime,isUsedCN',
		'fnInitComplete':function(oSettings){},
		'fnDrawCallback':function(oSettings){
			giftSupplierList.oTable.oSettings = oSettings;
		},
		'fnRowCallback':function(nRow,aData,iDisplayIndex){
			$(nRow).append(giftSupplierList.getOperationStr(aData.objId,aData.isUsed));
			return nRow;
		}
	});
});