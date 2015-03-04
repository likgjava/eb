$(document).ready(function(){
	
	//确认委托
	$("#sure").click(function(){
		if($("#acceptPlanno").attr("checked")==true){
			if(($("textarea[name='opinion']").val()==null || $("textarea[name='opinion']").val()=="")){
				alert("请填写拒绝接受意见!");
			}else{
				alert("操作成功!");
			}
		}else{
			alert("操作成功!");
		}
	})
	
	//上一步
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/sub_entrust.jsp");
	})
	
	//下一步
	$("#next").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/bidtype_set.jsp");
	})
	
})