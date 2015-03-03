var TalkProjectCreateForm = {};

TalkProjectCreateForm.save = function(saveType){
	if(!$('#talkProjectCreateForm').valid()){alert('请正确填写表单!');return;}
	
	var jsonObj = formToJsonObject('talkProjectCreateForm');
	//需求条目对象
	var requireItemJson = [];
	
	$('#requireItemList_goods tbody').find('tr[class=goodsInfo]').each(function(i,n){
		var index = $(n).attr('status');
		if($(n).find('input[id=goods_'+index+']').val() != null && $(n).find('input[id=goods_'+index+']').val() != "") {
			var objId = $(n).find('input[id=objId_'+index+']').val();
			var goodsPrice = $(n).find('input[id=goodsPrice_'+index+']').val();//单价
			var goodsQty = $(n).find('input[id=goodsQty_'+index+']').val();//数量
			var productName = $(n).find('span[id=productName_'+index+']').text();//商品名称
			var marketPrice = $(n).find('input[id=marketPrice_'+index+']').val();//市场价
			var agreePrice = $(n).find('input[id=agreePrice_'+index+']').val();//协议价
			var goodsUnit = $(n).find('input[id=goodsUnit_'+index+']').val();//计量单位
			var goodsTotal = $(n).find('input[id=goodsTotal_'+index+']').val();//金额
			
			var goodsClass = $(n).find('input[id=goodsClass_'+index+']').val();//分类ID
			var goodsClassName = $(n).find('input[id=goodsClassName_'+index+']').val();//分类名称
			var purCategory = $(n).find('input[id=purCategory_'+index+']').val();//品目ID
			var categoryName = $(n).find('input[id=categoryName_'+index+']').val();//品目名称
			
			//拼装json数组对象
			requireItemJson[i] = {};
			requireItemJson[i].objId=objId;
			requireItemJson[i].productName=productName;
			requireItemJson[i].marketPrice=marketPrice;
			requireItemJson[i].agreePrice=agreePrice;
			requireItemJson[i].goodsPrice=goodsPrice;
			requireItemJson[i].goodsQty=goodsQty;
			requireItemJson[i].goodsUnit=goodsUnit;
			requireItemJson[i].goodsTotal=goodsTotal;
			requireItemJson[i].goods = {};
			requireItemJson[i].goods.objId = $(n).find('input[id=goods_'+index+']').val();
			
			requireItemJson[i].goodsClass = {};
			requireItemJson[i].goodsClass.objId = goodsClass;
			requireItemJson[i].goodsClass.goodsClassName = goodsClassName;
			
			requireItemJson[i].purCategory = {};
			requireItemJson[i].purCategory.objId = purCategory;
			requireItemJson[i].purCategory.categoryName = categoryName;
		}
	});
	
	jsonObj.require = JSON.stringify(requireItemJson);
	
	if(saveType == 'save') {
		jsonObj.auditStatus = '00';//临时
		jsonObj.useStatus = '00';//临时
	} else if(saveType == 'submit') {
		jsonObj.auditStatus = '01';//通过
		jsonObj.useStatus = '01';//有效
	}
	
	//保存、提交
	$.getJSON($('#initPath').val()+'/TalkProjectController.do?method=updateTalkProject', jsonObj, function(json){
		if(json.failure){if(json.result)alert(json.result);return;}
		alert('操作成功！');
		$('#talkProjectReturnBut').click(); //刷新议价项目详情页面
	})
}

//删除需求条目
function removeRequireItem(ctrl,objId){
	if($('#requireItemList_goods tbody').find('input[id^=goods_]').length <= 1){
		alert("至少有一个需求条目！"); return ;
	}
	
	if(window.confirm('确定删除?')) {
		if(objId) {
			$.getJSON($('#initPath').val()+'/RequireItemController.do?method=remove', {"objId":objId}, function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
				var nextTrObj = $(ctrl).parent().parent().next();
				if(!$(nextTrObj).hasClass("goodsInfo")){//移除商品礼包行
					$(nextTrObj).remove();
				}
				$(ctrl).parent().parent().remove();//移除商品行
				
				calcTotal_(); //计算总金额
			});
		}
	}
}

//计算行金额和总金额、总数量
function changeTotal(ctrl,numberType){
	var price = $(ctrl).parent().parent().find('input[id^=goodsPrice]').val();
	var quantity = $(ctrl).parent().parent().find('input[id^=goodsQty]').val();
	if(numberType=='float') {
		price = parseFloat($(ctrl).val());
	} else {
		quantity = parseInt($(ctrl).val());
	}
	if(price+'' != "NaN" && quantity+'' != "NaN") {
		$(ctrl).parent().parent().find('input[id^=goodsTotal]').val(""+parseFloat(price * quantity),2);
	}
	
	var goodsQty = 0;
	var goodsPrice = 0.0;
	var goodsTotal = 0.0;
	//计算总金额和总数量
	$('#requireItemList_desc tbody').find('tr[class=goodsInfo]').each(function(i,n){
		var index = i + 1;
		goodsQty += parseInt($(n).find('input[id=goodsQty'+index+']').val());//数量
		goodsTotal += parseInt($(n).find('input[id=goodsQty'+index+']').val())*parseFloat($(n).find('input[id=goodsPrice'+index+']').val());
	})
	$("#countGoods").text(goodsQty);
	$("#totalMoney").text(formatAmount(goodsTotal,2));
}

//计算行金额和总金额、总数量
function changeTotal_(ctrl,numberType){
	if(isNaN($(ctrl).val()) || $(ctrl).val()=='' || Number($(ctrl).val())<1){
		alert('请填写有效数据！');
		$(ctrl).val($(ctrl).attr('bakup'));
		return ;
	}else{
		$(ctrl).attr('bakup',$(ctrl).val())
	}
		
	var price = $(ctrl).parent().parent().find('input[id^=goodsPrice_]').val();
	var quantity = $(ctrl).parent().parent().find('input[id^=goodsQty_]').val();
	if(numberType=='float') {
		price = parseFloat($(ctrl).val());
	} else {
		quantity = parseInt($(ctrl).val());
	}
	if(price+'' != "NaN" && quantity+'' != "NaN") {
		$(ctrl).parent().parent().find('input[id^=goodsTotal_]').val(""+parseFloat(price * quantity));
		$(ctrl).parent().parent().find('span[id^=goodsTotal_]').text(""+formatAmount(parseFloat(price * quantity),2));
	}
	calcTotal_();
}

//计算总金额-goods
function  calcTotal_() {
	var goodsQty = 0;
	var goodsTotal = 0.0;
	$('#requireItemList_goods tbody').find('tr[class=goodsInfo]').each(function(i,n){
		var index = $(n).attr("status");
		goodsQty += parseInt($(n).find('input[id=goodsQty_'+index+']').val());//数量
		goodsTotal += parseFloat($(n).find('input[id=goodsTotal_'+index+']').val())
	})
	$("#countGoods").text(goodsQty);
	$("#totalMoney").text(formatAmount(goodsTotal,2));
}

//显示或隐藏商品礼包
TalkProjectCreateForm.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}

$(document).ready(function(){
	$("#evalStartTime").epsDatepicker({ timeShow:true });
	$("#evalEndTime").epsDatepicker({ timeShow:true });
	
	//计算总金额
	calcTotal_();
	
	//表单验证
	$("#talkProjectCreateForm").validate();
	
	//保存
	$("#save").click(function(){
		TalkProjectCreateForm.save('save');
	});
	//提交
	$("#submit").click(function(){
		TalkProjectCreateForm.save('submit');
	});
	
	//返回
	$("#talkProjectReturnBut").click(function(){
		window.location.reload();
	});
});

