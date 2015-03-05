<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/>
<title>阳光易购-中国权威的电子采购与招标第三方公共服务平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/pubProject.css" media="screen"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/smallscale/groupbuying/group_buyer_form.js'></script>
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
	<div id="bd">
		<div class="bd_top"></div>
		<!--主体部分start-->
		<div class="bd_context">
			<!--导航 开始-->
			<div class="bd_title">
				<div class="bd_t_name" style="font-size: 17px;">我要团购</div>
				<div class="bd_t_guide">
					<div class="bd_t_pos pos_1">填写订单信息</div>
					<div class="bd_t_pos_spc"></div>
					<div class="bd_t_pos pos_2">网银支付</div>
					<div class="bd_t_pos_spc"></div>
					<div class="bd_t_pos pos_3_on">团购成功</div>
				</div>
			</div>
			<!--导航 结束-->
			<!--内容-->
			<div class="bd_main">
			<div class="bd_m_top"></div>
				<div class="bd_m_context">
					<div class="bd_offer_success_cls"></div>
					<div class="<c:if test="${payFlag==true}">bd_offer_success</c:if><c:if test="${payFlag!=true}">bd_offer_failed</c:if>">
						<!-- 支付成功 -->
						<c:if test="${payFlag==true}">
							<div class="bd_offer_s_status1">支付成功！</div>
							<div class="bd_offer_s_others">您的团购商品我们将以最快的速度发送到你手中。</div>
						</c:if>
						
						<!-- 支付失败 -->
						<c:if test="${payFlag==false}">
							<div class="bd_offer_s_status1">支付失败！请重试或联系相关人员处理。</div>
						</c:if>
					</div>
					<div class="bd_offer_success_cls_bottom"></div>
				</div>
				<div class="bd_m_bottom"></div>
			</div>
		</div>
		<!--主体部分end-->
		<div class="bd_bottom"></div>
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