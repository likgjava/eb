$(document).ready(function(){
	$("#projectRulesForm").validate();
	$("#submitBtn").click(function(){
		if($("#projectRulesForm").valid()){
			if(window.confirm('确定要保存当前信息？')){
				alert('保存成功！');
			}
		}
	})
	
})