var employeeGridList={};

employeeGridList.rows=null;


	//列表操作验证
employeeGridList.validation=function(name,grid){
	//$(grid).getSelect()获取一个选中ID		$(grid).getSelects()获取多个选中ID	
	//$(grid).isSelectEmpty()是否没有选中	$(grid).isSelectOne()是否只选中了一个
	if($(grid).isSelectEmpty()){alert('请选择用户'+name);return false;}//是否选中
	return true;
}
	employeeGridList.before=function(){//查询条件过滤
		var option={};
		option['company.objId']=$("#orgId").val();
		$('#employeeGrid').flexOptions({params:option});
		return true;
	}
	employeeGridList.add=function(name,grid){
		if(!employeeGridList.validation(name,grid))return;
		$.getJSON($('#initPath').val()+'/WorkgMemberController.do?method=saveOpenBidMember&projectId='+$('#projectId').val(),{empIds:$(grid).getSelects().split(','),workGroupId:$('#workGroupId').val()},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#epsDialogClose').click();
			if($("#projectTaskId") && $("#projectTaskId").val() != null && $("#projectTaskId").val() != "") {
	        	planTemplateTask.clickMethod($("#projectTaskId").val()+"");
		   		planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		    } else {
		    	$("#myDesktop").click();
		    }
			$('#epsDialogClose').click();
		});
	}
$(document).ready(function(){});



