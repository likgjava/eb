//定义文件全局变量 处理方法名重复问题

var departmentForm={};

$(document).ready(function(){
	if(organizationParentShortName!='')
		$("input[name=parent.shortName]").val(organizationParentShortName);//初始化父级机构中文名称
	$('#departmentForm').validate();
	
	$("input[id=leaderCnName]").click(function(e){
		
		 $.epsDialog({
	            title:'请选择一个员工作为部门领导',
	            url:'OrganizationController.do?method=toFloatEmployeeTree&hiddenProperty=leader&showProperty=leaderCnName&orgId='+$("input[id=parent.objId]").val(),
	            width: '600',
	            height: '360',
	            hasBg:true,//背景
	            fadeTo:80//透明度
		    });
	})	
	$("input[id=supervisorCnName]").click(function(e){
		$.epsDialog({
            title:'请选择一个员工作为部门负责人',
            url:'OrganizationController.do?method=toFloatEmployeeTree&hiddenProperty=supervisor&showProperty=supervisorCnName&orgId='+$("input[id=parent.objId]").val(),
            width: '600',
            height: '360',
            hasBg:true,//背景
            fadeTo:80//透明度
	    });
	})	
	
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/DepartmentController.do?method=createOrUpdate',{objId:$('#objId').val(),includedProperties:'parent'},function(json){
    		jsonObjectToForm('departmentForm', json.department);
    		if(!json.department.parent){
    			$("input[name=parent.shortName]").val('');
    			$("input[name=parent.objId]").val('');
    		}
    	});
    }
	//返回
	$('#departmentReturn').click(function(){
		if("grid"==$("#model").val()){
			$('#conBody').loadPage($('#initPath').val()+'/OrganizationController.do') ;
		}
		else{
			$('#conBody').loadPage($('#initPath').val()+'/OrganizationController.do?method=toTreeList') ;
		}
		
	});
	//提交
	$('#departmentSave').click(function(){
		if(!$('#departmentForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		//alert(obj2str(formToJsonObject('departmentForm')));
		$.getJSON($('#initPath').val()+'/DepartmentController.do?method=saveDep', formToJsonObject('departmentForm'), function(json){
			if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
			if("grid"==$("#model").val()){
				$('#conBody').loadPage($('#initPath').val()+'/OrganizationController.do') ;
			}
			else{
				organizationList.reloadTree(json.depId,$("input[id=name]").val(),2);
				$('.treeRight').loadPage($('#initPath').val()+'/DepartmentController.do?method=toShowView&objId='+json.depId);
			}
			
		});
	});

});
