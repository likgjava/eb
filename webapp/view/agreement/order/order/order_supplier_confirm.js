
//定义文件全局变量 处理方法名重复问题
var OrderSupplierConfirm={};
OrderSupplierConfirm.oTable;

//显示商品详情
OrderSupplierConfirm.showDetail = function(goodsId) {
	$.epsDialog({
		id:"showGoodsDetail",
		title:"商品详情",
		url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&dailogId=showGoodsDetail&isShowPic=true&objId="+goodsId
	})
}

//单价更改
OrderSupplierConfirm.goodsPriceChange = function(e){
	if(isNaN($(e).val()+'')||Number($(e).val())<1){alert("请输入大于零的数字");$(e).val("");return;}
	OrderSupplierConfirm.reCaculator();//重新计算
	OrderSupplierConfirm.goodsQtyPriceChange(
			$(e).parent().parent().attr("id"),
			$(e).parent().parent().find("span[id=itemqty]").html(),
			$(e).val(),
			$(e).parent().parent().find("span[id=goodsTotal]").html()
	);
}

//商品的数量和金额改变事件
OrderSupplierConfirm.goodsQtyPriceChange=function(objId,qty,price,goodstotal){
	var param={
			"objId":objId,
			"goodsQty":qty.replace(/,/g,''),
			"goodsPrice":price.replace(/,/g,''),
			"goodsTotal":goodstotal.replace(/,/g,'')
	};
	$.getJSON($('#initPath').val()+'/OrderItemController.do?method=updateGoodsChangeQtyAndMoney',param,function(json){
		if(json.failure){alert(json.result);return;}
		$("#confirm").attr("disabled","disabled");
	});
}


//计算金额,数量,节省金额,以及购物车条目的总金额
OrderSupplierConfirm.reCaculator = function(){
	$.each($("span[id=goodsTotal]"),function(index,obj){
		$(obj).html(formatAmount(Number($(obj).parent().parent().find("input[name=itmeprice]").val()*$(obj).parent().parent().find("span[id=itemqty]").html()),2));
	})
	var mark = 0;
	var qty = 0;
	var total= 0;
	$.each($("span[id=mark]"),function(index,obj){
		mark += Number($(obj).html().replace(/,/g,'')) * Number($(obj).parent().parent().find("span[id=itemqty]").html());
	})
	$.each($("span[id=itemqty]"),function(index,obj){
		qty += Number($(obj).html().replace(/,/g,''));
	})
	$.each($("span[id=goodsTotal]"),function(index,obj){
		total += Number($(obj).html().replace(/,/g,''));
	})
	$('#countGoods').html(qty);
	$('#totalMoney').html(formatAmount(total,2));
	$('#saveAmount').html(formatAmount((mark-total<0?0:mark-total),2));
}

//确认/申请调整订单
OrderSupplierConfirm.save=function(methodName){
	
	var flag = true;
	$.each($("#goodsAndOption tbody tr"),function(index,obj){
		if($(obj).find("input[name=itmeprice]")!=null&&$(obj).find("input[name=itmeprice]").val()==""){alert("单价不能为空！");flag=false;}
	})
	if(!flag){return;}
	
	var josnObj={};
	josnObj.objId=$("#objId").val();
	josnObj.goodsQty=$('#countGoods').text().replace(/,/g,'');
	josnObj.goodsTotal=$('#totalMoney').text().replace(/,/g,'');
	josnObj.supplierOption = native2ascii($("textarea[name=supplierOption]").val());
	
	$.ajax({
		url:$('#initPath').val()+'/OrderController.do?method=saveOrder',
		data:{orderStr:JSON.stringify(josnObj),methodName:methodName},
		dataType:'json',
		success:function(json){
			if(json.failure){
				alert(json.result);return;
			}
			if(methodName == "sure"){
				alert("订单已经确认完成！");
			}
			else{
				alert("已经将订单退回给"+$("span[id=buyer.orgName]").text()+"，请等待其调整！");
			}
			$("button[name=historyBackBtn]").click();
		},
		error:function(msg){
			alert('操作失败');
		}
	});
}

//确认订单
OrderSupplierConfirm.sureOrder = function(){
	if(confirm("确定要确认该订单吗?")){
		OrderSupplierConfirm.save("sure");
	}
}

//显示或隐藏商品礼包
OrderSupplierConfirm.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}
//显示或隐藏商品零配件
OrderSupplierConfirm.showOrHideGoodsAccess = function(obj){
	$(obj).parent().parent().next().toggle();
}

$(document).ready(function(){

	//申请调整
	$("#adjust").click(function(){
		if(window.confirm("确定要退回该订单吗?")){
			OrderSupplierConfirm.save("back");
		}
	})
	
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
});
	
