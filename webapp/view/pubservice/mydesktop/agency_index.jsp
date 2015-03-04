<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="formTips attention">
	<ul><li><em>代理机构桌面：</em><spring:message code="pubservice.myDesptop.agency"/></li></ul>
</div>
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
	<a  id="user_securitys" href="javascript:void(0);" onclick="$('#conBody').loadPage('view/pubservice/common/user_security_direction.jsp');return false;">请点这里</a>)</li>
</ul>
<div class="notice">
<div class="wrapper clearfix">
<div class="section clearfix"><strong>系统提醒：</strong>
<ul>
	<c:if test="${task.AP==0}"><li>进行中的项目<strong>(0)</strong></li></c:if>
	<c:if test="${task.AP>0}"><li><a title="我参与的项目" href="javascript:void(0);" onclick="('#conBody').loadPage('view/pubservice/bizdata/project/project_agencies_list.jsp');return false;">我参与的项目<strong>(${task.AP})</strong></a></li></c:if>
</ul>
</div>
</div>
</div>
</div>

<div class="importantNote myTask">
<h4><span>我参与的项目</span></h4>
<span class="more">
	<c:if test="${projectList != null && fn:length(projectList) > 0}">
		<a href="javascript:void(0);" onclick="javascript:$('#conBody').loadPage('view/pubservice/bizdata/project/project_agencies_list.jsp');return false;" title="更多项目">更多…</a>
	</c:if>
</span>
<ul>
	<c:choose>
		<c:when test="${projectList != null && fn:length(projectList) > 0}">
		<c:forEach var="projObj" items="${projectList}" varStatus="status">
    	<li>
	        <a href="javascript:void(0);" title="${projObj.tendername}">${projObj.tendername} 
	        	<c:if test="${projObj.src_app != null && projObj.src_app != ''}">[${projObj.src_app}]</c:if>
	        </a>
	        <span class="right"><fmt:formatDate value="${projObj.credate}" pattern="yyyy-MM-dd"/></span>
    	</li>
    	</c:forEach>
		</c:when>
		<c:otherwise><li>对不起,未找到最近我参与的项目!</li></c:otherwise>
	</c:choose>
</ul>
</div>

<script>
$(document).ready(function(){
	//设定返回路径
	$("#returnUrl").val($('#initPath').val()+"/ModelIndexController.do?method=getAgencyIndex");

	//判断机构的状态，提示用户完善信息
	myDesktop.isfinishInfo();
	
	//上传头像
	$("#addPic").click(function(){
		$.epsDialog({
	        title:'上传头像',
	        url:$('#initPath').val()+'/UserController.do?method=toUploadAvatar&userHeadPic='+$('#userHeadPic').val(),
	        width: '600',
	        height: '230'
	    }); 
	})
})
</script>
