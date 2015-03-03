
//定义文件全局变量 处理方法名重复问题
var OrderBuyerDetail={};
OrderBuyerDetail.oTable;	


//显示商品详情
OrderBuyerDetail.showDetail = function(goodsId) {
	$.epsDialog({
		id:"showGoodsDetail",
		title:"商品详情",
		url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&dailogId=showGoodsDetail&isShowPic=true&objId="+goodsId
	})
}

//查看合同
OrderBuyerDetail.openContract=function(objId){
	$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDetail&appType=XEJY&objId="+objId);
}

//显示或隐藏商品礼包
OrderBuyerDetail.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}
//显示或隐藏商品零配件
OrderBuyerDetail.showOrHideGoodsAccess = function(obj){
	$(obj).parent().parent().next().toggle();
}

//网银支付
OrderBuyerDetail.toPayView=function(orderId,orderCode,orderTotal){
	var bankUrl = $("#initPath").val()+"/OrderController.do?method=toOrderPayView&v_oid="+orderCode+"&v_amount="+orderTotal+"&orderId="+orderId;
	window.open(bankUrl);
}

$(document).ready(function(){
	
	//根据操作类型，显示相应的按钮
	if("draft_contract" == $("#type").val()&&$("#contract").text()==""){
		$("#draftContract").show();
		$("#destroy").show();
	}else if("to_confirm" == $("#type").val()){
		$("#revocate").show();
	}
	//作废订单
	$("#destroy").click(function(){
		if(window.confirm("确定作废订单吗?")){
			var data={"objId":$("#objId").val(),"destroyType":"buyer"};
			$.getJSON($('#initPath').val()+'/OrderController.do?method=destroyOrder',data,function(json){
				if(json.failure){
					alert(json.result);return;
				}
				alert("订单已作废，并已通知相关人员！");	
				$("button[name=historyBackBtn]").click();
			});
		}
	})
	
	//撤销提交
	$("#revocate").click(function(){
		if(window.confirm("确定取消提交该订单吗?")){
			var josnObj={};
			josnObj.objId=$("#objId").val();
			$.ajax({
				url:$('#initPath').val()+'/OrderController.do?method=saveOrder',
				data:{orderStr:JSON.stringify(josnObj),methodName:"revocate"},
				dataType:'json',
				success:function(json){
					if(json.failure){
						alert(json.result);return;
					}
					alert("已将该订单撤销，请重新提交！");
					$("button[name=historyBackBtn]").click();
				},
				error:function(msg){
					alert('操作失败');
				}
			});
		}
	})
	
	//起草合同
	$("#draftContract").click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/AgContractController.do?method=toContractDraft&forType=buyer&orderIds="+$("#objId").val()+"&supplierId="+$("input[name=supplier.objId]").val()+"&supplierName="+$("span[id=supplierOrg]").html());
	})
});
	
