var project_buyer = {};

//操作合同
project_buyer.operContract = function(subprojid,contractId,usestatus,bconfirmstatus,sconfirmstatus,buyerId){
	if(contractId != null && contractId != "undefined" && contractId != "" && contractId != undefined){
		if(usestatus=="00" && bconfirmstatus=="00" && sconfirmstatus=="00"){//待提交
			$('#tabform').loadPage($('#initPath').val()+"/ContractController.do?method=toSaveOrUpdate&objId="+contractId+"&subprojectId="+subprojid);
		}
		if(usestatus=="00" && bconfirmstatus=="00" && sconfirmstatus=="01"){//待确认
			$('#tabform').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+contractId+"&type=buyerToSure");
		}
		if(usestatus=="00" && bconfirmstatus=="01" && sconfirmstatus=="00"){//待投标单位确认
			$('#tabform').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+contractId+"&type=buyerToSubmitView");
		}
		if(usestatus=="00" && bconfirmstatus=="00" && sconfirmstatus=="02"){//被退回
			$('#tabform').loadPage($('#initPath').val()+"/ContractController.do?method=toSaveOrUpdate&objId="+contractId+"&subprojectId="+subprojid);
		}
		if(usestatus=="01" && bconfirmstatus=="01" && sconfirmstatus=="01"){//已签订
			$('#tabform').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+contractId+"&type=buyerViewSigned");
		}
		if(usestatus=="01" && bconfirmstatus=="00" && sconfirmstatus=="01"){//待确认作废
			$('#tabform').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+contractId+"&type=buyerToSureCancel");
		}
		if(usestatus=="02" && bconfirmstatus=="01" && sconfirmstatus=="01"){//作废合同
			$('#tabform').loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+contractId+"&type=buyerViewCancel");
		}
	}
}
project_buyer.operNotice = function(objId){
	if(objId != null && objId != "undefined" && objId != "" && objId != undefined){
		$.epsDialog({
	        title:"查看通知书",
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
$(document).ready(function(){
	$('#tabInfo').tabs();
	$("#tabValue").val("1");
	//采购公告
	$("#tabform1").click(function(){
		$("#tabValue").val("1");
		var ebuyMethod = $("input[name='ebuyMethod']").val();
		if (ebuyMethod=='03') {//询价
			$("#tabform").empty().loadPage($('#initPath').val()+'/InqpbulletinController.do?method=toBulletinByProjectId&bullType=10&projectId='+$("#projectId").val());	
		}else {//公开招标
			$("#tabform").empty().loadPage($('#initPath').val()+'/PurBulletinController.do?method=toViewBulletinByProjectId&bullType=01&projectId='+$("#projectId").val());	
		}
	})
	
	//采购文件
	$("#tabform2").click(function(){
		$("#tabValue").val("2");
		var ebuyMethod = $("input[name='ebuyMethod']").val();
//		if(ebuyMethod=='00'){//公开招标
//			$("#tabform").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPruchaseDocPage&fromType=fromList&projectId='+$("#projectId").val());
//		}else if(ebuyMethod=='03'){//询价
//			$("#tabform").empty().loadPage($('#initPath').val()+'/InqpDocController.do?method=toPurchaseDocConfig&projectId='+$("#projectId").val());
//		}else if(ebuyMethod=='04'){//单一来源
//			$("#tabform").empty().loadPage($('#initPath').val()+'/SingleSourceDocController.do?method=toPurchaseDocConfig&projectId='+$("#projectId").val());	
//		}
		if (ebuyMethod=='03') {//询价
			$("#tabform").empty().loadPage($('#initPath').val()+'/InqpDocController.do?method=toPurchaseDocConfig&projectId='+$("#projectId").val());
		}else {//公开招标
			$("#tabform").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocConfig&fromType=fromList&projectId='+$("#projectId").val());
		}
	})
	//澄清文件
	$("#tabform10").click(function(){
		$("#tabValue").val("10");
		$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/clarificationDoc/clarificationDocListForBuyer.jsp?projectId='+$("#projectId").val());
	})
	//项目规则
	$("#tabform3").click(function(){
		$("#tabValue").val("3");
		$("#tabform").empty().loadPage($('#initPath').val()+'/ProjProcessRuleController.do?method=toProjProcessRuleBuyer&projectId='+$("#projectId").val());
		
	})
	
	
	//报名信息
	$("#tabform4").click(function(){
		$("#tabValue").val("4");
		$("#tabform").empty().loadPage($('#initPath').val()+'/SignUprecordController.do?method=toSignupPageByBuyer&projectId='+$("#projectId").val());
//		$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/signuprecord/supplierList.jsp?projectId='+$("#projectId").val());
	})
	
	//投标信息
	$("#tabform5").click(function(){
		$("#tabValue").val("5");
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
		$("#tabValue").val("7");
//		$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/noticemanage/supplierNoticeList.jsp?projectId='+$("#projectId").val());
		$.getJSON($('#initPath').val()+'/NoticeController.do?method=toBuyerList',{"projectId":$("#projectId").val()},function(json){
			var tempStrP = "";
			$.each(json.noticeList,function(i,n){
				tempStrP += "<h2 style=\"border-bottom: 1px dotted rgb(204, 204, 204); width: 98%; padding: 5px;\">";
				tempStrP += "<a href='#' onClick='project_buyer.operNotice(\""+n.objId+"\");'>";
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
    		$("#tabform").append(tempStrP);
		});
	})
	
	//质疑
	$("#tabform8").click(function(){
		$("#tabValue").val("8");
		$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqBuyerList.jsp?projectId='+$("#projectId").val()+'&consBuyerName=00');
	})
	
	//合同
	$("#tabform9").click(function(){
		$("#tabValue").val("9");
		$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractListForBuyer&projectId='+$("#projectId").val());
	})
	$("#tabform2").click();
	//邀请函
	$("#tabform12").click(function(){
		$("#tabValue").val("12");	
		$("#tabform").empty().loadPage($('#initPath').val()+'/InrqDetailController.do?method=toViewInrqDetailForBuyer&fromProj=yes&projectId='+$("#projectId").val());
	})
})