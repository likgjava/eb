var CreateGroupBuying = {};

//保存
CreateGroupBuying.saveGroupBuying = function(useStatus){
	//表单验证
	if(!$("#createGroupBuyingForm").valid()){ alert("请正确填写团购信息!");return; }
	if($('#startTime').val() >= $('#endTime').val()) { alert("团购开始时间不能大于团购结束时间!"); return; }
	
	//所有条件验证完毕更换按钮样式
	$('#submitDiv').addClass('hidden')
	$('#submittingDiv').removeClass('hidden');
	
	$('#useStatus').val(useStatus);
	
	$('#createGroupBuyingForm').ajaxSubmit({
		url: $('#initPath').val()+"/GroupBuyingController.do?method=saveGroupBuying",
		dataType:'json',
		success:function(json){
			if(json.failure){if(json.result)alert(json.result);return;}
			$('#sysContent').loadPage($('#initPath').val()+'/view/smallscale/groupbuying/submit_group_buying.jsp?useStatus='+useStatus+'&objId='+json.objId);
		},
		error:function(msg){
			alert(JSON.stringify(msg));
			$('#submitDiv').removeClass('hidden');
			$('#submittingDiv').addClass('hidden');
		}
	});
}

//格式化价格
CreateGroupBuying.formatPrice = function(){
	$("#showMarketPrice").html(formatAmount($("#marketPrice").val(),2));
	$("#showGroupPrice").html(formatAmount($("#groupPrice").val(),2));
}

//计算团购价
CreateGroupBuying.calculateGroupPrice = function(){
	var marketPrice = Number($("#marketPrice").val());
	var discount = Number($("#discount").val());
	var groupPrice = marketPrice * discount * 0.1;
	$("#groupPrice").val(formatAmount(groupPrice,2).replace(/,/g,''));
}
//计算折扣
CreateGroupBuying.calculateDiscount = function(){
	var marketPrice = Number($("#marketPrice").val());
	var groupPrice = Number($("#groupPrice").val());
	var discount = groupPrice / marketPrice * 10;
	$("#discount").val(formatAmount(discount,2).replace(/,/g,''));
}

$(document).ready(function(){
	
	//初始化时间
	$("#startTime").epsDatepicker({ timeShow:true });
	$("#endTime").epsDatepicker({ timeShow:true });

	//挑选商品
	$('#productName').click(function(){
		if($('#groupBuyingClassName').val() == ""){
			alert("请先选择团购分类！");
			$('#groupBuyingClassName').click();
			return ;
		}
		$.epsDialog({
	        title:'挑选商品',
	        width: 820,
	        url:$('#initPath').val()+'/view/smallscale/groupbuying/select_goods_list.jsp?HproductName=productName&HgoodsId=goodsId&HreferPrice=marketPrice&goodsClassId='+$("#goodsClassId").val(),
	        onClose: function(){
				$("#marketPrice").attr("referPrice",$("#marketPrice").val());
				CreateGroupBuying.formatPrice();
			}
		})
	});
	
	//选择团购分类（用来过滤商品）
	$('#groupBuyingClassName').click(function(){
		$.epsDialog({
			title:'挑选团购分类',
			width: 820,
			url:$('#initPath').val()+'/view/smallscale/groupbuying/select_group_buying_class.jsp?name=groupBuyingClassName&goodsClassId=goodsClassId',
			onClose: function(){
			}
		})
	});
	
	//更改市场价
	$('#marketPrice').keyup(function(){
		var marketPriceStr = $("#marketPrice").val();
		if(isNaN(marketPriceStr)){
			marketPriceStr = $("#marketPrice").attr("referPrice");
			$("#marketPrice").val(marketPriceStr);
		}
		
		//小数位多于2位
		if(marketPriceStr.indexOf('.')!=-1 && marketPriceStr.length-marketPriceStr.indexOf('.') > 3){
			$("#marketPrice").val(marketPriceStr.substring(0,marketPriceStr.length-1));
			return ;
		}
		
		if($('#groupPrice').val()!=""){
			CreateGroupBuying.calculateDiscount();
		}
		CreateGroupBuying.formatPrice();
	});
	//更改团购价
	$('#groupPrice').keyup(function(){
		var groupPriceStr = $("#groupPrice").val();
		if(isNaN(groupPriceStr)){
			$("#groupPrice").val("");
			$("#discount").val("");
			return ;
		}
		
		//小数位多于2位
		if(groupPriceStr.indexOf('.')!=-1 && groupPriceStr.length-groupPriceStr.indexOf('.') > 3){
			$("#groupPrice").val(groupPriceStr.substring(0,groupPriceStr.length-1));
			return ;
		}
		
		CreateGroupBuying.calculateDiscount();
		CreateGroupBuying.formatPrice();
	});
	//更改折扣
	$('#discount').keyup(function(){
		var discountStr = $("#discount").val();
		if(isNaN(discountStr)){
			$("#groupPrice").val("");
			$("#discount").val("");
			return ;
		}
		
		//小数位多于2位
		if(discountStr.indexOf('.')!=-1 && discountStr.length-discountStr.indexOf('.') > 3){
			$("#discount").val(discountStr.substring(0,discountStr.length-1));
			return ;
		}
		
		CreateGroupBuying.calculateGroupPrice();
		CreateGroupBuying.formatPrice();
	});
	
	//保存团购信息
	$('#saveGroupBuyingBtn').click(function(){
		CreateGroupBuying.saveGroupBuying("00");
	});
	//提交团购信息
	$('#submitGroupBuyingBtn').click(function(){
		CreateGroupBuying.saveGroupBuying("01");
	});
	
})
