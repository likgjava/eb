<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光易购采购交易平台</title>
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
	<c:choose>
		<c:when test="${!isOutCss}"><jsp:include page="/view/srplatform/portal/include/backgroundmenu.jsp"></jsp:include></c:when>
		<c:otherwise><%@ include file="/view/srplatform/portal/include/main_header_index.jsp"%></c:otherwise>
	</c:choose>
  </div>
  <!--头部容器 结束--> 
	<!--主要内容容器 开始-->
	<div class="page" style="min-height:400px!important;">
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
		    			<div class="bd_t_name"><img src="<%=request.getContextPath()%>/view/resource/skin/skin07/img/step1_goods.jpg" /></div>
		    			<div class="bd_t_guide">
		    				<div class="bd_t_pos pos_1">选择类目</div>
		    				<div class="bd_t_pos_spc"></div>
		    				<div class="bd_t_pos pos_2">填写商品信息</div>
		    				<div class="bd_t_pos_spc"></div>
		    				<div class="bd_t_pos pos_3_on">提交商品信息</div>
		    			</div>
		    		</div>
		    		<!--内容-->
		    		<div class="bd_main">
		    			<div class="bd_m_top"></div>
		    			<div class="bd_m_context">
		    				<div class="bd_offer_success_cls"></div>
		    				<div class="bd_offer_success">
		    				<c:choose>
		    					<c:when test="${!empty param.objId}">
		    						<div class="bd_offer_s_status1">商品修改成功！</div>
		    						<div class="bd_offer_s_btn">
			    						<a href="javascript:void()0;" id="previewGoods" goodsid="${param.objId}" class="bd_offer_s_goon">预览商品信息</a>&nbsp;&nbsp;
				    					<a href="<%=request.getContextPath()%>/ModelIndexController.do?method=toDeskTopIndex" class="bd_offer_s_scan">进入您的商务室管理您发布的商品信息</a>
			    					</div>
		    					</c:when>
		    					<c:otherwise>
			    					<c:if test="${param.goodsStatus=='00'}">
				    					<div class="bd_offer_s_status1">商品保存成功！</div>
				    					<div class="bd_offer_s_others">您发布的商品尚未提交，请进入商务室将商品提交给管理员审核</div>
			    					</c:if>
			   						<c:if test="${param.goodsStatus=='01'}">
			   							<div class="bd_offer_s_status1">商品提交成功！</div>
				    					<div class="bd_offer_s_others">您发布的商品已经提交审核</div>
			   						</c:if>
			   						<c:if test="${param.goodsStatus=='02'}">
			   							<div class="bd_offer_s_status1">商品提交成功！</div>
				    					<div class="bd_offer_s_others">您发布的商品已经成为正式商品</div>
			   						</c:if>
			    					<div class="bd_offer_s_btn">
			    						<a href="<%=request.getContextPath()%>/GoodsController.do?method=toCreateGoods" class="bd_offer_s_goon">继续发布商品信息</a>&nbsp;&nbsp;
			    						<a href="javascript:void()0;" id="previewGoods" goodsid="${param.goodsId}" class="bd_offer_s_goon">预览商品信息</a>&nbsp;&nbsp;
			    					</div>
		    					</c:otherwise>
		    					</c:choose>
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

<script>
$(document).ready(function(){	
	//预览商品
	$('#previewGoods').click(function(){
		window.open($('#initPath').val()+"/GoodsShowController.do?method=getGoodsInfo&isShowPic=true&objId=" + $(this).attr('goodsid')+"&viewName=goodsPreview");
	})
})
</script>

</body>
</html>