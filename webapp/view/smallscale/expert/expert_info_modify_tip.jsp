<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formTips attention">
	<ul>
		<li>
			<c:choose>
				<c:when test="${expertInfo.auditStatus == '00'}">
					您的专家信息尚未提交到管理员审核！
				</c:when>
				<c:when test="${expertInfo.auditStatus == '01'}">
					您的专家信息正在审核中...
				</c:when>
				<c:when test="${expertInfo.auditStatus == '02'}">
					您的专家信息已被审核通过！
				</c:when>
				<c:when test="${expertInfo.auditStatus == '03'}">
					您已申请的专家未审核通过，请修改相应的信息后，再重新提交到管理员审核！
				</c:when>
			</c:choose>
		</li>
		<li>
			维护我的：
			<span class="sysicon siAdd">
				<a href="javascript:ExpertInfoModifyTip.toExpertInfoModifyView('${expertInfo.objId}');"><strong>基本信息</strong></a>
			</span>
		</li>
	</ul>
</div>

<script>
var ExpertInfoModifyTip = {};

ExpertInfoModifyTip.toExpertInfoModifyView=function(expertId){
	$("#conBody").loadPage($("#initPath").val()+'/ExpertInfoController.do?method=toExpertInfoModifyView&expertId='+expertId);
}
</script>