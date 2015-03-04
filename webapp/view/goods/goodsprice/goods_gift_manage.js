/**
 * 商品礼包管理页面
 * create by likg
 */
var GoodsGiftManage = {};

//选中礼包（单选）
GoodsGiftManage.addOne = function(obj) {
	var trObj = $(obj).parent().parent();
	var goodsGiftObjId = $(trObj).attr("objid");
	var goodsGiftName = $(trObj).find("td[name=giftName]").text();
	var hasExists = false;
	$("#goodsGiftSelectedTbody tr").each(function(j, domEleT){
		if(goodsGiftObjId == $(domEleT).attr("goodsGiftId")){
			hasExists = true;
		}
	});
	if(!hasExists){
		$("#goodsGiftPriceTempTable").find("tr:first").clone().appendTo($("#goodsGiftSelectedTbody"));
		$("#goodsGiftSelectedTbody").find("tr:last").attr("goodsGiftId",goodsGiftObjId).find("td:first").text(goodsGiftName);
	}
	
	//隐藏“选择”链接
	$(trObj).find("a[name=sel]").hide();
	
	//改变操作按钮样式
	GoodsGiftManage.validSelectedGift();
}

//取消选择（单删）
GoodsGiftManage.removeOne = function(obj) {
	var trObj = $(obj).parent().parent();
	var goodsGiftObjId = $(trObj).attr("objid");
	$("#goodsGiftSelectedTbody tr").each(function(j, domEleT){
		if(goodsGiftObjId == $(domEleT).attr("goodsGiftId") && $(domEleT).attr("goodsGiftPriceId")){
			//删除数据库中的记录
			GoodsGiftManage.deleteData($(domEleT).attr("goodsGiftPriceId"));
			$(domEleT).remove();
		}else if(goodsGiftObjId == $(domEleT).attr("goodsGiftId")){
			$(domEleT).remove();
		}
	});
	$(trObj).find("input[name=checkAll]").attr("checked", false);
	$(trObj).find("a[name=sel]").show();
	
	//改变操作按钮样式
	GoodsGiftManage.validSelectedGift();
}

//删除已选择的礼包
GoodsGiftManage.removeSelected = function(obj) {
	var trObj = $(obj).parent().parent();
	var goodsGiftObjId = $(trObj).attr("goodsGiftId");
	if($(trObj).attr("goodsGiftPriceId")!=null && $(trObj).attr("goodsGiftPriceId")!=""){
		//删除数据库中记录
		GoodsGiftManage.deleteData($(trObj).attr("goodsGiftPriceId"));
	}
	$("#goodsGiftList tbody").find("tr[objid="+goodsGiftObjId+"]").find("a[name=sel]").show();
	$("#goodsGiftList tbody").find("tr[objid="+goodsGiftObjId+"]").removeClass("row_selected").find("input[name=checkAll]").attr("checked", false);
	
	//删除当前行
	$(trObj).remove();
	
	//改变操作按钮样式
	GoodsGiftManage.validSelectedGift();
}

//选中当前页面列表中的所有礼包（全选）
GoodsGiftManage.addAll = function() {
	$("#goodsGiftList tbody tr").each(function(i, domEleL){
		var hasExists = false;
		$("#goodsGiftSelectedTbody tr").each(function(j, domEleT){
			if($(domEleL).attr("objid") == $(domEleT).attr("goodsGiftId")){
				hasExists = true;
			}
		});
		if(!hasExists){
			var goodsGiftObjId = $(domEleL).attr("objid");
			var goodsGiftName = $(domEleL).find("td[name=giftName]").text();
			$("#goodsGiftPriceTempTable").find("tr:first").clone().appendTo($("#goodsGiftSelectedTbody"));
			$("#goodsGiftSelectedTbody").find("tr:last").attr("goodsGiftId",goodsGiftObjId).find("td:first").text(goodsGiftName);
		}

		$(domEleL).find("a[name=sel]").hide();
	});	
	
	//改变操作按钮样式
	GoodsGiftManage.validSelectedGift();
}

//删除当前页面列表中的所有礼包（全删）
GoodsGiftManage.removeAll = function() {
	$("#goodsGiftList tbody tr").each(function(i, domEleL){
		$("#goodsGiftSelectedTbody tr").each(function(j, domEleT){
			if($(domEleL).attr("objid") == $(domEleT).attr("goodsGiftId") && $(domEleT).attr("goodsGiftPriceId")){
				//删除数据库中记录
				GoodsGiftManage.deleteData($(domEleT).attr("goodsGiftPriceId"));
				
				$(domEleL).find("a[name=sel]").show();
				$(domEleT).remove();
			}else if($(domEleL).attr("objid") == $(domEleT).attr("goodsGiftId")){
				
				$(domEleL).find("a[name=sel]").show();
				$(domEleT).remove();
			}
		});
	});	
	
	//改变操作按钮样式
	GoodsGiftManage.validSelectedGift();
}

//删除数据库中的记录
GoodsGiftManage.deleteData = function(goodsGiftPriceId) {
	var url = $('#initPath').val()+'/GoodsGiftPriceController.do?method=remove';
	$.getJSON(url, {'objId':goodsGiftPriceId}, function(json){
		if(!json.success){
			alert("操作失败！");
		}
	});
}

//检查是否有选择礼包，改变操作按钮样式
GoodsGiftManage.validSelectedGift = function() {
	if($("#goodsGiftSelectedTbody tr").length <= 0){
		$("#saveGoodsGiftPriceBtn").attr('disabled', true);
	}else{
		$("#saveGoodsGiftPriceBtn").attr('disabled', false);
	}
}

//查看礼包详情
GoodsGiftManage.view = function(goodsGiftId) {
	var url = $('#initPath').val()+'/GoodsGiftController.do?method=toGoodsGiftDetailView&dialogId=goodsGiftDetailDiv&goodsGiftId='+goodsGiftId;
	$.epsDialog({
		id:'goodsGiftDetailDiv',
        title:'商品礼包详细信息',
        url:url,
        width: '800',
        height: '500'
    });
}

//保存礼包价格
GoodsGiftManage.saveGoodsGiftPrice = function() {
	var isValide = true;
	$("#goodsGiftSelectedTbody").find("input[name=giftPrice]").each(function(i, domEle){
		if($(domEle).val()=="" || isNaN($(domEle).val())){
			isValide = false;
		}
	});
	if(!isValide){
		alert("请正确填写礼包价格!");return;
	}
	
	var jsonObj = {};
	var goodsGiftPrice = [];
	var goodsPriceId = $('#goodsPriceId').val();
	
	$('#goodsGiftSelectedTbody tr').each(function(index, n){
		if($(n).attr("goodsGiftPriceId")!=null && $(n).attr("goodsGiftPriceId")!=""){
			goodsGiftPrice[index] = {};
			goodsGiftPrice[index].objId = $(n).attr("goodsGiftPriceId");
			goodsGiftPrice[index].giftPrice = $(n).find('input[name=giftPrice]').val();
			
		}else{
			goodsGiftPrice[index] = {};
			goodsGiftPrice[index].giftPrice = $(n).find('input[name=giftPrice]').val();
			
			var goodsGift = {};
			goodsGift.objId = $(n).attr("goodsGiftId");
			goodsGiftPrice[index].goodsGift = goodsGift;
			
			var goodsPrice = {};
			goodsPrice.objId = goodsPriceId;
			goodsGiftPrice[index].goodsPrice = goodsPrice;
		}
	});
	jsonObj.goodsGiftPrice = JSON.stringify(goodsGiftPrice);
	
	//保存
	$.getJSON($('#initPath').val()+'/GoodsGiftPriceController.do?method=saveGoodsGiftPrice',jsonObj, function(json){
		if(json.failure){if(json.result)alert(json.result);return;}
		//跳转至我的报价列表页面
		$("#closeBtn").click();
	})
}

//修改列表样式，把已选择的礼包标记为选择状态
GoodsGiftManage.changeListCss = function() {
	$("#goodsGiftList tbody tr").each(function(i, domEleL){
		$("#goodsGiftSelectedTbody tr").each(function(j, domEleT){
			if($(domEleL).attr("objid")==$(domEleT).attr("goodsGiftId")){
				$(domEleL).addClass("row_selected").find("input[name=checkAll]").attr("checked", true);
				$(domEleL).find("a[name=sel]").hide();
			}
		});
	});	
}

$(document).ready(function() {
	
	//检查是否有选择礼包，改变操作按钮样式
	GoodsGiftManage.validSelectedGift();

	//加载列表
	GoodsGiftManage.oTable = $('#goodsGiftList').dataTable({
		'singleSelect' : false,
		'checkbox' : true,
		'queryColumns' : 'giftPicture,giftName,goods.productName',
		'hiddenColums' : 'auditStatus',
		'fnInitComplete' : function(oSettings) {
		},
		'fnDrawCallback' : function(oSettings) {
			GoodsGiftManage.oTable.oSettings = oSettings;
			//修改列表样式，把已选择的礼包标记为选择状态
			GoodsGiftManage.changeListCss();
			
			//全选
			$("#goodsGiftList thead input[name=checkAll]").click(function() {
				if($(this).attr("checked")){
					GoodsGiftManage.addAll();
				}else{
					GoodsGiftManage.removeAll();
				}
			});
			//单选
			$("#goodsGiftList tbody input[name=checkAll]").click(function() {
				if($(this).attr("checked")){
					GoodsGiftManage.addOne(this);
				}else{
					GoodsGiftManage.removeOne(this);
				}
			});
		},
		'fnRowCallback' : function(nRow, aData, iDisplayIndex) {
			$(nRow).find("td[name=giftPicture]").empty().append('<img src="AttachmentController.do?method=showImg&objId='+aData.giftPicture+'" style="cursor:pointer" width="30px" height="30px" id="'+aData.giftPicture+'" />');
			//添加操作按钮
			var operStr = '<td class="operation">';
			operStr += '<a name="sel" title="选择该礼包" href="javascript:void(0);" onclick="GoodsGiftManage.addOne(this);return false;">选择</a>';
			operStr += '<a title="查看礼包详情" href="javascript:void(0);" onclick="GoodsGiftManage.view(\'' + aData.objId + '\');return false;">查看</a>';
			operStr += '</td>';
			$(nRow).append(operStr);
			
			return nRow;
		},
		"sAjaxSource" : $('#initPath').val()+ "/GoodsGiftController.do?method=list",
		'params' : {'goods.objId':$("#goodsId").val(),'supplier.objId':$("#supplierId").val()}
		//'searchZone':'expertInfoAuditListForm'
	});
	
	//查询
	$("#query").click(function() {
		GoodsGiftManage.oTable.fnDraw();
	});
	
	//保存礼包价格
	$("#saveGoodsGiftPriceBtn").click(function() {
		GoodsGiftManage.saveGoodsGiftPrice();
	});

	//关闭
	$("#closeBtn").click(function(){         
		$('#epsDialogClose').click();
	});
});