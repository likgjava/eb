<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

  <div class="regGuide expert">
    <h3><span>采购人注册</span></h3>
    <ol>
      <li><span id="step01">阅读注册条款</span></li>
      <li><span  class="next">下一步</span></li>
      <li><span id="step02">填写用户信息</span></li>
      <li><span  class="next">下一步</span></li>
      <li><span id="step03">完成注册</span></li>
    </ol>
  </div>
  <div class="regMain formLayout">
    <h3 class="title"><span>完成注册登记</span></h3>
    <div class="textCenter">
    	<p><em>注册成功！</em></p>
       	<p>欢迎您加入阳光易购采购交易平台！请您进入&nbsp;<a href="<%=request.getContextPath()%>/ModelIndexController.do?method=toDeskTopIndex" class="icon room">电子采购室&nbsp;</a>并<a name="addMyInfo" href="javascript:void(0);"><span class="sysicon siEdit">完善资料</span></a>，然后<font color="red">&nbsp;提交审核&nbsp;</font>...</p>
        <p>我们会尽快审核您的信息！审核通过后，您将成为正式会员。</p>
    </div>
  </div>
  <div class="pageSubmitBtnBox"> 
  <button type="button" id="finishBtn" class="home"><span>首页</span></button>
  <button type="button" id="publicRequ" class="pubRequi"><span>发布需求</span></button>
  </div>
  
<script>
$(document).ready(function(){
	$("#finishBtn").click(function(){
		window.location.href=$('#initPath').val()+"/IndexViewController.do?method=index";
	});

	//发布采购需求
	$("#publicRequ").click(function(){
		if(common.isLogin(true)){
			window.location.href = $('#initPath').val() + "/RequirementController.do?method=toChooseCategory";
			return false;
		}
	})
})
</script>