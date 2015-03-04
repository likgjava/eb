<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="infocenter">
<div class="avatar" id="J_Avatar">
<c:choose>
	<c:when test="${userAvatar != null && userAvatar != ''}">
		<img src='<c:url value="AttachmentController.do?method=showImg&objId=${userAvatar}" />'/>
	</c:when>
	 <c:otherwise>
		<img width="80" height="80" src="view/resource/skin/pubservice/img/myDeskPerson.gif">
	 </c:otherwise>
</c:choose>
<input type="hidden" id="userHeadPic" value="${userAvatar}" />
<a href="javascript:void(0);" class="modify" id="addPic">添加头像</a>
</div>
<div class="info"><span class="sstrong">${userName}</span><span class="vip">您好！您是${role}</span>
</div>

<ul class="prompt clearfix">
	<li class="logintips">上次登录:${lastLoginTime}(不是您登录的？
	<a id="user_securitys" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/pubservice/common/user_security_direction.jsp');return false;">请点这里</a>)</li>
</ul>
<div class="notice">
<div class="wrapper clearfix">
<div class="section clearfix"><strong>维护我的：</strong>
<ul>
	<li><a title="基本信息" href="javascript:void(0);" onclick="$('#conBody').loadPage('ExpertInfoController.do?method=toExpertInfoModifyView');return false;">基本信息</a></li>
	<li><a title="教育经历" href="javascript:void(0);" onclick="$('#conBody').loadPage('EducationController.do?method=toEducationList');return false;">教育经历</a></li>
	<li><a title="培训信息" href="javascript:void(0);" onclick="$('#conBody').loadPage('TrainingController.do?method=toTrainingList');return false;">培训信息</a></li>
	<li><a title="任职经历" href="javascript:void(0);" onclick="$('#conBody').loadPage('ExperienceController.do?method=toExperienceList');return false;">任职经历</a></li>
	<li><a title="职称信息" href="javascript:void(0);" onclick="$('#conBody').loadPage('CertificateController.do?method=toCertificateList');return false;">职称信息</a></li>
</ul>
</div>
</div>
</div>
</div>

<script>
$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/ModelIndexController.do?method=getExpertIndex");

	//上传头像
	$("#addPic").click(function(){
		$.epsDialog({
	        title:'上传头像',
	        url:$('#initPath').val()+'/UserController.do?method=toUploadAvatar&userHeadPic='+$('#userHeadPic').val(),
	        width: '560',
	        height: '230'
	    }); 
	})
})
</script>
