<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formLayout">        
	<input type="hidden" value="" id="projectId" name="projectId">
	<h5><span>招标文件</span></h5>
	<ul>
		<li>
			<label class="short">招标文件下载：</label>
			<c:choose>
				<c:when test="${signUpAuditPassed}">
					<a title="点击下载【${attachmentName}】" id="downLoad2" href="<%=request.getContextPath()%>/PurchaseDocController.do?method=downLoadDocFile&purchaseDocId=${purchaseDoc.objId}" class="sysicon siDownBtn"><span>${attachmentName}</span></a>
				</c:when>
				<c:otherwise>
					<span>对不起，您的报名信息还未审核通过，暂不能下载招标文件！</span>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
	
	<div class="conOperation">
		<button id="closeDownloadDocBut" type="button"><span><spring:message code="globe.close"/></span></button>
	</div>
</div>

<script>
$(document).ready(function(){
	//关闭弹出层
	$("#closeDownloadDocBut").click(function(){
		$('.epsDialogClose').trigger('click');
	});
});
</script>