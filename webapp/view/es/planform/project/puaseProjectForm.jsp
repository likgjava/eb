<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@ page import="com.gpcsoft.epp.common.domain.EbuyMethodEnum"%>
<%@ page import="com.gpcsoft.epp.project.domain.ProjExceptionApplyEnum"%>
<input type="hidden" id="fromType" value="${fromType}">
<div class="partContainers">
	<div class="formLayout form2Pa">  
		<form id="puaseProjectForm" method="post">
			<h5><span>暂停项目处理</span></h5>
				<ul>
				<li>
					<label class="short">招标编号：</label>
					<span>${project.projCode}</span>
				</li>
				<li>
					<label class="short">招标名称：</label>
					<span>${project.projName}</span>
				</li>
				<c:if test="${project.content != null}" >
				<li>
					<label class="short">项目内容：</label>
					<span>${project.content}</span>
				</li>
				</c:if>
				
				<li>
					<label class="short">采购方式：</label>
					<span>${project.ebuyMethodCN}</span>
				</li>
				<li>
					<label class="short">项目负责人：</label>
					<span>${project.manager.name}</span>
				</li>
				<li>
					<label class="short">预算金额：</label>
					<span><fmt:formatNumber value="${project.budgetTotalMoney}" type="currency"/>（元）</span>
				</li>
				<li>
					<label class="short">选择处理方式：</label>
					<select id="adviceProcwayId" name="adviceProcway" onchange="projectExceptionForm.findProcway(this.value);">
						<option value="<%=ProjExceptionApplyEnum.REPURVHASE %>" <c:if test="${projectExceptionApply.adviceProcway=='01'}">selected="selected"</c:if>>--重新招标--</option>
						<option value="<%=ProjExceptionApplyEnum.CHANGEEBUYMETHOD %>" <c:if test="${projectExceptionApply.adviceProcway=='02'}">selected="selected"</c:if>>--变更采购方式--</option>
						<option value="<%=ProjExceptionApplyEnum.STOPPROJECT %>" <c:if test="${projectExceptionApply.adviceProcway=='03'}">selected="selected"</c:if>>--终止项目--</option>
					</select>
				</li>
				<li>
					<label class="short">是否重新立项：</label>
					<input type="radio" name="reCreateProj" value="<%=ProjExceptionApplyEnum.ISRECREATEPROJECT%>" <c:if test="${projectExceptionApply.reCreateProj=='04'}">checked="checked"</c:if>/>是&nbsp;&nbsp;
					<input type="radio" name="reCreateProj" value="<%=ProjExceptionApplyEnum.NORECREATEPROJECT%>" <c:if test="${projectExceptionApply.reCreateProj=='05'}">checked="checked"</c:if> <c:if test="${projectExceptionApply.reCreateProj==null}">checked="checked"</c:if>/>否
				</li>
				<li class="fullLine formTextarea" id="01">
					<label class="short">重新招标原因：</label>
					<textarea rows="" cols="" id="rePurchase"></textarea><span class="eleRequired">*</span>
				</li>
				
				<li class="fullLine" id="02">
					<label class="short">选择采购方式：</label>
					<select name="newEbuyMethod" id="newEbuyMethod" class="required">
						<option value="">--请选择--</option>
						<option value="<%=EbuyMethodEnum.OPEN_BIDDING%>" <c:if test="${projectExceptionApply.newEbuyMethod=='00'}">selected="selected"</c:if>>公开招标</option>
						<option value="<%=EbuyMethodEnum.INVITE_BIDDING%>" <c:if test="${projectExceptionApply.newEbuyMethod=='01'}">selected="selected"</c:if>>邀请招标</option>
						<option value="<%=EbuyMethodEnum.NEGOTIATE%>" <c:if test="${projectExceptionApply.newEbuyMethod=='02'}">selected="selected"</c:if>>竞争性谈判</option>
						<option value="<%=EbuyMethodEnum.SINGLE_SOURCE%>" <c:if test="${projectExceptionApply.newEbuyMethod=='04'}">selected="selected"</c:if>>单一来源</option>
						<option value="<%=EbuyMethodEnum.INQUIRY%>" <c:if test="${projectExceptionApply.newEbuyMethod=='03'}">selected="selected"</c:if>>询价</option>
					</select><span class="eleRequired">*</span>
				</li>
				<li class="fullLine formTextarea" id="02">
					<label class="short">变更采购方式原因：</label>
					<textarea rows="" cols="" id="changeEbuyMethod"></textarea><span class="eleRequired">*</span>
				</li>
				<li class="fullLine formTextarea" id="03">
					<label class="short">终止项目原因：</label>
					<textarea rows="" cols="" id="stopProject"></textarea><span class="eleRequired">*</span>
				</li>
				
				</ul>
				<div class="conOperation">
					<%-- 隐藏数据 --%>
					<input type="hidden" id="objId" name="objId" value="${projectExceptionApply.objId}"/>
					<input type="hidden" id="adviceProcway" value="${projectExceptionApply.adviceProcway}"/>
					<input type="hidden" id="reasonDescId" name="reasonDesc" value="${projectExceptionApply.reasonDesc}"/>
					<input type="hidden" name="project.objId" value="${project.objId}"/>
					<input type="hidden" name="agentyId" value="${project.agencies.objId}"/>
					<input type="hidden" name="agentyName" value="${project.agencies.orgName}"/>
					<input type="hidden" name="formerEbuyMethod" value="${project.ebuyMethod}"/>
					<input type="hidden" name="auditStatus" value="00"/>
					<%-- <button id="saveBtn" type="button" tabindex="18"><span>保存</span></button>--%>
					<button id="submitBtn" type="button" tabindex="18"><span>提交</span></button>
					<button id="ppfHistoryBackBtn" type="button" tabindex="20"><span><spring:message code="globe.return"/></span></button>
				</div>
			</form>
	</div>
</div>
        
	 
<script language="javascript">

var projectExceptionForm={};

$(document).ready(function(){
	if($("#fromType").val()=='fromProj'){
		$("#ppfHistoryBackBtn").hide();
	}

	
	projectExceptionForm.findProcway = function(id){
		$('#adviceProcwayId option').each(function(i,n){
			$('[id='+n.value+']').hide();
			$('[id='+n.value+']').each(function(k,o){
				$(o).find('textarea,select').removeClass('required');
			})
		})
		$('[id='+id+']').show();
		$('[id='+id+']').find('textarea,select').addClass('required');
	}
	var adviceProcway = $('#adviceProcway').val();
	if (adviceProcway==01) {
		$('#rePurchase').val($('#reasonDescId').val());
	} else if (adviceProcway==02) {
		$('#changeEbuyMethod').val($('#reasonDescId').val());
	}  else if (adviceProcway==03) {
		$('#stopProject').val($('#reasonDescId').val());
	}
	if (adviceProcway!=null&&adviceProcway!=''&&adviceProcway!=undefined) {
		projectExceptionForm.findProcway(adviceProcway);
	}else{
		projectExceptionForm.findProcway('01');
	}

	$('#saveBtn').click(function(){//保存
		$('#reasonDescId').val('');
		var adviceProcway= $('#adviceProcwayId').val();
		if (adviceProcway=='01') {
			$('#newEbuyMethod').val('');
			$('#changeEbuyMethod').val('');
			$('#stopProject').val('');
			$('#reasonDescId').val($('#rePurchase').val());
		} else if (adviceProcway=='02') {
			$('#rePurchase').val('');
			$('#stopProject').val('');
			$('#reasonDescId').val($('#changeEbuyMethod').val());
		} else if (adviceProcway=='03') {
			$('#rePurchase').val('');
			$('#newEbuyMethod').val('');
			$('#changeEbuyMethod').val('');
			$('#reasonDescId').val($('#stopProject').val());
		}
		
		var item = $(":radio:checked").length; 
		if (item<1) {
			alert('请选择是否重新立项！');
			return false;
		}
		if($('#puaseProjectForm').valid()){
			$.getJSON($('#initPath').val()+'/ProjectExceptionApplyController.do?method=saveExceptionApply',formToJsonObject('puaseProjectForm'),function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$("#ppfHistoryBackBtn").click();
	    	});
		}
	});
	
	$('#submitBtn').click(function(){//提交
		$('#reasonDescId').val('');
		var adviceProcway= $('#adviceProcwayId').val();
		if (adviceProcway=='01') {
			$('#newEbuyMethod').val('');
			$('#changeEbuyMethod').val('');
			$('#stopProject').val('');
			$('#reasonDescId').val($('#rePurchase').val());
		} else if (adviceProcway=='02') {
			$('#rePurchase').val('');
			$('#stopProject').val('');
			$('#reasonDescId').val($('#changeEbuyMethod').val());
		} else if (adviceProcway=='03') {
			$('#rePurchase').val('');
			$('#newEbuyMethod').val('');
			$('#changeEbuyMethod').val('');
			$('#reasonDescId').val($('#stopProject').val());
		}
		
		var item = $(":radio:checked").length; 
		if (item<1) {
			alert('请选择是否重新立项！');
			return false;
		}
		if($('#puaseProjectForm').valid()){
			$.getJSON($('#initPath').val()+'/ProjectExceptionApplyController.do?method=saveSubmitExceptionApply',formToJsonObject('puaseProjectForm'),function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$("#ppfHistoryBackBtn").click();
	    	});
		}
	});
	
	//返回按钮
	$("#ppfHistoryBackBtn").click(function(){
		if($("#fromType").val()=='fromLeft'){
			$('#conBody').loadPage($('#initPath').val()+'/view/es/planform/project/puaseProjectList.jsp');
		}else if($("#fromType").val()=='fromProj') {
			$("#projectDoDiv").loadPage($('#initPath').val()+'/ProjectController.do?method=toPuaseProject&isComplete=true&fromType=fromProj&projectId='+$("#projectId").val());	
		}else{
			$('#epsDialogClose').click();
			}		
	});
});

</script>