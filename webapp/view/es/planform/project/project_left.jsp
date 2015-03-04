<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<ul>
	<!--<li class="" onmouseover="javascript:changeMouseover(this);" onmouseout="javascript:changeMouseout(this);">
		<a href="#" id="menu_agent_assign"	class="icon1" onclick="checkProjectMenu('menu_agent_assign');"><span></span>指定经办人</a>		
	</li>
	<li class="" onmouseover="javascript:changeMouseover(this);" onmouseout="javascript:changeMouseout(this);">
		<a href="#" id="menu_agent_accept"	class="icon1" onclick="checkProjectMenu('menu_agent_accept');"><span></span>经办人接收</a>		
	</li>
	--><li class="" onmouseover="javascript:changeMouseover(this);" onmouseout="javascript:changeMouseout(this);">
		<a href="#" id="menu_bidtype"	class="icon1" onclick="checkProjectMenu('menu_bidtype');"><span></span>设置采购方式</a>		
	</li>
	<li class="" onmouseover="javascript:changeMouseover(this);" onmouseout="javascript:changeMouseout(this);">
		<a href="#" id="menu_project_number"	class="icon1" onclick="checkProjectMenu('menu_project_number');"><span></span>设置招标编号</a>		
	</li>
	<li class="" onmouseover="javascript:changeMouseover(this);" onmouseout="javascript:changeMouseout(this);">
		<a href="#" id="menu_project"	class="icon1" onclick="checkProjectMenu('menu_project');"><span></span>项目<dm:out value="local__package" tenderType="${project.tenderType}">包组</dm:out></a>		
	</li>
	<li class="">
		<a href="#"	id="menu_projProcessRule" class="icon1" onclick="checkProjectMenu('menu_projProcessRule');"><span></span>项目规则</a>
	</li>
	<li class="">
		<a href="#"	id="menu_projProcessRule_aidot" class="icon1" onclick="checkProjectMenu('menu_projProcessRule_aidot');"><span></span>审核项目规则</a>
	</li>
	<li class="">
		<a href="#"	id="menu_purchaseDoc" class="icon1" onclick="checkProjectMenu('menu_purchaseDoc');"><span></span><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></a>
	</li>
	<li class="">
		<a href="#"	id="menu_purBulletin" class="icon1" onclick="checkProjectMenu('menu_purBulletin');"><span></span><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></a>
	</li><!--
	<li class="">
		<a href="#"	id="menu_purBulletinAudit" class="icon1" onclick="checkProjectMenu('menu_purBulletinAudit');"><span></span>审核招标公告</a>
	</li>
	<li class="">
		<a href="#"	id="menu_purBulletinRelease" class="icon1" onclick="checkProjectMenu('menu_purBulletinRelease');"><span></span>发布招标公告</a>
	</li>
	<li class="">
		<a href="#"	id="menu_correctionsBulletin" class="icon1" onclick="checkProjectMenu('menu_correctionsBulletin');"><span></span>变更公告</a>
	</li>
	<li class="">
		<a href="#"	id="menu_correctionsBulletinAudit" class="icon1" onclick="checkProjectMenu('menu_correctionsBulletinAudit');"><span></span>审核更正公告</a>
	</li>
	<li class="">
		<a href="#"	id="menu_correctionsBulletinRelease" class="icon1" onclick="checkProjectMenu('menu_correctionsBulletinRelease');"><span></span>发布变更公告</a>
	</li>
	<li class="">
		<a href="#"	id="menu_suspendBulletinRelease" class="icon1" onclick="checkProjectMenu('menu_suspendBulletin');"><span></span>暂停公告</a>
	</li>
	<li class="">
		<a href="#"	id="menu_suspendBulletinAudit" class="icon1" onclick="checkProjectMenu('menu_suspendBulletinAudit');"><span></span>审核暂停公告</a>
	</li>
	<li class="">
		<a href="#"	id="menu_suspendBulletinRelease" class="icon1" onclick="checkProjectMenu('menu_suspendBulletinRelease');"><span></span>发布暂停公告</a>
	</li>-->
	<li class="">
		<a href="#"	id="menu_signUp" class="icon1" onclick="checkProjectMenu('menu_signUp');"><span></span>投标单位报名</a>
	</li>
	<li class="">
		<a href="#"	id="menu_openbidgroup" class="icon1" onclick="checkProjectMenu('menu_openbidgroup');"><span></span><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out>小组</a>
	</li>
	<li class="">
		<a href="#"	id="menu_bid" class="icon1" onclick="checkProjectMenu('menu_bid');"><span></span><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out></a>
	</li>
	<li class="">
		<a href="#"	id="menu_openbid" class="icon1" onclick="checkProjectMenu('menu_openbid');"><span></span><dm:out value="local__OpenTendering__openBid_zh">开标</dm:out></a>
	</li>
	<li class="">
		<a href="#"	id="menu_meetRoom" class="icon1" onclick="checkProjectMenu('menu_meetRoom');"><span></span>评标室预定</a>
	</li>
	<li class="">
		<a href="#"	id="menu_evalbid" class="icon1" onclick="checkProjectMenu('menu_evalbid');"><span></span>评标</a>
	</li>
	<!-- 
	<li class="">
		<a href="#"	id="menu_resultPublicity" class="icon1" onclick="checkProjectMenu('menu_resultPublicity');"><span></span>结果公示</a>
	</li>
	<li class="">
		<a href="#"	id="menu_resultPublicityAudit" class="icon1" onclick="checkProjectMenu('menu_resultPublicityAudit');"><span></span>审核结果公示</a>
	</li>
	<li class="">
		<a href="#"	id="menu_resultPublicityRelease" class="icon1" onclick="checkProjectMenu('menu_resultPublicityRelease');"><span></span>发布结果公示</a>
	</li>
	<li class="">
		<a href="#"	id="menu_resultBulletin" class="icon1" onclick="checkProjectMenu('menu_resultBulletin');"><span></span>中标公告</a>
	</li>
	<li class="">
		<a href="#"	id="menu_resultBulletinAudit" class="icon1" onclick="checkProjectMenu('menu_resultBulletinAudit');"><span></span>审核中标公告</a>
	</li>
	<li class="">
		<a href="#"	id="menu_resultBulletinAffirm" class="icon1" onclick="checkProjectMenu('menu_resultBulletinAffirm');"><span></span>审批中标公告</a>
	</li>
	<li class="">
		<a href="#"	id="menu_resultBulletinRelease" class="icon1" onclick="checkProjectMenu('menu_resultBulletinRelease');"><span></span>发布中标公告</a>
	</li> -->
	<li class="">
		<a href="#"	id="menu_notice" class="icon1" onclick="checkProjectMenu('menu_notice');"><span></span>结果<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></a>
	</li>
	<li class="">
		<a href="#"	id="menu_oppugnrequisition" class="icon1" onclick="checkProjectMenu('menu_oppugnrequisition');"><span></span>质疑</a>
	</li>
	<li class="">
		<a href="#"	id="" class="icon1" onclick="checkProjectMenu('menu_workgroup');"><span></span>工作小组</a>
	</li>
	<li class="">
		<a href="#"	id="" class="icon1" onclick="checkProjectMenu('menu_projectTime');"><span></span>项目时间设置</a>
	</li><!--
	<li class="">
		<a href="#"	id="" class="icon1" onclick="checkProjectMenu('menu_purchaseDoc_dept_audit');"><span></span>经办部门审核</a>
	</li>
	<li class="">
		<a href="#"	id="" class="icon1" onclick="checkProjectMenu('menu_purchaseDoc_release');"><span></span>招标文件发布</a>
	</li>
	--><li class="">
		<a href="#"	id="" class="icon1" onclick="checkProjectMenu('menu_purchaseDoc_config');"><span></span>确认<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></a>
	</li>
	<li class="">
		<a href="#"	id="" class="icon1" onclick="checkProjectMenu('menu_meetRoom_set');"><span></span>标评室管理</a>
	</li>
	<li class="">
		<a href="#"	id="" class="icon1" onclick="checkProjectMenu('menu_BuyResult_config');"><span></span>确认成交候选人</a>
	</li>
</ul>
<script>
function changeMouseover(obj){
	$(obj).attr("style","backgroundColor:#ff9");
}
function changeMouseout(obj){
	$(obj).attr("style","backgroundColor:transparent");
}
function checkProjectMenu(id){
	if(id == 'menu_assign_dept'){
		//分配执行部门
		$("#projectContent").empty().loadPage($("#initPath").val()+"/ProjectController.do?method=toProjectDepart&projectId="+$("#projectId").val());
	}if(id == 'menu_agent_assign'){
		//指定经办人
		$("#projectContent").empty().loadPage($("#initPath").val()+"/ProjectController.do?method=toProjectLinkGovMan&projectId="+$("#projectId").val());
	}if(id == 'menu_agent_accept'){
		//经办人接收
		$("#projectContent").empty().loadPage($("#initPath").val()+"/view/es/planform/project/agentAccept.jsp?projectId="+$("#projectId").val());
	}if(id == 'menu_bidtype'){
		//设置采购方式
		$("#projectContent").empty().loadPage($("#initPath").val()+"/ProjectController.do?method=toProjectComWork&projectId="+$("#projectId").val());
	}if(id == 'menu_project_number'){
		//设置招标编号
		$("#projectContent").empty().loadPage($("#initPath").val()+"/view/es/planform/project/numberSet.jsp?projectId="+$("#projectId").val());
	}else if(id == 'menu_project'){
		$("#projectContent").empty().loadPage($("#initPath").val()+"/ProjectController.do?method=toProjectCraete&projectId="+$("#projectId").val());
	}else if(id=='menu_projProcessRule'){//alert(id);
		$("#projectContent").empty().loadPage($('#initPath').val()+'/ProjProcessRuleController.do?method=toProjProcessRule&projectId='+$("#projectId").val());
	}else if(id=='menu_purchaseDoc'){
		$("#projectContent").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDoc&projectId='+$("#projectId").val());
	}else if(id=='menu_purBulletin'){
		//招标公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurBulletin&projectId='+$("#projectId").val());
	}else if(id=='menu_purBulletinAudit'){
		//审核招标公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurbulletinAudit&projectId='+$("#projectId").val());
	}else if(id=='menu_purBulletinAffirm'){
		//审批招标公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurbulletinAffirm&projectId='+$("#projectId").val());
	}else if(id=='menu_purBulletinLeaderAffirm'){
		//主管领导审批
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurbulletinLeaderAffirm&projectId='+$("#projectId").val());
	}else if(id=='menu_purBulletinRelease'){
		//发布招标公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toPurbulletinRelease&projectId='+$("#projectId").val());
	}else if(id=='menu_correctionsBulletin'){
		//变更公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toCorrectionsBulletin&projectId='+$("#projectId").val());
	}else if(id=='menu_correctionsBulletinAudit'){
		//审核变更公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toCorrectionsBulletinAudit&projectId='+$("#projectId").val());
	}else if(id=='menu_correctionsBulletinAffirm'){
		//审批变更公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toCorrectionsBulletinAffirm&projectId='+$("#projectId").val());
	}else if(id=='menu_correctionsBulletinRelease'){
		//发布变更公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toCorrectionsBulletinRelease&projectId='+$("#projectId").val());
	}else if(id=='menu_suspendBulletin'){
		//暂停公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toSuspendBulletin&projectId='+$("#projectId").val());
	}else if(id=='menu_suspendBulletinAudit'){
		//审核暂停公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toSuspendBulletinAudit&projectId='+$("#projectId").val());
	}else if(id=='menu_suspendBulletinAffirm'){
		//审批暂停公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toSuspendBulletinAffirm&projectId='+$("#projectId").val());
	}else if(id=='menu_suspendBulletinRelease'){
		//发布暂停公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toSuspendBulletinRelease&projectId='+$("#projectId").val());
	}else if(id=='menu_signUp'){//alert(id);
		$("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/signuprecord/supplierList.jsp?projectId='+$("#projectId").val());
	}else if(id=='menu_openbidgroup'){//alert(id);
		$("#projectContent").empty().loadPage($('#initPath').val()+'/WorkGroupController.do?method=saveOrUpdateOpenBidGroup&projectId='+$("#projectId").val());
	}else if(id=='menu_bid'){
		$("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/bid/bidList.jsp?projectId='+$("#projectId").val());
	}else if(id=='menu_openbid'){
		$("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/projreview/openBidList.jsp?projectId='+$("#projectId").val());
	}else if(id=='menu_meetRoom'){//alert(id);
		$("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/projrule/meetRoomShowView.jsp?projectId='+$("#projectId").val());
	}else if(id=='menu_evalbid'){//alert(id);
	     //评标
		$("#projectContent").empty().loadPage($('#initPath').val()+'/EvaSellerRecordController.do?method=toEvaSellerRecord&projectId='+$("#projectId").val());
	}else if(id=='menu_resultPublicity'){
		//结果公示
		$("#projectContent").empty().loadPage($('#initPath').val()+'/ResultPublicityController.do?method=toResultPublicity&projectId='+$("#projectId").val());
	}else if(id=='menu_resultPublicityAudit'){
		//审核结果公示
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toResultPublicityAudit&projectId='+$("#projectId").val());
	}else if(id=='menu_resultPublicityAffirm'){
		//审批结果公示
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toResultPublicityAudit&projectId='+$("#projectId").val());
	}else if(id=='menu_resultPublicityRelease'){
		//发布结果公示
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toResultPublicityRelease&projectId='+$("#projectId").val());
	}else if(id=='menu_resultBulletin'){//alert(id);
		//中标公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/ResultBulletinController.do?method=getResultBulletin&projectId='+$("#projectId").val());
	}else if(id=='menu_resultBulletinAudit'){//alert(id);
		//审核中标公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toResultBulletinAudit&projectId='+$("#projectId").val());
	}else if(id=='menu_resultBulletinAffirm'){//alert(id);
		//审批中标公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toResultBulletinAffirm&projectId='+$("#projectId").val());
	}else if(id=='menu_resultBulletinRelease'){//alert(id);
		//发布中标公告
		$("#projectContent").empty().loadPage($('#initPath').val()+'/BulletinController.do?method=toResultBulletinRelease&projectId='+$("#projectId").val());
	}else if(id=='menu_notice'){//alert(id);
		//结果通知书
		$("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/noticemanage/projectNotice.jsp?projectId='+$("#projectId").val());
	}else if(id=='menu_oppugnrequisition'){//alert(id);
		//质疑
	$("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/oppugnrequisition/oppugnReqList.jsp?projectId='+$("#projectId").val());
	}else if(id=='menu_workgroup'){//alert(id);
		$("#projectContent").empty().loadPage($('#initPath').val()+'/WorkGroupController.do?method=saveOrUpdateWorkGroup&projectId='+$("#projectId").val());
	}else if(id=='menu_projectTime'){//alert(id);
		$("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/projrule/projectScheduleForm.jsp?projectId='+$("#projectId").val());
	}else if(id=='menu_purchaseDoc_dept_audit'){//alert(id);
        //招标文件经办部门审核
		$("#projectContent").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocDeptAudit&projectId='+$("#projectId").val());
	}else if(id=='menu_purchaseDoc_dept_approve'){//alert(id);
        //招标文件综合部审批
		$("#projectContent").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocDeptAudit&projectId='+$("#projectId").val());
	}else if(id=='menu_purchaseDoc_leader_approve'){//alert(id);
		//招标文件领导审批
	    $("#projectContent").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocDeptAudit&projectId='+$("#projectId").val());
	}else if(id=='menu_purchaseDoc_release'){//alert(id);
        //招标文件发布
	 $("#projectContent").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocRelease&projectId='+$("#projectId").val());
	}
	else if(id=='menu_purchaseDoc_buy_record'){
        //购买招标文件
     $("#projectContent").empty().loadPage($('#initPath').val()+'/DosBuyRecordController.do?method=toPurchaseDocBuyRecord&projectId='+$("#projectId").val());
	}
	else if(id=='menu_purchaseDoc_downLoad'){
        //下载招标文件
     $("#projectContent").empty().loadPage($('#initPath').val()+'/DosBuyRecordController.do?method=toPurchaseDocDownLoad&projectId='+$("#projectId").val());
	}

	else if(id=='menu_purchaseDoc_config'){
        //确认招标文件
     $("#projectContent").empty().loadPage($('#initPath').val()+'/PurchaseDocController.do?method=toPurchaseDocConfig&projectId='+$("#projectId").val());
	}
	else if(id=='menu_consignprotocol'){
        //提交委托协议
     $("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/project/consignprotocolList.jsp?projectId='+$("#projectId").val());
	}
	else if(id=='menu_consignprotocol_config'){
        //确认委托协议
        alert("1");
     $("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/project/consignprotocolListConfig.jsp?projectId='+$("#projectId").val());
	}
	else if(id=='menu_meetRoom_set'){
        //标评室设置
     	$("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/projrule/meetRoomList.jsp');
	}else if(id == 'menu_projProcessRule_aidot'){//项目规则
		$("#projectContent").empty().loadPage($('#initPath').val()+'/ProjProcessRuleController.do?method=toAuditProjProcessRule&projectId='+$("#projectId").val());
	}
	else if(id=='menu_BuyResult_config'){
		//确认成交候选人
	 $("#projectContent").empty().loadPage($('#initPath').val()+'/view/es/planform/buyresult/buyResultList.jsp?projectId='+$("#projectId").val());	
	}
}
</script>