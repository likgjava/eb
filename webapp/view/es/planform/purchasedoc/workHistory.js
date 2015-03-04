var workHistory = {};

$(document).ready(function(){
	$("#detailsSwitch").click(function(){
		$(".operationLog .logTitle").toggle("slow");
		$(".operationLog .logContent").toggle("slow");
		$("#detailsSwitch").toggleClass("collapsable");
	});
});
