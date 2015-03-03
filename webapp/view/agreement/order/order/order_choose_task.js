//定义文件全局变量 处理方法名重复问题
var orderChooseTask={};
orderChooseTask.oTable;	

//保存
orderChooseTask.save=function(){
	if(confirm("确定保存吗?")){
		var sumQty = 0; //抵消数量
		var sumTotal = 0;//抵消金额
		$('#OrderTaskItemList').find('input:text').each(function(i,n){
			sumQty += eval($(n).val());
		})
			var selects = $("#OrderTaskItemList").dtSelects();
			if(selects.length == 0){
				alert("请至少选中一个任务书条目!");return;
			}
			if(sumQty != $("#orderGoodsQty").val()){
				alert("商品数量为：" + $("#orderGoodsQty").val() + ",所选数量与订单商品数量不符合!");
				return;
			}
			var temp = selects.split(',');
			for(var i=0; i<temp.length; i++){
				var inputQty = $("#inp_"+temp[i]).val();
				
				var dataObj = {};
				dataObj.orderItem = {};
				dataObj.orderItem.objId = $("#orderItemId").val();
				dataObj.protaskItem = {};
				dataObj.protaskItem.objId = temp[i];
				dataObj.orderTaskQty = inputQty;
				dataObj.orderTaskTotal = inputQty * $("#orderGoodsPrice").val();
				
				$.ajax({
					url: $("#initPath").val()+"/OrderProtaskController.do?method=saveOrderProtask",
					type:"POST",
					data:{orderProtaskStr:JSON.stringify(dataObj)},
					dataType:'json',
					success:function(msg){
						$("#conBody").loadPage($("#initPath").val()+"/OrderController.do?method=toOrderForm&appType=XYGH&type=edit_toSubmit&objId="+$("#objId").val());
						$("#close").click();
					},
					error:function(msg){
						alert('操作失败');
					}
				})
			}
	}
}

//文本框改变事件
orderChooseTask.inputChange=function(inputDom,aData){
	var qtyAll = 0;
	$('#OrderTaskItemList').find('input:text').each(function(j,m){
		qtyAll += parseInt($(m).val());
	})
	if(qtyAll*1 > $("#orderGoodsQty").val()){
		alert("抵消数量大于订单中商品数量！");
		$(inputDom).val(0);
		$(inputDom).focus();
		return;
	}
	if($(inputDom).val()*1 > aData.finQty*1){
		alert("抵消数量大于任务书剩余数量！");
		$(inputDom).val(0);
		$(inputDom).focus();
		return;
	}
	if($(inputDom).val() * $("#orderGoodsPrice").val() > aData.finTotal*1){
		alert("抵消金额大于任务书剩余金额！");
		$(inputDom).val(0);
		$(inputDom).focus();
		return;
	}
}

//根据可用金额和单价，返回最佳数量
orderChooseTask.getAmout=function(total,amount){
	var _amount = total / $("#orderGoodsPrice").val()|0;
	if(amount && amount*1 < _amount)
		return amount;
	else
		return _amount;
}

$(document).ready(function(){
	
	var orderGoodsQty = $("#orderGoodsQty").val();//商品数量
	
	orderChooseTask.oTable=$('#OrderTaskItemList').dataTable( {
		'singleSelect':false,
		'checkbox':true,
		'queryColumns':'goodsTotal,goodsQty,finQty,finTotal,goodsPrice',
		'hiddenColums':'',
		//'bPaginate':false, 
		'fnInitComplete':function(oSettings) {
		},
		'fnDrawCallback':function(oSettings) {
			orderChooseTask.oTable.oSettings=oSettings;
		},
		'fnRowCallback': function( nRow, aData, iDisplayIndex ) {
		    $(nRow).append("<td class='center'><input type='text' value='0' style='width:30px;text-align:right' id='inp_"+aData.objId+"'/></td>");//抵消数量
		    $(nRow).find('td').find('input:text').blur(function(){orderChooseTask.inputChange(this,aData);});
		    $(nRow).each(function(i,n){
		    	$(n).click(function(){  //给行添加点击事件
		    		var qtyAll = 0;  //取所有文本框值之和
		    		var finQty = aData.finQty * 1;  //每行的剩余数量
		    		var finTotal = aData.finTotal * 1;  //每行的剩余金额
		    		$('#OrderTaskItemList').find('input:text').each(function(j,m){
		    			qtyAll += parseInt($(m).val());
		    		})
		    		if($(n).find('input:checkbox').attr("checked")){
		    			//如果可用剩余数量为0，则不选中
			    		if(orderGoodsQty - qtyAll == 0 || finTotal < $("#orderGoodsPrice").val() *1 ){
			    			if(orderGoodsQty - qtyAll == 0)
			    				alert("所选任务书的抵消商品数量已经全部用完！");
			    			else
			    				alert("所选任务书的剩余金额不足！");
			    			$(n).find('input:checkbox').attr("checked",false);
			    			$(n).removeClass("row_selected");
			    			return false;
			    		}
			    		//自动计算数量
		    			if(finQty < orderGoodsQty - qtyAll){
		    				$(n).find('input:text').val(orderChooseTask.getAmout(finTotal,finQty));//抵消数量=剩余数量
						}else{
							$(n).find('input:text').val(orderChooseTask.getAmout(finTotal,orderGoodsQty - qtyAll));//抵消数量=商品数量
						}
		    		}
		    		else{
		    			$(n).find('input:text').val(0);
		    		}
	    		})
		    })
			return nRow;
		},
		'params':{"purCategory.objId":$("#purCategoryId").val(),"finQty":"0","finQty_op":"!="},//过滤当前品目并且剩余数量大于零
		"sAjaxSource": $('#initPath').val()+"/ProtaskItemController.do?method=list&tochoose=true"
	});
	
	$('.amount').inputFillter({reg:'',type:''});

	//保存
	$("#saveOrderTask").click(function(){
		orderChooseTask.save();
	})
	
	//关闭
	$("#close").click(function(){
		$("#orderTaskItem").find('.epsDialogClose').trigger('click');
	})
});
	
