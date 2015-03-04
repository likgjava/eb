
var taskPlanDetailForm={};

// 申报书明细列表
taskPlanDetailForm.taskPlanSubList = function(taskPlanObjId){
    $.getJSON($("#initPath").val()+"/TaskPlanMSubController.do?method=getObjectQuery&queryColumns=objId,taskPlanSub.objId,taskPlanSub.purchase.objId,taskPlanSub.purchaseName,taskPlanSub.unit,taskPlanSub.quantity,taskPlanSub.totalPrice,taskPlanSub.remark",{"taskPlan.objId":taskPlanObjId},function(json){
    	var list = json.result;
    	if(null != list && '' != list && "null" != list && "[]" != list){
    		var taskPlanSubListContent = "";
    		taskPlanSubListContent += '<tr>';
    		taskPlanSubListContent += '<td colspan="10"><strong>申报书明细</strong></td>';
    		taskPlanSubListContent += '</tr>';
    		
    		$.each(list,function(i,n){
    			taskPlanSubListContent += '<tr>';
    			taskPlanSubListContent += '<td><span style="font-weight: bold">采购品目：</span></td>';
    			taskPlanSubListContent += '<td>'+n["taskPlanSub.purchaseName"]+'</td>';
    			taskPlanSubListContent += '<td><span style="font-weight: bold">计量单位：</span></td>';
    			taskPlanSubListContent += '<td>'+n["taskPlanSub.unit"]+'</td>';
    			taskPlanSubListContent += '<td><span style="font-weight: bold">采购数量：</span></td>';
    			taskPlanSubListContent += '<td>'+n["taskPlanSub.quantity"]+'</td>';
    			taskPlanSubListContent += '<td><span style="font-weight: bold">预算金额：</span></td>';
    			taskPlanSubListContent += '<td>'+n["taskPlanSub.totalPrice"]+'</td>';
    			taskPlanSubListContent += '<td><span style="font-weight: bold">备注：</span></td>';
    			var remark = n["taskPlanSub.remark"];
    			if(remark.length >100){
    				remark = remark.substring(0,100);
    				remark += "...";
    			}
    			taskPlanSubListContent += '<td>'+remark+'</td>';
    			taskPlanSubListContent += '<td><a href="#" id="lookTaskPlanSub" objId="'+n["taskPlanSub.objId"]+'"><span>查看明细</span></a></td>';
				taskPlanSubListContent += '</tr>';
    		})
    		$("#taskPlanItemList").empty().append(taskPlanSubListContent).show();
    		$("#taskPlanItemList").find('a').click(function(){
    			// 查看
    			if(this.id == "lookTaskPlanSub"){
    				var objId = $(this).attr("objId");
    				$.epsDialog({
    			    	title:'申报书条目-查看需求信息',
    			    	url:$('#initPath').val()+'/TaskPlanController.do?method=toLookTaskPlanSubRequireInfoView&objId='+objId,
    			    	width: '800',
    			    	height: '420',
    			    	onClose: function(){ 
    			       }
    				});
    			}
    		})
    	}
    	
    	// 采购资金列表
    	$.getJSON($("#initPath").val()+"/TaskPlanMDetailController.do?method=getObjectQuery&queryColumns=objId,taskPlanDetail.objId,taskPlanDetail.superiorApp,taskPlanDetail.localApp,taskPlanDetail.ownerApp,taskPlanDetail.otherApp,taskPlanDetail.quantity,taskPlanDetail.approvalNumber",{"taskPlan.objId":taskPlanObjId},function(json){
    		var list = json.result;
    		if(null != list && '' != list && "null" != list && "[]" != list){
    			var taskPlanDetailListContent = "";
    			taskPlanDetailListContent += '<tr>';
    			taskPlanDetailListContent += '<td colspan="12"><strong>采购资金明细</strong></td>';
    			taskPlanDetailListContent += '</tr>';
    			
    			$.each(list,function(i,n){
    	    		taskPlanDetailListContent += '<tr>';
    	    		taskPlanDetailListContent += '<td><span style="font-weight: bold">批准文号</span></td>';
    	    		taskPlanDetailListContent += '<td>'+n["taskPlanDetail.approvalNumber"]+'</td>';
    	    		taskPlanDetailListContent += '<td><span style="font-weight: bold">上级拨款(元)</span></td>';
    	    		taskPlanDetailListContent += '<td>'+n["taskPlanDetail.superiorApp"]+'</td>';
    	    		taskPlanDetailListContent += '<td><span style="font-weight: bold">本级财政(元)</span></td>';
    	    		taskPlanDetailListContent += '<td>'+n["taskPlanDetail.localApp"]+'</td>';
    	    		taskPlanDetailListContent += '<td><span style="font-weight: bold">自筹资金(元)</span></td>';
    	    		taskPlanDetailListContent += '<td>'+n["taskPlanDetail.ownerApp"]+'</td>';
    	    		taskPlanDetailListContent += '<td><span style="font-weight: bold">其他资金(元)</span></td>';
    	    		taskPlanDetailListContent += '<td>'+n["taskPlanDetail.otherApp"]+'</td>';
    	    		taskPlanDetailListContent += '<td><span style="font-weight: bold">总资金(元)</span></td>';
    	    		taskPlanDetailListContent += '<td>'+n["taskPlanDetail.quantity"]+'</td>';
    	    		taskPlanDetailListContent += '</tr>';
    			})
    			$("#taskPlanDetailList").empty().append(taskPlanDetailListContent);
    		}
    	})
    })
}

$(document).ready(function(){
	$("#returnUrl").val($("#initPath").val()+"/view/es/planform/taskplan/taskPlanList1.jsp");
	
	var objId = $("#show_detail_id").val();
	taskPlanDetailForm.taskPlanSubList(objId);
	
	// 预览打印
	$("#taskPlanPrintView").click(function(){
		$.getJSON($('#initPath').val()+'/TaskPlanController.do?method=taskPlanPrintView',{"objId":objId}, function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			window.open($("#initPath").val()+"/view/es/common/RequestContentPrint.jsp");
		})
	})
});