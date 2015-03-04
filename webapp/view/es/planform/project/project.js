var project = {};
$(document).ready(function(){
	if("agent" == $("#userType").val()){
		$("#contentSub").empty().loadPage($('#initPath').val()+'/view/es/planform/project/project_left.jsp');
		$("#projectContent").empty().loadPage($("#initPath").val()+"/view/es/planform/project/project_agent.jsp");
	}else if("buyer" == $("#userType").val()){
		$("#projectContent").empty().loadPage($("#initPath").val()+"/view/es/planform/project/project_buyer1.jsp");
	}else if("supplier" == $("#userType").val()){
		$("#projectContent").empty().loadPage($("#initPath").val()+"/view/es/planform/project/project_supplier1.jsp");
	}
	
	$("#projectInfoClick").click(function(){
		//根据已有效果判断隐藏还是显示
		//alert("||"+$("#projectInfo").attr("style")+"||");
		if("display: none;"==$("#projectInfo").attr("style")){
			//alert("显示");
			$("#projectInfo").attr("style","display:block");
		}else{
			//alert("隐藏");
			$("#projectInfo").attr("style","display:none");
		}
		//根据已有数据判断是否再去加载数据
		if("" == $("#projectInfo").html()){
			$("#projectInfo").empty().loadPage($("#initPath").val()+"/ProjectController.do?method=toProjectInfo&objId="+$("#projectId").val());
		}
	});
});