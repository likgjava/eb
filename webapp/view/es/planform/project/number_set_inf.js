var list={};
//列表查询的结果集
list.rows=null;
//查询条件过滤
list.before=function(){
	return true;
}
//加载数据成功之后调用的函数
list.success=function(){
	 $("#taskDetailGrid").flexGetColByName({
	        'projCode':function(id,t){
		 			$(t).html("<input name='projCode' type='text' value='"+$(t).text()+"'")
		 	}
	 });
}

$(document).ready(function(){
	
	//设置招标编号表单提交
	$("#sure").click(function(){
		var projCode=$("input[name='projCode']").val();
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
				if(json.result)alert(json.result);if(json.failure)return;
			});
		}
	})
	
	//上一步
	$("#pre").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/bidtype_set.jsp");
	})
	
	//下一步
	$("#next").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/project/bid_notice_submit.jsp");
	})
	
})