var mydesktop_supervise = {};

	//查看招标公告或询价公告
 	mydesktop_supervise.view=function(rowId,bullType){
 		if('10'==bullType){
 			$('#conBody').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=getBulletinByObjId&objId='+rowId+'&returnUrl=yes');//查看询价公告页面
 		}else{
 			$('#conBody').loadPage($('#initPath').val()+'/PurBulletinController.do?method=getBulletinByObjId&objId='+rowId+'&returnUrl=yes');//查看招标公告页面
 		}
	}
	//到审核公告页面
	mydesktop_supervise.auditBulletin = function(objId,bullType){
	    if('10'==bullType){
	    	$('#conBody').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=getBulletinAuditByObjId&fromDesk=yes&objId='+objId+'&divTarget=&divTargetUrl=');
	    }else{
	    	$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=getBulletinAuditByObjId&fromDesk=yes&objId='+objId+'&divTarget=&divTargetUrl=');
	    }
	}
	//到待审核招标公告页面
	mydesktop_supervise.auditPurchaseBulletin = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/PurBulletinController.do?method=getPurchaseBulletinAuditByObjId&fromDesk=yes&objId='+objId);
	}
	
	//到待审核询价公告页面
	mydesktop_supervise.auditInqpPurchaseBulletin = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=getBulletinAuditByObjId&fromDesk=yes&objId='+objId);
	}
	
	//到待审核结果公告页面
	mydesktop_supervise.auditResultBulletinBulletin = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/ResultBulletinController.do?method=getResultBulletinAuditByObjId&fromDesk=yes&objId='+objId);
	}
	//到待审核结果公示页面
	mydesktop_supervise.getResultPublicity = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/ResultPublicityController.do?method=getResultPublicityAuditByObjId&fromDesk=yes&objId='+objId);
	}
	// 到审核采购文件页面
	mydesktop_supervise.viewPurchaseDoc = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocDeptAudit&fromType=fromDesk&objId='+objId);
	}
	// 到审核询价文件页面
	mydesktop_supervise.viewInqpDocAudit = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/InqpDocController.do?method=toSuperviseAuditPurchaseDoc&objId='+objId);
	}
	// 到审核采购申报书页面
	mydesktop_supervise.auditTaskPlan = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toAuditTaskPlanPage&objId='+objId);
	}
	//到审核下级待提交申报书页面
	mydesktop_supervise.auditTaskPlanForSub = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=showDetail&type=sub&objId='+objId);
	}
	// 根据公告类型获取所有的公告数据(待修改的招标公告)
	mydesktop_supervise.viewBulletin = function(objId,bullType){
		if('10'==bullType){
			$('#conBody').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=getBulletinByBullType&fromDesk=yes&objId='+objId+'&bullType='+bullType);
		}else {
			$('#conBody').loadPage($('#initPath').val()+'/BulletinController.do?method=getBulletinByBullType&fromDesk=yes&objId='+objId+'&bullType='+bullType);
		}
	}
	//待确认的询价文件
	mydesktop_supervise.viewInqpDoc = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/InqpDocController.do?method=toPurchaseDocConfig&objId='+objId);
	}
	//待修改招标公告
	mydesktop_supervise.updatePurchaseBulletin = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/PurBulletinController.do?method=toUpdatePurchaseBulletin&fromDesk=yes&objId='+objId);
	}
	//待修改询价公告
	mydesktop_supervise.updateInqpBulletin = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/InqpbulletinController.do?method=toUpdatePurchaseBulletin&fromDesk=yes&objId='+objId);
	}
	//待修改的结果公告
	mydesktop_supervise.updateResultBulletin = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/ResultBulletinController.do?method=toUpdateResultBulletin&fromDesk=yes&objId='+objId);
	}
	//待修改的结果公示
	mydesktop_supervise.updateResultPublicity = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/ResultPublicityController.do?method=toUpdateResultPublicity&fromDesk=yes&objId='+objId);
	}
	// 获取待修改采购文件
	mydesktop_supervise.NoUpdateviewPurchaseDoc = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toUpdatePurchaseDocPage&objId='+objId+'&fromType=fromDesk');
	}
	// 获取待修改询价文件
	mydesktop_supervise.NoUpdateviewInqpDoc = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/InqpDocController.do?method=toAgentMadePurchaseDoc&objId='+objId+'&fromType=fromDesk');
	}
	//获取抽取招标中心申报书
	mydesktop_supervise.selectAgentTaskplan = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSelectAgentPage&objId='+objId+'&fromType=fromDesk');
	}
	//获取重新抽取招标中心申报书
	mydesktop_supervise.selectAgentAgainTaskplan = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSelectAgentPage&objId='+objId+'&fromType=fromDesk');
	}
	//获取待审核抽取招标中心申报书
	mydesktop_supervise.auditSelectAgentTaskplan = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toAuditTaskPlanSelectForAgentPage&objId='+objId+'&fromType=fromDesk');
	}
	// 获取所有的待审核的申报书
	mydesktop_supervise.getTaskPlan = function(divId){
		var url = 'TaskPlanController.do?method=getMoreForTaskPlanList&confirmStatus=01&auditDetail=02&leader='+PlatForm.user.emp.objId;
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	//抽取招标中心
	mydesktop_supervise.selectAgent = function(divId){
		var url = 'TaskPlanController.do?method=getMoreForTaskPlanList&useStatus=01&confirmStatus=02&auditDetail=02&taskType=00';
		$('#conBody').loadPage($('#initPath').val()+'/'+url);
	}
	//重新抽取招标中心
	mydesktop_supervise.selectAgentAgain = function(divId){
		var url = 'TaskPlanController.do?method=getMoreForTaskPlanList&useStatus=01&confirmStatus=05&auditDetail=02';
		$('#conBody').loadPage($('#initPath').val()+'/'+url);
		
	}
	//审核抽取招标中心
	mydesktop_supervise.auditSelectAgent = function(divId){
		var url = 'TaskPlanController.do?method=getMoreForTaskPlanList&useStatus=01&confirmStatus=07&auditDetail=02';
		$('#conBody').loadPage($('#initPath').val()+'/'+url);
		
	}
	// 获取所有的待提交的申报书
	mydesktop_supervise.getSubmitTaskPlan = function(divId){
		var url = 'TaskPlanController.do?method=getMoreForTaskPlanList&useStatus=00&confirmStatus=00&auditDetail=00&isSubmit=yes';
		$('#conBody').loadPage($('#initPath').val()+'/'+url);
	}
	//获取所有下级待审核的申报书
	mydesktop_supervise.getAuditTaskPlanForSub = function(divId){
		var url = 'view/es/planform/taskplan/moreForTaskPlanListForSub.jsp';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 获取所有的待审核资金的申报书
	mydesktop_supervise.getTaskPlanDetail = function(divId){
	    var url = 'TaskPlanController.do?method=getMoreForTaskPlanList&auditDetail=01&governmentId='+PlatForm.user.emp.department.objId;
    	$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	//待委托的申报书 
	mydesktop_supervise.toTrustTaskPlan = function(divId){
		var url = 'TaskPlanController.do?method=getMoreForTaskPlanList&taskType=00&useStatus=01&confirmStatus=06&taskAgentId='+$("#taskAgentId").val();
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 获取所有的待审核的采购公告
	mydesktop_supervise.getPurBulletinForAudit = function(divId){
		var url = 'BulletinController.do?method=getMoreForBulletinList&auditStatus=00&bullType=01';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 获取所有的待审核的询价公告
	mydesktop_supervise.getInqpPurBulletinForAudit = function(divId){
		var url = 'InqpbulletinController.do?method=getMoreForBulletinList&auditStatus=00&bullType=10';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 获取所有的待审核的中标公告
	mydesktop_supervise.getResultBulletinForAudit = function(divId){
		var url = 'BulletinController.do?method=getMoreForBulletinList&auditStatus=00&bullType=06';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 获取所有的待审核的采购文件
	mydesktop_supervise.getPurDocForAudit = function(divId){
		var url = 'PurchaseDocController.do?method=getMorePurchaseDocRecordList&fileType=07&auditStatus=06';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 获取所有的待审核的询价文件
	mydesktop_supervise.getInqpDocForAudit = function(divId){
		var url = 'InqpDocController.do?method=getMorePurchaseDocRecordList&auditStatus=06';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 获取所有的待审核的询价公告
	mydesktop_supervise.getInqpBulletinForAudit = function(){
		var url = 'InqpbulletinController.do?method=getMoreForBulletinList&auditStatus=00&bullType=10';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 获取所有的待审核的中标公示
	mydesktop_supervise.getResultBulletinPublicityForAudit = function(divId){
		var url = 'BulletinController.do?method=getMoreForBulletinList&auditStatus=00&bullType=05';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 获取退回待修改的申报书
	mydesktop_supervise.getModifyingTaskPlan = function(divId){
		var url = 'TaskPlanController.do?method=getMoreForTaskPlanList&useStatus=01&confirmStatus=04&auditDetail=04';
		$('#conBody').loadPage($('#initPath').val()+'/'+url);
	}
	//跳转到修改任务书页面
	mydesktop_supervise.viewModifyingTaskPlan = function(objId,departmentId,budgetId){
		//需要判断是一级还是二级，
		if(departmentId==budgetId){//一级单位
			$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSaveOrUpdateForSum&objId='+objId+'&paramType=desktop');
		}else{//二级单位
			$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=toSaveOrUpdate&fromDesk=yes&objId='+objId+'&paramType=desktop');
		}
	}
	//获取待确认的采购文件
	mydesktop_supervise.getPurchaseDoc = function(divId){
		var url = 'PurchaseDocController.do?method=getMorePurchaseDocRecordList&fileType=07&auditStatus=00';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	mydesktop_supervise.NoEndviewPurchaseDoc = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocConfig&fromType=fromDesk&objId='+objId);
	}
	//获取待修改的采购文件
	mydesktop_supervise.getNoUpdatePurDOCCount = function(divId){
		var url = 'PurchaseDocController.do?method=getMorePurchaseDocRecordList&fileType=07&auditStatus=04&fromType=desktopList';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 根据公告类型获取所有的公告数据(待修改的询价公告)
	mydesktop_supervise.getInqpBulletin = function(divId,bullType){
		var url = 'InqpbulletinController.do?method=getMoreForBulletinList&auditStatus=02&bullType='+bullType;
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 根据公告类型获取所有的公告数据(待修改的结果公告)
	mydesktop_supervise.getResultBulletin2 = function(divId,bullType){
		var url = 'BulletinController.do?method=getMoreForBulletinList&auditStatus=02&bullType='+bullType;
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 根据公告类型获取所有的公告数据(待修改的结果公示)
	mydesktop_supervise.getWaitUpdatePublicityList = function(divId,bullType){
		var url = 'BulletinController.do?method=getMoreForBulletinList&auditStatus=02&bullType='+bullType;
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	// 获取待审核的投标单位报名信息
	mydesktop_supervise.getSupplierList = function(divId){
		var url = 'SignUprecordController.do?method=getMoreForSignUprecordList&auditStatus=00';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	//审核供应商页面
	mydesktop_supervise.viewSupplierInfo = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/SignUprecordController.do?method=getSupplierForAudit&objId='+objId);
	}
	
	// 指定项目负责人
	mydesktop_supervise.getSetupProjectLeader = function(divId){
		var url = 'view/es/planform/project/agentassignList1.jsp';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	mydesktop_supervise.toSetupProjectLeader = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/TaskPlanController.do?method=getAuthUser&objId='+objId);
	}
	//tab点击事件
	mydesktop_supervise.tabClick = function(id){
		$('#conBody').find("li").removeClass();
		$("#"+id).addClass('selected');
	}
	
	function changeProjectColor() {
		$("a[name=projs]").each(function(i,n){
			var ebuyMethod = $(n).find('span').eq(0).attr('id');
			if(ebuyMethod == '00'){ //如果为公开招标
				$(n).find('span').eq(0).attr("style","color: red;");//公开招标项目
			}else{
				$(n).find('span').eq(0).attr("style","color: #ffa500;");//非公开招标项目
			}
		})
	}
	//获取待确认的询价文件
	mydesktop_supervise.getInqpDoc = function(divId){
		var url = 'InqpDocController.do?method=getMorePurchaseDocRecordList&auditStatus=00';
			$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	//获取待修改的询价文件
	mydesktop_supervise.getNoUpdateInqpDoc = function(divId){
		var url = 'InqpDocController.do?method=getMorePurchaseDocRecordList&auditStatus=04&fromType=desktopList';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	//待确认的合同
	mydesktop_supervise.waitConfigContract = function(divId){
		$(divId).empty();
		var url = '/ContractController.do?method=toContractSupplierListByRole';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	//被退回的合同
	mydesktop_supervise.noPassContract = function(divId){
		$(divId).empty();
		var url = '/ContractController.do?method=toContractSupplierListByRole';
		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	
	//待审核邀请函
	mydesktop_supervise.waitAuditInviterollrequ = function(divId){
		 var url = 'view/es/singlesource/singlesourcedoc/moreInviterollrequList.jsp?auditStatus=01&useStatus=01';
		   $('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	mydesktop_supervise.towaitAuditInviterollrequ= function(projectId){
		$('#conBody').loadPage($('#initPath').val()+'/InviterollrequController.do?method=toInviterollrequAudit&projectId='+projectId);
	}
	//根据公告类型获取所有的招标公告数据
	mydesktop_supervise.getBulletin = function(divId,bullType){
  		var url = '';
		if(bullType=='01'){//招标公告
			 url = 'BulletinController.do?method=getMoreForBulletinList&auditStatus=01&bullType=01';
		}else if(bullType=='10'){//询价公告
			 url = 'InqpbulletinController.do?method=getMoreForBulletinList&auditStatus=01&bullType=10';
    			}else if(bullType=='02'){//待修改的招标公告
    				//edit by shenjz 2011-6-1
    				 url = 'BulletinController.do?method=getMoreForBulletinList&auditStatus=02&bullType=01';
		}	
  		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	
	
	
	//根据公告类型获取所有的更正公告数据
	mydesktop_supervise.getVariationBulletin = function(divId,bullType){
		var url = 'BulletinController.do?method=getMoreForBulletinList&auditStatus=01&bullType=02';
			$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	
	//根据更正公告Id跳转到查看页面
	mydesktop_supervise.viewVariationBulletin = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/VariationBulletinController.do?method=toViewVariationBulletinForSupplier&objId='+objId);
	}
	
	//根据公告类型获取所有的结果公告数据
	mydesktop_supervise.getResultBulletin = function(divId,bullType){
  		var url = 'BulletinController.do?method=getMoreForBulletinList&auditStatus=01&bullType=06';
  		$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	
	//根据结果公告Id跳转到查看页面
	mydesktop_supervise.viewResultBulletin = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/ResultBulletinController.do?method=toViewResultBulletinForSupplier&objId='+objId);
	}
	
	//根据获取待修改的报名信息
	mydesktop_supervise.getUpdateSupplierList = function(divId){
		var url = 'SignUprecordController.do?method=getMoreForSignUprecordList&auditStatus=02';
			$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	
	//根据获取待修改的报名信息ID获得报名信息
	mydesktop_supervise.toViewSignupPage = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/SignUprecordController.do?method=toViewSignupPage&objId='+objId);
	}
	
	//根据公告类型获取审核通过的结果公示
	mydesktop_supervise.getPassResultPublicity = function(divId,bullType){
		var url = 'BulletinController.do?method=getMoreForBulletinList&auditStatus=01&bullType=05';
			$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	
	//根据结果公示Id跳转到查看页面
	mydesktop_supervise.viewResultPublicity = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/ResultPublicityController.do?method=toViewResultPublicityForSupplier&objId='+objId);
	}
	
	
	//得到单一来源邀请函
	mydesktop_supervise.getInrqDetail = function(divId){
		var url = 'view/es/planform/bulletin/moreInrqDetailForSingleSourceList.jsp';
			$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	
	//得到单一来源、邀请招标邀请函
	mydesktop_supervise.toViewInrqDetail = function(projectId){
		$('#conBody').loadPage($('#initPath').val()+'/InrqDetailController.do?method=toViewInrqDetailForSupplier&projectId='+projectId);
	}
	
	
	//得到邀请招标邀请函
	mydesktop_supervise.getinvite = function(divId){
		var url = 'view/es/planform/bulletin/moreInrqDetailForInviteList.jsp';
			$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	
	
	//得到被退回的合同
	mydesktop_supervise.getUpdateContract = function(divId){
		var url = 'ContractController.do?method=toContractSupplierListByRole';
			$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	
	mydesktop_supervise.togetUpdateContract =function(objId,buyerId,supplierId){
		$('#conBody').loadPage($('#initPath').val()+'/ContractController.do?method=toSaveOrUpdate&fromType=fromDesk&supplierId='+supplierId+'&buyerId='+buyerId+'&objId='+objId);
	}
	//待确认的通知书
	mydesktop_supervise.getNotice = function(divId){
		var url = 'view/es/planform/noticemanage/moreNoticeList.jsp';
			$('#conBody').empty().loadPage($('#initPath').val()+'/'+url);
	}
	mydesktop_supervise.getNoticePage = function(objId){
		$('#conBody').loadPage($('#initPath').val()+'/NoticeController.do?method=toReceipt&fromType=fromDesk&objId='+objId);
	}
	$(document).ready(function(){	
		/**更多按钮样式问题**/
		if($.browser.msie) {
			$("#moreSpan").attr("style","float: right;margin-top: -16px");
		}else{
			$("#moreSpan").attr("style","float: right;margin-top: 6px");
		}
		/***********待办任务 硬编码Start************/
		$(".workTask").click(function(){
				var taskType = $(this).attr('taskType');
				// 待审核采购申报书
				if('No_AuditTaskPlan' == taskType){
					mydesktop_supervise.getTaskPlan($(this).attr('href'));
				}
				
				// 待审核资金采购申报书
				if('No_WaitAuditTaskPlanDetail' == taskType){
					mydesktop_supervise.getTaskPlanDetail($(this).attr('href'));
				}
				
				// 待审核招标公告
				if('No_WaitAuditBulletin' == taskType){
					mydesktop_supervise.getPurBulletinForAudit($(this).attr('href'));
				}
				
				// 待审核招标文件
				if('No_WaitAuditPurDOC' == taskType){
					mydesktop_supervise.getPurDocForAudit($(this).attr('href'));
				}
				
				// 待审核中标公告
				if('No_ResultBulletinCount' == taskType){
					mydesktop_supervise.getResultBulletinForAudit($(this).attr('href'));
				}
				
				// 待审核中标公示
				if('No_ResultBulletinPublicity' == taskType){
					mydesktop_supervise.getResultBulletinPublicityForAudit($(this).attr('href'));
				}
				
				// 退回待修改申报书
				if('NO_UpdateBackTaskPlan' == taskType){
					mydesktop_supervise.getModifyingTaskPlan($(this).attr('href'));
				}
				
				// 待确认招标文件
				if('NO_WaitConfPurDOC' == taskType){
					mydesktop_supervise.getPurchaseDoc($(this).attr('href'));
				}
				//待确认询价文件
				if('No_WaitConfInqpDoc' == taskType){
					mydesktop_supervise.getInqpDoc($(this).attr('href'));
				}
				
				// 待修改招标文件
				if('No_WaitAuditPurDOCCount' == taskType){
					mydesktop_supervise.getNoUpdatePurDOCCount($(this).attr('href'));
				}
				
				// 待修改的招标公告
				if('No_UpdatePurBulletin' == taskType){
					//edit by shenjz 2011-6-1
					mydesktop_supervise.getBulletin($(this).attr('href'),'02');
				}
				
				// 待修改的中标公告
				if('No_UpdateResultBulletin' == taskType){
					mydesktop_supervise.getResultBulletin2($(this).attr('href'),'06');
				}
				
				// 待审核的投标单位报名
				if('No_SignUprecordCount' == taskType){
					mydesktop_supervise.getSupplierList($(this).attr('href'));
				}
				// 待修改中标公示
				if('No_WaitUpdateBulletinPublicity' == taskType){
					mydesktop_supervise.getWaitUpdatePublicityList($(this).attr('href'),'05');
				}
				
				//待审核的询价公告
				if('No_WaitAuditInqpBulletin' == taskType){
					mydesktop_supervise.getInqpPurBulletinForAudit($(this).attr('href'),'10');
				}
				
				//待修改的询价文件
				if('No_UpdateInqpDoc' == taskType){
					mydesktop_supervise.getNoUpdateInqpDoc($(this).attr('href'));
				}
				
				//待修改的询价公告
				if('No_UpdateInqpBulletin' == taskType){
					mydesktop_supervise.getInqpBulletin($(this).attr('href'),'10');
				}
				//待提交的采购申报书
				if('No_SubmitTaskPlan' == taskType){
					mydesktop_supervise.getSubmitTaskPlan($(this).attr('href'));
				}
				//下级待审核的采购申报书
				if('N0_AuditTaskPlanForSub' == taskType){
					mydesktop_supervise.getAuditTaskPlanForSub($(this).attr('href'));
				}
				//待抽取招标中心
				if('No_selectAgent' == taskType){
					mydesktop_supervise.selectAgent($(this).attr('href'));
				}
				//重新抽取招标中心
				if('No_selectAgentAgain' == taskType){
					mydesktop_supervise.selectAgentAgain($(this).attr('href'));
				}
				//审核抽取招标中心
				if('No_auditSelectAgent' == taskType){
					mydesktop_supervise.auditSelectAgent($(this).attr('href'));
				}
				//待委托的申报书
				if('No_toTrustTaskPlan' == taskType){
					mydesktop_supervise.toTrustTaskPlan($(this).attr('href'));
				}
				//待确认的合同
				if('No_WaitConfigContract'==taskType){
					mydesktop_supervise.waitConfigContract($(this).attr('href'));
				}
				//被退回的合同
				if('NO_PassContract'==taskType){
					mydesktop_supervise.noPassContract($(this).attr('href'));
				}
				//待审核大宗申报书
				if('No_WaitAuditTaskPlanForTJC'==taskType){
					mydesktop_supervise.waitAuditTaskPlanForTJC($(this).attr('href'));
				}
				//待审核邀请函
				if('No_WaitAuditInviterollrequ'==taskType){
					mydesktop_supervise.waitAuditInviterollrequ($(this).attr('href'));
				}
				
				//审核通过的采购公告
				if('NO_PassBulletin'==taskType){
					mydesktop_supervise.getBulletin($(this).attr('href'),'01');
				}
				
				//审核通过的更正公告
				if('NO_PassChangeBulletin'==taskType){
					mydesktop_supervise.getVariationBulletin($(this).attr('href'),'02');
				}
				
				//审核通过的中标公告
				if('NO_PassResultBulletin'==taskType){
					mydesktop_supervise.getResultBulletin($(this).attr('href'),'06');
				}
				
				// 待修改的报名信息
				if('NO_UpdateSignUprecord'==taskType){
					mydesktop_supervise.getUpdateSupplierList($(this).attr('href'));
				}
				
				//  审核通过的询价公告
				if('NO_PassInqpBulletin'==taskType){
					mydesktop_supervise.getBulletin($(this).attr('href'),'10');
				}
				
				//审核通过的中标公示
				if('NO_PassBulletinPublicity'==taskType){
					mydesktop_supervise.getPassResultPublicity($(this).attr('href'),'05');
				}
				
				//单一采购邀请函
				if('NO_SingleSourceInviterollrequ'==taskType){
					mydesktop_supervise.getInrqDetail($(this).attr('href'));
				}
				
				//邀请招标邀请函
				if('NO_InviteBiddingInviterollrequ'==taskType){
					mydesktop_supervise.getinvite($(this).attr('href'));
				}
				
				//待修改的合同 
				if('NO_UpdateContract'==taskType){
					mydesktop_supervise.getUpdateContract($(this).attr('href'));
				}
				
				//待确认通知书
				if('NO_WaitConfigNotice'==taskType){
					mydesktop_supervise.getNotice($(this).attr('href'));
				}
				
				//待审核询价文件
				if('No_WaitInqpDocCount' == taskType){
					mydesktop_supervise.getInqpDocForAudit($(this).attr('href'));
				}
		})
		
		/***********待办任务 硬编码 End************/
		
		//点击更多项目
		$('#moreProject').click(function(){
			$('#conBody').loadPage($('#initPath').val()+"/ProjectController.do?method=searchProjectListDljg&serviceName="+$('#serviceName').val());
		});
		
		//点击待立项的申报书
		$('#waitCreateProjectId').click(function(){
			$('#conBody').loadPage($('#initPath').val()+"/TaskPlanController.do?method=toTaskPlanListForCreateProj");
		});
		
		//点击待办的子流程
		$('#waitAuditProcessInstance').click(function(){
			$('#conBody').loadPage($('#initPath').val()+'/WorkFlowController.do?method=loadWaitpTaskPage');
		});
		
		//点击填报政府采购登记
		$("#fillZFCGTaskPlan").click(function(){
			$('#conBody').loadPage($('#initPath').val()+"/TaskPlanController.do?method=toCreateTaskPlanWwain");
		})
		//点击填报土地登记
		$("#fillTDTaskPlan").click(function(){
			$('#conBody').loadPage($('#initPath').val()+"/TaskPlanController.do?method=toCreateTaskPlanWwain");
		})
		//点击填报产权交易登记
		$("#fillCQTaskPlan").click(function(){
			$('#conBody').loadPage($('#initPath').val()+"/TaskPlanController.do?method=toCreateTaskPlanWwain");
		})
		//点击填报建筑工程采购登记
		$("#fillJZGCRecordForm").click(function(){
			$('#conBody').loadPage($('#initPath').val()+"/RecordFormController.do?method=toCreateOrUpdate&isComplete=true");
		})
		
	});