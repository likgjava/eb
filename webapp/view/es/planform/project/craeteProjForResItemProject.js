/*
 * 执行平台，委托书表单
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var craeteProjForTaskPlanSub={};

craeteProjForTaskPlanSub.saveOrSumbit=function(){
	alert($("#resProjecItemId").val());
	$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveProjectByResProjectItem&isComplete=true&resProjecItemId='+$("#resProjecItemId").val(), formToJsonObject('craeteProjForTaskPlanSubForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('#conBody').empty().loadPage($('#initPath').val()+'/TaskPlanController.do?method=toTaskPlanListForCreateProjBiXuan2');
	});
}
$(document).ready(function(){
    
    //查询条件过滤
    craeteProjForTaskPlanSub.before=function(){
    	var option={"objId":$("#resProjectItemId").val()}
    	$('#consignGrid').flexOptions({params:option});
    	return true;
    }
    craeteProjForTaskPlanSub.getStatus = function(confirmStatus){
    	if ("00"==confirmStatus) {//待上级采购单位审核申报书
    		return "自主招标";
    	}else {//待提交申报书
    		return "委托招标";
    	}
    }		
    craeteProjForTaskPlanSub.getStatus3 = function(confirmStatus){
    	if ("00"==confirmStatus) {//待上级采购单位审核申报书
    		return "不招标";
    	}else {//待提交申报书
    		return "招标";
    	}
    }		
  //加载数据成功之后调用的函数
    craeteProjForTaskPlanSub.success=function(){
    	$("#consignGrid").flexGetColByName({
    		'ebuyStyle':function(id,t){
    			var json = $("#consignGrid").flexGetRowJsonById(id);
    			var confirmStatus = json['ebuyStyle'];
    			$(t).html(craeteProjForTaskPlanSub.getStatus(confirmStatus));
    		}
    	});
    	$("#consignGrid").flexGetColByName({
    		'ebuyMethod':function(id,t){
    			$(t).html("公开招标");
    		}
    	});
    	$("#consignGrid").flexGetColByName({
    		'isEbuy':function(id,t){
    			var json = $("#consignGrid").flexGetRowJsonById(id);
    			$(t).html(craeteProjForTaskPlanSub.getStatus3(json['isEbuy']));
    		}
    	});
		$("#consignGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn"><u>分标段</u></a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$('#conBody').loadPage($('#initPath').val()+'/ProjectController.do?method=toCreateProjectByReProjectId&objId='+$("#resProjecItemt").val());
				}).appendTo(obj);
			}
		});
		return true;
	}

	
	
	//提交
	$('#projectSaveButton').click(function(){
			if (window.confirm('确认提交？')){
				if(!$('#craeteProjForTaskPlanSubForm').valid()){alert('请正确填写表单!');return;}
					craeteProjForTaskPlanSub.saveOrSumbit();
			}
	});

});
