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
					<span title="${project.content}">${project.content}</span>
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
					<span>${projectExceptionApply.adviceProcwayCN}</span>
				</li>
				<li>
					<label class="short">状态：</label>
					<span>${projectExceptionApply.auditStatusCN}</span>
				</li>
				<li>
					<label class="short">是否重新立项：</label>
					<span>
						<c:if test="${projectExceptionApply.reCreateProj=='04'}">是</c:if>
						<c:if test="${projectExceptionApply.reCreateProj=='05'}">否</c:if>
					</span>
				</li>
				<c:if test="${projectExceptionApply.adviceProcway=='01'}">
					<li class="fullLine formTextarea" id="01">
						<label class="short">重新招标原因：</label>
						<span>${projectExceptionApply.reasonDesc}</span>
					</li>
				</c:if>
				
				<c:if test="${projectExceptionApply.adviceProcway=='02'}">
				<li class="fullLine" id="02">
					<label class="short">选择采购方式：</label>
					<span>${projectExceptionApply.newEbuyMethodCN}</span>
				</li>
				<li class="fullLine formTextarea" id="02">
					<label class="short">变更采购方式原因：</label>
					<span>${projectExceptionApply.reasonDesc}</span>
				</li>
				</c:if>
				</ul>
				<div class="conOperation">
						<button id="closeID" type="button" tabindex="20"><span>关闭</span></button>
				</div>
			</form>
	</div>
</div>
        
	 
<script language="javascript">

var projectExceptionForm={};

$(document).ready(function(){
	 if($("#fromType").val()=='fromProj'){
		 $('#closeID').hide();
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


	$('#closeID').click(function(){//关闭
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