
$(document).ready(function(){
	$("#sure").click(function(){
		if($("#acceptno").attr("checked")==true){
			if(($("textarea[name='agentAcceptRemark']").val()==null || $("textarea[name='agentAcceptRemark']").val()=="")){
				alert("请填写拒绝接受意见!");
				return;
			}else{
				alert("接收成功!");
				return;
			}
		}else{
			alert("接收成功!");
		}
	})
})