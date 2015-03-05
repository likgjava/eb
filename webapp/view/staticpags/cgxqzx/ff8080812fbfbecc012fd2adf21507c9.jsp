<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国劳工成本增加 西欧纺织服装采购商陷入困境- 【阳光易购】</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/skin07/css/seller.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/listDetail.css" />
<!--JS-->
<script type="text/javascript" src='/view/srplatform/portal/include/common.js'></script>
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
		<div id="contentSub" class="index3pa">
			<%@ include file="/view/staticpags/load/left_cms.jsp" %>
		</div>
		<div id="contentMain" class="index3pa">
			<div id="conTitle">
				<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fd2adf21507c9.jsp" title="中国劳工成本增加 西欧纺织服装采购商陷入困境" class="cmsHref_self">中国劳工成本增加 西欧纺织服装采购商陷入困境</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>中国劳工成本增加 西欧纺织服装采购商陷入困境</h1>
					<div class="source">
						<span>发布时间：2011-05-09</span>
						<span>发布人：-  </span>
					</div>
					<p><p>5月9日讯　据法国《纺织报》日前报道称，当前全球性金融和经济危机还未结束，接踵而来的是地缘政治的动荡，原材料价格指数的上涨，纺织业如同石油业经历着前所未有的价格波动。在此经济、社会和政治环境下，加之中国的变化，给西欧时尚品牌供应采购商带来了巨大的影响，使其处于两难境地。</p>
<p>报道说，2010年中国劳工成本增加价格上涨，中国的工厂重点转向国内市场，扩大内需。中国不再是分包的乐园。作为对中国订单的补充，纺织服装采购商们将部分欧洲订单转向孟加拉、巴基斯坦、老挝和印尼等国，但这些国家的生产能力和质量都不能与中国同仁而与，其作用也不能取代中国，况且有些国家还没有原材料，如孟加拉就没有棉花。离欧洲距离较近的马格里布五国(阿、摩、突、埃及和利比亚)，虽然其交货期短，质量有保障，反应快，懂时尚，但目前地区政治不稳定，没有能力或很少能承接纺织业上游的生产。阿拉伯国家的变革使发包人转向土耳其，该国纺织产业完整，是发包人大举进入的市场，但又存在市场饱和的危险。目前可推进的地区是东欧国家，这些国家能收回部分地中海和其它国家的订单。</p>
<p>报道认为，过去很长时间，采购商首要追求的是最好的价格，面对上述形势，采购商必须改变工作的优先重点，引导企业重新评估其职能。今后，采购商应优先考虑安全问题。</p>
</p>
				</div>
			</div>
		</div>
	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
</div>
</body>

</html>

<script>
	// 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
</script>