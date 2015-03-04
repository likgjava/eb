<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="java.util.List,com.gpcsoft.plugin.acegi.AuthenticationHelper,com.gpcsoft.srplatform.auth.domain.User,com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光易购采购交易平台</title>
</head>
<body>
<noscript>
  <div class="userTips">
    <p>尊敬的用户，您好！您使用的浏览器不支持或禁用JavaScript脚本。请使用支持JavaScript脚本的浏览器或启用浏览器JavaScript脚本功能。</p>
    <p>如有任何疑问或需要帮助，请<a href="http://www.gpcsoft.com" target="_blank" title="珠海政采软件技术有限公司 技术支持">与我们联系</a>。</p>
  </div>
</noscript>
<!--浏览器检测 结束-->
<div id="sysContainer">
	<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
 	<div id="sysContent">
    <!--系统内容-->
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
  
  
  <div class="formTips attention">
	<ul>
		<li>
			<em>注意：</em>密码将重设后发至您注册邮箱，请确认您的注册邮箱安全，谨慎操作！
		</li>
	</ul>
</div>
  
  	<input type="hidden" name="userId" id="userId" value="${param.userId }" />
    <form id="userSecurityForm" method="post">
	    <div class="content" id="userLogin">
	    	<h3 class="title"><span>填写用户邮箱</span></h3>
	    	<div align="center">
		      <label for="orgName">用户邮箱：</label>
		      <input type="text" id="email" name="email" class="required email" maxlength="50" class="required"/>
		       <span class="eleRequired">*</span> 
		    </div>
	    </div>
	  <div class="pageSubmitBtnBox">
	    <button type="button" id="sendEmail" class="agree"><span>找回</span></button>
	  </div>
	</form>
    </div>
  </div>
</div>
</body>
</html>
<script>
	$(document).ready(function(){
		//发送密码到邮箱
		$("#sendEmail").click(function(){
			if(!$('#userSecurityForm').valid()){alert('请正确填写邮箱!');return;}
			$.getJSON($("#initPath").val()+"/UserSecurityController.do?method=checkEmail",{"userId":$("input[name=userId]").val(),"email":$("input[name=email]").val()},function(json){
				if(json.success){
					alert("用户密码已经发到您的邮箱！");
					$("#sendEmail").attr("disable",true);
					//跳转到登陆页面
					common.loginUrl = $('#initPath').val()+"/IndexViewController.do?method=toLogin";
					window.location.href = common.loginUrl;
				}else{
					alert("用户名和邮箱不匹配！");
				}
			})
		})
	});
</script>

