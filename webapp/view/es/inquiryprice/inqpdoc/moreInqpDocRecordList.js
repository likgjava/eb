
var purchaseDocList={};


//加载数据成功之后调用的函数
purchaseDocList.success=function(){
	var auditStatus =$('#auditStatus').val();
	if(auditStatus=='00'){
	$("#purchaseDocGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">确认</a></span>'  : function(btn,rowId,obj){
			 btn.click(function(){
				 purchaseDocList.auditByBuyer(rowId);
			 }).appendTo(obj);
		}
	});
	}else if(auditStatus=='04'){
		$("#purchaseDocGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">修改</a></span>'  : function(btn,rowId,obj){
				 btn.click(function(){
					 purchaseDocList.modify(rowId);
				 }).appendTo(obj);
			}
		});
	}else if(auditStatus=='06'||auditStatus=='05'){
		$("#purchaseDocGrid").flexAddOptionStr({
		'<span><a href="#" class="abtn">审核</a></span>'  : function(btn,rowId,obj){
				 btn.click(function(){
					 purchaseDocList.audit(rowId);
				 }).appendTo(obj);
			}
		});
	}
}

//待确认的采购文件
purchaseDocList.auditByBuyer=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/InqpDocController.do?method=toPurchaseDocConfig&objId='+rowId);
}
//待修改的采购文件
purchaseDocList.modify=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/InqpDocController.do?method=toAgentMadePurchaseDoc&objId='+rowId);
}
//采购办审核
purchaseDocList.audit=function(rowId){
	$('#conBody').loadPage($('#initPath').val()+'/InqpDocController.do?method=toSuperviseAuditPurchaseDoc&objId='+rowId);
}

