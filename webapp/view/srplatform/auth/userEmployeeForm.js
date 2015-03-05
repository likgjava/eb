//定义文件全局变量 处理方法名重复问题

var employeeForm={};

$(document).ready(function(){
//	$('#employeeForm').validate();
    if($('#objId').val()!=''){
    	$.getJSON($('#initPath').val()+'/EmployeeController.do?method=createOrUpdate',{objId:$('#objId').val()},function(json){
    		jsonObjectToForm('employeeForm', json.employee);
    	});
    }
	//返回
	$('#employeeReturn').click(function(){
		$('#conBody').loadPage($('#initPath').val()+"/EmployeeController.do");
	});
	//提交
	$('#employeeSave').click(function(){
//		if(!$('#employeeForm').valid()){alert('请正确填写表单!','提示',{icon:'info'});return;}
		$.getJSON($('#initPath').val()+'/EmployeeController.do?method=saveEmployeeByUser', formToJsonObject('employeeForm'), function(json){
			if(json.result)alert(json.result,{inco:'info'});;if(json.failure)return;
			$('#conBody').loadPage($('#initPath').val()+'/UserController.do');
		});
	});
	
	//选择部门岗位
	$('input[id=org.name]').click(function(){
	    $.epsDialog({
	        title:'选择'+$('#name').val()+'的部门岗位',
	        url:$('#initPath').val()+'/TreeController.do?method=toTree&property=org&className=Organization',
	        width: '500',
	        height: '400',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
	    }); 
	});

});
