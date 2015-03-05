
$(document).ready(function(){
	$("#createCom").click(function(){
		$(".treeRight").loadPage("CompanyController.do?method=toCreateOrUpdate");
	})
	$("#createDep").click(function(){
		$(".treeRight").loadPage("DepartmentController.do?method=toCreateOrUpdate");
	})
	$("#createPost").click(function(){
		$(".treeRight").loadPage("PostController.do?method=toCreateOrUpdate");
	})
})
