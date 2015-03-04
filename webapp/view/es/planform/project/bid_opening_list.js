var bidOpeningList ={};

var tendersRecord = {};

$(document).ready(function(){
	//加载工作记录
	$("#workHistory").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	$("#projectDetail").click(function(){
		$("#projectDetailContent").toggle("slow");
		$("#projectDetail span").toggleClass("collapsable");
	})
	
	$("#group").click(function(){
		$("#groupContent").toggle("slow");
		$("#group span").toggleClass("collapsable");
	})
	
	$("#tendersRecord").click(function(){
		$("#tendersRecordContent").toggle("slow");
		$("#tendersRecord span").toggleClass("collapsable");
	})
	
	var path=$('#initPath').val();
	$.getJSON(path+'/view/esdemo/data/projectmanager/bid_opening_message.txt',function(json){
		json2Object('projMessageForm',json);
   	});
});