<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>


<%@page import="com.gpcsoft.plugin.acegi.AuthenticationHelper"%>
<c:choose>
<c:when test="${canCreate || canJoin }">
<div id="epsTabs">
		<ul>
			<c:if test="${canCreate}">
			<li>
				<a href="#newComunity" id = "tabs_mycomunity" onclick="communityOrgManager.getMyComunity();" class="refreshData"><span>我创建的社区</span></a>
				
			</li>
			</c:if>
			<c:if test="${canJoin}">
			<li>
			
				<a href="#newComunity" id = "tabs_joincomunity" onclick="communityOrgManager.getJoincomunity();" class="refreshData"><span>我参与的社区</span></a>
			</li>
			</c:if>
			
		</ul>
  		<div id="newComunity">
			
		</div>
</div>
</c:when>
<c:otherwise>
<!-- 操作 -->
<div class="formTips attention">
	申请的服务还未审核通过，请联系管理员尽快审核！
</div>
</c:otherwise>
</c:choose>
<script type="text/javascript">

var communityOrgManager = {};

//跳转到我的社区
communityOrgManager.getMyComunity = function(){
	$("#newComunity").loadPage($("#initPath").val()+"/CommunityController.do?method=toCommunityList&orgId=<%=AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()%>"  );
}

//跳转到我参与的社区
communityOrgManager.getJoincomunity = function(){
	$("#newComunity").loadPage($("#initPath").val()+"/CommunityController.do?method=toOrgJoinCommunityList&orgId=<%=AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()%>"  );
}

$(document).ready(function(){

	//点击第一个
	$("#epsTabs").find("a:first").click();
	
	//设置返回url
	$("#returnUrl").val($("#initPath").val()+"/CommunityController.do?method=toOrgComunityList");

	//初始化tabs
	$('#epsTabs').tabs({}); 

});
</script>