var expertCondition = {};

expertCondition.delCondition= function(expertRuleItemId){//删除明细表
	$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=removeExpertRuleItem',{objId:expertRuleItemId},function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		alert('删除成功!');
		if($("#projectTaskId") && $("#projectTaskId").val() != ""){
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
	   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
	   	}
	});
}

expertCondition.modifyCondition = function(expertRuleItemId){//修改明细表
	 if(expertRuleItemId!=''){
	    	$.getJSON($('#initPath').val()+'/ExpertruleController.do?method=toExpertRuleItem',{objId:expertRuleItemId},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#resetCondition').click();
				$('input[name=purcategory.objId]').val(json.takeExpertRuleItem.purcategory.objId);//品目Id
				$('input[name=purcategory.name]').val(json.takeExpertRuleItem.purcategory.categoryName);//品目名称
	    		if (json.takeExpertRuleItem.expertLevel=='2') {
	    			$('#expert_level_2').attr('checked',true);//抽取类型
	    		}else {
	    			$('#expert_level_1').attr('checked',true);//抽取类型
	    		}
	    		$('#specialty_year').val(json.takeExpertRuleItem.specialtyYear);//专业工龄
	    		$('#amount').val(json.takeExpertRuleItem.amount);//正选人数
	    		$('#sub_Amount').val(json.takeExpertRuleItem.subAmount);//备选人数
	    		$('#expert_nlStart').val(json.takeExpertRuleItem.ageStart);//起
	    		$('#expert_nlEnd').val(json.takeExpertRuleItem.ageEnd);//止
	    		$('#ageId').val(json.takeExpertRuleItem.age);//起-止
	    		
	    		$('#city_codeId').val(json.takeExpertRuleItem.cityCode);//评审地域
	    		$('#expert_groupId').val(json.takeExpertRuleItem.expertGroup);//专家类型
	    		$('#top_educId').val(json.takeExpertRuleItem.topEduc);//学历

	    		$('#takeExpertRuleItemId').val(json.takeExpertRuleItem.objId);//学历
	    		
	    		//回填选中
	    		var city_codeId = $('#city_codeId').val();
	    		sendSperviseInvitation.backFillCheckBoxValue(city_codeId,'city_code_');//地域
	    		var expert_groupId = $('#expert_groupId').val();
	    		sendSperviseInvitation.backFillCheckBoxValue(expert_groupId,'expert_group_');//专家类型
	    		var top_educId = $('#top_educId').val();
	    		sendSperviseInvitation.backFillCheckBoxValue(top_educId,'top_educ_');//学历
	    		
	    	});
	    }
}

