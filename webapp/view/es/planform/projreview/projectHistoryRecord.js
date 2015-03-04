var projectHistoryRecord = {};

projectHistoryRecord.operNotice = function(objId){
	if(objId != null && objId != "undefined" && objId != "" && objId != undefined){
		$.epsDialog({
	        title:"通知书信息",
	        id:"noticeInfo",
	        url:$('#initPath').val()+"/NoticeController.do?method=toViewNotice&objId="+objId,
	        width: "580",
	        height: "300",
	        isReload:false,
	        onClose: function(){ 
	        }
		});
	}
}

//采购公告
$("#tabform1").click(function(){
	var ebuyMethod = $("input[name='ebuyMethod']").val();
	if (ebuyMethod=='03') {//询价
		$("#tabform").empty().loadPage($('#initPath').val()+'/InqpbulletinController.do?method=toBulletinByProjectId&bullType=10&projectId='+$("#projectId").val());	
	}else {//公开招标
		$("#tabform").empty().loadPage($('#initPath').val()+'/PurBulletinController.do?method=toViewBulletinByProjectId&projectId='+$("#projectId").val());	
	}
})

//采购文件
$("#tabform2").click(function(){
	var ebuyMethod = $("input[name='ebuyMethod']").val();
	if (ebuyMethod=='03') {//询价
		$("#tabform").empty().loadPage($('#initPath').val()+'/InqpDocController.do?method=toInqpDocDetailView&isComplete=true&projectId='+$("#projectId").val());
	}else{ //除询价之外
		$("#tabform").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocDetailView&isComplete=true&projectId='+$("#projectId").val());
	}
})


//澄清文件
$("#tabform10").click(function(){
	$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/clarificationDoc/clarificationDocListForBuyer.jsp?projectId='+$("#projectId").val());
})

//项目规则
$("#tabform3").click(function(){
	$("#tabform").empty().loadPage($('#initPath').val()+'/ProjProcessRuleController.do?method=toProjProcessRuleBuyer&projectId='+$("#projectId").val());
	
})


//报名信息
$("#tabform4").click(function(){
	$("#tabform").empty().loadPage($('#initPath').val()+'/SignUprecordController.do?method=toSignupPageByBuyer&projectId='+$("#projectId").val());
})

//投标信息
$("#tabform5").click(function(){
	$("#tabform").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=getRestBulletin&projectId='+$("#projectId").val());
})
//结果公告
$("#tabform6").click(function(){
	$("#tabValue").val("6");
	$("#tabform").empty().loadPage($('#initPath').val()+'/ResultBulletinController.do?method=toViewResultBulletinForBuyer&projectId='+$("#projectId").val());
})

//通知书
$("#tabform7").click(function(){
	$("#tabform").empty();
	$.getJSON($('#initPath').val()+'/NoticeController.do?method=toBuyerList',{"projectId":$("#projectId").val()},function(json){
		var tempStrP = "";
		if(json.noData){
			tempStrP += "暂无数据";
		}else{
		 $.each(json.noticeList,function(i,n){
			tempStrP += "<h2 style=\"border-bottom: 1px dotted rgb(204, 204, 204); width: 98%; padding: 5px;\">";
			tempStrP += "<a href='#' onClick='projectHistoryRecord.operNotice(\""+n.objId+"\");'>";
			tempStrP += "【包件名: "+n.project.projName+",包件编号: "+n.project.projCode+",";
			if(n.objId){
				if(n.noticeType=='00'){
					tempStrP += " 通知书信息:<font color=red> "+n.noticeTitle+"</font>, 与投标单位:"+n.receiveOrg.orgName+"—"+n.noticeTypeCn+"】";
				}else{
					tempStrP += " 通知书信息:<font color=gray> "+n.noticeTitle+"</font>, 与投标单位:"+n.receiveOrg.orgName+"—"+n.noticeTypeCn+"】";
				}
			}else{
				tempStrP += ", <font color=gray>尚无合同</font>";
			}
			tempStrP += "</a></h2>";
		 })
		}
		$("#tabform").append(tempStrP);
	});
})

//质疑
$("#tabform8").click(function(){
	$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqBuyerList.jsp?projectId='+$("#projectId").val());
})

//合同
$("#tabform9").click(function(){
	$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractListForBuyer&fromType=fromHistory&projectId='+$("#projectId").val());
})

//邀请函
$("#tabform12").click(function(){
	$("#tabform").empty().loadPage($('#initPath').val()+'/InrqDetailController.do?method=toViewInrqDetailForBuyer&fromProj=yes&projectId='+$("#projectId").val());
})

$("#recordProject").click(function(){
	if(window.confirm("确认要备案吗?")) {
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveRecordProject&projectId='+$('#projectId').val(),{},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			ProjectInfo.projectKeepOnRecord(); 
    	});
	}
})
$("#dRecordProject").click(function(){
	if(window.confirm("确认要撤销备案吗?")) {
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=saveDRecordProject&projectId='+$('#projectId').val(),{},function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			ProjectInfo.projectKeepOnRecord(); 
    	});
	}
})		

$(document).ready(function(){
	$("#projectInfoTabs").tabs();  //tab页
	$("#tabform2").click();        //默认点击第一个
	
})
