var roleListAll={};
roleListAll.rows=null;//列表查询的结果集
	
	//分配资源
	roleListAll.saveResource=function(name,grid){
		if(!roleListAll.validation(name,grid))return;
		roleList.roleId = $(grid).getSelect();
		$("#returnUrl").val($('#initPath').val()+'/view/srplatform/auth/rol/roleList.jsp');
		$('#roleListAllId').loadPage($('#initPath').val()+"/RoleController.do?method=roleAuthorizeResource");
	}

	//新增
	roleListAll.add=function(name,grid){
		//跳转到新增页面
		$("#returnUrl").val($('#initPath').val()+'/view/srplatform/auth/rol/roleList.jsp');
		$('#conBody').empty().loadPage($('#initPath').val()+"/RoleController.do?method=toCreateRoleFormView");
	}   
	
	//修改
	roleListAll.update=function(name,grid){
		$("#returnUrl").val($('#initPath').val()+'/view/srplatform/auth/rol/roleList.jsp');
		if(!roleListAll.validation(name,grid))return;
		//跳转到修改页面
		$('#conBody').empty().loadPage($('#initPath').val()+"/RoleController.do?method=toUpdateRoleFormView&objId="+$(grid).getSelect());
	}   
	
	//删除
	roleListAll.remove=function(name,grid){
		if(!roleListAll.validation(name,grid))return;
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/RoleController.do?method=deleteRole',{'roleId':$(grid).getSelect()},function(json){
				//if(json.result)alert(json.result);
				if(json.failure)return;
				$(grid).reload();//刷新
			});
		}
	}
	
	//授权
	roleListAll.authToUser = function(name,grid){
		if(!roleListAll.validation(name,grid))return;
		$.epsDialog({
			id:"authToUserDiv",
			title:"给用户授权",
			url:$("#initPath").val()+"/view/srplatform/auth/rol/role_to_user.jsp?roleId="+$(grid).getSelect()
		})
	}
	
	//列表操作验证
	roleListAll.validation=function(name,grid){
		if($(grid).isSelectEmpty()){alert('请选择角色'+name);return false;}//是否选中
		if(!$(grid).isSelectOne()){alert('请选择一个角色'+name);return false;}//是否只选中一个
		return true;
	}
	
	//查询条件过滤
	roleListAll.before=function(){
		var option={
			'org.objId':PlatForm.user.emp.company.objId
		}
		$('#roleGrid').flexOptions({params:option});
		return true;
	}
	roleListAll.success = function(){
		$("#roleGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">详情</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					$("#returnUrl").val($('#initPath').val()+'/view/srplatform/auth/rol/roleList.jsp');
					$('#conBody').empty().loadPage($('#initPath').val()+"/RoleController.do?method=toShowDetail&objId="+rowId);
				}).appendTo(obj);
			}
		});
	}

$(document).ready(function(){
	$('input[name=org.name]').autocomplete($('#initPath').val() + '/OrganizationController.do?method=getObjectQuery&queryColumns=name,shortSpellName', {
		extraParams:{},//查询条件  例如    {objId:111}
		matchColumn:'name,shortSpellName',//作为查询显示, 被选中之后匹配的列
		minChars: 0,
		max: 8,
		autoFill: false,
		mustMatch: false,
		scrollHeight: 220,
		formatItem: function(data, i, total) {
			return data.name;
		},
		formatMatch: function(data, i, total) {
			return data.name;
		},
		formatResult: function(data) {
			return data.name;
		}
	});     
});