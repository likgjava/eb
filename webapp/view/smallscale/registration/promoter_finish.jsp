<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

  <div class="regGuide expert">
    <h3><span>采购大使注册</span></h3>
    <ol>
      <li><span id="step01">阅读服务条款</span></li>
      <li><span  class="next">下一步</span></li>
      <li><span id="step02">填写用户信息</span></li>
      <li><span  class="next">下一步</span></li>
      <li><span id="step03">完成注册</span></li>
    </ol>
  </div>
  <div class="regMain formLayout">
    <h3 class="title"><span>完成注册登记</span></h3>
    <div class="textCenter">
    	<p><em>提交成功！</em></p>
       	<p>欢迎您加入阳光易购采购交易平台！您可以进入&nbsp;<a href="<%=request.getContextPath()%>/ModelIndexController.do?method=toDeskTopIndex" class="icon room">电子采购室&nbsp;</a>并<font color="red">&nbsp;填写自己的推广信息&nbsp;</font>，换取积分。</p>
        <p>请返回首页重新登录。</p>
    </div>
  </div>
  <div class="pageSubmitBtnBox"> <button type="button" id="finishBtn" class="home"><span>返回首页</span></button></div>
  
<script>
$(document).ready(function(){
	$("#finishBtn").click(function(){
		window.location.href=$('#initPath').val()+"/IndexViewController.do?method=index";
	});
})
</script>