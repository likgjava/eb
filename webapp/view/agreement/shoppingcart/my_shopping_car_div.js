var shoppingCartDiv = {};

//显示商品详情
shoppingCartDiv.showGoodsDetail = function(goodsId) {
	common.geToGoodsDetail(goodsId);
}

//显示供应商详情
shoppingCartDiv.showSupplierDetail = function(supplierId) {
	common.goToOrgShop(supplierId); //跳转到企业商铺
}

//计算金额,数量,节省金额,以及购物车条目的总金额
shoppingCartDiv.reCaculator = function(){
	$.each($("td[id=total]"),function(index,obj){
		$(obj).find("span").html(formatAmount(Number($(obj).find("input[name=cellTotal]").val()*$(obj).parent().find("input[name=itemqty]").val()),2));
	})
	var mark = 0;
	var qty = 0;
	var total= 0;
	$.each($("span[id=mark]"),function(index,obj){mark += Number($(obj).html().replace(/,/g,'')) * Number($(obj).parent().parent().find("input[name=itemqty]").val());})
	$.each($("td[id=qty]"),function(index,obj){qty += Number($(obj).find("input").val().replace(/,/g,''));})
	$.each($("td[id=total]"),function(index,obj){total += Number($(obj).find("span").html().replace(/,/g,''));})
	$('#countGoods').html(qty);
	$('#totalAmount').html(formatAmount(total,2));
	$('#saveAmount').html(formatAmount(mark-total,2));
}

//删除商品礼包
shoppingCartDiv.deleteGoodsGiftPrice = function(obj,goodsGiftCartItemId,agreePriceObjId,singlePrice){
	if(window.confirm('确定删除所选商品礼包?')) {
		$.getJSON($('#initPath').val()+'/ShoppingCartGoodsGiftController.do?method=remove',{objId:goodsGiftCartItemId},function(json){
			if(json.failure){alert(json.result);return;}
			var agreePrice = Number($("#"+agreePriceObjId).find("span").html().replace(/,/g,'')) - singlePrice;
			$("#"+agreePriceObjId).find("span").html(formatAmount(agreePrice,2));
			$("#"+agreePriceObjId).parent().find("input[name=cellTotal]").val(agreePrice);
			
			var tdObj = $(obj).parent().parent().parent();
			if($(tdObj).siblings().length == 0){
				$(tdObj).html("无商品礼包！");
			}else{
				$(tdObj).remove();//移除商品礼包
			}
			shoppingCartDiv.reCaculator();//重新计算总价
			var oldSize = $('input[name=giftSize]').val();
			$('input[name=giftSize]').val(oldSize-1);//礼包个数减一
		});
	}
}

//删除零配件
shoppingCartDiv.deleteGoodsAccessPrice = function(obj,goodsAccessCartItemId){
	if(window.confirm('确定删除所选零配件?')) {
		$.getJSON($('#initPath').val()+'/ShoppingCartGoodsAccessoriesController.do?method=remove',{objId:goodsAccessCartItemId},function(json){
			if(json.failure){alert(json.result);return;}
			var agreePrice = Number($("#"+agreePriceObjId).find("span").html().replace(/,/g,'')) - singlePrice;
			$("#"+agreePriceObjId).find("span").html(formatAmount(agreePrice,2));
			$("#"+agreePriceObjId).parent().find("input[name=cellTotal]").val(agreePrice);
			
			var tdObj = $(obj).parent().parent().parent();
			if($(tdObj).siblings().length == 0){
				$(tdObj).html("无零配件！");
			}else{
				$(tdObj).remove();//移除商品零配件
			}
			shoppingCartDiv.reCaculator();//重新计算总价
		});
	}
}

//显示或隐藏商品礼包
shoppingCartDiv.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}

//显示或隐藏商品零配件
shoppingCartDiv.showOrHideGoodsAccess = function(obj){
	$(obj).parent().parent().next().toggle();
}

//清空购物车
shoppingCartDiv.clearCart = function(){
	if(confirm("确定清空当前购物车？")){
	$.getJSON($('#initPath').val()+'/ShoppingCartController.do?method=removeAllByOrgInfo',{/*objId:PlatForm.userInfo.orgInfo.objId*/},function(json){
		if(json.failure){alert(json.result);return;}
		$('tbody tr','#goodsAndOption').remove();
		$('#countGoods').html('0');
		$('#totalAmount').html('0');
		$('#saveAmount').html('0');
		alert(json.result);
		getCartInfo();//刷新购物车信息
	});
	}
}

//删除一条记录
shoppingCartDiv.deleteItem = function(objId,cartId,supplierId){
	if(window.confirm('确定删除该商品吗？')){
		$.getJSON($('#initPath').val()+'/ShoppingCartController.do?method=removeShoppingItem',{objId:objId,'shoppingCart.objId':cartId},function(json){
			var nextTrObj = $('tr[id='+objId+']','#goodsAndOption').next();
			if(!$(nextTrObj).hasClass("goodsInfo")){//移除商品礼包\商品零配件行
				$(nextTrObj).remove();
			}
			$('tr[id='+objId+']','#goodsAndOption').remove();//移除删除行
			var obj = $("#goodsAndOption").find("input[type=checkbox][name*="+(supplierId!=null&&supplierId!=""?supplierId:"nosupplier")+"]");
			if(obj.length==1){$(obj).parent().remove();}
			getCartInfo();
			shoppingCartDiv.reCaculator();
		});
	}
}

//修改数量
shoppingCartDiv.updateQty = function(itemId,cartId,e,trId){
	if(isNaN($(e).val()+'') || $(e).val()==""){
		alert("请输入有效商品数量！");
		$(e).val($(e).attr("oldvalue"));
		return;
	}else{
		$(e).attr("oldvalue", $(e).val());
	}
	shoppingCartDiv.reCaculator();
	var carItem = {
			objId:itemId,
			goodsQty:$(e).val(),
			goodsTotal:$(e).parent().parent().find("td[id=total]").find("span").html().replace(/,/g,''),
			shoppingCart:{objId:cartId,goodsQty:$('#countGoods').html(),goodsTotal:$('#totalAmount').html().replace(/,/g,'') }
	};
	$.getJSON($("#initPath").val()+'/ShoppingCartItemController.do?method=updateGoodsChangeQtyAndMoney',{"cartItemStr":JSON.stringify(carItem)},function(json){
		if(json.success){
			//刷新购物车信息
			getCartInfo();
		}
	})
	carItem = null;//闭包内存泄露问题 by yucy
}

//直接订购
shoppingCartDiv.orderGoods = function(){
	if(!common.isHasRole('b')){alert("只有采购人才能做此操作！");return;};
	
	$.getJSON($("#initPath").val()+"/OrgInfoController.do?method=getIsAuditPass" ,{},function(json){
		if(json.isAudit=="true"){
			var selects = [];
			var hasNoSupplier =  "0";
			var supplier_ids = "";//供货商Id是
			$.each($("input[name*=checkCell]:checked"),function(index,obj){
				if($(obj).val()!="on"&&$(obj).val()!=null){
					selects.push($(obj).val());
					if(!$(obj).parent().find("input[name=cellSupplierId]").val()){
						hasNoSupplier  = "1";
					}
					if($(obj).parent().find("input[name=cellSupplierId]").val()){
						supplier_ids = supplier_ids + $(obj).parent().find("input[name=cellSupplierId]").val()+",";
					}
				}
			})
			if(hasNoSupplier == "1"){alert("直接订购的商品必须存在供应商！");return; }
			if(selects.length == 0){alert("请至少选中一件商品");return; }
			
			//保存关注我的客户
			$.getJSON($('#initPath').val()+"/ConcernController.do?method=saveClientConcern",{"orgInfoIds":supplier_ids},function(json){
			});
			/*小额交易的方法 */
			$('#conBody').loadPage($('#initPath').val()+"/OrderController.do?method=createOrder&carItem="+selects+"&createType=notask&appType=XEJY" ,function(){
				$("#returnUrl").val($("#initPath").val()+"/OrderController.do?method=toOrderBuyerList&appType=XEJY");
				//刷新购物车信息
				getCartInfo();
			});
			
			selects = null;//闭包内存泄露问题 by yucy
		}else{
			alert("通过机构审核才能直接订购！");
		}
	})
}

//发起议价(创建议价项目)
shoppingCartDiv.bargainGoods = function(){
	if(!common.isHasRole('b')){alert("只有采购人才能做此操作！");return;};
	
	$.getJSON($("#initPath").val()+"/OrgInfoController.do?method=getIsAuditPass" ,{},function(json){
		if(json.isAudit=="true"){
			var selects = [];
			var hasNoSupplier =  "0";
			var supplier_ids = "";//供货商Id是
			$.each($("input[name*=checkCell]:checked"),function(index,obj){
				if($(obj).val()!="on"&&$(obj).val()!=null){
					selects.push($(obj).val());
					if(!$(obj).parent().find("input[name=cellSupplierId]").val()){
						hasNoSupplier  = "1";
					}
					if($(obj).parent().find("input[name=cellSupplierId]").val()){
						supplier_ids = supplier_ids + $(obj).parent().find("input[name=cellSupplierId]").val()+",";
					}					
				}
			})
			if(hasNoSupplier == "1"){alert("议价的商品必须存在供应商！");return; }
			
			if(selects.length == 0){
				alert("请至少选中一件商品");return;
			}
			
			//保存关注我的客户
			$.getJSON($('#initPath').val()+"/ConcernController.do?method=saveClientConcern",{"orgInfoIds":supplier_ids},function(json){
			});	
			//创建议价项目
			if(confirm('确定要创建议价项目吗？')){
				/*小额交易*/
				$("#returnUrl").val($('#initPath').val()+'/BargainProjectController.do?method=orgProjectQueryListView');
				$('#conBody').loadPage($('#initPath').val()+"/TalkProjectController.do?method=toCreateTalkProjectView&cartItem="+selects, function(){
					getCartInfo();
				});
			}
			selects = null;//闭包内存泄露问题 by yucy
		}else{
			alert("通过机构审核才能发起议价！");
		}
	})
}

//发起竞价
shoppingCartDiv.createBargainProject = function(){
	if(!common.isHasRole('b')){alert("只有采购人才能做此操作！");return;};
	
	$.getJSON($("#initPath").val()+"/OrgInfoController.do?method=getIsAuditPass" ,{},function(json){
		if(json.isAudit=="true"){
			var selects = [];
			var giftFlag = false;
			$.each($("input[name*=checkCell]:checked"),function(index,obj){
				if($(obj).val()!="on"&&$(obj).val()!=null){
					selects.push($(obj).val());
					if($(obj).parent().find('input[name=giftSize]').val()>0) {
						giftFlag = true;
					}
				}
			})
			if(selects.length == 0){
				alert("请至少选中一件商品");return;
			}
			if(giftFlag) {
				alert('请注意：礼包信息不参与竞价项目的竞价,请删除购物车中的礼包!');
				giftFlag = false;
				return;
			}
			/*小额交易*/
			$("#returnUrl").val($('#initPath').val()+'/view/agreement/bargin/project/bargain/bargain_project_list.jsp');
			window.open($('#initPath').val()+"/BargainProjectController.do?method=toCreateBidProject_1&cartItem="+selects);
			
			selects = null;//闭包内存泄露问题 by yucy
		}else{
			alert("通过机构审核才能发起竞价！");
		}
	});
	

}

//全选或全不选事件
shoppingCartDiv.checkAllOrNot = function(name,e){
	$("input[name*="+name+"]").attr("checked",$(e).attr("checked"));
}

//继续购物
shoppingCartDiv.goonShopping = function(){
	window.location.href = $('#initPath').val()+"/GoodsShowController.do?method=toShowGoodsIndexView";
	return false;
}

$(document).ready(function(){
	//商品数量，控制不能输入非正整数
	$("input[name=itemqty]").inputFillter({type:'int'});
});
