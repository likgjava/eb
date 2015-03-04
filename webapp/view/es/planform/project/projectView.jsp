<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<style type="text/css">
<!--
a.abtn {text-decoration:underline}
--> 
</style>
<div id="printingId" class="frameReport">
<input type="hidden"  id="projectId" value="${param.projectId}">
<input type="hidden" id="openBidSDate" value="${projectRule.openBidSDate}">
<input type="hidden" id="nowTime_1" value="${thisTime}">
<input type="hidden" id="submitStTimeHasBeen" value="${submitStTimeHasBeen}">
<input type="hidden" id="ProjectMonitorId" value="${project.monitor.objId}">
<input type="hidden" id="monitorId" value="${currentUser.emp.objId}">
   	<div class="reportTitle">
     		<h2>${project.projName }</h2>
    		<span class="projectNumber" style="font-size: 15px">招标编号：${project.projCode }</span>
    		<span class="publishTime"  style="font-size: 15px">立项时间：<fmt:formatDate value="${project.createTime }" pattern="yyyy-MM-dd HH:mm"/></span>
    	</div>
	<div class="abstract" style="font-size: 12px">
		<p><span>报名时间：<fmt:formatDate value="${projectRule.signUpSTime }" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${projectRule.signUpETime }" pattern="yyyy-MM-dd HH:mm"/></span>
			<c:set var="isContains" value="0"></c:set>
			<authz:authorize ifAnyGranted="RULE_A">
				<c:set var="isContains" value="1"></c:set>
				<button class="sysicon siModify" id="modify_supplier_bid_time" title="修改投标单位报名时间" projectId="${project.objId}"><span></span></button>
			</authz:authorize>
			<c:if test="${isContains == 0}">
				<authz:authorize ifAnyGranted="RULE_B">
					<c:if test="${v.signUpSTime<thisTime && thisTime<projectRule.signUpETime || '' == projectRule.signUpSTime}">
						<button class="sysicon siModify" id="modify_supplier_bid_time" title="修改投标单位报名时间" projectId="${project.objId}"><span></span></button>
					</c:if>
				</authz:authorize>
			</c:if>
			
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>时间：<fmt:formatDate value="${projectRule.submitStTime }" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${projectRule.submitETime }" pattern="yyyy-MM-dd HH:mm"/></span>
			<c:set var="isContains" value="0"></c:set>
			<authz:authorize ifAnyGranted="RULE_A">
				<c:set var="isContains" value="1"></c:set>
				<button class="sysicon siModify" id="modify_supplier_bid_time" title="修改投标单位投标时间" projectId="${project.objId}"><span></span></button>
			</authz:authorize>
			<c:if test="${isContains == 0}">
				<authz:authorize ifAnyGranted="RULE_B">
					<c:if test="${projectRule.submitStTime<thisTime && thisTime<projectRule.submitETime || '' == project.submitETime}">
						<button class="sysicon siModify" id="modify_supplier_bid_time" title="修改投标单位投标时间" projectId="${project.objId}"><span></span></button>
					</c:if>
				</authz:authorize>
			</c:if>
		</p>
   	
	<p>本项目采购(采取<span class="em highlight">${project.ebuyMethodCN}</span>采购方式)，项目采购公告刊登在“黄山市政府采购网”上，
	报名时间于<span class="em"><fmt:formatDate value="${projectRule.signUpETime }" pattern="yyyy-MM-dd HH:mm"/></span>截止 ，截至${thisTime}，共有<span class="em">${project.projectCountView.signUpC }</span>家公司购买了<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>。
	投标时间于<span class="em"><fmt:formatDate value="${projectRule.submitETime }" pattern="yyyy-MM-dd HH:mm"/></span>截止，截至${thisTime}，共有来自省内外的<span class="em">${project.projectCountView.bidC }</span>家公司参加本项目的投标。
	评审程序是严格按照政府采购相关法律法规来进行的，评审办法及评分细则是依照该项目<dm:out value="local__OpenTendering__purchasedoc_zh">招标文件</dm:out>中相关规定制定的。</p>
	<authz:authorize ifAnyGranted="GK_PROJECT_SIGNUPLIST">
	<p>申报书信息：</p>
	<table style="width: 60%">
    	<tr>
	        <th class="center" width="10%">序号</th>
	        <th class="center">申报书名称</th>
	        <th class="center">申报书明细品目</th>
	        <th class="center">招标单位名称</th>
	        <th class="center" width="10%">操作</th>
      	</tr>
      	<c:set var="count" value="0"></c:set>
      	<c:forEach items="${TaskPlanMSubList}" var="TaskPlanMSub">      
      	<tr>
	        <td class="amount" width="3%"><c:set var="count" value="${count+1}"></c:set>${count}</td>
	        <td class="left" width="10%"><a href="#" class="abtn" onclick="projectView.showDetail('${TaskPlanMSub.taskPlan.objId}')">${TaskPlanMSub.taskPlan.taskName}</a></td>
	        <td class="left" width="10%"><a href="#" class="abtn" onclick="projectView.showPurchaseDetail('${TaskPlanMSub.taskPlanSub.objId}')">${TaskPlanMSub.taskPlanSub.purchaseName}</a></td>
	        <td class="left" width="10%">${TaskPlanMSub.taskPlanSub.budgetName}</td>
	        <td class="center" width="5%"><a href="#" class="abtn" onclick="projectView.showDetail('${TaskPlanMSub.taskPlan.objId}')">查看详情</a></td>
      	</tr>
      	</c:forEach>
	</table>
	</authz:authorize>
	
	
	<authz:authorize ifAnyGranted="GK_PROJECT_SIGNUPLIST">
	<p style="margin-top: 28px">参与报名的投标单位：</p>
	<table style="width: 60%">
    	<tr >
	        <th class="center" width="10%">序号</th>
	        <th class="center" width="40%">公司名称</th>
	        <th class="center" width="20%">报名时间</th>
	        <th class="center" width="10%">联系人</th>
	        <th class="center" width="10%">联系电话</th>
	        <th class="center" width="10%">审核情况</th>
      	</tr>
      	<c:if test="${thisTime>projectRule.openBidSDate}">   
      	<c:set value="0" var="signupListCount" />
      	<c:forEach items="${signupList}" var="signUprecord">      
      	<tr >
	        <td class="amount" width="5%"><c:set value="${signupListCount+1 }" var="signupListCount" />${signupListCount }</td>
	        <td class="left" width="50%">${signUprecord.supplier.orgName}</td>
	        <td class="center" ><fmt:formatDate value="${signUprecord.applyDate}" pattern="yyyy-MM-dd HH:mm"/></td>
	        <td class="left" >${signUprecord.linker}</td>
	        <td class="left" >${signUprecord.linkerTel}</td>
	        <td class="center" >${signUprecord.auditStatusCN}</td>
      	</tr>
      	</c:forEach>
      	</c:if>
	</table>
	</authz:authorize>
	
	
	<authz:authorize ifAnyGranted="GK_PROJECT_BIDLIST">
	<c:forEach items="${bidmodel}" var="bidList">
	<p>参与<span class="em" id="proj_Name">${bidList.key}</span><dm:out value="local__OpenTendering__submitTender_zh">投标</dm:out>的投标单位：</p>
	<table style="width: 90%">
 		<tr>
	        <th class="center" width="10%">序号</th>
	        <th class="center" width="40%">公司名称</th>
	        <th class="center" width="20%">投标时间</th>
	        <th class="center" width="10%">联系人</th>
	        <th class="center" width="20%">证件号</th>
      	</tr>
      	<c:set value="0" var="bidListCount" />
      	<c:forEach items="${bidList.value}" var="bidPackage">
      	<tr>
	        <td class="amount"><c:set value="${bidListCount+1 }" var="bidListCount" />${bidListCount }</td>
	        <td class="left" ><c:if test="${pac =='NO'}"> ${bidPackage.supplierName}</c:if>
	       					  <c:if test="${pac =='YES'}"> ${bidPackage.bid.supplierName}</c:if>
	        </td>
	        <td class="center" ><fmt:formatDate value="${bid.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
	        <td class="left" >${bid.bidLinker}</td>
	        <td class="left" >${bid.bidLinkerIdCard}</td>
      	</tr>
		</c:forEach>
	</table><br/>
	</c:forEach>
    </authz:authorize>
	</div>    
	<div class="operationBtnDiv noprint">
      <button id="printBtn" class="iconBtn" type="button"><span>打印</span></button>
      <authz:authorize ifAnyGranted="puaseProject">
	      <c:choose>
				<c:when test="${project.projImplStatus==00}">
					<button id="projectSave1" type="button" tabindex="15" class="iconBtn"><span>暂停项目</span></button>
					<button id="projectSave2" type="button" tabindex="15" class="iconBtn"><span>终止项目</span></button>
					</c:when>
				<c:when test="${project.projImplStatus==01}">
					<button id="projectSave0" type="button" tabindex="15" class="iconBtn"><span>恢复项目</span></button>
					<button id="projectSave2" type="button" tabindex="15" class="iconBtn"><span>终止项目</span></button>
					<button id="exceptionApp" type="button" tabindex="15" class="iconBtn"><span>异常申请</span></button>
				</c:when>
				<c:when test="${project.projImplStatus==03}">
					<button id="exceptionApp" type="button" tabindex="15" class="iconBtn"><span>异常申请</span></button>
				</c:when>
			</c:choose>
			</authz:authorize>
    </div>
    <c:if test="${project.monitor.objId == currentUser.emp.objId}">
    <div class="conOperation">
      <button id="printSupplierBtn" class="iconBtn" type="button"><span>打印投标投标单位清单</span></button>
   	   <span id="info" style="color: red"></span>
    </div>
    </c:if>
   <c:if test="${project.projImplStatus==02}">
			<span style="color: red; float: right;">该项目已被终止！</span>
		</c:if>
		<c:if test="${project.projImplStatus==01}">
			<span style="color: red; float: right;">该项目已暂停！</span>
		</c:if>
	<c:if test="${sysConfigItemListStringLength!=0}">
		<p style="margin-top: 28px;margin-bottom:10px;" >附件信息：</p>
					 <div id="epsTabs" style="margin-left: 20px; font-size: 14px">
			<ul>
				<c:forEach items="${sysConfigItemListString}" var="sysConfigItem" varStatus="i">
					<li>
				      <a href="#PVUploadAttachment" onclick="javascript:projectView.loadAttachment('${sysConfigItem.objId}');" class="refreshData"><span>${sysConfigItem.name}</span></a>
				    </li>
			    </c:forEach>
		    </ul>
		    <div id="PVUploadAttachment"></div>
		</div>
	</c:if>  
</div>
<script>
var projectView = {};

projectView.showDetail = function(objId){
	$.epsDialog({
    	title:'申报书条目信息',
    	url:$('#initPath').val()+'/TaskPlanController.do?method=showDetail&objId='+objId+'&type=selectAgent',
    	width: '800',
    	height: '600',
    	onClose: function(){ 	
       }
	});	
}

projectView.showPurchaseDetail = function(objId){
	$.epsDialog({
    	title:'申报书条目-查看需求信息',
    	url:$('#initPath').val()+'/TaskPlanController.do?method=toLookTaskPlanSubRequireInfoView&objId='+objId,
    	width: '800',
    	height: '420',
    	onClose: function(){ 	
       }
	});	
}

//加载附件
projectView.loadAttachment = function(itemId){
	//附件
	$('#PVUploadAttachment').loadPage($('#initPath').val()+'/view/srplatform/upload/extAttachmentAjaxFile.jsp',{
		defineSelf:'attachRelaId',//存放关联id的属性名
		attBizId:$("#projectId").val(),
		attBizType:"PROJECT",
		itemId:itemId,
		readOnly:'no'
	});
}

$(document).ready(function(){
	$("span[id=proj_Name]").each(function(){
     $(this).text(($(this).text().substring(33))) ;
	})

	$("#epsTabs").tabs();
    /*加载附件信息部分第一个TabPanel的数据*/
	$('#epsTabs').find("li").each(function(i,n){
			$(this).find(">a").click();
			return false;
	});
	 if($("#submitStTimeHasBeen").val() == "false"){
		$("#printSupplierBtn").attr("disabled","disabled");
		$("#info").text("交纳投标保证金截止时间前，不能打印投标投标单位清单。");
	}
	 
	// 修改项目时间
	$('[id=modify_supplier_bid_time]').click(function(){
		$.epsDialog({
	        title:'更新项目时间',
	        url:$('#initPath').val()+'/ProjectController.do?method=toUpdateProjectTime&projectId='+$(this).attr("projectId"),
	        width: '700',
	        height: '150',
	        isReload:false,
	        onClose: function(){ 
	        	$('#projectDoDiv').loadPage($('#initPath').val()+"/ProjectViewController.do?method=toProjectView&projectId="+$("#projectId").val());
	        }
		});	
	})
	//暂停项目
	$('#projectSave1').click(function(){
		var projectId= $("#projectId").val();
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=updateProjImplStatus&projectId='+projectId+'&projImplStatus=01',function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&objId='+json.project.objId);
		});
	});
	// 恢复项目
	$('#projectSave0').click(function(){
		var projectId= $("#projectId").val();
		$.getJSON($('#initPath').val()+'/ProjectController.do?method=updateProjImplStatus&projectId='+projectId+'&projImplStatus=00',function(json){
			if(json.result)alert(json.result);if(json.failure)return;
			$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&objId='+json.project.objId);
		});
	});
	// 终止项目
	$('#projectSave2').click(function(){
		if (window.confirm("确认终止该项目？终止后该项目只能查看进度！")) {
			var projectId= $("#projectId").val();
			$.getJSON($('#initPath').val()+'/ProjectController.do?method=updateProjImplStatus&projectId='+projectId+'&projImplStatus=02',function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$('#conBody').empty().loadPage($('#initPath').val()+'/ProjectController.do?method=toProjectInfo&objId='+json.project.objId);
			});
		}
	});
	// 异常申请
	$('#exceptionApp').click(function(){
		var projectId= $("#projectId").val();
		$.epsDialog({
	    	title:'异常申请',
	    	url:$('#initPath').val()+'/ProjectController.do?method=toPuaseProject&isComplete=true&fromType=fromDilog&projectId='+projectId,
	    	width: '800',
	    	height: '450',
	    	onClose: function(){ 	
	    		$('#epsDialogCloseNoReload').click();
	       }
		});	
	});
	// 打印预览
	$('#printBtn').click(function(){
		var projectId= $("#projectId").val();
		window.open($('#initPath').val()+'/ProjectViewController.do?method=printProjectView&projectId='+projectId);
	})
	//打印投标投标单位清单
	$('#printSupplierBtn').click(function(){
		var projectId= $("#projectId").val();
		window.open($('#initPath').val()+'/ProjectController.do?method=printProjectView&projectId='+projectId);
		});
})
</script>