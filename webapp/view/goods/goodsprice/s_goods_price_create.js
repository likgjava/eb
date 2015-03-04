var goodsPriceCreate = {};

//选择使用区域
goodsPriceCreate.selectDistrict = function(){
    $.epsDialog({
    	id:'districtDiv',
        title:'选择使用区域',
        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=district&className=District&action=listTop&isOpen=1&dialogId=districtDiv'
    }); 
}

//价格 折扣 协议价 的改变
goodsPriceCreate.priceChange = function(type,e){
	//判断数字
	if(isNaN($(e).val())){
		alert("请输入数字！");
		$(e).val("");
		return ;
	}
	//单价的改变
	if('unitPrice'==type){
		if(Number($("#unitPrice").val().replace(/,/g,''))>Number($("span[id=referPrice]").html().replace(/,/g,''))){
			alert("不得大于参考价！");$("#unitPrice").val($("span[id=referPrice]").html().replace(/,/g,''));return;
		}
		$("#dscuRate").val()!=""?$("#prtcPrice").val(getPrtcPrice($("#unitPrice").val(),$("#dscuRate").val()).replace(/,/g,'')):$("#prtcPrice").val("")
		$("#unitPrice").val(formatAmount($("#unitPrice").val(),2).replace(/,/g,''));	
	}
	//折扣的改变
	else if('dscuRate'==type){
		$("#unitPrice").val()!=""?$("#prtcPrice").val(getPrtcPrice($("#unitPrice").val(),$("#dscuRate").val()).replace(/,/g,'')):$("#prtcPrice").val("")
	}
	//协议价的改变
	else if('prtcPrice'==type){
		$("#dscuRate").val(getDscuRate($("#unitPrice").val().replace(/,/g,''),$("#prtcPrice").val().replace(/,/g,'')));
	}
}


//保存或提交
goodsPriceCreate.saveOrSubmit = function(opType){
	if(!$('#priceForm').valid()){alert('请正确填写表单!');return;}
	
	var priceJson = formToJsonObject("priceForm");
	priceJson.goodsId = $("#GoodsDetailForm").find("input[id=objId]").val();
	priceJson.OptFitPriceJson = "";
	priceJson.opType = opType;
	//拼装js串
	$.each($("#priceForm").find("input[name^=relativePrice]"),function(index,obj){
		if($(obj).val()){
			if(index==0||priceJson.OptFitPriceJson==""){
				priceJson.OptFitPriceJson += $(obj).parent().find("input[name^=optId]").val()+":"+$(obj).val().replace(/,/g,'');
			}else{
				priceJson.OptFitPriceJson += ","+$(obj).parent().find("input[name^=optId]").val()+":"+$(obj).val().replace(/,/g,'');
			}
		}
	})
	$.getJSON($("#initPath").val()+"/GoodsPriceController.do?method=savePrice",priceJson,function(json){
		if(json.success){
			alert("操作成功！");
			$("#priceSupplierId").val(json.goodsPrice.goodsPriceSupplier.objId);
			$('.epsDialogClose').trigger('click');
			goodsPriceForm.oTable.fnDraw();
		}else{
			alert("操作失败！");
		}
	})
}

//保存行情
goodsPriceCreate.savePrice = function(){
	goodsPriceCreate.saveOrSubmit("save");
}

//提交行情
goodsPriceCreate.submitPrice = function(){
	goodsPriceCreate.saveOrSubmit("submit");
}

//关闭
goodsPriceCreate.close = function(){
	$('.epsDialogClose').trigger('click');
}

$(document).ready(function(){
	$('#priceForm').validate();

	//开始时间
    $("#efctDate").epsDatepicker({applyRule: endRule });  //增加结束时间的规则
    //结束时间
    $("#endDate").epsDatepicker({applyRule: startRule });  //增加开始时间的规则
    
})