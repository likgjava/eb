var organizationList={};
var roleList={};
roleList.rows=null;//列表查询的结果集
roleList.orgId='';//点击的树形节点id
roleList.orgName='';//点击的树形节点名称
roleList.roleId='';//列表中选取的角色id
roleList.div='roleListAllId';//存储当前用户所点击的div的id

	//加载tabs
	var $tabs = $('#epsTabs').tabs({
		add: function(event, ui) {
			$tabs.tabs('select', '#' + ui.panel.id);
		}
	}); 
	
	//点击左侧机构树动态查询右侧系统角色列表
	roleList.queryRoleListByOrgId=function(id){
		if(id=='-1'){//点击组织机构根节点
			roleList.orgId = '';
			roleList.orgName = '';
		}else{//点击组织机构非根节点
			roleList.orgId = id;
			roleList.orgName = organizationList.tree.getItemText(id)
		}
		//初始化角色所属机构（供表单页面使用）
		$("input[id=orgId]").attr('value',roleList.orgId);
		$("input[id=org.objId]").attr('value',roleList.orgId);
		$("input[id=org.name]").attr('value',roleList.orgName);
		$("#roleQuery").trigger('click'); //提交查询
	}

	//加载系统角色tab页
	roleList.showAllRole=function(){
		roleList.div='roleListAllId';
		$("#roleListDefaultId").empty();
		$("#roleListAllId").loadPage($('#initPath').val()+"/RoleController.do?method=roleListAll");
	}
	
	//加载全局角色tab页
	roleList.showDefaultRole=function(){
		roleList.div='roleListDefaultId';
		$("#roleListAllId").empty();
		$("#roleListDefaultId").loadPage($('#initPath').val()+"/RoleController.do?method=roleListDefault");
	}
	
	//根据用户所点击的div加载相应的页面
	roleList.showRole=function(id){
		if(roleList.div=='roleListAllId')
			roleList.showAllRole();
		else
			roleList.showDefaultRole();
	}
	
	//加载组织机构xml树
	function organizationTree(roleOrgId,roleOrgName){
		organizationList.tree=new dhtmlXTreeObject("organizationTree","100%","100%",0);
		organizationList.tree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
		organizationList.tree.setOnClickHandler(roleList.queryRoleListByOrgId);
		//organizationList.tree.setOnRightClickHandler(rightKeyTree);
		organizationList.tree.setXMLAutoLoading("OrganizationController.do?method=getOwnerOrgTree&order=sort&action=listById&isOpen=0");
		organizationList.tree.loadXML($("#initPath").val()+"/OrganizationController.do?method=getOwnerOrgTree&action=listTop&order=sort&isOpen=0&id="+roleOrgId+"&treeName="+roleOrgName,function(){
			
			if(roleOrgId!=0){
//				if(organizationList.tree.hasChildren('-1')==0){
//					$('div.treeOutside').remove();
//					$('#treeRight').removeClass('treeRight frameSub');
//					return;
//				}
				organizationList.tree.setItemText("-1",roleOrgName,"organization");
				organizationList.tree.changeItemId("-1",roleOrgId);
			}
			if(""==organizationList.tree.getItemText('-1')){
				organizationList.tree.setItemText("-1","组织机构","organization");
				var icon ="book_titel.gif";
				organizationList.tree.setItemImage2('-1',icon,icon,icon);
			}
		});
	}

$(document).ready(function(){
	
	var userId = PlatForm.user.objId;
	var roleOrgId='0';
	var roleOrgName ="";
	//判断是否超级管理员
	if(PlatForm.user.usrIsAdmin!='2'){
		
		$("#roleListDefaultIdTab").remove();
		
//		$.getJSON("RoleController.do?method=getRoleOrgByUser",{'userId':userId},function(json){
//			if(json.failure) return;
//			if(json.result){
//				roleOrgId = json.result.objId;
//				roleOrgName = json.result.name;
//				organizationTree(roleOrgId,roleOrgName);//加载左侧组织机构树
//			}
//		})
		//机构管理员角色不再和机构挂钩，这里直接取当前员工所在公司的id
		roleOrgId=PlatForm.user.emp.company.objId;
		roleOrgName=PlatForm.user.emp.company.name;
		organizationTree(roleOrgId,roleOrgName);//加载左侧组织机构树
		
	}else{
		organizationTree(roleOrgId,roleOrgName);//加载左侧组织机构树
		
		$("#roleListDefault").click(function(){//点击加载全局角色tab
			roleList.showDefaultRole();
		})
	}
	
	roleList.showAllRole();//加载系统角色tab
	
	$("#roleListAll").click(function(){//点击加载系统角色tab
		roleList.showAllRole();
	})
	
});