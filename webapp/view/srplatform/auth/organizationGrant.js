var OrganizationGrant={};

OrganizationGrant.sumbitClick=function(){
	var rolesArr=OrganizationGrant.tree.getAllCheckedBranches().replace("-1","");
	var dataObj={"objId":$("#objId").val()};
	dataObj.roles=[];
	//没有选中用户的时候，不执行SET值操作
	if(rolesArr != ""){
		var rolesCount=0;
		for(var i = 0 ; i<rolesArr.split(",").length ; i++){
			//处理修改时,默认选中时带有空id
			if(rolesArr.split(",")[i]!=""&&rolesArr.split(",")[i]!=null){
				//初始话dataObj.roles[i]为空对象
				dataObj.roles[rolesCount]={};
				dataObj.roles[rolesCount].objId=rolesArr.split(",")[i];
				rolesCount++;
			}
		}
	}
	dataObj.json=JSON.stringify(dataObj);
	
	//将提交按钮置为不可用
	$("*[name=submit]").attr("disabled","true");
	$.ajax({
		url:"OrganizationController.do?method=saveRoles",
		type:"POST",
		data:dataObj,
		success:function(msg){	
			loadPage_toContent("/OrganizationController.do?method=toList")
		}
	})
}


$(document).ready(function(){
	index.treeHeight();
	$(window).resize(function(){index.treeHeight()});

	OrganizationGrant.tree=new dhtmlXTreeObject('treeLeft',"100%","85%",0); 
	OrganizationGrant.tree.enableDragAndDrop(false);
	OrganizationGrant.tree.enableCheckBoxes(true)
	//初始化按钮不级联选中 0为表示不选中,1为选中状态
	OrganizationGrant.tree.enableThreeStateCheckboxes(0);
	OrganizationGrant.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/csh_vista/");
	OrganizationGrant.tree.loadXML("RoleTreeController.do?method=getBaseObjectListByProperty&action=listTop&id=0&isOpen=1",function(){	

		if($("#objId").val()!=""&&$("#objId").val()!="null"){
			$.getJSON("OrganizationController.do?method=createOrUpdate",{objId:$("#objId").val()},function(json){
				json2ObjectDiv("OrganizationInfo",json.list[0])
				$.getJSON("OrganizationController.do?method=getRolesByOrg",{objId:$("#objId").val()},function(json){
					$.each(json,function(i,n){
						OrganizationGrant.tree.setCheck(n.objId,1);
					})
					OrganizationGrant.tree.setCheck("-1",1);
					OrganizationGrant.tree.enableThreeStateCheckboxes(1);
				})
			})
		}else{//处理JSON数据延迟加载的问题
			OrganizationGrant.tree.enableThreeStateCheckboxes(1);
		}

	});

	//返回
	$("#return").click(function(){
		$("#content").loadPage("/OrganizationController.do");
	});

	//提交
	$("#submit").click(OrganizationGrant.sumbitClick)
})