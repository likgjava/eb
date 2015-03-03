<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/register.css" type="text/css" rel="stylesheet" />

<form id="OrgInfoApplyForm" method="post">
	<input type="hidden" id="orgId" name="orgId" value="${currentOrgId}"/>
	<input type="hidden" id="objId" name="objId" value="${serverObjId}"/>
	<input type="hidden" id="auditStatus" name="auditStatus" value="${serverAuditStatus}"/>

<div class="formTips attention">
	<ul>
		<li>
			<em>提示：</em>
			成为服务商后,您可以维护酒店信息(包括客房和会议室信息)。
		</li>
	</ul>
</div>

	<c:set var="msg" value="" scope="page"></c:set>
	<c:if test="${serverAuditStatus == '02'}">
		<c:set var="msg" value="恭喜,您申请的服务商已经审核通过." scope="page"></c:set>
	</c:if>
	<c:if test="${serverAuditStatus == '01'}">
		<c:set var="msg" value="您申请的服务商正在审核中..." scope="page"></c:set>
	</c:if>
	<c:if test="${serverAuditStatus == '03'}">
			<c:set var="msg" value="您申请的服务商未能通过审核,请联系管理员或重新申请." scope="page"></c:set>
		</c:if>

	<c:if test="${msg != ''}">
		<div class="formTips attention">
		<ul>
			<li>
			${msg}
			</li>
		</ul>
		</div>
	</c:if>

	<c:if test="${empty serverAuditStatus || serverAuditStatus == '03'}">
		<div class="applyFws">
			<ul>
			<li><a href="javascript:void(0);" id="serverRole" title="服务商">服务商</a></li>
			</ul>
		</div>
	</c:if>
</form>

<script>

$(document).ready(function(){
	//申请成为服务商
	$("#serverRole").click(function(){
		if(window.confirm('确定要申请成为服务商?')) {
			var applyOrg = {};
			if($('#auditStatus').val()=='03') {
				applyOrg={
						objId:$('#objId').val(),
						orgInfo:{objId:$('#orgId').val()},
						auditStatus:'01'
				}
			}else {
				applyOrg={
						orgInfo:{objId:$('#orgId').val()},
						auditStatus:'01'
				}
			}
			$.getJSON($('#initPath').val()+'/OrgInfoApplyController.do?method=saveApplyServer',{applyOrgStr:JSON.stringify(applyOrg)}, function(json){
				if(json.success=='true'){
					$('#conBody').loadPage($('#initPath').val()+"/OrgInfoApplyController.do?method=toApplyOrgInfo&viewName=toApplyServerView&applyType=r");
				}else{
					alert('操作失败');
				}
			})
		}
	})
})
</script>