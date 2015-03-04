<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>保存品牌-阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/purchaseRequirement.css" media="screen"/>
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
	<div class="page" style="min-height:500px!important;">
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
		    			<div class="bd_t_name"><img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_brand.jpg"/></div>
		    			<div class="bd_t_guide">
		    				<div class="bd_t_pos pos_1">填写品牌信息</div>
		    				<div class="bd_t_pos_spc"></div>
		    				<div class="bd_t_pos pos_2_on">提交或保存品牌信息</div>
		    			</div>
		    		</div>
		    		<!--内容-->
		    		<div class="bd_main">
		    			<div class="bd_m_top"></div>
		    			<div class="bd_m_context">
		    				<div class="bd_offer_success_cls"></div>
		    				<div class="bd_offer_success">
		    					<div class="bd_offer_s_status1">操作成功，您的品牌信息已
		    					<c:if test="${goodsBrand.auditStatus=='01'}">提交审核</c:if>
		    					<c:if test="${goodsBrand.auditStatus!='01'}">保存</c:if>！
		    					</div>
		    					<div class="bd_offer_s_others">提交的信息将在工作时间（周一到周五 9：00-17：00）2小时内发布上网，非工作时间24小时内发布。</div>
		    					<div class="bd_offer_s_btn">
		    						<a href="<%=request.getContextPath()%>/GoodsBrandController.do?method=toCreateOrUpdateView" class="bd_offer_s_goon">继续添加品牌信息</a>&nbsp;&nbsp;
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
			<!-- conBody结束 内容页面加载位置-->
    	</div>
	</div>
	<!--主要内容容器 结束--> 
  
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
  </div>
  <!--底部容器 结束--> 
</div>
<!--页面容器 结束--> 
</body>
</html>