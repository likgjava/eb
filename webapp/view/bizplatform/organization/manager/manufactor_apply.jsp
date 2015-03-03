<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<link href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/register.css" type="text/css" rel="stylesheet" />

<form id="OrgInfoApplyForm" method="post">
		<input type="hidden" id="orgId" name="orgId" value="${currentOrgId}"/>
		<input type="hidden" id="objId" name="objId" value="${manufatorObjId}"/>
		<input type="hidden" id="auditStatus" name="auditStatus" value="${manufatorAuditStatus}"/>

<div class="formTips attention">
	<ul>
		<li>
			<em>提示：</em>
			成为厂商后,您可以品牌信息,以及指定维护商信息。
		</li>
	</ul>
</div>

		<c:set var="msg" value="" scope="page"></c:set>
		<c:if test="${manufatorAuditStatus == '02'}">
			<c:set var="msg" value="恭喜,您申请的厂商已经审核通过." scope="page"></c:set>
		</c:if>
		<c:if test="${manufatorAuditStatus == '01'}">
			<c:set var="msg" value="您申请的厂商正在审核中..." scope="page"></c:set>
		</c:if>
		<c:if test="${manufatorAuditStatus == '03'}">
			<c:set var="msg" value="您申请的厂商未能通过审核,请联系管理员或重新申请." scope="page"></c:set>
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
		
		<c:if test="${empty manufatorAuditStatus || manufatorAuditStatus == '03'}">
			<div class="applyCs">
	        	<ul>
		        <li><a href="javascript:void(0);" id="manufactorRole" title="厂商">厂商</a></li>
		        </ul>
	       	</div>
       	</c:if>
</form>

<script>

$(document).ready(function(){
	//申请成为厂商
	$("#manufactorRole").click(function(){
		if(window.confirm('确定要申请成为厂商?')) {
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
			
			$.getJSON($('#initPath').val()+'/OrgInfoApplyController.do?method=saveApplyManufactor',{applyOrgStr:JSON.stringify(applyOrg)}, function(json){
				if(json.success=='true'){
					$('#conBody').loadPage($('#initPath').val()+"/OrgInfoApplyController.do?method=toApplyOrgInfo&viewName=toApplyManufactorView&applyType=m");
				}else{
					alert('操作失败');
				}
			})
		}
	})
})
</script>