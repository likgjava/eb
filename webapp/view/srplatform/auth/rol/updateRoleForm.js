var roleForm={};

//获得资源树
roleForm.getResourceTree = function(){
	roleForm.tree;
	roleForm.tree = new dhtmlXTreeObject("roleResourceTreeGrid","100%","100%",0);
	roleForm.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	roleForm.tree.setOnClickHandler(null);
	roleForm.tree.enableCheckBoxes(1);
	roleForm.tree.enableThreeStateCheckboxes(true);
	roleForm.tree.setXMLAutoLoading("ResourceController.do?method=getResourceFilterTree&action=listById&isOpen=1",function(){});
	roleForm.tree.loadXML($("#initPath").val()+"/ResourceController.do?method=getResourceFilterTree&action=openChecked&isOpen=1&id=0",function(){
		roleForm.checkResourceByRole($("#resourceIdMy").val(),"select")
	});
}

//选择资源by角色
roleForm.checkResourceByRole = function(roleResourceIds,selectStatus){
	$.each(roleResourceIds.split(","),function(index,obj){
		if(selectStatus=='select' ){
			if( roleForm.tree.getAllSubItems(obj)==''){
				roleForm.tree.setCheck(obj,1);
			}
		}else{
			roleForm.tree.setCheck(obj,0);
		}
	})
}

$(document).ready(function(){
	
	//初始化树
	roleForm.getResourceTree();
	
	$('#roleSave').click(function(){
		if(!$('#roleForm').valid()){return false;}
		if(PlatForm.user.emp){
			$('input[name=org.objId]').val(PlatForm.user.emp.company.objId);
		}
		var jsonObj = formToJsonObject('roleForm');
		var allcheckedResource = roleForm.tree.getAllCheckedBranches();
		if(allcheckedResource.indexOf("-1")>=0){
			allcheckedResource = allcheckedResource.replace("-1,","").replace(",-1","");
		}
		jsonObj.resPathIds = allcheckedResource;
		$.getJSON($('#initPath').val()+'/RoleController.do?method=saveRole',jsonObj, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('[name=historyBackBtn]').click();
			});
	});
	
});