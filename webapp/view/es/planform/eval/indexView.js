$(document).ready(function(){
	var html = $("#html").val();
	if(html==null||""==html||undefined==html){
		$("#test").append("暂无指标！");
	}else{
		$("#test").append(html);
	}
})