
var companyForm={};
$(document).ready(function(){
	if("grid"==$("#model").val()){
		$('#createBtnsArea').append("<li  class=\"back\"><a href=\"OrganizationController.do?menthod=toGridList\" target=\"#conBody\"><span>返回</span></a></li>") ;
	}
	
	if($('#objId').val()!=''){
		organizationList.objIdVal=$('#objId').val();
		var queryColumns=[];
		$('#companyDetailForm  div >span').each(function(i,n){
			queryColumns.push(n.id);
		});
		//alert(queryColumns.toString())
    	$.getJSON($('#initPath').val()+'/CompanyController.do?method=createOrUpdate', {objId:$('#objId').val(),'includedProperties':'town,town.parent,town.parent.parent'}, function(json){
    		
    		json2Object('companyDetailForm',json.company);
    		if(json.company.town){
    			var town = json.company.town;
    			var district = town.parent.parent.name+town.parent.name+town.name;
    			$("#district").html(district);
    		}
    		organizationParentShortName = json.company.shortName;//初始化父级机构中文名称
    		//获取动态菜单
			organizationList.getOrgRules("1",$("#createBtnsArea"),json.company.objId,json.company.shortName);
    	});
    }
	
	
	//编辑
	$('#companyEdit').click(function(){
		if("grid"==$("#model").val()){
			$('#conBody').loadPage($('#initPath').val()+'/CompanyController.do?method=toCreateOrUpdate&model=grid&objId='+$('#objId').val());
		}
		else{
			$('.treeRight').loadPage($('#initPath').val()+'/CompanyController.do?method=toCreateOrUpdate&objId='+$('#objId').val());
		}
	});
	//delete
	$('#companyDel').click(function(){
		if(window.confirm("确定删除?")){
			$.ajax({
					url:"OrganizationController.do?method=deleteOrg",
					type:"POST",
					data:{ids:$('#objId').val()},
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