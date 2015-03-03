<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<form:form id="OrganizationChangeForm" method="post" modelAttribute="org"> 
	<input type="hidden" name="orgId" id="orgId" value="${org.objId}"/>
	<input type="hidden" name="applyType" id="applyType" value="${applyOrg.applyType}"/>
	<input type="hidden" name="objId" id="objId" value="${applyOrg.objId}"/>
	
	<div class="formLayout form2Pa">
		<ul>
			<li class="fullLine"><label>机构名称：</label>
			<span>${org.orgName}</span>
			</li>
			<li class="fullLine"><label>机构代码：</label>
			<span>${org.orgCode}</span>
			</li>
			<li class="fullLine"><label>状态：</label>
			<span>
				<c:if test="${applyOrg.applyType=='m'}">申请成为厂商</c:if>
				<c:if test="${applyOrg.applyType=='r'}">申请成为服务商</c:if>
				<c:if test="${applyOrg.applyType=='s'}">申请成为供应商</c:if>
				<c:if test="${applyOrg.applyType=='b'}">申请成为采购人</c:if>
				<c:if test="${applyOrg.applyType=='a'}">申请成为代理机构</c:if>
			</span>
			</li>
		</ul>
	</div>
	
	<div class="formLayout form2Pa">
		<ul>
			<li class="formTextarea"><label>审核意见：</label> 
			<textarea name="opinion" maxLength="100" class="required"></textarea>
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
	var msg = status=='02'?"通过":"不通过";
	if(window.confirm('确定审核'+msg+"吗?")) {
	  var jsonObj = formToJsonObject('OrganizationChangeForm');
	  jsonObj.status=status;
	  $.getJSON($('#initPath').val()+'/OrgInfoApplyController.do?method=auditApplyOrg',jsonObj,function(json){
	      if(json.result)alert(json.result);
	      if(json.failure){return;}
	      $('#conBody').loadPage('view/bizplatform/organization/manager/organization_apply_audit_list.jsp');
	  });	
	}
}

$(document).ready(function(){
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
		$('#conBody').loadPage('view/bizplatform/organization/manager/organization_apply_audit_list.jsp');
	})
})
</script>