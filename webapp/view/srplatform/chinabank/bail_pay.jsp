<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>支付页面-阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/base/css/plugInEdit.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/purchaseRequirement.css"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
</head>
<body>
<!--页面容器 开始-->
<div id="container"> 

  <!-- 接收初始路径参数，保证金金额参数 ，支付返回调用方法参数，业务ID参数-->
  <input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
  <input type="hidden" id="v_amount" value="${v_amount}" />
  <input type="hidden" id="v_back_req_method" value="${v_back_req_method}" />
  <input type="hidden" id="v_business_id" value="${v_business_id}" />
  
  <!--头部容器 开始-->
  <div class="header"> 
  		<input type="hidden" id="isOutCss" value="${param.isOutCss}"/>
  		<c:choose>
	  		<c:when test="${!isOutCss}"><jsp:include page="/view/srplatform/portal/include/backgroundmenu.jsp"></jsp:include></c:when>
  			<c:otherwise><%@ include file="/view/srplatform/portal/include/main_header_index.jsp"%></c:otherwise>
  		</c:choose>
  </div>
  <!--头部容器 结束--> 
	<!--主要内容容器 开始-->
	<div class="page" style="min-height:800px!important;">
		<div class="gridBox">
			<!-- 三级菜单 -->
			<div class="grid16_3 hidden" id="menuList">
				<div class="menuList"><ul></ul></div>
			</div>
			
			<!-- conBody开始 内容页面加载位置 -->
			<div class="grid16_16" id="conBody">
			
		<div id="bd">
    	<div class="bd_top"></div>
    	<!--主体部分start-->
    	<div class="bd_context">
    		<!--导航 position-->
    		<div class="bd_title">
				<div class="bd_t_name"><img  src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_pommi_pay.jpg" /></div>
				<div class="bd_t_guide">
				
				<div class="bd_t_pos pos_1_on">确定支付保证金</div>
				<div class="bd_t_pos_spc">
				</div><div class="bd_t_pos pos_2">支付返回结果</div>
				
				</div>
			</div>
    		<!--内容-->
    		<div class="bd_main">
    			<div class="bd_m_context">	
    				<div class="bd_post_space2"></div>										
    				<div class="bd_post_form">
    				
    				<div class="bd_post_form_line">
   						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>采购项目：</div>
   						<div class="bd_post_form_context">
   							<div class="bd_post_form_input_float">${proj.projName}【${proj.projCode}】</div>
   						</div>												
   					</div>
   					
   					<div class="bd_post_form_line">
   						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>付款单位：</div>
   						<div class="bd_post_form_context">
   							<div class="bd_post_form_input_float">${proj.buyersName}</div>
   						</div>												
   					</div>
   					
   					<div class="bd_post_form_line">
   						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>收款单位：</div>
   						<div class="bd_post_form_context">
   							<div class="bd_post_form_input_float">${proj.agencies.orgName}</div>
   						</div>												
   					</div>
   					
   					<div class="bd_post_form_line">
   						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>项目负责人：</div>
   						<div class="bd_post_form_context">
   							<div class="bd_post_form_input_float">${proj.manager.name}</div>
   						</div>												
   					</div>
   					
   					<div class="bd_post_form_line">
   						<div class="bd_post_form_title"><span class="bd_post_form_must">&nbsp;</span>保证金金额(元)：</div>
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
   							<div class="bd_post_form_input_float"><input type="text" id="mailingAddress" name="mailingAddress" /></div>
   						</div>												
   					</div>
   					
					</div>
					<!--分割线2-->
					<div class="bd_post_space2"></div>
					
					<!-- 加载预支付页面，含支付参数 -->
					<div id="prepayDiv">
						<div class="bd_post_subscribe">	
							<div class="bd_post_submit">
								<a onclick="BailPay.toPrePayView();" class="bd_post_submit_btn">确定</a>
							</div>
							<div class="bd_post_submit_cls"></div>
						</div>
					</div>
				</div>
				<div class="bd_m_bottom"></div>										
			</div>								
		</div>
 		<!--主体部分end-->
		<div class="bd_bottom"></div>
		
		</div>
			</div>
			<!-- conBody结束 内容页面加载位置-->
    	</div>
	</div>
	<!--主要内容容器 结束--> 
  
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
  </div>
  <!--底部容器 结束-->
  <!--在线客服开始-->
  <%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
  <!--在线客服结束-->
</div>
<!--页面容器 结束--> 

<script type="text/javascript">
var BailPay = {};
BailPay.toPrePayView = function(){
	$('#invoiceTitle').attr('disabled', true);
	$('#invoiceItems').attr('disabled', true);
	$('#mailingAddress').attr('disabled', true);
	var remark1 = native2ascii('_'+$('#invoiceTitle').val()+'_'+$('#invoiceItems').val()+'_'+$('#mailingAddress').val());
	//加载预支付页面
	$('#prepayDiv').loadPage($('#initPath').val()+'/PayController.pay?method=toPrePayView&v_business_id='+$('#v_business_id').val()+'&v_back_req_method='+$('#v_back_req_method').val()+'&v_amount='+$('#v_amount').val(),{'remark1':remark1});
}
</script>
</body>
</html>