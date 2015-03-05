
var departmentForm={};
$(document).ready(function(){
	if("grid"==$("#model").val()){
		$('#createBtnsArea').append("<li  class=\"back\"><a href=\"OrganizationController.do\" target=\"#conBody\"><span>返回</span></a></li>") ;
	}
	if($('#objId').val()!=''){
		organizationList.objIdVal=$('#objId').val();
		var queryColumns=[];
		$('#departmentDetailForm  div >span').each(function(i,n){
			queryColumns.push(n.id);
		});
		//alert(queryColumns.toString())
    	$.getJSON($('#initPath').val()+'/DepartmentController.do?method=createOrUpdate', {objId:$('#objId').val(),queryColumns:queryColumns.toString()}, function(json){
    		json2Object('departmentDetailForm',json.department);
    		organizationParentShortName = json.department.shortName;//初始化父级机构中文名称
    		//获取动态菜单
    		organizationList.getOrgRules("2",$("#createBtnsArea"),json.department.objId,json.department.shortName);
    	});
    }
	//修改
	$('#departmentEdit').click(function(){
		if("grid"==$("#model").val()){
			$('#conBody').loadPage($('#initPath').val()+'/DepartmentController.do?method=toCreateOrUpdate&model=grid&objId='+$('#objId').val());
		}
		else{
			$('.treeRight').loadPage($('#initPath').val()+'/DepartmentController.do?method=toCreateOrUpdate&objId='+$('#objId').val());
		}
	});
	
	//delete
	$('#departmentDel').click(function(){
		if(window.confirm("确定删除?")){
			$.ajax({
					url:"OrganizationController.do?method=deleteOrg",
					type:"POST",
					data:{objId:$('#objId').val()},
					success:function(json){
						if(json.result)alert(json.result,{inco:'info'});;
						if(json.failure)return;
						if("grid"==$("#model").val()){
							$('#conBody').loadPage($('#initPath').val()+'/OrganizationController.do');
						}else{
							organizationList.tree.deleteItem($('#objId').val(),true);
							$('.treeRight').empty();
						}
						
					},error:function(json){
						alert(json)
					}
			})
		}
	});
});