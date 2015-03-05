<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>发改委对下游行业价格干预可能接近尾声- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/supplierzx.jsp" title="供应商资讯" class="cmsHref_self">供应商资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01306ca3668522f1.jsp" title="发改委对下游行业价格干预可能接近尾声" class="cmsHref_self">发改委对下游行业价格干预可能接近尾声</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>发改委对下游行业价格干预可能接近尾声</h1>
					<div class="source">
						<span>发布时间：2011-06-08</span>
						<span>发布人：-  </span>
					</div>
					<p><p>&ldquo;六一&rdquo;刚过，恰逢实施了近两年的定频空调节能补贴政策正式取消，节能惠民新政正式实施，这使得空调价格上涨传言再次甚嚣尘上。从苏宁、国美在北京的各个卖场传来的消息称，虽然空调产品价格并未出现大幅度实质性上涨，但部分企业空调上涨后新的价格方案已经出台。</p>
<p>苏宁电器海淀桥销售部经理表示：&ldquo;空调价格从6月1日开始普遍有上涨势头，上涨价格在一两百元左右，往年，&lsquo;五一&rsquo;是每年例行的特惠日，但从今年6月1日开始，国家节能减排取消补贴，合资和国产的空调价格几乎都有上涨。&rdquo;市场人士指出，空调或将掀起一波涨价热潮。</p>
<p>对此，国家发改委农经司副司长胡恒洋表示：&ldquo;空调不是生活必需品，全行业成本上涨的情况下，价格上涨也是必然，但是空调的价格调控更多的还是市场作用结果，发改委不会过多干预。&rdquo;</p>
<p>分析人士认为，从日用品到白酒再到空调等产品，发改委对下游行业的价格干预有逐步放松的态势。而且，油价、电价等基础资源品的价格已陆续上调，再继续干预下游行业的产品价格恐怕会放大价格管制的负面作用。因此，上半年结束后，发改委很可能结束对绝大多数下游行业的价格干预。</p>
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