<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html PUBtrC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
    <link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/fps_default/css/main.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/srplatform/login/changePwd.css"/>
	<script type="text/javascript" src='<%=request.getContextPath()%>/view/resource/plug-in/jquery/jquery.js'></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/resource/scripts/jquery/epsValidate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/login/changeUserPwd.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/login/sha1.js"></script>
</head>  
<body id="expert-page">
<input type="hidden" id="initPath" name="initPath" value="<%=request.getContextPath()%>">
<div id="dialogDiv"></div>
<div id="container">

	<!--页头-->
  <div id="pageHead">
    <!--主导航-->
    <div id="navMain" class="dropDownMenu">

    </div>
    <!--查询栏-->
    
  </div>
  <div id="pageBody">
    <!-- 免费注册步骤-->
   <div class="modifyPassword">
      <h3>修改密码</h3>
      <ol>
        <li><span id="step01" class="finish">输入新旧密码</span></li>
        <li><span class="next finish">下一步</span></li>
        <li><span id="step02">保存提交</span></li>
        <li><span class="next">下一步</span></li>
        <li><span id="step03">返回,重新登录</span></li>
      </ol>
      <div id="registerHelpButton"></div>
    </div>
  	<!-- 填写注册信息 -->
  	<div class="errors">您第一次登录系统或者重置了密码，为了账户的安全，请先修改密码，然后重新登录</div>
  	<div class="form-container">
  	<br />
  		<fieldset>
	    <legend>填写注册信息</legend>
    	<form id="changeUserPwdForm" name="changeUserPwdForm">
		<input type="hidden" name="objId" value="<%=session.getAttribute("user.objId") %>">
		<div>
	      <label>旧密码<em>*</em>:</label>
	      <input type="password" id="oldPsw"  class="required" size="40"/>
	      <span class="requisite"> </span>
	    </div>
	    <div class="reg_tip">请输入旧密码</div>
	    
	    <div>
	      <label>新密码<em>*</em>:</label>
	      <input type="password" name="usrPassrowd1" id="usrPassrowd1"  class="required" size="40"/>
	      <span class="requisite"> </span>
	    </div>
	    <div class="reg_tip">请输入新密码</div>
	    
	    <div>
	      <label>新密码确认<em>*</em>:</label>
	      <input type="password" id="usrPassrowd2" name="usrPassrowd2"  class="required" equalTo="#usrPassrowd1" size="40"/>
	      <span class="requisite"> </span>
	    </div>
	    <div class="reg_tip">请再次输入新密码</div>
	</form>
    </fieldset>
    
    <div class="buttonClass">
  		<button type="button" id="save" class="btn"><span>保存</span></button>
  		<button type="button" id="return" class="btn"><span>返回</span></button>
    </div>
</div>
</div>
</div>
<!--主体End-->

    <!--页面内容结束-->

  </body>

</html>