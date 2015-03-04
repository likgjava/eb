<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

  <div class="regGuide security">
    <h3><span>找回密码</span></h3>
	    <ol>
	      <li><span id="step01">填写用户名称</span></li>
	      <li><span  class="next">下一步</span></li>
	      <li><span id="step02">填写密保问题</span></li>
	      <li><span  class="next">下一步</span></li>
	      <li><span id="step03">完成密码找寻</span></li>
	    </ol>
  </div>
  <div class="content" id="userLogin">
   	<h3 class="title"><span>完成密码找寻</span></h3>
   	<div align="center">
      		您的密码已重置成功，重置密码已发到你注册邮箱里了，请注意查收.
    </div>
  </div>
  <div class="pageSubmitBtnBox"> <button type="button" id="finishBtn" class="home"><span>返回首页</span></button></div>

<script>
$(document).ready(function(){
	//密码找回完成
	$("#finishBtn").click(function(){
		window.location.href=$('#initPath').val()+"/IndexViewController.do?method=index";
	});
})
</script>