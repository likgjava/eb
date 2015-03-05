<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<div class="formTips light">
	<ul class="big">
		<li><em>当前状态：</em>
			<c:choose>
				<c:when test="${expertInfo.auditStatus == '01'}">
					您已提交专家申请，请等待审核...
					<br/>
				</c:when>
				<c:when test="${expertInfo.auditStatus == '02'}">
					您已申请成为专家！请完善您的：
					<a title="教育经历" href="javascript:void(0);" onclick="$('#conBody').loadPage('EducationController.do?method=toEducationList');return false;">教育经历</a>
					<a title="培训信息" href="javascript:void(0);" onclick="$('#conBody').loadPage('TrainingController.do?method=toTrainingList');return false;">培训信息</a>
					<a title="任职经历" href="javascript:void(0);" onclick="$('#conBody').loadPage('ExperienceController.do?method=toExperienceList');return false;">任职经历</a>
					<a title="职称信息" href="javascript:void(0);" onclick="$('#conBody').loadPage('CertificateController.do?method=toCertificateList');return false;">职称信息</a>
				</c:when>
				<c:when test="${expertInfo.auditStatus == '03'}">
					您已申请的专家未审核通过，请修改相应的信息后，再重新申请！点此
					<a title="重新申请" href="javascript:void(0);" onclick="$('#conBody').loadPage('ExpertInfoController.do?method=toApplyExpertView&type=reapply');return false;">重新申请</a>
				</c:when>
			</c:choose>
		</li>
	</ul>
</div>