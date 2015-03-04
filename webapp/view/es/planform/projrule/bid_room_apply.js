
 
$(document).ready(function(){
    
	$("#orderRoom").click(function (){
		$("#bidRoomDetail").toggle("slow");
		$("#orderRoom span").toggleClass("collapsable");	
	
	})
	
  
	$("#bidRoomDetail").loadPage($("#initPath").val()+"/view/es/planform/projrule/bid_room_Detail.jsp");
	
	$("#bidRoomAdd").loadPage($("#initPath").val()+"/view/es/planform/projrule/bid_room_add.jsp");
  
  //加载工作记录
	$("#historyDiv").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/workHistory.jsp");
	
	
	//返回上一步
	$("#lastStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/projrule/project_time_set.jsp");
	})
	//进行下一步
	$("#nextStep").click(function(){
		$("#projectContent").loadPage($("#initPath").val()+"/view/es/planform/purchasedoc/purchase_file_made.jsp");
	})
  
  
})