<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

  <div class="regGuide buyer">
    <h3><span>供应商注册</span></h3>
    <ol>
      <li><span id="step01">阅读服务条款</span></li>
      <li><span  class="next">下一步</span></li>
      <li><span id="step02">选择机构角色</span></li>
      <li><span  class="next">下一步</span></li>
      <li><span id="step03">填写基本信息</span></li>
      <li><span  class="next">下一步</span></li>
      <li><span id="step04">完成注册</span></li>
    </ol>
  </div>
  <div class="regMain formLayout">
    <h3 class="title"><span>完成注册登记</span></h3>
    <div class="textCenter">
    	<em>提交成功，等待审核……</em>
        <p>欢迎您加入阳光易购采购交易平台！您的注册资料已经提交成功！</p>
        <p>请您登录系统并完善自己的资料。</p>
        <p>我们会尽快审核您的信息！审核通过后，您将成为正式会员。</p>
    </div>
  </div>
  <div class="pageSubmitBtnBox"> <button type="button" id="finishBtn" class="home"><span>首页</span></button></div>

<script>
$(document).ready(function(){
	$("#finishBtn").click(function(){
		window.location.href=$('#initPath').val()+"/IndexViewController.do?method=index";
	});
})
</script>