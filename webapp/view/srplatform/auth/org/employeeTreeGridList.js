
var employeeTreeGrid={};
employeeTreeGrid.orgId;
employeeTreeGrid.tree;

//点击节点事件
employeeTreeGrid.nodeClick=function(){
	var id = employeeTreeGrid.tree.getSelectedItemId();
	if(id=='-1'){
		$("input[name=orgId]").attr('value','');
		$("input[name=orgName]").attr('value','');
		$("#employeeSearchZone button").click(); //提交查询
		return;
	}
	var name =employeeTreeGrid.tree.getItemText(id)
	$("input[name=orgId]").attr('value',id);
	$("input[name=orgName]").attr('value',name);
	$("#employeeSearchZone button").click(); //提交查询
}


//初始化树
//加载xml树
employeeTreeGrid.orgnizationTree=function(){
	employeeTreeGrid.tree=new dhtmlXTreeObject("employeeTreeGrid","100%","100%",0);
	employeeTreeGrid.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	employeeTreeGrid.tree.setOnClickHandler(employeeTreeGrid.nodeClick);
	//employeeTreeGrid.tree.setOnRightClickHandler(employeeTreeGrid.rightKeyTree);
	employeeTreeGrid.tree.setXMLAutoLoading("OrganizationController.do?method=getOwnerOrgTree&order=sort&action=listById&isOpen=0");
	employeeTreeGrid.tree.loadXML($("#initPath").val()+"/OrganizationController.do?method=getOwnerOrgTree&action=listTop&order=sort",function(){
		if(PlatForm.user.usrIsAdmin!='2'){
			employeeTreeGrid.tree.changeItemId("-1",employeeTreeGrid.orgId);
		}
		
	});
		
}
$(document).ready(function(){	   
	//判断是否超级管理员
	if(PlatForm.user.usrIsAdmin!='2'){
		employeeTreeGrid.orgId = PlatForm.user.emp.company.objId;
	}
//	employeeTreeGrid.orgnizationTree();
	$("#treeRight").empty().loadPage($("#initPath").val()+"/EmployeeController.do?method=toGrid");
	
  
});

