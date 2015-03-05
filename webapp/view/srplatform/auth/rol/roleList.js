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
	

	//加载系统角色tab页
	roleList.showAllRole=function(){
		roleList.div='roleListAllId';
		$("#roleListDefaultId").empty();
		$("#roleListAllId").loadPage($('#initPath').val()+"/view/srplatform/auth/rol/roleListAll.jsp");
	}
	
	//加载全局角色tab页
	roleList.showDefaultRole=function(){
		roleList.div='roleListDefaultId';
		$("#roleListAllId").empty();
		$("#roleListDefaultId").loadPage($('#initPath').val()+"/view/srplatform/auth/roleListDefault.jsp");
	}
	
	//根据用户所点击的div加载相应的页面
	roleList.showRole=function(id){
		if(roleList.div=='roleListAllId')
			roleList.showAllRole();
		else
			roleList.showDefaultRole();
	}
	
	//加载组织机构xml树
	function organizationTree(roleOrgId,roleOrgName){}

$(document).ready(function(){
	
	$("#roleListDefault").click(function(){//点击加载全局角色tab
			roleList.showDefaultRole();
		})
	
	
	$("#roleListAll").click(function(){//点击加载系统角色tab
		roleList.showAllRole();
	})
	
	var userId = PlatForm.user.objId;
	var roleOrgId='0';
	var roleOrgName ="";
	//判断是否超级管理员
	if(PlatForm.user.usrIsAdmin!='2'){
		$("#roleListDefaultIdTab").remove();
		roleList.showAllRole();
	}else{
		$("#postRoleTab").remove();
		$("#roleListDefault").click();
	}
		
	
});