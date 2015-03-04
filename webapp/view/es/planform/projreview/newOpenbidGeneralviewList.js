$(document).ready(function(){
	$("#openbidGeneralList").tabs();
	
	
	$("#openBidGeneralVitemSave").click(function(){
		var allGenviewDefinetdValues = '';
		$("td.genviewDefinetd").each(function(){
		  var genviewDefinetdValue = $(this).attr("id");
		  allGenviewDefinetdValues += genviewDefinetdValue+',';
		});
		
		var projectId = $("#_project_id").val();
		$("#openBidGeneralVitemSave").attr("disabled","disabled");
		$.getJSON($("#initPath").val()+"/OpenBidGeneralVitemController.do?method=saveAllOpenBidGeneralVitem",{projectId:projectId ,allValues:allGenviewDefinetdValues},function(json){
    	  planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		});
		
	})

	
	
})


