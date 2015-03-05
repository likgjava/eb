var roleForm={};

$(document).ready(function(){
	if(roleList.div == 'roleListDefaultId'){//如果用户点击全局角色div则表单页面
		$("#orgForm").remove();//移除组织机构
	}
	//加载修改页面的值
	if( $('#objId').val() != undefined && $('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/RoleController.do?method=createOrUpdate',{objId:$('#objId').val()},function(json){
    		jsonObjectToForm('roleForm', json.role);
    	});
    }
	
	//初始化角色所属机构
	$("input[id=org.objId]").attr('value',roleList.orgId);
	$("input[id=org.name]").attr('value',roleList.orgName);
	
	if(PlatForm.user.usrIsAdmin != '2' && roleList.div != 'roleListDefaultId'){//如果为机构管理员或全局角色页面
		//机构管理员只能创建普通用户
		//$("<option value='0'>"+"普通角色"+"</option>").appendTo($("#type"));
		//$("input[id=org.name]").attr("disabled",true);
		if(roleList.orgName == ''){//如果未选中组织机构,设置所选机构默认值,直接取当前用户所属公司
//			$.getJSON("RoleController.do?method=getRoleOrgByUser",{'userId':PlatForm.user.objId},function(json){
//				if(json.failure) return;
//				if(json.result){
//					$('input[name=org.name]').attr('value',json.result.name);
//					$('input[name=org.objId]').attr('value',json.result.objId);
//				}
//			})
			$('input[name=org.name]').attr('value',PlatForm.user.emp.company.shortName);
			$('input[name=org.objId]').attr('value',PlatForm.user.emp.company.objId);
		}
	}else{//如果为超级管理员，可以创建机构管理员和普通用户
		//$("<option value='0'>"+"普通角色"+"</option>").appendTo($("#type"));
		//$("<option value='1'>"+"机构管理员"+"</option>").appendTo($("#type"));
		//$("<option value='3'>"+"机构角色"+"</option>").appendTo($("#type"));
	}
    
	//取消按钮
	$('#roleReturn').click(function(){
		roleList.showRole(roleList.div);
	});
	
	//保存按钮
	$('#roleSave').click(function(){
		if(!$('#roleForm').valid()){alert('请正确填写表单!');return;}
		$.getJSON($('#initPath').val()+'/RoleController.do?method=save', formToJsonObject('roleForm'), function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				roleList.showRole(roleList.div);//加载系统角色div
			});
		
//		if($("#objId").val()=="" && $("#type").val()=="1"){//超级管理员新增操作时判断所选机构是否已存在机构管理员
//			var orgId = $("*[name=org.objId]").val();
//			$.getJSON("RoleController.do?method=checkOrgManager",{'orgId':orgId},function(json){
//				 if(json.failure) return;
//				 if(json.result){
//					 if(json.result=='false'){//不存在机构管理员
//						 $.getJSON($('#initPath').val()+'/RoleController.do?method=save', formToJsonObject('roleForm'), function(json){
//								if(json.result)alert(json.result);if(json.failure)return;
//								roleList.showRole(roleList.div);//加载div
//						 });
//					 }else{//已存在机构管理员
//						 alert('此机构已存在机构管理员');
//					 }
//				 }
//	    	})
//		}else if($("#objId").val()=="" && $("#type").val()=="3"){//超级管理员新增操作时判断所选机构是否已存在机构角色
//			var orgId = $("*[name=org.objId]").val();
//			$.getJSON("RoleController.do?method=checkOrgRole",{'orgId':orgId},function(json){
//				 if(json.failure) return;
//				 if(json.result){
//					 if(json.result=='false'){//不存在机构角色
//						 $.getJSON($('#initPath').val()+'/RoleController.do?method=save', formToJsonObject('roleForm'), function(json){
//								if(json.result)alert(json.result);if(json.failure)return;
//								roleList.showRole(roleList.div);//加载div
//						 });
//					 }else{//已存在机构角色
//						 alert('此机构已存在机构角色');
//					 }
//				 }
//	    	})
//		}else{//机构管理员
//			$.getJSON($('#initPath').val()+'/RoleController.do?method=save', formToJsonObject('roleForm'), function(json){
//				if(json.result)alert(json.result);if(json.failure)return;
//				roleList.showRole(roleList.div);//加载系统角色div
//			});
//		}
	});
 	
 	//查询组织机构弹出树
//	$("input[id=org.name]").click(function(e){
//		$.epsDialog({
//	        title:'选择组织机构',
//	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=org&className=Organization',
//	        width: '500',
//	        height: '400',
//	        onOpen: function(){ },
//	        afterLoad: function(){ },
//	        onClose: function(){ }
//	    }); 
// 	})

});
