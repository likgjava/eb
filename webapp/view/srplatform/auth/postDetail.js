
var postForm={};
$(document).ready(function(){
	if("grid"==$("#model").val()){
		$('#createBtnsArea').append("<li  class=\"back\"><a href=\"OrganizationController.do\" target=\"#conBody\"><span>返回</span></a></li>") ;
	}
	if($('#objId').val()!=''){
		organizationList.objIdVal=$('#objId').val();
		var queryColumns=[];
		$('#postDetailForm  div >span').each(function(i,n){
			queryColumns.push(n.id);
		});
		//alert(queryColumns.toString())
    	$.getJSON($('#initPath').val()+'/PostController.do?method=createOrUpdate', {objId:$('#objId').val(),queryColumns:queryColumns.toString()}, function(json){
    		json2Object('postDetailForm',json.post);
    		organizationParentShortName = json.post.shortName;//初始化父级机构中文名称
    		//获取动态菜单
			organizationList.getOrgRules("3",$("#createBtnsArea"),json.post.objId,json.post.shortName);
    	});
    }
	//edit
	$('#postEdit').click(function(){
		if("grid"==$("#model").val()){
			$('#conBody').loadPage($('#initPath').val()+'/CompanyController.do?method=toCreateOrUpdate&objId='+$('#objId').val());
		}
		else{
			$('.treeRight').loadPage($('#initPath').val()+'/PostController.do?method=toCreateOrUpdate&objId='+$('#objId').val());
		}
	});
	
	//delete
	$('#postDel').click(function(){
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