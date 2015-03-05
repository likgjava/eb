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
</head>
<body>
<div id="container">
  <!-- 头部开始 -->
  <div class="header">
	<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
  </div>
  <!-- 头部结束-->
  
  <!-- 接收初始路径参数，团购总金额参数 ，支付返回调用方法参数，业务ID参数-->
  <input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
  <input type="hidden" id="v_amount" value="${v_amount}" />
  <input type="hidden" id="v_back_req_method" value="${v_back_req_method}" />
  <input type="hidden" id="v_business_id" value="${groupBuyer.objId }" />
  
  <!--主要内容 开始-->
  <div id="sysContent" class="page">
	<div id="bd">
		<div class="bd_top"></div>
		<!--主体部分start-->
		<div class="bd_context">
			<!--导航 position-->
			<div class="bd_title">
				<div class="bd_t_name" style="font-size: 17px;">我要团购</div>
				<div class="bd_t_guide">
					<div class="bd_t_pos pos_1">填写订单信息</div>
					<div class="bd_t_pos_spc"></div>
					<div class="bd_t_pos pos_2_on">网银支付</div>
					<div class="bd_t_pos_spc"></div>
					<div class="bd_t_pos pos_3">团购成功</div>
				</div>
			</div>
			<!--内容-->
			<div class="bd_main">
				<div class="bd_m_context">
					<div class="bd_post_space2"></div>
					<div class="bd_post_form">
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>团购商品：</div>
							<div class="bd_post_form_context">
								<div class="bd_post_form_input_float">${groupBuyer.groupBuying.name }</div>
							</div>
						</div>
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>收货人：</div>
							<div class="bd_post_form_context">
								<div class="bd_post_form_input_float">${groupBuyer.receiveName }</div>
							</div>
						</div>
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>团购价：</div>
							<div class="bd_post_form_context">
								<div class="bd_post_form_input_float">￥<fmt:formatNumber value="${groupBuyer.groupBuying.groupPrice }" pattern="#,##0.00#" /></div>
							</div>
						</div>
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>数量：</div>
							<div class="bd_post_form_context">
								<div class="bd_post_form_input_float">${groupBuyer.amount}</div>
							</div>
						</div>
						<div class="bd_post_form_line">
							<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>总金额：</div>
							<div class="bd_post_form_context">
								<div class="bd_post_form_input_float">￥<fmt:formatNumber value="${v_amount}" pattern="#,##0.00#" /></div>
							</div>
						</div>
						<div class="bd_post_form_line">
	   						<div class="bd_post_form_title">发票抬头：</div>
	   						<div class="bd_post_form_context">
	   							<div class="bd_post_form_input_float"><input type="text" id="invoiceTitle" name="invoiceTitle" /></div>
	   						</div>												
	   					</div>
	   					<div class="bd_post_form_line">
	   						<div class="bd_post_form_title">发票名目：</div>
	   						<div class="bd_post_form_context">
	   							<div class="bd_post_form_input_float"><input type="text" id="invoiceItems" name="invoiceItems" /></div>
	   						</div>												
	   					</div>
	   					<div class="bd_post_form_line">
	   						<div class="bd_post_form_title">邮寄地址：</div>
	   						<div class="bd_post_form_context">
	   							<div class="bd_post_form_input_float"><input type="text" id="mailingAddress" name="mailingAddress" value="${groupBuyer.address}" /></div>
	   						</div>												
	   					</div>
					</div>
					<!--分割线2-->
					<div class="bd_post_space2"></div>
				
						<c:choose>
						<c:when test="${hasEnd}">
							<div class="bd_post_subscribe">
									<div class="bd_post_submit">
										<a class="bd_post_submit_btn">对不起，团购已结束</a>
										<div class="bd_post_submit_cls"></div>
									</div>
							</div>
						</c:when>
						<c:otherwise>
						<!-- 加载预支付页面，含支付参数 -->
						<div id="prepayDiv">
							<div class="bd_post_subscribe">	
								<div class="bd_post_submit">
									<a onclick="GroupBuyingPay.toPrePayView();" class="bd_post_submit_btn">确定</a>
								</div>
								<div class="bd_post_submit_cls"></div>
							</div>
						</div>
						</c:otherwise>
					</c:choose>
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

<script>
var GroupBuyingPay = {};
GroupBuyingPay.toPrePayView = function(){
	$('#invoiceTitle').attr('disabled', true);
	$('#invoiceItems').attr('disabled', true);
	$('#mailingAddress').attr('disabled', true);
	var remark1 = native2ascii('_'+$('#invoiceTitle').val()+'_'+$('#invoiceItems').val()+'_'+$('#mailingAddress').val());
	//加载预支付页面
	$('#prepayDiv').loadPage($('#initPath').val()+'/PayController.pay?method=toPrePayView&v_business_id='+$('#v_business_id').val()+'&v_back_req_method='+$('#v_back_req_method').val()+'&v_amount='+$('#v_amount').val(),{'remark1':remark1});
}
</script>