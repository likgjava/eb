
var employeeTreeForm={};
employeeTreeForm.tree;
employeeTreeForm.isSort=0;
employeeTreeForm.init=0//
//刷新节点
employeeTreeForm.reloadTree = function (itemId,text,orgId){
	//判断是否增加还是修改
	if(employeeTreeForm.tree._globalIdStorageFind(itemId)){
		employeeTreeForm.tree.setItemText(itemId,text);
	}else{
		//获取节点状态：0 - 没有子节点或者  节点合拢, 1  节点展开
		if(employeeTreeForm.tree.hasChildren(orgId)&&employeeTreeForm.tree.getOpenState(orgId)==0){
			return false;
		}
		else{
			var icon ="iconJob.gif";
			employeeTreeForm.tree.insertNewItem(orgId,itemId,text,0,0,0,0,'SELECT');
			employeeTreeForm.tree.setItemImage2(itemId,"leaf.gif","folderOpen.gif","folderClosed.gif");
		}
	}
	
}
//delete
employeeTreeForm.delEmp = function(id){
	if(window.confirm("确定删除?")){
		$.ajax({
				url:"EmployeeController.do?method=delEmpByIds",
				type:"POST",
				data:{objId:id},
				success:function(json){
					if(json.result)alert(json.result,{inco:'info'});;
					if(json.failure)return;
					if("grid"==$("#model").val()){
						$('#conBody').loadPage($('#initPath').val()+'/EmployeeController.do');
					}else{
						employeeTreeForm.tree.deleteItem(id,true);
						$(".treeRight").empty().loadPage("EmployeeController.do?method=toCreateOrUpdate&isForm=yes");
					}
					
				},error:function(json){
					alert(json)
				}
		})
	}
}

//点击节点事件
employeeTreeForm.nodeClick=function(){
	var id = employeeTreeForm.tree.getSelectedItemId();
	employeeTreeForm.currentId=id;
	if("employee"==employeeTreeForm.tree.getItemTooltip(id)){
		employeeTreeForm.init=1;
		$(".treeRight").empty().loadPage("EmployeeController.do?method=toShowView&objId="+id);
	}
	else{
		if(employeeTreeForm.init==1){
			$(".treeRight").empty().loadPage("EmployeeController.do?method=toCreateOrUpdate&isForm=yes");
		}
		if($("input[id=orgId]")) $("input[id=orgId]").val(id);
		if($("input[id=orgName]")) $("input[id=orgName]").val(employeeTreeForm.tree.getItemText(employeeTreeForm.tree.getSelectedItemId()));
		employeeTreeForm.init=0;
	}
	
}
employeeTreeForm.rightKeyTree = function(id,e){

	employeeTreeForm.tree._selectItem(employeeTreeForm.tree._globalIdStorageFind(id));
	employeeTreeForm.nodeClick();

}

//初始化树
employeeTreeForm.initEmployeeTree=function(roleOrgId,roleOrgName){
	//dhtmlXTreeObject(htmlObject, width, height, rootId)//rootId 虚拟根节点，在界面上不会显示，一般取值0
	employeeTreeForm.tree=new dhtmlXTreeObject('employeeTreeForm',"100%","100%",0);
	employeeTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
	//employeeTreeForm.tree.enableCheckBoxes(1);
	//employeeTreeForm.tree.enableDragAndDrop(1);
	//employeeTreeForm.tree.enableThreeStateCheckboxes(true);
	employeeTreeForm.tree.setOnClickHandler(employeeTreeForm.nodeClick);
	employeeTreeForm.tree.setOnRightClickHandler(employeeTreeForm.rightKeyTree);
	employeeTreeForm.tree.setXMLAutoLoading('OrganizationController.do?method=getOrgEmployeeTree&action=listById&isOpen=0');//点 + 号触发展开事件
	employeeTreeForm.tree.loadXML('OrganizationController.do?method=getOrgEmployeeTree&action=listTop&isOpen=0&id='+roleOrgId,function(){
		if(roleOrgId!=0){
			employeeTreeForm.tree.setItemText("-1",roleOrgName,"orgnization");
			employeeTreeForm.tree.changeItemId("-1",roleOrgId);
		}
		if(""==employeeTreeForm.tree.getItemText('-1')){
			employeeTreeForm.tree.setItemText("-1","组织员工树","orgnization");
			var icon ="book_titel.gif";
			employeeTreeForm.tree.setItemImage2('-1',icon,icon,icon);
		}
	});
}

$(document).ready(function(){		   
	var userId = PlatForm.user.objId;
	var roleOrgId='0';
	var roleOrgName ="";
	//判断是否超级管理员
	if(PlatForm.user.usrIsAdmin!='2'){
		$.getJSON("RoleController.do?method=getRoleOrgByUser",{'userId':userId},function(json){
			if(json.failure) return;
			if(json.result){
				roleOrgId = json.result.objId;
				roleOrgName = json.result.name;
				employeeTreeForm.initEmployeeTree(roleOrgId,roleOrgName);
			}
		})
	}else{
		employeeTreeForm.initEmployeeTree(roleOrgId,roleOrgName);
	}
	
	
	$(".treeRight").empty().loadPage("EmployeeController.do?method=toCreateOrUpdate");
	
	
	
	//节点上移
   	$("#up").click(function(){
   		var itemId=employeeTreeForm.tree.getSelectedItemId();
   		if("employee"==employeeTreeForm.tree.getItemTooltip(itemId)){
   			//上移还要判断上个节点不能是机构
   			//var prevNode = tree._getNextNode(employeeTreeForm.tree._globalIdStorageFind(itemId));
   			if(employeeTreeForm.isSort==0){
   				employeeTreeForm.isSort=1;
   	   	   		if(treeUtil.sortUp(employeeTreeForm.tree,"up","com.gpcsoft.srplatform.auth.domain.Employee"))
   	   	   			employeeTreeForm.isSort=0;
   	   		}
   		}
   		else{
   			alert('请选择一名员工！','提示',{icon:'info'});
   		}
   		
   		
	})
	//节点下移
	$("#down").click(function(){
		if("employee"==employeeTreeForm.tree.getItemTooltip(employeeTreeForm.tree.getSelectedItemId())){
			if(employeeTreeForm.isSort==0){
				employeeTreeForm.isSort=1;
				if(treeUtil.sortDown(employeeTreeForm.tree,"down","com.gpcsoft.srplatform.auth.domain.Employee"))
					employeeTreeForm.isSort=0;
			}
		}
		else{
   			alert('请选择一名员工！','提示',{icon:'info'});
   		}
		
	})
	//节点下移
	$("#chooseModel").click(function(){
		$("#conBody").empty().loadPage("EmployeeController.do");
	})
  
});

