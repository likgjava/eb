
//定义文件全局变量 处理方法名重复问题
var OrderPurchaserForm={};
OrderPurchaserForm.oTable;	

//显示商品详情
OrderPurchaserForm.showDetail = function(goodsId) {
	$.epsDialog({
		id:"showGoodsDetail",
		title:"商品详情",
		url:$("#initPath").val()+"/GoodsShowController.do?method=getGoodsInfo&viewName=goodsDetailView&viewFrom=bargain&fromDiv=yes&dailogId=showGoodsDetail&isShowPic=true&objId="+goodsId
	})
}

//数量加减
OrderPurchaserForm.goodsQtyAddOrReduce = function(e,value){
	var itemqty = Number($(e).parent().find("input[name=itemqty]").val()) + value;
	if(itemqty>0){
		$(e).parent().find("input[name=itemqty]").val(itemqty);
		$(e).parent().find("input[name=itemqty]").attr("oldvalue", itemqty);
	}else{
		alert("数量不得小于1！");
	}
	OrderPurchaserForm.reCaculator();//重新计算
	OrderPurchaserForm.goodsQtyPriceChange(
			$(e).parent().parent().attr("id"),
			$(e).parent().parent().find("input[name=itemqty]").val(),
			$(e).parent().parent().find("input[name=itmeprice]").val(),
			$(e).parent().parent().find("span[id=goodsTotal]").html()
	);
}

//数量更改
OrderPurchaserForm.goodsQtyChange = function(e){
	if(isNaN($(e).val()+'') || $(e).val()==""){
		alert("请输入有效商品数量！");
		$(e).val($(e).attr("oldvalue"));
		return;
	}else{
		$(e).attr("oldvalue", $(e).val());
	}
	OrderPurchaserForm.reCaculator();//重新计算
	OrderPurchaserForm.goodsQtyPriceChange(
			$(e).parent().parent().attr("id"),
			$(e).parent().parent().find("input[name=itemqty]").val(),
			$(e).parent().parent().find("input[name=itmeprice]").val(),
			$(e).parent().parent().find("span[id=goodsTotal]").html()
	);
}

//单价更改
OrderPurchaserForm.goodsPriceChange = function(e){
	if(isNaN($(e).val()+'')||Number($(e).val())<1){alert("请输入大于零的数字");$(e).val("");return;}
	OrderPurchaserForm.reCaculator();//重新计算
	OrderPurchaserForm.goodsQtyPriceChange(
			$(e).parent().parent().attr("id"),
			$(e).parent().parent().find("input[name=itemqty]").val(),
			$(e).parent().parent().find("input[name=itmeprice]").val(),
			$(e).parent().parent().find("span[id=goodsTotal]").html()
	);
}

//商品的数量和金额改变事件
OrderPurchaserForm.goodsQtyPriceChange=function(objId,qty,price,goodstotal){//当前激活的输入框
	var param={
			"objId":objId,
			"goodsQty":qty.replace(/,/g,''),
			"goodsPrice":price.replace(/,/g,''),
			"goodsTotal":goodstotal.replace(/,/g,''),
			"orderQty":$("#totalMoney").html(),
			"orderTotal":$("#countGoods").html(),
			"orderId":$("#objId").val()
	};
	$.getJSON($('#initPath').val()+'/OrderItemController.do?method=updateGoodsChangeQtyAndMoney',param,function(json){
		if(json.failure){alert(json.result);return;}
	});
}

//计算金额,数量,节省金额,以及购物车条目的总金额
OrderPurchaserForm.reCaculator = function(){
	$.each($("span[id=goodsTotal]"),function(index,obj){
		$(obj).html(formatAmount(Number($(obj).parent().parent().find("input[name=itmeprice]").val().replace(/,/g,'')*$(obj).parent().parent().find("input[name=itemqty]").val()),2));
	})
	var mark = 0;
	var qty = 0;
	var total= 0;
	$.each($("span[id=mark]"),function(index,obj){
		mark += Number($(obj).html().replace(/,/g,'')) * Number($(obj).parent().parent().find("input[name=itemqty]").val());
	})
	$.each($("input[name=itemqty]"),function(index,obj){
		qty += Number($(obj).val().replace(/,/g,''));
	})
	$.each($("span[id=goodsTotal]"),function(index,obj){
		total += Number($(obj).html().replace(/,/g,''));
	})
	$('#countGoods').html(qty);
	$('#totalMoney').html(formatAmount(total,2));
	$('#saveAmount').html(formatAmount(mark-total,2));
}

//显示或隐藏商品礼包
OrderPurchaserForm.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}
//显示或隐藏商品零配件
OrderPurchaserForm.showOrHideGoodsAccess = function(obj){
	$(obj).parent().parent().next().toggle();
}

//全选或不选
OrderPurchaserForm.checkAllOrNot = function(cell,e){
	$("input[name*="+cell+"]").attr("checked",$(e).attr("checked"));
}

//保存/提交订单
OrderPurchaserForm.save=function(methodName){

	var flag = true;
	$.each($("#goodsAndOption tbody tr"),function(index,obj){
		if($(obj).find("input[name=itemqty]")!=null&&$(obj).find("input[name=itemqty]").val()==""){alert("数量不能为空！");flag=false;}
		if($(obj).find("input[name=itmeprice]")!=null&&$(obj).find("input[name=itmeprice]").val()==""){alert("单价不能为空！");flag=false;}
	})
	if(!flag){return;}
	
	//验证‘采购人意见’的字符个数
	var buyerOption = $("textarea[name=buyerOption]").val();
	if(buyerOption!=null && buyerOption.length>1000){alert('"采购人意见"超过最大字符数！\n最大字符长度：1000\n实际字符长度：'+buyerOption.length);return;}
	
	var josnObj={};
	josnObj.objId=$("#objId").val();
	josnObj.goodsQty=$('#countGoods').text().replace(/,/g,'');
	josnObj.goodsTotal=$('#totalMoney').text().replace(/,/g,'');
	josnObj.buyerOption = native2ascii($("textarea[name=buyerOption]").val());
	$.ajax({
		url:$('#initPath').val()+'/OrderController.do?method=saveOrder',
		data:{orderStr:JSON.stringify(josnObj),methodName:methodName},
		dataType:'json',
		success:function(json){
			if(json.failure){
				alert(json.result);return;
			}
			if(methodName == "submit"){
				
				OrderPurchaserForm.toPayByBank(json.order.objId , json.order.orderNo ,json.order.goodsTotal );
				
				alert("已将该订单提交给"+$("span[id=supplier.orgName]").text()+"，请等待其确认！");
			}
			else{
				alert("已经成功保存订单！");
			}
			$("button[name=historyBackBtn]").click();
		},
		error:function(msg){
			alert('操作失败');
		}
	});
	
	//OrderPurchaserForm.toPayByBank("O-20110615-0006" ,0.01 );
}


//提交完毕跳转到网银交钱
OrderPurchaserForm.toPayByBank = function(orderId, orderCode ,orderTotal){
	/*
	 * v_oid  = 订单号
	 * v_amount = 总金额
	 */
	//orderCode = "O-20110615-0012";
	//orderTotal = 0.01;
	var bankUrl = $("#initPath").val()+"/OrderController.do?method=toOrderPayView&v_oid="+orderCode+"&v_amount="+orderTotal+"&orderId="+orderId;
	var futher="center: Yes; resizable: No; status: No;";	 
	window.open(bankUrl);
}

//删除订单子项
OrderPurchaserForm.remove=function(orderItemId){
	if($("#goodsAndOption").find("tbody tr[class=goodsInfo]").length==1){alert("订单需保留至少一个商品！");return;}
	if(confirm("确认删除？")){
		$.getJSON($('#initPath').val()+'/OrderItemController.do?method=remove',{objId:orderItemId},function(json){
			if(json.result){
				alert(json.result);
				var nextTrObj = $('tr[id='+orderItemId+']','#goodsAndOption').next();
				if(!$(nextTrObj).hasClass("goodsInfo")){//移除商品礼包行
					$(nextTrObj).remove();
				}
				$('tr[id='+orderItemId+']','#goodsAndOption').remove();//移除删除行
				OrderPurchaserForm.reCaculator();//重新计算
			}
		});
	}
}

//选择任务书
OrderPurchaserForm.vieTask = function(orderItemId,purCategoryId,e,count){
	var orderGoodsQty = $(e).parent().parent().find("input[name=itemqty]").val().replace(/,/g,'');
	var orderGoodsPrice = $(e).parent().parent().find("input[name=itmeprice]").val().replace(/,/g,'');
	var url = $('#initPath').val()+'/view/agreement/order/order/order_choose_task.jsp?orderItemId='+orderItemId+'&purCategoryId='+purCategoryId+'&orderGoodsQty='+orderGoodsQty+'&orderGoodsPrice='+orderGoodsPrice;
	if(count!=null&&count>0){
		url = $('#initPath').val()+'/view/agreement/order/order/order_task_list.jsp?orderItemId='+orderItemId+'&purCategoryId='+purCategoryId+'&orderGoodsQty='+orderGoodsQty+'&orderGoodsPrice='+orderGoodsPrice;
	}
	$.epsDialog({
		 	id:"orderTaskItem",
	        title:'任务书信息（商品数量：'+orderGoodsQty+'，单价：￥'+orderGoodsPrice+"元）",
			url:url
	}); 
}

$(document).ready(function(){
	//商品数量，控制不能输入非正整数
	$("input[name=itemqty]").inputFillter({type:'int'});

	//根据操作类型，显示相应的按钮
	if("edit_toSubmit" == $("#type").val()){
		$("#save").show();
	}

	//提交
	$("#submit").click(function(){
		var isCheck = true;
		$("span[id=chooseTask]").each(function(i,n){
			if($(n).text() == "挑选任务书"){
				isCheck = false;
			}
		})
		if(isCheck){
			if(!window.confirm("确定提交该订单吗?")){return;}
			OrderPurchaserForm.save("submit");
		}else{
			alert("还有未匹配任务书的商品，请先选择任务书再提交，或直接点击保存。");
		}
	})
	
	//保存
	$("#save").click(function(){
		OrderPurchaserForm.save("save");
	})
	
	//取消
	$("#cancel").click(function(){
		if(window.confirm("确定撤销该订单吗?（将删除订单）")){
			var data={"objId":$("#objId").val()};
			$.getJSON($('#initPath').val()+'/OrderController.do?method=remove',data,function(json){
				if(json.failure){
					alert(json.result);return;
				}
				alert("已经成功删除订单！");	
				$("button[name=historyBackBtn]").click();
			});
		}
	})
	
});
	
