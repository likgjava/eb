/**
 * 创建议价项目页面
 * create by likg
 */
var TalkProjectStartForm = {};

//保存或提交议价项目
TalkProjectStartForm.save = function(saveType){
	if(!$('#talkProjectStartForm').valid()){alert('请正确填写表单!');return;}
	
	//判断是否选择供应商
	if($("#talkSupplier").find("input:checked").length == 0){
		alert("请至少选择一个议价供应商！"); return;
	}
	
	//判断是不是都选了商品
	var goodsIsAllSelect = true;
	$.each($("input[name=goodsId]"),function(index,obj){
		if(!$(obj).val()){
			goodsIsAllSelect = false;
		}
	})
	if(!goodsIsAllSelect){
		alert("参与议价的条目必须选择商品！");return;
	}
	
	var tipMsg = ('submit'==saveType ? '提交' : '保存');
	if(!window.confirm('确定'+tipMsg+'吗?')){return;}
	
	if($('#evalStartTime').val().length==16) {
		$('#evalStartTime').val($('#evalStartTime').val() + ":00");
	}
	if($('#evalEndTime').val().length==16) {
		$('#evalEndTime').val($('#evalEndTime').val() + ":00");
	}
	
	var jsonObj = formToJsonObject('talkProjectStartForm');
	
	var cartItemIds = "";
	//需求条目对象
	var requireItemJson = [];
	$('#requireItemList tbody').find('tr[class=goodsInfo]').each(function(i,n){
		var index = $(n).attr('status');
		if($(n).find('input[id=goods'+index+']').val() != null && $(n).find('input[id=goods'+index+']').val() != "") {
			
			var goodsPrice = $(n).find('input[id=goodsPrice'+index+']').val();//单价
			var goodsQty = $(n).find('input[id=goodsQty'+index+']').val();//数量
			var unit = $(n).find('input[id=goodsUnit'+index+']').val();//计量单位
			var trId = $(n).attr('id');
			requireItemJson[i] = {};
			if(trId.indexOf("tempTR") == -1) {
				cartItemIds += trId + "_" + goodsPrice + "_" + goodsQty + "_" + unit+ ",";//购物车条目ID
			} else {
				var productName = $(n).find('span[id=productName'+index+']').text();//商品名称
				var marketPrice = $(n).find('input[id=marketPrice'+index+']').val();//市场价
				var agreePrice = $(n).find('input[id=agreePrice'+index+']').val();//协议价
				var goodsUnit = $(n).find('input[id=goodsUnit'+index+']').val();//计量单位
				var goodsTotal = $(n).find('input[id=goodsTotal'+index+']').val();//金额
				
				var categoryId = $(n).find('input[id*=categoryId]'); //品目id
				var categoryName = $(n).find('input[id*=categoryName]'); //品目id
				
				var goodsClassId = $(n).find('input[id*=goodsClassId]'); //分类id
				var goodsClassName = $(n).find('input[id*=goodsClassName]'); //分类id

				//拼装json数组对象
				requireItemJson[i].productName=productName;
				requireItemJson[i].marketPrice=marketPrice;
				requireItemJson[i].agreePrice=agreePrice;
				requireItemJson[i].goodsPrice=goodsPrice;
				requireItemJson[i].goodsQty=goodsQty;
				requireItemJson[i].goodsUnit=goodsUnit;
				requireItemJson[i].goodsTotal=goodsTotal;
				requireItemJson[i].goods = {};
				requireItemJson[i].goods.objId = $(n).find('input[id=goods'+index+']').val();
				
				if(categoryId!=null&&categoryId.val()!=null){
					requireItemJson[i].purCategory = {};
					requireItemJson[i].purCategory.objId = categoryId.val();
					requireItemJson[i].purCategory.categoryName = categoryName.val();
				}
				
				if(goodsClassId!=null&&goodsClassId.val()!=null){
					requireItemJson[i].goodsClass = {};
					requireItemJson[i].goodsClass.objId = goodsClassId.val();
					requireItemJson[i].goodsClass.goodsClassName = goodsClassName.val();
				}
				
			}
		}
	})
	jsonObj.require = JSON.stringify(requireItemJson);
	
	//项目任务书条目关联对象
	var taskJson = [];
	var taskArray = $('#taskItemIds').val().split(',');
	$.each(taskArray,function(i,n){
		taskJson[i] = {};
		taskJson[i].protaskItem = {};
		taskJson[i].protaskItem.objId = n;
	})
	jsonObj.task = JSON.stringify(taskJson);
	
	if(saveType == 'save') {
		jsonObj.auditStatus = '00';//临时
		jsonObj.useStatus = '00';//临时
	} else if(saveType == 'submit') {
		jsonObj.auditStatus = '01';//通过
		jsonObj.useStatus = '01';//有效
	}
	
	//议价供应商
	var supplierIds = "";
	$("#talkSupplier").find("input:checkbox").each(function(j,m){
		if($(m).attr("checked")){
			supplierIds += $(m).val() + ",";
		}
	})
	supplierIds = supplierIds.substring(0, supplierIds.length-1);
	jsonObj.supplierIds = supplierIds;
	
	//保存
	$.getJSON($('#initPath').val()+'/TalkProjectController.do?method=saveTalkProject&cartItemIds='+native2ascii(cartItemIds.substring(0,cartItemIds.length-1))+'&requirementIds='+($("#requirementIds").val()!=''?$("#requirementIds").val():''),jsonObj, function(json){
		if(json.failure){if(json.result)alert(json.result);return;}
		if(json.success){alert("操作成功！");}
		getCartInfo();
		//跳转至议价项目列表页面
		$("#return").click();
	})
}


//删除需求条目
function removeRequireItem(ctrl){
	if($("#requireItemList").find("input[name=goodsId]").length <= 1){
		alert("至少有一个需求条目!"); return ;
	}
	if(window.confirm('确定删除?')) {
		var nextTrObj = $(ctrl).parent().parent().next();
		if(!$(nextTrObj).hasClass("goodsInfo")){//移除商品礼包行
			$(nextTrObj).remove();
		}
		$(ctrl).parent().parent().remove();
		calcTotal();//计算总金额
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
		if(quantity < 1){
			alert("数量不得小于1！");
			quantity = 1;
			$(ctrl).parent().parent().find('input[id^=goodsQty]').val(quantity);
		}
		$(ctrl).parent().parent().find('input[id^=goodsTotal]').val(""+parseFloat(price * quantity));
		$(ctrl).parent().parent().find('span[id^=total]').text(""+formatAmount(parseFloat(price * quantity),2));
	}
	calcTotal();
}

//计算总金额
function  calcTotal() {
	var goodsQty = 0;
	var goodsTotal = 0.0;
	$('#requireItemList tbody tr[class=goodsInfo]').each(function(i,n){
		var index = $(n).attr("status");
		goodsQty += parseInt($(n).find('input[id=goodsQty'+index+']').val());//数量
		goodsTotal += parseFloat($(n).find('input[id=goodsTotal'+index+']').val())
	})
	$("#countGoods").text(goodsQty);
	$("#totalMoney").text(formatAmount(goodsTotal,2));
	$("#budgetTotalMoney").val(goodsTotal);//预算总金额
}

//显示或隐藏商品礼包
TalkProjectStartForm.showOrHideGoodsGift = function(obj){
	$(obj).parent().parent().next().toggle();
}

//选择商品
function selectGoods(e){
	var goodsIdReturn = $(e).parent().parent().find("input[name=goodsId]");
	var goodsNameReturn = $(e).parent().parent().find("input[name=goodsName]");
	var goodsRePriceReturn = $(e).parent().parent().find("input[name=goodsRePrice]");
	
	var goodsClassIdReturn = $(e).parent().parent().find("input[name=goodsClassId]");
	var goodsClassNameReturn = $(e).parent().parent().find("input[name=goodsClassName]");
	
	var goodsUnit = $(e).parent().parent().parent().find("input[name*=goodsUnit]");
	
	var categoryId = $(e).parent().parent().parent().find("input[name*=categoryId]").val();

	/**
	 * 参数    
	 * DialogId:	弹出层的id 关闭时用
	 * isCheckBox:	是否复选（不传为单选）
	 * domain:		要查的实体名称
	 * colums:		列表显示属性:逗号分隔(需存在于指定的domain中)
	 * returnColums:需回填属性:逗号分隔(需存在于指定的domain中  且默认回填的form表单id等于指定的属性名称  单选)
	 * defineRetuColums:指定回填的表单Id(如指定    指定顺序同returnColums 要一一对应 如未指定 则需回填表单Id必须与returnColums一一对应)
	 * columCns:	列表显示的中文表头:中文逗号分隔(因为国际化并未规范 所以也要传)
	 */
	var params = 'defineRetuColums='+goodsIdReturn.attr("id")+','+ goodsNameReturn.attr("id")+','+goodsRePriceReturn.attr("id")+','+goodsClassIdReturn.attr("id")+','+goodsClassNameReturn.attr("id")+','+goodsUnit.attr("id")
				+'&returnColums=objId,productName,referPrice,goodsClass.objId,goodsClass.goodsClassName,measureUnit'
				+'&DialogId=selectGoods'
				+'&isCheckBox=false'
				+'&colums=productName,referPrice,goodsClass.goodsClassName'
				+'&columCns=商品,参考价,分类'
				+'&domain=Goods'
				+'&queryParams="useStatus":"01","purCategory.objId":"'+categoryId+'","purCategory.objId_op":"like"';
	$.epsDialog({
		id:'selectGoods',
        title:'选择商品',
        url:$('#initPath').val()+'/view/agreement/mamagement/object_select_list.jsp?'+params,
        width: '700',
        height: '400',
        onClose:function(){
			$(e).parent().parent().find("span[id*=productName]").html(goodsNameReturn.val());
			$(e).parent().parent().parent().find("span[id*=marketPrice],span[id*=agreePrice]").html(goodsRePriceReturn.val());
			$(e).parent().parent().parent().find("input[id*=marketPrice],input[id*=agreePrice]").val(goodsRePriceReturn.val());
			$(e).parent().parent().parent().find("td:last").find("input[id*=goods]").val(goodsIdReturn.val());
		}
	}); 
}


$(document).ready(function(){
	
	$("#evalStartTime").epsDatepicker({ timeShow:true }).val(new Date().Format('yyyy-MM-dd hh:mm:00'));
	$("#evalEndTime").epsDatepicker({ timeShow:true });
	
	//项目名称唯一性验证
	$.validator.addMethod("projNameUnique",function(value,element,param){return uniqueHandler("Project",param,value,"");},'该项目已存在');
	$("#talkProjectStartForm").validate({
		rules:{
			projName:{projNameUnique:"projName"}
		}
	});	
	
	calcTotal();
	
	//添加需求条目
	$('#addRequireItem').click(function(){
		TalkProjectStartForm.cloneRequireItem();
	});
	
	//获得可选供应商
	var goodsIds = [];
	$.each($("#requireItemList").find("input[name=goodsId]") ,function(index,obj){
		if($(obj).val()){
			goodsIds.push($(obj).val());
		}
	});
	if(goodsIds.length>0){
		//获得议价供应商
		$.getJSON($('#initPath').val()+'/GoodsPriceController.do?method=getProvideSupplierByGoods&goodsIds='+goodsIds,{},function(supplier){
			var supplierIds = "";
			$.each(supplier.result,function(t,s){
				if(supplierIds.indexOf(s.objId) < 0 ){
					$("#talkSupplier").append("<li><input checked=checked type=checkbox value="+s.objId+" /><span>"+s.orgName+"</span></li>")
				}
				supplierIds += s.objId;
			});
		});
	}
	
	//获取报名的供应商
	if($("#requirementIds").val()){
		$.getJSON($("#initPath").val()+"/RequirementRegController.do?method=getRequirementRegList",{"requirementIds":$("#requirementIds").val()},function(json){
			var supplierIds = "";
			$.each(json.requirementRegList,function(index,obj){
				if(supplierIds.indexOf(obj.regOrg.objId) < 0 ){
					$("#talkSupplier").append("<li><input checked=checked type=checkbox value="+obj.regOrg.objId+" /><span>"+obj.regOrg.orgName+"</span></li>")
				}
				supplierIds += obj.regOrg.objId;
			});
		})
	}
	
	//保存
	$("#save").click(function(){
		TalkProjectStartForm.save('save');
	});
	
	//提交
	$("#submit").click(function(){
		TalkProjectStartForm.save('submit');
	});
});

