//定义文件全局变量 处理方法名重复问题
var ProcessDefNodeForm={};
$(document).ready(function(){
	$('[id=role.chName]').click(function(){
		$.epsDialog({
			 title:'请选择执行角色',
	         width: '650',
	         height: '420',
			 id:'select_role',
			 url:"view/workFlow/selectRoleList.jsp"
		})
	})
	$('[id=department.name]').click(function(){
		$.epsDialog({
			 title:'请选择执行部门',
	         width: '650',
	         height: '420',
			 id:'select_dept',
			 url:'view/workFlow/selectOrganizationList.jsp'
		})
	})
	$('[name=save_node]').click(function(){
		if(!$('#ProcessDefNodeForm').valid()){return;}
		$.getJSON($('#initPath').val()+'/ProcessDefNodeController.do?method=save', formToJsonObject('ProcessDefNodeForm'), function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$("#processDefNodeGrid").reload();
			$('#process_node_form .epsDialogCloseReload').click();
		});
	})
	$('[name=return_to_process_list]').click(function(){
		$('#conBody').empty().loadPage($('#initPath').val()+'/ProcessDefineController.do');
		$('#process_node_form .epsDialogCloseReload').click();
	})
	$('[name=return_to_node_list]').click(function(){
		$('#process_node_form .epsDialogCloseReload').click();
	})
})