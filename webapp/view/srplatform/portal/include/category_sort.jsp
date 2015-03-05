<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@ page contentType="text/html;charset=UTF-8" %><%@page import="java.util.*,com.gpcsoft.goods.goodsclass.domain.GoodsClass" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/listDetail.css" />

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/goods/showgoods/show_goods_list.js"></script>
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
		<div id="contentSub"  class="hidden"></div>
		<div id="contentMain">
			<div id="article">
			<c:choose>
				<c:when test="${gclist == null}"><div class="sorry">暂无分类信息。</div></c:when>
				<c:otherwise>
					<c:forEach var="gc" items="${gclist}">
						<c:if test="${gc.children != null && fn:length(gc.children) > 0}">
							<div class="market-cat">
							   <div class="hd">
									<h4>${gc.goodsClassName}</h4>
							   </div>
							   <div class="bd">
							        <ul>
							        	<c:forEach var="gc2" items="${gc.children}">
							             <li class="g-u section">
							                   <ul>
							                      <li class="sub"><a href="javascript:void(0);" onclick="searchByGoodsClass('${gc2.goodsClassCode}');return false;">${gc2.goodsClassName}</a></li> 
							                      <c:forEach  var="gc3" items="${gc2.children}">
								 					<li><a href="javascript:void(0);" onclick="searchByGoodsClass('${gc3.goodsClassCode}');return false;" >${gc3.goodsClassName}</a></li>
								 				</c:forEach>
							                   </ul>
							            </li>  
							            </c:forEach> 
							        </ul>
							    </div> 
							</div>
						</c:if>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
		<div id="contentSupp" class="hidden"></div>
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
<script type="text/javascript">
	$("#article").find("div.market-cat").mouseover(function(){
		$(this).addClass("hover");
	}).mouseout(function(){
		$(this).removeClass("hover");
	})
	//点击某一个分类
	function searchByGoodsClass(goodsClassCode){
		window.location.href = $('#initPath').val()+'/GoodsShowController.do?method=toGoodsList&rp=21&page=1&goodsClassCode='+goodsClassCode;
		$('.epsDialogClose').trigger('click');
	}
</script>
</body>
</html>