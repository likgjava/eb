<%@ page pageEncoding="UTF-8"%>
 	<form action="https://pay3.chinabank.com.cn/PayGate?encoding=UTF-8" method="POST" name="E_FORM">
  	<!--以下几项为网上支付重要信息，信息必须正确无误，信息会影响支付进行！-->
	  <input type="hidden" name="v_md5info"    value="${v_md5info }" size="100"/>
	  <input type="hidden" name="v_mid"        value="${v_mid}"/>
	  <input type="hidden" name="v_oid"        value="${v_oid}"/>
	  <input type="hidden" name="v_amount"     value="${v_amount }"/>
	  <input type="hidden" name="v_moneytype"  value="${v_moneytype }"/>
	  <input type="hidden" name="v_url"        value="${v_url}"/>
	  <input type="hidden" name="remark1" 	   value="${remark1}"/>
	  <input type="hidden" name="remark2"      value="${remark2}"/>
  	</form>
	<!--提交区域-->
	<div class="bd_post_subscribe">	
	  	<div class="bd_post_submit">
	  		<script>
	  			function goToPayFor(){
					$("#bd_post_submit_btn").replaceWith('<strong style="color:green;">跳转中...</strong>');
	  				document.E_FORM.submit();
	  			}
	  		</script>
			<a onclick="goToPayFor();" class="bd_post_submit_btn" id="bd_post_submit_btn" >去网上银行付款</a>
		</div>
		<div class="bd_post_submit_cls"></div>	
	</div>											
