var list={};
//列表查询的结果集
list.rows=null;
//查询条件过滤
list.before=function(){
	return true;
}

$(document).ready(function(){
	$.getJSON($('#initPath').val()+'/ProjectController.do?method=createOrUpdate',{objId:$("#objId").val()},function(json){
		if(json.failure)return;
		jsonObjectToForm($("#projectNumberForm")[0],json.project);
	});
	
	//设置招标编号表单提交
	$("#sure").click(function(){
		var projCode=$("#projCode").val();
		projCode=projCode.replace(/\s/g,"");
		var projCodeTest=/^[\u0391-\uFFE5]$/;
		if(projCode==null || projCode==""){
			alert("招标编号不能为空!");
			return;
		}else {
			for(var i=0;i<projCode.length;i++){
				if(projCodeTest.test(projCode[i])){
					alert("招标编号不能含有中文字符!");
					return;
				}
			}
			$.getJSON($('#initPath').val()+'/ProjectController.do?method=updateProjectNumber&projCode='+projCode,formToJsonObject('projectNumberForm'),function(json){
				if(json.failure)return;
				//跳转到招标中心项目首页
				$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfoForAgent&objId='+json.project.objId);
			});
		}
	})
	
})