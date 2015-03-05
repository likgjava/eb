var GroupBuyerForm={};

//保存团购采购人信息
GroupBuyerForm.saveGroupBuyer = function(){
	//表单验证
	if(!$("#groupBuyerForm").valid()){ alert("请正确填写表单!");return; }

	$("#provinceId").val($("select[id=province]").val());

	$("#saveGroupBuyerBut").attr("disabled",true);
	var url = $('#initPath').val()+"/GroupBuyerController.do?method=saveGroupBuyer";
	$('#groupBuyerForm').ajaxSubmit({
		url:url,
		dataType:'json',
		success:function(json){
			window.location.href = $('#initPath').val()+'/GroupBuyingShowController.do?method=toGroupBuyingPayView&groupBuyerId='+json.objId;
			return false;
		},
		error:function(msg){
			alert(JSON.stringify(msg));
			$("#saveGroupBuyerBut").attr("disabled",false);
		}
	});
}

//修改团购数量
GroupBuyerForm.updateAmount = function(oper){
	var amountStr = $("#amount").val();
	if(isNaN(amountStr) || amountStr=='' || amountStr=='0'){
		amountStr = 1;
		$("#amount").val(amountStr)
	}
	var amount = Number(amountStr);
	
	//有最大团购数量限制
	if($("#maxNumber").val() != ""){
		var maxNumber = Number($("#maxNumber").val()); //最大团购数
		var currentNumber = Number($("#currentNumber").val()); //当前团购数
		var remainNumber = maxNumber - currentNumber; //剩余数量
		
		//已卖完
		if(remainNumber <= 0){
			alert("对不起，该商品已卖完！");
			$("#amount").val("0");
			return ;
		}
		
		if(oper == "+"){
			if(amount >= remainNumber){
				alert("该商品只剩余"+remainNumber+"件，你最多可以购买"+remainNumber+"件。");
				return ;
			}
			amount = amount + 1;
		}else if(oper == "-"){
			if(amount <= 1){return ;}
			amount = amount - 1;
		}else{
			if(amount > remainNumber){
				alert("该商品只剩余"+remainNumber+"件，你最多可以购买"+remainNumber+"件。");
				amount = remainNumber;
			}
		}
	}else{
		if(oper == "+"){
			amount = amount + 1;
		}else if(oper == "-"){
			if(amount <= 1){return ;}
			amount = amount - 1;
		}
	}
	
	$("#amount").val(amount);
	GroupBuyerForm.calculateTotalMoney();
}

//计算总金额
GroupBuyerForm.calculateTotalMoney = function(){
	var amount = $("#amount").val();
	var unitPrice = $("#groupPrice").val();
	var totalPrice = amount * unitPrice;
	$("#totalPrice").html(formatAmount(totalPrice,2));
}

$(document).ready(function(){
	//选中顶部菜单
	changeTabsCss("goToGoodsMall");
	
	//表单验证
	$("#groupBuyerForm").validate();
	
	//设置省份的选中值
    $("#province").val($("#provinceId").val());
	
	//计算总金额
	GroupBuyerForm.calculateTotalMoney();
    
	//保存团购采购人信息
	$("#saveGroupBuyerBut").click(function(){
		GroupBuyerForm.saveGroupBuyer();
	});
})