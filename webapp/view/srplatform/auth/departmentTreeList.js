var departmentTreeList={};

//点击目录树节点
departmentTreeList.onNodeSelect = function (NodeId){	
	if(NodeId==" " || NodeId=="0" || NodeId=="-1"){
		$("#openAll").attr("disabled",true); return;
	}
	else{
		$("#openAll").attr("disabled",false);
		$.getJSON("OrganizationController.do?method=getBaseObjectListByProperty&_queryCols=type,parent.objId",{objId:NodeId},function(json){
			if(json[0].type == "0")
				$("#Department_from").loadPage("/OrganizationController.do?method=getList&parentId="+$.trim(NodeId))
			else
				$("#Department_from").loadPage("/OrganizationController.do?method=toCreateOrUpdate&type=1&objId="+$.trim(NodeId)+"&parentId="+json[0].parent.objId);
    	})
	}
		
}
	
$(document).ready(function(){
	$(window).resize(function(){index.treeHeight()});
	 	
   	//组织机构目录树
   	departmentTreeList.tree = new dhtmlXTreeObject('leftTree',"100%","85%",0); 
   	departmentTreeList.tree.enableDragAndDrop(true);
   	departmentTreeList.tree.setOnClickHandler(departmentTreeList.onNodeSelect);
	
   	departmentTreeList.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/csh_vista/");
   	departmentTreeList.tree.setXMLAutoLoading("OrganizationTreeController.do?method=getBaseObjectListByProperty&action=listById&level=1");
   	
   	departmentTreeList.tree.loadXML("OrganizationTreeController.do?method=getBaseObjectListByProperty&action=listTop&id=0&level=1",function(){
		$("#Department_from").loadPage("/OrganizationController.do?method=getList&parentId="+PlatForm.user.org.objId);
		index.treeHeight();
	});
   	
	//新增部门
	$("#addDepartment").click(function(){
		if(departmentTreeList.tree.getSelectedItemId() == "")
			alert("请先选择一个组织结构！");
		else
			$("#Department_from").loadPage("/OrganizationController.do?method=toCreateOrUpdate&type=1&parentId="+departmentTreeList.tree.getSelectedItemId())
	})
	
	//展开合并所有节点
	$("#openAll").click(function(){
		treeUtil.openOrClose(departmentTreeList.tree,"openAll","OrganizationTreeController.do?method=getBaseObjectListByProperty&isOpen=1&level=1");
	})
})