var menuForm={};

$(document).ready(function(){
	
	menuForm.vform=$("#MenuForm").validate();	
	
    if($("#objId").val()!=""&&$("#objId").val()!="null"){
    	$.getJSON("MenuController.do?method=createOrUpdate",{objId:$("#objId").val()},function(json){
    		var item = json.list[0];
    		jsonObjectToForm($("#MenuForm")[0],item);
    	})
    }
	$("#submit").click(function(){
		if(menuForm.vform.checkForm()){
			var josnObj=formToJsonObject($("#MenuForm")[0]);
			
			if($("#objId").val() =="")  //新增的时候，添加排序号
				josnObj['sort'] = treeUtil.getTreeSort(menuList.tree);
			
			//josnObj.json=JSON.stringify(josnObj);
			alert(obj2str(josnObj))
			$.ajax({
				url:"MenuController.do?method=save",
				type:"POST",
				data:josnObj,
				success:function(msg){	 
					treeUtil.refreshTreeAndForm(menuList.tree,"MenuForm",msg);
				}
			})
		}else{
			menuForm.vform.showErrors();
		}
	})
	
   	 $("#ResourceHov").click(function(e){	 
		$.epsDialog({
			 title:"资源",
			 url:"TreeController.do?property=resource&className=Resource"
		})
   	 })
   	 
   	$("#delete").click(function(){
   		if($.trim($("#objId").val())!=""){
   			if(window.confirm("确定删除?")){
   				$.ajax({
   						url:"MenuController.do?method=remove",
   						type:"POST",
   						data:{objId:$.trim($("#objId").val())},
   						success:function(msg){
   							treeUtil.refreshTree(menuList.tree,"delete");	
   						}
   				})
   			}
   		}
   	})

})
