<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.gpcsoft.core.utils.DateUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/register.css" />
	
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/login/sha1.js"></script>
</head>
<body>
<!--浏览器检测 开始-->
<%@ include file="/view/srplatform/portal/include/check_browser_ie6.jsp" %>
<!--浏览器检测 结束-->
<div id="container">
	<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<div id="sysContent" class="page">
		<div id="contentMain" class="index1pa" >
    		<div class="regGuide security">
    			<h3><span>找回密码</span></h3>
				<ol>
					<li><span id="step01">填写用户名称</span></li>
      				<li><span  class="next finish">下一步</span></li>
					<li><span id="step02" class="finish">填写密保问题</span></li>
					<li><span  class="next finish">下一步</span></li>
					<li><span id="step03" class="finish">完成密码找寻</span></li>
				</ol>
			</div>
			<form id="userSecurityForm" method="post">
				<div class="content" id="userLogin">
				<h3 class="title"><span>填写用户名称</span></h3>
				<div align="center">
					<label for="orgName">用户名称：</label>
					<input type="text" id="usName" name="usName" maxlength="50" class="required"/>
					<span class="eleRequired">*</span> 
				</div>
				</div>
				<div class="pageSubmitBtnBox">
					<button type="button" id="agreeBtn" class="agree"><span>下一步</span></button>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">
	<%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!--在线客服开始-->
	<%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
	<!--在线客服结束-->
</div>
</body>
</html>

<script>
$(document).ready(function(){
	$('#userSecurityForm').validate();

	//下一步
	$("#agreeBtn").click(function(){
		if(!$('#userSecurityForm').valid()){alert('请正确填写用户名!');return;}
			
		var usName=$("input[name='usName']").val();
		$.getJSON($('#initPath').val()+'/UserSecurityController.do?method=getPassword&step=2&userName='+usName, function(json){
			if(json.result=='success'){
				$('#contentMain').loadPage($('#initPath').val()+'/UserSecurityController.do?method=listQuestions&userId='+json.user.objId);	
			}else if(json.result=='noData'){
				$('#contentMain').loadPage($('#initPath').val()+'/view/pubservice/application/security/security_email_input.jsp?userId='+json.user.objId);	
				return;
			}else{
				alert("无此用户名！请确认后重新填写！");
				return;
			}
		});
	});
		
	//监听回车事件
	$('body').bind("keypress", function(event){
		if (event.keyCode == 13) {								   
			$("#agreeBtn").click();
			return false;
		}
	});
});
</script>
