
//定义文件全局变量 处理方法名重复问题
var OrderSupplierDetail={};
OrderSupplierDetail.oTable;	

//显示商品详情
OrderSupplierDetail.showDetail = function(goodsId) {
	$.epsDialog({
		id:"showGoodsDetail",
		title:"商品详情",
		url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&dailogId=showGoodsDetail&isShowPic=true&objId="+goodsId
	})
}

//查看合同
OrderSupplierDetail.openContract=function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDetail&appType=XEJY&objId="+objId);
}

//显示或隐藏商品礼包
OrderSupplierDetail.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}
//显示或隐藏商品零配件
OrderSupplierDetail.showOrHideGoodsAccess = function(obj){
	$(obj).parent().parent().next().toggle();
}

$(document).ready(function(){
	//根据操作类型，显示相应的按钮
//	if("draft_contract" != $("#type").val()){
//		$("#cancel").show();
//	}else if($("#contract").text() == ""){
//		$("#draftContract").show();
//	}
	
	//根据操作类型，显示相应的按钮
	if("draft_contract" == $("#type").val()&&$("#contract").text()==""){
		$("#draftContract").show();
		$("#cancel").show();
	}
	
	//作废订单
	$("#destroy").click(function(){
		if(window.confirm("确定作废订单吗?")){
			var data={"objId":$("#objId").val(),"destroyType":"supplier"};
			$.getJSON($('#initPath').val()+'/OrderController.do?method=destroyOrder',data,function(json){
				if(json.failure){
					alert(json.result);return;
				}
				alert("订单已作废，并已通知相关人员！");	
				$("button[name=historyBackBtn]").click();
			});
		}
	})
	
	//起草合同
	$("#draftContract").click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDraft&forType=supplier&orderIds="+$("#objId").val()+"&buyerId="+$("input[name=buyer.objId]").val()+"&buyerName="+$("span[id=buyerOrg]").html());
	})
	
});
	
