/*
 * 执行平台，审核采购申报书
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var taskPlanFormAudit={};

$(document).ready(function(){
	//加载详细信息页面
	$("#taskPlanDetail").loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+$("#taskPlanId").val());
	
	//隐藏任务书条目
	$("#li_details").hide();
	
	//返回
	$('#returnButton').click(function(){
		$('#conBody').empty().loadPage($('#initPath').val()+'/view/es/planform/taskplan/blockTradeListForAudit.jsp');
	});
	
	//通过
	$('#passButton').click(function(){

			$.getJSON($('#initPath').val()+'/BlockTradeController.do?method=blockSubmitTaskPlan&block_check_status=04&objId='+$("#objId").val(), {}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#returnButton').click();
			});

	});
	
	//不通过
	$('#noPassButton').click(function(){
		//操作权限
			if(($("#opinion").val()==null || $("#opinion").val()=="")){
				alert("请填写审核不通过原因!");
				return  false;
			}

			$.getJSON($('#initPath').val()+'/BlockTradeController.do?method=blockSubmitTaskPlan&block_check_status=03&objId='+$("#objId").val(), {}, function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#returnButton').click();
			});
	});
	
});
