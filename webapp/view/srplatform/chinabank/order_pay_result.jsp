<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单支付结果</title>
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
			<div id="sysContent">
				<div id="bd">
				
		    	<div class="bd_top"></div>
		    	<!--主体部分start-->
		    	<div class="bd_context">
		    		<!--导航 position-->
		    		<div class="bd_title">
		    			<div class="bd_t_name"><img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_order_pay.jpg"/></div>
		    			<div class="bd_t_guide">
							<div class="bd_t_pos pos_1">确定订单去支付</div>
							<div class="bd_t_pos_spc">
							</div><div class="bd_t_pos pos_2_on">支付返回结果</div>
		    			</div>
		    		</div>
		    		<!--内容-->
		    		<div class="bd_main">
		    			<div class="bd_m_top"></div>
		    			<div class="bd_m_context">
		    				<div class="bd_offer_success_cls"></div>
		    				
		    				<div class="<c:if test="${payFlag==true}">bd_offer_success</c:if><c:if test="${payFlag!=true}">bd_offer_failed</c:if>">
		    				
		    					<!-- 支付成功 -->
		    					<c:if test="${payFlag==true}">
		    					<div class="bd_offer_s_status1">支付成功！</div>
		    					<div class="bd_offer_s_others">您的信息将在工作时间（周一到周五 9：00-17：00）尽快得到审核与处理。</div>
		    					</c:if>
		    					
		    					<!-- 支付失败 -->
		    					<c:if test="${payFlag==false}">
		    					<div class="bd_offer_s_status1">支付失败！请重试或联系相关人员处理。</div>
		    					</c:if>
		    					
		    					<div class="bd_offer_s_btn">
		    						<a href="<%=request.getContextPath()%>/ModelIndexController.do?method=toDeskTopIndex" class="bd_offer_s_scan">进入您的商务室管理您的订单</a>
		    					</div>
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
			</div>
			<!-- conBody结束 内容页面加载位置-->
    	</div>
	</div>
	<!--主要内容容器 结束--> 
  
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp"%>
  </div>
  <!--底部容器 结束--> 
</div>
<!--页面容器 结束--> 
</body>
</html>
