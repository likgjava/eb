//sort已作为关键字，不可以作为变量名。
var treeUtil={};

/*************************************************************************************
 * treeUtil.sortUp 目录树节点的上移
 * treeUtil.sortDown 目录树节点的下移
 * treeUtil.refreshTree 刷新目录树
 * treeUtil.drag 目录树节点拖拽
 * treeUtil.getTreeSort 获得目录树的节点的排序号
 * treeUtil.refreshTreeAndForm 目录树的刷新以及刷新form页面
 * treeUtil.getTreeSort 获得目录树的节点的排序号
 * treeUtil.openOrClose 目录树展开合拢的方法
 *************************************************************************************/

//+---------------------------------------------------------
//|目录树节点的上移
//|tree 指定的目录树
//|upId 上移的按钮id
//|className 排序的类
//+---------------------------------------------------------
treeUtil.sortUp=function(tree,upId,className){
	return treeUtil.myTreeSort("up_strict",tree,upId,"",className);
}

//+---------------------------------------------------------
//|目录树节点的上移
//|tree 指定的目录树
//|upId 上移的按钮id
//|className 排序的类
//+---------------------------------------------------------
treeUtil.sortDown=function(tree,downId,className){
	return treeUtil.myTreeSort("down_strict",tree,"",downId,className);
}


//+---------------------------------------------------------
//|目录树节点的拖拽
//|tree 指定的目录树
//|id 被拖拽节点的id
//|newParentId 新的上级节点的id
//|className 排序的类
//+---------------------------------------------------------
treeUtil.drag=function(tree,id,newParentId,className){

	var jsonObj = {};
	jsonObj.className = className;
	jsonObj.drag = {};
	jsonObj.drag.objId = id;
	jsonObj.drag.sort = tree._globalIdStorageFind(newParentId).childsCount;
	if(newParentId == "-1")newParentId = null;
	jsonObj.drag.parentId = newParentId;

	var dataObj={};
	dataObj.json =obj2str(jsonObj);
	
	var result;
	$.ajax({
		url : $("#initPath").val()+"/DragController.do?method=drag",
		data: dataObj,
		async: false,
		type:"POST",
		success:function(msg){
			result=true;
		},
		error:function(msg){
			result=false;
		}
	})

	return result;
}

//+---------------------------------------------------------
//|目录树的排序
//|mode 上移还是下移,down_strict or up_strict
//|tree 指定的目录树
//|downId 下移的按钮id
//|upId 上移的按钮id
//|url 提交的url
//|sortClos 排序字段
//+---------------------------------------------------------
treeUtil.myTreeSort=function(mode,tree,downId,upId,className){
	var itemId=tree.getSelectedItemId()
	if(itemId!=""&&itemId!=null){
		var oIndex=tree.getIndexById(itemId);
		tree.moveItem(itemId,mode);
		tree.selectItem(itemId,false);
		var nIndex=tree.getIndexById(itemId);
		
		if(oIndex==nIndex&&nIndex==0){
			alert("该节点已是最顶端");
			return true;
		}
		if(oIndex==nIndex&&nIndex>0){
			alert("该节点已是最末端");
			return true;
		}
		var node =tree._globalIdStorageFind(itemId)
		var changeNode;
		
		var jsonObj = {};
		jsonObj['className'] = className;
		if(mode=="down_strict"){
			
			 changeNode=tree._getPrevNode(node);
			 jsonObj['sortA.objId'] = itemId;
			 jsonObj['sortA.sort'] = nIndex+1;
			 
			 jsonObj['sortB.objId'] = changeNode.id;
			 jsonObj['sortB.sort'] = nIndex;
			 

		}else{
			 changeNode=tree._getNextNode(node);
			 
			 jsonObj['sortA.objId'] = itemId;
			 jsonObj['sortA.sort'] = nIndex+1;
			 
			 jsonObj['sortB.objId'] = changeNode.id;
			 jsonObj['sortB.sort'] = nIndex+2;
		}
		
		if(downId != "")
			$("#"+downId).attr("disabled","true");
		if(upId != "")
			$("#"+upId).attr("disabled","true");
		
		$.ajax({
			url:$("#initPath").val()+"/SortController.do?method=sort",
			type:"POST",
			async:false,
			data:jsonObj,
			success:function(msg){
				if(downId != "")
					$("#"+downId).attr("disabled","");
				if(upId != "")
					$("#"+upId).attr("disabled","")
				return true;
			},error:function(e){
				alert("排序失败！");
				if(downId != "")
					$("#"+downId).attr("disabled","");
				if(upId != "")
					$("#"+upId).attr("disabled","")
				return true;
			}
		})
		return true;
	}
}

//+---------------------------------------------------------
//|目录树的刷新
//|tree 需要刷新的目录树
//|oper 操作
//|nodeId 如果是新增，新增后的id
//|nodeName 如果是新增或修改，更新后的节点名字
//+---------------------------------------------------------
treeUtil.refreshTree=function(tree,oper,nodeId,nodeName){
	var selectId = tree.getSelectedItemId();
	if(selectId==null || selectId == "" || selectId=="0"){
		selectId = "-1";
		oper = "child"
	}
	if(oper == "edit"){  //修改
		tree.setItemText(selectId,nodeName);}
	else if(oper == "new"){  //增加同级
		tree.insertNewNext(selectId,nodeId,nodeName,0,0,0,0,'');}
	else if(oper == "child"){  //增加下级
		tree.insertNewItem(selectId,nodeId,nodeName,0,0,0,0,'');}
	else if(oper == "delete"){//删除
		tree.deleteItem(selectId,true);}
	else
		return false;
}


//+---------------------------------------------------------
//|目录树的刷新以及刷新form页面
//|tree 需要刷新的目录树
//|form 表单的id 
//|msg  提交后返回的信息
//+---------------------------------------------------------
treeUtil.refreshTreeAndForm=function(tree,form,msg){
	var returnObj = eval('(' + msg + ')');
	if(returnObj.hasFieldErrors==true){
		alert(obj2str(returnObj.fielderrors))
	}else{
		alert("保存成功");
	}
	var pid=$("input[id=parent.objId]").val();
	var id=$("#objId").val();
	if(id == "" && pid == "")
		treeUtil.refreshTree(tree,"new",returnObj.list[0].objId,returnObj.list[0].name);
	else if(id == "" && pid != "")
		treeUtil.refreshTree(tree,"child",returnObj.list[0].objId,returnObj.list[0].name);
	else treeUtil.refreshTree(tree,"edit",returnObj.list[0].objId,returnObj.list[0].name);
	$("#"+form)[0].reset();//刷新目录树、清空表单
	$("input[id=parent.objId]").val(pid);
	$("#objId").val(id);
}


//+---------------------------------------------------------
//|获得目录树的节点的排序号
//|tree 需要刷新的目录树
//+---------------------------------------------------------
treeUtil.getTreeSort=function(tree){
	var sort = 0;
	var selectId = tree.getSelectedItemId();
	if(selectId="")
		selectId = "-1";

	//新增，获得选中节点的下级节点数，做为排序数
	sort = tree.hasChildren(tree.getSelectedItemId());
	if(sort != true) sort += 1;
	
	return sort;
}


//+---------------------------------------------------------
//|目录树展开合拢的方法
//|tree 需要控制的目录树
//|button 控制展开合拢的按钮id
//|url 或取目录树数据的url
//+---------------------------------------------------------
treeUtil.openOrClose=function(tree,button,url){
	$("#"+button).attr("disabled",true);
	var selectNode = tree.getSelectedItemId();
	var state = tree.getOpenState(selectNode)
	if(state == "0") //没有子节点
		tree.loadXML(url+"&id="+selectNode,function(){
			$("#"+button).attr("disabled",false);
		});
	else if(state == "-1"){ //合拢状态
		tree.openAllItems(selectNode);
		$("#"+button).attr("disabled",false);
	}
	else if(state == "1"){  //展开状态
		tree.closeAllItems(selectNode);
		$("#"+button).attr("disabled",false);
	}
}