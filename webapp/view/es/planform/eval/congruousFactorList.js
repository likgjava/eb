var congruousFactorList={};
congruousFactorList.rows=null;//列表查询的结果集
congruousFactorList.scoreSum = 0;
congruousFactorList.add=function(name,grid){
	var factorTypeId = $("#factorTypeId").val();
	var score = congruousFactorList.scoreSum;
	if(score >= congruousFactorList.scoreSum && 0 != congruousFactorList.scoreSum){
		alert("当前分值总和为"+score+"，不能新增指标。\n若需要新增指标，请先修改指标分值。\n指标分值总和应该为"+congruousFactorList.scoreSum+"。");
	}else{
		$.epsDialog({
	    	title:'新增指标',
	    	url:$('#initPath').val()+'/CongruousFactorController.do?method=toCreateCongruousFactorFormView&factorTypeId='+factorTypeId+"&projectId="+$("#project_objId").val()+"&scoreNum="+$("#cur_weightCoefficient").val(),
	    	width: '530',
	    	height: '230',
	    	isReload:true,
	    	onClose: function(){ 
				$("#congruousFactorGrid").reload();
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	       }
		});
	}
}
congruousFactorList.update=function(id){
	var score = congruousFactorList.getScoreSum();
	var json = $("#congruousFactorGrid").flexGetRowJsonById(id);
	if (undefined == json.score) {
		json.score = 0;
	}
	$.epsDialog({
    	title:'修改指标',
    	url:$('#initPath').val()+'/CongruousFactorController.do?method=toUpdateCongruousFactorFormView&factorTypeId='+$("#factorTypeId").val()+"&objId="+id+"&projectId="+$("#project_objId").val()+"&scoreNum="+$("#cur_weightCoefficient").val(),
    	width: '530',
    	height: '230',
    	onClose: function(){ 
			$("#congruousFactorGrid").reload();
       }
	});
}
congruousFactorList.updateType=function(name,grid){
		var factorTypeId = $("#factorTypeId").val();
		$.epsDialog({
	    	title:'修改指标分类',
	    	url:$('#initPath').val()+'/CongruousFactorTypeController.do?method=toUpdateCongruousFactorType&objId='+factorTypeId,
	    	width: '530',
	    	height: '230',
	    	isReload:true,
	    	onClose: function(){ 
				if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
			    	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			    }
	       }
		});
}
congruousFactorList.addType=function(name,grid){
	var factorTypeId = $("#factorTypeId").val();
	$.epsDialog({
    	title:'新增指标分类',
    	url:$('#initPath').val()+'/CongruousFactorTypeController.do?method=toAddCongruousFactorType&parentId='+factorTypeId,
    	width: '530',
    	height: '230',
    	isReload:true,
    	onClose: function(){ 
			if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
		    	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		    }
       }
	});
}
congruousFactorList.deleteType=function(name,grid){
	var objId = $("#factorTypeId").val();
	$.getJSON($('#initPath').val()+'/CongruousFactorTypeController.do?method=removeCongruousFactorType&objId='+objId,{}, function(json){
		if(json.result){
			alert(json.result);if(json.failure)return;
		}else{
			alert("删除成功！");
			if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
		    	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		    }
		}
	})
}
congruousFactorList.remove=function(id){
	if(window.confirm('确定删除?')){
		$.getJSON($('#initPath').val()+'/CongruousFactorController.do?method=removeCongruousFactor',{objId:id},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$("#congruousFactorGrid").reload();
		});
	}
}
congruousFactorList.before=function(){
	var option={
		"factorType.objId":$("#factorTypeId").val()
	}
	$('#congruousFactorGrid').flexOptions({params:option});
	return true;
}
congruousFactorList.success=function(){
	$("#congruousFactorGrid").flexAddOptionStr({
		'<button type="button" class="sysicon siModify" title="修改指标" ><span>修改</span></button>' : function(btn,rowId,obj){
			 btn.click(function(){
				 congruousFactorList.update(rowId);
			 }).appendTo(obj);
		},
		'<button type="button" class="sysicon siDelete" title="删除指标" ><span>删除</span></button>' : function(btn,rowId,obj){
			 btn.click(function(){
				 congruousFactorList.remove(rowId);
			 }).appendTo(obj);
		}
	});
}
// 引入指标
congruousFactorList.importFactor = function(){
	var score = congruousFactorList.getScoreSum();
	if(score >= congruousFactorList.scoreSum && 0 != congruousFactorList.scoreSum){
		alert("当前分值总和为"+score+"，不能新增指标。\n若需要新增指标，请先修改指标分值。\n指标分值总和应该为"+congruousFactorList.scoreSum+"。");
	}else{
		$.epsDialog({
	    	title:'引入系统指标',
	    	url:$('#initPath').val()+'/CongruousFactorController.do?method=toImportSysFactorListView&factorTypeId='+$("#factorTypeId").val()+"&projectId="+$("#project_objId").val()+"&scoreNum="+(congruousFactorList.scoreSum-score),
	    	width: '800',
	    	height: '470',
	    	isReload:true,
	    	onClose: function(){ 
				$("#congruousFactorGrid").reload();
				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	       }
		});
	}
}
// 获取指标分值
congruousFactorList.getScoreSum = function(){
	var sum = 0;
	$.ajax({
		url:$("#initPath").val()+"/CongruousFactorController.do?method=getScoreSum",
		type:"POST",
		data:{"factorTypeId":$('#factorTypeId').val()},
		async:false,
		success:function(msg){
			msg = eval('('+msg+')');
			sum = msg.scoreSum;
		}
	})
	return sum;
}
$(document).ready(function(){
	congruousFactorList.scoreSum = $('#weightCoefficient').val() * 1;//指标分值总和(允许的指标分值总和)
})