<%@ page pageEncoding="utf-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<% pageContext.setAttribute("gpc", request.getContextPath()); %>

<script type="text/javascript" src='${gpc}/view/srplatform/login/sha1.js'></script>
<style type="text/css">
 a:link {color: #000; text-decoration:none;}     
 a:active{color: #000; text-decoration:none; }     
 a:visited {color: #000; text-decoration:none;}      
 a:hover {color: #000; text-decoration:none;}  
*{ margin:0}
body{ font-size:12px}
ol ul li{ list-style-type:none}
.content{width:98%; height:100%;}
#top,#main,#foot{ overflow:hidden}
.title{ width:650px; height:27px; background-image:url(images/apply_Topbg.png) }
.nav{ width:640px; height:29px; background-color:#f2f7ff; border-top:#c4c4c4 1px solid; border-bottom:#e4e4e4 1px solid; line-height:29px; padding-left:10px}
.red{color:#F00}
.button1{ padding-left:35px; margin-top:2px;}
#main{ width:98%; height:342px; background-image:url(<%=request.getContextPath()%>/view/es/ext/apply/images/apply_bg.gif)}
#login{ width:610px; height:42px; border:#b3b2b2 1px dashed;  padding-left:12px; margin-left:10px; background-color:#b1d9eb; margin-top:8px;}
#login ul { list-style-type:none; padding-top:10px; margin:0; margin-left:-18px; *margin-left:0px;}
#login li{ height:25px; float:left; width:50%}
.mainContent1{
	width:640px;
	height:154px;
	margin:12px 5px 8px 5px;
*width:620px; *margin-left:20px;}
.mainContent1 ul { list-style-type:none; margin-top:10px; overflow:hidden}
.mainContent1 li{ float:left;  width:50%; height:45px; }
.mainContent{ width:605px; margin-left:5px; overflow:hidden; margin-top:8px; padding-left:26px; *padding-left:0px}
.mainContent ul{ list-style-type:none; margin-top:10px; overflow:hidden; margin:0; padding:0; padding-left:15px;}
.mainContent li{ height:35px}
.button2{ width:81px; height:23px; background-image:url(<%=request.getContextPath()%>/view/es/ext/apply/images/apply_button2.png); float:left; margin-right:25px; text-align:center;  line-height:23px}
#zc a:link {color: #F00; text-decoration:none;}     
#zc a:active{color: #F00; text-decoration:none; }     
#zc a:visited {color: #F00; text-decoration:none;}      
#zc a:hover {color: #F00; text-decoration:none;}  
input{ height:13px;}
</style>
<form id="loginForm" action="${gpc }/loginEXT.do" method="post">
<input type="hidden" name="initPath" id="initPath"value="${gpc }"/>
<input type="hidden" name="action" id="action" value="SignUprecordExtController.do"/>
<input type="hidden" name="method" id="method"  value="toIndexPage"/>
<input type="hidden" id="projectId" name="projectId" value="<%=request.getParameter("projectId") %>"/>
<div  id="login">
	<ul >
   		<li><label >用&nbsp;&nbsp;户&nbsp;&nbsp;名：</label><span><input name="j_username" id="j_username" type="text" size="19"/></span><span class="red">&nbsp;*&nbsp;</span><span id="zc"><a href="<%=request.getContextPath() %>/view/pubservice/common/registration.jsp" target="_blank">注册</a></span></li>
		<li><label >密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label><span ><input name="j_password" id="j_password"  type="password" size="20"/></span><span class="red">&nbsp;*&nbsp;</span></li>
	</ul>
</div>
</form>
<div class="mainContent" style="margin-top:5px;*margin-top:10px;">
           <div class="button2"  style="margin-left:280px"><a href="" id="loginButton" >登&nbsp;&nbsp;录</a></div>
       </div>
<script>
$(document).ready(function(){
//普通登录
	$("#loginButton").click(function(){
		/*******************用户名和密码空值验证*************************************/
		  	//初始化项目ID
           $("#projectId").val($("input[id=project.objId]").val());
		if($('#j_username').val().length<1){
			alert('用户名不能为空');
			return false;
		}
		if($('#j_password').val().length<1){
			alert('密码不能为空');
			return false;
		}
		/*******************end 用户名和密码空值验证**********************************/
		$('#j_password').attr('value',hex_sha1($('#j_password').val()));
		$("#loginForm").submit();
		
		return false;
	});
});
</script>
          
