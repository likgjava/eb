//定义文件全局变量 处理方法名重复问题
var ProcessDefineForm={};	
$(document).ready(function(){
	
})
	
//提交
$("*[name=submit]").click(function(){
	if(!$('#ProcessDefineForm').valid()){return;}
	$.getJSON($('#initPath').val()+'/ProcessDefineController.do?method=save', formToJsonObject('ProcessDefineForm'), function(json){
		if(json.result)alert(json.result);if(json.failure)return;
		$('[name=historyBackBtn]').click();
	});
});
