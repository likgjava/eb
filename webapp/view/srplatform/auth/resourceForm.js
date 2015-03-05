var resourceForm={};

$(document).ready(function(){
	$('#resourceForm').hide();//隐藏表单
	resourceForm.vform=$("#ResourceForm").validate();	
	
    if($("#objId").val()!=""&&$("#objId").val()!="null"){
    	$.getJSON("ResourceController.do?method=createOrUpdate",{objId:$("#objId").val()},function(json){
    		jsonObjectToForm($("#ResourceForm")[0],json.list[0]);
    	})
    }
	$("#submit").click(function(){
		if(resourceForm.vform.checkForm()){
			var josnObj=formToJsonObject($("#ResourceForm")[0]);
			
			if($("#objId").val() =="")  //新增的时候，添加排序号
				josnObj.sort = treeUtil.getTreeSort(resourceList.tree);
			
			josnObj.json=JSON.stringify(josnObj);
			$.ajax({
				url:"ResourceController.do?method=save",
				type:"POST",
				data:josnObj,
				success:function(msg){
					treeUtil.refreshTreeAndForm(resourceList.tree,"ResourceForm",msg);
				}
			})
		}else{
			resourceForm.vform.showErrors();
		}
	})

   	$("#delete").click(function(){
   		if($.trim($("#objId").val())!=""){
   			if(window.confirm("确定删除?")){
   				$.ajax({
   						url:"ResourceController.do?method=remove",
   						type:"POST",
   						data:{objId:$.trim($("#objId").val())},
   						success:function(msg){
   							treeUtil.refreshTree(resourceList.tree,"delete",$("#objId").val());	
   						},error:function(msg){
   							alert('在删除关联此资源的菜单之前,您不能删除它.')
   						}
   				})
   			}
   		}
   	})
})
