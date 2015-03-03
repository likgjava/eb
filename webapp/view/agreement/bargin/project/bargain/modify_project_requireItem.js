var ReverseProjectCreateForm = {};

ReverseProjectCreateForm.save = function(saveType){
	if($('#signUpSTime').val() >= $('#signUpETime').val()) {
		alert("报名开始时间不能大于报名结束时间");
		return;
	}
	
	var msg = "";
	var jsonObj = formToJsonObject('ReverseProjectCreateForm');
	//需求条目对象
	var requireItemJson = [];
	var requireItemLength = 0;
	if($('#requireItemList_goods tr').length == 0) {
		requireItemLength = $('#requireItemList_desc tbody').find('tr').length;
		
		$('#requireItemList_desc tbody').find('tr').each(function(i,n){
			var index = $(n).attr('id').replace('tempTR','');
			var objId = $(n).find('input[id=objId'+index+']').val();
			var descr = $(n).find('textarea[id=descr'+index+']').val();//商品描述
			
			if($(n).find('input[name=goodsClass_purCategory.name]').val()=="") {
				msg = "请选择所属分类和品目";
			}
			
			var purCategory = $(n).find('input[name=purCategory.objId]').val();//品目ID
			var categoryName = $(n).find('input[name=purCategory.name]').val();//品目名称
			
			var goodsPrice = $(n).find('input[id=goodsPrice'+index+']').val();//价格
			var goodsQty = $(n).find('input[id=goodsQty'+index+']').val();//数量
			var goodsUnit = $(n).find('input[id=goodsUnit'+index+']').val();//单位
			var goodsTotal = $(n).find('input[id=goodsTotal'+index+']').val();//金额
			
			//拼装json数组对象
			requireItemJson[i] = {};
			requireItemJson[i].objId=objId;
			requireItemJson[i].descr=descr;
			
			requireItemJson[i].purCategory = {};
			requireItemJson[i].purCategory.objId = purCategory;
			requireItemJson[i].purCategory.categoryName = categoryName;
			
			requireItemJson[i].goodsPrice=goodsPrice;
			requireItemJson[i].goodsQty=goodsQty;
			requireItemJson[i].goodsUnit=goodsUnit;
			requireItemJson[i].goodsTotal=goodsTotal;
		})
	}else if($('#requireItemList_desc tr').length == 0) {
		requireItemLength = $('#requireItemList_goods tbody').find('tr').length;
		
		$('#requireItemList_goods tbody').find('tr').each(function(i,n){
			var index = $(n).attr('id').replace('tempTR_','');
			if($(n).attr('id').indexOf('tempTR') == -1) {
				index = parseInt(i+1);
			}
			if($(n).find('input[id=goods_'+index+']').val() != null && $(n).find('input[id=goods_'+index+']').val() != "") {
				var objId = $(n).find('input[id=objId_'+index+']').val();
				var goodsPrice = $(n).find('input[id=goodsPrice_'+index+']').val();//单价
				var goodsQty = $(n).find('input[id=goodsQty_'+index+']').val();//数量
				var productName = $(n).find('span[id=productName_'+index+']').text();//商品名称
				var marketPrice = $(n).find('input[id=marketPrice_'+index+']').val();//市场价
				var agreePrice = $(n).find('input[id=agreePrice_'+index+']').val();//协议价
				var goodsUnit = $(n).find('input[id=goodsUnit_'+index+']').val();//计量单位
				var goodsTotal = $(n).find('input[id=goodsTotal_'+index+']').val();//金额
				
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
				
				requireItemJson[i].purCategory = {};
				requireItemJson[i].purCategory.objId = purCategory;
				requireItemJson[i].purCategory.categoryName = categoryName;
				
				requireItemJson[i].goods = {};
				requireItemJson[i].goods.objId = $(n).find('input[id=goods_'+index+']').val();
			}
		})
	}
	if(requireItemLength  <= 0) {
		alert("请添加需求条目");
		return;
	}
	
	jsonObj.require = JSON.stringify(requireItemJson);

	if(saveType == 'save') {
		jsonObj.auditStatus = '00';//临时
		jsonObj.useStatus = '00';//临时
	} else if(saveType == 'submit') {
		jsonObj.auditStatus = '01';//通过
		jsonObj.useStatus = '01';//有效
	}
	
	if(msg != "") {
		alert(msg);
		return;
	}

	$('button[id=save]').attr('disabled','disabled');
	//保存、提交
	$.getJSON($('#initPath').val()+'/BargainProjectController.do?method=createProjectNoGoods',jsonObj, function(json){
		if(json.failure){if(json.result)alert(json.result);return;};
		alert('保存成功');
		$('button[id=save]').removeAttr('disabled');
		$('#project_info').loadPage($('#initPath').val()+"/BargainProjectController.do?method=toUpdateRequireInfo&objId="+$('#projectId').val());
	})
}

//克隆需求条目-desc
ReverseProjectCreateForm.cloneRequireItem = function() {
	var trSize;
	if($('#requireItemList_desc tbody').find('tr').length < 1) {
		trSize = 1;
	}else{
		trSize = parseInt($('#requireItemList_desc tr:last').attr('id').replace('tempTR','')) + 1;
	}
	var newTR = $('#tempTable').find('tr').clone(true);
	$(newTR).removeClass("hidden").attr('id','tempTR'+trSize);
	$(newTR).find('textarea[id=descr1]').attr('id','descr'+trSize).attr('name','descr'+trSize);//改变商品描述ID
	
	//初始化品目为空
	$(newTR).find('input[id=purCategoryId1]').val('');
	$(newTR).find('input[id=purCategoryName1]').val('');
	
	$(newTR).find('input[id=purCategoryId1]').attr('id','purCategoryId'+trSize);
	$(newTR).find('input[id=purCategoryName1]').attr('id','purCategoryName'+trSize);
	
	$(newTR).find('input[id=goodsPrice1]').attr('id','goodsPrice'+trSize).attr('name','goodsPrice'+trSize);//改变商品价格ID
	$(newTR).find('input[id=goodsQty1]').attr('id','goodsQty'+trSize).attr('name','goodsQty'+trSize);//改变商品数量ID
	$(newTR).find('input[id=goodsUnit1]').attr('id','goodsUnit'+trSize).attr('name','goodsUnit'+trSize);//改变商品单位ID
	$(newTR).find('input[id=goodsTotal1]').attr('id','goodsTotal'+trSize).attr('name','goodsTotal'+trSize);//改变商品金额ID
	
	$("#requireItemList_desc tbody").append(newTR);
}
//克隆需求条目-goods
ReverseProjectCreateForm.cloneRequireItem_g = function() {
	var trSize;
	if($('#requireItemList_goods tbody').find('tr').length < 1) {
		trSize = 1;
	}else{
		if($('#requireItemList_goods tr:last').attr('id').indexOf('tempTR_') == -1) {
			trSize = $('#requireItemList_goods tbody').find('tr').length + 1;
		}else{
			trSize = parseInt($('#requireItemList_goods tr:last').attr('id').replace('tempTR_','')) + 1;
		}
	}
	var newTR = $('#tempTable_').find('tr').clone(true);
	$(newTR).removeClass("hidden").attr('id','tempTR_'+trSize);
	$(newTR).find('span[id=productName_1]').attr('id','productName_'+trSize);//商品名称
	$(newTR).find('input[id=marketPrice_1]').attr('id','marketPrice_'+trSize).attr('name','marketPrice_'+trSize);//市场价
	$(newTR).find('input[id=agreePrice_1]').attr('id','agreePrice_'+trSize).attr('name','agreePrice_'+trSize);//协议价
	$(newTR).find('input[id=goodsPrice_1]').attr('id','goodsPrice_'+trSize).attr('name','goodsPrice_'+trSize);//单价
	$(newTR).find('input[id=goodsQty_1]').attr('id','goodsQty_'+trSize).attr('name','goodsQty_'+trSize);//商品数量
	$(newTR).find('input[id=goodsUnit_1]').attr('id','goodsUnit_'+trSize).attr('name','goodsUnit_'+trSize);//商品数量
	$(newTR).find('input[id=goodsTotal_1]').attr('id','goodsTotal_'+trSize).attr('name','goodsTotal_'+trSize);//商品金额
	$(newTR).find('input[id=goods_1]').attr('id','goods_'+trSize).attr('name','goods_'+trSize);//商品ID
	
	//初始化分类和品目为空
	$(newTR).find('input[id=purCategory_1]').val('');
	$(newTR).find('input[id=categoryName_1]').val('');
	
	$(newTR).find('input[id=purCategory_1]').attr('id','purCategory_'+trSize).attr('name','purCategory_'+trSize);//品目ID
	$(newTR).find('input[id=categoryName_1]').attr('id','categoryName_'+trSize).attr('name','categoryName_'+trSize);//品目名称
	
	$("#requireItemList_goods tbody").append(newTR);
}

//挑选商品
function selectGood(ctrl) {
	var ctrlId = $(ctrl).parent().parent().find('span[id^=productName]').attr('id');
	$.epsDialog({
	        title:'挑选商品',
	        url:$('#initPath').val()+'/view/agreement/bargin/project/bargain/goods_select_list.jsp?ctrlId='+ctrlId,
	        width: 800,
			height: 400    
	})
}

//删除需求条目
function removeRequireItem(ctrl,type,objId){
	if(window.confirm('确定删除?')) {
		$(ctrl).parent().parent().remove();
		if(objId) {
			$.getJSON($('#initPath').val()+'/RequireItemController.do?method=remove',{"objId":objId},function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
			});
		}
		if(type=='desc') {
			calcTotal();
		}else {
			calcTotal_();
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
	
	calcTotal();
}

//计算行金额和总金额、总数量
function changeTotal_(ctrl,numberType){
	var price = $(ctrl).parent().parent().find('input[id^=goodsPrice_]').val();
	var quantity = $(ctrl).parent().parent().find('input[id^=goodsQty_]').val();
	if(numberType=='float') {
		price = parseFloat($(ctrl).val());
	} else {
		quantity = parseInt($(ctrl).val());
	}
	if(price+'' != "NaN" && quantity+'' != "NaN") {
		$(ctrl).parent().parent().find('input[id^=goodsTotal_]').val(""+parseFloat(price * quantity));
		$(ctrl).parent().parent().find('span[id^=goodsTotal_]').text(""+parseFloat(price * quantity));
	}
	calcTotal_();
}

//计算总金额-goods
function  calcTotal_() {
	var goodsQty = 0;
	var goodsTotal = 0.0;
	$('#requireItemList_goods tbody').find('tr').each(function(i,n){
		if($(n).attr('id') != 'accTR1' && $(n).attr('id') != 'accTR2') {
			var index = $(n).attr('id').replace('tempTR_','');
			if($(n).attr('id').indexOf('tempTR') == -1) {
				index = parseInt(i+1);
			}
			goodsQty += parseInt($(n).find('input[id=goodsQty_'+index+']').val());//数量
			goodsTotal += parseFloat($(n).find('input[id=goodsTotal_'+index+']').val())
		}
	})
	$("#countGoods").text(goodsQty);
	$("#totalMoney").text(formatAmount(goodsTotal,2));
	$("#budgetTotalMoney").val(goodsTotal);//预算总金额
}

//计算总金额-desc
function  calcTotal() {
	var goodsQty = 0;
	var goodsTotal = 0.0;
	$('#requireItemList_desc tbody').find('tr').each(function(i,n){
		var index = $(n).attr('id').replace('tempTR','');
		goodsQty += parseInt($(n).find('input[id=goodsQty'+index+']').val());//数量
		goodsTotal += parseInt($(n).find('input[id=goodsQty'+index+']').val())*parseFloat($(n).find('input[id=goodsPrice'+index+']').val());
	})
	$("#countGoods").text(goodsQty);
	$("#totalMoney").text(formatAmount(goodsTotal,2));
	$("#budgetTotalMoney").val(goodsTotal);//预算总金额
}
//判断是否选择商品,以及商品数量是否为0
function checkGoods() {
	var tempV = "";
	if($('#requireItemList_goods tr').length == 0) {
		$('#requireItemList_desc tbody').find('tr').each(function(i,n){
			var index = $(n).attr('id').replace('tempTR','');
			if($(n).find('input[name=goodsClass.objId]').val()=="") {
				tempV = "请选择商品分类";
			}else {
				if($(n).find('input[id=goodsQty'+index+']').val() == 0) {
					tempV = '请输入商品数量';
				}
			}
		})
	}else if($('#requireItemList_desc tr').length == 0) {
		$('#requireItemList_goods tbody').find('tr').each(function(i,n){
			if($(n).attr('id').indexOf('tempTR_') != -1) {
				var index = $(n).attr('id').replace('tempTR','');
				if($(n).find('input[id=goods'+index+']').val() == null || $(n).find('input[id=goods'+index+']').val() == "") {
					tempV = '请选择商品';
				}else {
					if($(n).find('input[id=goodsQty'+index+']').val() == 0) {
						tempV = '请输入商品数量';
					}
				}
			}
		})
	}
	return tempV;
}

//显示或隐藏商品零配件
function showOrHideGoodsAccess(obj){
	$(obj).parent().parent().next().toggle();
}

//挑选品目
function selectCategory(index){
	$.epsDialog({
        title:'选择品目',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&IDS=purCategoryId'+index+'&NAMES=purCategoryName'+index+'&className=PurCategory&action=listTop&childNodeOnly=true'
    }); 
}

$(document).ready(function(){
	$("#signUpSTime").epsDatepicker({ timeShow:true });
	$("#signUpETime").epsDatepicker({ timeShow:true });
	
	//选择品目
	$("input[name=purCategory.name]").click(function(){
	    selectCategory($(this).attr('id').replace('purCategoryName',''));
	});
	
	//项目名称唯一性验证
	$.validator.addMethod("projNameUnique",function(value,element,param){return uniqueHandler("Project",param,value,$('#objId').val());},'该项目已存在');
	$("#ReverseProjectCreateForm").validate({
		rules:{
			projName:{projNameUnique:"projName"}
		}
	});	
	
	//根据是否有商品判断加载和克隆对象
	var goodsId = $('#goodsId').val();
	if(goodsId != "" && goodsId != null) {
		$('#requireItemList_goods').removeClass('hidden');
		calcTotal_();
	}else {
		calcTotal();
		$('#requireItemList_desc').removeClass('hidden');
	}
	
	//添加需求条目-desc
	$('#addRequireItem').click(function(){
		if($('#requireItemTB tr').length >= 5){
			alert("已到达最大需求条目数！");
		}else{
			ReverseProjectCreateForm.cloneRequireItem();
		}
	})
	//添加需求条目-goods
	$('#addRequireItem_g').click(function(){
		if($('#goodsAndOption tr').length >= 5){
			alert("已到达最大需求条目数！");
		}else{
			ReverseProjectCreateForm.cloneRequireItem_g();
		}
	})
	
	//保存
	$("#save").click(function(){
		if($('#requireItemList_desc').attr('class').indexOf('hidden') == -1) {
			$('#requireItemList_goods tr').remove();
		}else if($('#requireItemList_goods').attr('class').indexOf('hidden') == -1) {
			$('#requireItemList_desc tr').remove();
		}
		if(!$('#ReverseProjectCreateForm').valid()){alert('请正确填写表单!');return;}
		
		if(window.confirm('确定保存吗?')) {
			var msg = checkGoods();
			if(msg != "") {
				alert(msg);
			}else{
				ReverseProjectCreateForm.save('save');
			}
		}
	});
	//提交
	$("#submit").click(function(){
		if($('#requireItemList_desc').attr('class').indexOf('hidden') == -1) {
			$('#requireItemList_goods tr').remove();
		}else if($('#requireItemList_goods').attr('class').indexOf('hidden') == -1) {
			$('#requireItemList_desc tr').remove();
		}
		if(!$('#ReverseProjectCreateForm').valid()){alert('请正确填写表单!');return;}
		
		if(window.confirm('确定提交吗?')) {
			var msg = checkGoods();
			if(msg != "") {
				alert(msg);
			}else{
				ReverseProjectCreateForm.save('submit');
			}
		}
	});
});

