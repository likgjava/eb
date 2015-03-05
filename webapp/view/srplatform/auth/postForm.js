//定义文件全局变量 处理方法名重复问题

var postForm={};

$(document).ready(function(){
	$("input[name=parent.shortName]").val(organizationParentShortName);//初始化父级机构中文名称
	$('#postForm').validate();
    if($('#objId').val()!=''){
    	var queryColumns=[];
    	$('#postForm .formField input').each(function(i,n){
    		queryColumns.push(n.name);
    	});
    	//alert(queryColumns.toString())
    	$.getJSON($('#initPath').val()+'/PostController.do?method=createOrUpdate',{objId:$('#objId').val(),queryColumns:queryColumns.toString()},function(json){
    		jsonObjectToForm('postForm', json.post);
    	});
    }
	//返回
	$('#postReturn').click(function(){
		if("grid"==$("#model").val()){
			$('#conBody').loadPage($('#initPath').val()+'/OrganizationController.do') ;
		}
		else{
			$('#conBody').loadPage($('#initPath').val()+'/OrganizationController.do?method=toTreeList') ;
		}
	});
	//提交
	$('#postSave').click(function(){
		if(!$('#postForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		//alert(obj2str(formToJsonObject('postForm')));
		$.getJSON($('#initPath').val()+'/PostController.do?method=savePost', formToJsonObject('postForm'), function(json){
			if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
			if("grid"==$("#model").val()){
				$('#conBody').loadPage($('#initPath').val()+'/OrganizationController.do') ;
			}
			else{
				organizationList.reloadTree(json.postId,$("input[id=name]").val(),3);
				$('.treeRight').loadPage($('#initPath').val()+'/PostController.do?method=toShowView&objId='+json.postId);
			}
			
		});
	});

});
