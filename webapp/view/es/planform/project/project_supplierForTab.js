var project_supplier = {};

//操作合同
project_supplier.operContract = function(subprojid,contractId,usestatus,bconfirmstatus,sconfirmstatus,buyerId){
	if(contractId != null && contractId != "undefined" && contractId != "" && contractId != undefined){
		if(usestatus=="00" && bconfirmstatus=="00" && sconfirmstatus=="00"){//待提交
			$('#tabform').empty().loadPage($('#initPath').val()+"/ContractController.do?method=toSaveOrUpdate&objId="+contractId+"&subprojectId="+subprojid+"&supplierId="+PlatForm.userInfo.orgInfo.objId+"&buyerId="+buyerId);
		}
		if(usestatus=="00" && bconfirmstatus=="01" && sconfirmstatus=="00"){//待确认
			$('#tabform').empty().loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+contractId+"&type=supplierToSure");
		}
		if(usestatus=="00" && bconfirmstatus=="00" && sconfirmstatus=="01"){//待采购单位确认
			$('#tabform').empty().loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+contractId+"&type=supplierToSubmitView");
		}
		if(usestatus=="00" && bconfirmstatus=="02" && sconfirmstatus=="00"){//被退回
			$('#tabform').empty().loadPage($('#initPath').val()+"/ContractController.do?method=toSaveOrUpdate&objId="+contractId+"&subprojectId="+subprojid+"&supplierId="+PlatForm.userInfo.orgInfo.objId+"&buyerId="+buyerId);
		}
		if(usestatus=="01" && bconfirmstatus=="01" && sconfirmstatus=="01"){//已签订
			$('#tabform').empty().loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+contractId+"&type=supplierViewSigned");
		}
		if(usestatus=="01" && bconfirmstatus=="01" && sconfirmstatus=="00"){//待确认作废
			$('#tabform').empty().loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+contractId+"&type=supplierToSureCancel");
		}
		if(usestatus=="02" && bconfirmstatus=="01" && sconfirmstatus=="01"){//作废
			$('#tabform').empty().loadPage($('#initPath').val()+"/ContractController.do?method=toContractDetail&objId="+contractId+"&type=supplierViewCancel");
		}
	}else{
		//起草合同
		$('#tabform').empty().loadPage($('#initPath').val()+"/ContractController.do?method=toSaveOrUpdate"+"&subprojectId="+subprojid+"&supplierId="+PlatForm.userInfo.orgInfo.objId+"&buyerId="+buyerId);
	}
}

$(document).ready(function(){
	$('#tabInfo').tabs();
	$("#tabValue").val("1");

	if('03'==$("#ebuyMethod").val()){//如果采购方式为询价采购
		$("#tabform").empty().loadPage($('#initPath').val()+'/InqpbulletinController.do?method=toBulletinByProjectId&bullType=10&projectId='+$("#projectId").val());
	}else if ('01'==$("#ebuyMethod").val()||'04'==$("#ebuyMethod").val()) {//如果采购方式为邀请招标或单一来源
		$("#tabValue").val("12");	
		$("#tabform").empty().loadPage($('#initPath').val()+'/InrqDetailController.do?method=toViewInrqDetailForSupplier&fromProj=yes&projectId='+$("#projectId").val());
	}else{
		$("#tabform").empty().loadPage($('#initPath').val()+'/PurBulletinController.do?method=toBulletinByProjectId&bullType=01&projectId='+$("#projectId").val());
	}
	
	
	$("#tabform5").hide();//屏蔽投标按钮
	
	$("#tabform10").hide();//屏蔽录入谈判结果按钮
	
	
	//采购公告
	$("#tabform1").click(function(){
		$("#tabValue").val("1");
		if('03'==$("#ebuyMethod").val()){
			$("#tabform").empty().loadPage($('#initPath').val()+'/InqpbulletinController.do?method=toBulletinByProjectId&bullType=10&projectId='+$("#projectId").val());
		}else{
			$("#tabform").empty().loadPage($('#initPath').val()+'/PurBulletinController.do?method=toBulletinByProjectId&bullType=01&projectId='+$("#projectId").val());
		}
	})
	
	//采购文件
	$("#tabform2").click(function(){
		$("#tabValue").val("2");
		if('03'==$("#ebuyMethod").val()){
			$("#tabform").empty().loadPage($('#initPath').val()+'/InqpDocController.do?method=toPurchaseDocbuyer&projectId='+$("#projectId").val());
		}else{
			$("#tabform").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocbuyer&projectId='+$("#projectId").val());
		}
	
		
	})
	
	//项目规则
	$("#tabform3").click(function(){
		$("#tabValue").val("3");
		$("#tabform").empty().loadPage($('#initPath').val()+'/ProjProcessRuleController.do?method=toProjProcessRuleBuyer&projectId='+$("#projectId").val());
		
	})
	
	//报名信息
	$("#tabform4").click(function(){
		$("#tabValue").val("4");
		$("#tabform").empty().loadPage($('#initPath').val()+'/SignUprecordController.do?method=toSignupPage&projectId='+$("#projectId").val());
		
	})
	
	//投标信息

	$("#tabform5").click(function(){
		$("#tabValue").val("5");	
		$("#tabform").empty().loadPage($('#initPath').val()+'/BidController.do?method=toBidSupplier&projectId='+$("#projectId").val());
		
	})
	
	//结果公告
	$("#tabform6").click(function(){
		$("#tabValue").val("6");
		$("#tabform").empty().loadPage($('#initPath').val()+'/ResultBulletinController.do?method=toViewResultBulletinForSupplierTab&projectId='+$("#projectId").val());
		
	})
	
	//通知书
	$("#tabform7").click(function(){
		$("#tabValue").val("7");
		$("#tabform").empty().loadPage($('#initPath').val()+'/NoticeController.do?method=toOwnList&projectId='+$("#projectId").val());
		
	})
	
	//质疑
	$("#tabform8").click(function(){
		$("#tabValue").val("8");
		$("#tabform").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqSupplierList.jsp');
	})
	
	//合同
	$("#tabform9").click(function(){
		$("#tabform").empty();
		$("#tabValue").val("9");
		$("#tabform").empty().loadPage($('#initPath').val()+'/ContractController.do?method=toContractList&projectId='+$("#projectId").val());
	})
	//录入谈判结果
	$("#tabform10").click(function(){
		$("#tabValue").val("10");
		$("#tabform").empty().loadPage($('#initPath').val()+'/NegotiateResultController.do?method=toBidSupplier&projectId='+$("#projectId").val());
		
	})
	//更正公告
	$("#tabform11").click(function(){
		$("#tabValue").val("11");	
		$("#tabform").empty().loadPage($('#initPath').val()+'/VariationBulletinController.do?method=toViewVariationBulletinListForSupplier&projectId='+$("#projectId").val());
	})
	//邀请函
	$("#tabform12").click(function(){
		$("#tabValue").val("12");	
		$("#tabform").empty().loadPage($('#initPath').val()+'/InrqDetailController.do?method=toViewInrqDetailForSupplier&fromProj=yes&projectId='+$("#projectId").val());
	})
	
	
	//结果公示
	$("#tabform13").click(function(){
		$("#tabValue").val("13");	
		$("#tabform").empty().loadPage($('#initPath').val()+'/ResultPublicityController.do?method=toViewResultPublicityForSupplierTab&projectId='+$("#projectId").val());
	})
	
});