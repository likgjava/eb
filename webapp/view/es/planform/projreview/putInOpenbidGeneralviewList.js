var putInOpenbidGeneralviewList={};

$(document).ready(function(){
	$("#openbidGeneralList").tabs();
	
	$("li.ui-state-disabled").each(function(){
		$(this).attr("class","ui-state-default ui-corner-top ui-state-active");
	})
	
	$("li a.refreshData").click(function(){
		putInOpenbidGeneralviewList.currentTabID = $(this).attr("ids");
		if (!putInOpenbidGeneralviewList.currentTabID) {
			putInOpenbidGeneralviewList.currentTabID = this.ids;
		}
		$("#currentTabId").val(putInOpenbidGeneralviewList.currentTabID);
	})
	$("li a.refreshData:first").click();
   
	$("td.genviewDefinetd").each(function(){
		var num = $(this).find("input").length;
		if(num==0){
			$(this).append("<input type='text' value='' class='required'>");
		}
	})
	
	//判断时间是否可以录入开标一览表内容
	var isOpenBid = $("#isOpenBid").val();
	if(isOpenBid != 'YES'){
		$("button").attr("disabled","disabled");
		$("#message").append("<span style='color:red'>开标时间未到，不能录入开标一览表！</span>")
	}
	
	
	
	if($("#vitemSize").val()=='isNO'){ //如果没有录入开标一览表明细数据
		var allGenviewDefinetdValues = '';
	$("td.genviewDefinetd").each(function(){
	  var genviewDefinetdValue = $(this).attr("id")+$(this).find("input").val();
	  allGenviewDefinetdValues += genviewDefinetdValue+',';
	});
	
	var projectId = $("#_project_id").val();
//	$.getJSON($("#initPath").val()+"/OpenBidGeneralVitemController.do?method=saveAllOpenBidGeneralVitemSec",{projectId:projectId ,allValues:allGenviewDefinetdValues},function(json){
//	});
	}
	
	
	  //打印预览
    $('#toPrint').click(function(){
    	var taskPlanId = $("#taskPlanIds").val();
		$.getJSON($('#initPath').val()+'/OpenBidGeneralVitemController.do?method=toOpenBidGeneralVitemPrintPage',{"projectId":$("#projectId").val(),"subProjectId":$("#currentTabId").val()},function(json){
		    if(json.result){
		    	alert(json.result);
		    }else{
		    	 window.open($('#initPath').val()+'/view/es/common/RequestContentPrint.jsp');
		    }
		    if(json.failure)return;
		});	
	});
    
	
	  //打印预览
    $('#toPrintopenBidWord').click(function(){
    	var projId = $("#_project_id").val();
    	window.open($('#initPath').val()+'/BidController.do?method=getOpenBidWord&projId='+projId);
	});
    
    $('#finshSaveButton').click(function(){
//		 alert($('#projectId').val());
		$.getJSON($('#initPath').val()+'/OpenBidGeneralVitemController.do?method=finishOpenBidGeneralVitem&projectId='+$('#projectId').val(),function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			planTemplateTask.clickMethod($("#projectTaskId").val()+"");
			planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
		});
	  });
    
    //打印主持人讲话
    $('#toPrintHostSpeakWord').click(function(){
    	var projId = $("#_project_id").val();
    	window.open($('#initPath').val()+'/BidController.do?method=getHostSpeak&projId='+projId);
	});
	
	
	
	$("#openBidGeneralVitemSave").click(function(){
		if(window.confirm("确定要提交吗？")){
			var allGenviewDefinetdValues = '';
			$("td.genviewDefinetd").each(function(){
			  var genviewDefinetdValue = $(this).attr("id")+$(this).find("input").val();
			  allGenviewDefinetdValues += genviewDefinetdValue+',';
			});
			
			var projectId = $("#_project_id").val();
			$("#openBidGeneralVitemSave").attr("disabled","disabled");
			$.getJSON($("#initPath").val()+"/OpenBidGeneralVitemController.do?method=saveAllOpenBidGeneralVitemSec",{projectId:projectId ,allValues:allGenviewDefinetdValues},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				planTemplateTask.clickMethod($("#projectTaskId").val()+"");
//				planTemplateTask.fulfillPlan($("#projectTaskId").val()+"");
			});
		}
	})
})


