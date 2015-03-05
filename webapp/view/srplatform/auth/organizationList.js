//包组区分JS方法、属性的唯一性
var organizationList={};
organizationList.objIdVal;//调转页面前empty前，记录当前objId。
organizationList.tree;
organizationList.roleOrgId='0';//机构范围
organizationList.model='tree';//机构范围

organizationList.isSort=0;
//删除机构
organizationList.delOrg = function(id){
	if(window.confirm("确定删除?")){
			$.ajax({
					url:"OrganizationController.do?method=deleteOrg",
					type:"POST",
					data:{objId:id},
					success:function(json){
						if(json.result)alert(json.result,{inco:'info'});;
						if(json.failure)return;
						organizationList.tree.deleteItem(id,true);
					},error:function(json){
						alert(json)
					}
			})
		}
	
}

//获取 新增下级菜单,type 机构类型   btnsArea动态按钮的区域div
organizationList.getOrgRules = function(type,btnsArea,objId,shortName){
	if(type=='1'){
		//btnsArea.prepend(organizationList.createBtnHtml('2',objId,shortName));
		btnsArea.prepend(organizationList.createBtnHtml('1',objId,shortName));
	}
	else if(type=='2'){
		btnsArea.prepend(organizationList.createBtnHtml('2',objId,shortName));
	}
//	$.getJSON($("#initPath").val()+"/OrganizationController.do?method=getOrgRules",{type:type},function(json){
//		var rule = json.result;
//		if(rule.length>0){
//			for(var i=0;i<rule.length;i++){
//				btnsArea.prepend(organizationList.createBtnHtml(rule[i],objId,shortName));
//			}
//		}
//		
//	})
}
organizationList.createBtnHtml = function(type,objId,shortName){
	var btn;
	switch(type){
		case '1':
			btn = "<li class='add'><a href='view/bizplatform/organization/manager/create_orginfo.jsp?parentCompanyId="+objId+"&model=tree&parentShortName="+shortName+"' target='#treeRight' ><span>新增下级公司</span></a></li>";
			break;
		case '2':
			btn = "<li class='addDep'><a href='DepartmentController.do?method=toCreateOrUpdate&model=tree&parentShortName="+shortName+"&parentId="+objId+"' target='#treeRight' ><span>新增下级部门</span></a></li>";
			break;
		case '3':
			btn = "<li class='addJob'><a href='PostController.do?method=toCreateOrUpdate&model=tree&parentShortName="+shortName+"&parentId="+objId+"' target='#treeRight' ><span>新增下级岗位</span></a></li>";
			break;
		default:
			btn="";
		break;
	}
	return btn;
} 
//刷新节点
organizationList.reloadTree = function (itemId,text,type){
	//判断是否增加还是修改
	if(organizationList.tree._globalIdStorageFind(itemId)){
		organizationList.tree.setItemText(itemId,text);
	}else{
		var temp = organizationList.tree.getSelectedItemId();
		if(!temp)temp=-1;
		//获取节点状态：0 - 没有子节点或者  节点合拢, 1  节点展开
		if(organizationList.tree.hasChildren(temp)&&organizationList.tree.getOpenState(temp)==0){
			return false;
		}
		else{
			var icon ="book_titel.gif";
			if(type==1)icon ="tombs_mag.gif";
			else if(type==2)icon ="magazines_open.gif";
			else if(type==3)icon ="iconClient.gif";
			organizationList.tree.insertNewItem(temp,itemId,text,0,0,0,0,'SELECT');
			organizationList.tree.setItemImage2(itemId,"node.gif","folderOpen.gif","folderClosed.gif");
		}
	}
	
}

//点击事件
organizationList.edit=function(objId){
	
	if(objId==""||objId==-1||objId==0){
		return false;
	}
	else{
		var queryColumns =[];
		queryColumns.push("type");
		queryColumns.push("objId");
		$.getJSON($("#initPath").val()+"/OrganizationController.do?method=createOrUpdate",{objId:objId,queryColumns:queryColumns.toString()},function(json){
			if(json.result)alert(json.result,{inco:'info'});;
			if(json.failure)return;
			switch(json.organization.type){
			case '1':
				$(".treeRight").loadPage("CompanyController.do?method=toShowView&objId="+objId);
				break;
			
			case '2':
				$(".treeRight").loadPage("DepartmentController.do?method=toShowView&objId="+objId);
				break;
				
			case '3':
				$(".treeRight").loadPage("PostController.do?method=toShowView&objId="+objId);
				break;
			default:
				alert('未找到 机构类型');
				break;
			}
		})
	}
	
}

//右键菜单,e为兼容FireFox
organizationList.rightKeyTree=function(id,e){
	organizationList.tree._selectItem(organizationList.tree._globalIdStorageFind(id));
	//alert(this.getItemText(id));
	
	//清空菜单
	$('.epsContentMenu>ul').html("");
	
	if(id==""||id==-1||id==0){
		if(PlatForm.user.usrIsAdmin!='2'){
			return;
		}
		//只有新增公司选项
		$('.epsContentMenu').find('ul').append("<li ><a href='CompanyController.do?method=toCreateOrUpdate' target='#treeRight' class='add aLink'  ><span></span>新增公司</a></li>").end().epsContentMenu(id,{},e);
	}
	else{
		$.getJSON($("#initPath").val()+"/OrganizationController.do?method=createOrUpdate",{objId:id},function(json){
			organizationList.getOnRightMenu(json.organization.type,$('.epsContentMenu>ul'),id);
			$('.epsContentMenu').epsContentMenu(id,{},e);
		})
	}
}

//加载xml树
function orgnizationTree(){
	
	//dhtmlXTreeObject(htmlObject, width, height, rootId)//rootId 虚拟根节点，在界面上不会显示，一般取值0
	organizationList.tree=new dhtmlXTreeObject("orgnizationTree","100%","100%",0);
	organizationList.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	organizationList.tree.setOnClickHandler(organizationList.edit);
	//organizationList.tree.setOnRightClickHandler(organizationList.rightKeyTree);
	organizationList.tree.setXMLAutoLoading("OrganizationController.do?method=getOwnerOrgTree&order=sort&action=listById&isOpen=0");
	organizationList.tree.loadXML($("#initPath").val()+"/OrganizationController.do?method=getOwnerOrgTree&action=listTop&order=sort",function(){
		if(PlatForm.user.usrIsAdmin!='2'){
			organizationList.tree.changeItemId("-1",organizationList.roleOrgId);
		}
		
	});
		
	//设置拖拽事件
	organizationList.tree.setDragHandler(function(id,newParentId){
		//无法更新 ("PLAT"."AUTH_ORGNIZATION"."ORG_AUDIT_STATUS") 为 NULL
		if(organizationList.isSort==1)return true;
   		var jsonObj={};
   		jsonObj.sort=organizationList.tree._globalIdStorageFind(newParentId).childsCount;
   		if(newParentId == "-1")newParentId = null;
   		jsonObj.objId=id;
   		//jsonObj.parent={};
   		//jsonObj.parent.objId=newParentId;
   		jsonObj['parent.objId']=newParentId;
   		jsonObj.json=JSON.stringify(jsonObj);
   		alert(jsonObj.json)
   		$.ajax({
			url : $("#initPath").val()+"/OrganizationController.do?method=save",
			data: jsonObj,
			type:"POST"
		})
		return true;
   	})
}



$(document).ready(function(){   
	//判断是否超级管理员
	if(PlatForm.user.usrIsAdmin!='2'){
		organizationList.roleOrgId = PlatForm.user.emp.company.objId;
		orgnizationTree();
		$("#treeRight").empty().loadPage("CompanyController.do?method=toShowView&objId="+organizationList.roleOrgId);
//		$.ajax({
//			url:"RoleController.do?method=getRoleOrgByUser",
//			type:"POST",
//			async:false,
//			data:{'userId':PlatForm.user.objId},
//			dataType:'json',
//			success:function(json){
//				if(json.failure) return;
//				if(json.result){
//					organizationList.roleOrgId = json.result.objId;
//					orgnizationTree();
//				}
//			}
//		})
	}
	else{
		orgnizationTree();
		$("#treeRight").empty().loadPage("CompanyController.do?method=toCreateOrUpdate");
	}
	
	
	
	//节点上移
   	$("#up").click(function(){
   		if(organizationList.tree.getSelectedItemId()==""){
   			return;
   		}
   		if(organizationList.isSort==0){
   			organizationList.isSort=1;
   	   		if(treeUtil.sortUp(organizationList.tree,"up","com.gpcsoft.srplatform.auth.domain.Organization"))
   	   			organizationList.isSort=0;
   		}
   		
	})
	//节点下移
	$("#down").click(function(){
		if(organizationList.tree.getSelectedItemId()==""){
   			return;
   		}
		if(organizationList.isSort==0){
			organizationList.isSort=1;
			if(treeUtil.sortDown(organizationList.tree,"down","com.gpcsoft.srplatform.auth.domain.Organization"))
				organizationList.isSort=0;
		}
	})
	//chooseModel
	$("#chooseModel").click(function(){
			$("#conBody").empty().loadPage("OrganizationController.do");
	})
	
})




	
