
var employeeForm={};
$(document).ready(function(){
	//if("grid"==$("#model").val()){
	//	$('#createBtnsArea').append("<li  class=\"back\"><a href=\"EmployeeController.do\" target=\".treeRight\"><span>返回</span></a></li>") ;
	//}
	if($('#objId').val()!=''){
		
    	//alert(queryColumns.toString())
    	$.getJSON($('#initPath').val()+'/EmployeeController.do?method=createOrUpdate', {objId:$('#objId').val(),includedProperties:"company,department,post"}, function(json){
    		//alert(obj2str(json.employee))
    		json2ObjectSpan('employeeDetailForm',json.employee);
    	});
    }
	//edit
	$('#empEdit').click(function(){
//		if("grid"==$("#model").val()){
//			$('#conBody').loadPage($('#initPath').val()+'/EmployeeController.do?method=toCreateOrUpdate&model=grid&objId='+$('#objId').val());
//		}
//		else{
			$('.treeRight').loadPage($('#initPath').val()+'/EmployeeController.do?method=toCreateOrUpdate&objId='+$('#objId').val());
//		}
		
	});
	$('#back').click(function(){
			$('.treeRight').loadPage($('#initPath').val()+'/EmployeeController.do?method=toGrid');
	});
	//delete
	$('#empDel').click(function(){
		if(window.confirm("确定删除?")){
			$.ajax({
					url:"EmployeeController.do?method=delEmpByIds",
					type:"POST",
					data:{objId:$("#objId").val()},
					success:function(json){
						if(json.result)alert(json.result,{inco:'info'});;
						if(json.failure)return;
						$('.treeRight').loadPage($('#initPath').val()+'/EmployeeController.do?method=toGrid');
//						if("grid"==$("#model").val()){
//							$('#conBody').loadPage($('#initPath').val()+'/EmployeeController.do');
//						}else{
//							employeeTreeForm.tree.deleteItem($("#objId").val(),true);
//							$(".treeRight").empty().loadPage("EmployeeController.do?method=toCreateOrUpdate&isForm=yes");
//						}
						
					},error:function(json){
						alert(json)
					}
			})
		}
		//$('.treeRight').empty();
	});
});