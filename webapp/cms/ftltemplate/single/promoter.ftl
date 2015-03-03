<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="java.util.List,com.gpcsoft.plugin.acegi.AuthenticationHelper,com.gpcsoft.srplatform.auth.domain.User"%>
<link rel="stylesheet" type="text/css" href="${base}/view/resource/skin/skin07/css/promoter.css"/>
<script type="text/javascript">
$(document).ready(function(){
	fnRemoveOtherMain(); 

	 $.each($('[id^=promoter_]'),function(i,n){
	 	$("#promoter_"+(i+1)).load("${load}/promoter/promoter_"+(i+1)+".html");
	 });
	 
	 //注册
	$("#regesterBtn").click(function(){
		window.open($('#initPath').val()+'/RegistrationController.do?method=toRegistration');
	})
	//已登录情况下申请成为采购大使。
	$("#applyBtn").click(function(){
		$('#promotMain').loadPage($('#initPath').val()+'/PromoterController.do?method=toApplyPromoterView');
	})
		
	//推广
	$("#promoterBtn").click(function(){
		if(common.isLogin(true)) {
			$("#promotMain").loadPage($('#initPath').val()+'/PromoterController.do?method=toPromoterDoingPage&fromWeb=yes');
		
		}
	})
})
</script>
<%
	User user = (User)request.getAttribute("user");
%>
<div id="contentSub" class="container-left">
	<div class="promoterCol" id="promoter_1">
    </div>
	<div class="promoterCol" id="promoter_2">
    </div>
	<div class="promoterCol" id="promoter_3">
    </div>
</div>
<div id="contentMain" class="containerCenter">
	<div id="conTitle"></div>
    <div id="conBody">
    	<div class="promoterCenterTopCol" id="promotMain">
    	    <h1>快速推广流程轻松3步</h1>
    		<div>
    			<ul class="liuchen">
    			<li><h4 class="firstH4">注册为采购大使</h4><p>注册为推广员，可以获得推广积分</p></li>
    			<li><h4 class="secondH4">登录，进入推广中心</h4><p>推广中心位于"我的商务室"里面的"我的推广"</p></li>
    			<li><h4 class="threeH4">管理已推广客户记录</h4><p>记录并发送采购平台介绍邮件</p></li>
    			</ul>
    			<ul class="tuiguanBtn">
    				<% if(user==null){%>
					<li><span id="regesterBtn">注册采购大使</span></li>
				<%}else{ 
					String isPromoter = (String)request.getAttribute("isPromoter");
					if(isPromoter==null){
					%>
					<li><span id="applyBtn">申请采购大使</span></li>
					<%}
					else{%>
						<li><span id="promoterBtn">现在推广</span></li>
					<%}
				}%>	
					
				</ul>
    		</div>
    	</div>
    	<div class="promoterCenterCol" id="promoter_4">
    	</div>
    	<div class="promoterCenterCol" id="promoter_5">
    	</div>
    	<div class="promoterCenterCol" id="promoter_6">
    	</div>
    </div>
</div>
<div id="contentSupp" class="containerRight show">
	<div class="promoterCol" id="promoter_7">
	</div>
	<div class="promoterCol" id="promoter_8">
	</div>
	<div class="promoterCol" id="promoter_9">
	</div>
</div>
