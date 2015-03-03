
//定义文件全局变量 处理方法名重复问题
var procurementTaskForm={};

//时期类型转换
procurementTaskForm.periodTypeChange = function(){
	var periodType =  $("#procurementForm").find("select[name=periodType]").val();
	
	if(periodType=='year'){
		$("#procurementForm").find("select[name=month]").hide();
		$("#procurementForm").find("select[name=quarter]").hide();
	}else if(periodType=='month'){
		$("#procurementForm").find("select[name=month]").show();
		$("#procurementForm").find("select[name=quarter]").hide();
	}else{
		$("#procurementForm").find("select[name=month]").hide();
		$("#procurementForm").find("select[name=quarter]").show();
	}
	
	//初始化选项
	$("#year").empty();
	var year = gpcsoftDate.getFullYear();
	var periodValue = $("input[name=periodValue]").val();
	for(var i=0;i<5;i++){
		if(periodValue!=null&&periodValue!=""&&periodValue.split("-").length>0){
			$("#year").append('<option value="'+(year+i)+'" '+((year+i)==periodValue.split("-")[0]?'selected="selected"':'')+'>'+(year+i)+'</option>' );
		}else{
			$("#year").append('<option value="'+(year+i)+'">'+(year+i)+'</option>' );
		}
	}
	$("#month").empty();
	for(var i=1;i<=12;i++){
		if(periodValue!=null&&periodValue!=""&&periodValue.split("-").length>1){
			$("#month").append('<option value="'+i+'" '+(i==periodValue.split("-")[1]?'selected="selected"':'')+'>'+i+'</option>' );
		}else{
			$("#month").append('<option value="'+i+'" '+(i==gpcsoftDate.getMonth()?'selected="selected"':'')+'>'+i+'</option>' );
		}
	}
	
	if(periodType=='quarter'&&periodValue!=null&&periodValue!=""&&periodValue.split("-").length>1){
		$("#quarter").val(periodValue.split("-")[1]);
	}
	
	$.each($("#content").find("tr"),function(index,obj){
		$(obj).show();
	})
}

//克隆一行
procurementTaskForm.cloneTr = function(){
	var trSize = $("#procurementTaskItem tbody").find('tr').length+1;
	var newTR = $('#tempTable').find('tr').clone(true);
	$(newTR).removeClass("hidden").attr('id',$(newTR).attr('id')+trSize);
	
	//$(newTR).find('input[id=purCategory1.objId]').attr('id','purCategory'+trSize+'.objId');//品目
	//$(newTR).find('input[id=purCategory1.name]').attr('id','purCategory'+trSize+'.name');
	
	//初始化分类和品目为空
	$(newTR).find('input[id=goodsClass1.objId]').val('');
	$(newTR).find('input[id=goodsClass1.name]').val('');
	$(newTR).find('input[id=purCategory1.objId]').val('');
	$(newTR).find('input[id=purCategory1.name]').val('');
	$(newTR).find('input[id=goodsClass1_purCategory1.id]').val('');
	
	$(newTR).find('input[id=goodsClass1.objId]').attr('id','goodsClass'+trSize+'.objId');//分类和品目
	$(newTR).find('input[id=goodsClass1.name]').attr('id','goodsClass'+trSize+'.name');
	$(newTR).find('input[id=purCategory1.objId]').attr('id','purCategory'+trSize+'.objId');
	$(newTR).find('input[id=purCategory1.name]').attr('id','purCategory'+trSize+'.name');
	$(newTR).find('input[id=goodsClass1_purCategory1.id]').attr('id','goodsClass'+trSize+'_purCategory'+trSize+'.id');
	
	$("#content").append(newTR);
	$(newTR).show();
}

//数量改变
procurementTaskForm.QtyOrPriceChange = function(e){
	if(isNaN($(e).val())){alert("必须输入数字！");return; }
	var goodsQty = $(e).parent().parent().find("input[name=goodsQty]").val();
	var goodsPrice = $(e).parent().parent().find("input[name=goodsPrice]").val().replace(new RegExp(",","gm"),"");
	$(e).parent().parent().find("input[name=goodsTotal]").val(formatAmount(goodsPrice*goodsQty,2));
	
	procurementTaskForm.changeTotal();
}

//改变总书目金额
procurementTaskForm.changeTotal = function(){
	var total = 0.0;
	var qty = 0;
	$.each($("#content").find("input[name=goodsTotal]"),function(index,obj){
		total += Number($(obj).val().replace(new RegExp(",","gm"),""));
	})
	
	$.each($("#content").find("input[name=goodsQty]"),function(index,obj){
		qty += Number($(obj).val());
	})
	
	$("span[id=sumTotal]").html(formatAmount(total,2));
	$("span[id=sumQty]").html(qty);
}


//保存
procurementTaskForm.savePlanAndItem = function(saveType){
	
	//条目表单验证
	var flag = true;
	$.each($("#content").find("tr"),function(index,obj){
		if( $(obj).find("input[name=purCategory.objId]").val()==""
		||$(obj).find("input[name=goodsQty]").val()==""
		||$(obj).find("input[name=goodsPrice]").val()==""
		||$(obj).find("input[name=goodsTotal]").val()=="" ){
			flag = false;
		}
	})
	
	if(!flag){alert("请将明细信息填写详细！"); return ;}
	
	//采购计划对象
	var plan = {};
	
	if($("input[name=objId]").val()!=""){
		plan.objId = $("input[name=objId]").val();
	}
	plan.taskNo = $("input[name=taskNo]").val();
	plan.periodType = $("select[name=periodType]").val();
	
	if(plan.periodType=='year'){
		plan.periodValue=$("select[id=year]").val()
	}else if(plan.periodType=='month'){
		plan.periodValue=($("select[id=year]").val()+"-"+$("select[id=month]").val())
	}else{
		plan.periodValue=($("select[id=year]").val()+"-"+$("select[id=quarter]").val())
	}
	plan.sumTotal = $("span[id=sumTotal]").html().replace(new RegExp(",","gm"),"");
	plan.sumQty = $("span[id=sumQty]").html();
	plan.memo = $("textarea[name=memo]").val();
	plan.saveType = saveType;
	
	//拼装计划条目对象
	plan.itemStr = "";
	$.each($("#content").find("tr"),function(index,obj){
		if(plan.itemStr!=""){
			plan.itemStr+=",";
		}
		plan.itemStr += $(obj).find("textarea[name=memo]").val();
		plan.itemStr += ":"+$(obj).find("input[name=purCategory.objId]").val();
		plan.itemStr += ":"+$(obj).find("input[name=purCategory.categoryName]").val();
		plan.itemStr += ":"+$(obj).find("input[name=goodsQty]").val();
		plan.itemStr += ":"+$(obj).find("input[name=goodsPrice]").val().replace(new RegExp(",","gm"),"");
		plan.itemStr += ":"+$(obj).find("input[name=goodsTotal]").val().replace(new RegExp(",","gm"),"");
		
		plan.itemStr += ":"+$(obj).find("input[name=goodsClass.objId]").val();//加入分类
		
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
	if($("#content").find("tr").length==1){alert("需要至少录入一个明细条目！");return;}
	if(window.confirm('确定删除?')) {
		$(e).parent().parent().remove();
		procurementTaskForm.changeTotal();
		if(objId!=''){
			var plan = {"itemId":objId,"planId":$("input[name=objId]").val(),"sumTotal":$("span[id=sumTotal]").html(),"sumQty":$("span[id=sumQty]").html()};
			$.getJSON($("#initPath").val()+"/ProtaskItemController.do?method=removePlan",plan,function(json){})
		}
	}
}

$(document).ready(function(){
	//初始化时期
	procurementTaskForm.periodTypeChange();
	
	//添加一行
	if($("#content").find("tr").length==0){
		procurementTaskForm.cloneTr();
	}
	
	
	//选择分类品目
	$("input[name=goodsClass_purCategory.name]").click(function(){
		var id = $(this).parent().parent().attr("id").replace("tempTR","");
		var v = $(this).parent().find('input[name=goodsClass.objId]').val();
	    $.epsDialog({
	        title:'请选择分类品目',
	        url:$('#initPath').val()+'/view/agreement/bargin/project/bargain/select_class_category.jsp?&property1=goodsClass'+id+"&property2=purCategory"+id+"&goodsClassId="+v,
	        width: 600,
			height: 500   
	    }); 
	});
	
	
	//选择品目
//	$("input[name=purCategory.categoryName]").click(function(){
//		var id = $(this).parent().parent().attr("id").replace("tempTR","");
//	    $.epsDialog({
//	        title:'请选择品目',
//	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=purCategory'+id+'&className=PurCategory&action=listTop&isOpen=1&checkValues='+$("input[id=purCategoryIds.objId]").val()
//	    }); 
//	});
});
	
