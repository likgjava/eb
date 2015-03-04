<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script type="text/javascript">
var ProjectOthrAttcInfo = {};

ProjectOthrAttcInfo.show = function(attachmentId){
	window.open($('#initPath').val()+"/AttachmentController.do?method=downLoadFile&objId="+attachmentId);
}
</script>
<div>
    <ul style="line-height: 28px;">
    	<c:choose>
    		<c:when test="${fn:length(attachmentList) >0}">
    			<c:forEach items="${attachmentList}" var="attachment">
    				<li>
			      		<a class="filedown" href="#" onclick="javascript:ProjectOthrAttcInfo.show('${attachment.objId}')" title="点击下载" class="abtn"><span >${attachment.viewName}</span></a>
			   		</li>
    			</c:forEach>
    		</c:when>
    		<c:otherwise>
    			<li>无附件</li>
    		</c:otherwise>
    	</c:choose>
	</ul>
</div>
