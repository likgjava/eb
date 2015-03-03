<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>

  <div class="regGuide supplier">
    <h3><span>供应商注册</span></h3>
    <ol>
      <li><span id="step01">阅读注册条款</span></li>
      <li><span  class="next finish">下一步</span></li>
      <li><span id="step02" class="finish">选择机构角色</span></li>
      <li><span  class="next finish">下一步</span></li>
      <li><span id="step03" class="finish">填写基本信息</span></li>
      <li><span  class="next finish">下一步</span></li>
      <li><span id="step04" class="finish">完成注册</span></li>
    </ol>
  </div>
  <div class="regMain">
    <%@ include file="/view/srplatform/portal/include/registration_rule.jsp" %>
  </div>
  <div class="pageSubmitBtnBox">
    	<button type="button" id="agreeBtn" class="agree"><span>同意</span></button>
    	<button type="button" id="return" class="home"><span>返回</span></button>
  </div>

<script>
	$(document).ready(function(){
		$("#agreeBtn").click(function(){
			$('#regContent').loadPage($('#initPath').val()+'/SupplierController.do?method=toRegisterPage&step=2');
		});

		$("#return").click(function(){
			window.location.href = $('#initPath').val()+'/RegistrationController.do?method=toRegistration';
		})
	});
</script>
