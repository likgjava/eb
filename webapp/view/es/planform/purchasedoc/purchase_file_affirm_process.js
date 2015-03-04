$(document).ready(function(){
	
	$("#submitBtn").click(function(){
		//评审未通过且未填写意见
		if($("#opinion").val()=='' && $("#acceptno").attr("checked")==true)  
		{
			alert("请填写评审意见");
		}
		else if(window.confirm("确认提交?"))
		{
			alert("提交成功");
		}
		
	})
	
})

