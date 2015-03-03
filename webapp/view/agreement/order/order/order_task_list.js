//定义文件全局变量 处理方法名重复问题
var orderTaskList={};
orderTaskList.oTable;	

$(document).ready(function(){
	
	var orderGoodsQty = $("#orderGoodsQty").val();//商品数量
	
	orderTaskList.oTable=$('#OrderTaskItemList').dataTable( {
		'singleSelect':true,
		'checkbox':false,
		'queryColumns':'protaskItem.goodsTotal,protaskItem.goodsQty,protaskItem.finQty,protaskItem.finTotal,protaskItem.goodsPrice,orderTaskQty,orderTaskTotal',
		'hiddenColums':'',
		//'bPaginate':false, 
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			orderTaskList.oTable.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
			return nRow;
		},
		'params':{"orderItem.objId":$("#orderItemId").val()},
		"sAjaxSource": $('#initPath').val()+"/OrderProtaskController.do?method=list"
	});
	
	//重新挑选任务书
	$("#delOrderTask").click(function(){
		if(window.confirm("确定取消取消所有对应任务书条目，进行重新选择吗?")){
			$.getJSON($('#initPath').val()+'/OrderProtaskController.do?method=removeOrderProtaskByOrder',{"orderItemId":$("#orderItemId").val()},function(json){
				if(json.failure){
					alert(json.result);return;
				}
				alert("已经成功取消相关任务书条目，请重新选择！");	
				$("#conBody").loadPage($("#initPath").val()+"/OrderController.do?method=toOrderForm&appType=XYGH&type=edit_toSubmit&objId="+$("#objId").val());
				$("#close").click();
			});
		}
	})
	
	//关闭
	$("#close").click(function(){
		$("#orderTaskItem").find('.epsDialogClose').trigger('click');
	})
});
	
