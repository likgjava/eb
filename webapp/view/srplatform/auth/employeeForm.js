//定义文件全局变量 处理方法名重复问题

var employeeForm={};
employeeForm.roleOrgId = '0' ;

employeeForm.openAccount=function(){
	$("#openAccount").remove();
	$("#accountLi").removeClass("eleHide");
	//$("#emailLi").css("display","inline")
	$("#empUsName").addClass("required")
	//$("#empUsEmail").addClass("required")
	$.validator.addMethod(
			"unique",
			function(value,element,param){
				return uniqueHandler("User","usName",value);
			},
			'账号已经存在'
	);
	$("#employeeForm").validate(
			{
				rules:{
					empUsName:{unique:"empUsName"}
				}
			}
	);	
}


$(document).ready(function(){
	
	if("true"!=$("#isSelf").val()){
		if(employeeTreeGrid.tree){
			var id = employeeTreeGrid.tree.getSelectedItemId();
			if(id==''||id=='-1'){
				$("input[id=orgId]").val('');
				$("input[id=orgName]").val('');
			}
			else{
				$("input[id=orgId]").val(id);
				$("input[id=orgName]").val(employeeTreeGrid.tree.getItemText(id));
			}
			
		}
	}
	
	$('#orgName').autocomplete($('#initPath').val() + '/CompanyController.do?method=getObjectQuery&queryColumns=objId,name,shortName', {
		extraParams:{
		'status':'1',
		'status_relative':'[and]'},//查询条件  例如    {objId:111}
		matchColumn:'name,shortName',//作为查询显示, 被选中之后匹配的列
		mustMatch: false,
		formatItem: function(data, i, total) {
			return data.name;
		},
		formatMatch: function(data, i, total) {
			return data.name;
		},
		formatResult: function(data) {
			return data.name;
		}
	}).result(function(event,data,formatted){
		if(data){
			$("#orgName").val(data.orgName);//回填名称
			$("#orgId").val(data.objId);//回填id
		}
	});   
	
	
	//判断是否超级管理员
	if(PlatForm.user.usrIsAdmin!='2'){
//		$.getJSON("RoleController.do?method=getRoleOrgByUser",{'userId':PlatForm.user.objId},function(json){
//			if(json.failure) return;
//			if(json.result){
//				employeeForm.roleOrgId = json.result.objId;
//			}
//		})
		employeeForm.roleOrgId = PlatForm.user.emp.company.objId;
	}
	
	//自己维护信息，不可改变所属机构
	if($("#isSelf").val()=='true'){
//		$("input[id=orgName]").click(function(e){
//			 $.epsDialog({
//		            title:'请选择员工直属机构',
//		            url:'OrganizationController.do?method=toFloatOrgTree&hiddenProperty=orgId&showProperty=orgName&roleOrgId='+employeeForm.roleOrgId,
//		            width: '600',
//		            height: '360',
//		            hasBg:true,//背景
//		            fadeTo:80 //透明度
//			    });
//		})	
		$("input[id=orgName]").attr("disabled","disabled");
	}
	
	
    if($('#objId').val()!=undefined && $('#objId').val()!=''){
    	$("#usName").remove();
    	var include = new Array('company','department','post');
    	$.getJSON($('#initPath').val()+'/EmployeeController.do?method=createOrUpdate',{objId:$('#objId').val(),includedProperties:include.toString()},function(json){
    		jsonObjectToForm('employeeForm', json.employee);
    		var emp = json.employee;
    		var org = emp.post!=null?emp.post:emp.department!=null?emp.department:emp.company;
    		$("#orgId").val(org.objId);
    		$("#orgName").val(org.name);
    	});
    }
    else{
    	$("#openAccount").css("display","inline");
    	$("#openAccount").click(employeeForm.openAccount);
    }
    
    
	//返回
	$('#employeeReturn').click(function(){
		if('true'==$('#isSelf').val()) return;
		
			$('.treeRight').loadPage($('#initPath').val()+'/EmployeeController.do?method=toGrid') ;
	});
	//提交
	$('#employeeSave').click(function(){
		if(!$('#employeeForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		$.getJSON($('#initPath').val()+'/EmployeeController.do?method=saveEmployee', formToJsonObject('employeeForm'), function(json){
			if(json.result)alert(json.result,'提示',{inco:'info'});;if(json.failure)return;
			if($("#isSelf").val()=='true')$('#epsDialogClose').click();;
			$('.treeRight').loadPage($('#initPath').val()+'/EmployeeController.do?method=toGrid') ;
//			if("grid"==$("#model").val()){
//				$('#conBody').loadPage($('#initPath').val()+'/EmployeeController.do') ;
//			}
//			else{
//				var orgId ;
//				if(json.employee.post)orgId = json.employee.post.objId;
//				else if(json.employee.department)orgId = json.employee.department.objId;
//				else orgId = json.employee.company.objId;
//				employeeTreeForm.reloadTree(json.employee.objId,json.employee.name,orgId);
//				$('.treeRight').empty().loadPage($('#initPath').val()+'/EmployeeController.do?method=toShowView&objId='+json.employee.objId);
//			}
		});
	});
	

});
