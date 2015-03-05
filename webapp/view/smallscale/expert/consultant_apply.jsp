<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/register.css" type="text/css" rel="stylesheet" />

<form id="expertInfoApplyForm" method="post">
	<input type="hidden" id="expertId" name="expertId" value="${currentExpertId}"/>
	<input type="hidden" id="objId" name="objId" value="${consultantObjId}"/>
	<input type="hidden" id="auditStatus" name="auditStatus" value="${consultantAuditStatus}"/>

	<c:set var="msg" value="" scope="page"></c:set>
	<c:if test="${consultantAuditStatus == '02'}">
		<c:set var="msg" value="恭喜,您申请的咨询员已经审核通过." scope="page"></c:set>
	</c:if>
	<c:if test="${consultantAuditStatus == '01'}">
		<c:set var="msg" value="您申请的咨询员正在审核中..." scope="page"></c:set>
	</c:if>
	<c:if test="${consultantAuditStatus == '03'}">
		<c:set var="msg" value="您申请的咨询员未能通过审核,请联系管理员或重新申请." scope="page"></c:set>
	</c:if>
		
	<c:if test="${msg != ''}">
		<div class="formTips attention">
			<ul>
				<li>${msg}</li>
			</ul>
		</div>
	</c:if>
		
	<c:if test="${empty consultantAuditStatus || consultantAuditStatus == '03'}">
		<div class="applyCs">
			<ul>
				<li><a href="javascript:void(0);" id="consultantRole" title="咨询员">咨询员</a></li>
			</ul>
		</div>
	</c:if>
</form>

<script>

$(document).ready(function(){
	//申请成为咨询员
	$("#consultantRole").click(function(){
		if(window.confirm('确定要申请成为咨询员?')) {
			var applyExpert = {};
			if($('#auditStatus').val()=='03') { //重新申请
				applyExpert={
						objId:$('#objId').val(),
						expertInfo:{objId:$('#expertId').val()},
						auditStatus:'01'
				}
			}else { //新申请
				applyExpert={
						expertInfo:{objId:$('#expertId').val()},
						auditStatus:'01'
				}
			}
			
			$.getJSON($('#initPath').val()+'/ExpertInfoApplyController.do?method=saveApplyConsultant',{applyExpertStr:JSON.stringify(applyExpert)}, function(json){
				if(json.success=='true'){
					$('#conBody').loadPage($('#initPath').val()+"/ExpertInfoApplyController.do?method=toApplyExpertInfoView&viewName=applyConsultantView&applyType=consultant");
				}else{
					alert('操作失败！');
				}
			})
		}
	})
});
</script>