$(document).ready(function(){
	//将对应的数据勾选上
	$("#ebuyMethod_"+$("#old_ebuyMethod").val()).attr("checked","checked"); 
	//设置采购方式表单提交
	$("#sure").click(function(){
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=addProjectComWork',formToJsonObject('bidtypeForm'),function(json){
			if(json.failure)return;
			if(json.result){
				$('#epsDialogClose').click();
			}
			
		});
	})
	//关闭
	$('#closeButton').click(function(){
		$('#epsDialogClose').click();
	});
})