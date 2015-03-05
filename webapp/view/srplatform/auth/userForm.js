//定义文件全局变量 处理方法名重复问题

var userForm={};

$(document).ready(function(){
	
	//判断是否超级管理员
	if(PlatForm.user.usrIsAdmin!='2'){
		$("input[id=emp.name]").addClass("required");
	}
	
	$.validator.addMethod(
			"unique",
			function(value,element,param){
				return uniqueHandler("User",param,value,$("#objId").val());
			},
			'用户名已存在'
	);
	$("#userForm").validate(
			{
				rules:{
					usName:{unique:"usName"}
				}
			}
	);	
	
//	if($('#employeeId').val()!='')
//		$('#makeEmployeeBtn').hide();
	
	$('#userForm').validate();
	
	//账号有效期
	$('#usrPeriodOfValidity').epsDatepicker({ timeShow:false, picker: "" });
	$('#usrPeriodOfValidity').val(new Date().DateAdd('y',1).Format('yyyy-MM-dd'));//当前时间累加1年
	
	//密码有效期
	$('#usrPwdPeriodValidity').epsDatepicker({ timeShow:false, picker: "" });
	$('#usrPwdPeriodValidity').val(new Date().DateAdd('y',1).Format('yyyy-MM-dd'));//当前时间累加1年
	
	//超级管理员下拉框选择(普通用户0、机构管理员1、超级管理员2)
	$('#usrIsAdmin').append('<option value=0 selected>普通员工</option>');
	
	//alert(PlatForm.user.usrIsAdmin)
	if(PlatForm.user.usrIsAdmin=='2'){
		$('#usrIsAdmin').append('<option value=1>机构管理员</option>');
		$('#usrIsAdmin').append('<option value=2>超级管理员</option>');
	}
	
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/UserController.do?method=createOrUpdate',{objId:$('#objId').val(),includedProperties:'emp'},function(json){
    		jsonObjectToForm('userForm', json.user);
    	});
    }
	//保存提交
	$('#userSave').click(function(){
		if(!$('#userForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		//alert(obj2str(formToJsonObject('userForm')));
		$.getJSON($('#initPath').val()+'/UserController.do?method=saveUser', formToJsonObject('userForm'), function(json){
			if(json.failure){if(json.result)alert(json.result,{inco:'info'});;return;}	
			//重置密码，并邮件通知用户
			$.getJSON($('#initPath').val()+'/UserController.do?method=resetPassword',{objId:json.userId},function(json){
				if(json.failure){if(json.result)alert(json.result);return;}
			});
			$("button[name=historyBackBtn]").click();
		});
	});
	
	//开通员工账号
	$('#makeEmployeeBtn').click(function(){
		if(!$('#userForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		$.getJSON($('#initPath').val()+'/UserController.do?method=saveUser', formToJsonObject('userForm'), function(json){
			if(json.failure){if(json.result)alert(json.result,{inco:'info'});;return;}		
			$('#conBody').loadPage($('#initPath').val()+'/UserController.do?method=toMakeEmployeePage&userId='+json.userId);
		});
	});
	
	//查询组织机构弹出树
	$("input[id=emp.name]").click(function(e){
		var url = PlatForm.user.usrIsAdmin!='2'? 'OrganizationController.do?method=toFloatEmployeeTree&hiddenProperty=emp.objId&showProperty=emp.name' : 'view/srplatform/auth/org/employee_to_user.jsp?&hiddenProperty=emp.objId&showProperty=emp.name';
		$.epsDialog({
	        title:'请选择一名员工进行绑定',
	        url:url,
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
	    }); 
 	})	

});
