<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<%@page import="com.gpcsoft.plugin.acegi.AuthenticationHelper"%>

<%if(AuthenticationHelper.getCurrentUser()==null){%>
	<div class="userLoginBox">
		<a href="javascript:void(0);" onclick="common.goToLogin();return false;" title="请点击登录" class="userLogin"><span>用户登录</span></a><a href="javascript:void(0);" onclick="common.goToRegistration();return false;" title="点击可免费注册" class="freeRegister"><span>免费注册</span></a>
	</div>
<%}else{%>
	<authz:authorize ifAnyGranted="xejy,ztb">
		<authz:authorize ifAllGranted="xejy,ztb">
			<div class="userLoginBox">
				<a href="javascript:void(0);" onclick="common.goToModelIndex();return false;" class="dzcgsBtn" ><span>电子采购室</span></a><a href="javascript:void(0);" onclick="common.goToModelIndexES();return false;" class="dzzbBtn"><span>电子招标室</span></a>
			</div>
		</authz:authorize>
		<authz:authorize ifNotGranted="ztb">
			<div class="welcome">
				<a href="javascript:void(0);" onclick="common.goToModelIndex();return false;"><span>电子采购室</span></a>
			</div>
		</authz:authorize>
		<authz:authorize ifNotGranted="xejy">
			<div class="welcome">
				<a href="javascript:void(0);" onclick="common.goToModelIndexES();return false;"><span>电子招标室</span></a>
			</div>
		</authz:authorize>
	</authz:authorize>
<%}%>