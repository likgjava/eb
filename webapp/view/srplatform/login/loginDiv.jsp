<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/login/sha1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/login/loginDiv.js"></script>

<style>
.formLogin{ background:#b0d2f7 url(<%=request.getContextPath()%>/view/srplatform/login/images/formLogin.jpg) no-repeat left top; height:400px;}
.formLogin ul{ padding:100px 10px 10px 330px;}
.formLogin li{ vertical-align:middle; margin:5px 0;}
.formLogin li input{ height:22px;}
.formLogin .conOperation{ padding-left:330px;}
</style>

<form id="loginDivForm">
<div class="formLogin">
	<ul>
		<li><label for="j_username">用户名：</label> 
			<input type="text" name="j_username" value="" id="j_username_div" /> <span></span>
		</li>
		<li><label for="j_password">密&nbsp;&nbsp;&nbsp;码：</label> 
			<input type="password" id="j_password_div" value="" name="j_password" /> <span></span>
		</li>
		<li><label for="j_rand">验证码：</label>
			<input size="12" type="text" id="j_rand_div" value="" name="j_rand" /> <span></span> 
			<img id="randImg_div" title="点击换一张" border="0" align="middle" src="<%=request.getContextPath()%>/RandImageController.do?method=getRandImage"/>
		</li>
		<li><span id="ErrorMsg"></span></li>
	</ul>
    <div class="conOperation">
		<button type="button" id="login_div" class="enterBtn"><span>登录</span></button>
		<button type="button" id="registration_div"><span>注册</span></button>
		<a href="javascript:void(0);" id="getPassWord"><span>忘记密码？</span></a>
	</div>
</div>
</form>