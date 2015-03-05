<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>阳光易购电子采购与招标平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/promoter.css"/>
<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>
<div id="container">
	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
	
<div id="contentSub" class="container-left">
	<div class="promoterCol" id="promoter_1"></div>
	<div class="promoterCol" id="promoter_2"></div>
	<div class="promoterCol" id="promoter_3"></div>
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
    				<c:choose>
    					<c:when test="${user == null}">
    						<li><span id="regesterBtn" style="cursor:pointer;">注册采购大使</span></li>
    					</c:when>
    					<c:when test="${isPromoter}">
    						<li><span id="promoterBtn" style="cursor:pointer;">现在推广</span></li>
    					</c:when>
    					<c:otherwise>
    						<li><span id="applyBtn" style="cursor:pointer;">申请采购大使</span></li>
    					</c:otherwise>
    				</c:choose>
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
	<div class="promoterCol" id="promoter_7"></div>
	<div class="promoterCol" id="promoter_8"></div>
	<div class="promoterCol" id="promoter_9"></div>
</div>

	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
	<!--在线客服开始-->
    <%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
    <!--在线客服结束-->
</div>
</body>
</html>

<script type="text/javascript">
$(document).ready(function(){
	fnRemoveOtherMain(); 

	//加载页面
	$.each($('[id^=promoter_]'),function(i,n){
		$("#promoter_"+(i+1)).load($('#initPath').val()+"/view/staticpags/load/promoter/promoter_"+(i+1)+".jsp");
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