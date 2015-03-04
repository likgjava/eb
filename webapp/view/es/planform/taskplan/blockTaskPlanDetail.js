/*
 * 执行平台，采购申报书详情
 * author: yangx
 * mail: yangx@gpcsoft.com
 */
var taskPlanDetail={};

//显示委托协议信息
taskPlanDetail.showConsign = function(id){
	$('#conBody').empty().loadPage($('#initPath').val()+'/ConsignController.do?method=toShowView&objId='+id);
}

$(document).ready(function(){	
	//加载tabs
	$('#epsTabs').tabs();
	
    //查询条件过滤
    taskPlanDetail.before=function(){
    	var option={"taskPlan.objId":$("#objId").val()}
    	$('#taskPlanDetailGrid').flexOptions({params:option});
    	$('#taskPlanSubGrid').flexOptions({params:option});
    	return true;
    }
    
    //隐藏发起委托的按钮
    if($("#auditDetail").val() != '02' || $("#confirmStatus").val() != '02' || $("#consigns").text().length > 20){
    	$("#toConsignprotocol").hide();
    }
    
    //隐藏返回按钮
    if($("#taskPlanId").val() && $("#taskPlanId").val() != ''){
    	$("#taskPlanReturn").hide();
    }
	
	//返回
	$('#taskPlanReturn').click(function(){
		if($("#type").val() == "forSum"){
			$('#epsDialogClose').click();
		}else{
			$('#conBody').empty().loadPage($('#initPath').val()+'/view/es/planform/taskplan/blockTradeListForCreate.jsp');
		}
	});

	  //打印预览
    $('#toPrint').click(function(){
    	var taskPlanId = $("#taskPlanIds").val();
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=toBuyerTaskPlanPrintPage&taskPlanId='+taskPlanId,function(json){
		    if(json.result)alert(json.result);if(json.failure)return;
		    window.open($('#initPath').val()+'/view/es/common/RequestContentPrint.jsp');
		});	
	});
	
	
	//发起委托
	$('#toConsignprotocol').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/consign/consignDraft.jsp?taskId='+$("#objId").val());
	});
});