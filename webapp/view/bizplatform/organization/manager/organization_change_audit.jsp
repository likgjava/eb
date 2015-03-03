<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="OrganizationChangeForm" method="post"> 
	<input type="hidden" name="orgId" id="orgId" value="${orgId}"/>
	<c:set var="orgName"></c:set>
	<c:set var="modUser"></c:set>
	<c:set var="modTime"></c:set>
    <div class="formTips light">
		<ul>
		<li class="big"><em><span id="orgNameSpan"></span></em> 申请变更，变更信息如下：</li>
		<c:forEach var="changeOrg" items="${modifyAudit}" varStatus="status1">
			<c:set var="orgName" value="${changeOrg.orgInfo.orgName}"></c:set>
			<c:set var="modUser" value="${changeOrg.createUser.emp.name}"></c:set>
			<c:set var="modTime" value="${changeOrg.createTime}"></c:set>
			<li>
				<c:if test="${changeOrg.modifyType=='orgName'}">
				<strong>机构名称：</strong>
				</c:if>
				<c:if test="${changeOrg.modifyType=='orgCode'}">
				<strong>机构代码：</strong>
				</c:if>
				<c:if test="${changeOrg.modifyType=='bidForRange'}">
				<strong>经营范围：</strong>
				</c:if>
				${changeOrg.oldValue} <font color='red'>变更为 >></font> ${changeOrg.newValueName}
			</li>
		</c:forEach>
		<li class="r">申请变更人：${modUser}</li>
		<li class="r">申请时间：<fmt:formatDate value="${modTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
		</ul>
		<input type="hidden" value="${orgName}" id="orgNameInput"/>
	</div>
	<div class="formLayout form2Pa">
		<ul>
			<li class="formTextarea">
				<label>审核意见：</label> 
				<textarea name="opinion" maxlength="250" class="required"></textarea>
				<span class="eleRequired">*</span>
			</li>
		</ul>
	</div>
	<div class="conOperation">
		<button type="button" id="agree"><span>通过</span></button>
		<button type="button" id="notAgree"><span>不通过</span></button>
		<button type="button" id="return"><span>返回</span></button>
	</div>
</form:form>		

<script>
var OrganizationChangeForm={};

//审核
OrganizationChangeForm.audit=function(status){
	if(!$("#OrganizationChangeForm").valid()){
		alert("请正确填写信息!");return;
	}
	
	var jsonObj = formToJsonObject('OrganizationChangeForm');
	jsonObj.status=status;
	  
	var msg = status=='02'?"通过":"不通过";
	if(window.confirm('确定审核'+msg+"吗?")) {
	  $.getJSON($('#initPath').val()+'/OrgInfoModifyController.do?method=auditModifyOrg',jsonObj,function(json){
	      if(json.result)alert(json.result);
	      if(json.failure){return;}
	      $('#conBody').loadPage($('#initPath').val()+'/OrgInfoModifyController.do?method=toAuditModifyOrgList');
	  });	
	}
}

$(document).ready(function(){
	$("#orgNameSpan").text($("#orgNameInput").val());

	$("#OrganizationChangeForm").validate();
	
	//通过
	$('#agree').click(function(){
		OrganizationChangeForm.audit('02');
	})
	//不通过
	$('#notAgree').click(function(){
		OrganizationChangeForm.audit('03');
	})
	
	//返回
	$('#return').click(function(){
		$('#conBody').loadPage($('#initPath').val()+'/OrgInfoModifyController.do?method=toAuditModifyOrgList');
	})
})
</script>