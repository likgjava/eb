
//定义文件全局变量 处理方法名重复问题
var procurementTaskForm={};

//时期类型转换
procurementTaskForm.periodTypeChange = function(){
	if($("#periodType").val()=='year'){
		$("#year").show();$("#month").hide();
	}else{
		$("#year").show();$("#month").show();
	}
	//初始化选项
	$("#year").html("");
	var year = (new Date()).getFullYear();
	var periodValue = $("input[name=periodValue]").val();
	for(var i=0;i<5;i++){
		if(periodValue!=null&&periodValue!=""&&periodValue.split("-").length>0){
			$("#year").append('<option value="'+(year+i)+'" '+((year+i)==periodValue.split("-")[0]?'selected="selected"':'')+'>'+(year+i)+'</option>' );
		}else{
			$("#year").append('<option value="'+(year+i)+'">'+(year+i)+'</option>' );
		}
	}
	$("#month").html("");
	for(var i=1;i<=12;i++){
		if(periodValue!=null&&periodValue!=""&&periodValue.split("-").length>1){
			$("#month").append('<option value="'+i+'" '+(i==periodValue.split("-")[1]?'selected="selected"':'')+'>'+i+'</option>' );
		}else{
			$("#month").append('<option value="'+i+'" '+(i==(new Date()).getMonth()?'selected="selected"':'')+'>'+i+'</option>' );
		}
	}
}

//克隆一行
procurementTaskForm.cloneTr = function(){
	var trSize = $("#procurementTaskItem tbody").find('tr').length+1;
	var newTR = $('#tempTable').find('tr').clone(true);
	$(newTR).removeClass("hidden").attr('id',$(newTR).attr('id')+trSize);
	
	$(newTR).find('input[id=purCategory1.objId]').attr('id','purCategory'+trSize+'.objId');//品目
	$(newTR).find('input[id=purCategory1.name]').attr('id','purCategory'+trSize+'.name');
	
	$("#content").append(newTR);
}

//数量改变
procurementTaskForm.QtyOrPriceChange = function(e){
	if(isNaN($(e).val())){alert("必须输入数字！");return }
	var goodsQty = $(e).parent().parent().find("input[name=goodsQty]").val();
	var goodsPrice = $(e).parent().parent().find("input[name=goodsPrice]").val();
	$(e).parent().parent().find("input[name=goodsTotal]").val(formatAmount(goodsPrice*goodsQty,2));
	
	procurementTaskForm.changeTotal();
}

//改变总书目金额
procurementTaskForm.changeTotal = function(){
	var total = 0.0;
	var qty = 0;
	$.each($("#content").find("input[name=goodsTotal]"),function(index,obj){
		total += Number($(obj).val());
	})
	
	$.each($("#content").find("input[name=goodsQty]"),function(index,obj){
		qty += Number($(obj).val());
	})
	
	$("span[id=sumTotal]").html(formatAmount(total,2));
	$("span[id=sumQty]").html(qty);
}


//保存
procurementTaskForm.savePlanAndItem = function(saveType){
	
	//采购计划对象
	var plan = {};
	
	if($("input[name=objId]").val()!=""){
		plan.objId = $("input[name=objId]").val();
	}
	plan.taskNo = $("input[name=taskNo]").val();
	plan.periodType = $("select[name=periodType]").val();
	plan.periodType=='year'?plan.periodValue=$("select[id=year]").val():plan.periodValue=($("select[id=year]").val()+"-"+$("select[id=month]").val());
	plan.sumTotal = $("span[id=sumTotal]").html();
	plan.sumQty = $("span[id=sumQty]").html();
	plan.memo = $("textarea[name=memo]").val();
	plan.saveType = saveType;
	
	//拼装计划条目对象
	plan.itemStr = "";
	$.each($("#content").find("tr"),function(index,obj){
		if(plan.itemStr!=""){
			plan.itemStr+=","
		}
		plan.itemStr += $(obj).find("textarea[name=memo]").val();
		plan.itemStr += ":"+$(obj).find("input[name=purCategory.objId]").val();
		plan.itemStr += ":"+$(obj).find("input[name=purCategory.categoryName]").val();
		plan.itemStr += ":"+$(obj).find("input[name=goodsQty]").val();
		plan.itemStr += ":"+$(obj).find("input[name=goodsPrice]").val();
		plan.itemStr += ":"+$(obj).find("input[name=goodsTotal]").val();
		if($(obj).find("input[name=objId]").val()){
			plan.itemStr += ":"+$(obj).find("input[name=objId]").val();
		}
	})
	
	//保存
	$.getJSON($("#initPath").val()+"/ProcurementtaskController.do?method=createOrUpdatePlan",plan,function(json){
		if(json.success){
			alert("保存成功！");
		}else{
			alert("保存失败！");
		}
		$("button[name=historyBackBtn]").click();
	})
}

//删除条目
procurementTaskForm.removeRequireItem = function(e,objId){
	if(window.confirm('确定删除?')) {
		$(e).parent().parent().remove();
		procurementTaskForm.changeTotal();
		if(objId!=''){
			var plan = {"itemId":objId,"planId":$("input[name=objId]").val(),"sumTotal":$("span[id=sumTotal]").html(),"sumQty":$("span[id=sumQty]").html()};
			$.getJSON($("#initPath").val()+"/ProtaskItemController.do?method=removePlan",plan,function(json){})
		}
	}
}

//全选或全不选事件
procurementTaskForm.checkAllOrNot = function(name,e){
	$("input[name*="+name+"]").attr("checked",$(e).attr("checked"));
}

//弹出订单列表
procurementTaskForm.finishedOrderView = function(taskItemId){
	$.epsDialog({
		id:"finishedOrderViewDiv",
		title:"任务书订单",
		url:$("#initPath").val()+"/view/agreement/order/order/order_list_taskitem.jsp?taskItemId="+taskItemId
	})
}

$(document).ready(function(){
	
	//初始化时期
	procurementTaskForm.periodTypeChange();
	
	//添加一行
	if($("#content").find("tr").length==0){
		procurementTaskForm.cloneTr();
	}
	
	//选择品目
	$("input[name=purCategory.categoryName]").click(function(){
		var id = $(this).parent().parent().attr("id").replace("tempTR","");
	    $.epsDialog({
	        title:'请选择品目',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=purCategory'+id+'&className=PurCategory&action=listTop&isOpen=1&checkValues='+$("input[id=purCategoryIds.objId]").val()
	    }); 
	});
	
	//创建竞价项目
	$("button[name=createProjectBtn]").click(function(){
		var selects = [];
		$.each($("input[name*=checkCell]:checked"),function(index,obj){
			if($(obj).val()!="on"&&$(obj).val()!=null){
				selects.push($(obj).val());
			}
		})
		if(selects.length == 0){
			alert("请至少选择一个条目");return;
		}
		window.open($('#initPath').val()+"/BargainProjectController.do?method=toCreateBidProject_1&taskId="+$("#objId").val()+"&taskItemIds="+selects);
	})
});
	
