<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国工程机械正迎来扩大东盟市场好时机- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fbfbecc012fc34f0380026d.jsp" title="中国工程机械正迎来扩大东盟市场好时机" class="cmsHref_self">中国工程机械正迎来扩大东盟市场好时机</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>中国工程机械正迎来扩大东盟市场好时机</h1>
					<div class="source">
						<span>发布时间：2011-05-06</span>
						<span>发布人：-  </span>
					</div>
					<p><p>2011中国-东盟商务与投资峰会机械展览会将于5月30日在南宁举行。随着中国-东盟自由贸易区建设的深化，中国工程机械正逐步成为东盟国家采购商的首选，今年第八届中国-东盟博览会也将重点推介这一领域的合作机会。专家认为，中国工程机械企业正迎来扩大东盟市场的好时机。</p>
<p>随着东盟国家经济加速发展，基础设施投资规模的加大，机械及相关配件产品需求量大幅增加，东盟各国每年都要从中国进口大量机械类产品，去年中国-东盟自由贸易区全面建成后，有8种工程机械整机降低关税，协定税率为5%。随着中国-东盟自贸区的进一步建设，机械行业的进口税将降为零，东盟国家进口中国机械的成本更低。这些因素都为中国工程机械和运输车辆扩大东盟市场提供了更多机遇。</p>
<p>随着&ldquo;零关税&rdquo;时代的到来，有眼光的中国工程机械企业家都看到了走向东盟的新机遇。企业走向东盟，不仅能扩大东盟市场，而且可以更加便利地在国际区域范围内进行产业结构调整。</p>
<p>中国工程机械东盟市场好时机一帆机械忙前忙后。一帆机械作为国内外知名的破碎筛分设备制造商，为客户提供全系列破碎筛分设备，包括液压圆锥破碎机、颚式破碎机、反击式破碎机、立式冲击破碎机(制砂机)、移动式破碎站和振动筛，我们不仅为客户提供高性价比的产品，还提供一流的服务支持及解决方案。成套设备已出口到俄罗斯、蒙古、中亚、非洲等30多个国家和地区。通过参加中国-东盟博览会，一帆机械的产品和品牌的影响力在国内外不断增强，同时也促进了中国-东盟博览会的进一步发展。</p>
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